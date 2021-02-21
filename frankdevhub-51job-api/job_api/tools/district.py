#!/usr/bin/env python
# -*- coding: UTF-8 -*-
"""
@File ：district.py
@Author ：frankdevhub@gmail.com
@Blog : http://blog.frankdevhub.site
@Date ：2021/2/18 10:35
"""

from enum import Enum, unique

# 默认上海市各个辖区
default_districts = {
    'HP': {'en_name': 'HP', 'cn_name': '黄浦', 'district_code': 310101},
    'SJ': {'en_name': 'SJ', 'cn_name': '松江', 'district_code': 310102},
    'LW': {'en_name': 'LW', 'cn_name': '卢湾', 'district_code': 310103},
    'QP': {'en_name': 'QP', 'cn_name': '青浦', 'district_code': 310118},
    'XH': {'en_name': 'XH', 'cn_name': '徐汇', 'district_code': 310104},
    'NH': {'en_name': 'NH', 'cn_name': '南汇', 'district_code': 310119},
    'CN': {'en_name': 'CN', 'cn_name': '长宁', 'district_code': 310105},
    'FX': {'en_name': 'FX', 'cn_name': '奉贤', 'district_code': 310120},
    'JA': {'en_name': 'JA', 'cn_name': '静安', 'district_code': 310106},
    'CS': {'en_name': 'CS', 'cn_name': '川沙', 'district_code': 310152},
    'PT': {'en_name': 'PT', 'cn_name': '普陀', 'district_code': 310107},
    'CM': {'en_name': 'CM', 'cn_name': '崇明', 'district_code': 310230},
    'ZB': {'en_name': 'ZB', 'cn_name': '闸北', 'district_code': 310108},
    'HK': {'en_name': 'HK', 'cn_name': '虹口', 'district_code': 310109},
    'YP': {'en_name': 'YP', 'cn_name': '杨浦', 'district_code': 310110},
    'MH': {'en_name': 'MH', 'cn_name': '闵行', 'district_code': 310112},
    'BS': {'en_name': 'BS', 'cn_name': '宝山', 'district_code': 310113},
    'JD': {'en_name': 'JD', 'cn_name': '嘉定', 'district_code': 310114},
    'PD': {'en_name': 'PD', 'cn_name': '浦东', 'district_code': 310115},
    'JS': {'en_name': 'JS', 'cn_name': '金山', 'district_code': 310116}}


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

    HP = 'HP'  # 上海市-黄浦区
    SJ = 'SJ'  # 上海市-松江区
    LW = 'LW'  # 上海市-卢湾区
    QP = 'QP'  # 上海市-青浦区
    XH = 'XH'  # 上海市-徐汇区
    NH = 'NH'  # 上海市-南汇区
    CN = 'CN'  # 上海市-长宁区
    FX = 'FX'  # 上海市-奉贤区
    JA = 'JA'  # 上海市-静安区
    CS = 'CS'  # 上海市-川沙区
    PT = 'PT'  # 上海市-普陀区
    CM = 'CM'  # 上海市-崇明区
    ZB = 'ZB'  # 上海市-闸北区
    HK = 'HK'  # 上海市-虹口区
    YP = 'YP'  # 上海市-杨浦区
    MH = 'MH'  # 上海市-闵行区
    BS = 'BS'  # 上海市-宝山区
    JD = 'JD'  # 上海市-嘉定区
    PD = 'PD'  # 上海市-浦东新区
    JS = 'JS'  # 上海市-金山区
