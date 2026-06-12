package com.example.userage.repository;

import com.example.userage.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA gives us CRUD + pagination for free, so the interface stays
 * empty. The service depends only on this contract — easy to mock in tests.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
