# property management system

#### 介绍
物业管理系统----中德F4

#### 软件架构
软件架构说明

### 前后端交互接口规范

#### 业主信息：

**分页查询（param：页数，每页显示数；return：数据数组）**

> 接口：<mark>/owner/{currentPage}/{pageSize}</mark>
> 请求方式：***GET***

**条件查询（param：业主信息ID、业主编号、业主姓名、房号；return：数据数组）**

> 接口：<mark>/owner/{currentPage}/{pageSize}</mark>
> 请求方式：***POST***

**信息添加（param：业主编号、姓名、房号、房屋面积、工作单位、联系电话）**

> 接口：<mark>/owner</mark>
> 请求方式：***POST***

**信息修改（param：信息ID，业主编号、姓名、房号、房屋面积、工作单位、联系电话**

> 接口：<mark>/owner</mark>
> 请求方式：***PUT***

**信息删除（param：业主信息ID）**

> 接口：<mark>/owner/{ownerId}</mark>
> 请求方式：***DELETE***

#### 员工信息

**分页查询（param：页数，每页显示数）**

> 接口：<mark>/emp/{currentPage}/{pageSize}</mark>
> 请求方式：***GET***

**条件查询（param：员工编号、电话号码、部门ID，部门名称）**

> 接口：<mark>/emp/{currentPage}/{pageSize}</mark>
> 请求方式：***POST***

**信息添加（param：员工编号、姓名、出生年月、性别、住址、联系电话、所在并部门ID、职务ID、密码）**

> 接口：<mark>/emp</mark>
> 请求方式：***POST***

**信息修改（param：信息ID，姓名、出生年月、性别、住址、联系电话、密码）**

> 接口：<mark>/emp</mark>
> 请求方式：***PUT***

**信息删除（param：员工信息ID）**

> 接口：<mark>/emp/{empId}</mark>
> 请求方式：***DELETE***

#### 部门信息

**信息查询（param：- ）**

> 接口：<mark>/dept</mark>
> 请求方式：***GET***

**信息删除（param：部门ID）**

> 接口：<mark>/emp/{deptId}</mark>
> 请求方式：***DELETE***

**信息修改（param：部门ID，部门名称、部门编号）**

> 接口：<mark>/dept</mark>
> 请求方式：***PUT***

**信息添加（param：部门名称，部门代码）**

> 接口：<mark>/dept</mark>
> 请求方式：***POST***

#### 职务（角色）

**数据查询（param：部门ID）**

> 接口：<mark>/role/{roleId}</mark>
> 请求方式：***GET***

**数据删除（param：职务（角色ID））**

> 接口：<mark>/role/{roleId}</mark>
> 请求方式：***DELETE***

**权限分配（param：职务（角色ID）、权限id数组）**

> 接口：<mark>/role/{roleId}</mark>
>
> 请求方式：***POST***

#### 登录

**登录（param：用户名、密码)**

> 接口：/login 请求方式：***POST***

#### 缴费处理

**分页查询（param：页数，每页显示数；return：数据数组）**

**条件查询（param：缴费信息ID，业主编号，员工号，收费日期；return：数据数组）**

**信息添加（param：房号，业主编号，收费日期，收费类型，收费金额，员工号）**

**信息修改（param：信息ID，房号，业主编号，收费日期，收费类型，收费金额，员工号）**

**信息删除（param：缴费信息ID）**

#### 历史缴费

**分页查询（param：页数，每页显示数；return：数据数组）**

**条件查询（param：缴费信息ID，业主编号，员工号，收费日期；return：数据数组）**

**导出（param：房号；return：数据数组）**

**信息删除（param：缴费信息ID)**

####