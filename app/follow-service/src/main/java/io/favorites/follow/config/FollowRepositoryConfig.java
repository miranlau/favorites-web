package io.favorites.follow.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import io.favorites.follow.domain.Follow;
import io.favorites.follow.domain.User;

@Configuration
public class FollowRepositoryConfig extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Follow.class).exposeIdsFor(User.class);
    }
}
