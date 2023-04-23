package com.oh.sec.repositories;

import com.oh.sec.models.User;
import com.oh.sec.repositories.custom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom
{
    public List<User> findAll();
    public Optional<User> findById(Integer id);

    public Optional<User> findByName(String s);

    // Optional<User> findByEmail(String email);
}
