#!/usr/bin/env python
# -*- coding: UTF-8 -*-
"""
@File ：models_orm.py
@Author ：frankdevhub@gmail.com
@Blog : http://blog.frankdevhub.site
@Date ：2021/3/7 22:23
"""
# 为了使用MySQL支持的类型，如unsigned bigint
import sqlalchemy as db
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker

# 创建对象的基类:
Base = declarative_base()


# 初始化数据库连接:
# 连接字符串模式：数据库类型+连接库+用户名+密码+主机，字符编码，是否打印建表细节
# 其中，连接库是当前用于操作数据库的库，对于python2.7，一般使用MysqlDb，对于Python3，一般使用pymysql
# 连接的例子如：create_engine("mysql+pymysql://cai:123@localhost/test?charset=utf8", echo=True)
# engine = db.create_engine('mysql+pymysql://root:frank#0806db@ecs@39.98.246.50:3306/51job_data_center')


# 删除现有的表，谨慎决定是否需要这样操作
# Base.metadata.drop_all(engine)

# 创建表
# Base.metadata.create_all(engine)

def get_session():
    db_session = sessionmaker(bind=engine)
    return db_session


def get_engine():
    engine = db.create_engine('mysql+pymysql://root:frank#0806db@ecs@39.98.246.50:3306/51job_data_center')
    return engine
