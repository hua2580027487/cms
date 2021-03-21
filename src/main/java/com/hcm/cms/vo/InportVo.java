package com.hcm.cms.vo;

import java.util.Date;

import com.hcm.cms.domain.Inport;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class InportVo extends Inport {

	private static final long serialVersionUID = 1L;

	
	private Integer page=1;
	private Integer limit=10;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endTime;
}
