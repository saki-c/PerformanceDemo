package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.mapper.*;
import com.example.demo.service.PerformanceService;
import com.example.demo.util.JWTUtil;
import com.example.demo.util.Result;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 咲蛍
 * @date 2021/05/17
 */
@Service
public class PerformanceServiceImpl implements PerformanceService {
    @Resource
    PerformanceMapper performanceMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    PerformanceItemMapper performanceItemMapper;

    @Resource
    DepartmentMapper departmentMapper;

    @Resource
    PositionMapper positionMapper;

    private static final String SUMMARIZED = "summarized";

    private static final String MESS = "无权限进行修改";

    private static final String SUBMITED = "submited";

    private static final String DIRECTORGRADED = "directorgraded";

    private static final String MANAGERGRADED = "managergraded";

    private static final String SAVED = "saved";

    private static final String PERFORMANCE_ITEM_PERFORMANCE_ID = "performance_item_performance_id";

    @Override
    public Result addPerformance(String token, PerformanceDTO performanceDTO) throws ParseException {
        List<DomainsDTO> list = performanceDTO.getDomains();
        if (list.size() < 3) {
            return new Result<T>(400, "你这绩效也太短了吧");
        }
        if (list.size() > 4) {
            return new Result<T>(400, "你这绩效细分太多了吧");
        }
        int weight = 0;
        for (DomainsDTO value : list) {
            if (Integer.parseInt(value.getWeight()) <= 0) {
                return new Result<T>(400, "权重不得小于0");
            }
            weight += Integer.parseInt(value.getWeight());
        }
        if (weight != 100) {
            return new Result<T>(400, "权重总和不为100");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(performanceDTO.getTerm());
        QueryWrapper<Performance> wrapper = new QueryWrapper<>();
        wrapper.eq("performance_term", date);
        wrapper.eq("performance_user_id", JWTUtil.verifyToken(token).get("id").asString());
        if (performanceMapper.selectOne(wrapper) != null) {
            return new Result<T>(400, "已存在此月绩效");
        }
        QueryWrapper<User> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("user_id", JWTUtil.verifyToken(token).get("id").asString());
        User user = userMapper.selectOne(wrapper1);
        if (date.before(user.getUserJoinDate())) {
            return new Result<T>(400, "当前周期您未入职");
        }
        Performance performance = new Performance();
        performance.setPerformanceTerm(date);
        performance.setPerformanceUserId(1);
        performance.setPerformanceStatus(SAVED);
        performanceMapper.insert(performance);
        int performanceId = performance.getPerformanceId();
        PerformanceItem performanceItem = new PerformanceItem();
        for (DomainsDTO domainsDTO : list) {
            performanceItem.setPerformanceItemPerformanceId(performanceId);
            performanceItem.setPerformanceItemDemand(domainsDTO.getDemand());
            performanceItem.setPerformanceItemWeight(Integer.parseInt(domainsDTO.getWeight()));
            performanceItem.setPerformanceItemStandard(domainsDTO.getStandard());
            performanceItemMapper.insert(performanceItem);
        }
        return new Result<>(200, "", performanceDTO);
    }

    @Override
    public Result selectPerformancePage(String token, QueryDTO queryDTO) {
        String userId = JWTUtil.verifyToken(token).get("id").asString();
        User user = userMapper.selectById(userId);
        Page<Performance> page = new Page<>(queryDTO.getPageNo(), queryDTO.getPageSize());
        if ("user".equals(user.getUserStatus())) {
            return new Result<>(200, "", performanceMapper.selectOnePerformancePage(page, queryDTO.getKeyword(), userId));
        }

        return new Result<>(200, "", performanceMapper.selectPerformancePage(page, queryDTO.getKeyword(), userId));
    }

    @Override
    public void downloadPerformance(HttpServletResponse response, DownloadDTO downloadDTO) throws IOException {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", downloadDTO.getUserName());
        User user = userMapper.selectOne(wrapper);

        Position position = positionMapper.selectById(user.getUserPositionId());
        Department department = departmentMapper.selectById(position.getPositionDepartmentId());
        User director = userMapper.selectById(user.getUserDirectorId());
        User departmentManager = userMapper.selectById(department.getDepartmentManagerId());
        Performance performance = performanceMapper.selectById(downloadDTO.getPerformanceId());

        QueryWrapper<PerformanceItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(PERFORMANCE_ITEM_PERFORMANCE_ID, downloadDTO.getPerformanceId());
        List<PerformanceItem> list = performanceItemMapper.selectList(queryWrapper);

        if (list.size() == 3) {
            File f = new File(this.getClass().getResource("/").getPath());
            String filePath = f + File.separator + "static" + File.separator + "三行模板.xlsx";
            File file = new File(filePath);
            try (FileInputStream in = new FileInputStream(file);
                 XSSFWorkbook wb = new XSSFWorkbook(in))
            //读取excel模板
            //读取了模板内所有sheet内容
            {
                XSSFSheet sheet = wb.getSheetAt(0);

                sheet.getRow(1).getCell(1).setCellValue(user.getUserName());
                sheet.getRow(1).getCell(4).setCellValue(position.getPositionName());
                sheet.getRow(1).getCell(7).setCellValue(department.getDepartmentName());
                sheet.getRow(2).getCell(1).setCellValue(user.getUserJoinDate());
                sheet.getRow(2).getCell(4).setCellValue(director.getUserName());
                sheet.getRow(2).getCell(7).setCellValue(departmentManager.getUserName());

                int dg = 0;
                int dmg = 0;
                for (int i = 0; i < list.size(); i++) {
                    sheet.getRow(i + 5).getCell(1).setCellValue(downloadDTO.getPerformanceTerm().substring(0, 7));
                    String a = list.get(i).getPerformanceItemWeight() + "%";
                    sheet.getRow(i + 5).getCell(2).setCellValue(a);
                    sheet.getRow(i + 5).getCell(3).setCellValue(list.get(i).getPerformanceItemDemand());
                    sheet.getRow(i + 5).getCell(4).setCellValue(list.get(i).getPerformanceItemStandard());
                    sheet.getRow(i + 5).getCell(6).setCellValue(list.get(i).getPerformanceItemSituation());
                    sheet.getRow(i + 5).getCell(7).setCellValue(list.get(i).getPerformanceItemDirectorGrade());
                    dg += list.get(i).getPerformanceItemDirectorGrade();
                    sheet.getRow(i + 5).getCell(8).setCellValue(list.get(i).getPerformanceItemDepartmentManagerGrade());
                    dmg += list.get(i).getPerformanceItemDepartmentManagerGrade();
                }
                sheet.getRow(8).getCell(7).setCellValue(dg);
                sheet.getRow(8).getCell(8).setCellValue(dmg);
                sheet.getRow(9).getCell(0).setCellValue("员工工作总结：" + performance.getPerformanceConclusion());
                sheet.getRow(10).getCell(0).setCellValue("部门负责人对员工评价：" + performance.getPerformanceEvaluation());

                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                String fn = sdf.format(new Date()) + ".xlsx";
                response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
                response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fn, "UTF-8"));
                OutputStream os = null;
                try {
                    os = response.getOutputStream();
                    wb.write(os);
                    os.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (os != null) {
                            os.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        os.close();
                    }
                }
            }
        }

