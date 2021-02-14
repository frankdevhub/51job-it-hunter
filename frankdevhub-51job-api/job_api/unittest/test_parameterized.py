#!/usr/bin/env python
# encoding: utf-8
# @author: frankdevhub
# @contact: frankdevhub@gmail.com
# @blog: http://blog.frankdevhub.site
# @file: test_parameterized.py
# @time: 2021/2/12 16:27
# @desc: parameterized参数化单元测试


import unittest

import xlrd
from parameterized import parameterized


def read_cases(file_name):
    book = xlrd.open_workbook(file_name)
    sh = book.sheet_by_index(0)
    case = []
    for rx in range(sh.nrows):
        item = {}
        for cx in range(sh.ncols):
            if rx != 0:
                item[sh.cell_value(0, cx)] = sh.cell_value(rx, cx)
        if item:
            case.append(item)
    return case


cases = read_cases("cases.xlsx")
print("cases is", cases)


class TestCreateDashboard(unittest.TestCase):

    # @parameterized.expand([param(1, 2), param(3, 4)])
    @parameterized.expand([(1, 2), (3, 4)])
    def test_1(self, num1, num2):
        print("\n num is ", num1, num2)

    # num is 1 2
    # num is 3 4

    @parameterized.expand([{"key1": "value1"}, {"key2": "value2"}])
    def test_21(self, a):
        print("\n a is", a)

    # a is key1
    # a is key2

    # @parameterized.expand([param({"key1": "value1"}, {"key2": "value2"})])
    @parameterized.expand([({"key1": "value1"}, {"key2": "value2"})])
    def test_2(self, a, b):
        print("\n a is", a, "b is", b)

    # a is {'key1': 'value1'} b is {'key2': 'value2'}

    @parameterized.expand(cases)
    def test_3(self, uid, name, b, url, para, bbb, expect):
        print("\n", uid, name, b, url, para, bbb, expect)


if __name__ == "__main__":
    unittest.main()
