#!/usr/bin/env python
# encoding: utf-8
# @author: frankdevhub
# @contact: frankdevhub@gmail.com
# @blog: http://blog.frankdevhub.site
# @file: serializers.py
# @time: 2021/2/14 22:05


from rest_framework import serializers

from job_api.models import *


class PlatDataJsonSerializer(serializers.ModelSerializer):
    """PlatformDataBriefSource"""

    class Meta:
        model = PlatformDataBriefSource
        fields = '__all__'  # 序列化所有字段
