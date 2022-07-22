package com.calvert.web.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.calvert.common.annotation.Log;
import com.calvert.common.core.controller.BaseController;
import com.calvert.common.core.domain.AjaxResult;
import com.calvert.common.core.page.TableDataInfo;
import com.calvert.common.enums.BusinessType;
import com.calvert.system.domain.SysNotice;
import com.calvert.system.service.ISysNoticeService;

/**
 * 公告 信息操作处理
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/notice")
public class SysNoticeController extends BaseController
{
    @Autowired
    private ISysNoticeService noticeService;


    /**
     * 获取通知公告列表
     */
    @PreAuthorize("@ss.hasPermi('system:notice:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysNotice notice)
    {
        startPage();
        List<SysNotice> list = noticeService.selectNoticeList(notice);
        return getDataTable(list);
    }

    /**
     * 获取通知公告列表
     */
    @GetMapping("/listNoticeByUser")
    public TableDataInfo listNoticeByUser(SysNotice notice)
    {
        startPage();
        notice.setUserId(getUserId());
        List<SysNotice> list = noticeService.findNoticeByUser(notice);
        return getDataTable(list);
    }

    /**
     * 根据通知公告编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:notice:query')")
    @GetMapping(value = "/{noticeId}")
    public AjaxResult getInfo(@PathVariable Long noticeId)
    {
        return AjaxResult.success(noticeService.selectNoticeById(noticeId));
    }

    /**
     * 根据通知公告编号获取详细信息
     */
    @GetMapping("/getNoticeByUser")
    public AjaxResult getNoticeByUser(@Validated @RequestBody SysNotice notice)
    {
        return toAjax(noticeService.getNoticeByUser(notice));
    }

    /**
     * 新增通知公告
     */
    @PreAuthorize("@ss.hasPermi('system:notice:add')")
    @Log(title = "通知公告", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysNotice notice)
    {
        notice.setCreateBy(getUsername());
        return toAjax(noticeService.insertNotice(notice));
    }

     /**
     * 将单条通知公告标记为已读
     */
//    @PreAuthorize("@ss.hasPermi('system:notice:updateNoticeToRead')")
    @Log(title = "公告标记为已读", businessType = BusinessType.UPDATE)
    @PutMapping("/updateNoticeToRead")
    public AjaxResult updateNoticeToRead(@Validated @RequestBody SysNotice notice)
    {
//        notice.setReadNotice("1");
        notice.setIsRead("1");
        return toAjax(noticeService.setReadNotice(notice));
    }

    /**
     * 将多条通知公告标记为已读
     */
    @Log(title = "公告标记为已读", businessType = BusinessType.UPDATE)
    @PutMapping("/updateNoticesToRead{noticeIds}")
    public AjaxResult updateNoticesToRead(@PathVariable Long[] noticeIds)
    {
        return toAjax(noticeService.updateNoticesToRead(noticeIds));
    }

    /**
     * 修改通知公告
     */
    @PreAuthorize("@ss.hasPermi('system:notice:edit')")
    @Log(title = "通知公告", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysNotice notice)
    {
        notice.setUpdateBy(getUsername());
        notice.setReadNotice("0");
        notice.setIsRead("0");
        return toAjax(noticeService.updateNotice(notice));
    }

    /**
     * 删除通知公告
     */
    @PreAuthorize("@ss.hasPermi('system:notice:remove')")
    @Log(title = "通知公告", businessType = BusinessType.DELETE)
    @DeleteMapping("/{noticeIds}")
    public AjaxResult remove(@PathVariable Long[] noticeIds)
    {
        return toAjax(noticeService.deleteNoticeByIds(noticeIds));
    }

     // author Calvert

    /**
     * 获取个人公告阅读列表
     */
    @PostMapping("/insertNoticeInfoToUserRead")
    public AjaxResult insertNoticeInfoToUserRead(@Validated @RequestBody SysNotice notice)
    {
        notice.setUserId(getUserId());
        notice.setReadNotice("1");
        notice.setIsRead("1");
        return toAjax(noticeService.insertNoticeInfoToUserRead(notice));
    }
    /**
     * 将单条通知公告标记为已读
     */
    @PutMapping("/updateUserIdToUserRead")
    public AjaxResult updateUserIdToUserRead(@Validated @RequestBody SysNotice notice)
    {
        return toAjax(noticeService.updateUserIdToUserRead(notice));
    }



}
