package com.aba.executor;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ReloadService {
	private ScheduledExecutorService executor;
	private LeapYearChecker checker;
	
	ReloadService(ScheduledExecutorService executor, LeapYearChecker checker) {
		this.executor = executor;
		this.checker = checker;
	}
	
	public void scheduleReloadTask() {
        executor.scheduleAtFixedRate(() -> {
          checker.isLeapYear(2003);
        }, 0, 1, TimeUnit.DAYS);
    }
}
