package io.favorites.folder.domain.custom;

import org.springframework.data.rest.core.config.Projection;

import io.favorites.folder.domain.Favorites2;

@Projection(name = "idProjection", types = { Favorites2.class })
public interface FolderIdView{
	Long getId();
}
