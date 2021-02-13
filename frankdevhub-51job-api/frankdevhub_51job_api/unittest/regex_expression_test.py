#!/usr/bin/env python
# encoding: utf-8
# @author: frankdevhub
# @contact: frankdevhub@gmail.com
# @blog: http://blog.frankdevhub.site
# @file: regex_expression_test.py
# @time: 2021/2/13 12:24
# @desc: 解析匹配的正则表达式测试

import re
import unittest

"""企业岗位招聘人数的正则表达式 eg:(招1人)"""
HEAD_COUNT_REGEX = """
.*(?P<prefix>[招聘|招纳|招|需要|急需|需]+)(?P<numeric>[\u4e00-\u9fa5\u767e\u5343\u96f6]+|[0-9]+|[若干])(?P<surfix>人)$
"""

TEST_HEAD_COUNT = " 招纳23人"


class RegexExpressionTest(unittest.TestCase):

    def test_match_head_count(self):
        print('invoke match_head_count')
        print(f'example string = {TEST_HEAD_COUNT}')

        filter_string = re.sub('\\t|\\s|\\n', '', TEST_HEAD_COUNT, re.M | re.I)  # 去除换行符空格符
        print(f'filtered string = {filter_string}')

        pattern = re.compile(HEAD_COUNT_REGEX, re.M | re.I)
        matched = pattern.match(filter_string)
        if matched:
            print(f'prefix = {matched.group("prefix")}')  # prefix
            print(f'numeric = {matched.group("numeric")}')  # numeric
            print(f'surfix = {matched.group("surfix")}')  # surfix
        else:
            print('mot matched')


if __name__ == "__main__":
    unittest.main()
