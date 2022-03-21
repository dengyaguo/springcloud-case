package com.example.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.AbstractCellStyleStrategy;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.example.handler.CustomCellWriteHandler;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


public class ExcelUtil {

    public static void writeExcel(HttpServletResponse response, List<? extends Object> data, String fileName, String sheetName, Class clazz) throws IOException {
        //表头样式
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        //设置表头居中对齐
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        //设置表头背景颜色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        //设置字体样式
        WriteFont writeFont = new WriteFont();
        //设置字体加粗
        writeFont.setBold(true);
        headWriteCellStyle.setWriteFont(writeFont);
        //内容样式
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        //设置内容靠左对齐
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        //设置内容背景颜色
        contentWriteCellStyle.setFillBackgroundColor(IndexedColors.WHITE.getIndex());

        //设置导出样式
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

        //设置自适应列宽
        CustomCellWriteHandler customCellWriteHandler = new CustomCellWriteHandler();

        EasyExcel.write(getOutputStream(fileName, response), clazz).excelType(ExcelTypeEnum.XLSX).sheet(sheetName).registerWriteHandler(horizontalCellStyleStrategy).registerWriteHandler(customCellWriteHandler).doWrite(data);

    }


    public static void writeExcelCustom(HttpServletResponse response, List<? extends Object> data, String fileName, String sheetName, Class clazz, AbstractCellStyleStrategy CellStyleStrategy) throws IOException {
        //设置自适应列宽
        CustomCellWriteHandler customCellWriteHandler = new CustomCellWriteHandler();

        EasyExcel.write(getOutputStream(fileName, response), clazz)
                .excelType(ExcelTypeEnum.XLSX).sheet(sheetName)
                .registerWriteHandler(CellStyleStrategy)
                .registerWriteHandler(customCellWriteHandler).doWrite(data);

    }

    private static OutputStream getOutputStream(String fileName, HttpServletResponse response) throws IOException {
        response.setContentType("application/ms-excel;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename="+new String((fileName + ".xlsx").getBytes("ISO-8859-1"), "UTF-8"));
        return response.getOutputStream();
    }

}
