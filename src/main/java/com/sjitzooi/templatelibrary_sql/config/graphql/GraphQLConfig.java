package com.sjitzooi.templatelibrary_sql.config.graphql;
import graphql.kickstart.servlet.apollo.ApolloScalars;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQLConfig {

    @Bean
    public GraphQLScalarType uploadScalar() {
        return ApolloScalars.Upload;
    }

}
