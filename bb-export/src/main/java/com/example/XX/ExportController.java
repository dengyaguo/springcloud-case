package com.example.XX;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("exporttest")
public class ExportController {


    /**
     * https://blog.csdn.net/ambition_test/article/details/140897841
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping(value = "/test")
    public void dox(@RequestBody Object request, HttpServletResponse response) throws Exception {

        try {

            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("xxname", "xxxx");
            dataMap.put("param2", "2");
            dataMap.put("param3", "3");
            dataMap.put("param4", "哈哈哈");
            dataMap.put("total", "总共11");

            final ArrayList<ExcelData> objects = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                final ExcelData excelData = new ExcelData();
                excelData.setName("setName"+i);
                excelData.setName1("setName1"+i);
                excelData.setName2("setName2"+i);
                excelData.setName3("setName3"+i);
                excelData.setName4("setName4"+i);
                excelData.setName5("setName5"+i);
                excelData.setName6("setName6"+i);
                excelData.setName7("setName7"+i);
                objects.add(excelData);
            }

            final ArrayList<ExcelData2> object2 = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                final ExcelData2 excelData = new ExcelData2();
                excelData.setSheet1("setSheet1"+i);
                excelData.setSheet2("setSheet2"+i);
                excelData.setSheet3("setSheet3"+i);
                excelData.setSheet4("setSheet4"+i);
                excelData.setSheet5("setSheet5"+i);
                excelData.setSheet6("setSheet6"+i);
                excelData.setSheet7("setSheet7"+i);
                object2.add(excelData);
            }

            final ArrayList<ExcelData3> object3 = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                final ExcelData3 excelData = new ExcelData3();
                if(i == 0){
                    excelData.set序号(i+"-"+10*3);
                }else{
                    excelData.set序号("i");
                }
                excelData.set商品编号("商品编号"+i);
                excelData.set商品名称("商品名称"+i);
                excelData.set数量("数量"+i);
                excelData.set单位("单位"+i);
                excelData.set原产国("原产国"+i);
                excelData.set最终目的国("最终目的国"+i);
                excelData.set单价("单价"+i);
                excelData.set规格型号("规格型号"+i);
                excelData.set法一("法一"+i);
                excelData.set法一单位("法一单位"+i);
                excelData.set法二("法二"+i);
                excelData.set法二单位("法二单位"+i);
                excelData.set总价("总价"+i);
                excelData.set币种("币种"+i);
                excelData.set境内货源地("境内货源地"+i);
                excelData.set征免("征免"+i);
                object3.add(excelData);
            }

            final ArrayList<Map<String,String>> objects3 = Lists.newArrayList();
            for (ExcelData3 excelData3 : object3) {
                final HashMap<String, String> hashMap1 = Maps.newHashMap();
                final HashMap<String, String> hashMap2 = Maps.newHashMap();
                final HashMap<String, String> hashMap3 = Maps.newHashMap();
                hashMap1.put("test0",excelData3.get序号());
                hashMap1.put("test1",excelData3.get商品编号());
                hashMap1.put("test2",excelData3.get商品名称());
                hashMap1.put("test3",excelData3.get数量());
                hashMap1.put("test4",excelData3.get单位());
                hashMap1.put("test5",excelData3.get单价());
                hashMap1.put("test6",excelData3.get原产国());
                hashMap1.put("test7",excelData3.get最终目的国());
                hashMap1.put("test8",excelData3.get境内货源地());
                hashMap1.put("test9",excelData3.get征免());

                hashMap2.put("test0", StringUtils.EMPTY);
                hashMap2.put("test1", StringUtils.EMPTY);
                hashMap2.put("test6", StringUtils.EMPTY);
                hashMap2.put("test7", StringUtils.EMPTY);
                hashMap2.put("test8", StringUtils.EMPTY);
                hashMap2.put("test9", StringUtils.EMPTY);
                hashMap2.put("test2",excelData3.get规格型号());
                hashMap2.put("test3",excelData3.get法一());
                hashMap2.put("test4",excelData3.get法一单位());
                hashMap2.put("test5",excelData3.get总价());


                hashMap3.put("test0", StringUtils.EMPTY);
                hashMap3.put("test1", StringUtils.EMPTY);
                hashMap3.put("test6", StringUtils.EMPTY);
                hashMap3.put("test7", StringUtils.EMPTY);
                hashMap3.put("test8", StringUtils.EMPTY);
                hashMap3.put("test9", StringUtils.EMPTY);
                hashMap3.put("test2",excelData3.get规格型号());
                hashMap3.put("test3",excelData3.get法二());
                hashMap3.put("test4",excelData3.get法二单位());
                hashMap3.put("test5",excelData3.get币种());


                objects3.add(hashMap1);
                objects3.add(hashMap2);
                objects3.add(hashMap3);
            }

            // 获取一个已设置基础参数的ExcelWriter对象
            ExcelWriter excelWriter = ExcelUtil.initExportFillWriter(UUID.randomUUID().toString(),
                    ExcelTypeEnum.XLSX, ExcelUtil.ExcelTemplate.declarationfrom_template, response);
            WriteSheet writeSheet = EasyExcelFactory.writerSheet(0, ExcelUtil.DeclarationFormExport.INVOICE_INFO.getName()).build();


            // 基于模板填充数据
            FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
            excelWriter.fill(objects, fillConfig, writeSheet);
            excelWriter.fill(dataMap, writeSheet);




            WriteSheet writeSheet2 = EasyExcelFactory.writerSheet(1,ExcelUtil.DeclarationFormExport.PACKING_INFO.getName()).build();
            excelWriter.fill(object2, fillConfig,writeSheet2);
            excelWriter.fill(dataMap, writeSheet2);

            final ExcelWriterSheetBuilder sheet3 = EasyExcelFactory.writerSheet(2, ExcelUtil.DeclarationFormExport.DRAFT_INFO.getName());
            WriteSheet writeSheet3 = sheet3.build();


            excelWriter.fill(objects3, fillConfig,writeSheet3);
            excelWriter.fill(dataMap, writeSheet3);


//            final FillWrapper fillWrapper = new FillWrapper("",StringUtils.EMPTY);
//            excelWriter.fill(dataMap, writeSheet2);

            excelWriter.finish();
        }
        catch (IOException e) {
            throw new Exception("熊猫统计报表导出失败！");
        }
    }




}
