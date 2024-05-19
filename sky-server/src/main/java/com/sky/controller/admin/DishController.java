package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController("adminDishController")
@Slf4j
@Api("菜品相关接口")
@RequestMapping("/admin/dish")
public class DishController {

    @Autowired
    private DishService dishService;
    @Autowired
    private RedisTemplate redisTemplate;


    @PostMapping
    @ApiOperation("新增菜品")
    public Result addDish(@RequestBody DishDTO dishDto) {
        log.info("新增菜品：{}", dishDto);
        dishService.addDish(dishDto);

        //清理缓存数据
        String key = "dish_" + dishDto.getCategoryId();
        cleanCache(key);

        return Result.success();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询菜品")
    public Result<DishVO> getDishById(@PathVariable Long id) {
        log.info("根据id查询菜品：{}", id);
        DishVO dishVO = dishService.getBYId(id);
        return Result.success(dishVO);
    }

    @GetMapping("/page")
    @ApiOperation("分页查询菜品")
    public Result<PageResult> pageDish(DishPageQueryDTO dishPageQueryDTO) {
        log.info("分页查询菜品：{}", dishPageQueryDTO);
        PageResult pageResult = dishService.pageDish(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    @PostMapping("/status/{status}")
    @ApiOperation("修改菜品状态")
    public Result<String> updateStatus(@PathVariable Integer status, Long id) {
        log.info("修改菜品状态：{}，{}", status, id);
        dishService.updateStatus(status, id);
        //清理所有菜品缓存 先获取（支持*），在删除
        cleanCache("dish*");

        return Result.success();
    }

    @PutMapping
    @ApiOperation("修改菜品")
    public Result<String> updateDish(@RequestBody DishDTO dishDto) {
        log.info("修改菜品：{}", dishDto);
        dishService.updateDish(dishDto);

        //清理所有菜品缓存 先获取（支持*），在删除
        cleanCache("dish*");

        return Result.success();
    }


    @GetMapping("/list")
    @ApiOperation("根据分类id查询菜品列表")
    public Result<List<Dish>> list(Long categoryId) {
        log.info("categoryId:{}", categoryId);

        List<Dish> dishes = dishService.list(categoryId);
        return Result.success(dishes);
    }

    @DeleteMapping
    @ApiOperation("根据id批量删除菜品")
    public Result deleteByIds(@RequestParam List<Long> ids) {

        dishService.deleteByIds(ids);

        //清理所有菜品缓存 先获取（支持*），在删除
        cleanCache("dish*");
        return Result.success();
    }


    private void cleanCache(String pattern){
        log.info("清理缓存：{}",pattern);
        //清理所有菜品缓存 先获取（支持*），在删除
        Set keys = redisTemplate.keys(pattern);
//        System.out.println(keys);
        redisTemplate.delete(keys);
    }
}
