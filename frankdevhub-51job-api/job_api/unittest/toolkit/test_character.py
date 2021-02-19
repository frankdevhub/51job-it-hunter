# !/usr/bin/env python
# encoding: utf-8
# @author: frankdevhub
# @contact: frankdevhub@gmail.com
# @blog: http://blog.frankdevhub.site
# @file: test_character.py
# @time: 2021/2/16 12:03

import logging as log
import unittest

from job_api.toolkit.character import CharacterHelper

log.basicConfig(level=log.INFO)


class TestCharacterHelper(unittest.TestCase):
    CHARACTER_EXAMPLE = ('1', '各', '时', '個', '詢', 's', '1', '-')

    # @staticmethod
    # def test_is_simple_chinese_character():
    #     """测试是否是简体中文字符"""
    #     for x in TestCharacterHelper.CHARACTER_EXAMPLE:
    #         log.info(type(x))
    #         bool_res = CharacterHelper.is_simple_chinese_character(x)
    #         log.info(f'test rest: {bool_res}')

    @staticmethod
    def test_cls_reflection_methods():
        """测试获取类的函数成员"""
        # methods = inspect.getmembers(CharacterHelper, inspect.ismethod)
        instance_members = CharacterHelper.__dict__
        log.info(f'instance members: {instance_members}')
        log.info(f'dict keys: {instance_members.keys()}')

        instance_methods = []
        for key in instance_members.keys():
            try:
                m = instance_members.get(key)
                print(m)
                log.info(f'member name: {key}, type: {type(m)}')
                if type(m) == staticmethod:
                    instance_methods.append(m)
            except Exception as e:
                log.error(e)

        # TODO: AttributeError: 'staticmethod' object has no attribute '__code__'
        for method in instance_methods:
            args_count = method.__code__.co_argcount
            log.info(f'args_count : {args_count}')
            args_names = method.__code__.co_varnames
            log.info(f'args_names : {args_names}')


if __name__ == '__main__':
    unittest.main()
