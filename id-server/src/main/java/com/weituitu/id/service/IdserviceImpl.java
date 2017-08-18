package com.weituitu.id.service;

import com.weituitu.core.exception.ServiceException;
import com.weituitu.id.api.IdService;

import java.util.Random;

/**
 * @描述:
 * @作者:liuguozhu
 * @创建:2017/7/29-下午2:06
 * @版本:v1.0
 */
public class IdserviceImpl implements IdService {

    public long nextId() throws ServiceException {
        int number = new Random(10).nextInt();
        System.out.println("服务器数据" + number);
        return number;
    }
}
