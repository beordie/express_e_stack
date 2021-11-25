# express_e_stack


## 1、 软件设计概要

### 1.1 设计目的

 &nbsp;   &nbsp;  在一般的快递管理系统中主要有3大类信息：用户管理、数据管理、快递管理。

 &nbsp;   &nbsp;  疫情期间大量人员不能聚集且不能大范围内的活动，人员闲置在家的同时加大了网络购物的发展，大量快件的运输和收取成为了一个困难。采用计算机进行信息的自动管理，不仅能减少人们的接触也能很好的完成社会服务任务，快递员通过录入用户快件信息，用户只需要确认取件码信息自主取件即可，既减少了人工取件的工作流程，也有效避免人员的接触。

### 1.2 特别说明

 &nbsp;   &nbsp;  在快递管理系统中主要有2大分模块：web端后台数据管理和微信端用户平台。后台数据管理包括管理员登陆、控制台总体信息显示、快递来源分布情况图、快递管理、用户管理、快递员管理；用户平台包括用户登陆（注册）、用户权限分配（快递员有快递助手的功能界面）、用户快递显示、用户信息查看、取件二维码查看、排名信息查看。

### 1.3 性能需求

 &nbsp;   &nbsp;  系统后台采用web端管理，界面功能展示清晰，便于管理员的操作；用户端部署在微信端，通过微信公众号进行推送，方便用户的使用，降低了使用难度。系统设置权限管理，明确用户相应的权限及对应的功能展示。

环境需求

 &nbsp;   &nbsp;  硬件环境：采用 `Windows 10` 开发

 &nbsp;   &nbsp;  软件环境： `MySQL5.7` + `JDK 11` + `Redis`

 &nbsp;   &nbsp;  开发环境：`JAVA` + `MySQL5.7`

## 2、 界面设计

### 2.1 登录启动界面
<center><img src="https://img-blog.csdnimg.cn/db3e4d9e35d644b7859da5bae9fe4580.png"   width="70%"></center>
<center>图2-1 管理员登录界面



<center><img src="https://img-blog.csdnimg.cn/6ed422506ba949269672a950e3cac20e.png"   width="70%"></center>
<center>图2-2 用户登陆登录界面

### 2.2 系统数据控制界面

<center><img src="https://img-blog.csdnimg.cn/531f89d4130943a8ac1565ff62d809cb.png"   width="70%"></center>
<center>图2-3 系统数据展示界面

<center><img src="https://img-blog.csdnimg.cn/4fd6e32049b9408797fcf1240991a03a.png"   width="70%"></center>
<center>图2-4 快递分布数据展示界面

### 2.3 用户信息界面

<center><img src="https://img-blog.csdnimg.cn/d784d061c1a44e82a4c45e86bf2c2da9.png"   width="70%"></center>
<center>图2-5 用户信息展示界面

<center><img src="https://img-blog.csdnimg.cn/47dad949af59482793d7dac4ffca5bd4.png"   width="70%"></center>
<center>图2-6 用户新增界面

<center><img src="https://img-blog.csdnimg.cn/42a3ef9ef3bc426f89feced7250e0362.png"   width="70%"></center>
<center>图2-7 用户更新界面

<center><img src="https://img-blog.csdnimg.cn/14e1697c77634de4880ac0afde0db198.png"   width="70%"></center>
<center>图2-8 用户删除界面

### 2.4 快递信息界面

<center><img src="https://img-blog.csdnimg.cn/65f4bf999f614b12a36c952ba24f176a.png"   width="70%"></center>
<center>图2-9 快递信息展示界面

<center><img src="https://img-blog.csdnimg.cn/af337d6f8b1043cbaeb0c83642678803.png"   width="70%"></center>
<center>图2-10 快递新增界面

<center><img src="https://img-blog.csdnimg.cn/6a50ae3186d94d4ea956073eb4775dcc.png"   width="70%"></center>
<center>图2-11 快递更新界面

<center><img src="https://img-blog.csdnimg.cn/19a80727c5f446d0ab2bc26dd3e07e3e.png"   width="70%"></center>
<center>图2-12 快递删除界面

### 2.5 微信用户界面
<center><img src="https://img-blog.csdnimg.cn/6ed422506ba949269672a950e3cac20e.png"   width="70%"></center>
<center>图2-13 微信用户登陆界面

