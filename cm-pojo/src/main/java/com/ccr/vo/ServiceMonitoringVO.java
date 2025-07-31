package com.ccr.vo;

import com.ccr.entity.Cpu;
import com.ccr.entity.Jvm;
import com.ccr.entity.Mem;
import com.ccr.entity.Sys;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 31373
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceMonitoringVO implements Serializable {

    private Cpu cpu;

    private Mem mem;

    private Jvm jvm;

    private Sys sys;

}
