#!/usr/bin/env python
# -*- coding: UTF-8 -*-
"""
@File ：test_page.py
@Author ：frankdevhub@gmail.com
@Blog : http://blog.frankdevhub.site
@Date ：2021/2/25 2:03
"""
import unittest
from frankdevhub_51job_api.bsoup.platform.page import *
import logging as log

log.basicConfig(level=log.DEBUG)

test_url = "https://search.51job.com/list/020000,000000,0000,00,9,99,java,2,1.html?" \
           "lang=c" \
           "&postchannel=0000" \
           "&workyear=99" \
           "&cotype=99" \
           "&degreefrom=99" \
           "&jobterm=99" \
           "&companysize=99" \
           "&ord_field=0" \
           "&dibiaoid=0" \
           "&line=&welfare="


class TestPage(unittest.TestCase):
    def test_get_page_html_context(self):
        """测试获取页面文档"""
        pass

    def test_get_previous_page(self):
        """测试获取上一页链接"""
        url = get_previous_page(test_url)
        print(url)

    def test_get_next_page(self):
        """测试获取下一页链接"""
        pass

    def test_get_search_keyword(self):
        pass

    def test_get_page_union_id(self):
        """测试获取链接中的唯一标识"""
        pass


if __name__ == '__main__':
    test_suite = unittest.TestCase()
    test_suite.addTest(TestPage('test_get_previous_page'))
    runner = unittest.TextTestRunner()
    runner.run(test_suite)
