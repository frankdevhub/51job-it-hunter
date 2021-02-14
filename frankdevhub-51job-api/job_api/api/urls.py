#!/usr/bin/env python
# encoding: utf-8
# @author: frankdevhub
# @contact: frankdevhub@gmail.com
# @blog: http://blog.frankdevhub.site
# @file: urls.py
# @time: 2021/2/14 22:57

from django.conf.urls import include, url
from rest_framework import routers

from job_api.unittest.django.test_restful_api import *

# 定义路由地址
route = routers.DefaultRouter()

# 注册新的路由地址
route.register(r'plat_data', PlatDataJsonViewSet)

# 注册上一级的路由地址并添加
urlpatterns = [
    url('api/', include(route.urls)),
]
