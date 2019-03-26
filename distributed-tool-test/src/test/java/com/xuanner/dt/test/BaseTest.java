package com.xuanner.dt.test;

import com.xuanner.dt.Dt;
import org.junit.After;
import org.junit.Before;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by xuan on 2018/8/12.
 */
public class BaseTest {

    @Before
    public void before() {
        //Dt.getInstance().initJedis("xxx", 8380, "xxx");

        Set<String> sentinelSet = new HashSet<>();
        sentinelSet.add("ip:port");
        sentinelSet.add("ip:port");
        Dt.getInstance().initJedisSentinel("mymaster", sentinelSet, "xxx");
    }

    @After
    public void after() {
        Dt.getInstance().getDefaultJedisFactory().destroy();
    }

    protected void sleep(int time) {
        try {
            Thread.sleep(time * 1000);
        } catch (Exception e) {
            //Ingore
        }
    }

    protected void hold() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            String command = sc.next();
            System.out.println("输入命令:" + command);
            if ("quit".equals(command)) {
                System.out.println("成功退出");
                break;
            }
        }
    }

}
