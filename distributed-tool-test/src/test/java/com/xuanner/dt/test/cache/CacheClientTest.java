package com.xuanner.dt.test.cache;

import com.xuanner.dt.Dt;
import com.xuanner.dt.cache.DtCacheClient;
import com.xuanner.dt.test.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xuan on 2018/8/14.
 */
public class CacheClientTest extends BaseTest {

    private final static String TEST_KEY     = "TEST_KEY";
    private final static String TEST_VAL     = "TEST_VAL";
    private final static int    TEST_TIMEOUT = 5;

    @Test
    public void testSetGetDel() {
        DtCacheClient cacheClient = Dt.newDtCacheClient();

        //单个操作
        //set
        cacheClient.set(TEST_KEY, TEST_VAL, TEST_TIMEOUT);
        //get
        Assert.assertTrue(TEST_VAL.equals(cacheClient.get(TEST_KEY)));
        //del
        cacheClient.del(TEST_KEY);
        Assert.assertTrue(null == cacheClient.get(TEST_KEY));

        //批量操作
        List<String> kList = new ArrayList<>();
        kList.add("k1");
        kList.add("k2");

        Map<String, String> vMap = new HashMap<>();
        vMap.put("k1", "v1");
        vMap.put("k2", "v2");

        //setBulk
        cacheClient.setBulk(vMap, TEST_TIMEOUT);
        //getBulk
        Map<String, String> map = cacheClient.getBulk(kList);
        Assert.assertTrue("v1".equals(map.get("k1")));
        Assert.assertTrue("v2".equals(map.get("k2")));
        //delBulk
        cacheClient.delBulk(kList);
        Assert.assertTrue(null == cacheClient.get("k1"));
        Assert.assertTrue(null == cacheClient.get("k2"));

        cacheClient.close();
    }

}
