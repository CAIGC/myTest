package com.cai.modules.DelayQNotify.demo;

import com.cai.modules.DelayQNotify.base.DelayQObserver;
import com.cai.modules.DelayQNotify.base.topic;
import org.springframework.stereotype.Component;

/**
 * Created by caigc on 19/5/12.
 */
@Component
@topic(value="testTopic")
public class TestTopic extends DelayQObserver{
    @Override
    public Boolean handleJob(Object job) {
        System.out.println(job.toString());
        return true;
    }

}
