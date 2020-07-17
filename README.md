# oa系统
### 功能列表：
+ 管理员功能
    + 管理员信息管理 -已完成
    + 休假类型管理 -已完成
    + 员工信息  -已完成
    + 部门信息管理 - 已完成
    + 请假列表- 已完成
    + 请假审批- 已完成
    + 统计- 已完成
    + 年假剩余时间 - 已完成
+ 员工功能
    + 修改信息 - 已完成
    + 请假- 已完成
        + 校验信息
        + 所填写的请假日期不能已经请过
        + 排除所有周末和法定节假日
        
+ 请假接口校验说明
    + 新增接口
        1. 校验请求参数
        2. 校验请假日期填写是否正确
        3. 判断当前日期已经是否请过
        4. 如果请的是年假，判断剩余年假时间是否大于所请的年假时间
        5. 判断年假是否超过本年度范围
        6. 计算请假时间
    + 更新接口
        1. 校验请求参数
        2. 判断当前假条是否已经审批过
        3. 判断当前日期已经是否请过
        4. 如果请的是年假，判断剩余年假时间是否大于所请的年假时间
        5. 判断年假是否超过本年度范围
        6. 重新计算请假时间
    + 管理员审批接口
        1. 校验请求参数
        2. 判断当前假条是否已经审批过
        3. 如果是年假，判断年假请假时长
        4. 年假，审批成功，重新计算年假时间
        5. 向该用户发送邮件提醒
    + 用户提交审批接口
        1. 参数校验
        2. 判断是否审批过
        3. 给相同部门的管理员发送邮件提醒

### 项目访问地址：

项目开源地址：[点击访问项目地址 ](https://github.com/xyqy/oa)

管理员：
[点击访问管理员 ](http://123.57.18.56:8083/#/login)  账号密码：admin

用户：
[点击访问用户](http://123.57.18.56:8082/#/login)    账号密码：123456 

### 效果图预览：
![http://123.57.18.56/img/1.png](http://123.57.18.56/img/1.png)
![http://123.57.18.56/img/2.png](http://123.57.18.56/img/2.png)
![http://123.57.18.56/img/3.png](http://123.57.18.56/img/3.png)
![http://123.57.18.56/img/4.png](http://123.57.18.56/img/4.png)
![http://123.57.18.56/img/5.png](http://123.57.18.56/img/5.png)



### 重要说明：
+ 请假信息排除了周末和节假日，只有整天，没有半天
+ 请假表state字段说明：
    + 0：默认值，待提交
    + 1：已经提交，等待审核
    + 2：审核成功
    + 3：审核失败
    
## 框架说明：
- controller只负责跳转
- 所有成功失败信息全在service回调
- 全局异常处理(有待优化)
- 日志输出(有待优化)
- 技术栈
    - spring boot 2.3.1.RELEASE
    - mybatis
    - lombok
    - druid 数据源
    - swagger
    - easyExcel 导出表格 
    - 发送邮件 
    - 定时任务
    
    - vue
    - axois 
    

### 配置类 (config) ：
+ GlobalExceptionHandler 全局统一异常
+ PageView 分页信息配置
+ ResultData 统一返回信息实体
+ SwaggerConfig swagger 配置
+ TaskConfig 定时任务配置

### 枚举类 (enums) ：
+ BooleanEnum 布尔值枚举
+ VacationTypeEnum 假期类型枚举
+ YearsEnum 年假枚举

### 定时任务 (job) ：
+ DateJob 用户年假信息定时任务

### 工具类 （util） ：
+ EmptyUtil 判空工具类
+ Md5Util md5加密工具类
+ DaysUtils 计算请假时长工具类

### 数据库设计：
+ admin 管理员表
+ department 员工部门表
+ vacation_type 休假类型表
+ vacation 请假
+ user 用户表

### 模块：
+ admin 管理员模块
+ department 员工模块
+ vacationtype 休假类型模块
+ vacation 请假
+ user 用户

### 2020-07-06 进度
1. 构建spring boot 项目
2. 在git上新建项目

### 2020-07-07 进度
1. 新建管理员数据库
2. 完成swagger配置
3. 完成项目的框架配置
4. 完成管理员增删改查功能
5. 设计部门表数据库
6. 完成部门增删改查
7. 增加全局异常处理

### 2020-07-08 进度
1. 完成假期类型功能
2. 新增使用Lombok
3. 完成用户模块的整体功能
4. 新增请假数据表
5. 完成请假的列表，新增功能

### 2020-07-09 进度
1. 完成请假的审批
2. 完成发送邮件功能
3. 完成前台的搭建
4. 完成前后台联调的基础功能
5. 完成导出表格功能

### 2020-07-10 进度
1. 完成员工的模块
2. 整体进行联调

### 2020-07-11 进度
1. 前后端分离部署
2. 完善请假模块

### 2020-07-13 进度
1. 修改完善信息
2. 新增2020年法定节假日
3. 修改前端的请假状态模块
4. 重写请假状态

### 2020-07-14 进度
1. 新增入职时间
2. 新增年假体系
3. 年假剩余时间计算

### 2020-07-15 进度
1. 新增枚举模块
2. 新增定时任务
3. 完成定时任务的配置

### 2020-07-16 进度
1. 修改下载文件接口
2. 新增统计图信息
3. 新增图表统计
4. 前后端联调测试
