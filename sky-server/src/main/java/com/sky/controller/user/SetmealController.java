package com.sky.controller.user;

import com.sky.entity.Setmeal;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.service.SetmealService;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("userSetmealController")
@RequestMapping("/user/setmeal")
@Slf4j
@Api(tags = "C端-套餐浏览接口")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;



    @GetMapping("/list")
    @Cacheable(cacheNames="setmealCache" ,key="#categoryId")
    public Result<List<Setmeal>> getByCategoryId(Long categoryId){
        log.info("categoryId:{}",categoryId);

        List<Setmeal> setmeals  = setmealService.getByCategoryId(categoryId);

        return Result.success(setmeals);
    }

    @GetMapping("/dish/{id}")
    public Result<List<DishItemVO>> getById(@PathVariable Long id){
        log.info("根据套餐id查询包含的菜品:{}",id);

        List<DishItemVO> setmealVOS = setmealService.getDishItemById(id);

        return Result.success(setmealVOS);
    }
}
