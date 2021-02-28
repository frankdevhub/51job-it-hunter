#!/usr/bin/env python
# -*- coding: UTF-8 -*-
"""
@File ：errors.py
@Author ：frankdevhub@gmail.com
@Blog : http://blog.frankdevhub.site
@Date ：2021/2/25 2:12
"""


# 业务运行异常
class BusinessError(Exception):
    def __init__(self, err_msg) -> None:
        """

        @rtype: object
        """
        self.err_msg = err_msg

    def __str__(self) -> str:
        return self.err_msg


# 系统运行时异常
class PlatformError(Exception):
    def __init__(self, err_msg) -> None:
        self.err_msg = err_msg

    def __str__(self) -> str:
        return self.err_msg


# 获取不到有效资源时的异常
class NoSuchResourceError(Exception):
    def __init__(self, err_msg) -> None:
        self.err_msg = err_msg

    def __str__(self) -> str:
        return self.err_msg


# 获取不到有效资源时的异常
class NoSuchPermissionError(Exception):
    def __init__(self, err_msg) -> None:
        self.err_msg = err_msg

    def __str__(self) -> str:
        return self.err_msg
