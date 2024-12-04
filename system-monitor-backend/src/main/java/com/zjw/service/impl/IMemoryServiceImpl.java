package com.zjw.service.impl;

import com.zjw.service.IMemoryService;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.PhysicalMemory;
import oshi.util.FormatUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 朱俊伟
 * @since 2024/12/04 13:31
 */
@Service
public class IMemoryServiceImpl implements IMemoryService {

    @Override
    public Map<String, Object> memoryInfo() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hardware = si.getHardware();
        GlobalMemory memory = hardware.getMemory();

        // 可用/总量
        long available = memory.getAvailable();
        long total = memory.getTotal();
        String formatAvailable = FormatUtil.formatBytes(available);
        String formatTotal = FormatUtil.formatBytes(total);
        double usageRatio =  ((double)(total - available) / total);

        // 页大小
        long pageSize = memory.getPageSize();

        // 物理内存
        List<PhysicalMemory> pmList = memory.getPhysicalMemory();
        List<Map<String, String>> physicalMemoryInfo = new ArrayList<>();
        if (!pmList.isEmpty()) {
            for (PhysicalMemory pm : pmList) {
                Map<String, String> pmDetails = new HashMap<>();
                pmDetails.put("capacity", FormatUtil.formatBytes(pm.getCapacity()));
                pmDetails.put("memoryType", pm.getMemoryType());
                pmDetails.put("bankLabel", pm.getBankLabel());
                pmDetails.put("manufacturer", pm.getManufacturer());
                pmDetails.put("clockSpeed", FormatUtil.formatHertz(pm.getClockSpeed()));
                pmDetails.put("partNumber", pm.getPartNumber().trim());
                pmDetails.put("serialNumber", pm.getSerialNumber());
                physicalMemoryInfo.add(pmDetails);
            }
        }

        // 构建返回的Map
        Map<String, Object> memoryInfo = new HashMap<>();
        memoryInfo.put("available", formatAvailable);
        memoryInfo.put("total", formatTotal);
        memoryInfo.put("usageRatio", String.format("%.2f", usageRatio * 100));
        memoryInfo.put("pageSize", FormatUtil.formatBytes(pageSize));
        memoryInfo.put("physicalMemory", physicalMemoryInfo);

        return memoryInfo;
    }
}
