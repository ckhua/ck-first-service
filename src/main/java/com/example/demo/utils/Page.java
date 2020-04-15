package com.example.demo.utils;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 封装page
 * @param <T>
 */
public class Page<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final int NORMAL_PAGE_NO = 1;
    private static final int NORMAL_PAGE_SIZE = 10;
    private List<T> list;
    private int pageNo;
    private int pageNumber;
    private int pageSize;
    private int totalPage;
    private int totalRow;
    private boolean firstPage;
    private boolean lastPage;

    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    public int getFirstResult() {
        int firstResult = (this.getPageNo() - 1) * this.getPageSize();
        if (firstResult >= this.getTotalRow()) {
            firstResult = 0;
        }

        return firstResult;
    }

    public int getMaxResults() {
        return this.getPageSize();
    }

    private Page() {
        this(new ArrayList(), 1, 0, 1, 0);
    }

    public Page(HttpServletRequest request, HttpServletResponse response) {
        this.pageNo = 1;
        this.pageNumber = 1;
        String no = request.getParameter("pageNo");
        String size = request.getParameter("pageSize");
        int sz;
        if (StringUtils.isNotBlank(no) && StringUtils.isNumeric(no)) {
            sz = Integer.parseInt(no);
            this.setPageNo(sz > 0 ? sz : 1);
        } else {
            this.setPageNo(1);
        }

        if (StringUtils.isNotBlank(size) && StringUtils.isNumeric(size)) {
            sz = Integer.parseInt(size);
            this.setPageSize(sz > 0 ? sz : 10);
        } else {
            this.setPageSize(10);
        }

    }

    public Page(int pageNo, int pageSize) {
        this(new ArrayList(), pageNo, pageSize, 1, 0);
    }

    Page(List<T> list, int pageNo, int pageSize, int totalPage, int totalRow) {
        this.pageNo = 1;
        this.pageNumber = 1;
        this.list = list;
        this.setPageNo(pageNo > 0 ? pageNo : 1);
        this.setPageSize(pageSize > 0 ? pageSize : 10);
        this.totalPage = totalPage;
        this.totalRow = totalRow;
        this.firstPage = this.isFirstPage();
        this.lastPage = this.isLastPage();
    }

    public boolean isFirstPage() {
        return this.pageNo == 1;
    }

    public boolean isLastPage() {
        return this.pageNo == this.totalPage;
    }

    public List<T> getList() {
        return this.list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
        this.pageNumber = pageNo;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalRow() {
        return this.totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
        if (totalRow > 0) {
            this.setTotalPage(this.calTotalPage(totalRow));
        }

        this.firstPage = this.isFirstPage();
        this.lastPage = this.isLastPage();
    }

    private int calTotalPage(int totalRow) {
        if (totalRow <= 0) {
            return 0;
        } else {
            int mo = totalRow % this.getPageSize() == 0 ? totalRow / this.getPageSize() : totalRow / this.getPageSize() + 1;
            return mo;
        }
    }

    public int getPageNumber() {
        return this.pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
