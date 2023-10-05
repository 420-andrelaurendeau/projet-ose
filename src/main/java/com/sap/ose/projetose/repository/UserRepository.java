
package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findAllByEmailEqualsIgnoreCase(String email);
}
