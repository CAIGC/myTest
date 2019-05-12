package com.cai.modules.DelayQNotify.base;

/**
 * Created by caigc on 19/5/12.
 */
public abstract class DelayQObserver implements IDelayQObserver{

    @Override
    public void onMessage(Object job) {
        if(this.handleJob(job)){
            deleteJob(job);
            return;
        }
        failAddJob(job);
    }

    public abstract Boolean handleJob(Object job);

    public void deleteJob(Object job){
        System.out.println("delete job" + job.toString());
    }

    public void failAddJob(Object job){
        System.out.println("fail add job to queue");
    }
}
