#!/usr/bin/env python
# -*- coding: UTF-8 -*-
"""
@File ：date.py
@Author ：frankdevhub@gmail.com
@Blog : http://blog.frankdevhub.site
@Date ：2021/2/18 10:13
"""

from enum import Enum, unique

date_units = {'day_1': '天', 'day_2': '日', 'month': '月', 'year': '年'}


@unique
class DateUnit(Enum):
    """通用时间单位"""
    def __new__(cls, name: str):
        instance = object.__new__(cls)
        instance.unit = date_units.get(name)
        return instance

    DAY_1 = 'day_1'
    DAY_2 = 'day_2'
    MONTH = 'month'
    YEAR = 'year'
