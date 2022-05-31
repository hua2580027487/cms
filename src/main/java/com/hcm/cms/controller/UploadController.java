package com.hcm.cms.controller;

import com.hcm.cms.service.UploadFileService;
import com.hcm.cms.strategy.ProcessParamContext;
import com.hcm.cms.strategy.ProcessParamStrategy;
import com.hcm.cms.strategy.impl.HoneyPillProcessPriceParam;
import com.hcm.cms.util.SpringContextUtils;
import com.hcm.cms.vo.ProcessPriceParamResponseVO;
import com.hcm.cms.vo.SysStudent;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.math.BigDecimal;

@Controller
@Slf4j
public class UploadController {
    @Autowired
    private UploadFileService uploadFileServiceservice;

    @RequestMapping(value = "/testParam", method = RequestMethod.POST)
    @ResponseBody
    public String testParam(@RequestBody ProcessPriceParamResponseVO processPriceParamResponseVO) {
        log.info("--------------- pId={}----------------", processPriceParamResponseVO.getPId());
        return "测试传参";
    }

    @RequestMapping("/importExcel")
    @ResponseBody
    public String importExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse
            response) {
        uploadFileServiceservice.updateDataDev();
//        System.out.println("file" + file.getSize());
//
//        System.out.println(SpringContextUtils.getApplicationContext());
//        ProcessParamStrategy processParamStrategy  = SpringContextUtils.getBean("honeyPillProcessPriceParam");
//        ProcessParamContext processParamContext = new ProcessParamContext(processParamStrategy);
//        ProcessPriceParamResponseVO processPriceParamResponseVO = processParamContext.getParam(1);
//        System.out.println(processPriceParamResponseVO.getAccessoriesNum());
//        uploadFileServiceservice.updateData();


//        try {
//            // @RequestParam("file") MultipartFile file 是用来接收前端传递过来的文件
//            // 1.创建workbook对象，读取整个文档
//            InputStream inputStream = file.getInputStream();
//            //POIFSFileSystem poifsFileSystem = new POIFSFileSystem(inputStream);
//            XSSFWorkbook wb = new XSSFWorkbook(inputStream);
//            // 2.读取页脚sheet
//            XSSFSheet sheetAt = wb.getSheetAt(0);
//
//            // 3.循环读取某一行
//            int index = 0;
//            for (Row row : sheetAt) {
//                // 4.读取每一行的单元格
//                if (index == 0) {
//                    index++;
//                    continue;
//                }
//                //创建一个学生对象
//                SysStudent student = new SysStudent();
//
//                //将Excel表中单元格的值与学生对象的值对应
//
//                //因为学号是数字，Excel默认是数字类型，我的数据库是字符串类型，所以需要设置下类型
//                row.getCell(1).setCellType(CellType.STRING);
//                row.getCell(0).setCellType(CellType.STRING);
//                String cellValue = new BigDecimal(String.valueOf(row.getCell(4).getNumericCellValue())).stripTrailingZeros().toPlainString();
//                student.setMediceId(row.getCell(1).getStringCellValue());
//                student.setName(row.getCell(1).getStringCellValue());
////                String stringCellValue1 = row.getCell(2).getStringCellValue();
//                String val = "";
////                student.setPrice(row.getCell(4).getStringCellValue());
////                uploadFileServiceservice.insert(student);
//
//
//                row.getCell(0).setCellType(CellType.STRING);
//                String stringCellValue = row.getCell(1).getStringCellValue();
////                String stringPrice = row.getCell(4).getStringCellValue();
////                row.getCell(1).setCellType(CellType.STRING);
////                String stringCellValue2 = row.getCell(1).getStringCellValue();
////                row.getCell(2).setCellType(CellType.STRING);
////                String stringCellValue3 = row.getCell(2).getStringCellValue();
//
//
//                // 写多少个具体看大家上传的文件有多少列.....
//                // 测试是否读取到数据,及数据的正确性
////                System.out.println("编号" + stringCellValue);
//                System.out.println("价格" + cellValue);
////                System.out.println(stringCellValue2);
////                System.out.println(stringCellValue3);
//            }
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        return "上传成功";
    }
//    public static String getCellFormatValue(Cell cell) throws ParseException {
//        String cellValue = null;
//        if(cell!=null){
//            //判断cell类型
//            switch(cell.getCellType()){
//                case Cell.CELL_TYPE_NUMERIC:{
//                    //判断cell是否为日期格式
//                    if(DateUtil.isCellDateFormatted(cell)){
//                        //转换为日期格式YYYY-mm-dd
//                        Date date = cell.getDateCellValue();
//                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//                        cellValue = sdf.format(date);
//                    }else{
//                        String cellstr = cell.toString();
//                        cell.setCellType(Cell.CELL_TYPE_STRING);
//                        cellValue = cell.getStringCellValue();
//                        if(cellValue.indexOf(".") > -1) {
//                            cellValue = cellstr;
//                        }
//                    }
//                    break;
//                }
//                case Cell.CELL_TYPE_FORMULA:{
//                    cellValue = String.valueOf(cell.getNumericCellValue());
//                    break;
//                }
//                case Cell.CELL_TYPE_STRING:{
//                    cellValue = cell.getStringCellValue();
//                    break;
//                }
//                default:
//                    cellValue = "";
//            }
//        }else{
//            cellValue = "";
//        }
//        return cellValue;
//    }
}
