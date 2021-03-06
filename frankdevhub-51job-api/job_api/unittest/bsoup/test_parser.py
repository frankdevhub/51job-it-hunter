#!/usr/bin/env python
# -*- coding: UTF-8 -*-
"""
@File ：test_parser.py
@Author ：frankdevhub@gmail.com
@Blog : http://blog.frankdevhub.site
@Date ：2021/2/26 1:17
"""

import json
import logging as log
import unittest

from frankdevhub_51job_api.tools.parser import *

log.basicConfig(level=log.INFO)


class TestParser(unittest.TestCase):
    """
    2-3万/月, 测试整数
    2——7万/年, 多个破折号作为间隔
    2——7万/年, 多个破折号作为间隔
    23.9-3万/月", 小数与整数
    12000-15000/月, 默认各位不显示单位的区间范围
    23.0-334.98, 默认没有任何单位显示的区间范围
    """
    range_texts = ['2-3万/月', '2——7万/年', '23.9-3万/月', '12000-15000/月', '23.0-334.98']

    def test_parse_salary_text(self):
        """测试解析薪资描述的字符串"""
        log.debug('invoke method -> test_parse_salary_text()')
        for text in TestParser.range_texts:
            log.debug(f'text : {text}')
            res = parse_salary_text(text)
            for value in res:
                log.debug(value)

    def test_parse_json_data(self):
        """解平台返回的职位信息"""
        log.debug('invoke method -> test_parse_json_data()')
        with open('search-result-2021.json', 'r') as f:
            log.debug(f'load data json, filename = {f.name}')
            test_json = json.load(f)

        assert test_json is not None, f'cannot find test_json, filename = {f.name}'
        # top_ads
        top_ads = test_json['test_json']
        log.debug(f'top_ads = {top_ads}, is None = {top_ads is None}')


if __name__ == '__main__':
    test_suite = unittest.TestSuite()
    test_suite.addTest(TestParser('test_parse_salary_text'))  # test_parse_salary_text
    test_suite.addTest(TestParser('test_parse_json_data'))  # test_parse_json_data
    runner = unittest.TextTestRunner()
    runner.run(test_suite)
