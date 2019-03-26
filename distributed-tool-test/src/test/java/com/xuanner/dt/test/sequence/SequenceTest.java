package com.xuanner.dt.test.sequence;

import com.xuanner.dt.Dt;
import com.xuanner.dt.test.BaseTest;
import org.junit.Test;
import com.xuanner.dt.sequence.DtSequence;

import java.util.concurrent.CountDownLatch;

/**
 * 分布式序列号生成
 * Created by xuan on 2018/8/12.
 */
public class SequenceTest extends BaseTest {

    @Test
    public void test() {
        DtSequence sequence = Dt.newDtSequence();

        long start = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    System.out.println("++++++++++id:" + sequence.nextId("sequenceName4") + "thread:"
                                       + Thread.currentThread().getName());
                }
                countDownLatch.countDown();
            });
            thread.setName("thread-" + i);
            thread.start();
        }

        try {
            countDownLatch.await();
        } catch (Exception e) {
        }

        System.out.println("totalCost:" + (System.currentTimeMillis() - start) + "MS");
    }

}
