package com.example.demo.dto;

/**
 * @author 咲蛍
 * @date 2021/05/27
 */
public class QueryDTO {
    /**
     * 页码
     */
    private Integer pageNo;

    /**
     * 页面大小
     */
    private Integer pageSize;

    /**
     * 关键字
     */
    private String keyword;


    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "QueryDTO{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
