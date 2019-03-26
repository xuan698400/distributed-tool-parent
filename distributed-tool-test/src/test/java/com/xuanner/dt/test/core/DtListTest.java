package com.xuanner.dt.test.core;

import com.xuanner.dt.Dt;
import com.xuanner.dt.core.DtList;
import com.xuanner.dt.test.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by xuan on 2018/8/29.
 */
public class DtListTest extends BaseTest {

    @Test
    public void test() {
        DtList list = Dt.newDtList("listName");

        //清理
        while (null != list.popRight()) {

        }

        list.pushLeft(new String[] { "a", "b" });
        Assert.assertTrue(list.size() == 2);
        Assert.assertTrue("a".equals(list.popRight()));
        Assert.assertTrue(list.size() == 1);
        Assert.assertTrue("b".equals(list.popRight()));
        Assert.assertTrue(list.size() == 0);
        Assert.assertTrue(null == list.popRight());

        list.close();
    }

}
