package com.innobles.smartplaykids.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created by mushareb on 28/12/2017.
 */


public interface SchedulerProvider {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();

}
