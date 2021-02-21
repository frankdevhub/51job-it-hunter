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

from bs4 import BeautifulSoup

log.basicConfig(level=log.INFO)


class TestBeautifulSoup(unittest.TestCase):

    def test_local(self):
        """
         测试基础功能项 demo: https://www.cnblogs.com/scios/p/8652760.html
        """
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
        print(soup.prettify())


if __name__ == '__main__':
    test_suite = unittest.TestSuite()
    test_suite.addTest(TestBeautifulSoup("test_local"))
    runner = unittest.TextTestRunner()
    runner.run(test_suite)
