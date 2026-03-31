package com.sky.service;

import com.sky.vo.UserReportVO;

import java.time.LocalDate;

public interface ReportService {
    /**
     * 统计指定时间区间内的用户数据
     */
    UserReportVO getUserStatistics(LocalDate begin, LocalDate end);
}
