package com.xuanner.dt.test.core;

import com.xuanner.dt.Dt;
import com.xuanner.dt.core.DtSortedSet;
import com.xuanner.dt.test.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by xuan on 2018/8/29.
 */
public class DtSortedSetTest extends BaseTest {

    @Test
    public void test() {
        DtSortedSet set = Dt.newDtSortedSet("setName");

        //清理
        set.removeByScoreRange(1, 100);

        set.add(1, "a1");
        set.add(20, "a20");
        set.add(99, "a99");
        long rank = set.rank("a20");
        Assert.assertTrue(rank == 1);
        long size = set.size();
        Assert.assertTrue(size == 3);

        set.removeByScoreRange(1, 98);
        Assert.assertTrue(set.size() == 1);

        set.close();
    }

    @Test
    public void testIntersect() {
        DtSortedSet set1 = Dt.newDtSortedSet("setName1");
        DtSortedSet set2 = Dt.newDtSortedSet("setName2");
        DtSortedSet resultSet = Dt.newDtSortedSet("resultSetName");

        //清理
        set1.removeByScoreRange(1, 100);
        set2.removeByScoreRange(1, 100);
        resultSet.removeByScoreRange(1, 100);

        set1.add(80, "xuan");
        set1.add(60, "wuming");
        Assert.assertTrue(set1.size() == 2);
        Assert.assertTrue(set1.getScore("xuan") == 80);
        Assert.assertTrue(set1.getScore("wuming") == 60);

        set2.add(95, "xuan");
        Assert.assertTrue(set2.size() == 1);
        Assert.assertTrue(set2.getScore("xuan") == 95);

        set1.intersectFrom(set2, resultSet);
        Assert.assertTrue(resultSet.size() == 1);
        Assert.assertTrue(resultSet.getScore("xuan") == 80);

        //close
        set1.close();
        set2.close();
        resultSet.close();
    }

}
