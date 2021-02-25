# !/usr/bin/env python
# encoding: utf-8
# @author: frankdevhub
# @contact: frankdevhub@gmail.com
# @blog: http://blog.frankdevhub.site
# @file: test_pymysql.py
# @time: 2021/2/11 22:39

import logging as log
import unittest

import pymysql
from parameterized import parameterized

GET_SOURCE_DATA_COUNT = """
select count(*) as total from platform_data_brief_source
"""
GET_SOURCE_DATA_BY_COMPANY = """
select * from platform_data_brief_source where company_name like %s limit %s,%s
"""

log.basicConfig(level=log.DEBUG)


# 数据库连接配置
class DbConfig:
    HOST = "39.98.246.50"  # 数据源连接地址
    USERNAME = "root"  # 数据源连接用户名
    PASSWORD = "frank#0806db@ecs"  # 数据源连接密码
    DB_NAME = "51job_data_center"  # 数据库名称
    PORT = 3306  # 端口号


class TestPyMysql(unittest.TestCase):
    conn = None

    @staticmethod
    def test_get_conn() -> pymysql.Connection:
        log.debug('invoke method -> get_conn()')
        try:
            log.debug("client attempt to get connection ... ...")
            TestPyMysql.conn = pymysql.connect(host=DbConfig.HOST,
                                               user=DbConfig.USERNAME,
                                               passwd=DbConfig.PASSWORD,
                                               port=DbConfig.PORT,
                                               db=DbConfig.DB_NAME,
                                               cursorclass=pymysql.cursors.DictCursor)
            log.debug(f"remote database connected, host = {str(DbConfig.HOST)}")
        except pymysql.MySQLError as error:
            log.debug(error)

        return TestPyMysql.conn

    @staticmethod
    def test_get_source_data_count() -> None:
        log.debug('invoke method - > get_source_data_count()')
        query_sql = GET_SOURCE_DATA_COUNT
        try:
            conn = TestPyMysql.test_get_conn()
            with conn.cursor() as cursor:
                cursor.execute(query_sql)
                res = cursor.fetchone()
                cursor.close()
                conn.commit()
                conn.close()
            log.debug(f"query result = {res['total']}")
        except pymysql.MySQLError as error:
            log.debug(error)

    query_by_company = [('科技', 1, 100)]

    @staticmethod
    @parameterized.expand(query_by_company)
    def get_source_data_by_company(company_name: str, page_num: int, page_size: int) -> None:
        log.debug('invoke method -> get_source_data_by_company()')
        query_sql = GET_SOURCE_DATA_BY_COMPANY
        try:
            # company_name = '科技'
            # page_num = 1
            # page_size = 1
            conn = TestPyMysql.test_get_conn()
            with conn.cursor() as cursor:
                cursor.execute(query_sql, ('%' + company_name + '%', page_num, page_size))
                desc = cursor.description
                log.debug(desc)  # (('id', 253, None, 256, 256, 0, False)
                data_dict = [dict(zip([col[0] for col in desc], row)) for row in cursor.fetchall()]
            log.debug(data_dict)
        except pymysql.MySQLError as error:
            log.debug(error)


if __name__ == '__main__':
    test_suite = unittest.TestSuite()
    test_suite.addTest(TestPyMysql('test_get_conn'))  # test_get_con
    test_suite.addTest(TestPyMysql('test_get_source_data_count'))  # test_get_source_data_count
    test_suite.addTest(TestPyMysql('test_get_source_data_by_company'))  # test_get_source_data_by_company
    runner = unittest.TextTestRunner()
    runner.run(test_suite)
