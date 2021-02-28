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
import unittest
import requests
import time
import re
from job_api.error.errors import BusinessError
from frankdevhub_51job_api.dicts.constants import BusinessConstants

log.basicConfig(level=log.INFO)

__all__ = ['get_page_html_context', 'get_previous_page', 'get_next_page', 'get_search_keyword',
           'get_page_union_id']

header = {
    'Connection': 'close',
    'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36'
}


def valid_url(func):
    @functools.wraps(func)
    def wrapper(url_link):
        log.info(f'page link  = {url_link}')
        assert url_link and url_link.strip() is not None, 'page url_link cannot be empty'
        func(url_link)
        return wrapper


@valid_url
def get_page_html_context(url_link: str) -> str:
    """请求获取页面对象的字符串"""
    log.info(f'get_page_html_context, request_url = {url_link}')
    start = time.time()
    response = requests.get(url=url_link, headers=header)
    page_ctx = response.text
    end = time.time()
    log.info(f'time cost: {end - start} Seconds')
    return page_ctx


def get_index(m, is_next: bool):
    if m:
        full_group = m.group()
        group_str = m.group(1)
        index = int(group_str.split('.')[0])
        rest_str = full_group.replace(group_str, '', 1)
        if is_next:
            return str(index - 1) + rest_str
        else:
            return str(index - 1) + rest_str
    else:
        raise BusinessError(f'url regex cannot match page url')


# @valid_url
def get_previous_page(url_link: str) -> str:
    log.info(f'get_previous_page, url_link = {url_link}')
    url_link = re.sub('\\t|\\s|\\n', '', url_link, re.M | re.I)

    expr = "([0-9]+)(.html?)"
    p = re.compile(expr)
    m = p.search(url_link, re.M | re.I)
    p_url = re.sub(p, get_index(m, is_next=False), url_link)

    log.info(f'previous page url_link = {p_url}')
    return p_url


@valid_url
def get_next_page(url_link: str) -> str:
    log.info(f'get_next_page, url_link = {url_link}')
    url_link = re.sub('\\t|\\s|\\n', '', url_link, re.M | re.I)

    expr = "([0-9]+)(.html?)"
    p = re.compile(expr)
    m = p.search(url_link, re.M | re.I)
    n_url = re.sub(p, get_index(m, is_next=True), url_link)

    log.info(f'next page url_link = {n_url}')
    return next_url


@valid_url
def get_search_keyword(url_link: str) -> str:
    return ""


@valid_url
def get_page_union_id(url_link: str) -> str:
    log.info(f'get_page_union_id, url_link = {url_link}')
    expr = BusinessConstants.DEFAULT_HTTP_LINK_MARK_REGEX
    p = pattern.compile(expr)
    m = p.match(url_link, re.M | re.I)
    if m:
        union_id = m.group('key')
    else:
        raise BusinessError(f'cannot match union id url_link = {url_link}')
    log.info(f'union_id = {union_id}')
    return union_id
