#!/usr/bin/env python
# -*- coding: UTF-8 -*-
"""
@File ：dicts.py
@Author ：frankdevhub@gmail.com
@Blog : http://blog.frankdevhub.site
@Date ：2021/2/24 18:34
"""


class Xpath:
    """
      selenium dicts
      web element attribute name
      页面对象常用属性参数
    """
    ATTRIBUTE_NAME = "name"
    ATTRIBUTE_TITLE = "title"
    ATTRIBUTE_TARGET = "target"
    ATTRIBUTE_HREF = "href"
    ATTRIBUTE_CLASS = "class"
    ATTRIBUTE_VALUE = "value"
    # 搜索模糊查询文本框
    INPUT_SEARCH_CLASS = "ipt"
    INPUT_SEARCH_XPATH = "//p[@class='ipt']/input[@id='kwdselectid']"
    # 搜索提交按钮
    SUBMIT_SEARCH_XPATH = "//div[@class='ush top_wrap']/button"
    # 查询结果集列表页面
    SEARCH_RESULT_LIST_XPATH = "//div[@class='j_joblist']/div[@class='e']"  # 查询返回的职位列表集合
    # 查询结果集元素
    RESULT_JD_NAME_CLASS = "t1"
    RESULT_COMPANY_NAME_CLASS = "t2"
    RESULT_JD_LOCATION_CLASS = "t3"
    RESULT_SALARY_RANGE_CLASS = "t4"
    RESULT_JD_PUBLISH_DATE_CLASS = "t5"
    # 元素定位Xpath表达式
    # 搜索返回职位(职位简介,薪资,发布日期)
    RESULT_JD_NAME_XPATH = "a[@class='el']/p[@class='t']/span[@class='jname at']"  # 职位名称
    RESULT_JD_LOCATION_XPATH = "a[@class='el']/p[@class='info']/span[@class='d at']"  # 职位地点信息  上海-浦东新区  |  2年经验  |  本科  |  招2人
    RESULT_SALARY_RANGE_XPATH = "a[@class='el']/p[@class='info']/span[@class='sal']"  # 职位薪资范围描述
    RESULT_JD_PUBLISH_DATE_XPATH = "a[@class='el']/p[@class='t']/span[@class='time']"  # 职位发布日期  01-26发布
    # 搜索返回职位(企业简介,行业,标签)
    COMPANY_INFO_TEXT_XPATH = "//div[@class='tCompany_center clearfix']"  # jsoup解析企业信息页面中的企业信息段落(含html)
    RESULT_COMPANY_NAME_XPATH = "div[@class='er']/a"  # 职位企业名称  盛趣信息技术（上海）有限公司
    RESULT_COMPANY_TYPE_NAME_XPATH = "div[@class='er']/p[@class='dc at']"  # 职位企业类型(民营公司) 民营公司 | 1000-5000人
    RESULT_COMPANY_INDUSTRY_TYPE_NAME_XPATH = "div[@class='er']/p[@class='int at']"  # 职位企业类型(民营公司) 计算机软件
    # TODO:尚未更新定位的元素表达式
    RESULT_JD_CAMPUS_ONLY_XPATH = "p[@class='t1 ']/img[@alt='校招']"  # 职位是否是校招的
    RESULT_JD_INTERNSHIP_ONLY_XPATH = "p[@class='t1 ']/img[@alt='实习']"  # 职位是否是实习生或兼职的岗位
    # 分页导航控件
    RESULT_PAGE_NAVIGATOR_XPATH = "//div[@class='p_in']//ul/li"
    # 首页用户登录按钮
    HOMEPAGE_USER_LOGIN_BTN_XPATH = "//p[@class='op']/a"


