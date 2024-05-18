package com.sky.controller.user;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

//重命名bean对象名字，防止冲突
@RestController("userShopController")
@Slf4j
@Api(tags="店铺相关接口")
@RequestMapping("/user/shop")
public class ShopController {

    public static  final  String KEY="SHOP_STATUS";

    @Autowired
    private RedisTemplate redisTemplate;


    @GetMapping("/status")
    public Result<Integer> getStatus(){
        Integer shopStatus = (Integer) redisTemplate.opsForValue().get(KEY);
        log.info("获取店铺状态，status:{}",shopStatus);
        return Result.success(shopStatus);
    }

}
