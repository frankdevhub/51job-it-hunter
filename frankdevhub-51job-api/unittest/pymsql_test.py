#!/usr/bin/env python
# encoding: utf-8
# @author: frankdevhub
# @contact: frankdevhub@gmail.com
# @blog: http://blog.frankdevhub.site
# @file: pymsql_test.py
# @time: 2021/2/11 22:39
# @desc: 测试pymsql数据持久化


import unittest

import parameterized
import pymysql

GET_SOURCE_DATA_COUNT = """
select count(*) as total from platform_data_brief_source
"""
GET_SOURCE_DATA_BY_COMPANY = """
select * from platform_data_brief_source where company_name like %s limit %s,%s
"""


# 数据库连接配置
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

    def get_conn(self):
        try:
            print("get connection")
            self.conn = pymysql.connect(host=DbConfig().host,
                                        user=DbConfig().username,
                                        passwd=DbConfig().password,
                                        port=DbConfig().port,
                                        db=DbConfig().db,
                                        cursorclass=pymysql.cursors.DictCursor)
            print(f"database connected, host = {str(DbConfig.host)}")
        except pymysql.MySQLError as error:
            print(error)

        return self.conn

    def get_source_data_count(self):
        query_sql = GET_SOURCE_DATA_COUNT
        try:
            conn = self.get_conn()
            with conn.cursor() as cursor:
                cursor.execute(query_sql)
                res = cursor.fetchone()
                cursor.close()
                conn.commit()
                conn.close()
            print(f"query result = {res['total']}")
        except pymysql.MySQLError as error:
            print(error)

    query_by_company = [['科技', 1, 100]]

    @parameterized.parameterized.expand(query_by_company)
    def get_source_data_by_company(self, company_name, page_num, page_size):
        query_sql = GET_SOURCE_DATA_BY_COMPANY
        try:
            conn = self.get_conn()
            with conn.cursor() as cursor:
                cursor.execute(query_sql, ('%' + company_name + '%', page_num, page_size))
                print(cursor.description)
                data_dict = [dict(zip([col[0] for col in desc], row)) for row in cursor.fetchall()]  # 列表表达式把数据组装起来
            print(data_dict)
        except pymysql.MySQLError as error:
            print(error)


if __name__ == '__main__':
    testunit = unittest.TestSuite()
    # testunit.addTest(TestMysql("get_con"))  # get_con
    testunit.addTest(TestMysql("get_source_data_count"))  # get_source_data_count
    testunit.addTest(TestMysql("get_source_data_by_company"))  # get_source_data_by_company
    runner = unittest.TextTestRunner()
    runner.run(testunit)
