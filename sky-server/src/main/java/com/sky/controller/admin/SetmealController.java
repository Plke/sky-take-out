package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.DishVO;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("adminSetmealController")
@Slf4j
@Api("套餐相关接口")
@RequestMapping("/admin/setmeal")
public class SetmealController {
    @Autowired
    private SetmealService setmealService;

    @PostMapping
    @ApiOperation("新增套餐")
    public Result addSetmeal(@RequestBody SetmealDTO setmealDto){
        log.info("新增套餐：{}",setmealDto);
        setmealService.addSetmeal(setmealDto);
        return Result.success();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询套餐")
    public Result<SetmealVO> getSetmealById(@PathVariable Long id){
        log.info("根据id查询菜品：{}",id);
        SetmealVO setmealVO = setmealService.getBYId(id);
        return Result.success(setmealVO);
    }

    @GetMapping("/page")
    @ApiOperation("分页查询套餐")
    public Result<PageResult> pageSetmeal(SetmealPageQueryDTO setmealPageQueryDTO){
        log.info("分页查询套餐：{}",setmealPageQueryDTO);
        PageResult pageResult = setmealService.pageSetmeal(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    @PostMapping("/status/{status}")
    @ApiOperation("修改套餐状态")
    public Result<String> updateStatus(@PathVariable Integer status, Long id) {
        log.info("修改套餐状态：{}，{}", status, id);
        setmealService.updateStatus(status, id);
        return Result.success();
    }
    @PutMapping
    @ApiOperation("修改菜品")
    public Result<String> updateSetmeal(@RequestBody SetmealDTO setmealDto){
        log.info("修改菜品：{}",setmealDto);
        setmealService.updateSetmeal(setmealDto);
        return Result.success();
    }


    @DeleteMapping
    @ApiOperation("根据id批量删除菜品")
    public Result deleteByIds(@RequestParam List<Long> ids){
        setmealService.deleteByIds(ids);
        return Result.success();
    }

}
