package com.weituitu.ac.service;

import com.weituitu.ac.api.AcService;
import com.weituitu.message.api.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @描述:
 * @作者:liuguozhu
 * @创建:2017/8/21-下午1:46
 * @版本:v1.0
 */
@Service
public class AcServiceImpl implements AcService {
    @Autowired
    MessageService messageService;

    @Override
    public String getName() {
        messageService.sendMessage();
        return new Date().toString();
    }
}