<center><img src="https://img-blog.csdnimg.cn/6ed422506ba949269672a950e3cac20e.png"   width="70%"></center>
<center>图2-14 微信用户个人信息界面

### 2.6 微信用户操作界面

<center><img src="https://img-blog.csdnimg.cn/e084cfa643cf40dd894b5840cab6e7fe.png"   width="70%"></center>
<center>图2-15 微信用户操作界面

<center><img src="https://img-blog.csdnimg.cn/2356cf447ede4eb5b8cf3abd43e6f260.png"   width="70%"></center>
<center>图2-16 快递二维码展示界面

<center><img src="https://img-blog.csdnimg.cn/7954846786674eb5af32c78af18def34.png"   width="70%"></center>
<center>图2-17 用户快递展示界面

## 3、 系统分析

 &nbsp;   &nbsp;  为了编程的过程中，程序结构更加的简洁明了，采用 `*springMVC*` 开发模式进行开发，主要编制了以下几个模式层。

### 3.1 控制层

 &nbsp;   &nbsp;  作用：用于同前端的请求进行直接的交互，在拿到前端请求时携带的数据后，根据相应的请求地址调用服务层实现业务的编写；服务层返回数据后转换成json文件形式交由封装的工具类进行数据的传送。

### 3.2 服务层

 &nbsp;   &nbsp;  作用：连接控制层和数据层的中间结构，避免结构的过于耦合，系统的业务功能在此进行编写完善。从数据库请求到数据后，经过二次封装成前端需要的数据信息，再返回给控制层进行交互。

### 3.3 数据层

 &nbsp;   &nbsp;  作用：用于同数据库的直接交互，所有的需求性的SQL语句预先进行编译，根据服务层的不同请求来调用不同的数据请求，包括数据的增、删、改、查等功能。事务管理由服务层进行定义使用。服务层的数据请求先去对接*Redis*，查询无果之后才会连接数据库进行数据的查询。

### 3.4 模型层

 &nbsp;   &nbsp;  作用：负责系统中所有的对象模型的结构定义，符合 `*Javabean*` 的编写规范，和数据库的字段表对应，多表关系采用对象存储的形式进行表示，在查询时直接进行保存。

### 3.5 工具类

 &nbsp;   &nbsp;  作用：系统所需要的各种工具类定义在此包中，包括 `*Druid*` 数据库连接工具类、时间日期转换工具类，用户信息 `session`、 ` 存储工具类、 ` `json` 文件转换类等等工具类。

## 4、 数据库设计

### 4.1 系统数据表

<center>表4-1 数据表</center>
-----------------------------------------------------------------------

|    表    | 介绍                           |
| :------: | :----------------------------- |
| 管理员表 | 存放后台管理员的信息。         |
|  快递表  | 用于用快递的详细信息。         |
|  用户表  | 用于存放用户包括快递员的信息。 |

-----------------------------------------------------------------------

<center>表4-2 快递表</center>
-----------------------------------------------------------------------


|   字段   | 介绍                             |
| :------: | -------------------------------- |
|    ID    | 快递的唯一标识。                 |
|  NUMBER  | 快递单号，不可重复（四位数字）。 |
| COMPANY  | 快递的收录公司。                 |
|   CODE   | 快递的取件码，不可重复。         |
|  INTIME  | 快递的入库时间。                 |
| OUTTIME  | 快递的出库时间。                 |
|  STATUS  | 快递的状态（1为取件、0为未取件） |
| SYSPHONE | 进行信息录入的管理员电话。       |

-------------------- --------------------------------------------------
<center>表4-3 快递表
-----------------------------------------------------------------------
| 字段       | 介绍                               |
| ---------- | ---------------------------------- |
| ID         | 用户的唯一标识。                   |
| USERNAME   | 用户名，不可重复。                 |
| PASSWORD   | 用户密码。                         |
| LOGINIP    | 用户上一次登陆的远程IP地址。       |
| LOGINTIME  | 用户上一次登陆时间。               |
| CREATETIME | 用户账号创建的时间。               |
| PHONE      | 用户的手机号，不可重复。           |
| NUMBER     | 用户的身份证号码，不可重复。       |
| IDENTITY   | 用户的身份（1为用户、0为快递员）。 |
-------------------- --------------------------------------------------
###  4.2 数据表的连接关系

<center><img src="https://img-blog.csdnimg.cn/607553bbf3a542e39d15db6688ae9c09.jpg"   width="70%"></center>
<center>图4-1 数据库连接关系

