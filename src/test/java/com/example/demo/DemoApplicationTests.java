package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Department;
import com.example.demo.entity.Position;
import com.example.demo.mapper.DepartmentMapper;
import com.example.demo.mapper.PositionMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.example.demo.vo.PositionVO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Resource
    DepartmentMapper departmentMapper;

    @Resource
    PositionMapper positionMapper;
    @Resource
    private UserService userService;

    @Autowired
    private UserMapper userMapper;
    @Test
    void contextLoads() {
        System.out.println(1&1);
    }

    @Test
    public void excel() throws IOException {
        //创建工作簿
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();

        //创建工作表
        XSSFSheet sheet = xssfWorkbook.createSheet("员工绩效考核表");

        //列宽
        sheet.setColumnWidth(0, (short) (11.6 * 256 + 184));
        sheet.setColumnWidth(1, (short) (16.8 * 256 + 184));
        sheet.setColumnWidth(2, (short) (15.3 * 256 + 184));
        sheet.setColumnWidth(3, (short) (35.5 * 256 + 184));
        sheet.setColumnWidth(4, (short) (24.1 * 256 + 184));
        sheet.setColumnWidth(5, (short) (37.7 * 256 + 184));
        sheet.setColumnWidth(6, (short) (24.1 * 256 + 184));
        sheet.setColumnWidth(7, (short) (18.9 * 256 + 184));
        sheet.setColumnWidth(8, (short) (15.5 * 256 + 184));

        //第一行样式
        XSSFCellStyle cellStyle = xssfWorkbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setWrapText(true);
        XSSFFont font = xssfWorkbook.createFont();
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        cellStyle.setFont(font);

        //副标题粗体
        XSSFCellStyle cellStyle1 = xssfWorkbook.createCellStyle();
        cellStyle1.setAlignment(HorizontalAlignment.CENTER);
        cellStyle1.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle1.setWrapText(true);
        XSSFFont font1 = xssfWorkbook.createFont();
        font1.setFontName("微软雅黑");
        font1.setFontHeightInPoints((short) 12);
        font1.setBold(true);
        cellStyle1.setFont(font1);

        //
        XSSFCellStyle cellStyle2 = (XSSFCellStyle) cellStyle1.clone();
        cellStyle2.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        cellStyle2.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        //1
        XSSFRow row = sheet.createRow(0);
        row.setHeight((short) (54 * 20));
        XSSFCell rowcell = row.createCell(0);
        rowcell.setCellStyle(cellStyle);
        rowcell.setCellValue("知嘛家员工绩效考核表");

        //2
        XSSFRow row1 = sheet.createRow(1);
        row1.setHeight((short) (39 * 20));

        //A2
        XSSFCell row1cell = row1.createCell(0);
        row1cell.setCellStyle(cellStyle1);
        row1cell.setCellValue("姓名");

        //D2
        XSSFCell row1cell3 = row1.createCell(3);
        row1cell3.setCellStyle(cellStyle1);
        row1cell3.setCellValue("岗位");

        //G2
        XSSFCell row1cell6 = row1.createCell(6);
        row1cell6.setCellStyle(cellStyle1);
        row1cell6.setCellValue("部门");

        //3
        XSSFRow row2 = sheet.createRow(2);
        row2.setHeight((short) (39 * 20));

        //A3
        XSSFCell row2cell = row2.createCell(0);
        row2cell.setCellStyle(cellStyle1);
        row2cell.setCellValue("入职时间");

        //D3
        XSSFCell row2cell3 = row2.createCell(3);
        row2cell3.setCellStyle(cellStyle1);
        row2cell3.setCellValue("直接主管");

        //G3
        XSSFCell row2cell6 = row2.createCell(6);
        row2cell6.setCellStyle(cellStyle1);
        row2cell6.setCellValue("部门负责人");

        //4
        XSSFRow row3 = sheet.createRow(3);
        row3.setHeight((short) (41 * 20));

        //A4
        XSSFCell row3cell = row3.createCell(0);
        row3cell.setCellStyle(cellStyle2);
        row3cell.setCellValue("考核指标类型");

        //D4
        XSSFCell row3cell3 = row3.createCell(3);
        row3cell3.setCellStyle(cellStyle2);
        row3cell3.setCellValue("考核要点");

        //G4
        XSSFCell row3cell6 = row3.createCell(6);
        row3cell6.setCellStyle(cellStyle2);
        row3cell6.setCellValue("绩效指标评核");




        CellRangeAddress region = new CellRangeAddress(0, 0, 0, 8);
        sheet.addMergedRegion(region);

        CellRangeAddress region1 = new CellRangeAddress(3,3,0,2);
        sheet.addMergedRegion(region1);
        CellRangeAddress region2 = new CellRangeAddress(3,3,3,5);
        sheet.addMergedRegion(region2);
        CellRangeAddress region3 = new CellRangeAddress(3,3,6,8);
        sheet.addMergedRegion(region3);




        FileOutputStream out = new FileOutputStream("C:\\Users\\aaa\\Desktop\\" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString() + ".xlsx");
        xssfWorkbook.write(out);
        out.close();


    }

    @Test
    public void excel1() throws IOException {
        File f = new File(this.getClass().getResource("/").getPath());
        String filePath = f+File.separator+"..\\"+File.separator+"classes"+File.separator+"static"+File.separator+"四行模板.xlsx";
        System.out.println(filePath);
        File file = new File(filePath);
        FileInputStream in =new FileInputStream(file);
        //读取excel模板
        XSSFWorkbook wb = new XSSFWorkbook(in);
        //读取了模板内所有sheet内容
        XSSFSheet sheet = wb.getSheetAt(0);

        sheet.getRow(1).getCell(1).setCellValue("aaa");

        FileOutputStream out = new FileOutputStream("C:\\Users\\aaa\\Desktop\\" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString() + ".xlsx");
        wb.write(out);
        out.close();


    }

    @Test
    public void a(){
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("department_status","exist");
        List<Department> list = departmentMapper.selectList(queryWrapper);
        List<PositionVO> positionVOList = new ArrayList<>();
        for (Department department : list) {
            PositionVO positionVO = new PositionVO();
            positionVO.setDepartmentId(department.getDepartmentId());
            positionVO.setDepartmentName(department.getDepartmentName());
            QueryWrapper<Position> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("position_department_id", department.getDepartmentId());
            queryWrapper1.eq("position_status", "exist");
            List<Position> list1 = positionMapper.selectList(queryWrapper1);
            positionVO.setPositionList(list1);
            positionVOList.add(positionVO);
        }
        System.out.println(positionVOList);
    }


}
