package com.sjitzooi.templatelibrary_sql.repository;

import com.sjitzooi.templatelibrary_sql.entity.BookMarkList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMarkListRepository extends JpaRepository<BookMarkList, String> {
    public List<BookMarkList> findBookMarkListsByListOwner_Id(String listOwnerId);
}
