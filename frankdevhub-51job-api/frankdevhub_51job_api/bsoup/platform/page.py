#!/usr/bin/env python
# -*- coding: UTF-8 -*-
"""
@File ：page.py
@Author ：frankdevhub@gmail.com
@Blog : http://blog.frankdevhub.site
@Date ：2021/2/24 23:11
"""
import json
import logging as log
import re
import time
from functools import wraps

import requests

from frankdevhub_51job_api.bsoup.platform import parser
from frankdevhub_51job_api.dicts.constants import BusinessConstants
from job_api.error.errors import BusinessError

log.basicConfig(level=log.INFO)

__all__ = ['valid_url', 'get_page_html_context', 'get_previous_page', 'get_next_page', 'get_search_keyword',
           'get_page_union_id', 'get_search_list', 'get_search_list']

header = {
    'Connection': 'close',
    'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36'
}


def valid_url(f):
    @wraps(f)
    def decorated(*args, **kwargs):
        # 参数长度校验
        assert len(args) == 1, f'invalid argument length'
        url_link = args[0]
        # 参数类型校验
        assert type(url_link) is str, f'invalid argument type, {str(url_link)}'
        log.info(f'page link  = {url_link}')
        # 链接非空校验
        assert url_link.isspace() is not True, f'page link cannot be empty'
        return f(*args, **kwargs)

    return decorated


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
    """
    获取页面链接中的页数相关的字段
     eg:
       input:
       matched_url = https://search.51job.com/list/020000,000000,0000,00,9,99,java,2,1.html?
                      lang=c&postchannel=0000&workyear=99&cotype=99&degreefrom=99&
                      jobterm=99&companysize=99&ord_field=0&dibiaoid=0&line=&welfare=
       is_next = True
       output = 2
    @param m: 正则返回的匹配结果集合
    @param is_next: 是否下一页, 是 = True 否 = False
    @return: 上一页或下一页的页面下标索引
    @rtype: str
    """
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


@valid_url
def get_previous_page(url_link: str) -> str:
    """
    获取上一页链接地址
    eg:
       input = https://search.51job.com/list/020000,000000,0000,00,9,99,java,2,1.html?
               lang=c&postchannel=0000&workyear=99&cotype=99&degreefrom=99&
               jobterm=99&companysize=99&ord_field=0&dibiaoid=0&line=&welfare=
       output = https://search.51job.com/list/020000,000000,0000,00,9,99,java,2,0.html?
                lang=c&postchannel=0000&workyear=99&cotype=99&degreefrom=99&
                jobterm=99&companysize=99&ord_field=0&dibiaoid=0&line=&welfare=

    @param url_link: 当前页面的链接地址
    @return: 上一页链接地址
    @rtype: str
    """
    log.info(f'get_previous_page, url_link = {url_link}')
    url_link = re.sub('\\t|\\s|\\n', '', url_link, re.M | re.I)

    expr = "([0-9]+)(.html?)"
    p = re.compile(expr)
    m = p.search(url_link, re.M | re.I)
    p_url = re.sub(p, get_index(m, is_next=False), url_link)

    assert p_url.isspace() is not True
    log.info(f'previous page url_link = {p_url}')
    return p_url


@valid_url
def get_next_page(url_link: str) -> str:
    """
    获取上一页链接地址
    eg:
       input = https://search.51job.com/list/020000,000000,0000,00,9,99,java,2,1.html?
               lang=c&postchannel=0000&workyear=99&cotype=99&degreefrom=99&
               jobterm=99&companysize=99&ord_field=0&dibiaoid=0&line=&welfare=
       output = https://search.51job.com/list/020000,000000,0000,00,9,99,java,2,2.html?
                lang=c&postchannel=0000&workyear=99&cotype=99&degreefrom=99&
                jobterm=99&companysize=99&ord_field=0&dibiaoid=0&line=&welfare=

    @param url_link: 当前页面的链接地址
    @return: 下一页链接地址
    @rtype: str
    """
    log.info(f'get_next_page, url_link = {url_link}')
    url_link = re.sub('\\t|\\s|\\n', '', url_link, re.M | re.I)

    expr = "([0-9]+)(.html?)"
    p = re.compile(expr)
    m = p.search(url_link, re.M | re.I)
    n_url = re.sub(p, get_index(m, is_next=True), url_link)

    assert n_url.isspace() is not True
    log.info(f'next page url_link = {n_url}')
    return next_url


@valid_url
def get_search_keyword(url_link: str) -> str:
    """
    获取搜索链接中的职位搜索关键字
    eg:
       input = https://search.51job.com/list/020000,000000,0000,00,9,99,java,2,1.html?
               lang=c&postchannel=0000&workyear=99&cotype=99&degreefrom=99&
               jobterm=99&companysize=99&ord_field=0&dibiaoid=0&line=&welfare=
       output = java

    @param url_link: 当前页面的链接地址
    @return: 链接中的搜索关键字
    @rtype: str
    """
    log.info(f'get_search_keyword, url_link = {url_link}')
    expr = BusinessConstants.DEFAULT_HTTP_LINK_MARK_REGEX
    p = pattern.compile(expr)
    m = p.match(url_link, re.M | re.I)
    if m:
        key_word = m.group(1)
        assert key_word.isspace() is False, 'search key word cannot be empty'
        log.info(f'matched search key word = {key_word}')
    else:
        raise BusinessError(f'cannot match search keyword, url_link = {url_link}')
    return key_word


@valid_url
def get_page_union_id(url_link: str) -> str:
    """
    获取页面链接中的唯一标识
    eg:
       input = https://jobs.51job.com/shanghai-ptq/121842092.html?s=sou_sou_soulb&t=1
       output = 121842092

    @param url_link: 页面链接
    @return: 页面链接中的唯一标识
    @rtype: str
    """
    log.info(f'get_page_union_id, url_link = {url_link}')
    expr = BusinessConstants.DEFAULT_HTTP_LINK_MARK_REGEX
    p = pattern.compile(expr)
    m = p.match(url_link, re.M | re.I)
    if m:
        union_id = m.group('key')
        assert union_id.isspace() is False, 'union id cannot be empty'
        log.info(f'matched union id = {union_id}')
    else:
        raise BusinessError(f'cannot match union id, url_link = {url_link}')
    return union_id


def get_search_list(url_link: str) -> []:
    """解析职位搜索的返回列表页"""
    ctx = get_page_html_context(url_link)
    assert len(ctx.strip()) > 0, 'page context cannot be empty'
    ctx = ctx.sub('\\n', '', re.M | re.I)
    # 源码:window.__SEARCH_RESULT__ = 句柄处开始
    json_regex = 'window.__SEARCH_RESULT__\\s?=\\s?(?<context>\\{.*\\})</script>'
    p = pattern.compile(json_regex)
    m = p.match(ctx, re.M | re.I)
    if m:
        json_str = m.group('context')
        # 页面返回的职位列表信息的json格式数组对象
        assert json_str.isspace() is not True, 'cannot find data json from matched context'
        log.info(f'json_str = {json_str}')
    else:
        raise BusinessError(f'cannot data json from current page context')

    data_json = json.load(json_str)
    log.info(f'parse data_json')
    data_rows = parser.convert_context(data_json)
    log.info(f'parsed data_rows size = {len(data_rows)}')

    return data_rows