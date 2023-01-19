package com.example.treinstein.resources;

import com.example.treinstein.core.User;
import com.example.treinstein.db.UserDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TreinsteinResource {

    private final UserDAO userDAO;

    public TreinsteinResource(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Path("/user")
    @GET
    @UnitOfWork
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    @Path("/user")
    @POST
    @UnitOfWork
    public long addUser(User user) {
        return userDAO.create(user);
    }

}


