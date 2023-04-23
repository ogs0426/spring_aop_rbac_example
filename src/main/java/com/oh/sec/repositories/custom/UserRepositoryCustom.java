package com.oh.sec.repositories.custom;

import com.oh.sec.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepositoryCustom
{
    public void register(User user, String roleName);
}
