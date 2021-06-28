package com.beordie.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname ResultData
 * @Description TODO
 * @Date 2021/6/27 19:01
 * @Created 30500
 */
public class ResultData<T> {
    //每次查询的数据集合
    private List<T> rows = new ArrayList<>();
    //总数量
    private int total;

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

