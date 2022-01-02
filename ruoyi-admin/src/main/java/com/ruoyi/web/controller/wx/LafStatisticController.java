package com.ruoyi.web.controller.wx;

import com.ruoyi.wx.domain.LafRelease;
import com.ruoyi.wx.service.ILafReleaseService;
import com.ruoyi.wx.service.ILafStudentService;
import com.ruoyi.wx.util.echart.Graph;
import com.ruoyi.wx.util.echart.Statistic;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/wx")
public class LafStatisticController {

    @Autowired
    private ILafStudentService lafStudentService;
    @Autowired
    private ILafReleaseService lafReleaseService;

    @GetMapping("/statistic")
    @ResponseBody
    public Statistic showStatistic() throws ParseException {
        Statistic statistic = new Statistic();
        statistic.setInformation(lafReleaseService.informationCount());
        statistic.setSuccess(lafReleaseService.successCount());
        statistic.setNumber(lafStudentService.countNumbers());
        statistic.setFan_chart(lafReleaseService.fanChart());
        LafRelease lafRelease = new LafRelease();
        List<Graph> graphList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 7; i++) {
            Graph graph = new Graph();
            Date date = DateUtils.addDays(new Date(), -i);
            String formatDate = sdf.format(date);
            System.out.println(formatDate);
            graph.setName(formatDate);
            lafRelease.setCreateTime(sdf.parse(formatDate));
            int nums = lafReleaseService.selectLafReleaseList(lafRelease).size();
            graph.setValue(nums);
            graphList.add(graph);
        }
        statistic.setColumn_chart(graphList);
        return statistic;
    }

}
