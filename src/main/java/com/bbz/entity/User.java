package com.bbz.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "yx_user")
public class User {
    @Id
    @Excel(name = "ID")
    private String id;
    @Column(name = "nick_name")
    @Excel(name = "昵称")
    private String nickName;//昵称
    @Excel(name = "手机号")
    private Integer phone;//手机号
    @Column(name = "pic_img")
    @Excel(name = "头像",type = 2)
    private String picImg;//头像
    @Excel(name = "简介")
    private String brief;//简介
    @Excel(name = "学分")
    private Double score;//学分
    @Column(name = "create_date")
    @Excel(name = "创建时间")
    private Date createDate;//创建时间
    @Excel(name = "状态")
    private String status;//状态
    private String sex;
}
