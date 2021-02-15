#!/usr/bin/env python
# encoding: utf-8
# @author: frankdevhub
# @contact: frankdevhub@gmail.com
# @blog: http://blog.frankdevhub.site
# @file: test_regex_expression.py
# @time: 2021/2/13 12:24
# @desc: 解析匹配的正则表达式测试

import logging
import re
import unittest


class TestRegexExpression(unittest.TestCase):

    def setUp(self):
        """企业岗位招聘人数的正则表达式 eg:(招1人)"""
        self.HEAD_COUNT_REGEX = ".*(?P<prefix>[招聘|招纳|招|需要|急需|需]+)(?P<numeric>" \
                                "[\u4e00-\u9fa5\u767e\u5343\u96f6]+|[0-9]+|[若干])(?P<surfix>人)$"
        self.TEST_HEAD_COUNT = " 招纳23人"

    def teardown(self):
        pass

    def test_match_head_count(self):
        logging.Info('invoke method -> match_head_count()')
        logging.Info(f'example string = {self.TEST_HEAD_COUNT}')

        filter_string = re.sub('\\t|\\s|\\n', '', self.TEST_HEAD_COUNT, re.M | re.I)  # 去除换行符空格符
        logging.Info(f'filtered string = {filter_string}')

        pattern = re.compile(self.HEAD_COUNT_REGEX, re.M | re.I)
        matched = pattern.match(filter_string)
        if matched:
            logging.Info(f'prefix = {matched.group("prefix")}')  # prefix
            logging.Info(f'numeric = {matched.group("numeric")}')  # numeric
            logging.Info(f'surfix = {matched.group("surfix")}')  # surfix
        else:
            logging.Info('mot matched')


if __name__ == "__main__":
    testunit = unittest.TestSuite()
    testunit.addTest(TestRegexExpression("test_match_head_count"))  # test_match_head_count
    runner = unittest.TextTestRunner()
    runner.run(testunit)
