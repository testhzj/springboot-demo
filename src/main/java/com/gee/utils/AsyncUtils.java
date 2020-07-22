package com.gee.utils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AsyncUtils {
	
	private static final int corePoolSize = 1;
	private static final int maximumPoolSize = 10;
	private static final int keepAliveTime = 60 * 1000;
	private static final int maxTaskNum = 1;
	private static final int initialTaskNum = 0;
	private static final AtomicInteger successaskNum = new AtomicInteger(initialTaskNum);
	private static final AtomicInteger failTaskNum = new AtomicInteger(initialTaskNum);
	private static final AtomicInteger rejectTaskNum = new AtomicInteger(initialTaskNum);


	private static final boolean successFlag = true;
	
	private static final ThreadPoolExecutor executorService = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
			keepAliveTime, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(maxTaskNum),
			new RejectedExecutionHandler() {
				@Override
				public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
					// 被拒绝需要有补偿机制--todo
					rejectTaskNum.incrementAndGet();
				}
			});

	public static void execute(Runnable runnable) throws InterruptedException, ExecutionException {
		try {
			if (executorService.submit(runnable, successFlag).get().booleanValue()) {
				successaskNum.incrementAndGet();
			}
		} catch (Exception e) {
			failTaskNum.incrementAndGet();
		}
	}
	
	public static int getSuccessTaskNum() {
		return successaskNum.get();
	}
	
	public static int getFailTaskNum() {
		return failTaskNum.get();
	}
	
	public static int getRejectTaskNum() {
		return rejectTaskNum.get();
	}
}
