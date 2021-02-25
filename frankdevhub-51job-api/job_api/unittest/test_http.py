# !/usr/bin/env python
# encoding: utf-8
# @author: frankdevhub
# @contact: frankdevhub@gmail.com
# @blog: http://blog.frankdevhub.site
# @file: test_http.py
# @time: 2021/2/14 11:11

import logging as log
import unittest
import urllib
import urllib.request

import requests

log.basicConfig(level=log.INFO)


# noinspection PyUnresolvedReferences
class TestHttpRequest(unittest.TestCase):
    TENCENT_API_KEY = "EPOBZ-NAQ36-5VZSA-MBIIB-NMB7O-SEBRQ"  # 腾讯地图API秘钥
    TEST_IP = "39.98.246.50"  # 测试用ip地址,阿里云服务器ECS地址
    GET_IP_LOCATION = "https://apis.map.qq.com/ws/location/v1/ip?"

    @staticmethod
    def test_urllib_get_ip_location():
        """urllib module 测试获取ip地址的经纬度地理信息"""
        log.info('invoke method -> test_urllib_get_ip_location')
        request_data = {'key': TestHttpRequest.TENCENT_API_KEY, 'ip': TestHttpRequest.TEST_IP}

        path_variables = urllib.parse.urlencode(request_data)
        log.info(f'path_variables = {str(path_variables)}')
        api_url = TestHttpRequest.GET_IP_LOCATION
        request_url = api_url + path_variables
        log.info(f'request_url = {str(request_url)}')

        response_data = urllib.request.urlopen(request_url).read()
        log.info(str(response_data.decode('utf-8')))

    @staticmethod
    def test_request_get_ip_location():
        """
        requests module 测试获取ip地址的经纬度地理信息
        """
        log.info('invoke method -> test_request_get_ip_location')
        request_data = {'key': TestHttpRequest.TENCENT_API_KEY, 'ip': TestHttpRequest.TEST_IP}

        path_variables = urllib.parse.urlencode(request_data)
        log.info(f'path_variables = {str(path_variables)}')
        api_url = TestHttpRequest.GET_IP_LOCATION
        request_url = api_url + path_variables
        log.info(f'request_url = {str(request_url)}')
        response_data = requests.get(request_url)

        log.info(type(response_data))  # <class 'requests.models.Response'>
        log.info(response_data.text)  # response context


if __name__ == '__main__':
    # test_suite = unittest.TestSuite()
    # test_suite.addTest(TestHttpRequest('test_request_get_ip_location'))  # test_request_get_ip_location
    # runner = unittest.runner.TextTestRunner()
    # runner.run(test_suite)
    unittest.main()
