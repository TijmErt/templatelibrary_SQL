package com.sjitzooi.templatelibrary_sql.service;

import com.sjitzooi.templatelibrary_sql.entity.Review;
import com.sjitzooi.templatelibrary_sql.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> findAllFromUser(String user_id) {
        return reviewRepository.findByAuthor_Id(user_id);
    }
}
