package com.gee.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

import org.junit.Test;
import org.springframework.util.StopWatch;

import com.gee.ConfigTest;

public class AsyncUtilsTest extends ConfigTest{
	class MyThread implements Runnable{
		private CountDownLatch countDownLatch;
		public CountDownLatch getCountDownLatch() {
			return countDownLatch;
		}

		public void setCountDownLatch(CountDownLatch countDownLatch) {
			this.countDownLatch = countDownLatch;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("sayHello");
			this.countDownLatch.countDown();
		}
		
	}
	@Test
	public void testPool() throws InterruptedException {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		CountDownLatch countDownLatch = new CountDownLatch(100);
		List<MyThread> tlist = new ArrayList<MyThread>();
		for(int i = 0 ; i < 100 ; i ++) {
			MyThread t = new MyThread();
			t.setCountDownLatch(countDownLatch);
			tlist.add(t);
		}
		tlist.parallelStream().forEach(item -> {
			try {
				AsyncUtils.execute(item);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		countDownLatch.await();
		stopWatch.stop();
		System.out.println(stopWatch.getTotalTimeMillis());
		System.out.println(AsyncUtils.getSuccessTaskNum());
		System.out.println(AsyncUtils.getRejectTaskNum());

	}
}
