package com.yasso.dfbb.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author guochuang
 * @version 1.0
 * @date 2021/3/9 13:16
 */
@Component
public class ApplicationContextBean implements ApplicationContextAware, InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationContextBean.class);
    public static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextBean.applicationContext = applicationContext;
        LOGGER.info("ApplicationContextAware");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        int beanDefinitionCount = applicationContext.getBeanDefinitionCount();
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            if (beanDefinitionName.contains(".")) {
                LOGGER.info(beanDefinitionName.substring(beanDefinitionName.lastIndexOf(".") + 1));

            } else {
                LOGGER.info(beanDefinitionName);
            }
        }
        LOGGER.info("count -> {}", beanDefinitionCount);
//        LOGGER.info("InitializingBean");
    }

}
