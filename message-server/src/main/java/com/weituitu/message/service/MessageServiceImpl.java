package com.weituitu.message.service;

import com.weituitu.message.api.MessageService;
import org.springframework.stereotype.Service;

/**
 * @描述:
 * @作者:liuguozhu
 * @创建:2017/8/21-下午1:46
 * @版本:v1.0
 */
@Service
public class MessageServiceImpl implements MessageService {


    @Override
    public String sendMessage() {
        return "send message";
    }
}
