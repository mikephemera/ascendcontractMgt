package com.ascendcargo.contractmgt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/system")
@RequiredArgsConstructor
public class SystemController {

    private final JdbcTemplate jdbcTemplate;

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> checkSystemHealth() {
        Map<String, String> healthStatus = new HashMap<>();

        // 检查应用状态
        healthStatus.put("status", "UP");

        // 检查数据库连接并获取数据库名称
        try {
            // 验证数据库连接
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);

            // 获取当前数据库名称
            String databaseName = jdbcTemplate.queryForObject("SELECT DATABASE()", String.class);
            healthStatus.put("database", "CONNECTED");
            healthStatus.put("databaseName", databaseName); // 添加数据库名称
        } catch (Exception e) {
            healthStatus.put("status", "DOWN");
            healthStatus.put("database", "DISCONNECTED");
            healthStatus.put("databaseName", "UNKNOWN"); // 数据库不可用时设置为UNKNOWN
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(healthStatus);
        }

        return ResponseEntity.ok(healthStatus);
    }
}

