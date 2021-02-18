#!/usr/bin/env python
# -*- coding: UTF-8 -*-
"""
@File ：district.py
@Author ：frankdevhub@gmail.com
@Blog : http://blog.frankdevhub.site
@Date ：2021/2/18 10:35
"""

from enum import Enum, unique

default_districts = {
    'HP': {'en_name': 'HP', 'cn_name': '黄浦', 'district_code': 310101}}  # 默认上海市各个辖区


@unique
class DefaultDistrict(Enum):
    """默认是上海地区的枚举类"""

    def __new__(cls, name: str):
        instance = object.__new__(cls)
        district = default_districts.get(name)
        instance.en_name = district['en_name']

        instance.cn_name = district['cn_name']
        instance.district_code = district['district_code']
        return instance

    HP = 'HP'
