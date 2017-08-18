package com.weituitu.task.treasure.web;

import com.weibo.api.motan.config.springsupport.RegistryConfigBean;
import com.weibo.api.motan.config.springsupport.annotation.MotanReferer;
import com.weituitu.core.exception.ServiceException;
import com.weituitu.id.api.IdService;
import com.weituitu.task.treasure.conf.MotanConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    MotanConfig motanConfig;


    @MotanReferer(basicReferer = "motanClientBasicConfig")
    IdService idService;

    /**
     * 用户登录
     *
     * @return
     */
    @RequestMapping("/login")
    public ResponseEntity<String> login() throws ServiceException {
        LOG.info("test");
        LOG.info(motanConfig.getRegProtocol());
        LOG.info(motanConfig.getRegAddress());
        LOG.info("########" + String.valueOf(idService.nextId()));
        return ResponseEntity.ok("success");
    }
}
