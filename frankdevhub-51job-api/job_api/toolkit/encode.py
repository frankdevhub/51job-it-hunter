#!/usr/bin/env python
# -*- coding: UTF-8 -*-
"""
@File ：encode.py
@Author ：frankdevhub@gmail.com
@Blog : http://blog.frankdevhub.site
@Date ：2021/2/16 20:49
"""

from enum import Enum, unique


@unique
class CharacterEncode(Enum):
    """通用国际字符编码"""

    def __new__(cls, name: str):
        instance = object.__new__(cls)
        instance.code_name = name
        return instance

    GB2312 = 'GB2312'
    ASCII = 'ASCII'
    MBCS = 'MBCS'
    GBK = 'GBK'
    Big5 = 'Big5'
    Unicode = 'Unicode',
    UTF8 = 'UTF8',
    Base64 = 'Base64'
