package com.example.library_api.controller;

import com.example.library_api.dto.StatisticsResponse;
import com.example.library_api.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping("/statistics")
    public StatisticsResponse getStatistics() {
        return statisticsService.getStatistics();
    }
}
