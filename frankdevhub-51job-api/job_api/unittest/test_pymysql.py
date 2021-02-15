#!/usr/bin/env python
# encoding: utf-8
# @author: frankdevhub
# @contact: frankdevhub@gmail.com
# @blog: http://blog.frankdevhub.site
# @file: test_pymysql.py
# @time: 2021/2/11 22:39
# @desc: 测试pymsql数据持久化


import logging
import unittest

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


class TestPyMysql(unittest.TestCase):
    conn = None

    def setUp(self):
        pass

    def teardown(self):
        pass

    def test_get_conn(self):
        logging.info('invoke method -> get_conn()')
        try:
            logging.info("client attempt to get connection ... ...")
            self.conn = pymysql.connect(host=DbConfig().host,
                                        user=DbConfig().username,
                                        passwd=DbConfig().password,
                                        port=DbConfig().port,
                                        db=DbConfig().db,
                                        cursorclass=pymysql.cursors.DictCursor)
            logging.info(f"remote database connected, host = {str(DbConfig.host)}")
        except pymysql.MySQLError as error:
            logging.info(error)

        return self.conn

    def test_get_source_data_count(self):
        logging.info('invoke method - > get_source_data_count()')
        query_sql = GET_SOURCE_DATA_COUNT
        try:
            conn = self.test_get_conn()
            with conn.cursor() as cursor:
                cursor.execute(query_sql)
                res = cursor.fetchone()
                cursor.close()
                conn.commit()
                conn.close()
            logging.info(f"query result = {res['total']}")
        except pymysql.MySQLError as error:
            logging.info(error)

    query_by_company = [('科技', 1, 100)]

    # @parameterized.expand(query_by_company)
    # def get_source_data_by_company(self, company_name, page_num, page_size):
    def get_source_data_by_company(self):
        logging.info('invoke method -> get_source_data_by_company()')
        query_sql = GET_SOURCE_DATA_BY_COMPANY
        try:
            company_name = '科技'
            page_num = 1
            page_size = 1
            conn = self.test_get_conn()
            with conn.cursor() as cursor:
                cursor.execute(query_sql, ('%' + company_name + '%', page_num, page_size))
                desc = cursor.description
                logging.info(desc)  # (('id', 253, None, 256, 256, 0, False)
                data_dict = [dict(zip([col[0] for col in desc], row)) for row in cursor.fetchall()]
            logging.info(data_dict)
        except pymysql.MySQLError as error:
            logging.info(error)


if __name__ == '__main__':
    testunit = unittest.TestSuite()
    # testunit.addTest(TestMysql("test_get_con"))  # test_get_con
    testunit.addTest(TestPyMysql("test_get_source_data_count"))  # test_get_source_data_count
    testunit.addTest(TestPyMysql("test_get_source_data_by_company"))  # test_get_source_data_by_company
    runner = unittest.TextTestRunner()
    runner.run(testunit)
