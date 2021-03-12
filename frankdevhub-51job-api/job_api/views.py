from django.http import HttpResponse


# !/usr/bin/env python
# encoding: utf-8
# @author: frankdevhub
# @contact: frankdevhub@gmail.com
# @blog: http://blog.frankdevhub.site
# @time: 2021/2/12 16:27

# Create your views here.


def echo_index(request):
    content = '<h3>Welcome, frankdevhub@gmail.com</h3>'
    return HttpResponse(content)


if __name__ == "__main__":
    pass
