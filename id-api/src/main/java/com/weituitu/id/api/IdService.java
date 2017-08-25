package com.weituitu.id.api;

import com.weituitu.core.exception.ServiceException;

/**
 * @描述:服务器唯一id服务接口
 * @作者:liuguozhu
 * @创建:2017/7/29-下午2:00
 * @版本:v1.0
 */
public interface IdService {


    /**
     * 生成唯一id
     *
     * @return
     */
    String nextId() throws ServiceException;
}
