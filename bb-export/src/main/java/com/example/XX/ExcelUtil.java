package com.example.XX;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ExcelUtil {
   static String XLS_CONTENT_TYPE = "application/vnd.ms-excel";
    static  String XLSX_CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String CSV_CONTENT_TYPE = "text/csv";

    @Getter
    @AllArgsConstructor
    public enum DeclarationFormExport {
        INVOICE_INFO("发票"),
        PACKING_INFO("装箱单"),
        CONTRACT_INFO("合同"),
        DRAFT_INFO("报关草单"),
        ORDER_INFO("订单信息"),
        ;

        private final String name;
    }



    public static ExcelWriter initExportFillWriter(String fileName, ExcelTypeEnum excelType,
                                                  ExcelTemplate template, HttpServletResponse response) throws IOException {
        setResponse(fileName, excelType, response);
        final ClassPathResource classPathResource = new ClassPathResource(template.getPath());
        return EasyExcelFactory.write(response.getOutputStream())
                .excelType(excelType)
                .withTemplate(classPathResource.getInputStream()).registerWriteHandler(new MergeCellWriteHandler()).build();
    }

    /*
     * 设置通用的响应头信息
     * */
    public static void setResponse(String fileName, ExcelTypeEnum excelType, HttpServletResponse response) {
        // 对文件名进行UTF-8编码、拼接文件后缀名
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20") + excelType.getValue();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        switch (excelType) {
            case XLS:
                response.setContentType(XLS_CONTENT_TYPE);
                break;
            case XLSX:
                response.setContentType(XLSX_CONTENT_TYPE);
                break;
//            case CSV:
//                response.setContentType(CSV_CONTENT_TYPE);
//                break;
        }
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment;filename*=utf-8''" + fileName);
    }


    @Getter
    @AllArgsConstructor
    public static enum ExcelTemplate {
        template(1, "template/template.xlsx", "模板"),
        declarationfrom_template(1, "template/declarationfrom_template.xlsx", "模板"),
//        export(2, "template/export.xlsx", "模板"),
        bgzl(3, "template/报关资料导出模板 (1).xlsx", "模板"),
        ;

        private final Integer code;
        private final String path;
        private final String desc;
    }
}