## 5、 功能模块设计

### 5.1 系统功能结构

 &nbsp;   &nbsp;  管理员登录在web端进行数据的管理，用户登录在微信端实现取件等功能，每个管理模块下面又分为若干个小功能组，共同实现整个系统的功能。

<center><img src="https://img-blog.csdnimg.cn/dda5e2e241494fe69a184cfe877efbce.jpg"   width="70%"></center>
<center>图5-1 系统结构功能图

### 5.2 后台管理界面

#### 5.2.1 管理员登陆

 &nbsp;   &nbsp;  管理员通过账号和密码进行系统登录，前端js会针对账号和密码进行先期的验证，当满足条件时才会向服务器进行数据的提交，通过查询数据库来对登录的管理员进行验证，验证通过则可以进行后台管理的主页面，否则继续跳转登录界面。

<center><img src="https://img-blog.csdnimg.cn/503eaab497a84073b259ef984c3ebcd3.png"   width="100%"></center>
#### 5.2.2 快递分布管理

 &nbsp;   &nbsp;  登录成功后进入后台数据管理界面，首先展示的便是快递的分布管理，包括快递数量、用户数量等等后台的统计数据展示，以及一张对快递地址的统计缝补图。此界面的所有数据均由后台的数据库统计导出，不直接由管理员进行操作，管理员只具有查看的权限。
<center><img src="https://img-blog.csdnimg.cn/1840ae64eee24d97baf3a41eda1a6ca6.png"   width="100%"></center>
#### 5.2.3 快递管理

 &nbsp;   &nbsp;  快递的管理分为快递的新增、快递删除、快递修改、快递查询四个功能，新增时由管理员或者快递员进行新增，只上传一些具体的数据，其他的数据由系统自动给出，例如快递的取件码及录入时间等等；快递删除由快递的单号查询出快递之后，根据其数据库对应的ID号进行删除，ID号是一个隐藏表单域；快递更新也是先查询对应单号的快递信息之后在前台进行显示，快递员进行修改之后在提交给数据库进行数据的更新；快递查询分为分页查询和全查，由boostrap的格式进行定义。

<center><img src="https://img-blog.csdnimg.cn/5926cf1c729d4ff1bda3dc209cf62680.png"   width="100%"></center>
#### 5.2.4 用户管理

 &nbsp;   &nbsp;  用户管理分为用户的查询、用户删除、用户新增、用户更新四个功能。新增时由管理员进行数据的录入，同样只录入必要的信息，其余信息有系统自动给出；用户删除和用户更新也是由用户的手机号进行查询之后，将数据显示给前台，由管理员进行处理之后提交命令给数据库执行；用户查询由bootstrap框架进行控制。
<center><img src="https://img-blog.csdnimg.cn/2bdce141a94840b49697792cca7c93c6.png"   width="100%"></center>
#### 5.2.5 快递员管理

 &nbsp;   &nbsp;  快递员和用户的信息管理同属于一个表，对应的增删改查功能一致，由一个标记位进行控制，0代表该用户的身份为快递员，1代表该用户的身份为普通用户，快递员具有用户的所有功能权限，还具有快递员自身独有的功能权限。

### 5.3 用户平台管理

#### 5.3.1 用户登陆

 &nbsp;   &nbsp;  用户通过电话号码和验证码进行登陆，验证码由阿里云的短信验证码提供支持，由工具包进行引入。在输入数据之后用户可选择登陆和注册，如果用户是第一次登录则进行用户的注册再登陆，如果已经注册过就直接进行登录。登录成功之后将会跳转到主界面进行功能的展示，根据用户对应的权限（快递员和用户）进行不同功能的展示使用。
<center><img src="https://img-blog.csdnimg.cn/e72acad2115848d4a23de2039ebfef1b.png"   width="100%"></center>
#### 5.3.2 用户快递展示

 &nbsp;   &nbsp;  此界面主要用于对用户的所有快递信息进行统一展示，包括已取件快递和未取件快递，展示的数据界面通过流对象的过滤进行展示，未取件快递按照快递的录入时间进行排序，优先展示近期的快递；已取件快递按照取件时间进行展示，优先展示最近的取件信息。未取件快递下方还有一个二维码的入口位置，点击可进入该快递的二维码界面。

