package com.example.demo.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
@EnableJpaRepositories
public interface AuthRepository extends JpaRepository<User, Integer>{

	List<User> findByusername(String username);


}
