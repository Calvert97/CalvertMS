package com.calvert.common.core.page;

import com.calvert.common.utils.ServletUtils;
import com.calvert.common.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 分页数据
 * 
 * @author ruoyi
 */
public class PageDomain
{

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * 分页参数合理化
     */
    public static final String REASONABLE = "reasonable";

    /** 当前记录起始索引 */
    private Integer pageNum;

    /** 每页显示记录数 */
    private Integer pageSize;

    /** 排序列 */
    private String orderByColumn;

    /** 排序的方向desc或者asc */
    private String isAsc = "asc";

    /** 分页参数合理化 */
    private Boolean reasonable = true;

    public String getOrderBy()
    {
        if (StringUtils.isEmpty(orderByColumn))
        {
            return "";
        }
        return StringUtils.toUnderScoreCase(orderByColumn) + " " + isAsc;
    }

    public Integer getPageNum()
    {
        return pageNum;
    }

    public void setPageNum(Integer pageNum)
    {
        this.pageNum = pageNum;
    }

    public Integer getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }

    public String getOrderByColumn()
    {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn)
    {
        this.orderByColumn = orderByColumn;
    }

    public String getIsAsc()
    {
        return isAsc;
    }

    public void setIsAsc(String isAsc)
    {
        if (StringUtils.isNotEmpty(isAsc))
        {
            // 兼容前端排序类型
            if ("ascending".equals(isAsc))
            {
                isAsc = "asc";
            }
            else if ("descending".equals(isAsc))
            {
                isAsc = "desc";
            }
            this.isAsc = isAsc;
        }
    }

    public Boolean getReasonable()
    {
        if (StringUtils.isNull(reasonable))
        {
            return Boolean.TRUE;
        }
        return reasonable;
    }

    public void setReasonable(Boolean reasonable)
    {
        this.reasonable = reasonable;
    }


    public PageDomain() {

    }

    public PageDomain(HttpServletRequest request, HttpServletResponse response) {
        this(request, response, -2);
    }

    /**
     * 构造方法
     *
     * @param request         传递 repage 参数，来记住页码
     * @param response        用于设置 Cookie，记住页码
     * @param defaultPageSize 默认分页大小，如果传递 -1 则为不分页，返回所有数据
     */
    public PageDomain(HttpServletRequest request, HttpServletResponse response, int defaultPageSize) {
        // 设置页码参数（传递repage参数，来记住页码）
        String pageNum = request.getParameter(PAGE_NUM);
        if (StringUtils.isNumeric(pageNum)) {
            this.setPageNum(Integer.parseInt(pageNum));
        }
        // 设置页面大小参数（传递repage参数，来记住页码大小）
        String pageSize = request.getParameter(PAGE_SIZE);
        if (StringUtils.isNumeric(pageSize)) {
            this.setPageSize(Integer.parseInt(pageSize));
        } else if (defaultPageSize != -2) {
            this.pageSize = defaultPageSize;
        }
        // 设置排序参数
        String orderByColumn = request.getParameter(ORDER_BY_COLUMN);
        if (StringUtils.isNotBlank(orderByColumn)) {
            this.setOrderByColumn(orderByColumn);
        }

        String isAsc = request.getParameter(IS_ASC);
        if (StringUtils.isNotBlank(isAsc)) {
            this.setIsAsc(isAsc);
        }

        this.setReasonable(ServletUtils.getParameterToBool(REASONABLE));

    }
}
