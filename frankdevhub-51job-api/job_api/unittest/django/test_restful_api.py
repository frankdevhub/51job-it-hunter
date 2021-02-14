import unittest

from requests import Response
from rest_framework import status
from rest_framework.decorators import api_view

from job_api.models import PlatformDataBriefSource
from job_api.serializers import PlatDataJsonSerializer


# !/usr/bin/env python
# encoding: utf-8
# @author: frankdevhub
# @contact: frankdevhub@gmail.com
# @blog: http://blog.frankdevhub.site
# @file: test_restful_api.py
# @time: 2021/2/13 23:09
# @desc: restful API 功能测试

@api_view(['GET'])
def get_platform_data_count(request):
    print('invoke method -> get_platform_data_count()')
    total_rows = PlatformDataBriefSource.objects.count()
    total_serializer = PlatDataJsonSerializer(total_rows, many=False)

    print(f'total_rows = {total_rows}')

    return Response(data=total_serializer.data, status=status.HTTP_200_OK)


if __name__ == '__main__':
    unittest.main()
