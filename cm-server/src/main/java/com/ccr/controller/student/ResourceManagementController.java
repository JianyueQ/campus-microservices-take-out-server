package com.ccr.controller.student;

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
@RestController("studentResourceManagementController")
@RequestMapping("/student/resource")
public class ResourceManagementController {

    @Autowired
    private ResourceManagementService resourceManagementService;

    /**
     * 获取文件夹列表/获取文件列表
     */
    @GetMapping("/list")
    public Result<List<ResourceVO>> getResourceList(ResourceListDTO resourceListDTO) {
        log.info("获取文件夹列表/获取文件列表: {}", resourceListDTO);
        List<ResourceVO> resourceVOList = resourceManagementService.getResourceList(resourceListDTO);
        return Result.success(resourceVOList);
    }

}
