package com.sky.controller.admin;

import com.sky.constant.FileConstant;
import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RestController
@Slf4j
@Api("通用接口")
@RequestMapping("/admin/common")
public class CommonController {

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public Result upload( MultipartFile file) throws Exception {

        log.info("文件上传：{}", file);

        try {
            String originalFileName = file.getOriginalFilename();

            int  index=originalFileName.lastIndexOf(".");
            String extName=originalFileName.substring(index);
            String fileName=UUID.randomUUID().toString()+extName;
            file.transferTo(new File(FileConstant.LOCAL_FILE_PATH +fileName));
            return Result.success(FileConstant.REQUEST_FILE_PATH+fileName);
        } catch (Exception e) {
            log.error("文件上传失败：{}",e);
        }

        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
