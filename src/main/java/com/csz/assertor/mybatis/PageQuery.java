package com.csz.assertor.mybatis;

import com.baomidou.mybatisplus.plugins.Page;
import com.csz.assertor.rest.request.QueryRequest;

/**
 * 分页工具类
 * @param <T>
 */
public class PageQuery<T> extends Page<T> {
    /**
     * 当前页，默认值 1
     */
    private static final int DEFAULT_CURRENT_PAGE = 1;
    /**
     * 每页条数，默认值 10
     */
    private static final int DEFAULT_PAGE_SIZE = 10;

    /**
     * 当前页
     */
    private int currentPage = DEFAULT_CURRENT_PAGE;

    /**
     * 每页条数
     */
    private int pageSize = DEFAULT_PAGE_SIZE;

    public PageQuery(QueryRequest queryRequest) {
        if (queryRequest.getCurrent() != null) {
            currentPage=queryRequest.getCurrent();
        }
        if (queryRequest.getSize() != null) {
            pageSize=queryRequest.getSize();
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
