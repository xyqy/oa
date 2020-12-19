package com.qyy.oa.modules.controller;

import com.qyy.oa.config.ResultData;
import com.qyy.oa.modules.dto.VacationTypeMoreDTO;
import com.qyy.oa.modules.entity.VacationTypeEntity;
import com.qyy.oa.modules.service.VacationTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: qiyayu
 * @date: 2020-07-08 09:21
 * @description: 假期类型
 */
@RestController
@RequestMapping("/vacationType")
@Api(description = "假期类型")
public class VacationTypeController {

    @Autowired
    private VacationTypeService vacationTypeService;

    /**
     * @author: qiyayu
     * @description: 假期类型列表
     * @date: 2020-07-08 09:27
     * @param: [vacationTypeMoreDto]
     * @return: com.qyy.oa.config.ResultData
     **/
    @GetMapping("/list")
    @ApiOperation(value = "假期类型列表")
    public ResultData getVacationTypeList(@ModelAttribute VacationTypeMoreDTO vacationTypeMoreDto) {
        return vacationTypeService.getVacationTypeList(vacationTypeMoreDto);
    }

    /**
     * @author: qiyayu
     * @description: 新增假期类型
     * @date: 2020-07-08 09:52
     * @param: [vacationTypeEntity]
     * @return: com.qyy.oa.config.ResultData
     **/
    @PostMapping("/insertVacationType")
    @ApiOperation(value = "新增假期类型")
    public ResultData insertVacationType(@RequestBody VacationTypeEntity vacationTypeEntity) {
        return vacationTypeService.insertVacationType(vacationTypeEntity);
    }

    /**
     * @author: qiyayu
     * @description: 修改假期类型
     * @date: 2020-07-08 09:57
     * @param: [vacationTypeEntity]
     * @return: com.qyy.oa.config.ResultData
     **/
    @PostMapping("/updateVacationType")
    @ApiOperation(value = "修改假期类型")
    public ResultData updateVacationType(@RequestBody VacationTypeEntity vacationTypeEntity) {
        return vacationTypeService.updateVacationType(vacationTypeEntity);
    }

    /**
     * @author: qiyayu
     * @description: 删除假期
     * @date: 2020-07-08 10:04
     * @param: [vacationTypeEntity]
     * @return: com.qyy.oa.config.ResultData
     **/
    @PostMapping("/deleteVacation")
    @ApiOperation(value = "删除假期")
    public ResultData deleteVacationType(@RequestBody VacationTypeEntity vacationTypeEntity) {
        return vacationTypeService.deleteVacationType(vacationTypeEntity);
    }
}
