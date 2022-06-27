package com.calvert.sduser.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.calvert.common.annotation.Log;
import com.calvert.common.core.controller.BaseController;
import com.calvert.common.core.domain.AjaxResult;
import com.calvert.common.enums.BusinessType;
import com.calvert.sduser.domain.SdUser;
import com.calvert.sduser.service.ISdUserService;
import com.calvert.common.utils.poi.ExcelUtil;
import com.calvert.common.core.page.TableDataInfo;

/**
 * 用户信息Controller
 * 
 * @author Calvert
 * @date 2022-06-27
 */
@RestController
@RequestMapping("/obj/sduser")
public class SdUserController extends BaseController
{
    @Autowired
    private ISdUserService sdUserService;

    /**
     * 查询用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('obj:sduser:list')")
    @GetMapping("/list")
    public TableDataInfo list(SdUser sdUser)
    {
        startPage();
        List<SdUser> list = sdUserService.selectSdUserList(sdUser);
        return getDataTable(list);
    }

    /**
     * 导出用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('obj:sduser:export')")
    @Log(title = "用户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SdUser sdUser)
    {
        List<SdUser> list = sdUserService.selectSdUserList(sdUser);
        ExcelUtil<SdUser> util = new ExcelUtil<SdUser>(SdUser.class);
        util.exportExcel(response, list, "用户信息数据");
    }

    /**
     * 获取用户信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('obj:sduser:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sdUserService.selectSdUserById(id));
    }

    /**
     * 新增用户信息
     */
    @PreAuthorize("@ss.hasPermi('obj:sduser:add')")
    @Log(title = "用户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SdUser sdUser)
    {
        return toAjax(sdUserService.insertSdUser(sdUser));
    }

    /**
     * 修改用户信息
     */
    @PreAuthorize("@ss.hasPermi('obj:sduser:edit')")
    @Log(title = "用户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SdUser sdUser)
    {
        return toAjax(sdUserService.updateSdUser(sdUser));
    }

    /**
     * 删除用户信息
     */
    @PreAuthorize("@ss.hasPermi('obj:sduser:remove')")
    @Log(title = "用户信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sdUserService.deleteSdUserByIds(ids));
    }
}
