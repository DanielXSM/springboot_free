package com.free.zdp.model;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zdp123
 * @since 2019-09-02
 */
public class TLdUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String pwd;
    private String like;
    private Integer age;
    private String sex;
    private String phone;
    private String cname;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "TLdUser{" +
        ", id=" + id +
        ", username=" + username +
        ", pwd=" + pwd +
        ", like=" + like +
        ", age=" + age +
        ", sex=" + sex +
        ", phone=" + phone +
        ", cname=" + cname +
        "}";
    }
}
