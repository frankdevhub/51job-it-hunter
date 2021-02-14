from django.http import HttpResponse

from job_api.models import PlatformDataBriefSource


# !/usr/bin/env python
# encoding: utf-8
# @author: frankdevhub
# @contact: frankdevhub@gmail.com
# @blog: http://blog.frankdevhub.site
# @time: 2021/2/12 16:27


def echo_index(request):
    content = '<h3>Welcome, frankdevhub@gmail.com</h3>'
    return HttpResponse(content)


def get_platform_data_count(request):
    print('invoke method -> get_platform_data_count()')
    total_rows = PlatformDataBriefSource.objects.count()
    print(f'total_rows = {total_rows}')

    # TODO: None 需要替换空的视图会产生运行时异常
    return None


if __name__ == "__main__":
    pass
