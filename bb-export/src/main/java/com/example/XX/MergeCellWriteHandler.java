package com.example.XX;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.handler.context.CellWriteHandlerContext;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.*;

public class MergeCellWriteHandler implements CellWriteHandler {

    /**
     * Called before create the cell
     *
     * @param context
     */
    @Override
    public void beforeCellCreate(CellWriteHandlerContext context) {
        final Row row = context.getRow();
        final WriteSheetHolder writeSheetHolder = context.getWriteSheetHolder();
        final String sheetName = writeSheetHolder.getSheetName();
        if (!"报关草单".equals(sheetName)) {
            return;
        }
        System.out.println("");
    }

    @Override
    public void afterCellDataConverted(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, WriteCellData<?> cellData, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        final String sheetName = writeSheetHolder.getSheetName();
        if (!"报关草单".equals(sheetName)) {
            return;
        }
        final String stringCellValue = cell.getStringCellValue();
        final Row row = cell.getRow();
        System.out.println("");
    }
    private Map<Integer, Map<Integer, String>> cache = new HashMap<>();
    private Map<String, CellRangeAddress> mergedRegions = new HashMap<>();

    private Set<Integer>   sets = Sets.newHashSet();
    private long  dynamicColumnNum = 0;
    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<WriteCellData<?>> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        final String sheetName = writeSheetHolder.getSheetName();
        if (!"报关草单".equals(sheetName)) {
            return;
        }
        final int rowIndex = cell.getRowIndex();
        final int columnIndex = cell.getColumnIndex();
        if(rowIndex == 17 && columnIndex == 0){
            final String[] split = cell.getStringCellValue().split("-");
            cell.setCellValue(split[0]);
            dynamicColumnNum = Long.parseLong(split[1]);
        }
        final Row row = cell.getRow();
        final Sheet sheet = cell.getSheet();
        // 获取当前单元格的值
        String cellValue = cell.getStringCellValue();
        if(StringUtils.isBlank(cellValue)){
            return;
        }

        if(rowIndex < 18 || rowIndex >= (17+dynamicColumnNum)){
            return;
        }
        if (rowIndex%3 == 1) {
            return;
        }
        if (rowIndex%3 == 2) {
            if (sets.contains(rowIndex)) {
                return;
            }
            writeSheetHolder.getSheet().addMergedRegion(new CellRangeAddress(cell.getRowIndex(), cell.getRowIndex(),1,2));
            writeSheetHolder.getSheet().addMergedRegion(new CellRangeAddress(cell.getRowIndex(),cell.getRowIndex(), 3,5));
            writeSheetHolder.getSheet().addMergedRegion(new CellRangeAddress(cell.getRowIndex(), cell.getRowIndex(),8,9));
            writeSheetHolder.getSheet().addMergedRegion(new CellRangeAddress(cell.getRowIndex(), cell.getRowIndex(),10,11));
            writeSheetHolder.getSheet().addMergedRegion(new CellRangeAddress(cell.getRowIndex(), cell.getRowIndex(),12 ,14));
            writeSheetHolder.getSheet().addMergedRegion(new CellRangeAddress(cell.getRowIndex(), cell.getRowIndex(),15,17));
            sets.add(rowIndex);
        }
        if (rowIndex%3 == 0) {
            if (sets.contains(rowIndex)) {
                return;
            }
            writeSheetHolder.getSheet().addMergedRegion(new CellRangeAddress(cell.getRowIndex(), cell.getRowIndex(),1,2));
            writeSheetHolder.getSheet().addMergedRegion(new CellRangeAddress(cell.getRowIndex(),cell.getRowIndex()+1, 3,5));
            writeSheetHolder.getSheet().addMergedRegion(new CellRangeAddress(cell.getRowIndex(), cell.getRowIndex(),8,9));
            writeSheetHolder.getSheet().addMergedRegion(new CellRangeAddress(cell.getRowIndex(), cell.getRowIndex(),10,11));
            writeSheetHolder.getSheet().addMergedRegion(new CellRangeAddress(cell.getRowIndex(), cell.getRowIndex(),12 ,14));
            writeSheetHolder.getSheet().addMergedRegion(new CellRangeAddress(cell.getRowIndex(), cell.getRowIndex(),15,17));
            writeSheetHolder.getSheet().addMergedRegion(new CellRangeAddress(cell.getRowIndex()+1, cell.getRowIndex()+1,1,2));
            writeSheetHolder.getSheet().addMergedRegion(new CellRangeAddress(cell.getRowIndex()+1, cell.getRowIndex()+1,8,9));
            writeSheetHolder.getSheet().addMergedRegion(new CellRangeAddress(cell.getRowIndex()+1, cell.getRowIndex()+1,10,11));
            writeSheetHolder.getSheet().addMergedRegion(new CellRangeAddress(cell.getRowIndex()+1, cell.getRowIndex()+1,12 ,14));
            writeSheetHolder.getSheet().addMergedRegion(new CellRangeAddress(cell.getRowIndex()+1, cell.getRowIndex()+1,15,17));
            sets.add(rowIndex);
        }

    }

    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Head head, Integer columnIndex, Integer relativeRowIndex, Boolean isHead) {
        final String sheetName = writeSheetHolder.getSheetName();
        if (!"报关草单".equals(sheetName)) {
            return;
        }
        final Cell cell = row.getCell(columnIndex);
        if (Objects.nonNull(cell)) {
            final String stringCellValue = cell.getStringCellValue();
            System.out.println("");
        }
        // 在创建单元格之前，判断是否需要合并单元格
//        if (isNeedMerge(row, head, columnIndex, isHead)) {
//            writeSheetHolder.getSheet().addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum() + 1, columnIndex, columnIndex));
//        }
    }

//    private boolean isNeedMerge(Row row, Head head, Integer columnIndex, Boolean isHead) {
//        // 根据业务逻辑判断是否需要合并单元格
//        // 这里简单示例，假设第一列相同的需要合并单元格
//        if (columnIndex == 0 && !isHead) {
//            Cell preCell = row.getCell(columnIndex - 1);
//            Cell curCell = row.getCell(columnIndex);
//            return preCell != null && curCell != null && preCell.getStringCellValue().equals(curCell.getStringCellValue());
//        }
//        return false;
//    }

    @Override
    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        final String sheetName = writeSheetHolder.getSheetName();
        // do nothing
        if (!"报关草单".equals(sheetName)) {
            return;
        }
        System.out.println("");
    }
}
