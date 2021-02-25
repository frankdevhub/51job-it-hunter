#!/usr/bin/env python
# -*- coding: UTF-8 -*-
"""
@File ：parser.py
@Author ：frankdevhub@gmail.com
@Blog : http://blog.frankdevhub.site
@Date ：2021/2/18 14:47
"""

import logging as log
import re

from job_api.error.errors import BusinessError
from ..dicts.constants import BusinessConstants

log.basicConfig(level=log.INFO)

__all__ = ['parse_salary_text']

range_regex = "(?P<min>(([1-9]\\d*\\.?\\d+)|(0\\.\\d*[1-9])|(\\d+))?)" + \
              "(?P<hyphen>((—|-)+)?)" + \
              "(?P<max>(([1-9]\\d*\\.?\\d+)|(0\\.\\d*[1-9])|(\\d+))?)" + \
              "(?P<numeric>[\\u4e00-\\u9fa5]?)(/?)(?<date>[\\u4e00-\\u9fa5]?)"


def parse_salary_text(text: str) -> tuple:
    assert text and text.strip() is not None, 'salary text cannot be empty'
    text = text.strip()
    log.info(f'salary text : {text}')

    pattern = re.compile(range_regex, re.M | re.I)
    matched = pattern.match(text)
    if matched:
        pass
    else:
        raise BusinessError(BusinessConstants.SALARY_RANGE_REGEX_MATCH_ERROR)
    return ()
