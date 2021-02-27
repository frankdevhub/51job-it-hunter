#!/usr/bin/env python
# -*- coding: UTF-8 -*-
"""
@File ：test_bsoup.py
@Author ：frankdevhub@gmail.com
@Blog : http://blog.frankdevhub.site
@Date ：2021/2/21 17:42
"""
import requests
import logging as log
import unittest

from lxml import etree
from bs4 import BeautifulSoup

log.basicConfig(level=log.DEBUG)

test_headers = {
    'Connection': 'close',
    'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36'
}

test_url = "https://search.51job.com/list/020000,000000,0000,00,9,99,java,2,1.html?" \
           "lang=c&postchannel=0000&" \
           "workyear=99&cotype=99&" \
           "degreefrom=99&" \
           "jobterm=99&" \
           "companysize=99&" \
           "ord_field=0&" \
           "dibiaoid=0&" \
           "line=&welfare="


class TestBeautifulSoup(unittest.TestCase):

    @staticmethod
    def test_local():
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
        # soup = BeautifulSoup(open('index.html'))  # 使用本地文件创建对象
        # print(soup.prettify())

    @staticmethod
    def test_get_html_page():
        response = requests.get(url=test_url, headers=test_headers)
        page_context = response.text
        # print(page_context)
        tree = etree.HTML(page_context)
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
        print(f'header_tags size: {len(header_tags)}')
        assert len(header_tags) > 0
        header_tag = header_tags[0]
        print(f'tag_name: {header_tag.tag}')

        sub_tags = header_tag.xpath("a")
        print(sub_tags)
        assert len(sub_tags) > 0
        print(f'sub_tags size: {len(sub_tags)}')

        """获取链接地址"""
        for a_href in sub_tags:
            inner_text = a_href.xpath("string(.)")
            print(f'text = {inner_text}, href = {a_href.attrib.get("href")}')


if __name__ == '__main__':
    test_suite = unittest.TestSuite()
    # test_suite.addTest(TestBeautifulSoup('test_local'))
    test_suite.addTest(TestBeautifulSoup('test_get_html_page'))
    runner = unittest.TextTestRunner()
    runner.run(test_suite)
