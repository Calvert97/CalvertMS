package com.calvert.system.mapper;

import java.util.List;
import com.calvert.system.domain.SysNotice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 通知公告表 数据层
 *
 * @author ruoyi
 */
@Mapper
public interface SysNoticeMapper
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
     * 批量删除公告
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
     * 已读公告信息
     *
     * @param notice 需要已读的公告ID
     * @return 结果
     */
    public int setReadNotice(SysNotice notice);

    /**
     * 批量已读公告信息
     *
     * @param noticeId 需要已读的公告ID
     * @return 结果
     */
    public int updateNoticeToRead(Long[] noticeId);

    /**
     * 批量已读公告信息
     *
     * @param noticeId 需要已读的公告ID
     * @return 结果
     */
    public int updateNoticesToRead(Long[] noticeId);

    /**
     * 批量已读公告信息
     *
     * @param notice 需要已读的公告ID
     * @return 结果
     */
    public int insertNoticeInfoToUserRead(SysNotice notice);

    /**
     * 批量已读公告信息
     *
     * @param notice 需要已读的公告ID
     * @return 结果
     */
    public int updateUserIdToUserRead(SysNotice notice);




}
