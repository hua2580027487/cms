package com.hcm.cms.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LoginfoVo extends LogLogin {
    /**
     *
     */
    private static final long serialVersionUID = 1L;


    private Integer page = 1;
    private Integer limit = 10;

    private Integer[] ids;//接收多个ID


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
