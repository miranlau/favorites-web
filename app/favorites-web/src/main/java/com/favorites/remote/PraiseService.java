package com.favorites.remote;

import com.favorites.domain.Praise;
import com.favorites.domain.view.CommentView;

public interface PraiseService {
	
	Praise save(Praise praise);
	
	Long countByCollectId(Long collectId);

	Praise findByUserIdAndCollectId(Long userId, Long collectId);

	CommentView findPraiseUser(Long id);

	void deleteById(Long id);
}
