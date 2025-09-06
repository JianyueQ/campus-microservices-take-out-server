package com.ccr.service;

import com.ccr.dto.ResourceDTO;
import com.ccr.dto.ResourceListDTO;
import com.ccr.vo.ResourceVO;

import java.util.List;

/**
 * @author 31373
 */
public interface ResourceManagementService {
    /**
     * 添加文件夹/上传文件
     * @param resourceDTO 文件夹/文件信息
     */
    void addResource(ResourceDTO resourceDTO);

    /**
     * 获取文件夹/文件列表
     * @param resourceListDTO 文件夹/文件列表参数
     * @return 文件夹/文件列表
     */
    List<ResourceVO> getResourceList(ResourceListDTO resourceListDTO);

    /**
     * 修改文件夹名称
     * @param resourceDTO 文件夹信息
     */
    void updateResource(ResourceDTO resourceDTO);

    /**
     * 删除文件夹/文件
     * @param id 文件夹/文件id
     * @param fileUrls 文件路径
     */
    void deleteResource(Long id, String fileUrls);
}
