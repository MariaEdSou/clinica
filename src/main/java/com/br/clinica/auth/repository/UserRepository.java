package com.br.clinica.auth.repository;

import com.br.clinica.auth.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, String> {

    Optional<UserModel> findByUsername(String username);

}
