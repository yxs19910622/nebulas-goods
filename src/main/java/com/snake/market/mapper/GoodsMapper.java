package com.snake.market.mapper;


import com.snake.market.model.Goods;
import com.snake.market.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yangxs on 2018/1/23.
 */
public interface GoodsMapper {

    List<Goods> listGoods ();

    Goods getGoodsById(@Param("id") Long id);
}
