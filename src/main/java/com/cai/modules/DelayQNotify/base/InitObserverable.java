package com.cai.modules.DelayQNotify.base;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by caigc on 19/5/12.
 */
@Component
public class InitObserverable implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        DelayQObserverable delayQObserverable = applicationContext.getBean(DelayQObserverable.class);
        String[] observerBeanNames = applicationContext.getBeanNamesForType(IDelayQObserver.class);
        List<String> topicList = new ArrayList<>();
        for (String s : observerBeanNames){
            IDelayQObserver observer = applicationContext.getBean(s,IDelayQObserver.class);
            topic t = observer.getClass().getAnnotation(topic.class);
            if(t != null && !t.value().isEmpty()){
                delayQObserverable.registerObserver(t.value(),observer);
                topicList.add(t.value());
            }
        }
        initTimer(topicList,delayQObserverable);
    }

    private void initTimer(List<String> topicList, final DelayQObserverable delayQObserverable) {
            Timer timer = new Timer();
        for (final String t : topicList){
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    delayQObserverable.setInformation(t, t);
                }
            },0,1000);
        }
    }


}
