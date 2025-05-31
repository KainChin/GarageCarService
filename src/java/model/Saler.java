package model;

import java.sql.Date;

public class Saler {

    private String salesName;
    private String salesID;
    private Date birthday;
    private String sex;
    private String salesAddress;

    public Saler(String salesName, String salesID, Date birthday, String sex, String salesAddress) {
        this.salesName = salesName;
        this.salesID = salesID;
        this.birthday = birthday;
        this.sex = sex;
        this.salesAddress = salesAddress;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public String getSalesID() {
        return salesID;
    }

    public void setSalesID(String salesID) {
        this.salesID = salesID;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSalesAddress() {
        return salesAddress;
    }

    public void setSalesAddress(String salesAddress) {
        this.salesAddress = salesAddress;
    }

}
