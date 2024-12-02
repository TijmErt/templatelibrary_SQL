package com.sjitzooi.templatelibrary_sql.service;

import com.sjitzooi.templatelibrary_sql.entity.BookMarkList;
import com.sjitzooi.templatelibrary_sql.repository.BookMarkListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookMarkListService {


    private BookMarkListRepository bookMarkListRepository;

    @Autowired
    public BookMarkListService(BookMarkListRepository bookMarkListRepository) {
        this.bookMarkListRepository = bookMarkListRepository;
    }

    public List<BookMarkList> getAllFromUser(String userId) {
        return bookMarkListRepository.findBookMarkListsByListOwner_Id(userId);
    }
    public  BookMarkList getBookMarkListById(String id) {
        return bookMarkListRepository.findById(id).isPresent() ? bookMarkListRepository.findById(id).get() : null;
    }
}
