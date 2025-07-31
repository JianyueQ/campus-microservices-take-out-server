package com.ccr.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统配置实体类
 * 对应数据库表: system_config
 * @author 31373
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemConfig implements Serializable {
    
    /**
     * 配置ID
     */
    private Long id;
    
    /**
     * 配置键
     */
    private String configKey;
    
    /**
     * 配置值
     */
    private String configValue;
    
    /**
     * 配置名称
     */
    private String configName;
    
    /**
     * 配置说明
     */
    private String configDesc;
    
    /**
     * 配置类型（system:系统配置, business:业务配置）
     */
    private String configType;
    
    /**
     * 状态（0:禁用, 1:启用）
     */
    private Integer status;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    
    /**
     * 创建人ID
     */
    private Long createUser;
    
    /**
     * 更新人ID
     */
    private Long updateUser;
    
    /**
     * 逻辑删除标识（0：未删除，1：已删除）
     */
    private Integer isDeleted;
}