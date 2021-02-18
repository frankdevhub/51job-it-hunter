# !/usr/bin/env python
# encoding: utf-8
# @author: frankdevhub
# @contact: frankdevhub@gmail.com
# @blog: http://blog.frankdevhub.site
# @file: test_regex_expression.py
# @time: 2021/2/13 12:24

import logging as log
import re
import unittest

log.basicConfig(level=log.INFO)


class TestRegexExpression(unittest.TestCase):
    HEAD_COUNT_REGEX = ".*(?P<prefix>[招聘|招纳|招|需要|急需|需]+)(?P<numeric>" \
                       "[\u4e00-\u9fa5\u767e\u5343\u96f6]+|[0-9]+|[若干])(?P<surfix>人)$"
    TEST_HEAD_COUNT = " 招纳23人"

    @staticmethod
    def test_match_head_count():
        log.info('invoke method -> match_head_count()')
        log.info(f'example string = {TestRegexExpression.TEST_HEAD_COUNT}')

        filter_string = re.sub('\\t|\\s|\\n', '', TestRegexExpression.TEST_HEAD_COUNT, re.M | re.I)  # 去除换行符空格符
        log.info(f'filtered string = {filter_string}')

        pattern = re.compile(TestRegexExpression.HEAD_COUNT_REGEX, re.M | re.I)
        matched = pattern.match(filter_string)
        if matched:
            log.info(f'prefix = {matched.group("prefix")}')  # prefix
            log.info(f'numeric = {matched.group("numeric")}')  # numeric
            log.info(f'surfix = {matched.group("surfix")}')  # surfix
        else:
            log.info('mot matched')


if __name__ == "__main__":
    test_suite = unittest.TestSuite()
    test_suite.addTest(TestRegexExpression('test_match_head_count'))  # test_match_head_count
    runner = unittest.TextTestRunner()
    runner.run(test_suite)
