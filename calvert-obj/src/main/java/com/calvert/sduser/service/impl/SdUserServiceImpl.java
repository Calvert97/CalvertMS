package com.calvert.sduser.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.calvert.sduser.mapper.SdUserMapper;
import com.calvert.sduser.domain.SdUser;
import com.calvert.sduser.service.ISdUserService;

/**
 * 用户信息Service业务层处理
 * 
 * @author Calvert
 * @date 2022-06-27
 */
@Service
public class SdUserServiceImpl implements ISdUserService 
{
    @Autowired
    private SdUserMapper sdUserMapper;

    /**
     * 查询用户信息
     * 
     * @param id 用户信息主键
     * @return 用户信息
     */
    @Override
    public SdUser selectSdUserById(Long id)
    {
        return sdUserMapper.selectSdUserById(id);
    }

    /**
     * 查询用户信息列表
     * 
     * @param sdUser 用户信息
     * @return 用户信息
     */
    @Override
    public List<SdUser> selectSdUserList(SdUser sdUser)
    {
        return sdUserMapper.selectSdUserList(sdUser);
    }

    /**
     * 新增用户信息
     * 
     * @param sdUser 用户信息
     * @return 结果
     */
    @Override
    public int insertSdUser(SdUser sdUser)
    {
        return sdUserMapper.insertSdUser(sdUser);
    }

    /**
     * 修改用户信息
     * 
     * @param sdUser 用户信息
     * @return 结果
     */
    @Override
    public int updateSdUser(SdUser sdUser)
    {
        return sdUserMapper.updateSdUser(sdUser);
    }

    /**
     * 批量删除用户信息
     * 
     * @param ids 需要删除的用户信息主键
     * @return 结果
     */
    @Override
    public int deleteSdUserByIds(Long[] ids)
    {
        return sdUserMapper.deleteSdUserByIds(ids);
    }

    /**
     * 删除用户信息信息
     * 
     * @param id 用户信息主键
     * @return 结果
     */
    @Override
    public int deleteSdUserById(Long id)
    {
        return sdUserMapper.deleteSdUserById(id);
    }
}
