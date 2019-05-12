package com.cai.modules.DelayQNotify.base;

/**
 * Created by caigc on 19/5/12.
 */
public interface IDelayQObserverable {
    public void registerObserver(String topic,IDelayQObserver o);
    public void removeObserver(String topic,IDelayQObserver o);
    public void notifyObserver(String topic,Object object);
}
