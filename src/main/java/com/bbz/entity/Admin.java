package com.bbz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "yx_admin")
public class Admin {
    @Id
    private String id;
    @Column(name = "username")
    private String userName;
    @Column(name = "password")
    private String passWord;
}
