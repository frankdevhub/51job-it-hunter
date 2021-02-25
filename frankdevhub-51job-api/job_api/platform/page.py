#!/usr/bin/env python
# -*- coding: UTF-8 -*-
"""
@File ：page.py
@Author ：frankdevhub@gmail.com
@Blog : http://blog.frankdevhub.site
@Date ：2021/2/24 23:11
"""
import functools
import logging as log

log.basicConfig(level=log.INFO)

__all__ = ['get_page_html_context', 'get_previous_page', 'get_next_page', 'get_search_keyword',
           'get_page_link_union_id']


def valid_url(func):
    @functools.wraps(func)
    def wrapper(url):
        log.info(f'page link  = {url}')
        assert url and url.strip() is False, "page url cannot be empty"
        func(url)
        return wrapper


@valid_url
def get_page_html_context(url: str) -> str:
    """请求获取页面对象的字符串"""

    return ""


@valid_url
def get_previous_page(url: str) -> str:
    return ""


@valid_url
def get_next_page(url: str) -> str:
    return ""


@valid_url
def get_search_keyword(url: str) -> str:
    return ""


@valid_url
def get_page_link_union_id(url: str) -> str:
    return ""
