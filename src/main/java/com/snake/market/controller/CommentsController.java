package com.snake.market.controller;

import com.snake.market.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yangxs on 2018/6/11.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @RequestMapping(value = "add")
    public void addComments(Long userId, Long goodsId, String content){
        commentsService.addComments(userId, goodsId, content);
    }
}