        if (list.size() == 4) {
            File f = new File(this.getClass().getResource("/").getPath());
            String filePath = f + File.separator + "static" + File.separator + "四行模板.xlsx";
            File file = new File(filePath);

            try (FileInputStream in = new FileInputStream(file);
                 XSSFWorkbook wb = new XSSFWorkbook(in))
            //读取excel模板
            //读取了模板内所有sheet内容
            {
                XSSFSheet sheet = wb.getSheetAt(0);

                sheet.getRow(1).getCell(1).setCellValue(user.getUserName());
                sheet.getRow(1).getCell(4).setCellValue(position.getPositionName());
                sheet.getRow(1).getCell(7).setCellValue(department.getDepartmentName());
                sheet.getRow(2).getCell(1).setCellValue(user.getUserJoinDate());
                sheet.getRow(2).getCell(4).setCellValue(director.getUserName());
                sheet.getRow(2).getCell(7).setCellValue(departmentManager.getUserName());

                int dg = 0;
                int dmg = 0;
                for (int i = 0; i < list.size(); i++) {
                    sheet.getRow(i + 5).getCell(1).setCellValue(downloadDTO.getPerformanceTerm().substring(0, 7));
                    String a = list.get(i).getPerformanceItemWeight() + "%";
                    sheet.getRow(i + 5).getCell(2).setCellValue(a);
                    sheet.getRow(i + 5).getCell(3).setCellValue(list.get(i).getPerformanceItemDemand());
                    sheet.getRow(i + 5).getCell(4).setCellValue(list.get(i).getPerformanceItemStandard());
                    sheet.getRow(i + 5).getCell(6).setCellValue(list.get(i).getPerformanceItemSituation());
                    sheet.getRow(i + 5).getCell(7).setCellValue(list.get(i).getPerformanceItemDirectorGrade());
                    dg += list.get(i).getPerformanceItemDirectorGrade();
                    sheet.getRow(i + 5).getCell(8).setCellValue(list.get(i).getPerformanceItemDepartmentManagerGrade());
                    dmg += list.get(i).getPerformanceItemDepartmentManagerGrade();
                }

                sheet.getRow(9).getCell(7).setCellValue(dg);
                sheet.getRow(8).getCell(8).setCellValue(dmg);
                sheet.getRow(10).getCell(0).setCellValue("员工工作总结：" + performance.getPerformanceConclusion());
                sheet.getRow(11).getCell(0).setCellValue("部门负责人对员工评价：" + performance.getPerformanceEvaluation());

                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                String fn = sdf.format(new Date()) + ".xlsx";
                response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
                response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fn, "UTF-8"));
                OutputStream os = null;
                try {
                    os = response.getOutputStream();
                    wb.write(os);
                    os.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (os != null) {
                            os.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        os.close();
                    }
                }
            }


        }
    }


    @Override
    public Result selectPerformanceItemPage(Integer performanceId) {
        QueryWrapper<PerformanceItem> wrapper = new QueryWrapper<>();
        wrapper.eq(PERFORMANCE_ITEM_PERFORMANCE_ID, performanceId);
        List<PerformanceItem> list = performanceItemMapper.selectList(wrapper);
        return new Result<>(200, "", list);
    }

    @Override
    public Result updatePerformanceItem(String token, ItemDTO itemDTO) {

        User user = userMapper.selectById(JWTUtil.verifyToken(token).get("id").asString());

        PerformanceItem performanceItem = performanceItemMapper.selectById(itemDTO.getPerformanceId());

        Performance performance = performanceMapper.selectById(performanceItem.getPerformanceItemPerformanceId());

        User user1 = userMapper.selectById(performance.getPerformanceUserId());

        if (SUMMARIZED.equals(performance.getPerformanceStatus())) {
            return new Result<T>(400, "此绩效已无法修改");
        }
        if (user.getUserId().equals(performance.getPerformanceUserId())) {
            if (SAVED.equals(performance.getPerformanceStatus())) {
                performanceItem.setPerformanceItemDemand(itemDTO.getPerformanceItemDemand());
                performanceItem.setPerformanceItemStandard(itemDTO.getPerformanceItemStandard());
                performanceItem.setPerformanceItemSituation(itemDTO.getPerformanceItemSituation());
                performanceItemMapper.updateById(performanceItem);
                return new Result<T>(200, "修改成功");
            }
            return new Result<T>(400, MESS);
        }
        if (user.getUserId().equals(user1.getUserDirectorId())) {
            if (SUBMITED.equals(performance.getPerformanceStatus())) {
                performanceItem.setPerformanceItemDirectorGrade(Integer.parseInt(itemDTO.getPerformanceItemDirectorGrade()));
                performanceItemMapper.updateById(performanceItem);
                return new Result<T>(200, "修改成功");
            }
            return new Result<T>(400, MESS);
        }

        Position position = positionMapper.selectById(user1.getUserPositionId());
        Department department = departmentMapper.selectById(position.getPositionDepartmentId());

        if (user.getUserId().equals(department.getDepartmentManagerId())) {
            if (DIRECTORGRADED.equals(performance.getPerformanceStatus())) {
                performanceItem.setPerformanceItemDepartmentManagerGrade(Integer.parseInt(itemDTO.getPerformanceItemDepartmentManagerGrade()));
                performanceItemMapper.updateById(performanceItem);
                return new Result<T>(200, "修改成功");
            }
            return new Result<T>(400, MESS);
        }
        return new Result<T>(400, "还没轮到你呢");

    }

    @Override
    public Result submitPerformance(String token, Integer performanceId) {
        User user = userMapper.selectById(JWTUtil.verifyToken(token).get("id").asString());
        Performance performance = performanceMapper.selectById(performanceId);
        if (!SAVED.equals(performance.getPerformanceStatus())) {
            return new Result<T>(400, "请勿重复提交");
        }
        if (!user.getUserId().equals(performance.getPerformanceUserId())) {
            return new Result<T>(400, "非本人提交");
        }
        QueryWrapper<PerformanceItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(PERFORMANCE_ITEM_PERFORMANCE_ID, performance.getPerformanceId());
        List<PerformanceItem> list = performanceItemMapper.selectList(queryWrapper);
        for (PerformanceItem performanceItem : list) {
            if (null == performanceItem.getPerformanceItemDemand() || "".equals(performanceItem.getPerformanceItemDemand())) {
                return new Result<T>(400, "缺少考核指标要求");
            }
            if (null == performanceItem.getPerformanceItemStandard() || "".equals(performanceItem.getPerformanceItemStandard())) {
                return new Result<T>(400, "缺少考核标准");
            }
            if (null == performanceItem.getPerformanceItemSituation() || "".equals(performanceItem.getPerformanceItemSituation())) {
                return new Result<T>(400, "缺少完成情况");
            }

        }
        if (SAVED.equals(performance.getPerformanceStatus()) && user.getUserId().equals(performance.getPerformanceUserId())) {
            performance.setPerformanceStatus(SUBMITED);
            return new Result<>(200, "提交成功", performanceMapper.updateById(performance));
        }
        return new Result<T>(400, "提交异常");
    }

    @Override
    public Result gradePerformance(String token, Integer performanceId) {
        User user = userMapper.selectById(JWTUtil.verifyToken(token).get("id").asString());
        Performance performance = performanceMapper.selectById(performanceId);
        User user1 = userMapper.selectById(performance.getPerformanceUserId());

        QueryWrapper<PerformanceItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(PERFORMANCE_ITEM_PERFORMANCE_ID, performance.getPerformanceId());
        List<PerformanceItem> list = performanceItemMapper.selectList(queryWrapper);
        if (!"admin".equals(user.getUserStatus())) {
            return new Result<T>(400, "权限不足");
        }
        if (MANAGERGRADED.equals(performance.getPerformanceStatus())) {
            return new Result<T>(400, "已无法修改评分");
        }
        if (SAVED.equals(performance.getPerformanceStatus())) {
            return new Result<T>(400, "此绩效还未提交");
        }
        if (user.getUserId().equals(user1.getUserId())) {
            return new Result<T>(400, "权限不足");
        }
        if (SUBMITED.equals(performance.getPerformanceStatus()) && user.getUserId().equals(user1.getUserDirectorId())) {
            for (PerformanceItem performanceItem : list) {
                if (null == performanceItem.getPerformanceItemDirectorGrade()) {
                    return new Result<T>(400, "缺少直接主管评分");
                }

            }
            performance.setPerformanceStatus(DIRECTORGRADED);
            return new Result<>(200, "", performanceMapper.updateById(performance));
        }


        Position position = positionMapper.selectById(user1.getUserPositionId());
        Department department = departmentMapper.selectById(position.getPositionDepartmentId());

        if (DIRECTORGRADED.equals(performance.getPerformanceStatus())) {
            if (user.getUserId().equals(department.getDepartmentManagerId())) {
                for (PerformanceItem performanceItem : list) {
                    if (null == performanceItem.getPerformanceItemDepartmentManagerGrade()) {
                        return new Result<T>(400, "缺少部门主管评分");
                    }
                }
                performance.setPerformanceStatus(MANAGERGRADED);
                return new Result<>(200, "", performanceMapper.updateById(performance));
            }
            return new Result<T>(400, "请等待部门负责人评分");
        }

        return new Result<T>(400, "直属主管还未评分");
    }

    @Override
    public Result addSummary(String token, SummaryDTO summaryDTO) {
        Performance performance = performanceMapper.selectById(summaryDTO.getPerformanceId());
        User user = userMapper.selectById(JWTUtil.verifyToken(token).get("id").asString());
        if (!MANAGERGRADED.equals(performance.getPerformanceStatus())) {
            return new Result<T>(400, "还不可以总结");
        }
        if (user.getUserId().equals(performance.getPerformanceUserId())) {
            if (null == summaryDTO.getPerformanceConclusion() || "".equals(summaryDTO.getPerformanceConclusion())) {
                return new Result<T>(400, "总结不能为空");
            } else {
                performance.setPerformanceConclusion(summaryDTO.getPerformanceConclusion());
                if (null == performance.getPerformanceEvaluation() || "".equals(performance.getPerformanceEvaluation())) {
                    return new Result<>(200, "保存成功,请等待部门负责人评价", performanceMapper.updateById(performance));
                }
                performance.setPerformanceStatus(SUMMARIZED);
                return new Result<>(200, "保存成功,部门负责人已评价,此绩效之后无法更改", performanceMapper.updateById(performance));

            }

        }

        if (null == summaryDTO.getPerformanceEvaluation() || "".equals(summaryDTO.getPerformanceEvaluation())) {
            return new Result<T>(400, "评价不能为空");
        }
        performance.setPerformanceEvaluation(summaryDTO.getPerformanceEvaluation());
        if (null == performance.getPerformanceConclusion() || "".equals(performance.getPerformanceConclusion())) {
            return new Result<>(200, "保存成功,请等待员工总结", performanceMapper.updateById(performance));
        }
        performance.setPerformanceStatus(SUMMARIZED);
        return new Result<>(200, "保存成功,员工已总结,此绩效之后无法更改", performanceMapper.updateById(performance));


    }
}
