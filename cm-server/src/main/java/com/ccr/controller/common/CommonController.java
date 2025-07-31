package com.ccr.controller.common;

import cn.hutool.core.lang.UUID;
import com.ccr.constant.MessageConstant;
import com.ccr.exception.CommonQuestionException;
import com.ccr.result.Result;
import com.ccr.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author 31373
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    @Autowired
    private AliOssUtil aliOssUtil;

    /**
     * 实现文件上传
     */
    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("file") MultipartFile file) {

        //获取文件名称
        String originalFilename = file.getOriginalFilename();
        log.info("图片上传开始,上传的文件名为:{}", originalFilename);
        //判断文件名的后缀
        String[] split = originalFilename.split("\\.");
        String suffix = split[split.length - 1];
        if (!"png".equals(suffix) && !"jpg".equals(suffix) && !"jpeg".equals(suffix)) {
            //文件格式错误
            throw new CommonQuestionException(MessageConstant.FILE_FORMAT_ERROR);
        }
        //生成新的文件名称
        String newFileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        //调用阿里云oss工具类
        try {
            String upload = aliOssUtil.upload(file.getBytes(), newFileName);
            log.info("图片上传成功,上传的文件地址为:{}", upload);
            return Result.success(upload);
        } catch (IOException e) {
            //文件上传失败
            log.error(e.getMessage());
            return Result.error(MessageConstant.UPLOAD_FAILED);
        }
    }

    /**
     * 实现视频上传
     */
    @PostMapping("/uploadVideo")
    public Result<String> uploadVideo(@RequestParam("file") MultipartFile file) {
        //获取文件名称
        String originalFilename = file.getOriginalFilename();
        log.info("视频上传开始,上传的文件名为:{}", originalFilename);
        //判断文件名的后缀
        String[] split = originalFilename.split("\\.");
        String suffix = split[split.length - 1];
        if (!"mp4".equals(suffix) && !"avi".equals(suffix) && !"mkv".equals(suffix)) {
            //文件格式错误
            throw new CommonQuestionException(MessageConstant.FILE_FORMAT_ERROR);
        }
        //生成新的文件名称
        String newFileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        //调用阿里云oss工具类
        try {
            String upload = aliOssUtil.upload(file.getBytes(), newFileName);
            log.info("视频上传成功,上传的文件地址为:{}", upload);
            return Result.success(upload);
        } catch (IOException e) {
            //文件上传失败
            log.error(e.getMessage());
            return Result.error(MessageConstant.UPLOAD_FAILED);
        }
    }
}
