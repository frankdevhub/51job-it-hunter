#!/usr/bin/env python
# -*- coding: UTF-8 -*-
"""
@File ：numeric.py
@Author ：frankdevhub@gmail.com
@Blog : http://blog.frankdevhub.site
@Date ：2021/2/18 14:51
"""
import inspect
import logging as log
from enum import Enum, unique

from job_api.tools.character import CharacterHelper

log.basicConfig(level=log.INFO)


@unique
class NumericUnit(Enum):
    """ 通用计量单位枚举类"""

    def __new__(cls, unit: chr):
        instance = object.__new__(cls)
        instance.unit = unit
        instance_members = [(obj, type(obj)) for (type_name, obj) in inspect.getmembers(CharacterHelper) if
                            inspect.isfunction(obj)]

        for inst in instance_members:
            func = inst[0]
            log.info(f'function_name: {func.__name__} ,arg_count: {func.__code__.co_argcount}')
            fun_name = func.__name__
            if fun_name.lstrip().startswith('is_'):
                bool_res = func(unit)
                assert isinstance(bool_res, bool)
                setattr(instance, func.__name__, bool_res)
                log.info(f'function_name: {func.__name__} ,bool_res: {bool_res}')
        return instance

    Digitis_CN = '个'  # 个位数 简体中文
    Digitis_TW = '個'  # 个位数 繁体中文
    Ten_Digitis_CN_TW = '十'  # 十位数 简体中文
    Hundred_CN = '百'  # 百位数 简体中文
    Hundred_TW = '百'  # 百位数 繁体中文
    Thousand_CN_TW = '千'  # 千位数 繁体中文
    Thousand_EN = 'K'  # 千位数 英文字符
    Ten_Thousand_CN = '万'  # 万位数 中文简体
    Ten_Thousand_TW = '萬'  # 万位数 繁体中文
    Ten_Thousand_EN = 'W'  # 万位数 英文字符
