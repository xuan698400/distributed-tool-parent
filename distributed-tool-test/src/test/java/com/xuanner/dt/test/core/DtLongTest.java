package com.xuanner.dt.test.core;

import com.xuanner.dt.Dt;
import com.xuanner.dt.core.DtLong;
import com.xuanner.dt.core.impl.JedisDtLong;
import com.xuanner.dt.test.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * Created by xuan on 2018/8/29.
 */
public class DtLongTest extends BaseTest {

    @Test
    public void test() throws Exception {

        DtLong l = Dt.newDtLong("longName");
        l.setValue(0L);
        Assert.assertTrue(l.getValue() == 0L);

        CountDownLatch c1 = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                DtLong ll = new JedisDtLong("longName");
                for (int j = 0; j < 20; j++) {
                    ll.incr(1L);
                }
                c1.countDown();
            }).start();
        }
        c1.await();
        Assert.assertTrue(l.getValue() == 400L);

        CountDownLatch c2 = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                DtLong ll = new JedisDtLong("longName");
                for (int j = 0; j < 20; j++) {
                    ll.decr(1L);
                }
                c2.countDown();
            }).start();
        }
        c2.await();
        Assert.assertTrue(l.getValue() == 200L);
    }

}
