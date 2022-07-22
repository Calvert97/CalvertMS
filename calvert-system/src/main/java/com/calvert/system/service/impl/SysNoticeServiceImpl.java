package com.calvert.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.calvert.system.domain.SysNotice;
import com.calvert.system.mapper.SysNoticeMapper;
import com.calvert.system.service.ISysNoticeService;

/**
 * 公告 服务层实现
 * 
 * @author ruoyi
 */
@Service
public class SysNoticeServiceImpl  implements ISysNoticeService
{

    @Autowired
    private SysNoticeMapper noticeMapper;

    /**
     * 查询公告信息
     *
     * @param notice 公告ID
     * @return 公告信息
     */
    @Override
    public int getNoticeByUser(SysNotice notice)
    {
        return noticeMapper.getNoticeByUser(notice);
    }

    /**
     * 查询公告信息
     *
     * @param noticeId 公告ID
     * @return 公告信息
     */
    @Override
    public SysNotice selectNoticeById(Long noticeId)
    {
        return noticeMapper.selectNoticeById(noticeId);
    }

    /**
     * 查询公告列表
     * 
     * @param notice 公告信息
     * @return 公告集合
     */
    @Override
    public List<SysNotice> selectNoticeList(SysNotice notice)
    {
        return noticeMapper.selectNoticeList(notice);
    }

    /**
     * 查询用户公告列表
     *
     * @param notice 公告信息
     * @return 公告集合
     */
    @Override
    public List<SysNotice> findNoticeByUser(SysNotice notice)
    {
        return noticeMapper.findNoticeByUser(notice);
    }

    /**
     * 新增公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int insertNotice(SysNotice notice)
    {
        return noticeMapper.insertNotice(notice);
    }


    /**
     * 批量已读公告信息
     *
     * @param notice 需要删除的公告ID
     * @return 结果
     */
    @Override
    public int setReadNotice(SysNotice notice)
    {
        return noticeMapper.setReadNotice(notice);
    }

    /**
     * 批量已读公告信息
     *
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    @Override
    public int updateNoticesToRead(Long[] noticeIds)
    {
        return noticeMapper.updateNoticesToRead(noticeIds);
    }
    

    /**
     * 修改公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int updateNotice(SysNotice notice)
    {
        return noticeMapper.updateNotice(notice);
    }

    /**
     * 删除公告对象
     * 
     * @param noticeId 公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeById(Long noticeId)
    {
        return noticeMapper.deleteNoticeById(noticeId);
    }

    /**
     * 批量删除公告信息
     * 
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeByIds(Long[] noticeIds)
    {
        return noticeMapper.deleteNoticeByIds(noticeIds);
    }


    /**
     * 新增公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int insertNoticeInfoToUserRead(SysNotice notice)
    {
        return noticeMapper.insertNoticeInfoToUserRead(notice);
    }

    /**
     * 新增公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int updateUserIdToUserRead(SysNotice notice)
    {
        return noticeMapper.updateUserIdToUserRead(notice);
    }

}
