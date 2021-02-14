import unittest
import urllib
import urllib.request

import requests


# !/usr/bin/env python
# encoding: utf-8
# @author: frankdevhub
# @contact: frankdevhub@gmail.com
# @blog: http://blog.frankdevhub.site
# @file: test_http.py
# @time: 2021/2/14 11:11
# @desc:  Http 请求测试

class TestHttpRequest(unittest.TestCase):
    TENCENT_API_KEY = "EPOBZ-NAQ36-5VZSA-MBIIB-NMB7O-SEBRQ"  # 腾讯地图API秘钥
    TEST_IP = "39.98.246.50"  # 测试用ip地址,阿里云服务器ECS地址

    GET_IP_LOCATION = "https://apis.map.qq.com/ws/location/v1/ip?"

    def test_urllib_get_ip_location(self):
        """urllib module 测试获取ip地址的经纬度地理信息"""
        print('invoke method -> test_urllib_get_ip_location')
        request_data = {'key': self.TENCENT_API_KEY, 'ip': self.TEST_IP}

        path_variables = urllib.parse.urlencode(request_data)
        print(f'path_variables = {str(path_variables)}')
        api_url = self.GET_IP_LOCATION
        request_url = api_url + path_variables
        print(f'request_url = {str(request_url)}')

        response_data = urllib.request.urlopen(request_url).read()
        print(str(response_data.decode('utf-8')))

    def test_request_get_ip_location(self):
        """requests module 测试获取ip地址的经纬度地理信息"""
        print('invoke method -> test_request_get_ip_location')
        request_data = {'key': self.TENCENT_API_KEY, 'ip': self.TEST_IP}

        path_variables = urllib.parse.urlencode(request_data)
        print(f'path_variables = {str(path_variables)}')
        api_url = self.GET_IP_LOCATION
        request_url = api_url + path_variables
        print(f'request_url = {str(request_url)}')
        response_data = requests.get(request_url)

        print(type(response_data))  # <class 'requests.models.Response'>
        print(response_data.text)  # response context


if __name__ == '__main__':
    testSuite = unittest.TestSuite()
    testSuite.addTest(TestHttpRequest('test_request_get_ip_location'))  # test_request_get_ip_location
    runners = unittest.runner.TextTestRunner()
    runners.run(testSuite)
    # unittest.main()
