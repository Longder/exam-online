create database exam_online default character set UTF8

create table SYS_USER
(
	id_ bigint auto_increment comment '主键',
	name_ varchar(255) null comment '姓名',
	login_name_ varchar(100) null comment '登录名',
	password_ varchar(100) null comment '密码',
	gender_ varchar(50) null comment '性别',
	constraint SYS_USER_pk
		primary key (id_)
)
comment '系统用户表';

create table SYS_USER_ROLE
(
	id_ bigint auto_increment comment '主键',
	sys_user_id_ BIGINT null comment '关联用户表主键',
	role_ varchar(50) null comment '角色名',
	constraint SYS_USER_ROLE_pk
		primary key (id_)
)
comment '用户角色表';

create table COURSE
(
	id_ bigint auto_increment comment '主键'
		primary key,
	name_ varchar(100) null comment '课程名称',
	description_ varchar(255) null comment '课程描述'
)
comment '课程表';

create table STUDENT_COURSE
(
	id_ bigint auto_increment,
	student_id_ bigint null comment '学生id 关联用户表',
	course_id_ bigint null comment '课程id 关联课程表',
	constraint STUDENT_COURSE_pk
		primary key (id_)
)
comment '学生选课表';

create table QUESTION
(
	id_ BIGINT auto_increment comment '主键',
	content_ text null comment '题目内容',
	type_ varchar(100) null,
	difficulty_ int null comment '难度',
	score_ decimal null comment '分数',
	answer_ varchar(100) null,
	course_id_ bigint comment '试题所属科目',
	constraint QUESTION_pk
		primary key (id_)
)
comment '题目';

create table EXAM_PAPER
(
    id_ bigint auto_increment,
    course_id_ bigint null comment '课程id',
    name_ varchar(255) null comment '试卷名称',
        primary key (id_)
)
    comment '试卷';

create table EXAM_PAPER_QUESTION
(
    id_ bigint auto_increment,
    exam_paper_id_ bigint null comment '试卷id',
    question_id_ bigint null comment '题目id',
    primary key (id_)
)
    comment '试卷题目';

create table EXAM
(
	id_ bigint auto_increment,
	student_id_ bigint null comment '学生id',
	course_id_ bigint null comment '课程id',
	grade_ varchar(50) null,
	constraint EXAM_pk
		primary key (id_)
)
comment '考试';

create table EXAM_DETAIL
(
	id_ bigint auto_increment comment '主键',
	exam_id_ bigint null comment '考试id',
	question_id_ bigint null comment '题目id',
	correct_ bit default 0 null comment '是否正确',
	constraint EXAM_DETAIL_pk
		primary key (id_)
)
comment '考试详情';












