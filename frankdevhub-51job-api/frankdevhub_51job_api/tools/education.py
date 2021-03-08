#!/usr/bin/env python
# -*- coding: UTF-8 -*-
"""
@File ：education.py
@Author ：frankdevhub@gmail.com
@Blog : http://blog.frankdevhub.site
@Date ：2021/2/19 19:22
"""

from enum import Enum, unique


@unique
class EducationDegree(Enum):
    """教育学历文化水平"""

    def __new__(cls, args):
        instance = object.__new__(cls)
        instance.name = args['name']  # 学历中文名称
        instance.code = args['code']  # 学历英文代码
        return instance


DOCTOR = {'name': '博士', 'code': 'doctor'}
MASTER = {'name': '硕士研究生', 'code': 'master'}
BACHELOR = {'name': '本科', 'code': 'bachelor'}
TACHNICAL_COLLEGE = {'name': '中专', 'code': 'technical_college'}
