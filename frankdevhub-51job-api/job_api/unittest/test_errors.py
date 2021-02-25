#!/usr/bin/env python
# -*- coding: UTF-8 -*-
"""
@File ：test_errors.py
@Author ：frankdevhub@gmail.com
@Blog : http://blog.frankdevhub.site
@Date ：2021/2/25 11:43
"""
import inspect
import logging as log
import sys
import traceback
import unittest

import job_api.error.errors as api_errors

log.basicConfig(level=log.DEBUG)


class TestErrors(unittest.TestCase):
    def test_custom_errors(self):
        sub_class_list = Exception.__subclasses__()
        for i in range(len(sub_class_list)):
            class_name = sub_class_list[i].__name__
            # print(class_name)

        classes = []
        cls_members = inspect.getmembers(api_errors, inspect.isclass)
        for (name, _) in cls_members:
            classes.append(name)
        print(classes)
        for name in classes:
            try:
                obj_cls = getattr(api_errors, name)
                error_obj = obj_cls(f'test_error:{name}')
                print(f'cls_name: {name}, type: {type(error_obj)}')
                assert issubclass(type(error_obj), Exception) is True, f'type error, cls_name: {name}'
                raise error_obj
            except Exception as e:
                # traceback.format_exc()
                exc_type, exc_value, exc_traceback = sys.exc_info()
                print('e.message:\t', exc_value)
                print("Note, object e and exc of Class %s is %s the same." %
                      (type(exc_value), ('not', '')[exc_value is e]))
                print('traceback.print_exc(): ', traceback.print_exc())
                print('traceback.format_exc():\n%s' % traceback.format_exc())
        pass


if __name__ == '__main__':
    test_suite = unittest.TestSuite()
    # test_suite.addTest(TestErrors('test_business_error'))
    # test_suite.addTest(TestErrors('test_platform_error'))
    # test_suite.addTest(TestErrors('test_no_such_resource_error'))
    # test_suite.addTest(TestErrors('test_no_such_permission_error'))
    runner = unittest.TextTestRunner()
    runner.run(test_suite)
