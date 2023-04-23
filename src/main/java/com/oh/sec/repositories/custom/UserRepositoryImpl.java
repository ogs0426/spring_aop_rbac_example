package com.oh.sec.repositories.custom;

import com.oh.sec.models.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void register(User user, String roleName) {
        Role role = entityManager.createQuery("SELECT r FROM Role r WHERE r.name = :name", Role.class)
                .setParameter("name", roleName)
                .getSingleResult();
        user.setRoles(new HashSet<>() {{
            add(role);
        }});
        entityManager.merge(user);
    }
}
