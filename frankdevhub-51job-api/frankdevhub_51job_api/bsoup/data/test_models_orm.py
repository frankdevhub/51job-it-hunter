#!/usr/bin/env python
# -*- coding: UTF-8 -*-
"""
@File ：test_models_orm.py
@Author ：frankdevhub@gmail.com
@Blog : http://blog.frankdevhub.site
@Date ：2021/3/12 22:53
"""

import logging as log

log.basicConfig(level=log.DEBUG)


class TestModelORM(TestCase.unittest):
    def test_get_session(self):
        self.fail()

    def test_get_engine(self):
        self.fail()


if __name__ == '__main__':
    test_suite = unittest.TestSuite()
    test_suite.addTest(TestModelORM('test_get_session'))  # test_get_session
    test_suite.addTest(TestModelORM('test_get_engine'))  # test_get_engine