<center><img src="https://img-blog.csdnimg.cn/9c31cabd95e442d8883913c4c458af1e.png"   width="100%"></center>
#### 5.3.3 排名信息查看

 &nbsp;   &nbsp;  系统提供信息排名查看界面，排名按照用户的取件数量进行规定，分为月排名、年排名和总排名三个榜单，通过添加时间限定条件来完成榜单数据的查询。

#### 5.3.4 取件二维码查看

 &nbsp;   &nbsp;  二维码分为两类，一是用户的二维码，快递员扫描此二维码之后会获取当前用户的所有未取件快递信息，可执行取件操作；二是快递单独的二维码，扫码之后会出现当前快递的所有详细信息，也可执行区间操作。

#### 5.3.5 用户信息查看

 &nbsp;   &nbsp;  在点击用户的头像之后会进入用户的详情信息界面，如果用户是第一次进入会进行完善信息的提示，否则会直接显示详情。包含两个入口，一是用户取件码的展示，二是用户的详情信息查看。

## 6、 具体模块设计

### 6.1.  后台数据管理

#### 6.1.1 模块功能

 &nbsp;   &nbsp;  针对于整个系统的数据信息的管理，由最高管理员和快递管理员进行登录管理，但是具有不同的权限功能展示。管理员具有所有的系统权限管理，包括对快递管理员的信息管理，可以对所有数据进行操作，涵盖增、删、改、查功能。本模块在web端进行展示，采用ajax进行数据的异步交互，在不进行数据的整体刷新的前提下，确保数据的稳定传输，提高用户的使用感。用户的请求在处理之前会先进入到拦截器当中，拦截规则为如果用户未进行登陆开启对话则跳转到登陆界面，已登录则直接放行该请求，针对数据编码进行修正为UTF-8，防止出现前后端数据乱码的现象，拿到错误的数据。

#### 6.1.2 功能详细设计

 &nbsp;   &nbsp;  整个后台数据管理由管理员登陆、快递分布管理、快递管理、用户管理、快递员管理组成。

 &nbsp;   &nbsp;  **一）管理员登陆**

 &nbsp;   &nbsp;  管理员的登陆界面有两个输入输入框用于管理员的账号和密码的输入，用户在点击登陆之时会先按照数据格式规则进行先期的验证，当满足条件时才会向后端发起登陆请求（用户名为5-10位数字字母组成、密码由数字字母和特殊字符进行组成），如果验证不通过界面提示用户的输入格式错误，由用户进行修正之后再进行数据的提交。

 &nbsp;   &nbsp;  数据在达到服务器之后，先由控制层拿到请求携带的用户密码和用户名，再向服务层传递根据用户名和密码查询数据库信息的消息，服务层通过dao层拿到返回结果之后返回给控制层。再根据查询的结果进行判断，如果返回结果大于1，则数据库中有当前用户的信息，则反馈允许登陆，并将当前用户的查询信息保存在当前会话当中，确保其他功能开放给用户，否则反馈不允许登陆。

 &nbsp;   &nbsp;  前端拿到后端返回的数据之后进行状态判断，如果是成功则将请求定向到主界面，否则继续登陆并提示用户或密码错误信息。

```javascript
var regN = new RegExp(/^[-_a-zA-Z0-9]{5,10}/i);
var regP = new RegExp(/^(?:\d+|[a-zA-Z]+|[!@#$%^&*]+)$/)
if (regN.test(username) && regP.test(password)) {
    //1.    先使用layer，弹出load（提示加载中...）
    var windowId = layer.load();
    //2.    ajax与服务器交互
    $.post("/admin/login.udo",{username:username,password:password},function(data){
        //3.    关闭load窗口
        layer.close(windowId);
        //4.    将服务器回复的结果进行显示
        layer.msg(data.result);
        if(data.status == 0){
            //跳转到主页
            location.assign("/admin/index.html");
        }
        //
    },"JSON");
} else {
    window.alert("用户名或密码格式不正确");
}
```

 &nbsp;   &nbsp;  **二）快递分布管理**

 &nbsp;   &nbsp;  进入主界面之后会优先展示整体信息，包括已取快递数据、未取快递数据、快递员数据和用户数据，以及一个快递的分布展示，由数组进行信息的管理之后，通过图表的形式进行数据的可视化展示。此部分的数据管理员有权限进行查看，但不具备修改的权限，因为得确保数据的正确性和合理性。

