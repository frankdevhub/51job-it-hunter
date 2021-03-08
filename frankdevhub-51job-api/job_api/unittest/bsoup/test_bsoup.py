#!/usr/bin/env python
# -*- coding: UTF-8 -*-
"""
@File ：test_bsoup.py
@Author ：frankdevhub@gmail.com
@Blog : http://blog.frankdevhub.site
@Date ：2021/2/21 17:42
"""
import logging as log
import unittest

import requests
from bs4 import BeautifulSoup
from lxml import etree

log.basicConfig(level=log.DEBUG)

test_headers = {
    'Connection': 'close',
    'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36'
}

# 上海地区职位搜索返回结果集的测试链接
test_search_url = "https://search.51job.com/list/020000,000000,0000,00,9,99,java,2,1.html?" \
                  "lang=c&postchannel=0000&" \
                  "workyear=99&cotype=99&" \
                  "degreefrom=99&" \
                  "jobterm=99&" \
                  "companysize=99&" \
                  "ord_field=0&" \
                  "dibiaoid=0&" \
                  "line=&welfare="
# 企业信息介绍测试链接
test_company_url = ""


class TestBeautifulSoup(unittest.TestCase):

    @staticmethod
    def test_local():
        """测试本地运行环境"""
        log.debug('invoke method -> test_local()')
        html = """
        <html><head><title>The Dormouse's story</title></head>
        <body>
        <p class="title" name="dromouse"><b>The Dormouse's story</b></p>
        <p class="story">Once upon a time there were three little sisters; and their names were
        <a href="http://example.com/elsie" class="sister" id="link1"><!-- Elsie --></a>,
        <a href="http://example.com/lacie" class="sister" id="link2">Lacie</a> and
        <a href="http://example.com/tillie" class="sister" id="link3">Tillie</a>;
        and they lived at the bottom of a well.</p>
        <p class="story">...</p>
        """
        soup = BeautifulSoup(html)
        assert soup is not None, 'soup cannot be empty'
        # soup = BeautifulSoup(open('index.html'))  # 使用本地文件创建对象
        # log.debug(soup.prettify())

    @staticmethod
    def test_get_html_page():
        """测试解析基础页面对象，使用xpath定位元素"""
        log.debug('invoke method -> test_get_html_page()')
        response = requests.get(url=test_search_url, headers=test_headers)
        page_context = response.text
        # log.debug(page_context)
        tree = etree.HTML(page_context)
        assert tree is not None, 'xml tree cannot be empty'
        """测试Xpath
        <p class="nlink">
            <a class="" href="//www.51job.com/">首页</a>
            <a class="on" href="https://search.51job.com">职位搜索</a>
            <a class="" href="javascript:openAreaChannelLayer();">地区频道</a>
            <a class="" href="https://edu.51job.com" target="_blank">无忧学院</a>
            <a class="" href="https://mkt.51job.com/careerpost/default_res.php">职场资讯</a>
            <a class="" href="https://xy.51job.com/default-xs.php">校园招聘</a>
            <a href="http://my.51job.com/my/gojingying.php?direct=https%3A%2F%2Fwww.51jingying.com%2Fcommon%2Fsearchcase.php%3F5CC4CE%3D1008" target="_blank">无忧精英</a>
        </p>        </div>
            </div> 
        """
        header_tags = tree.xpath("//p[@class='nlink']")
        assert len(header_tags) > 0, 'header_tags cannot be empty'
        log.debug(f'header_tags size = {len(header_tags)}')
        header_tag = header_tags[0]
        log.debug(f'tag_name = {header_tag.tag}')

        sub_tags = header_tag.xpath("a")
        assert len(sub_tags) > 0, 'sub_tags cannot be empty'
        log.debug(f'sub_tags =  {sub_tags}')
        log.debug(f'sub_tags size = {len(sub_tags)}')

        """获取链接地址"""
        for a_href in sub_tags:
            inner_text = a_href.xpath("string(.)")
            log.debug(f'text = {inner_text}, href = {a_href.attrib.get("href")}')

    @staticmethod
    def test_get_company_page():
        """测获取职位信息对应企业的介绍信息"""
        log.debug('invoke method -> test_get_company_page()')
        response = requests.get(url=test_company_url, headers=test_headers)
        page_context = response.text
        # log.debug(page_context)
        tree = etree.HTML(page_context)
        assert tree is not None, 'xml tree cannot be empty'
        company_xpath = "//div[@class='tCompany_center clearfix']"
        company_xml = tree.xpath(company_xpath)
        assert company_xml is not None, f'company division cannot be locate, xpath = {str(company_xpath)}'
        company_doc = company_xml.xpath('substring(.)')
        log.debug(company_doc)


if __name__ == '__main__':
    test_suite = unittest.TestSuite()
    test_suite.addTest(TestBeautifulSoup('test_local'))  # test_local
    test_suite.addTest(TestBeautifulSoup('test_get_html_page'))  # test_get_html_page
    runner = unittest.TextTestRunner()
    runner.run(test_suite)
