package com.calvert.sduser.service;

import java.util.List;
import com.calvert.sduser.domain.SdUser;

/**
 * 用户信息Service接口
 * 
 * @author Calvert
 * @date 2022-06-27
 */
public interface ISdUserService 
{
    /**
     * 查询用户信息
     * 
     * @param id 用户信息主键
     * @return 用户信息
     */
    public SdUser selectSdUserById(Long id);

    /**
     * 查询用户信息列表
     * 
     * @param sdUser 用户信息
     * @return 用户信息集合
     */
    public List<SdUser> selectSdUserList(SdUser sdUser);

    /**
     * 新增用户信息
     * 
     * @param sdUser 用户信息
     * @return 结果
     */
    public int insertSdUser(SdUser sdUser);

    /**
     * 修改用户信息
     * 
     * @param sdUser 用户信息
     * @return 结果
     */
    public int updateSdUser(SdUser sdUser);

    /**
     * 批量删除用户信息
     * 
     * @param ids 需要删除的用户信息主键集合
     * @return 结果
     */
    public int deleteSdUserByIds(Long[] ids);

    /**
     * 删除用户信息信息
     * 
     * @param id 用户信息主键
     * @return 结果
     */
    public int deleteSdUserById(Long id);
}
