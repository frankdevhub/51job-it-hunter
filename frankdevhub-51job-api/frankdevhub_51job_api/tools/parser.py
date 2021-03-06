#!/usr/bin/env python
# -*- coding: UTF-8 -*-
"""
@File ：parser.py
@Author ：frankdevhub@gmail.com
@Blog : http://blog.frankdevhub.site
@Date ：2021/2/18 14:47
"""

import logging as log
import re

from frankdevhub_51job_api.bsoup.data import models
from frankdevhub_51job_api.dicts.constants import BusinessConstants
from frankdevhub_51job_api.tools.date import DateUnit
from frankdevhub_51job_api.tools.numeric import NumericUnit
from job_api.error.errors import BusinessError

log.basicConfig(level=log.INFO)

__all__ = ['parse_salary_text', 'is_unit_by_thousand', 'is_unit_by_ten_thousand',
           'is_unit_by_day', 'is_unit_by_month', 'is_unit_by_year', 'convert_context']

"""解析薪资范围的正则表达式"""
range_regex = "(?P<min>(([1-9]\\d*\\.?\\d+)|(0\\.\\d*[1-9])|(\\d+))?)" + \
              "(?P<hyphen>((—|-)+)?)" + \
              "(?P<max>(([1-9]\\d*\\.?\\d+)|(0\\.\\d*[1-9])|(\\d+))?)" + \
              "(?P<numeric>[\\u4e00-\\u9fa5]?)(/?)(?<date>[\\u4e00-\\u9fa5]?)"


def parse_salary_text(text: str) -> tuple:
    """解析匹配薪资范围描述的关键字"""
    assert text and text.strip() is not None, 'salary text cannot be empty'
    text = text.strip()
    log.info(f'salary text : {text}')

    pattern = re.compile(range_regex, re.M | re.I)
    matched = pattern.match(text)
    if matched:
        min_value = matched.group("min")  # 匹配最小值
        max_value = matched.group("max")  # 匹配最大值
        numeric_unit = matched.group("numeric")  # 匹配数值单位
        time_unit = matched.group("date")  # 匹配计量时间(年月日天)
    else:
        raise BusinessError(BusinessConstants.SALARY_RANGE_REGEX_MATCH_ERROR)
    return min_value, max_value, numeric_unit, time_unit


def is_unit_by_thousand(text: str) -> bool:
    """是否是以千为计量单位"""
    log.info(f'salary unit text: {text}')
    try:
        unit = NumericUnit(text.strip())
    except ValueError as e:
        log.error(str(e))
        return False
    if unit == NumericUnit.Thousand_CN_TW or unit == NumericUnit.Thousand:
        return True
    else:
        return False


def is_unit_by_ten_thousand(text: str) -> bool:
    """是否是以万为计量单位"""
    log.info(f'salary unit text: {text}')
    try:
        unit = NumericUnit(text.strip())
    except ValueError as e:
        log.error(str(e))
        return False
    if unit == NumericUnit.Ten_Thousand_CN \
            or unit == NumericUnit.Ten_Thousand_TW \
            or unit == NumericUnit.Ten_Thousand_EN:
        return True
    else:
        return False


def is_unit_by_day(text: str) -> bool:
    """是否是以天为计量单位"""
    log.info(f'time unit text: {text}')
    try:
        unit = DateUnit(text.strip())
    except ValueError as e:
        log.error(str(e))
        return False
    if unit == DateUnit.DAY_1 or unit == DateUnit.DAY_2:
        return True
    else:
        return False


def is_unit_by_month(text: str) -> bool:
    """是否是以月为计量单位"""
    log.info(f'time unit text: {text}')
    try:
        unit = DateUnit(text.strip())
    except ValueError as e:
        log.error(str(e))
        return False
    if unit == DateUnit.MONTH:
        return True
    else:
        return False


def is_unit_by_year(text: str) -> bool:
    """是否是以年为计量单位"""
    log.info(f'time unit text: {text}')
    try:
        unit = DateUnit(text.strip())
    except ValueError as e:
        log.error(str(e))
        return False
    if unit == DateUnit.YEAR:
        return True
    else:
        return False


def convert_context(data: str) -> []:
    """
    平台json转换为ORM持久化对象
    平台返回:
    "engine_search_result"  搜索引擎返回的结果集
    "market_ads"  市场推广广告职位
    "auction_ads"
    "top_ads"
    @param data 返回的json字符串
    @return ORM业务对象实体类集合
    """
    assert data.isspace() is not True, 'page search result json object cannot be empty'
    datas = []
    return datas


# noinspection PyTypeChecker
def convert_context(data: str) -> models.PlatformDataBriefSource:
    """
    平台json转换为ORM持久化对象
    平台返回:
    "engine_search_result"  搜索引擎返回的结果集
    "market_ads"  市场推广广告职位
    "auction_ads"
    "top_ads"
    @param data 返回的json字符串
    @return ORM业务对象实体类:PlatformDataBriefSource
    """
    assert data.isspace() is not True, 'json object cannot be empty, key = "engine_search_result"'
    source_data = models.PlatformDataBriefSource()
    source_data.type = data['type']  # type  engine_search_result
    source_data.job_title = data['jt']  # jt  职位名称
    source_data.tags = data['tag']  # tags
    source_data.ad_track = data['ad_track']  # ad_track
    source_data.jobid = data['job_id']  # jobid 职位标识信息id
    source_data.coid = data['coid']  # coid
    source_data.effect = data['effect']  # ad_track
    source_data.is_special_job = data['is_special_job']  # is_special_job 是否是专业特殊岗位
    source_data.job_href = data['job_href']  # job_href 职位信息链接
    source_data.job_title = data['job_title']  # job_title 高级Java开发
    source_data.job_name = data['job_name']  # job_name 职位名称 高级Java开发
    source_data.company_href = data['company_href']  # company_href 企业介绍信息链接
    source_data.company_name = data['company_name']  # company_name 北明软件有限公司
    source_data.provide_salary_text = data['providesalary_text']  # providesalary_text 1.3-2.6万/月
    source_data.work_area = data['workarea']  # workarea 021000
    source_data.work_area_text = data['workarea_text']  # workarea_text 上海-浦东新区
    source_data.update_date = data['updatedate']  # updatedate  01-29
    source_data.is_intern = data['isIntern']  # isIntern 是否是实习岗位
    source_data.is_communicate = data['iscommunicate']  # iscommunicate 岗位薪资是否面议
    source_data.company_type_text = data['companytype_text']  # companytype_text 上市公司
    source_data.degree_from = data['degreefrom']  # degreefrom 6
    source_data.work_year = data['workyear']  # workyear 5
    source_data.issue_date = data['issuedate']  # issuedate 2021-01-29 18:06:46
    source_data.is_from_xyz = data['isFromXyz']  # isFromXyz
    source_data.jobwelf = data['jobwelf']  # ad_track
    source_data.jobwelf_list = data['jobwelf_list']  # jobwelf 餐饮补贴 免费班车 五险一金 高温补贴 节日福利 年终奖金 加班补贴
    source_data.attribute_text = data['attribute_text']  # attribute_text
    source_data.company_size_text = data['companysize_text']  # companysize_text 500-1000人
    source_data.company_ind_text = data['companyind_text']  # companyind_text 互联网/电子商务
    source_data.adid = data['adid']  # ad_track

    return source_data
