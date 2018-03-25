package com.example.jswn.demo;

/**
 * Created by JSWN on 20-12-2017.
 */

class MyData  {
    private String name;
    private String mobileNo;
    private String Email;

    public MyData(String name, String mobileNo, String email) {
        this.name = name;
        this.mobileNo = mobileNo;
        Email = email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getEmail() {

        return Email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

}
