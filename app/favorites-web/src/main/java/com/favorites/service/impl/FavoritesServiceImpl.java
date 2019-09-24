package com.favorites.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.favorites.domain.Collect;
import com.favorites.domain.Favorites;
import com.favorites.domain.enums.CollectType;
import com.favorites.domain.enums.IsDelete;
import com.favorites.remote.BookmarkService;
import com.favorites.remote.FolderService;
import com.favorites.service.FavoritesService;
import com.favorites.utils.DateUtils;

@Service("favoritesService")
public class FavoritesServiceImpl implements FavoritesService{
	protected Logger logger =  LoggerFactory.getLogger(this.getClass());

	@Autowired
    private FolderService folderService;
	@Autowired
    private BookmarkService collectRepository;

	@Override
	public Favorites saveFavorites(Long userId, String name) {
		Favorites favorites = new Favorites();
		favorites.setName(name);
		favorites.setUserId(userId);
		favorites.setCount(0l);
		favorites.setPublicCount(10l);
		favorites.setCreateTime(DateUtils.getCurrentTime());
		favorites.setLastModifyTime(DateUtils.getCurrentTime());
        folderService.save(favorites);
		return  favorites;
	}

	/**
	 * 保存
	 * @return
	 */
	public Favorites saveFavorites(Collect collect){
		Favorites favorites = new Favorites();
		favorites.setName(collect.getNewFavorites());
		favorites.setUserId(collect.getUserId());
		favorites.setCount(1l);
		if(CollectType.PUBLIC.name().equals(collect.getType())){
			favorites.setPublicCount(1l);
		}else {
			favorites.setPublicCount(10l);
		}
		favorites.setCreateTime(DateUtils.getCurrentTime());
		favorites.setLastModifyTime(DateUtils.getCurrentTime());
        folderService.save(favorites);
		return favorites;
	}


	public void countFavorites(long id){
        Favorites favorite = folderService.findById(id);
		Long count=collectRepository.countByFavoritesIdAndIsDelete(id, IsDelete.NO);
		favorite.setCount(count);
		Long pubCount=collectRepository.countByFavoritesIdAndTypeAndIsDelete(id, CollectType.PUBLIC, IsDelete.NO);
		favorite.setPublicCount(pubCount);
		favorite.setLastModifyTime(DateUtils.getCurrentTime());
        folderService.save(favorite);

	}
}
