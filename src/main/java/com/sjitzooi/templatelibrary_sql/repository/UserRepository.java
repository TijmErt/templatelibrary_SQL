package com.sjitzooi.templatelibrary_sql.repository;

import com.sjitzooi.templatelibrary_sql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
