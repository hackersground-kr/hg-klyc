package org.example.hgproject.user.repository;

import org.example.hgproject.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity ,Long> {
    Boolean existsByUserName(String userName);

    UserEntity findByUserName(String userName);
}
