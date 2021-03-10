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
        page_context = get_page_html_context(test_url)
        log.debug(page_context)

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
        test_dict = (
            "https://search.51job.com/list/020000,000000,0000,00,9,99,java,2,1.html?lang=c&postchannel=0000&workyear"
            "=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&ord_field=0&dibiaoid=0&line=&welfare=",
            "https://search.51job.com/list/020000,000000,0000,00,9,99,Java%2BWeb,2,1.html?lang=c&postchannel"
            "=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&ord_field=0&dibiaoid=0&line=&welfare=")
        for url in test_dict:
            search_keyword = get_search_keyword(url)
            log.debug(f'url = {str(url)}, union id = {str(search_keyword)}')

    def test_get_page_union_id(self):
        """测试获取链接中的唯一标识"""
        log.debug('invoke method -> test_get_page_union_id()')
        test_dict = ("https://jobs.51job.com/shanghai-ptq/121842092.html?s=sou_sou_soulb&t=1",
                     "https://jobs.51job.com/shanghai-pdxq/102334414.html?s=sou_sou_soulb&t=0",
                     "https://jobs.51job.com/shanghai-xhq/129405970.html?s=04&t=17",
                     "https://jobs.51job.com/shanghai/129742323.html?s=04&t=17#"
                     "https://jobs.51job.com/all/co5848907.html",
                     "https://jobs.51job.com/all/co2730245.html",
                     "https://jobs.51job.com/shanghai-ptq/A-901842092.html")
        for url in test_dict:
            union_id = get_page_union_id(url)
            log.debug(f'url = {str(url)}, union id = {str(union_id)}')


if __name__ == '__main__':
    test_suite = unittest.TestCase()
    test_suite.addTest(TestPage("test_get_page_html_context"))  # test_get_page_html_context
    test_suite.addTest(TestPage('test_get_previous_page'))  # test_get_previous_page
    test_suite.addTest(TestPage('test_get_next_page'))  # test_get_next_page
    test_suite.addTest(TestPage('test_get_search_keyword'))  # test_get_search_keyword
    test_suite.addTest(TestPage('test_get_page_union_id'))  # test_get_page_union_id
    runner = unittest.TextTestRunner()
    runner.run(test_suite)
