package com.br.clinica.auth.repository;

import com.br.clinica.auth.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleModel,UUID> {

}
