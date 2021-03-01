# coding: utf-8
from sqlalchemy import BigInteger, CHAR, CheckConstraint, Column, DECIMAL, Float, ForeignKey, Index, Integer, String
from sqlalchemy.dialects.mysql import DATETIME, LONGTEXT, SMALLINT, TINYINT, VARCHAR
from sqlalchemy.orm import relationship
from sqlalchemy.ext.declarative import declarative_base

Base = declarative_base()
metadata = Base.metadata


class AuthGroup(Base):
    __tablename__ = 'auth_group'

    id = Column(Integer, primary_key=True)
    name = Column(String(150), nullable=False, unique=True)


class AuthUser(Base):
    __tablename__ = 'auth_user'

    id = Column(Integer, primary_key=True)
    password = Column(String(128), nullable=False)
    last_login = Column(DATETIME(fsp=6))
    is_superuser = Column(TINYINT(1), nullable=False)
    username = Column(String(150), nullable=False, unique=True)
    first_name = Column(String(150), nullable=False)
    last_name = Column(String(150), nullable=False)
    email = Column(String(254), nullable=False)
    is_staff = Column(TINYINT(1), nullable=False)
    is_active = Column(TINYINT(1), nullable=False)
    date_joined = Column(DATETIME(fsp=6), nullable=False)


class DjangoContentType(Base):
    __tablename__ = 'django_content_type'
    __table_args__ = (
        Index('django_content_type_app_label_model_76bd3d3b_uniq', 'app_label', 'model', unique=True),
    )

    id = Column(Integer, primary_key=True)
    app_label = Column(String(100), nullable=False)
    model = Column(String(100), nullable=False)


class DjangoMigration(Base):
    __tablename__ = 'django_migrations'

    id = Column(Integer, primary_key=True)
    app = Column(String(255), nullable=False)
    name = Column(String(255), nullable=False)
    applied = Column(DATETIME(fsp=6), nullable=False)


class DjangoSession(Base):
    __tablename__ = 'django_session'

    session_key = Column(String(40), primary_key=True)
    session_data = Column(LONGTEXT, nullable=False)
    expire_date = Column(DATETIME(fsp=6), nullable=False, index=True)


class PlatformCompanyInfo(Base):
    __tablename__ = 'platform_company_info'

    id = Column(VARCHAR(64), primary_key=True, comment='???id')
    union_id = Column(Integer, comment='????')
    plat_company_logo = Column(VARCHAR(100), comment='????')
    plat_company_type = Column(VARCHAR(50), comment='????(????)')
    plat_company_industry = Column(VARCHAR(50), comment='??????(?????,??)')
    plat_company_info = Column(LONGTEXT, comment='??????')
    plat_company_link = Column(String(100), comment='????????????')
    context = Column(LONGTEXT, comment='????????html??')
    tag_list = Column(VARCHAR(100), comment='????(??,????,??,???????)')
    create_time = Column(BigInteger, comment='????(???)')
    update_time = Column(BigInteger, comment='???????')


class PlatformDataBriefSource(Base):
    __tablename__ = 'platform_data_brief_source'
    __table_args__ = (
        Index('plat_data_index', 'jt', 'jobid', 'coid', 'effect', 'work_area', 'is_intern', 'is_communicate',
              'degree_from', 'work_year'),
    )

    id = Column(VARCHAR(64), primary_key=True, comment='??id')
    type = Column(VARCHAR(50), comment='????')
    jt = Column(Integer)
    tags = Column(String(100))
    ad_track = Column(String(100))
    jobid = Column(String(20))
    coid = Column(String(20))
    effect = Column(Integer)
    is_special_job = Column(String(5))
    job_href = Column(VARCHAR(100), comment='????')
    job_name = Column(VARCHAR(100), comment='????')
    job_title = Column(VARCHAR(100), comment='????')
    company_href = Column(VARCHAR(100), comment='??????')
    company_name = Column(VARCHAR(100), comment=' ????')
    provide_salary_text = Column(VARCHAR(100))
    work_area = Column(VARCHAR(50), comment='??????')
    work_area_text = Column(VARCHAR(100))
    update_date = Column(VARCHAR(50), comment='??????(?????????????????')
    is_intern = Column(VARCHAR(5), comment=' ???????')
    is_communicate = Column(VARCHAR(5), comment='??????')
    company_type_text = Column(VARCHAR(100), comment='?????????')
    degree_from = Column(Integer, comment='????')
    work_year = Column(Integer, comment='????')
    issue_date = Column(String(50))
    is_from_xyz = Column(String(5))
    jobwelf = Column(VARCHAR(100), comment='??????')
    jobwelf_list = Column(VARCHAR(100), comment='??????')
    attribute_text = Column(VARCHAR(100), comment='????????:??????,??,?????')
    company_size_text = Column(VARCHAR(100), comment='?????????')
    company_ind_text = Column(VARCHAR(100), comment='????????????')
    adid = Column(String(10))
    context = Column(LONGTEXT, comment='??????????')
    create_time = Column(BigInteger, comment='????(???)')
    update_time = Column(BigInteger, comment='????(???)')


