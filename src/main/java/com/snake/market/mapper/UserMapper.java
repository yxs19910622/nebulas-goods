package com.snake.market.mapper;


import com.snake.market.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by yangxs on 2018/1/23.
 */
public interface UserMapper {

    User getUserByAccountAndPassword(@Param("account") String account, @Param("password") String password);
}
