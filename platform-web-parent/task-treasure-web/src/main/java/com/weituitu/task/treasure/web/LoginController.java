package com.weituitu.task.treasure.web;

import com.weibo.api.motan.config.springsupport.annotation.MotanReferer;
import com.weituitu.ac.api.AcService;
import com.weituitu.core.exception.ServiceException;
import com.weituitu.id.api.IdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);


    @MotanReferer(basicReferer = "motanClientBasicConfig")
    IdService idService;

    @MotanReferer(basicReferer = "motanClientBasicConfig")
    AcService acService;

    /**
     * 用户登录
     *
     * @return
     */
    @RequestMapping("/oneWay")
    public ResponseEntity<String> login() throws ServiceException {
        long id = idService.nextId();
        return ResponseEntity.ok("success");
    }

    @RequestMapping("/acService")
    public ResponseEntity<String> acService() throws ServiceException {
        acService.getName();
        return ResponseEntity.ok("success");
    }

    @RequestMapping("/idService")
    public ResponseEntity<String> idService() throws ServiceException {
        idService.nextId();
        return ResponseEntity.ok("success");
    }

    @RequestMapping("/ai")
    public ResponseEntity<String> ai() throws ServiceException {
        acService.getName();
        idService.nextId();
        return ResponseEntity.ok("success");
    }
}
