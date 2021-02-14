# from requests import Response
# from rest_framework import status, viewsets
# from rest_framework.decorators import action
#
# from job_api.models import PlatformDataBriefSource
# from job_api.serializers import PlatDataJsonSerializer
#
#
# # !/usr/bin/env python
# # encoding: utf-8
# # @author: frankdevhub
# # @contact: frankdevhub@gmail.com
# # @blog: http://blog.frankdevhub.site
# # @file: test_restful_api.py
# # @time: 2021/2/13 23:09
# # @desc: restful API 功能测试
#
#
# class PlatDataJsonViewSet(viewsets.ModelViewSet):
#     queryset = PlatformDataBriefSource.objects.all()
#     serializer_class = PlatDataJsonSerializer
#
#     # action是drf提供的路由和视图方法绑定关系的装饰器
#     # from rest_framework.decorators import action
#     # 参数1: methods 列表，设置视图方法允许哪些http请求访问进来
#     # 参数2: detail  当前是否方法是否属于详情页视图，
#     #        False，系统不会自动增加pk在生成的路由地址中
#     #        True  则系统会自动增加pk在生成的路由地址
#
#     @action(methods=['get'], detail=False)  # detail=False 是否为详情页数据
#     def get_platform_data_count(self, request):  # 其接口 http://127.0.0.1:port/job_market/api/plat_data
#         print('invoke method -> get_platform_data_count()')
#         if request.method == 'GET':
#             total_rows = PlatformDataBriefSource.objects.count()
#             total_serializer = PlatDataJsonSerializer(total_rows, many=False)
#             print(f'total_rows = {total_rows}')
#             return Response(data=total_serializer.data, status=status.HTTP_200_OK)
#         else:
#             pass
