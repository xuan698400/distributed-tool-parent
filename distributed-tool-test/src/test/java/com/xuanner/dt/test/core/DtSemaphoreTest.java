package com.xuanner.dt.test.core;

import com.xuanner.dt.Dt;
import com.xuanner.dt.core.DtSemaphore;
import com.xuanner.dt.test.BaseTest;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xuan on 2018/8/30.
 */
public class DtSemaphoreTest extends BaseTest {

    @Test
    public void test() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(200);
        AtomicInteger concurrency = new AtomicInteger(0);

        for (int i = 0; i < 200; i++) {
            new Thread(() -> {
                DtSemaphore semaphore = Dt.newDtSemaphore("semaphoreName", 5);
                for (int j = 0; j < 5; j++) {
                    String sId = semaphore.acquire();
                    if (null != sId) {
                        //System.out.println("sId:" + sId);
                        concurrency.incrementAndGet();
                        sleep(1);
                        concurrency.decrementAndGet();
                        semaphore.release(sId);
                    } else {
                        sleep(1);
                    }
                }
                countDownLatch.countDown();
            }).start();
        }

        new Thread(() -> {
            while (true) {
                System.out.println("concurrency:" + concurrency.get());
                sleep(1);
            }
        }).start();

        countDownLatch.await();
    }

}
