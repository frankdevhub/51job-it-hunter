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

from frankdevhub_51job_api.dicts.constants import BusinessConstants
from frankdevhub_51job_api.tools.date import DateUnit
from frankdevhub_51job_api.tools.numeric import NumericUnit
from job_api.error.errors import BusinessError

import json
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
    pass
