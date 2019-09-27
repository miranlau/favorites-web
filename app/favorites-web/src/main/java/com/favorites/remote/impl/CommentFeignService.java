package com.favorites.remote.impl;

import com.favorites.domain.Comment;
import com.favorites.domain.view.CommentViewImpl;
import io.favorites.common.api.ListResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "${favorites.services.comment.address}", name = "comment-service")
public interface CommentFeignService{
    @RequestMapping(value="/comments/search/countByCollectId")
    Long countByCollectId(@RequestParam("collectId") Long collectId);

    @RequestMapping(value="/commentView/search/findByCollectIdOrderByIdDesc")
    ListResult<Comment> findByCollectIdOrderByIdDesc(@RequestParam("collectId")Long collectId);

    @RequestMapping(value="/commentView/search/findReplyUser")
    CommentViewImpl findReplyUser(@RequestParam("id") Long id);

    @RequestMapping(value="/comments", method = { RequestMethod.POST })
    Comment save(Comment comment);

    @RequestMapping(value="/comments/{id}", method = { RequestMethod.DELETE })
    void deleteById(@PathVariable("id")Long id);
}