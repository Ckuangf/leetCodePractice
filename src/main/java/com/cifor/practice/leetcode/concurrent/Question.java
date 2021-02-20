package com.cifor.practice.leetcode.concurrent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Question {


    /*有一个车牌拍卖系统，参与竞拍的人每个人需要拍出一个价格，
    但是竞拍人，不能连续出价，必须有人出价以后自己才能出价，否则无效。
    并且每次竞拍价格区间在 1-2001−200 元以内，请写一段代码，
    模拟多人竞拍 1,0001,000 次以后，谁获得了基础价格是 4,0004,000 元的车牌.*/
    public static void auction(int count, int peopleNum, int basePrice) {

        String userPrefix = "用户";
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(peopleNum, peopleNum,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        ArrayList<String> users = new ArrayList<>(peopleNum);
        for (int i = 1; i <= peopleNum; i++) {
            users.add(userPrefix + "-" + i);
        }
        final String[] currentName = {""};
        final Integer[] currentPrice = {basePrice};
        ReentrantLock reentrantLock = new ReentrantLock();
        AtomicInteger atomicInteger = new AtomicInteger(count);
        threadPoolExecutor.execute(() -> {
            while (atomicInteger.get() > 0) {
                if (reentrantLock.tryLock()) {
                    int index = (int) Math.floor(Math.random() * peopleNum);
                    while (currentName[0] != "" && String.valueOf(index).equalsIgnoreCase(currentName[0].split("-")[1])) {
                        index = (int) Math.floor(Math.random() * peopleNum);
                    }
                    String user = users.get(index);
                    currentName[0] = user;
                    currentPrice[0] = currentPrice[0] + (int) Math.floor(Math.random() * 200 + +1);
                    System.out.println(String.format("用户：%s 出价： %d", currentName[0], currentPrice[0]));
                    atomicInteger.getAndDecrement();
                    reentrantLock.unlock();
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadPoolExecutor.shutdown();
        while(!threadPoolExecutor.isTerminated()){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("最终出价：" + "  用户：" + currentName[0] + " 价格：" + currentPrice[0]);
    }


}
