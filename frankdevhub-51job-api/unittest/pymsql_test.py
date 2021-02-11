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


class DbConfig:
    def __init__(self):
        self._host = "39.98.246.50"  # 数据源连接地址
        self._username = "root"  # 数据源连接用户名
        self._password = "frank#0806db@ecs"  # 数据源连接密码
        self._db = "51job_data_center"  # 数据库名称
        self._port = 3306  # 端口号

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


class TestMysql(unittest.TestCase):
    conn = None

    def get_con(self):
        self.con = pymysql.connect(host=DbConfig().host,
                                   user=DbConfig().username,
                                   passwd=DbConfig().password,
                                   port=DbConfig().port,
                                   cursorclass=pymysql.cursors.DictCursor)
        return self.conn


if __name__ == '__main__':
    testunit = unittest.TestSuite()
    testunit.addTest(TestMysql("get_con"))
    runner = unittest.TextTestRunner()
    runner.run(testunit)
