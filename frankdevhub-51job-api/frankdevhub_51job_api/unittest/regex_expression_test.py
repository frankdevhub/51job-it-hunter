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

TEST_HEAD_COUNT = "招 23 人"


class RegexExpressionTest(unittest.TestCase):

    @staticmethod
    def test_match_head_count():
        print('invoke match_head_count')
        matched = re.match(HEAD_COUNT_REGEX, TEST_HEAD_COUNT, re.M | re.I)
        if matched:
            print("prefix :", matched.group('prefix'))
        else:
            print('no matched')


if __name__ == "__main__":
    unittest.main()
