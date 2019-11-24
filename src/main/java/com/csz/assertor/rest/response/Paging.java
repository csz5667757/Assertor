package com.csz.assertor.rest.response;

import com.baomidou.mybatisplus.plugins.Page;
import com.csz.assertor.utils.BeanUtil;
import com.google.common.collect.Lists;

import java.util.List;

public class Paging<T> {

    //总记录数
    private int totalCount;
    //每页记录数
    private int pageSize;
    //总页数
    private int totalPage;
    //当前页数
    private int curPage;
    //列表数据
    private List<T> list;

    public Paging(int totalCount, int pageSize, int totalPage, int curPage, List<T> list) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.curPage = curPage;
        this.list = list;
    }

    public Paging(Page<?> page, Class clazz) {
        if (this.list == null || this.list.isEmpty()) {
            this.list = Lists.newArrayList();
        }

        for (Object source : page.getRecords()) {
            T target = BeanUtil.copyBean(source, clazz);
            list.add(target);
        }

        this.totalCount = (int) page.getTotal();
        this.curPage = (int)page.getCurrent();
        this.pageSize = (int)page.getSize();
        this.totalPage = (int) page.getPages();
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
