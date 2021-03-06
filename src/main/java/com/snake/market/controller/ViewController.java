package com.snake.market.controller;

import com.snake.market.model.Comments;
import com.snake.market.model.Goods;
import com.snake.market.service.CommentsService;
import com.snake.market.service.GoodsService;
import com.snake.market.service.NebulasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

/**
 * Created by yangxs on 2018/6/11.
 */
@Controller
public class ViewController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private NebulasService nebulasService;

    /**
     * 首页
     * @return
     */
    @RequestMapping("/")
    public String home(ModelMap model) {
        List<Goods> goodsList = nebulasService.getGoodsList();
        Collections.reverse(goodsList);
        model.addAttribute("goodsList",goodsList);
        return "/index";
    }

    /**
     * 单页面
     * @return
     */
    @RequestMapping("/item")
    public String signle(Long id, ModelMap model) {
        Goods goods = nebulasService.getGoods(id.toString());
        List<Comments> comments = commentsService.listCommentsByGoodsId(id);
        model.addAttribute("goods",goods);
        model.addAttribute("comments",comments);
        return "/single-page";
    }

    /**
     * 新增好物
     * @return
     */
    @RequestMapping("/newGoods")
    public String newGoods(Long id, ModelMap model) {
        Goods goods = goodsService.getGoodsById(id);
        List<Comments> comments = commentsService.listCommentsByGoodsId(id);
        model.addAttribute("goods",goods);
        model.addAttribute("comments",comments);
        return "/new-goods";
    }
}
