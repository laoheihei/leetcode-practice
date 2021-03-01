package com.hstudy.mt.basic;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThirdThread {
	public static void main(String[] args) {
		ThirdThread thirdThread = new ThirdThread();

		Callable<Integer> callable = new Callable<Integer>() {
			@Override
			public Integer call() {
				int i = 0;
				for (; i < 100; i++) {
					System.out.println(Thread.currentThread().getName() + " 的循环变量i的值 " + i);
				}
				return i;
			}
		};
		FutureTask<Integer> integerFutureTask = new FutureTask<>(
				() -> {
			int i = 0;
			for (; i < 100; i++) {
				System.out.println(Thread.currentThread().getName() + " 的循环变量i的值 " + i);
			}

			return i;
		});
	}
}
