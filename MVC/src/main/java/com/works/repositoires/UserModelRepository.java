package com.works.repositoires;

import com.works.entities.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserModelRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByEmailEqualsIgnoreCase(String email);
}