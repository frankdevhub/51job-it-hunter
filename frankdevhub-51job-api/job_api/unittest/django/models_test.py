import unittest

from django.db.models import Count

from job_api import models


# !/usr/bin/env python
# encoding: utf-8
# @author: frankdevhub
# @contact: frankdevhub@gmail.com
# @blog: http://blog.frankdevhub.site
# @file: models_test.py
# @time: 2021/2/13 23:09
# @desc: Django model OR M持久化测试

class ModelsTest(unittest.TestCase):
    def initData(self):
        pass

    def test_model_orm(self):
        self.initData()
        print('invoke method -> test_model_orm()')
        total_num = models.PlatformDataJson.objects.annotate(total_num=Count('id'))
        print(total_num)


if __name__ == '__main__':
    unittest.main()
