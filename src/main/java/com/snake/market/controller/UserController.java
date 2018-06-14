package com.snake.market.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yangxs on 2018/6/11.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "test")
    public void test(){

    }
}
