//package com.example;
//
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/export")
//public class ExportController {
//
////    @GetMapping
////    public void export(HttpServletResponse response) throws IOException {
////        HeadStyleWriteHandler headStyleWriteHandler = new HeadStyleWriteHandler(null);
////        List<UserExcel> userExcels = new ArrayList<UserExcel>();
////        userExcels.add(UserExcel.builder().userCode("userCode1").userName("名称1").phone("13001010101").address("伊斯坦布尔").status("启用").build());
////        userExcels.add(UserExcel.builder().userCode("userCode2").userName("名称2").phone("13001010101").address("上海").status("启用").build());
////        ExcelUtil.  writeExcelCustom(response,userExcels,
////                "导出case-"+System.currentTimeMillis(),
////                "用户列表",UserExcel.class,headStyleWriteHandler);
////    }
//}
