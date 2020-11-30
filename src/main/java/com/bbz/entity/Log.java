package com.bbz.entity;

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
@Table(name = "yx_log")
public class Log {
    @Id
    private String id;
    @Column(name = "username")
    private String userName;
    @Column(name = "operation_time")
    private Date operationTime;
    private String oper;
    private String status;

}
