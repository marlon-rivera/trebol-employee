package com.trebol.auth.adapters.driven.jpa.mysql.repository;

import com.trebol.auth.adapters.driven.jpa.mysql.entity.EmployeeEntity;
import com.trebol.auth.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, String> {

    Optional<EmployeeEntity> findByIdOrEmail(String id, String email);
    Optional<EmployeeEntity> findByEmail(String email);
    List<EmployeeEntity> findAllByRole(Role role);
}
