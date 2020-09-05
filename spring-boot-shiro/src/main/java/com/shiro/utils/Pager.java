package com.shiro.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Pager<T> {
    public Pager() {
    }

    public Pager(Integer pageNum, Integer currentPage ) {

    }

    /**
     * 记录总数
     */
    @JsonProperty("total")
    private int count;
    /**
     * 每页多少条数据
     */
    private int pageSize = 3;
    /**
     * 第几页
     */
    private int pageNo = 1;

    /**
     * 数据
     */
    @JsonProperty("rows")
    private List<T> data = new ArrayList<>();

    /**
     * 总页数
     * @return
     */
    public int getTotalPages() {
        return (this.getCount()+this.getPageSize()-1)/this.getPageSize();
    }

    /**
     * 获取第几个开始取
     * @param pageSize 每页记录数
     * @param pageNo 当前第几页
     * @return
     */
    public int getStart(int pageSize, int pageNo) {
        return pageSize * (pageNo - 1);
    }

    public int getStart() {
        return this.getPageSize() * (this.getPageNo()-1);
    }

    /**
     * 取得首页
     * @return
     */
    public int getTopPageNo(){
        return 1;
    }

    /**
     * 上一页
     * @return
     */
    public int getPreviousPageNo(){
        if(this.getPageNo()<=1){
            return 1;
        }
        return this.getPageNo()-1;
    }
    /**
     * 下一页
     * @return
     */
    public int getNextPageNo(){
        if(this.getPageNo()>=getBottomPageNo()){
            return getBottomPageNo();
        }
        return this.getPageNo()+1;
    }
    /**
     * 取得尾页
     * @return
     */
    public int getBottomPageNo(){
        return getTotalPages();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
