package com.xuanner.dt.test.pubsub;

import com.xuanner.dt.Dt;
import com.xuanner.dt.pubsub.DtPublisher;
import com.xuanner.dt.pubsub.DtSubscriber;
import com.xuanner.dt.test.BaseTest;
import org.junit.Test;
import com.xuanner.dt.pubsub.DtMessageListener;

import java.util.concurrent.CountDownLatch;

/**
 * Created by xuan on 2018/8/17.
 */
public class PubsubTest extends BaseTest {

    private final static String TOPIC1 = "TOPIC1";

    @Test
    public void test() {
        CountDownLatch c1 = new CountDownLatch(3);
        CountDownLatch c2 = new CountDownLatch(3);

        //订阅者
        DtMessageListener l1 = new DtMessageListener() {

            private String topic = "TOPIC1";

            @Override
            public String topic() {
                return topic;
            }

            @Override
            public void onMessage(String message) {
                System.out.println("subscribe1--->topic=" + topic + ",message=" + message);
                c1.countDown();
            }
        };

        DtMessageListener l2 = new DtMessageListener() {

            private String topic = "TOPIC1";

            @Override
            public String topic() {
                return topic;
            }

            @Override
            public void onMessage(String message) {
                System.out.println("subscribe2--->topic=" + topic + ",message=" + message);
                c2.countDown();
            }
        };

        DtSubscriber subscriber = Dt.newDtSubscriber();
        subscriber.addListener(l1);
        subscriber.addListener(l2);
        subscriber.init();

        //发布者
        DtPublisher publisher = Dt.newDtPublisher();
        new Thread(() -> {
            while (true) {
                sleep(1);
                publisher.publish(TOPIC1, "你好，哈哈");
            }
        }).start();

        try {
            c1.await();
            c2.await();
        } catch (Exception e) {
            //Ingore
        }
    }

}
