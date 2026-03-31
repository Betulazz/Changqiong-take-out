package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.service.ReportService;
import com.sky.vo.OrderReportVO;
import com.sky.vo.SalesTop10ReportVO;
import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/admin/report/")
@Api(tags = "数据统计相关接口")
@Slf4j
public class ReportController {

    @Autowired
    ReportService reportService;

    /**
     * 营业额统计
     */
    @GetMapping("/turnoverStatistics")
    @ApiOperation("营业额统计")
    public Result<TurnoverReportVO> turnoverStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate end) {
        log.info("营业额统计：{}到{}", begin, end);
        TurnoverReportVO data = reportService.getTurnoverStatistics(begin, end);
        return Result.success(data);
    }

    /**
     * 用户数量统计
     */
    @GetMapping("/userStatistics")
    @ApiOperation("用户统计")
    public Result<UserReportVO> userStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate end) {
        log.info("用户数量统计：{}到{}", begin, end);
        UserReportVO data = reportService.getUserStatistics(begin, end);
        return Result.success(data);
    }

    /**
     * 订单数量统计
     */
    @GetMapping("/ordersStatistics")
    @ApiOperation("订单统计")
    public Result<OrderReportVO> ordersStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate end) {
        log.info("订单数量统计：{}到{}", begin, end);
        OrderReportVO data = reportService.getOrdersStatistics(begin, end);
        return Result.success(data);
    }

    /**
     * 销量排名
     */
    @GetMapping("/top10")
    @ApiOperation("销量排名Top10")
    public Result<SalesTop10ReportVO> top10(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate end) {
        log.info("销量排名Top10：{}到{}", begin, end);
        SalesTop10ReportVO data = reportService.getSalesTop10(begin, end);
        return Result.success(data);
    }
}
