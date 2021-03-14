#!/usr/bin/env python
# -*- coding: UTF-8 -*-
"""
@File ：test_models_orm.py
@Author ：frankdevhub@gmail.com
@Blog : http://blog.frankdevhub.site
@Date ：2021/3/12 22:53
"""

import logging as log
import unittest

from frankdevhub_51job_api.bsoup.data.models import *
from frankdevhub_51job_api.bsoup.data.models_orm import *

log.basicConfig(level=log.DEBUG)


class TestModelORM(unittest.TestCase):

    def test_get_session(self):
        log.debug('invoke method -> test_get_session()')
        session = get_session()
        log.debug(f'session = {session}')
        assert session is not None, f'session cannot be empty'

    def test_query_count(self):
        log.debug('invoke method -> test_query_count()')
        session = get_session()
        query = session.query(PlatformDataBriefSource.id)
        rows = query.count()
        log.debug(f'query params = PlatformDataBriefSource, result rows = {rows}')


if __name__ == '__main__':
    test_suite = unittest.TestSuite()
    test_suite.addTest(TestModelORM('test_get_session'))  # test_get_session
    test_suite.addTest(TestModelORM('test_query_count'))  # test_query_count
    runner = unittest.TextTestRunner()
    runner.run(test_suite)
