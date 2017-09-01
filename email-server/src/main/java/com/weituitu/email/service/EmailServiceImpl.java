package com.weituitu.email.service;

import com.weituitu.email.api.EmailService;
import org.springframework.stereotype.Service;

/**
 * @描述:
 * @作者:liuguozhu
 * @创建:2017/8/21-下午1:46
 * @版本:v1.0
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Override
    public String sendEmail() {
        return "send mail";
    }
}
