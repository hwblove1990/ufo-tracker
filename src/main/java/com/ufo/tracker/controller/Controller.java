package com.ufo.tracker.controller;

import cn.hutool.json.JSONUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/tracker")
public class Controller {

    @GetMapping("/announce")
    public String announce(HttpServletRequest httpServletRequest){
        log.error("{}", httpServletRequest);
        System.err.println(JSONUtil.toJsonStr(httpServletRequest) );
        return  JSONUtil.toJsonPrettyStr(httpServletRequest);
    }
}
