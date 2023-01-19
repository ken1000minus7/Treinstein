package com.example.treinstein;

import com.example.treinstein.core.User;
import com.example.treinstein.db.UserDAO;
import com.example.treinstein.resources.TreinsteinResource;
import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TreinsteinApplication extends Application<TreinsteinConfiguration> {

    private final HibernateBundle<TreinsteinConfiguration> hibernateBundle = new HibernateBundle<>(User.class) {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(TreinsteinConfiguration treinsteinConfiguration) {
            return treinsteinConfiguration.getDataSourceFactory();
        }
    };

    public static void main(String[] args) throws Exception {
        new TreinsteinApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<TreinsteinConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(TreinsteinConfiguration treinsteinConfiguration, Environment environment) {
        UserDAO userDAO = new UserDAO(hibernateBundle.getSessionFactory());
        TreinsteinResource resource = new TreinsteinResource(userDAO);
        environment.jersey().register(resource);
    }
}
