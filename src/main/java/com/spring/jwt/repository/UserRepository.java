package com.spring.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.jwt.model.UserDto;

@Repository
public interface UserRepository extends JpaRepository<UserDto, Long>{

	UserDto findByUserName(String userName);
}
