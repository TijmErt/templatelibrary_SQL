import http from 'k6/http';
import { check, sleep } from 'k6';

// Replace with your actual GraphQL endpoint
//172.17.0.1

const BASE_URL = `http://${__ENV.BASE_URL || "localhost:8080"}/graphql`;

// GraphQL queries for getTemplatePost and getFilteredTemplatePosts
const GET_TEMPLATE_POST_QUERY = `
  query GetTemplatePost($id: String!) {
    getTemplatePost(id: $id) {
      id
      title
      description
      createdDate
    }
  }
`;

const GET_FILTERED_TEMPLATE_POSTS_QUERY = `
  query GetFilteredTemplatePosts($pageInfo: PageInfoInput, $searchTerm: String) {
    getFilteredTemplatePosts(pageInfo: $pageInfo, searchTerm: $searchTerm) {
      posts {
        id
        title
        description
        createdDate
      }
      pageInfo {
        total
        totalPages
        hasNextPage
        hasPreviousPage
      }
    }
  }
`;
export const options = {
    stages: [
        { duration: '1m', target: 50 },
        { duration: '2m', target: 50 },
        { duration: '1m', target: 0 },
    ],
    thresholds: {
        'http_req_duration': ['p(95)<600'],
        'http_req_failed': ['rate<0.01'],
    },
};

export default function () {
    const token ='Basic dXNlcjpwYXNzd29yZA==';
    const headers = {
        'Authorization': token,
        'Content-Type': 'application/json'
    };
    // Test for getting a single TemplatePost
    const getTemplatePostPayload = JSON.stringify({
        query: GET_TEMPLATE_POST_QUERY,
        variables: { id: 'tempPost-id-1' }
    });

    const getTemplatePostResponse = http.post(BASE_URL, getTemplatePostPayload, {
        headers: headers,
    });

    // Check the response for getTemplatePost
    check(getTemplatePostResponse, {
        'getTemplatePost status is 200': (r) => r.status === 200,
        'getTemplatePost returns data': (r) => r.json('data') !== null,
        'contains id': (r) => r.json('data.getTemplatePost.id') !== undefined,
        'contains title': (r) => r.json('data.getTemplatePost.title') !== undefined,
    });

    // Test for getting filtered TemplatePosts
    const getFilteredTemplatePostsPayload = JSON.stringify({
        query: GET_FILTERED_TEMPLATE_POSTS_QUERY,
        variables: {
            pageInfo: {
                limit:4,
                page:0,
                total:0,
                totalPages:0,
                sortField:"avgRating",
                sortOrder:"desc",
                hasNextPage:true,
                hasPreviousPage:false},
            searchTerm: '',
        },
    });

    const getFilteredTemplatePostsResponse = http.post(BASE_URL, getFilteredTemplatePostsPayload, {
        headers: headers,
    });

    // Check the response for getFilteredTemplatePosts
    check(getFilteredTemplatePostsResponse, {
        'getFilteredTemplatePosts status is 200': (r) => r.status === 200,
        'getFilteredTemplatePosts returns data': (r) => r.json('data') !== null,
    });

    // Sleep to simulate user thinking time
    sleep(1);
}


