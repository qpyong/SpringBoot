package org.qpyong.demos.springboot.vo;

public class UserPageSearchVO extends PageSearchVO {

    private String userName;
    private String deptId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
}
