package com.snake.market.mapper;


import com.snake.market.model.Comments;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yangxs on 2018/1/23.
 */
public interface CommentsMapper {

    List<Comments> listCommentsByGoodsId(@Param("goodsId") Long goodsId);

    int addComments(@Param("userId") Long userId, @Param("goodsId") Long goodsId, @Param("content") String content, @Param("createTime") Long time);
}
