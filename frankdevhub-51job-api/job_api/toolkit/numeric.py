#!/usr/bin/env python
# -*- coding: UTF-8 -*-
"""
@File ：numeric.py
@Author ：frankdevhub@gmail.com
@Blog : http://blog.frankdevhub.site
@Date ：2021/2/18 14:51
"""

from enum import Enum, unique


@unique
class NumericUnit(Enum):
    """ 通用计量单位枚举类"""

    def __new__(cls, unit: chr):
        cls._is_capital, cls._is_en, cls._is_tw, cls._is_cn, cls._unit = unit

    @property
    def unit(self):
        return self._unit

    @unit.getter
    def unit(self, value):
        pass

    @property
    def is_capital(self):
        return self.is_capital

    @unit.getter
    def is_capital(self, value):
        pass

    @property
    def is_en(self):
        return self.is_en

    @unit.getter
    def is_en(self, value):
        pass

    @property
    def is_tw(self):
        return self.is_en

    @unit.getter
    def is_tw(self, value):
        pass

    @property
    def is_cn(self):
        return self.is_en

    @unit.getter
    def is_cn(self, value):
        pass
