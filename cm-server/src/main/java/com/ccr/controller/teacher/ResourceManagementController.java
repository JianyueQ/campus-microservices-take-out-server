package com.ccr.controller.teacher;

import com.ccr.annotations.Log;
import com.ccr.dto.ResourceDTO;
import com.ccr.dto.ResourceListDTO;
import com.ccr.enumeration.BusinessType;
import com.ccr.result.Result;
import com.ccr.service.ResourceManagementService;
import com.ccr.vo.ResourceVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 31373
 */
@Slf4j
@RestController("teacherResourceManagementController")
@RequestMapping("/teacher/resource")
public class ResourceManagementController {

    @Autowired
    private ResourceManagementService resourceManagementService;

    /**
     * 添加文件夹/上传文件
     */
    @PostMapping("/add")
    @Log(title = "教师端-添加文件夹/上传文件", businessType = BusinessType.INSERT)
    public Result<String> addResource(@RequestBody ResourceDTO resourceDTO) {
        log.info("添加文件夹/上传文件: {}", resourceDTO);
        resourceManagementService.addResource(resourceDTO);
        return Result.success();
    }

    /**
     * 获取文件夹列表/获取文件列表
     */
    @GetMapping("/list")
    public Result<List<ResourceVO>> getResourceList(ResourceListDTO resourceListDTO) {
        log.info("获取文件夹列表/获取文件列表: {}", resourceListDTO);
        List<ResourceVO> resourceVOList = resourceManagementService.getResourceList(resourceListDTO);
        return Result.success(resourceVOList);
    }

    /**
     * 修改文件夹名称
     */
    @PutMapping("/update")
    @Log(title = "教师端-修改文件夹名称", businessType = BusinessType.UPDATE)
    public Result<String> updateResource(@RequestBody ResourceDTO resourceDTO) {
        log.info("修改文件夹名称: {}", resourceDTO);
        resourceManagementService.updateResource(resourceDTO);
        return Result.success();
    }

    /**
     * 删除文件夹/删除文件
     */
    @DeleteMapping("/delete")
    @Log(title = "教师端-删除文件夹/删除文件", businessType = BusinessType.DELETE)
    public Result<String> deleteResource(@RequestParam("id") Long id, @RequestParam("fileUrls") String fileUrls) {
        log.info("删除文件夹/删除文件: {}", id);
        resourceManagementService.deleteResource(id, fileUrls);
        return Result.success();
    }
}
