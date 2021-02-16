#!/usr/bin/env python
# encoding: utf-8
# @author: frankdevhub
# @contact: frankdevhub@gmail.com
# @blog: http://blog.frankdevhub.site
# @file: character.py
# @time: 2021/2/15 20:50

import re


class CharacterHelper:
    CN_CHARACTERS = "[\\u4E00-\\u9FA5]"  # 中文字符
    EN_CHARACTERS = "[a-zA-Z]"  # 英文字符
    EN_CAPITAL_CHARACTERS = "[A-Z]"  # 大写英文字符
    NUMERIC_CHARACTERS = "[0-9]"  # 数值类字符
    SYMBOL_CHARACTERS = "[a-zA-Z0-9\\u4E00-\\u9FA5]"  # 符号类字符


def character_pattern_match(target, expression):
    """匹配字符对象"""
    pattern = re.compile(expression)
    matched = pattern.search(target, re.M | re.I)
    if matched:
        return True
    else:
        return False


def is_chinese_character(self, target):
    """判断是否是中文字符"""
    pass


def is_simple_chinese_character(self, target):
    """判断是否是简体中文字符"""
    pass


def is_taiwanese_character(self, target):
    """判断是否是繁体中文字符"""
    pass


def is_english_character(self, target):
    """判断是否是英文字符"""
    pass


def is_english_capital_character(self, target):
    """判断是否是英文大写字符"""
    pass


def is_numeric_character(self, target):
    """判断是否是数值类字符"""
    matched = character_pattern_match(target, self.NUMERIC_CHARACTERS)
    return matched


def is_symbol_character(self, target):
    """判断是否是符号类字符"""
    pass
