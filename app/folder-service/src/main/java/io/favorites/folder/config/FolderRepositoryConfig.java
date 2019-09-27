package io.favorites.folder.config;

import io.favorites.folder.domain.Favorites;
import io.favorites.folder.domain.Favorites2;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class FolderRepositoryConfig extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Favorites.class).exposeIdsFor(Favorites2.class);
    }
}
