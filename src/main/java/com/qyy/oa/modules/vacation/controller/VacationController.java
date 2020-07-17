package com.qyy.oa.modules.vacation.controller;

import com.qyy.oa.config.ResultData;
import com.qyy.oa.modules.vacation.dto.VacationCountDto;
import com.qyy.oa.modules.vacation.dto.VacationMoreDto;
import com.qyy.oa.modules.vacation.entity.VacationEntity;
import com.qyy.oa.modules.vacation.service.VacationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: qiyayu
 * @date: 2020-07-08 14:29
 * @description: 请假
 */
@RestController
@RequestMapping("/vacation")
@Api(description = "请假")
public class VacationController {

    @Autowired
    private VacationService vacationService;

    /**
     * @author: qiyayu
     * @description: 请假信息列表
     * @date: 2020-07-08 14:32
     * @param: [vacationMoreDto]
     * @return: com.qyy.oa.config.ResultData
     **/
    @GetMapping("/list")
    @ApiOperation(value = "请假列表", notes = "请假列表")
    public ResultData getVacationList(@ModelAttribute VacationMoreDto vacationMoreDto) {
        return vacationService.getVacationList(vacationMoreDto);
    }

    /**
     * @author: qiyayu
     * @description: 新增请假信息
     * @date: 2020-07-08 15:03
     * @param: [vacationEntity]
     * @return: com.qyy.oa.config.ResultData
     **/
    @PostMapping("/insertVacation")
    @ApiOperation(value = "新增请假信息", notes = "新增请假信息")
    public ResultData insertVacation(@RequestBody VacationEntity vacationEntity) throws Exception {
        return vacationService.insertVacation(vacationEntity);
    }

    /**
     * @author: qiyayu
     * @description: 查询单个请假详情
     * @date: 2020-07-08 20:21
     * @param: [id]
     * @return: com.qyy.oa.config.ResultData
     **/
    @GetMapping("/{id}")
    @ApiOperation(value = "查询单个请假详情", notes = "查询单个请假详情")
    public ResultData getVacationDetail(@PathVariable(name = "id") Integer id) {
        return vacationService.getVacationDetail(id);
    }

    /**
     * @author: qiyayu
     * @description: 修改请假信息
     * @date: 2020-07-09 09:55
     * @param: [vacationEntity]
     * @return: com.qyy.oa.config.ResultData
     **/
    @PostMapping("/updateVacation")
    @ApiOperation(value = "修改请假信息", notes = "修改请假信息")
    public ResultData updateVacation(@RequestBody VacationEntity vacationEntity) throws Exception {
        return vacationService.updateVacation(vacationEntity);
    }

    /**
     * @author: qiyayu
     * @description: 删除请假信息
     * @date: 2020-07-09 10:10
     * @param: [vacationEntity]
     * @return: com.qyy.oa.config.ResultData
     **/
    @PostMapping("/deleteVacation")
    @ApiOperation(value = "删除请假信息", notes = "删除请假信息")
    public ResultData deleteVacation(@RequestBody VacationEntity vacationEntity) {
        return vacationService.deleteVacation(vacationEntity);
    }


    /**
     * @author: qiyayu
     * @description: 管理员审批请假
     * @date: 2020-07-09 10:23
     * @param: [vacationEntity]
     * @return: com.qyy.oa.config.ResultData
     **/
    @PostMapping("/modifyVacation")
    @ApiOperation(value = "管理员审批请假信息", notes = "管理员审批请假信息")
    public ResultData modifyVacation(@RequestBody VacationEntity vacationEntity) {
        return vacationService.modifyVacation(vacationEntity);
    }

    /**
     * @author: qiyayu
     * @description: 计算请假时长
     * @date: 2020-07-10 10:23
     * @param: [vacationEntity]
     * @return: com.qyy.oa.config.ResultData
     **/
    @PostMapping("/countDays")
    @ApiOperation(value = "计算请假时长", notes = "计算请假时长")
    public ResultData countDays(@RequestBody VacationEntity vacationEntity) {
        return vacationService.countDays(vacationEntity);
    }

    /**
     * @author: qiyayu
     * @description: 统计请假信息
     * @date: 2020-07-10 11:26
     * @param: []
     * @return: com.qyy.oa.config.ResultData
     **/
    @PostMapping("/countVacation")
    @ApiOperation(value = "统计请假信息", notes = "统计请假信息")
    public ResultData countVacation(@RequestBody VacationCountDto vacationCountDto) {
        return vacationService.countVacation(vacationCountDto);
    }

    /**
     * @author: qiyayu
     * @description: 提交审批
     * @date: 2020-07-13 13:53
     * @param: [vacationEntity]
     * @return: com.qyy.oa.config.ResultData
     **/
    @PostMapping("/changeVacationState")
    @ApiOperation(value = "提交审批", notes = "提交审批")
    public ResultData changeVacationState(@RequestBody VacationEntity vacationEntity) {
        return vacationService.changeVacationState(vacationEntity);
    }

    /**
     * @author: qiyayu
     * @description: 导出请假表格
     * @date: 2020-07-10 10:22
     * @param: [response]
     * @return: void
     **/
    @GetMapping("/download")
    @ApiOperation(value = "导出请假表格数据", notes = "导出请假表格数据")
    public void download(HttpServletResponse response) throws IOException {
        vacationService.download(response);
    }

    /**
     * @author: qiyayu
     * @description: 统计请假信息类型
     * @date: 2020-07-16 14:01
     * @param: []
     * @return: com.qyy.oa.config.ResultData
     **/
    @GetMapping("/countVacationType")
    @ApiOperation(value = "统计请假信息类型", notes = "统计请假信息类型")
    public ResultData countVacationType() {
        return vacationService.countVacationType();
    }

}
