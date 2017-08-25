package com.weituitu.task.treasure.web;

import com.weibo.api.motan.config.springsupport.annotation.MotanReferer;
import com.weituitu.ac.api.AcService;
import com.weituitu.core.exception.ServiceException;
import com.weituitu.id.api.IdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.instrument.web.TraceFilter;
import org.springframework.cloud.sleuth.trace.DefaultTracer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

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
        idService.nextId();
        return ResponseEntity.ok("success");
    }

    @RequestMapping("/acService")
    public ResponseEntity<String> acService() throws ServiceException {
        acService.getName();
        return ResponseEntity.ok("success");
    }

    @RequestMapping("/idService")
    public ResponseEntity<String> idService(HttpServletRequest request) throws ServiceException {
        Enumeration<String> headerEn = request.getHeaderNames();
        while (null != headerEn && headerEn.hasMoreElements()) {
            String headerName = headerEn.nextElement();
            String headerValue = request.getHeader(headerName);
            System.out.printf("headerName:%s,headerValue:%s\r\n", headerName, headerValue);
        }
        String TRACE_REQUEST_ATTR = TraceFilter.class.getName()
                + ".TRACE";

        Span span = (Span) request.getAttribute(TRACE_REQUEST_ATTR);
        System.out.println(span);


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
