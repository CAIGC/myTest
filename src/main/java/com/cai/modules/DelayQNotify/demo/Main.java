package com.cai.modules.DelayQNotify.demo;

import com.cai.modules.DelayQNotify.base.DelayQObserver;
import com.cai.modules.DelayQNotify.base.DelayQObserverable;
import com.cai.modules.DelayQNotify.base.IDelayQObserverable;
import com.cai.modules.DelayQNotify.base.topic;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by caigc on 19/5/12.
 */
public class Main {
    public static  void main(String args[]){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        IDelayQObserverable delayQObserverable = applicationContext.getBean(DelayQObserverable.class);
        DelayQObserver delayQObserver = applicationContext.getBean(CloseOrderTopic.class);
        delayQObserverable.removeObserver(delayQObserver.getClass().getAnnotation(topic.class).value(),delayQObserver);
    }
}
