package com.weituitu.ac.service;

import com.weituitu.ac.api.AcService;
import org.springframework.stereotype.Service;

/**
 * @描述:
 * @作者:liuguozhu
 * @创建:2017/8/21-下午1:46
 * @版本:v1.0
 */
@Service
public class AcServiceImpl implements AcService {
    @Override
    public String getName() {
        return "sundy";
    }
}