```java 
	public int[] getConsoleData() {
        resultSet = super.query(SQL_CONSOLE, null);
        int[] data = null;
        try {
            if (resultSet.next()) {
                data = new int[4];
                data[0] = resultSet.getInt(1);
                data[1] = resultSet.getInt(2);
                data[2] = resultSet.getInt(3);
                data[3] = resultSet.getInt(4);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            super.close();
        }
        return data;
    }
```

**三）快递管理**

 &nbsp;   &nbsp;  快递管理下面分为四个小功能，包含快递的增、删、改、查功能。

 &nbsp;   &nbsp;  快递的查询页面直接发送全查的请求，按照分页进行数据的查询，默认查询界面为5条数据展示。

 &nbsp;   &nbsp;  快递新增时，管理员需要输入快递单号、快递公司、收件人姓名、收件人电话信息后将数据携带请求给后台进行数据的下一步处理，在进行实际请求之前会现针对数据进行验证（快单号为4位数字的随机组成、快递公司由快拉框进行选择、收件人姓名没有太多的要求，根据实际情况给出即可、手机号要符合市面通讯公司的手机号码的一般要求），验证通过后数据提交。后端拿到数据后会先生成快递的取件码、收录时间等信息后封装给数据库进行保存，之后将结果返还给前端进行展示，录入成功则清空当前的录入信息，反则停留在页面并继续展示之前输入的信息。

 &nbsp;   &nbsp;  快递删除时先根据快递的单号进行数据的查询显示，查询不到则提示错误并不展示删除的按键功能；成功则在下面进行数据信息的展示，数据的展示框只读不允许管理员进行更改，点击删除时会向后端发送根据此快递的id信息进行删除的请求，从而连接数据库进行数据的删除，id号由隐藏的表单域进行保存，此功能一般不会出现错误，因为只有查询到数据之后才会进行删除功能的展现。

 &nbsp;   &nbsp;  快递更新时也会先进行数据的查询展示后才会显示可以进行更新的字段信息，除了新增时的四个字段外还有一个快递的状态键，表示为已签收和未签收状态。如果管理员对用户的手机进行修改之后，系统的业务逻辑将会改变，不会直接向数据库进行更新，而是会重新向此电话的用户进行消息验证发送后再向数据库进行数据的保存。

```java
	@ResponseText("/express/findAll.udo")
    public String findAll(HttpServletRequest request, HttpServletResponse response) {
        //1.    获取查询数据的起始索引值
        String offsetS = request.getParameter("offset") == null ? "0" : request.getParameter("offset");
        int offset = Integer.parseInt(offsetS);
        //2.    获取当前页要查询的数据量
        String pageNumberS = request.getParameter("pageNumber") == null ? "5" : request.getParameter("pageNumber");
        int pageNumber = Integer.parseInt(pageNumberS);
        //3.    进行查询
        List<StandardExpress> list = service.getAllExpress(true, offset, pageNumber);
        List<Map<String, Integer>> result = service.getConsoleData();
        Integer total = result.get(0).get("data1_size");
        //4.    将集合封装为 bootstrap-table识别的格式
        ResultData<StandardExpress> data = new ResultData<>();
        data.setRows(list);
        data.setTotal(total);
        String json = JsonUtils.parseObject(data);
        return json;
    }
```

 &nbsp;   &nbsp;  **四）用户、快递员管理**

 &nbsp;   &nbsp;  用户和快递员的信息管理存储在一张表中，这里进行一起说明，也分为增、删、改、查四个小功能。

 &nbsp;   &nbsp;  快递员的查询数据和用户有一点差别，快递员拥有发件数量的展示字段，通过多表联查给出，查询时使用外联查询防止有的快递员因为没有派件而漏查数据。用户的查询就进行一般的分页查询显示。

 &nbsp;   &nbsp;  用户新增需要填入用户姓名、用户手机号、用户身份证和登录密码四个字段信息（每个字段都有相应的约束要求，不满足时不能进行后端请求的访问），向后端进行请求发送时采用post请求，因为数据携带了密码等私密数据。用户管理的控制器在拿到前端请求携带的数据后封装成用户对象向数据库发送插入新增请求，因为用户存在唯一性，因此可能会发生字段冲突的异常错误，这时候的数据不会进行提交，直接将错误信息及提示丢给前端展示给管理员。

 &nbsp;   &nbsp;  用户信息的修改需要先通过用户前期注册时录入的手机号进行数据信息的查询展示之后开启更新修改功能，如果未查询到将不能进行下一步的修改工作。查询结果包含的字段和录入时的字段一致，可直接进行修改之后更新提交，更新后的数据也需要满足对应的验证规则。

 &nbsp;   &nbsp;  用户信息删除和修改流程一致，也需要先进行查询后才能进行删除。

