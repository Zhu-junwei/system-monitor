package com.zjw.controller;

import com.zjw.result.R;
import com.zjw.service.IMemoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 内存controller
 * @author 朱俊伟
 * @since 2024/12/04 13:17
 */
@RestController
@RequestMapping("/memory")
@AllArgsConstructor
@CrossOrigin
public class MemoryController {

    private IMemoryService memoryService;

    @GetMapping("info")
    public R getMemoryInfo(){
        return R.success(memoryService.memoryInfo());
    }
}
