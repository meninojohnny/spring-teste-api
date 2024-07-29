package com.johnny.teste.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.johnny.teste.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    
}
