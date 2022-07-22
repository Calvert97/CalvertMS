package com.calvert.system.service;

import java.util.List;
import com.calvert.system.domain.SysNotice;

/**
 * 公告 服务层
 * 
 * @author ruoyi
 */
public interface ISysNoticeService
{
    /**
     * 查询公告信息
     * 
     * @param noticeId 公告ID
     * @return 公告信息
     */
    public SysNotice selectNoticeById(Long noticeId);

    /**
     * 查询公告信息
     *
     * @param notice 公告ID
     * @return 公告信息
     */
    public int getNoticeByUser(SysNotice notice);

    /**
     * 查询公告列表
     * 
     * @param notice 公告信息
     * @return 公告集合
     */
    public List<SysNotice> selectNoticeList(SysNotice notice);

    /**
     * 查询用户公告列表
     *
     * @param notice 公告信息
     * @return 公告集合
     */
    public List<SysNotice> findNoticeByUser(SysNotice notice);

    /**
     * 新增公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    public int insertNotice(SysNotice notice);

    /**
     * 修改公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    public int updateNotice(SysNotice notice);

    /**
     * 删除公告信息
     * 
     * @param noticeId 公告ID
     * @return 结果
     */
    public int deleteNoticeById(Long noticeId);
    
    /**
     * 批量删除公告信息
     * 
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    public int deleteNoticeByIds(Long[] noticeIds);

    /**
     * 批量删除公告信息
     *
     * @param notice 需要标记的公告ID
     * @return 结果
     */
    public int setReadNotice(SysNotice notice);

    /**
     * 批量删除公告信息
     *
     * @param noticeIds 需要标记的公告ID
     * @return 结果
     */
    public int updateNoticesToRead(Long[] noticeIds);

    /**
     * 新增公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    public int insertNoticeInfoToUserRead(SysNotice notice);

    /**
     * 新增公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    public int updateUserIdToUserRead(SysNotice notice);
}
