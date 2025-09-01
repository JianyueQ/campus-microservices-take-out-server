package com.ccr.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author 31373
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MajorTreeVO implements Serializable {

    /**
     * 专业ID
     */
    private Long id;

    /**
     * 学院ID
     */
    private Long collegeId;

    /**
     * 专业名称
     */
    private String name;

}
