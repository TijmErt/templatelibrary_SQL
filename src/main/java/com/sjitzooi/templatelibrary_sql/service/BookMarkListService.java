package com.sjitzooi.templatelibrary_sql.service;

import com.sjitzooi.templatelibrary_sql.entity.BookMarkList;
import com.sjitzooi.templatelibrary_sql.repository.BookMarkListRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookMarkListService {

    private static final String ERROR_MESSAGE_SERVICE_LAYER= "BookMarkListService:";

    private BookMarkListRepository bookMarkListRepository;

    @Autowired
    public BookMarkListService(BookMarkListRepository bookMarkListRepository) {
            this.bookMarkListRepository = bookMarkListRepository;
    }

    public List<BookMarkList> getAllFromUser(String userId) {
        try{
            return bookMarkListRepository.findBookMarkListsByListOwner_Id(userId);
        }
        catch(Exception e){
            log.error(ERROR_MESSAGE_SERVICE_LAYER +" getAllFromUser: {}", e.getMessage());
            throw e;
        }
    }
    public  BookMarkList getBookMarkListById(String id) {
        try{
            return bookMarkListRepository.findById(id).isPresent() ? bookMarkListRepository.findById(id).get() : null;
        }
        catch(Exception e){
            log.error(ERROR_MESSAGE_SERVICE_LAYER +" getBookMarkListById: {}", e.getMessage());
            throw e;
        }
    }
}
