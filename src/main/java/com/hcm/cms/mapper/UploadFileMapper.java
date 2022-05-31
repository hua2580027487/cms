package com.hcm.cms.mapper;

import com.hcm.cms.vo.SelfOwnedMedicine;
import com.hcm.cms.vo.ThirdMemberPatient;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Author hcm
 * @Date 2021/11/11 15:09
 * @Version 1.0
 */
public interface UploadFileMapper {


    List<SelfOwnedMedicine> ownedMedicineList();

    void updateDataPinYin(@Param("meId") String meId, @Param("py") String py);

    void updateDataById(SelfOwnedMedicine selfOwnedMedicine);

    List<SelfOwnedMedicine> ownedModMedicineList();

    List<ThirdMemberPatient> patientList();

    void updatePatient(@Param("birthday") Date birthday, @Param("paId") String paId);

    void updatePatient2(ThirdMemberPatient i);
}
