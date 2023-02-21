package com.macro.mall.tiny.controller;

import com.macro.mall.tiny.common.api.CommonPage;
import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.mbg.model.PmsBrand;
import com.macro.mall.tiny.service.PmsBrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
//import java.util.logging.Logger;

/**
 * 品牌管理Controller
 *
 * @author lwp
 * @date 2023/2/21 20:51
 */
@RestController
@RequestMapping("/brand")
public class PmsBrandController {
    @Resource
    PmsBrandService pmsBrandService;

    private static final Logger LOGGER= LoggerFactory.getLogger(PmsBrandController.class);

    @GetMapping("/listAll")
    public CommonResult<List<PmsBrand>> listAllBrand(){
        return CommonResult.success(pmsBrandService.listAllBrand());
    }

    @PostMapping("/create")
    public CommonResult createBrand(@RequestBody PmsBrand pmsBrand){
        int count=0;
        count=pmsBrandService.createBrand(pmsBrand);
        if(count==1){
            LOGGER.debug("create success"+pmsBrand);
            return CommonResult.success(pmsBrand);
        }else{
            LOGGER.debug("create fail"+pmsBrand);
            return CommonResult.failed("操作失败");
        }
    }

    @PostMapping("/update/{id}")
    public CommonResult updateBrand(@PathVariable("id") Long id,@RequestBody PmsBrand pmsBrand){
        int count=0;
        count=pmsBrandService.updateBrand(id,pmsBrand);
        if(count==1){
            LOGGER.debug("update success"+pmsBrand);
            return CommonResult.success(pmsBrand);
        }else{
            LOGGER.debug("update fail"+pmsBrand);
            return CommonResult.failed("操作失败");
        }
    }

    @GetMapping("/delete/{id}")
    public CommonResult deleteBrand(@PathVariable("id")Long id){
        int count=0;
        count=pmsBrandService.deleteBrand(id);
        if(count==1){
            LOGGER.debug("delete success"+id);
            return CommonResult.success(null);
        }else{
            LOGGER.debug("delete fail"+id);
            return CommonResult.failed("操作失败");
        }
    }

    @GetMapping("/list")
    public CommonResult listBrand(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                  @RequestParam(value = "pageSize",defaultValue = "3")Integer pageSize)
    {
        List<PmsBrand> list=pmsBrandService.listBrand(pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @GetMapping("/get/{id}")
    public CommonResult getBrand(@PathVariable("id")Long id){
        return CommonResult.success(pmsBrandService.getBrand(id));
    }
}