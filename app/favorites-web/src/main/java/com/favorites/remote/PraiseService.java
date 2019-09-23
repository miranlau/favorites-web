package com.favorites.remote;

import com.favorites.domain.Praise;
import com.favorites.domain.view.CommonUserView;

public interface PraiseService {
	
	void save(Praise praise);
	
	Long countByCollectId(Long collectId);

	Praise findByUserIdAndCollectId(Long userId, Long collectId);

	CommonUserView findPraiseUser(Long id);

	void deleteById(Long id);
}
