package com.snake.market.service;

import com.snake.market.mapper.CommentsMapper;
import com.snake.market.model.Comments;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yangxs on 2018/6/12.
 */
@Service
@Transactional
public class CommentsService {

    @Autowired
    private CommentsMapper commentsMapper;

    public List<Comments> listCommentsByGoodsId(Long goodsId) {
        List<Comments> list = commentsMapper.listCommentsByGoodsId(goodsId);
        list.forEach(comments -> {
            String date = DateFormatUtils.format(comments.getCreateTime(), "yyyy-MM-dd HH:mm:ss");
            comments.setTime(date);
        });
        return commentsMapper.listCommentsByGoodsId(goodsId);
    }

    public int addComments(Long userId, Long goodsId, String content) {
        Long time = System.currentTimeMillis();
        return commentsMapper.addComments(userId, goodsId, content, time);
    }
}
