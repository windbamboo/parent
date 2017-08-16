package com.weituitu.task.treasure.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @描述:
 * @作者:liuguozhu
 * @创建:2017/8/16-下午10:52
 * @版本:v1.0
 */
@RestController
public class LoginController {


    /**
     * 用户登录
     *
     * @return
     */
    @RequestMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("success");
    }
}
