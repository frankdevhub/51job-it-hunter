import unittest

from job_api.models import *


# !/usr/bin/env python
# encoding: utf-8
# @author: frankdevhub
# @contact: frankdevhub@gmail.com
# @blog: http://blog.frankdevhub.site
# @file: test_restful_api.py
# @time: 2021/2/13 23:09
# @desc: restful API 功能测试


def get_platform_data_count(request):
    print('invoke method -> get_platform_data_count()')
    total_rows = PlatformDataBriefSource.objects.count()
    print(f'total_rows = {total_rows}')

    return None


if __name__ == '__main__':
    unittest.main()
