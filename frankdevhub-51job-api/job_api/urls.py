"""frankdevhub_51job_api URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/3.1/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""

from django.conf.urls import include, url
from rest_framework import routers

from job_api.unittest.django.test_restful_api import *

# 定义路由地址
router = routers.DefaultRouter()

# 注册新的路由地址
router.register(r'plat_data', PlatDataJsonViewSet)

# 注册上一级的路由地址并添加
urlpatterns = [
    url('plat_data/', include(router.urls)),
]
urlpatterns += router.urls
