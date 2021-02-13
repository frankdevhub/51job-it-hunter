from django.db import models

# !/usr/bin/env python
# encoding: utf-8
# @author: frankdevhub
# @contact: frankdevhub@gmail.com
# @blog: http://blog.frankdevhub.site
# @time: 2021/2/12 16:27

# Create your models here.

'''
1、models.AutoField　　自增列= int(11)
　　如果没有的话，默认会生成一个名称为 id 的列，如果要显示的自定义一个自增列，必须将给列设置为主键 primary_key=True。
2、models.CharField　　字符串字段
　　必须 max_length 参数
3、models.BooleanField　　布尔类型=tinyint(1)
　　不能为空，Blank=True
4、models.ComaSeparatedIntegerField　　用逗号分割的数字=varchar
　　继承CharField，所以必须 max_lenght 参数
5、models.DateField　　日期类型 date
　　对于参数，auto_now =True则每次更新都会更新这个时间；auto_now_add 则只是第一次创建添加，之后的更新不再改变。
6、models.DateTimeField　　日期类型 datetime
　　同DateField的参数
7、models.Decimal　　十进制小数类型= decimal
　　必须指定整数位max_digits和小数位decimal_places
8、models.EmailField　　字符串类型（正则表达式邮箱）=varchar
　　对字符串进行正则表达式
9、models.FloatField　　浮点类型= double
10、models.IntegerField　　整形
11、models.BigIntegerField　　长整形
　　integer_field_ranges ={
　　　　'SmallIntegerField':(-32768,32767),
　　　　'IntegerField':(-2147483648,2147483647),
　　　　'BigIntegerField':(-9223372036854775808,9223372036854775807),
　　　　'PositiveSmallIntegerField':(0,32767),
　　　　'PositiveIntegerField':(0,2147483647),
　　}
12、models.IPAddressField　　字符串类型（ip4正则表达式）
13、models.GenericIPAddressField　　字符串类型（ip4和ip6是可选的）
　　参数protocol可以是：both、ipv4、ipv6
　　验证时，会根据设置报错
14、models.NullBooleanField　　允许为空的布尔类型
15、models.PositiveIntegerFiel　　正Integer
16、models.PositiveSmallIntegerField　　正smallInteger
17、models.SlugField　　减号、下划线、字母、数字
18、models.SmallIntegerField　　数字
　　数据库中的字段有：tinyint、smallint、int、bigint
19、models.TextField　　字符串=longtext
20、models.TimeField　　时间 HH:MM[:ss[.uuuuuu]]
21、models.URLField　　字符串，地址正则表达式
22、models.BinaryField　　二进制
23、models.ImageField图片
24、models.FilePathField文件
'''


class BaseRecord(models.Model):
    id = models.CharField()  # 主键序号
    create_time = models.BigIntegerField()  # 创建时间
    update_time = models.BigIntegerField()  # 更新时间


class PlatformDataJson(BaseRecord):
    class Meta:
        proxy = True

    type = models.CharField()  # 搜索结果的类型
    jt = models.IntegerField()
    tags = models.CharField()  # 职位的标签信息
    ad_track = models.CharField()
    job_id = models.CharField()  # jobId岗位信息的唯一标识
    coid = models.CharField()
