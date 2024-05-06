package com.Authentication.SpringSecurityJWT.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Authentication.SpringSecurityJWT.Entity.User;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User>findByEmail(String email);
}
