package com.xuanner.dt.sequence;

/**
 * 序列号生成异常
 * Created by xuan on 2018/1/10.
 */
public class DtSequenceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DtSequenceException(String message) {
        super(message);
    }

}
