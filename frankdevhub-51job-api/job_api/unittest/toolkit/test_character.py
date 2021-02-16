# !/usr/bin/env python
# encoding: utf-8
# @author: frankdevhub
# @contact: frankdevhub@gmail.com
# @blog: http://blog.frankdevhub.site
# @file: test_character.py
# @time: 2021/2/16 12:03

import logging as log
import unittest

from job_api.toolkit.character import CharacterHelper

log.basicConfig(level=log.INFO)


class TestCharacterHelper(unittest.TestCase):

    def setUp(self):
        self.characters_example = ('一', '各', '时', '個', '詢', 's', '1', '-')

    def test_is_simple_chinese_character(self):
        """测试是否是简体中文字符"""
        self.setUp()
        log.info(self.characters_example)
        for x in self.characters_example:
            bool_res = CharacterHelper.is_simple_chinese_character(x)
            log.info(f'test rest: {bool_res}')


if __name__ == '__main__':
    test_suite = unittest.TestSuite()
    test_suite.addTest(TestCharacterHelper("test_is_simple_chinese_character"))
    runner = unittest.TextTestRunner()
    runner.run(test_suite)
    # unittest.main()
