#!/usr/bin/env python
# encoding: utf-8
# @author: frankdevhub
# @contact: frankdevhub@gmail.com
# @blog: http://blog.frankdevhub.site
# @file: pymsql_test.py
# @time: 2021/2/11 22:39
# @desc: 测试pymsql数据持久化

import unittest

import pymysql


class TestMysql(unittest.TestSuite, object):
    def __init__(self):
        self._host = "39.98.246.50"
        self._username = "root"
        self._password = "frank#0806db@ecs"
        self._db = "51job_data_center"
        self._port = 3306

    @property
    def host(self):
        return self._host

    @property
    def username(self):
        return self._username

    @property
    def password(self):
        return self._password

    @property
    def port(self):
        return self._port


def get_con():
    con = pymysql.connect(host=TestMysql().host,
                          user=TestMysql().username,
                          passwd=TestMysql().password,
                          port=TestMysql().port,
                          cursorclass=pymysql.cursors.DictCursor)
    return con


if __name__ == '__main__':
    suite = unittest.TestSuite()
    suite.addTest()
    runner = unittest.TestRunner()
    runner.run(suite)
