package com.favorites.remote;


import java.util.List;

import com.favorites.domain.Favorites;

public interface FolderService {

	Favorites findById(long  id);

	List<Favorites> findByUserId(Long userId);

	List<Favorites> findByUserIdOrderByLastModifyTimeDesc(Long userId);

	List<Favorites> findByUserIdOrderByLastModifyTimeAsc(Long userId);

	Favorites findByUserIdAndName(Long userId,String name);

    void reduceCountById(Long id, Long lastModifyTime);

    void updateNameById(Long id, Long lastModifyTime, String name);

	List<Long> findIdByName(String name);

    Favorites save(Favorites favorites);

    void deleteById(long id);
}
