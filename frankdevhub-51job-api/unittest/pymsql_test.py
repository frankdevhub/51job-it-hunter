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

    @property
    def db(self):
        return self._db


class TestMysql(unittest.TestCase):
    conn = None

    def get_con(self):
        try:
            print("get connection")
            self.conn = pymysql.connect(host=DbConfig().host,
                                        user=DbConfig().username,
                                        passwd=DbConfig().password,
                                        port=DbConfig().port,
                                        db=DbConfig().db,
                                        cursorclass=pymysql.cursors.DictCursor)
            print(f"database connected, host = {DbConfig.host}")
        except pymysql.MySQLError as error:
            print(error)

        return self.conn

    def get_data_count(self):
        query_sql = 'select count(*) from platform_data_brief_source'
        try:
            con = self.get_con()
            with con.cursor() as cursor:
                cursor.execute(query_sql)
                res = cursor.fetchone()
                cursor.close()
                con.commit()
                con.close()
            print(f"query result = {res['count(*)']}")
        except pymysql.MySQLError as error:
            print(error)


if __name__ == '__main__':
    testunit = unittest.TestSuite()
    # testunit.addTest(TestMysql("get_con"))  # get_con
    testunit.addTest(TestMysql("get_data_count"))  # get_data_count
    runner = unittest.TextTestRunner()
    runner.run(testunit)
