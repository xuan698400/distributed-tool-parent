package com.xuanner.dt.test.core;

import com.xuanner.dt.Dt;
import com.xuanner.dt.core.DtQueue;
import com.xuanner.dt.test.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by xuan on 2018/8/29.
 */
public class DtQueueTest extends BaseTest {

    @Test
    public void test() {
        DtQueue queue = Dt.newDtQueue("queueName");

        //清理
        while (null != queue.pop()) {
        }

        queue.push("eeee");
        String eeee = queue.pop();
        Assert.assertTrue("eeee".equals(eeee));
        String eeee2 = queue.pop();
        Assert.assertTrue(null == eeee2);

        queue.close();
    }

    @Test
    public void testForLimitSize() {
        DtQueue queue = Dt.newDtQueue("queueName", 2);

        //清理
        while (null != queue.pop()) {
        }

        boolean b1 = queue.push("e1");
        Assert.assertTrue(b1);
        boolean b2 = queue.push("e2");
        Assert.assertTrue(b2);
        boolean b3 = queue.push("e3");
        Assert.assertTrue(!b3);
        Assert.assertTrue(queue.size() == 2);

        queue.close();
    }

}
