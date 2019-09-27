package com.favorites.remote;

import com.favorites.domain.Comment;
import com.favorites.domain.view.CommentViewImpl;

import java.util.List;

public interface CommentService {
    Long countByCollectId(Long collectId);

    List<Comment> findByCollectIdOrderByIdDesc(Long collectId);

    void deleteById(Long id);

    CommentViewImpl findReplyUser(Long id);

    Comment save(Comment comment);
}
