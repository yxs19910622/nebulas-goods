package com.snake.market.utils;

import com.aliyun.oss.OSSClient;
import java.io.InputStream;

/**
 * Created by yangxs on 2018/6/14.
 */
public class UploadUtils {

    public static String upload(InputStream inputStream,String fileName){
        String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
        String accessKeyId = "LTAIuJWArMemtRbd";
        String accessKeySecret = "aZgvfQbdiF4N5rLA8IbIBUMr1T4ABF";
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        String bucketName = "snake-market-pic";
        String objectName = "nebulas-market/";
        ossClient.putObject(bucketName, objectName +fileName, inputStream);
        ossClient.shutdown();
        return "https://snake-market-pic.oss-cn-hangzhou.aliyuncs.com/"+ objectName +fileName;
    }
}
