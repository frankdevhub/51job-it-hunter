#!/usr/bin/env python
# encoding: utf-8
# @author: frankdevhub
# @contact: frankdevhub@gmail.com
# @blog: http://blog.frankdevhub.site
# @file: regex_expression_test.py
# @time: 2021/2/13 12:24
# @desc: 解析匹配的正则表达式测试

import unittest

"""企业岗位招聘人数的正则表达式 eg:(招1人)"""
HEAD_COUNT_REGEX = """
.*(?<prefix>[招聘|招纳|招|需要|急需|需]+)(?<numeric>[\u4e00-\u9fa5\u767e\u5343\u96f6]+|[0-9]+|[若干])(?<surfix>人)$
"""


class TestRegexExpression(unittest.TestCase):
    pass


if __name__ == "__main__":
    testunit = unittest.TestSuite()
    testunit.addTest(TestRegexExpression('parse_head_count'))
    runner = unittest.TextTestRunner()
    runner.run(testunit)