class BusinessConstants:
    """业务常用字段"""
    # 接口规范常用字段
    SUCCESS = "success"  # 返回成功
    # selenium configuration
    SELENIUM_CACHE_ROOT_NULL = "selenium cache root directory path should not be null"  # 驱动缓存路径配置不存在
    SELENIUM_CACHE_ROOT_NOT_EXISTS = "selenium cache root directory not exist"  # 驱动缓存配置文件不存在
    SELENIUM_CACHE_FILE_NAME_NULL = "selenium cache file name should not be null"  # 驱动缓存路径配配置为空
    SELENIUM_WEB_DRIVER_PATH_NULL = "selenium web driver path should not be null"  # 无法加载驱动,路径配置为空
    SELENIUM_WEB_DRIVER_NOT_EXIST = "selenium web driver not exist"  # 无法加载驱动,驱动不存在
    # 字符信息常量
    CHARACTER_NULL_ARGUMENT = "character should not be null"  # 字符不能为空
    INVALID_CHINESE_CHARACTER = "not a chinese character"  # 非中文的字符异常
    INVALID_ENGLISH_CHARACTER = "not an english character"  # 非英文的字符的异常
    # https:#www.51job.com 首页地址
    JOB_PLATFORM_HOMEPAGE = "http://www.51job.com"
    # 上海地区入口
    JOB_PLATFORM_HOMEPAGE_SH = "https://search.51job.com/list/020000,000000,0000,00,9,99,%2B,2,1.html?" \
                               + "lang=c" \
                               + "&postchannel=0000" \
                               + "&workyear=99" \
                               + "&cotype=99" \
                               + "&degreefrom=99" \
                               + "&jobterm=99" \
                               + "&companysize=99" \
                               + "&ord_field=0" \
                               + "&dibiaoid=0" \
                               + "&line=" \
                               + "&welfare="

    # 默认上海地区搜索java关键字
    DEFAULT_SEARCH_JAVA = "https://search.51job.com/list/020000,000000,0000,00,9,99,java,2,1.html?" \
                          + "lang=c&postchannel=0000" \
                          + "&workyear=99" \
                          + "&cotype=99&degreefrom=99" \
                          + "&jobterm=99" \
                          + "&companysize=99" \
                          + "&ord_field=0" \
                          + "&dibiaoid=0" \
                          + "&line=" \
                          + "&welfare="
    # 枚举类变量
    CHARACTER_BOOL_VARIABLE_CONFLICT = "time unit variable type conflict"  # 时间单位逻辑性冲突
    # 默认职位地点
    DEFAULT_RESOURCE_LOCATION = "上海"  # 默认搜索地区名
    # 网页搜索关键字项
    JOB_PLATFORM_HOMEPAGE_TITLE_KEY = "招聘网_人才网"  # 页面标题关键字
    JOB_PLATFORM_HOMEPAGE_TITLE_KEY_1 = "招聘"  # 页面标题关键字
    JOB_SEARCH_KEYWORD_NULL = "job search key word search should not be null"  # 职位搜索关键字不能为空
    # 匹配描述薪资范围的正则表达式
    SALARY_RANGE_REGEX_MATCH_ERROR = "salary range regex match failure"  # 无法匹配薪资范围的描述异常
    # 下一页无数据
    NEXT_PAGE_NOT_AVAILABLE = "next page not available, this may be the last page"  # 当前页为最后一页
    # 数据库异常
    DUPLICATE_DB_RECORDS_WITH_UNION_ID = "duplicate records found with markId"  # 重复的唯一识别标识异常
    # 默认客户端浏览器会话缓存文件名
    DEFAULT_TEMP_COOKIE_FILE = "temp_cookie.dat"  # 缓存会话文件
    # 提取链接唯一标识的正则表达式
    DEFAULT_HTTP_LINK_MARK_REGEX = "((?P<key>[1-9]\\d*\\.?\\d*)(.html))"  # 提取链接唯一标识的正则表达式
    # 无法由平台链接生成唯一检索markId
    UNION_ID_GENERATE_ERROR = "cannot generate union id"  # 无法由平台链接生成唯一检索unionId

#
# if __name__ == '__main__':
#     url_link = BusinessConstants.JOB_PLATFORM_HOMEPAGE_SH
#     print(url_link)
