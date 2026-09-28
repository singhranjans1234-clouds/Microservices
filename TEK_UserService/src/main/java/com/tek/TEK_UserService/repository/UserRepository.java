package com.tek.TEK_UserService.repository;

import com.tek.TEK_UserService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {


}
