package org.qpyong.demos.springboot.vo;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ResponseVO {

    /**
     * 响应代码
     */
    private String code;

    private List<?> data;

    private String msg;

    private String exception;
    /**
     * 总共多少页
     */
    private int pages;
    /**
     * 当前页
     */
    private int currentPage;
    /**
     * 总共多少条记录
     */
    private int rows;


    public ResponseVO(String code) {
        this.code = code;
    }

    public String getException() {
        return exception;
    }

    protected void setException(String exception) {
        this.exception = exception;
    }

    public int getPages() {
        return pages;
    }

    protected void setPages(int pages) {
        this.pages = pages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    protected void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRows() {
        return rows;
    }

    protected void setRows(int rows) {
        this.rows = rows;
    }

    public String getCode() {
        return code;
    }

    protected void setCode(String code) {
        this.code = code;
    }

    public List<?> getData() {
        return data;
    }

    protected void setData(List<?> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    protected void setMsg(String msg) {
        this.msg = msg;
    }

    public static ResponseVO createResponseForError(String msg) {
        ResponseVO vo = new ResponseVO(ResponseStatus.Fail.getCode());
        vo.setMsg(msg);
        return vo;
    }

    public static <T> ResponseVO createResponseFromEntity(T entity) {
        ResponseVO vo = null;
        if(entity == null) {
            vo = new ResponseVO(ResponseStatus.Success.getCode());
            vo.setMsg("没有查询到数据");
            return vo;
        }
        vo = new ResponseVO(ResponseStatus.Success.getCode());
        vo.setData(Collections.singletonList(entity));
        return vo;
    }

    public static <T> ResponseVO createResponseFromPage(Page<T> page) {
        ResponseVO vo = null;
        if(page == null || !page.hasContent()) {
            vo = new ResponseVO(ResponseStatus.Success.getCode());
            vo.setMsg("没有查询到数据");
            return vo;
        }
        Iterator<T> it = page.iterator();
        vo = new ResponseVO(ResponseStatus.Success.getCode());
        List<T> list = new ArrayList<>();
        while (it.hasNext()) {
            list.add(it.next());
        }
        vo.setData(list);
        vo.setCurrentPage(page.getNumber());
        vo.setPages(page.getTotalPages());
        vo.setRows(new Long(page.getTotalElements()).intValue());
        return vo;
    }

    public enum ResponseStatus {
        Success("200"),
        Fail("400");

        private String code;

        ResponseStatus(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }


}
