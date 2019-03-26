package com.xuanner.dt.test.lock;

import com.xuanner.dt.Dt;
import com.xuanner.dt.lock.DtLock;
import com.xuanner.dt.test.BaseTest;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * Created by xuan on 2018/8/30.
 */
public class DtLockTest extends BaseTest {

    @Test
    public void test() throws Exception {
        CountDownLatch downLatch = new CountDownLatch(20);

        for (int i = 0; i < 20; i++) {
            DtLock lock = Dt.newDtLock("lockName");
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    String kId = lock.tryLock();
                    if (null != kId) {
                        sleep(1);
                        System.out.println("kId:" + kId);
                        lock.unLock(kId);
                    }else{
                        sleep(1);
                    }
                }
                downLatch.countDown();
            }).start();
        }
        downLatch.await();
    }

}
