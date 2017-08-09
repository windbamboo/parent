package com.weituitu.id;


import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.util.MotanSwitcherUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @描述:
 * @作者:liuguozhu
 * @创建:2017/7/29-下午2:02
 * @版本:v1.0
 */
public class IdServer {

    private static Logger LOG = LoggerFactory.getLogger(IdServer.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:motan_server.xml");
        MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true);
        LOG.info("id server start success!");
    }
}
