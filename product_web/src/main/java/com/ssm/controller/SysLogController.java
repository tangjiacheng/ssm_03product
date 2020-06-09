package com.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.domain.SysLog;
import com.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/9 14:15
 * @description: TODO
 */
@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    ISysLogService sysLogService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        ModelAndView mv = new ModelAndView();
        List<SysLog> sysLogs = sysLogService.findAll(page, size);
        PageInfo<SysLog> pageInfo = new PageInfo<>(sysLogs);
        mv.addObject("pageInfo", pageInfo);
        mv.addObject("sysLogs", sysLogs);
        mv.setViewName("syslog/syslog-list");
        return mv;
    }
}
