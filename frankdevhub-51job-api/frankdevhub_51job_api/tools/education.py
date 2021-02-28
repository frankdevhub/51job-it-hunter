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
        instance.name = args['name']
        instance.code = args['code']
        return instance


DOCTOR = {'name': '博士', 'code': 'DOCTOR'}
MASTER = {'name': '硕士研究生', 'code': 'MASTER'}
BACHELOR = {'name': '本科', 'code': 'BACHELOR'}
TACHNICAL_COLLEGE = {'name': '中专', 'code': 'TACHNICAL_COLLEGE'}
