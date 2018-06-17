package com.snake.market.controller;

import com.snake.market.service.CommentsService;
import com.snake.market.service.GoodsService;
import com.snake.market.service.NebulasService;
import com.snake.market.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by yangxs on 2018/6/14.
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private NebulasService nebulasService;

    /**
     * 图片上传
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, String> upload(@RequestParam("files[]") MultipartFile[] files) throws IOException {
        Map<String, String> result = new HashMap<>();
        if (files != null && files.length > 0) {
            MultipartFile file = files[0];
            String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            InputStream inputStream = file.getInputStream();
            Long timestamp = System.currentTimeMillis();
            String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
            String fileMark = uuid + timestamp + fileType;
            String url = UploadUtils.upload(inputStream, fileMark);
            result.put("url", url);
            result.put("fileName", file.getOriginalFilename());
        }
        return result;
    }

    /**
     * 新增东西
     */
    @RequestMapping(value = "add")
    public void addGoods(String title, String url, String label, String message){
        nebulasService.addGoods(title, url, label, message);
    }
}
