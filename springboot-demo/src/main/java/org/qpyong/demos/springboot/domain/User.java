package org.qpyong.demos.springboot.domain;

import freemarker.template.utility.StringUtil;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue()
    private Integer id;
    private String userName;
    private String password;
    private String email;
    private String deptId;
    private String deptName;
    private String title;// 岗位名称

    public boolean isEmpty() {
        if(id != null)
            return false;
        if(!StringUtils.isEmpty(userName))
            return false;
        if(!StringUtils.isEmpty(email))
            return false;
        if(!StringUtils.isEmpty(deptId))
            return false;
        if(!StringUtils.isEmpty(title))
            return false;
        return true;
    }


    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