class PlatformDictArea(Base):
    __tablename__ = 'platform_dict_area'
    __table_args__ = {'comment': '???'}

    id = Column(String(64), primary_key=True, comment='??')
    parent_id = Column(String(64), nullable=False, index=True, comment='????')
    parent_ids = Column(String(2000), nullable=False, comment='??????')
    name = Column(String(200), nullable=False, comment='??')
    merger_name = Column(String(500), nullable=False, comment='????')
    short_name = Column(String(200), nullable=False, comment='??')
    merger_short_name = Column(String(500), nullable=False, comment='????')
    type = Column(CHAR(1), nullable=False, comment='????')
    sort = Column(DECIMAL(10, 0), nullable=False, comment='??')
    code = Column(String(45), comment='????')
    city_code = Column(String(45), comment='????')
    pinyin = Column(String(500), comment='??')
    jianpin = Column(String(100), comment='????')
    first_char = Column(String(45), comment='???')
    position_ing = Column(String(64), comment='??x')
    position_lat = Column(String(64), comment='??y')
    hot_area = Column(String(64), nullable=False, comment='????')
    remarks = Column(String(1000), comment='????')
    extension1 = Column(String(500))
    extension2 = Column(String(500))
    extension3 = Column(String(500))
    create_time = Column(BigInteger, comment='??????(?????')
    update_time = Column(BigInteger, comment='??????(?????')


class PlatformSearchResult(Base):
    __tablename__ = 'platform_search_result'

    id = Column(VARCHAR(64), primary_key=True, comment='???id')
    job_title = Column(VARCHAR(50), comment='????')
    company_name = Column(VARCHAR(50), comment='????')
    location = Column(VARCHAR(50), comment='????')
    salary_range_chars = Column(VARCHAR(50), comment='?????????')
    salary_range_min = Column(Float(10, True), comment='?????')
    salary_range_max = Column(Float(10, True), comment='?????')
    salary_time_unit = Column(VARCHAR(5), comment='????????')
    salary_numeric_unit = Column(VARCHAR(5), comment='??????')
    head_count = Column(Integer, comment='??????')
    is_define_by_w = Column(TINYINT, comment='??????')
    is_define_by_k = Column(TINYINT, comment='??????')
    is_define_by_day = Column(TINYINT, comment='??????')
    is_define_by_month = Column(TINYINT, comment='??????')
    is_define_by_year = Column(TINYINT, comment='??????')
    is_internship_pos = Column(TINYINT, comment='??????')
    is_campus_only = Column(TINYINT, comment='??????')
    is_salary_negotiable = Column(TINYINT, comment='???????')
    publish_date_char = Column(VARCHAR(50), comment='\x7f????')
    publish_date_month_numeric = Column(Integer, comment='???????')
    publish_date_day_numeric = Column(Integer, comment='???????')
    link_url = Column(VARCHAR(255), comment='??????')
    union_id = Column(Integer, comment='????')
    create_time = Column(BigInteger, comment='????(???)')
    update_time = Column(BigInteger, comment='????(???)')


class AuthPermission(Base):
    __tablename__ = 'auth_permission'
    __table_args__ = (
        Index('auth_permission_content_type_id_codename_01ab375a_uniq', 'content_type_id', 'codename', unique=True),
    )

    id = Column(Integer, primary_key=True)
    name = Column(String(255), nullable=False)
    content_type_id = Column(ForeignKey('django_content_type.id'), nullable=False)
    codename = Column(String(100), nullable=False)

    content_type = relationship('DjangoContentType')


class AuthUserGroup(Base):
    __tablename__ = 'auth_user_groups'
    __table_args__ = (
        Index('auth_user_groups_user_id_group_id_94350c0c_uniq', 'user_id', 'group_id', unique=True),
    )

    id = Column(Integer, primary_key=True)
    user_id = Column(ForeignKey('auth_user.id'), nullable=False)
    group_id = Column(ForeignKey('auth_group.id'), nullable=False, index=True)

    group = relationship('AuthGroup')
    user = relationship('AuthUser')


class DjangoAdminLog(Base):
    __tablename__ = 'django_admin_log'
    __table_args__ = (
        CheckConstraint('(`action_flag` >= 0)'),
    )

    id = Column(Integer, primary_key=True)
    action_time = Column(DATETIME(fsp=6), nullable=False)
    object_id = Column(LONGTEXT)
    object_repr = Column(String(200), nullable=False)
    action_flag = Column(SMALLINT, nullable=False)
    change_message = Column(LONGTEXT, nullable=False)
    content_type_id = Column(ForeignKey('django_content_type.id'), index=True)
    user_id = Column(ForeignKey('auth_user.id'), nullable=False, index=True)

    content_type = relationship('DjangoContentType')
    user = relationship('AuthUser')


class AuthGroupPermission(Base):
    __tablename__ = 'auth_group_permissions'
    __table_args__ = (
        Index('auth_group_permissions_group_id_permission_id_0cd325b0_uniq', 'group_id', 'permission_id', unique=True),
    )

    id = Column(Integer, primary_key=True)
    group_id = Column(ForeignKey('auth_group.id'), nullable=False)
    permission_id = Column(ForeignKey('auth_permission.id'), nullable=False, index=True)

    group = relationship('AuthGroup')
    permission = relationship('AuthPermission')


class AuthUserUserPermission(Base):
    __tablename__ = 'auth_user_user_permissions'
    __table_args__ = (
        Index('auth_user_user_permissions_user_id_permission_id_14a6b632_uniq', 'user_id', 'permission_id',
              unique=True),
    )

    id = Column(Integer, primary_key=True)
    user_id = Column(ForeignKey('auth_user.id'), nullable=False)
    permission_id = Column(ForeignKey('auth_permission.id'), nullable=False, index=True)

    permission = relationship('AuthPermission')
    user = relationship('AuthUser')
