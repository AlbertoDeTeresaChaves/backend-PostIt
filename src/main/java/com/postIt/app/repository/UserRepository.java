package com.postIt.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.postIt.app.models.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	
	
}
