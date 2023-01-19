package com.example.treinstein.db;

import com.example.treinstein.core.User;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDAO extends AbstractDAO<User> {
    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public User findById(long id) {
        return get(id);
    }

    public long create(User user) {
        return persist(user).getId();
    }

    public List<User> findAll() {
        return list(namedTypedQuery("findAll"));
    }

}
