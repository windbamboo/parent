package com.weituitu.id.service;

import com.weituitu.ac.api.AcService;
import com.weituitu.core.exception.ServiceException;
import com.weituitu.id.api.IdService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @描述:
 * @作者:liuguozhu
 * @创建:2017/7/29-下午2:06
 * @版本:v1.0
 */
public class IdserviceImpl implements IdService {

    @Autowired
    AcService acService;

    public String nextId() throws ServiceException {

        return acService.getName();

    }
}
