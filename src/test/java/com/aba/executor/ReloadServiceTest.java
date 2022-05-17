package com.aba.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ReloadServiceTest {
	@BeforeEach
	public void setup() {
	    MockitoAnnotations.initMocks(this);
	}
	
	@Mock
	LeapYearChecker checker = new LeapYearChecker();
	
	@Mock
	ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
	
	@Test
	public void scheduleReloadTask_WhenInvoked_ExecutorMethodIsCalled() {
		ReloadService service = new ReloadService(executor, checker);
		
		service.scheduleReloadTask();
		
		final ArgumentCaptor<Runnable> argumentCaptor = ArgumentCaptor.forClass(Runnable.class);
		
		Mockito.verify(executor, Mockito.times(1)).scheduleAtFixedRate(argumentCaptor.capture(), Mockito.eq(0l), Mockito.eq(1l), Mockito.eq(TimeUnit.DAYS));
		
		argumentCaptor.getAllValues().get(0).run();
        Mockito.verify(checker, Mockito.times(1)).isLeapYear(2003);
	}
}
