package com.hansheng.GenericImpl;

/**
 * Created by hansheng on 2016/7/14.
 */
public class Contact implements Info {
    private String addr;                // 地址
    private String telphone;        // 电话
    private String zipcode;         // 邮编

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Contact(String addr, String telphone, String zipcode) {

        this.addr = addr;
        this.telphone = telphone;
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "addr='" + addr + '\'' +
                ", telphone='" + telphone + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
