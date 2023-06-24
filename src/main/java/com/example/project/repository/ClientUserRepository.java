package com.example.project.repository;

import com.example.project.entity.ClientUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientUserRepository extends JpaRepository<ClientUser, Long> {
    boolean existsByPhoneNumber(String phoneNumber);
}
