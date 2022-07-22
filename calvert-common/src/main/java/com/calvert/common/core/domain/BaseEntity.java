package com.calvert.common.core.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.calvert.common.core.page.PageDomain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.alibaba.fastjson2.annotation.JSONField;
import com.calvert.common.utils.StringUtils;

import com.calvert.common.core.domain.model.LoginUser;
import com.calvert.common.utils.SecurityUtils;
import com.calvert.common.utils.spring.SpringUtils;
import com.calvert.common.utils.uuid.IdUtils;
import org.springframework.core.env.Environment;


/**
 * Entity基类
 * 
 * @author ruoyi
 */
public class BaseEntity<T> implements Serializable
{
    /**
     * 删除标记（0：正常；1：删除；）
     */
    public static final String DEL_FLAG_NORMAL = "0";
    public static final String DEL_FLAG_DELETE = "1";

    private static final long serialVersionUID = 1L;

    //写入日志时记录变更前对象，变更后对象，日志类型（添加，编辑，删除）
    public static final String LOG_OLD_DATA = "LOG_OLD_DATA";
    public static final String LOG_NEW_DATA = "LOG_NEW_DATA";
    public static final String LOG_TYPE = "LOG_TYPE";

    /** 搜索值 */
    private String searchValue;

    /** 创建者 */
    private String createBy;

    /**
     * 创建部门
     */
    private String createDept;

    /**状态[1:正常]**/
    //private String status;


    protected String delFlag;

    private static final Environment EVN;

    static {
        EVN = SpringUtils.getBean(Environment.class);
    }

    public BaseEntity() {
        super();
        this.delFlag = DEL_FLAG_NORMAL;
    }


    public BaseEntity(String id) {
        this();
        this.id = id;
    }

    /**
     * 当前实体分页对象
     */
    @JsonIgnore
    @JSONField(serialize = false)
    protected PageDomain page;


    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /**
     * 版本
     */
    private Integer version;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 更新IP
     */
    private String updateIp;

    /** 备注 */
    private String remark;


    @JsonIgnore
    @JSONField(serialize = false)
    protected boolean isNewRecord = false;

    /** 请求参数 */
    private Map<String, Object> params;

    public String getSearchValue()
    {
        return searchValue;
    }

    public void setSearchValue(String searchValue)
    {
        this.searchValue = searchValue;
    }


    public String getCreateDept()
    {
        return createDept;
    }

    public String getUpdateIp()
    {
        return updateIp;
    }

    public Integer getVersion()
    {
        return version;
    }

    public String getDelFlag(){
        return delFlag;
    }

    public String getCreateBy()
    {
        return createBy;
    }


    public void setCreateBy(String createBy)
    {
        this.createBy = createBy;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public String getUpdateBy()
    {
        return updateBy;
    }

    public void setUpdateBy(String updateBy)
    {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public Map<String, Object> getParams()
    {
        if (params == null)
        {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params)
    {
        this.params = params;
    }

    public PageDomain getPage() {
        if (null == page) {
            return new PageDomain();
        }
        return page;
    }

    public void setPage(PageDomain page) {
        this.page = page;
    }

    /**
     * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
     * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
     * @return
     */
    public boolean getIsNewRecord() {
        return isNewRecord || StringUtils.isBlank(getId());
    }

    /**
     * 主键ID
     */
    private String id;



    /**
     * 更新之前执行方法，需要手动调用
     */
    public void preUpdate() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        if (null != loginUser.getUser()) {
            this.updateBy = loginUser.getUser().getId() + "";
            this.updateIp = loginUser.getIpaddr();
        } else {
            this.updateBy = "1";
            this.updateIp = "127.0.0.1";
        }
        this.updateTime = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String s) {
        this.id = id;
    }

    /**
     * 插入之前执行方法，需要手动调用
     */
    public void preInsert() {
        // 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
        if(StringUtils.isEmpty(this.getId())){
            setId(IdUtils.randomUUID());
        }
        LoginUser loginUser = SecurityUtils.getLoginUser();
        if (null != loginUser.getUser()) {
            this.createBy = loginUser.getUser().getId() + "";
            this.updateBy = loginUser.getUser().getId() + "";
            this.createDept = loginUser.getUser().getDeptId() + "";
            this.updateIp = loginUser.getIpaddr();
        } else {
            this.updateBy = "1";
            this.createBy = "1";
            this.updateIp = "127.0.0.1";
            this.createDept="100";
        }
        this.delFlag = DEL_FLAG_NORMAL;
        this.version = 1;
        this.createTime = new Date();
        this.updateTime = this.createTime;
    }

    public boolean isRecordLog() {
        return true;
    }



}
