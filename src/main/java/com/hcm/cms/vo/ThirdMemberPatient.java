package com.hcm.cms.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class ThirdMemberPatient implements Serializable {
    private String thMemPaId;

    private Integer wcaId;

    private String thMemId;

    private String thMemPaName;

    private Integer thMemPaSex;

    private Integer thMemPaAge;

    private String thMemPaPhone;

    private Integer status;

    private Date createDatetime;

    private Date updateDatetime;

    private Integer thMemMemberPatientRelation;

    private Date thMemBirthday;

    private Integer thMemDefault;

    private String thCommunityName;


}