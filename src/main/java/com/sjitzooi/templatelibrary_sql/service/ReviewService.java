package com.sjitzooi.templatelibrary_sql.service;

import com.sjitzooi.templatelibrary_sql.entity.Review;
import com.sjitzooi.templatelibrary_sql.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ReviewService {

    private static final String ERROR_MESSAGE_SERVICE_LAYER= "ReviewService:";

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    private ReviewRepository reviewRepository;



    public List<Review> findAllFromUser(String user_id) {
        try{
            return reviewRepository.findByAuthor_Id(user_id);
        }
        catch(Exception e){
            log.error(ERROR_MESSAGE_SERVICE_LAYER +" findAllFromUser: {}",e.getMessage());
            throw e;
        }
    }
}
