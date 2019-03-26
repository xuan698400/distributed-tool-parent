package com.xuanner.dt.core;

import com.xuanner.dt.common.DtCloseable;

/**
 * 可排序Set集合
 * Created by xuan on 2018/8/29.
 */
public interface DtSortedSet extends DtCloseable {

    /**
     * 集合名称
     *
     * @return 集合名称
     */
    String getName();

    /**
     * 新增一个元素（当元素已经存在，更新score）
     *
     * @param score   用来排序的分数
     * @param element 元素
     */
    void add(double score, String element);

    /**
     * 删除指定分数区间内的的元素（包含两端）
     *
     * @param scoreFrom 起始
     * @param scoreTo   结束
     */
    void removeByScoreRange(double scoreFrom, double scoreTo);

    /**
     * 删除指定元素
     *
     * @param element 指定元素
     */
    void remove(String element);

    /**
     * 获取元素的排行下标位置
     *
     * @param element 元素
     * @return 下标位置
     */
    long rank(String element);

    /**
     * 获取元素个数
     *
     * @return 元素个数
     */
    long size();

    /**
     * 两个集合取交集
     *
     * @param fromSet   to集合
     * @param resultSet 结果集合，交完后score不变
     */
    void intersectFrom(DtSortedSet fromSet, DtSortedSet resultSet);

    /**
     * 获取元素的分数
     *
     * @param element 元素
     * @return 分数
     */
    double getScore(String element);

}
