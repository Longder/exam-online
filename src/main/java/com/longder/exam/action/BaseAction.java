package com.longder.exam.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.ParentPackage;

/**
 * Struts2基础Action父类
 * 系统中所有Action可继承次类
 */
@ParentPackage("struts-default")
public class BaseAction extends ActionSupport {
    /**
     * 统一响应成功标识
     */
    protected final String SUCCESS = "success";
    /**
     * 统一响应失败标识
     */
    protected final String ERROR = "error";
    /**
     * Result类型，重定向
     */
    protected final String REDIRECT = "redirect";
    /**
     * Result类型，ajax
     */
    protected final String AJAX = "ajax";
}
