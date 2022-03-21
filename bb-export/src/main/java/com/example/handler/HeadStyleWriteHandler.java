package com.example.handler;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.util.StyleUtil;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.AbstractCellStyleStrategy;
import org.apache.poi.ss.usermodel.*;

import java.util.List;


/**
 * 自定义样式拦截器-复杂表头样式的使用
 *
 * @Author: nxf
 * @Date: 2021/1/17 14:31
 */
public class HeadStyleWriteHandler extends AbstractCellStyleStrategy {


//    /**
//     *   复杂表头自定义样式队列，先进先出，方便存储
//     * */
//    private ArrayBlockingQueue<ComplexHeadStyles> headStylesQueue;

    private List<Integer>  headColors;
    /**
     *   WorkBoot
     * */
    private Workbook workbook;

    /**
     *   构造方法，创建对象时传入需要定制的表头信息队列
     *
     */
    public HeadStyleWriteHandler(List<Integer>  headColors){
        this.headColors=headColors;
    }


    @Override
    protected void initCellStyle(Workbook workbook) {
        // 初始化信息时，保存Workbook对象，转换时需要使用
        this.workbook=workbook;
    }

    @Override
    protected void setHeadCellStyle(Cell cell, Head head, Integer relativeRowIndex) {

        WriteCellStyle writeCellStyle=new WriteCellStyle();

        if(headColors !=null && headColors.contains(cell.getColumnIndex())){

//            ComplexHeadStyles complexHeadStyle=headStylesQueue.peek();
//            // 取出队列中的自定义表头信息，与当前坐标比较，判断是否相符
//            if(cell.getColumnIndex() == complexHeadStyle.getY() && relativeRowIndex.equals(complexHeadStyle.getX())){
//                // 设置自定义的表头样式
//                writeCellStyle.setFillForegroundColor(complexHeadStyle.getIndexColor());
//                // 样式出队
//                headStylesQueue.poll();
//            }
            // 设置自定义的表头样式
            writeCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        }else{
            // 设置自定义的表头样式
            writeCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        }
        //设置表头居中对齐
        writeCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        //设置字体样式
        WriteFont writeFont = new WriteFont();
        //设置字体加粗
        writeFont.setBold(true);
        writeCellStyle.setWriteFont(writeFont);

        // WriteCellStyle转换为CellStyle
        CellStyle headCellStyle = StyleUtil.buildHeadCellStyle(workbook, writeCellStyle);

        // 设置表头样式
        cell.setCellStyle(headCellStyle);
    }

    @Override
    protected void setContentCellStyle(Cell cell, Head head, Integer relativeRowIndex) {
        //内容样式
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        //设置内容靠左对齐
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        //设置内容背景颜色
        contentWriteCellStyle.setFillBackgroundColor(IndexedColors.WHITE.getIndex());

        // WriteCellStyle转换为CellStyle
        CellStyle headCellStyle = StyleUtil.buildContentCellStyle(workbook, contentWriteCellStyle);
        // 设置内容样式
        cell.setCellStyle(headCellStyle);
    }

}