package com.snake.market.service;

import com.snake.market.mapper.GoodsMapper;
import com.snake.market.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by yangxs on 2018/1/23.
 */
@Service
@Transactional
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    public List<Goods> listGoods(String search) {
        return goodsMapper.listGoods(search);
    }

    public Goods getGoodsById(Long id) {
        return goodsMapper.getGoodsById(id);
    }

    public int addGoods(Long userId, String title, String url, String label, String message) {
        Long time = System.currentTimeMillis();
        return goodsMapper.addGoods(userId, title, url, label, message, time);
    }


}
