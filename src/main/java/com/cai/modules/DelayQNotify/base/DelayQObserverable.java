package com.cai.modules.DelayQNotify.base;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by caigc on 19/5/12.
 */
@Component
public class DelayQObserverable implements IDelayQObserverable {
    private Map<String, List> listMap;
    private ReentrantLock lock = new ReentrantLock();

    public DelayQObserverable() {
        listMap = new HashMap<>();
    }

    @Override
    public void registerObserver(String topic, IDelayQObserver o) {
        List<IDelayQObserver> list = listMap.get(topic);
        if (list == null || list.isEmpty()) {
            synchronized (this) {
                list = new ArrayList<>();
            }
        }
        list.add(o);
        listMap.put(topic, list);
    }

    @Override
    public void removeObserver(String topic, IDelayQObserver o) {
        if (listMap.isEmpty()) {
            return;
        }
        List<IDelayQObserver> list = listMap.get(topic);
        if (list == null || list.isEmpty()) {
            return;
        }
        try {
            lock.lock();
            list.remove(o);
            if (list.isEmpty()) {
                listMap.remove(topic);
            }
            System.out.println(JSON.toJSONString(listMap));
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void notifyObserver(String topic, Object message) {
        try {
            lock.lock();
            List<IDelayQObserver> list = listMap.get(topic);
            if (list == null || list.isEmpty()){
                return;
            }
            for (IDelayQObserver observer : list) {
                observer.onMessage(message);
            }
        } finally {
            lock.unlock();
        }
    }

    public void setInformation(String topic, Object message) {
        this.notifyObserver(topic, message);
    }
}
