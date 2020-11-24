package com.ljq.demo.springboot.schedule.cronjob;

import com.ljq.demo.springboot.dao.receiveaddress.ReceiveAddressDao;
import com.ljq.demo.springboot.dao.user.UserDao;
import com.ljq.demo.springboot.entity.ReceiveAddressEntity;
import com.ljq.demo.springboot.entity.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * @Description: 多数据源示例定时任务
 * @Author: junqiang.lu
 * @Date: 2019/6/16
 */
@Slf4j
@Component
public class MultiDataSourceDemoSchedule {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ReceiveAddressDao receiveAddressDao;

    /**
     * 用户列表定时任务
     */
    @Scheduled(fixedDelay = 10 * 1000, initialDelay = 10 * 1000)
    public void userListSchedule() {
        int userCount = userDao.countComplex(new HashMap<>(16));
        log.debug("userCount: {}", userCount);
        if (userCount > 0) {
            List<UserDO> userDBList = userDao.queryListComplex(null);
            userDBList.stream().forEach(userDO -> {
                log.debug("{}", userDO);
            });
        }
    }

    /**
     * 收货地址列表定时任务
     */
    @Scheduled(fixedDelay = 10 * 1000, initialDelay = 10 * 1000)
    public void receiveAddressListSchedule() {
        int receiveAddressCount = receiveAddressDao.countComplex(new HashMap<>(16));
        log.debug("receiveAddressCount: {}", receiveAddressCount);
        if (receiveAddressCount > 0) {
            List<ReceiveAddressEntity> receiveAddressDBList = receiveAddressDao.queryListComplex(null);
            receiveAddressDBList.stream().forEach(receiveAddressEntity -> {
                log.debug("{}", receiveAddressEntity);
            });
        }
    }


}