```java 
	@ResponseText("/admin/login.udo")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("username");
        String password = request.getParameter("password");

        // 消息结构
        Message msg = null;
        Admin admin = adminService.getByNamePass(name, password);
        // 判断密码和用户名是否正确
        if (admin != null) {
            String ip = request.getRemoteAddr();
            Date time = new Date();
            adminService.updateIpLoginTime(admin.getId(), ip, time);
            msg = new Message(0, "登陆成功");
            request.getSession().setAttribute("userName", admin.getName());
            request.getSession().setAttribute("userPhone", admin.getPhone());
        } else {
            msg = new Message(-1, "登陆失败");
        }
        String result = JsonUtils.parseObject(msg);
        return result;
    }
```

### 6.2 用户平台功能

####  &nbsp;  6.2.1 模块功能

 &nbsp;   &nbsp;  针对于微信端界面结构设计，主要针对用户使用进行开发，快递员也可以进行使用，根据权限展示不同的功能。用户在关注公众号之后会得到平台的入口网址，点击可直接进入首页进行登录访问，方便用户的操作、提高用户的体验感。

####  &nbsp;  6.2.2 功能详细设计

 &nbsp;   &nbsp;  整个用户服务模块分为用户登陆、用户快递展示、排名信息查看、取件二维码查看、用户信息查看等 5 个主要功能。

 &nbsp;   &nbsp;  **一）用户登陆**

 &nbsp;   &nbsp;  此处的用户登录由用户手机号和验证码进行登录，再输入正确的手机号后用户可点击获取验证码，此时的验证码会发送至用户的手机中，用户填写完毕后可以进行登陆（如果用户是第一次通过此手机号进行登录，会先进行用户的创建后进入系统），验证码的发送时间大概为60秒，有效期为30分钟，使用后将会在对话中删除此验证码的有效性。

```java 
	@ResponseText("/wx/cellCode.udo")
    public String getCode(HttpServletRequest request, HttpServletResponse response) {
        // 获取临时手机号的登陆验证码
        String userPhone = request.getParameter("userPhone");
        String cellCode = GenerationCode.generationCode();

        UserUtils.setTempInfo(request.getSession(), userPhone, cellCode);
        // 前端信息
        Message message = new Message();
        message.setResult("验证码: " + cellCode);
        message.setStatus(0);
        String json = JsonUtils.parseObject(message);
        return json;
    }
```

 &nbsp;   &nbsp;  **二）用户快递展示**

 &nbsp;   &nbsp;  用户快递分为已取件和未取件两大类别，通过其出库的取件时间进行区分，优先展示用户未进行取件的快递信息，按照入库时间进行排序后展示出来。未取件快递在展示详细信息的后面会有一个二维码取件的请求入口，点击可进入当前快递的二维码展示界面，快递员通过扫描此二维码可以拿到用户的快件信息。已取件快递展示在未取件的后面，按照其出库时间进行排序展示。

```java
	@ResponseText("/wx/getUserExpress.udo")
    public String getgetUserExpress(HttpServletRequest request, HttpServletResponse response) {
        String userPhone = request.getParameter("userPhone");
        // 查询手机号且没有出库的快递
        List<StandardExpress> result = expressService.getByUserPhoneAndStatus(userPhone, 0);
        Message message = new Message();
        if (result.size() == 0) {
            message.setStatus(-1);
            message.setResult("没有快递信息");
        } else {
            message.setStatus(0);
            message.setResult("查询成功");
            message.setData(result);
        }
        String json = JsonUtils.parseObject(message);
        return json;
    }
```

 &nbsp;   &nbsp;  **三）排名信息查看**

 &nbsp;   &nbsp;  用户在点击懒人排行榜功能按键时会进入排行榜界面，排序以快递数量作为标准，可分为三类，一是月排名，查询数据库以当月时间进行数量排序；二是年排名，以一年的所有信息作为基础；三是总排名，此排名没有时间限制，用户的所有快递信息都可计算在内。

 &nbsp;   &nbsp;  界面分为两栏，上方展示前三甲的用户姓名及其快递的数量，下方展示其他用户的信息，展示范围为前二十名。

