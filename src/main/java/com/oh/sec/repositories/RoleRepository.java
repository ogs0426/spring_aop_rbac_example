package com.oh.sec.repositories;

import com.oh.sec.models.Role;
import com.oh.sec.models.User;
import com.oh.sec.repositories.custom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer>
{
    public Optional<Role> findById(Integer id);
    public Optional<Role> findByName(String s);
}
