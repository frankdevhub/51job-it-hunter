# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey and OneToOneField has `on_delete` set to the desired behavior
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models


class AuthGroup(models.Model):
    name = models.CharField(unique=True, max_length=150)

    class Meta:
        managed = False
        db_table = 'auth_group'


class AuthGroupPermissions(models.Model):
    group = models.ForeignKey(AuthGroup, models.DO_NOTHING)
    permission = models.ForeignKey('AuthPermission', models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'auth_group_permissions'
        unique_together = (('group', 'permission'),)


class AuthPermission(models.Model):
    name = models.CharField(max_length=255)
    content_type = models.ForeignKey('DjangoContentType', models.DO_NOTHING)
    codename = models.CharField(max_length=100)

    class Meta:
        managed = False
        db_table = 'auth_permission'
        unique_together = (('content_type', 'codename'),)


class AuthUser(models.Model):
    password = models.CharField(max_length=128)
    last_login = models.DateTimeField(blank=True, null=True)
    is_superuser = models.IntegerField()
    username = models.CharField(unique=True, max_length=150)
    first_name = models.CharField(max_length=150)
    last_name = models.CharField(max_length=150)
    email = models.CharField(max_length=254)
    is_staff = models.IntegerField()
    is_active = models.IntegerField()
    date_joined = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'auth_user'


class AuthUserGroups(models.Model):
    user = models.ForeignKey(AuthUser, models.DO_NOTHING)
    group = models.ForeignKey(AuthGroup, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'auth_user_groups'
        unique_together = (('user', 'group'),)


class AuthUserUserPermissions(models.Model):
    user = models.ForeignKey(AuthUser, models.DO_NOTHING)
    permission = models.ForeignKey(AuthPermission, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'auth_user_user_permissions'
        unique_together = (('user', 'permission'),)


class DjangoAdminLog(models.Model):
    action_time = models.DateTimeField()
    object_id = models.TextField(blank=True, null=True)
    object_repr = models.CharField(max_length=200)
    action_flag = models.PositiveSmallIntegerField()
    change_message = models.TextField()
    content_type = models.ForeignKey('DjangoContentType', models.DO_NOTHING, blank=True, null=True)
    user = models.ForeignKey(AuthUser, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'django_admin_log'


class DjangoContentType(models.Model):
    app_label = models.CharField(max_length=100)
    model = models.CharField(max_length=100)

    class Meta:
        managed = False
        db_table = 'django_content_type'
        unique_together = (('app_label', 'model'),)


class DjangoMigrations(models.Model):
    app = models.CharField(max_length=255)
    name = models.CharField(max_length=255)
    applied = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'django_migrations'


class DjangoSession(models.Model):
    session_key = models.CharField(primary_key=True, max_length=40)
    session_data = models.TextField()
    expire_date = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'django_session'


class PlatformCompanyInfo(models.Model):
    id = models.CharField(primary_key=True, max_length=64)
    union_id = models.IntegerField(blank=True, null=True)
    plat_company_logo = models.CharField(max_length=100, blank=True, null=True)
    plat_company_type = models.CharField(max_length=50, blank=True, null=True)
    plat_company_industry = models.CharField(max_length=50, blank=True, null=True)
    plat_company_info = models.TextField(blank=True, null=True)
    plat_company_link = models.CharField(max_length=100, blank=True, null=True)
    context = models.TextField(blank=True, null=True)
    tag_list = models.CharField(max_length=100, blank=True, null=True)
    create_time = models.BigIntegerField(blank=True, null=True)
    update_time = models.BigIntegerField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'platform_company_info'


class PlatformDataBriefSource(models.Model):
    id = models.CharField(primary_key=True, max_length=64)
    type = models.CharField(max_length=50, blank=True, null=True)
    jt = models.IntegerField(blank=True, null=True)
    tags = models.CharField(max_length=100, blank=True, null=True)
    ad_track = models.CharField(max_length=100, blank=True, null=True)
    jobid = models.CharField(max_length=20, blank=True, null=True)
    coid = models.CharField(max_length=20, blank=True, null=True)
    effect = models.IntegerField(blank=True, null=True)
    is_special_job = models.CharField(max_length=5, blank=True, null=True)
    job_href = models.CharField(max_length=100, blank=True, null=True)
    job_name = models.CharField(max_length=100, blank=True, null=True)
    job_title = models.CharField(max_length=100, blank=True, null=True)
    company_href = models.CharField(max_length=100, blank=True, null=True)
    company_name = models.CharField(max_length=100, blank=True, null=True)
    provide_salary_text = models.CharField(max_length=100, blank=True, null=True)
    work_area = models.CharField(max_length=50, blank=True, null=True)
    work_area_text = models.CharField(max_length=100, blank=True, null=True)
    update_date = models.CharField(max_length=50, blank=True, null=True)
    is_intern = models.CharField(max_length=5, blank=True, null=True)
    is_communicate = models.CharField(max_length=5, blank=True, null=True)
    company_type_text = models.CharField(max_length=100, blank=True, null=True)
    degree_from = models.IntegerField(blank=True, null=True)
    work_year = models.IntegerField(blank=True, null=True)
    issue_date = models.CharField(max_length=50, blank=True, null=True)
    is_from_xyz = models.CharField(max_length=5, blank=True, null=True)
    jobwelf = models.CharField(max_length=100, blank=True, null=True)
    jobwelf_list = models.CharField(max_length=100, blank=True, null=True)
    attribute_text = models.CharField(max_length=100, blank=True, null=True)
    company_size_text = models.CharField(max_length=100, blank=True, null=True)
    company_ind_text = models.CharField(max_length=100, blank=True, null=True)
    adid = models.CharField(max_length=10, blank=True, null=True)
    context = models.TextField(blank=True, null=True)
    create_time = models.BigIntegerField(blank=True, null=True)
    update_time = models.BigIntegerField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'platform_data_brief_source'


class PlatformDictArea(models.Model):
    id = models.CharField(primary_key=True, max_length=64)
    parent_id = models.CharField(max_length=64)
    parent_ids = models.CharField(max_length=2000)
    name = models.CharField(max_length=200)
    merger_name = models.CharField(max_length=500)
    short_name = models.CharField(max_length=200)
    merger_short_name = models.CharField(max_length=500)
    type = models.CharField(max_length=1)
    sort = models.DecimalField(max_digits=10, decimal_places=0)
    code = models.CharField(max_length=45, blank=True, null=True)
    city_code = models.CharField(max_length=45, blank=True, null=True)
    pinyin = models.CharField(max_length=500, blank=True, null=True)
    jianpin = models.CharField(max_length=100, blank=True, null=True)
    first_char = models.CharField(max_length=45, blank=True, null=True)
    position_ing = models.CharField(max_length=64, blank=True, null=True)
    position_lat = models.CharField(max_length=64, blank=True, null=True)
    hot_area = models.CharField(max_length=64)
    remarks = models.CharField(max_length=1000, blank=True, null=True)
    extension1 = models.CharField(max_length=500, blank=True, null=True)
    extension2 = models.CharField(max_length=500, blank=True, null=True)
    extension3 = models.CharField(max_length=500, blank=True, null=True)
    create_time = models.BigIntegerField(blank=True, null=True)
    update_time = models.BigIntegerField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'platform_dict_area'


class PlatformSearchResult(models.Model):
    id = models.CharField(primary_key=True, max_length=64)
    job_title = models.CharField(max_length=50, blank=True, null=True)
    company_name = models.CharField(max_length=50, blank=True, null=True)
    location = models.CharField(max_length=50, blank=True, null=True)
    salary_range_chars = models.CharField(max_length=50, blank=True, null=True)
    salary_range_min = models.FloatField(blank=True, null=True)
    salary_range_max = models.FloatField(blank=True, null=True)
    salary_time_unit = models.CharField(max_length=5, blank=True, null=True)
    salary_numeric_unit = models.CharField(max_length=5, blank=True, null=True)
    head_count = models.IntegerField(blank=True, null=True)
    is_define_by_w = models.IntegerField(blank=True, null=True)
    is_define_by_k = models.IntegerField(blank=True, null=True)
    is_define_by_day = models.IntegerField(blank=True, null=True)
    is_define_by_month = models.IntegerField(blank=True, null=True)
    is_define_by_year = models.IntegerField(blank=True, null=True)
    is_internship_pos = models.IntegerField(blank=True, null=True)
    is_campus_only = models.IntegerField(blank=True, null=True)
    is_salary_negotiable = models.IntegerField(blank=True, null=True)
    publish_date_char = models.CharField(max_length=50, blank=True, null=True)
    publish_date_month_numeric = models.IntegerField(blank=True, null=True)
    publish_date_day_numeric = models.IntegerField(blank=True, null=True)
    link_url = models.CharField(max_length=255, blank=True, null=True)
    union_id = models.IntegerField(blank=True, null=True)
    create_time = models.BigIntegerField(blank=True, null=True)
    update_time = models.BigIntegerField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'platform_search_result'
