package com.xinvacation.bss.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;

/**
 * @author lijiayin
 */
@Entity
@Table(name = "t_user")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    
    @Column(nullable = false,length = 32)
    private String name;

    @Column(nullable = false,length = 32)
    private String password;

    @Column(length = 64)
    private String email;

    @Column(length = 16)
    private String phone;

    @Column(length = 2000)
    private String question;

    @Column(length = 2000)
    private String answer;

    @Column(nullable = false,length = 1)
    private Integer role;

    @CreatedDate
    @Column(nullable = false)
    private Date createTime;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