```java
	@Override
    public List<Map<String, String>> getAllRank() {
        List<Map<String, String>> result = new ArrayList<>();
        try {
            resultSet = super.query(SQL_GET_ALL_RANK, null);
            while (resultSet.next()) {
                Map<String, String> temp = new HashMap<>();
                temp.put("name", resultSet.getString(1));
                temp.put("total", resultSet.getString(2));
                result.add(temp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            super.close();
        }
        return result;
    }
```

 &nbsp;   &nbsp;  **四）取件二维码展示**

 &nbsp;   &nbsp;  二维码展示界面可展示两种二维码，一是用户的自身二维码，此二维码包含用户的电话信息，可通过此信息查询出用户的所有未取件的快递信息；二是快件的自身二维码，此二维码包含快递的单号信息，可查询出该单号对应快件的所有信息。在主页有一个二维码的入口，为用户的自身二维码，另外一个自身二维码可通过用户信息界面的二维码查看进行进入。快件自身的二维码只能通过用户的快递信息界面的快件二维码进行进入。

```java
	@ResponseView("/wx/createQRCode.udo")
    public String createQRCode(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("code");
        //express | user
        String type = request.getParameter("type");
        String userPhone = null;
        String qRCodeContent = null;
        if("express".equals(type)){
            //快递二维码:被扫后,展示单个快递的信息
            qRCodeContent = "express_"+code;
        }else{
            //用户二维码:被扫后,快递员(柜子)端展示用户所有快递
            //userPhone
            userPhone = UserUtils.getUserInfo(request.getSession()).getUserPhone();
            qRCodeContent = "userPhone_"+userPhone;
        }
        HttpSession session = request.getSession();
        session.setAttribute("qrcode",qRCodeContent);
        return "/personQRcode.html";
    }
```

 &nbsp;   &nbsp;  **五）用户信息查看**

 &nbsp;   &nbsp;  用户在主界面可进入信息查看界面，包含用户的头像、姓名、二维码入口等信息，如果用户未进行信息的填写会提示需要进行信息的完善，这个请求可进入到用户的信息填写界面。用户填写完信息进行数据的更新后，下次进入将不会提示完善信息的通知信息，可直接进入进行信息的查看。

## 7、 总结

 &nbsp;   &nbsp;  通过此次课设，融汇了课堂学习的java知识，在加深课本知识的学习的基础上也学习了很多课外的知识。对项目进行需求分析和模块搭建时，更加明白了项目的工作流程，对后期的项目实施有了更清晰的思路。项目编码过程中遇见很多的难点，但通过上网查阅资料和询问老师的方式将问题进行了解决，过程中也明白了此类问题的出现原因，为以后规避此类问题有了基础。

 &nbsp;   &nbsp;  项目融合了前端和后端知识，编写过程中更加清晰的了解到前后端进行交互的流程，为今后的分模块开发打下了良好的基础。 项目的整体是基于sprinMVC进行开发的，过程中也再次体会到了这种解耦合开发的便捷性，在一次bug中只修改一处代码便实现了代码的整合，对后期的维护提供了遍历。

 &nbsp;   &nbsp;  下一步会根据实际的需求将系统进行更一步的完善，将系统设计得更加得到接近人们的生活。


息的填写会提示需要进行信息的完善，这个请求可进入到用户的信息填写界面。用户填写完信息进行数据的更新后，下次进入将不会提示完善信息的通知信息，可直接进入进行信息的查看。

## 7、 总结

 &nbsp;   &nbsp;  通过此次课设，融汇了课堂学习的java知识，在加深课本知识的学习的基础上也学习了很多课外的知识。对项目进行需求分析和模块搭建时，更加明白了项目的工作流程，对后期的项目实施有了更清晰的思路。项目编码过程中遇见很多的难点，但通过上网查阅资料和询问老师的方式将问题进行了解决，过程中也明白了此类问题的出现原因，为以后规避此类问题有了基础。

 &nbsp;   &nbsp;  项目融合了前端和后端知识，编写过程中更加清晰的了解到前后端进行交互的流程，为今后的分模块开发打下了良好的基础。 项目的整体是基于sprinMVC进行开发的，过程中也再次体会到了这种解耦合开发的便捷性，在一次bug中只修改一处代码便实现了代码的整合，对后期的维护提供了遍历。

 &nbsp;   &nbsp;  下一步会根据实际的需求将系统进行更一步的完善，将系统设计得更加得到接近人们的生活。



