#!/usr/bin/env python
# -*- coding: UTF-8 -*-
"""
@File ：test_page.py
@Author ：frankdevhub@gmail.com
@Blog : http://blog.frankdevhub.site
@Date ：2021/2/25 2:03
"""
import logging as log
import unittest

from frankdevhub_51job_api.bsoup.platform.page import *

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
        log.debug('invoke method -> test_get_page_html_context()')
        pass

    def test_get_previous_page(self):
        """测试获取上一页链接"""
        log.debug('invoke method -> test_get_previous_page()')
        p_url = get_previous_page(test_url)
        log.debug(p_url)

    def test_get_next_page(self):
        """测试获取下一页链接"""
        log.debug('invoke method -> test_get_next_page()')
        n_url = get_next_page(test_url)
        log.debug(n_url)

    def test_get_search_keyword(self):
        log.debug('invoke method -> test_get_search_keyword()')

    def test_get_page_union_id(self):
        """测试获取链接中的唯一标识"""
        log.debug('invoke method -> test_get_page_union_id()')


if __name__ == '__main__':
    test_suite = unittest.TestCase()
    test_suite.addTest(TestPage('test_get_previous_page'))  # test_get_previous_page
    test_suite.addTest(TestPage('test_get_next_page'))  # test_get_next_page
    runner = unittest.TextTestRunner()
    runner.run(test_suite)
