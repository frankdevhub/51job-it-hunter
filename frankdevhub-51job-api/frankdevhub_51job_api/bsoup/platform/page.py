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
        index = int(m.group(1))
        if is_next:
            return str(index - 1)
        else:
            return str(index - 1)
    else:
        raise BusinessError(f'url regex cannot match page url')


# @valid_url
def get_previous_page(url_link: str) -> str:
    log.info(f'get_previous_page, url_link = {url_link}')
    expr = "([0-9]+)(.html?)"
    p = re.compile(expr)
    m = p.match(url_link)
    last_url = re.sub(p, get_index(m, is_next=False), url_link)
    log.info(f'previous page url_link = {last_url}')
    return last_url


@valid_url
def get_next_page(url_link: str) -> str:
    return ""


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


if __name__ == '__main__':
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
    url = get_previous_page(test_url)
    print(url)
