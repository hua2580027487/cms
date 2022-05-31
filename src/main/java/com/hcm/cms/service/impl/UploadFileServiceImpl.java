package com.hcm.cms.service.impl;

import com.hcm.cms.mapper.UploadFileMapper;
import com.hcm.cms.service.UploadFileService;
import com.hcm.cms.util.DateUtils;
import com.hcm.cms.util.PinyinUtils;
import com.hcm.cms.vo.SelfOwnedMedicine;
import com.hcm.cms.vo.ThirdMemberPatient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author hcm
 * @Date 2021/11/11 15:07
 * @Version 1.0
 */
@Service

public class UploadFileServiceImpl implements UploadFileService {
    @Autowired
    private UploadFileMapper uploadFileMapper;


    @Override
    @Transactional
    public void updateData() {
//        List<SelfOwnedMedicineType> ownedMedicineTypeList = uploadFileMapper.ownedMedicineTypeList();
        List<SelfOwnedMedicine> ownedMedicineList = uploadFileMapper.ownedMedicineList();
        List<SelfOwnedMedicine> modMedicineList = uploadFileMapper.ownedModMedicineList();
        ownedMedicineList.stream().forEach(j -> {
//            if (StringUtils.isEmpty(j.getMedicineNamePinyin())) {
//                j.setMedicineNamePinyin(PinyinUtils.getAllFormatStringPinYinEx(j.getMedicineName()));
//                System.out.println(j.getMedicineId());
//                uploadFileMapper.updateDataPinYin(j.getMedicineId(), j.getMedicineNamePinyin());
//            }
            modMedicineList.stream().forEach(i -> {
                if (j.getMedicineName().equalsIgnoreCase(i.getMedicineName())) {
                    j.setMedicinePrice(i.getMedicinePrice());
                    uploadFileMapper.updateDataById(j);
                    System.out.println(j.getMedicineName());
                }
            });
        });
//        uploadFileMapper.updateData();
    }

    @Override
    @Transactional
    public void updateDataDev() {
        List<ThirdMemberPatient> thirdMemberPatients = uploadFileMapper.patientList();
        thirdMemberPatients.stream().forEach(i -> {
            if (null == i.getThMemBirthday() && i.getThMemPaAge() != null) {
                Date now = DateUtils.getDateByDate(new Date(), DateUtils.DEFAULT_YEAR);
                Date patientBirthday = DateUtils.addYears(now, (0 - i.getThMemPaAge()));
//                uploadFileMapper.updatePatient(DateUtils.parseDate(patientBirthday,DateUtils.DEFAULT_DAY_PATTERN), i.getThMemPaId());
                System.out.println(i.getThMemPaAge());
                System.out.println(i.getThMemPaId());
                i.setThMemBirthday(DateUtils.parseDate(patientBirthday,DateUtils.DEFAULT_DAY_PATTERN));
                uploadFileMapper.updatePatient2(i);
            }
        });
        System.out.println("更新完毕");
    }
}
