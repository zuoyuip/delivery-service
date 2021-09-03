# 微信小程序后端API文档


<a name="overview"></a>
## 概览
微信小程序包裹信息平台接口说明


### 版本信息
*版本* : 1.0.0


### 联系方式
*名字* : zuoyu  
*邮箱* : zuoyuip@foxmail.com


### 许可信息
*许可证* : © 2019 zuoyu.top 版权所有 · 豫ICP备19014153号-1  
*服务条款* : https://www.zuoyu.top


### URI scheme
*域名* : 127.0.0.1:8080  
*基础路径* : /delivery


### 标签

* AdminCriteriaAPI : Admin Criteria Controller
* AdminDeliveryAPI : Admin Delivery Controller
* AdminUserAPI : Admin User Controller
* deliveryAPI : Delivery Controller
* meAPI : Me Controller
* userAPI : User Controller




<a name="paths"></a>
## 路径

<a name="selectsuggestusingget"></a>
### 获取所有建议反馈列表
```
GET /admin/criteria/allSuggest
```


#### 说明
注意：若返回状态码为204，表示没有信息


#### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Suggest](#suggest)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


#### 生成

* `application/json;charset=UTF-8`


#### 标签

* AdminCriteriaAPI


#### HTTP请求示例

##### 请求 path
```
/admin/criteria/allSuggest
```


#### HTTP响应示例

##### 响应 200
```
json :
{
  "suggestContent" : "string",
  "suggestDate" : "string",
  "suggestId" : "string",
  "suggestUserId" : "string"
}
```


<a name="dealwithcriteriausingput"></a>
### 审核申请处理
```
PUT /admin/criteria/deal/{userId}
```


#### 说明
注意：若返回状态码为500，表示服务器异常导致的反馈失败


#### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Path**|**userId**  <br>*必填*|账户ID|string|
|**Query**|**isPass**  <br>*必填*|是否通过申请|string|
|**Query**|**reviewMessage**  <br>*可选*|如果未通过申请，原因说明|string|


#### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


#### 消耗

* `multipart/form-data`


#### 生成

* `application/json;charset=UTF-8`


#### 标签

* AdminCriteriaAPI


#### HTTP请求示例

##### 请求 path
```
/admin/criteria/deal/string
```


##### 请求 query
```
json :
{
  "isPass" : "string",
  "reviewMessage" : "string"
}
```


#### HTTP响应示例

##### 响应 200
```
json :
{
  "data" : "object",
  "message" : "string"
}
```


<a name="getuserinfobyidusingget"></a>
### 根据唯一标识获取对应的用户详情信息
```
GET /admin/criteria/userInfo/{userInfoId}
```


#### 说明
注意：若返回状态码为204,表示没有该用户详情信息；若返回状态码为500,表示服务器异常


#### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Path**|**userInfoId**  <br>*必填*|用户详情信息实例的唯一标识|string|


#### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[UserInfo](#userinfo)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


#### 生成

* `application/json;charset=UTF-8`


#### 标签

* AdminCriteriaAPI


#### HTTP请求示例

##### 请求 path
```
/admin/criteria/userInfo/string
```


#### HTTP响应示例

##### 响应 200
```
json :
{
  "userInfoClass" : "string",
  "userInfoCollege" : "string",
  "userInfoId" : "string",
  "userInfoName" : "string",
  "userInfoPhotoUrl" : "string",
  "userInfoSex" : "string",
  "userInfoStudentNumber" : "string",
  "userInfoSubject" : "string",
  "userInfoThumbPhotoUrl" : "string"
}
```


<a name="selectwaitcriteriausingget"></a>
### 获取所有待审核的用户列表
```
GET /admin/criteria/wait
```


#### 说明
注意：若返回状态码为204，表示没有信息


#### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[User](#user)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


#### 生成

* `application/json;charset=UTF-8`


#### 标签

* AdminCriteriaAPI


#### HTTP请求示例

##### 请求 path
```
/admin/criteria/wait
```


#### HTTP响应示例

##### 响应 200
```
json :
{
  "reviewId" : "string",
  "userId" : "string",
  "userInfoId" : "string",
  "userIsAccountNonExpired" : true,
  "userIsAccountNonLocked" : true,
  "userIsByReview" : true,
  "userIsCredentialsNonExpired" : true,
  "userIsEnabled" : true,
  "userIsSubmitReview" : true,
  "userIsValid" : true,
  "userPassword" : "string",
  "userPhone" : "string"
}
```


<a name="getreviewbyidusingget"></a>
### 根据唯一标识获取对应的审核申请信息
```
GET /admin/criteria/wait/{reviewId}
```


#### 说明
注意：若返回状态码为204,表示没有该审核申请信息；若返回状态码为500,表示服务器异常


#### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Path**|**reviewId**  <br>*必填*|审核申请信息实例的唯一标识|string|


#### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Review](#review)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


#### 生成

* `application/json;charset=UTF-8`


#### 标签

* AdminCriteriaAPI


#### HTTP请求示例

##### 请求 path
```
/admin/criteria/wait/string
```


#### HTTP响应示例

##### 响应 200
```
json :
{
  "reviewDate" : "string",
  "reviewId" : "string",
  "reviewIsBy" : true,
  "reviewMessage" : "string",
  "userId" : "string"
}
```


<a name="selectdeliveryallusingget"></a>
### 获取所有的包裹信息
```
GET /admin/delivery/all
```


#### 说明
注意：若返回状态码为204，表示没有信息


#### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Delivery](#delivery)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


#### 生成

* `application/json;charset=UTF-8`


#### 标签

* AdminDeliveryAPI


#### HTTP请求示例

##### 请求 path
```
/admin/delivery/all
```


#### HTTP响应示例

##### 响应 200
```
json :
{
  "deliveryAddress" : "string",
  "deliveryCode" : "string",
  "deliveryDate" : "string",
  "deliveryDeliveryUserId" : "string",
  "deliveryGoalAddress" : "string",
  "deliveryGoalFloor" : "string",
  "deliveryId" : "string",
  "deliveryName" : "string",
  "deliveryOrderCode" : "string",
  "deliveryRemark" : "string",
  "deliveryReward" : "string",
  "deliverySexImageUrl" : "string",
  "deliveryStatus" : true,
  "deliveryUserId" : "string",
  "deliveryUserName" : "string",
  "deliveryUserPhone" : "string",
  "deliveryUserSex" : "string",
  "deliveryWeight" : "string"
}
```


<a name="selectdeliverynotreceiveusingget"></a>
### 获取所有未被领取的包裹信息
```
GET /admin/delivery/notReceive
```


#### 说明
注意：若返回状态码为204，表示没有信息


#### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Delivery](#delivery)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


#### 生成

* `application/json;charset=UTF-8`


#### 标签

* AdminDeliveryAPI


#### HTTP请求示例

##### 请求 path
```
/admin/delivery/notReceive
```


#### HTTP响应示例

##### 响应 200
```
json :
{
  "deliveryAddress" : "string",
  "deliveryCode" : "string",
  "deliveryDate" : "string",
  "deliveryDeliveryUserId" : "string",
  "deliveryGoalAddress" : "string",
  "deliveryGoalFloor" : "string",
  "deliveryId" : "string",
  "deliveryName" : "string",
  "deliveryOrderCode" : "string",
  "deliveryRemark" : "string",
  "deliveryReward" : "string",
  "deliverySexImageUrl" : "string",
  "deliveryStatus" : true,
  "deliveryUserId" : "string",
  "deliveryUserName" : "string",
  "deliveryUserPhone" : "string",
  "deliveryUserSex" : "string",
  "deliveryWeight" : "string"
}
```


<a name="selectdeliveryreceiveusingget"></a>
### 获取所有已经被领取的包裹信息
```
GET /admin/delivery/receive
```


#### 说明
注意：若返回状态码为204，表示没有信息


#### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Delivery](#delivery)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


#### 生成

* `application/json;charset=UTF-8`


#### 标签

* AdminDeliveryAPI


#### HTTP请求示例

##### 请求 path
```
/admin/delivery/receive
```


#### HTTP响应示例

##### 响应 200
```
json :
{
  "deliveryAddress" : "string",
  "deliveryCode" : "string",
  "deliveryDate" : "string",
  "deliveryDeliveryUserId" : "string",
  "deliveryGoalAddress" : "string",
  "deliveryGoalFloor" : "string",
  "deliveryId" : "string",
  "deliveryName" : "string",
  "deliveryOrderCode" : "string",
  "deliveryRemark" : "string",
  "deliveryReward" : "string",
  "deliverySexImageUrl" : "string",
  "deliveryStatus" : true,
  "deliveryUserId" : "string",
  "deliveryUserName" : "string",
  "deliveryUserPhone" : "string",
  "deliveryUserSex" : "string",
  "deliveryWeight" : "string"
}
```


<a name="selectallusingget"></a>
### 获取所有安全账户
```
GET /admin/user/all
```


#### 说明
该方法不可轻易调用


#### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[User](#user)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


#### 生成

* `application/json;charset=UTF-8`


#### 标签

* AdminUserAPI


#### HTTP请求示例

##### 请求 path
```
/admin/user/all
```


#### HTTP响应示例

##### 响应 200
```
json :
{
  "reviewId" : "string",
  "userId" : "string",
  "userInfoId" : "string",
  "userIsAccountNonExpired" : true,
  "userIsAccountNonLocked" : true,
  "userIsByReview" : true,
  "userIsCredentialsNonExpired" : true,
  "userIsEnabled" : true,
  "userIsSubmitReview" : true,
  "userIsValid" : true,
  "userPassword" : "string",
  "userPhone" : "string"
}
```


<a name="prohibituserusingput"></a>
### 根据唯一标识禁用该用户
```
PUT /admin/user/prohibit/{userId}
```


#### 说明
注意：若返回状态码为500,表示服务器异常


#### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Path**|**userId**  <br>*必填*|账户信息实例的唯一标识|string|


#### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


#### 消耗

* `application/json`


#### 生成

* `application/json;charset=UTF-8`


#### 标签

* AdminUserAPI


#### HTTP请求示例

##### 请求 path
```
/admin/user/prohibit/string
```


#### HTTP响应示例

##### 响应 200
```
json :
{
  "data" : "object",
  "message" : "string"
}
```


<a name="selectuserbyidusingget"></a>
### 根据唯一标识获取对应的账户信息
```
GET /admin/user/{userId}
```


#### 说明
注意：若返回状态码为204,表示没有该账户信息；若返回状态码为500,表示服务器异常


#### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Path**|**userId**  <br>*必填*|账户信息实例的唯一标识|string|


#### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[User](#user)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


#### 生成

* `application/json;charset=UTF-8`


#### 标签

* AdminUserAPI


#### HTTP请求示例

##### 请求 path
```
/admin/user/string
```


#### HTTP响应示例

##### 响应 200
```
json :
{
  "reviewId" : "string",
  "userId" : "string",
  "userInfoId" : "string",
  "userIsAccountNonExpired" : true,
  "userIsAccountNonLocked" : true,
  "userIsByReview" : true,
  "userIsCredentialsNonExpired" : true,
  "userIsEnabled" : true,
  "userIsSubmitReview" : true,
  "userIsValid" : true,
  "userPassword" : "string",
  "userPhone" : "string"
}
```


<a name="adddeliveryusingpost"></a>
### 添加包裹信息
```
POST /delivery
```


#### 说明
注意：若返回状态码为500，表示服务器异常导致的添加失败


#### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**deliveryAddress**  <br>*可选*|deliveryAddress包裹信息-包裹所在地址|string|
|**Query**|**deliveryCode**  <br>*可选*|deliveryCode包裹信息-快递取货号|string|
|**Query**|**deliveryDate**  <br>*可选*|deliveryDate包裹信息-日期|string (date-time)|
|**Query**|**deliveryDeliveryUserId**  <br>*可选*|deliveryDeliveryUserId包裹信息-包裹工作者的安全用户唯一标识|string|
|**Query**|**deliveryGoalAddress**  <br>*可选*|deliveryGoalAddress包裹信息-包裹要送达的地址|string|
|**Query**|**deliveryGoalFloor**  <br>*可选*|deliveryGoalFloor包裹信息-包裹要送达的楼号|string|
|**Query**|**deliveryId**  <br>*可选*|deliveryId包裹信息-唯一标识|string|
|**Query**|**deliveryName**  <br>*可选*|deliveryName包裹信息-快递名称|string|
|**Query**|**deliveryOrderCode**  <br>*可选*|deliveryOrderCode包裹信息-订单编号|string|
|**Query**|**deliveryRemark**  <br>*可选*|deliveryRemark包裹信息-备注|string|
|**Query**|**deliveryReward**  <br>*可选*|deliveryReward包裹信息-包裹的赏金|string|
|**Query**|**deliverySexImageUrl**  <br>*可选*|deliverySexImageUrl包裹信息-包裹收货人性别图片地址|string|
|**Query**|**deliveryStatus**  <br>*可选*|deliveryStatus包裹信息-包裹是否被接单|boolean|
|**Query**|**deliveryUserId**  <br>*可选*|deliveryUserId包裹信息-发布者的安全用户唯一标识|string|
|**Query**|**deliveryUserName**  <br>*可选*|deliveryUserName包裹信息-包裹的收货人名字|string|
|**Query**|**deliveryUserPhone**  <br>*可选*|deliveryUserPhone包裹信息-包裹的收货人手机号|string|
|**Query**|**deliveryUserSex**  <br>*可选*|deliveryUserSex包裹信息-包裹收货人性别|string|
|**Query**|**deliveryWeight**  <br>*可选*|deliveryWeight包裹信息-包裹重量|string|


#### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


#### 消耗

* `application/json`
* `application/x-www-form-urlencoded`


#### 生成

* `application/json;charset=UTF-8`


#### 标签

* deliveryAPI


#### HTTP请求示例

##### 请求 path
```
/delivery
```


##### 请求 query
```
json :
{
  "deliveryAddress" : "string",
  "deliveryCode" : "string",
  "deliveryDate" : "string",
  "deliveryDeliveryUserId" : "string",
  "deliveryGoalAddress" : "string",
  "deliveryGoalFloor" : "string",
  "deliveryId" : "string",
  "deliveryName" : "string",
  "deliveryOrderCode" : "string",
  "deliveryRemark" : "string",
  "deliveryReward" : "string",
  "deliverySexImageUrl" : "string",
  "deliveryStatus" : true,
  "deliveryUserId" : "string",
  "deliveryUserName" : "string",
  "deliveryUserPhone" : "string",
  "deliveryUserSex" : "string",
  "deliveryWeight" : "string"
}
```


#### HTTP响应示例

##### 响应 200
```
json :
{
  "data" : "object",
  "message" : "string"
}
```


<a name="selectallusingget_1"></a>
### 获取未被接单的包裹信息(只有简介信息，涉及重要私密信息不显示)
```
GET /delivery
```


#### 说明
注意：若返回状态码为204，表示没有信息


#### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Delivery](#delivery)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


#### 生成

* `application/json;charset=UTF-8`


#### 标签

* deliveryAPI


#### HTTP请求示例

##### 请求 path
```
/delivery
```


#### HTTP响应示例

##### 响应 200
```
json :
{
  "deliveryAddress" : "string",
  "deliveryCode" : "string",
  "deliveryDate" : "string",
  "deliveryDeliveryUserId" : "string",
  "deliveryGoalAddress" : "string",
  "deliveryGoalFloor" : "string",
  "deliveryId" : "string",
  "deliveryName" : "string",
  "deliveryOrderCode" : "string",
  "deliveryRemark" : "string",
  "deliveryReward" : "string",
  "deliverySexImageUrl" : "string",
  "deliveryStatus" : true,
  "deliveryUserId" : "string",
  "deliveryUserName" : "string",
  "deliveryUserPhone" : "string",
  "deliveryUserSex" : "string",
  "deliveryWeight" : "string"
}
```


<a name="transactiondeliveryusingput"></a>
### 根据包裹信息唯一标识接受该订单
```
PUT /delivery/transaction/{deliveryId}
```


#### 说明
若返回状态码为500,表示服务器异常


#### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Path**|**deliveryId**  <br>*必填*|包裹信息实例的唯一标识|string|
|**Query**|**authenticated**  <br>*可选*||boolean|
|**Query**|**authorities[0].authority**  <br>*可选*||string|
|**Query**|**credentials**  <br>*可选*||object|
|**Query**|**details**  <br>*可选*||object|
|**Query**|**principal**  <br>*可选*||object|


#### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


#### 消耗

* `application/json`


#### 生成

* `application/json;charset=UTF-8`


#### 标签

* deliveryAPI


#### HTTP请求示例

##### 请求 path
```
/delivery/transaction/string
```


##### 请求 query
```
json :
{
  "authenticated" : true,
  "authorities[0].authority" : "string",
  "credentials" : "object",
  "details" : "object",
  "principal" : "object"
}
```


#### HTTP响应示例

##### 响应 200
```
json :
{
  "data" : "object",
  "message" : "string"
}
```


<a name="getdeliverybyidusingget"></a>
### 根据唯一标识获取对应的包裹信息
```
GET /delivery/{deliveryId}
```


#### 说明
注意：若返回状态码为204,表示没有该包裹信息；若返回状态码为500,表示服务器异常


#### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Path**|**deliveryId**  <br>*必填*|包裹信息实例的唯一标识|string|


#### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Delivery](#delivery)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


#### 生成

* `application/json;charset=UTF-8`


#### 标签

* deliveryAPI


#### HTTP请求示例

##### 请求 path
```
/delivery/string
```


#### HTTP响应示例

##### 响应 200
```
json :
{
  "deliveryAddress" : "string",
  "deliveryCode" : "string",
  "deliveryDate" : "string",
  "deliveryDeliveryUserId" : "string",
  "deliveryGoalAddress" : "string",
  "deliveryGoalFloor" : "string",
  "deliveryId" : "string",
  "deliveryName" : "string",
  "deliveryOrderCode" : "string",
  "deliveryRemark" : "string",
  "deliveryReward" : "string",
  "deliverySexImageUrl" : "string",
  "deliveryStatus" : true,
  "deliveryUserId" : "string",
  "deliveryUserName" : "string",
  "deliveryUserPhone" : "string",
  "deliveryUserSex" : "string",
  "deliveryWeight" : "string"
}
```


<a name="canceldeliveryusingdelete"></a>
### 根据包裹信息唯一标识取消该订单
```
DELETE /delivery/{deliveryId}
```


#### 说明
若返回状态码为500,表示服务器异常


#### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Path**|**deliveryId**  <br>*必填*|包裹信息实例的唯一标识|string|


#### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**204**|No Content|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|


#### 生成

* `application/json;charset=UTF-8`


#### 标签

* deliveryAPI


#### HTTP请求示例

##### 请求 path
```
/delivery/string
```


#### HTTP响应示例

##### 响应 200
```
json :
{
  "data" : "object",
  "message" : "string"
}
```


<a name="listmedeliveryusingget"></a>
### 根据当前用户的唯一标识获取其发布的所有包裹信息
```
GET /me/deliveryUser/{deliveryUserId}
```


#### 说明
注意：若返回状态码为204,表示没有该包裹信息；若返回状态码为500,表示服务器异常


#### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Path**|**deliveryUserId**  <br>*必填*|当前用户的唯一标识|string|


#### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Delivery](#delivery)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


#### 生成

* `application/json;charset=UTF-8`


#### 标签

* meAPI


#### HTTP请求示例

##### 请求 path
```
/me/deliveryUser/string
```


#### HTTP响应示例

##### 响应 200
```
json :
{
  "deliveryAddress" : "string",
  "deliveryCode" : "string",
  "deliveryDate" : "string",
  "deliveryDeliveryUserId" : "string",
  "deliveryGoalAddress" : "string",
  "deliveryGoalFloor" : "string",
  "deliveryId" : "string",
  "deliveryName" : "string",
  "deliveryOrderCode" : "string",
  "deliveryRemark" : "string",
  "deliveryReward" : "string",
  "deliverySexImageUrl" : "string",
  "deliveryStatus" : true,
  "deliveryUserId" : "string",
  "deliveryUserName" : "string",
  "deliveryUserPhone" : "string",
  "deliveryUserSex" : "string",
  "deliveryWeight" : "string"
}
```


<a name="listmedeliveriesdeliveryusingget"></a>
### 根据当前用户的唯一标识获取其接收的所有包裹信息
```
GET /me/deliveryWorker/{deliveryDeliveryUserId}
```


#### 说明
注意：若返回状态码为204,表示没有该包裹信息；若返回状态码为500,表示服务器异常


#### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Path**|**deliveryDeliveryUserId**  <br>*必填*|当前用户的唯一标识|string|


#### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Delivery](#delivery)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


#### 生成

* `application/json;charset=UTF-8`


#### 标签

* meAPI


#### HTTP请求示例

##### 请求 path
```
/me/deliveryWorker/string
```


#### HTTP响应示例

##### 响应 200
```
json :
{
  "deliveryAddress" : "string",
  "deliveryCode" : "string",
  "deliveryDate" : "string",
  "deliveryDeliveryUserId" : "string",
  "deliveryGoalAddress" : "string",
  "deliveryGoalFloor" : "string",
  "deliveryId" : "string",
  "deliveryName" : "string",
  "deliveryOrderCode" : "string",
  "deliveryRemark" : "string",
  "deliveryReward" : "string",
  "deliverySexImageUrl" : "string",
  "deliveryStatus" : true,
  "deliveryUserId" : "string",
  "deliveryUserName" : "string",
  "deliveryUserPhone" : "string",
  "deliveryUserSex" : "string",
  "deliveryWeight" : "string"
}
```


<a name="getreviewbyuserusingget"></a>
### 根据唯一标识获取对应的审核申请信息
```
GET /me/review/{reviewId}
```


#### 说明
注意：若返回状态码为204,表示没有该审核申请信息；若返回状态码为500,表示服务器异常


#### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Path**|**reviewId**  <br>*必填*|审核申请信息实例的唯一标识|string|


#### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Review](#review)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


#### 生成

* `application/json;charset=UTF-8`


#### 标签

* meAPI


#### HTTP请求示例

##### 请求 path
```
/me/review/string
```


#### HTTP响应示例

##### 响应 200
```
json :
{
  "reviewDate" : "string",
  "reviewId" : "string",
  "reviewIsBy" : true,
  "reviewMessage" : "string",
  "userId" : "string"
}
```


<a name="submitcriteriausingpost"></a>
### 审核申请
```
POST /me/submitCriteria
```


#### 说明
注意：若返回状态码为500，表示服务器异常导致的反馈失败


#### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**userInfoClass**  <br>*可选*|userInfoClass用户信息-班级|string|
|**Query**|**userInfoCollege**  <br>*可选*|userInfoCollege用户信息-学院|string|
|**Query**|**userInfoId**  <br>*可选*|userInfoId用户信息的唯一标识|string|
|**Query**|**userInfoName**  <br>*可选*|userInfoName用户信息—名字|string|
|**Query**|**userInfoPhotoUrl**  <br>*可选*|userInfoPhotoUrl用户信息-审核图片|string|
|**Query**|**userInfoSex**  <br>*可选*|userInfoSex用户信息—性别|string|
|**Query**|**userInfoStudentNumber**  <br>*可选*|userInfoStudentNumber用户信息-学号|string|
|**Query**|**userInfoSubject**  <br>*可选*|userInfoSubject用户信息-专业|string|
|**Query**|**userInfoThumbPhotoUrl**  <br>*可选*|userInfoThumbPhotoUrl用户信息-审核缩略图片|string|
|**FormData**|**file**  <br>*必填*|file|file|


#### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


#### 消耗

* `multipart/form-data`
* `application/x-www-form-urlencoded`


#### 生成

* `application/json;charset=UTF-8`


#### 标签

* meAPI


#### HTTP请求示例

##### 请求 path
```
/me/submitCriteria
```


##### 请求 query
```
json :
{
  "userInfoClass" : "string",
  "userInfoCollege" : "string",
  "userInfoId" : "string",
  "userInfoName" : "string",
  "userInfoPhotoUrl" : "string",
  "userInfoSex" : "string",
  "userInfoStudentNumber" : "string",
  "userInfoSubject" : "string",
  "userInfoThumbPhotoUrl" : "string"
}
```


##### 请求 formData
```
json :
"file"
```


#### HTTP响应示例

##### 响应 200
```
json :
{
  "data" : "object",
  "message" : "string"
}
```


<a name="addsuggestusingpost"></a>
### 建议反馈
```
POST /me/suggest
```


#### 说明
注意：若返回状态码为500，表示服务器异常导致的反馈失败


#### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**suggestContent**  <br>*可选*|suggestContent建议反馈-反馈内容|string|
|**Query**|**suggestDate**  <br>*可选*|suggestDate建议反馈-反馈时间|string (date-time)|
|**Query**|**suggestId**  <br>*可选*|suggestId建议反馈的唯一标识|string|
|**Query**|**suggestUserId**  <br>*可选*|suggestUserId建议反馈-建议者的安全用户唯一标识|string|


#### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


#### 消耗

* `application/json`
* `application/x-www-form-urlencoded`


#### 生成

* `application/json;charset=UTF-8`


#### 标签

* meAPI


#### HTTP请求示例

##### 请求 path
```
/me/suggest
```


##### 请求 query
```
json :
{
  "suggestContent" : "string",
  "suggestDate" : "string",
  "suggestId" : "string",
  "suggestUserId" : "string"
}
```


#### HTTP响应示例

##### 响应 200
```
json :
{
  "data" : "object",
  "message" : "string"
}
```


<a name="registerusingpost"></a>
### 根据传入的安全用户实例信息进行注册
```
POST /user
```


#### 说明
注意：返回500表示服务器异常导致注册失败


#### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**FormData**|**authorities[0].authority**  <br>*可选*||string|
|**FormData**|**reviewId**  <br>*可选*|reviewId该帐号对应的审核信息唯一标识|string|
|**FormData**|**userId**  <br>*可选*|userId安全用户唯一标识|string|
|**FormData**|**userInfoId**  <br>*可选*|userInfoId该帐号对应用户信息的唯一标识|string|
|**FormData**|**userIsAccountNonExpired**  <br>*可选*|userIsAccountNonExpired该安全用户帐号是否未过期|boolean|
|**FormData**|**userIsAccountNonLocked**  <br>*可选*|userIsAccountNonLocked该安全用户帐号是否未锁定|boolean|
|**FormData**|**userIsByReview**  <br>*可选*|userIsByReview该安全用户帐号是否已通过审核|boolean|
|**FormData**|**userIsCredentialsNonExpired**  <br>*可选*|userIsCredentialsNonExpired该安全用户帐号凭证是否未过期|boolean|
|**FormData**|**userIsEnabled**  <br>*可选*|userIsEnabled该安全用户帐号是否启用|boolean|
|**FormData**|**userIsSubmitReview**  <br>*可选*|userIsSubmitReview该安全用户账户是否已提交用户审核|boolean|
|**FormData**|**userIsValid**  <br>*可选*|userIsValid该安全用户帐号是否已注册|boolean|
|**FormData**|**userPassword**  <br>*可选*|userPassword安全用户的密码|string|
|**FormData**|**userPhone**  <br>*可选*|userPhone安全用户帐号（手机号）|string|
|**FormData**|**verifyCode**  <br>*必填*|验证码|string|


#### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


#### 消耗

* `application/x-www-form-urlencoded`


#### 生成

* `application/json;charset=UTF-8`


#### 标签

* userAPI


#### HTTP请求示例

##### 请求 path
```
/user
```


##### 请求 formData
```
json :
"string"
```


#### HTTP响应示例

##### 响应 200
```
json :
{
  "data" : "object",
  "message" : "string"
}
```


<a name="selectallusingget_2"></a>
### 获取所有安全账户
```
GET /user
```


#### 说明
该方法不可轻易调用


#### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[User](#user)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


#### 生成

* `application/json;charset=UTF-8`


#### 标签

* userAPI


#### HTTP请求示例

##### 请求 path
```
/user
```


#### HTTP响应示例

##### 响应 200
```
json :
{
  "reviewId" : "string",
  "userId" : "string",
  "userInfoId" : "string",
  "userIsAccountNonExpired" : true,
  "userIsAccountNonLocked" : true,
  "userIsByReview" : true,
  "userIsCredentialsNonExpired" : true,
  "userIsEnabled" : true,
  "userIsSubmitReview" : true,
  "userIsValid" : true,
  "userPassword" : "string",
  "userPhone" : "string"
}
```


<a name="getcurrentuserusingget"></a>
### 获取当前的安全用户
```
GET /user/authentication
```


#### 说明
该方法仅适用客户端


#### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[User](#user)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


#### 生成

* `application/json;charset=UTF-8`


#### 标签

* userAPI


#### HTTP请求示例

##### 请求 path
```
/user/authentication
```


#### HTTP响应示例

##### 响应 200
```
json :
{
  "reviewId" : "string",
  "userId" : "string",
  "userInfoId" : "string",
  "userIsAccountNonExpired" : true,
  "userIsAccountNonLocked" : true,
  "userIsByReview" : true,
  "userIsCredentialsNonExpired" : true,
  "userIsEnabled" : true,
  "userIsSubmitReview" : true,
  "userIsValid" : true,
  "userPassword" : "string",
  "userPhone" : "string"
}
```


<a name="forgetuserusingpost"></a>
### 修改密码
```
POST /user/forgetUser
```


#### 说明
注意：若返回状态码为500，表示服务器异常导致的反馈失败


#### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**passWord**  <br>*必填*|密码|string|
|**Query**|**userPhone**  <br>*必填*|账户的手机号|string|
|**Query**|**verifyCode**  <br>*必填*|验证码|string|


#### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


#### 消耗

* `application/json`
* `application/x-www-form-urlencoded`


#### 生成

* `application/json;charset=UTF-8`


#### 标签

* userAPI


#### HTTP请求示例

##### 请求 path
```
/user/forgetUser
```


##### 请求 query
```
json :
{
  "passWord" : "string",
  "userPhone" : "string",
  "verifyCode" : "string"
}
```


#### HTTP响应示例

##### 响应 200
```
json :
{
  "data" : "object",
  "message" : "string"
}
```


<a name="modifyuserusingpost"></a>
### 修改手机号
```
POST /user/modifyUser
```


#### 说明
注意：若返回状态码为500，表示服务器异常导致的反馈失败


#### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**newUserPhone**  <br>*必填*|要更改为的手机号|string|
|**Query**|**passWord**  <br>*必填*|原密码|string|
|**Query**|**verifyCode**  <br>*必填*|验证码|string|


#### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


#### 消耗

* `application/json`
* `application/x-www-form-urlencoded`


#### 生成

* `application/json;charset=UTF-8`


#### 标签

* userAPI


#### HTTP请求示例

##### 请求 path
```
/user/modifyUser
```


##### 请求 query
```
json :
{
  "newUserPhone" : "string",
  "passWord" : "string",
  "verifyCode" : "string"
}
```


#### HTTP响应示例

##### 响应 200
```
json :
{
  "data" : "object",
  "message" : "string"
}
```


<a name="sendverificationforgetcodeusingget"></a>
### 获取重置验证码
```
GET /user/sendVerificationCode/forget/{phoneNumbers}
```


#### 说明
注意：若返回状态码为500,表示服务器异常


#### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Path**|**phoneNumbers**  <br>*必填*|用户手机号码|string|


#### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


#### 生成

* `application/json;charset=UTF-8`


#### 标签

* userAPI


#### HTTP请求示例

##### 请求 path
```
/user/sendVerificationCode/forget/string
```


#### HTTP响应示例

##### 响应 200
```
json :
{
  "data" : "object",
  "message" : "string"
}
```


<a name="sendverificationmodifycodeusingget"></a>
### 获取修改账户验证码
```
GET /user/sendVerificationCode/modify/{phoneNumbers}
```


#### 说明
注意：若返回状态码为500,表示服务器异常


#### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Path**|**phoneNumbers**  <br>*必填*|用户手机号码|string|


#### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


#### 生成

* `application/json;charset=UTF-8`


#### 标签

* userAPI


#### HTTP请求示例

##### 请求 path
```
/user/sendVerificationCode/modify/string
```


#### HTTP响应示例

##### 响应 200
```
json :
{
  "data" : "object",
  "message" : "string"
}
```


<a name="sendverificationregistercodeusingget"></a>
### 获取注册验证码
```
GET /user/sendVerificationCode/register/{phoneNumbers}
```


#### 说明
注意：若返回状态码为500,表示服务器异常


#### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Path**|**phoneNumbers**  <br>*必填*|用户手机号码|string|


#### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


#### 生成

* `application/json;charset=UTF-8`


#### 标签

* userAPI


#### HTTP请求示例

##### 请求 path
```
/user/sendVerificationCode/register/string
```


#### HTTP响应示例

##### 响应 200
```
json :
{
  "data" : "object",
  "message" : "string"
}
```


<a name="getuserinfobyidusingget_1"></a>
### 根据唯一标识获取对应的用户详情信息
```
GET /user/userInfo/{userInfoId}
```


#### 说明
注意：若返回状态码为204,表示没有该用户详情信息；若返回状态码为500,表示服务器异常


#### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Path**|**userInfoId**  <br>*必填*|用户详情信息实例的唯一标识|string|


#### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[UserInfo](#userinfo)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


#### 生成

* `application/json;charset=UTF-8`


#### 标签

* userAPI


#### HTTP请求示例

##### 请求 path
```
/user/userInfo/string
```


#### HTTP响应示例

##### 响应 200
```
json :
{
  "userInfoClass" : "string",
  "userInfoCollege" : "string",
  "userInfoId" : "string",
  "userInfoName" : "string",
  "userInfoPhotoUrl" : "string",
  "userInfoSex" : "string",
  "userInfoStudentNumber" : "string",
  "userInfoSubject" : "string",
  "userInfoThumbPhotoUrl" : "string"
}
```




<a name="definitions"></a>
## 定义

<a name="criteriamodel"></a>
### CriteriaModel
审核包装


|名称|说明|类型|
|---|---|---|
|**college**  <br>*可选*|**样例** : `"string"`|string|
|**criteriaTime**  <br>*可选*|**样例** : `"string"`|string (date-time)|
|**photoUrl**  <br>*可选*|**样例** : `"string"`|string|
|**reviewId**  <br>*可选*|**样例** : `"string"`|string|
|**sex**  <br>*可选*|**样例** : `"string"`|string|
|**studentNumber**  <br>*可选*|**样例** : `"string"`|string|
|**subject**  <br>*可选*|**样例** : `"string"`|string|
|**thumbPhotoUrl**  <br>*可选*|**样例** : `"string"`|string|
|**userClass**  <br>*可选*|**样例** : `"string"`|string|
|**userId**  <br>*可选*|**样例** : `"string"`|string|
|**userInfoId**  <br>*可选*|**样例** : `"string"`|string|
|**userName**  <br>*可选*|**样例** : `"string"`|string|
|**userPhone**  <br>*可选*|**样例** : `"string"`|string|


<a name="delivery"></a>
### Delivery
包裹信息


|名称|说明|类型|
|---|---|---|
|**deliveryAddress**  <br>*可选*|deliveryAddress包裹信息-包裹所在地址  <br>**样例** : `"string"`|string|
|**deliveryCode**  <br>*可选*|deliveryCode包裹信息-快递取货号  <br>**样例** : `"string"`|string|
|**deliveryDate**  <br>*可选*|deliveryDate包裹信息-日期  <br>**样例** : `"string"`|string (date-time)|
|**deliveryDeliveryUserId**  <br>*可选*|deliveryDeliveryUserId包裹信息-包裹工作者的安全用户唯一标识  <br>**样例** : `"string"`|string|
|**deliveryGoalAddress**  <br>*可选*|deliveryGoalAddress包裹信息-包裹要送达的地址  <br>**样例** : `"string"`|string|
|**deliveryGoalFloor**  <br>*可选*|deliveryGoalFloor包裹信息-包裹要送达的楼号  <br>**样例** : `"string"`|string|
|**deliveryId**  <br>*可选*|deliveryId包裹信息-唯一标识  <br>**样例** : `"string"`|string|
|**deliveryName**  <br>*可选*|deliveryName包裹信息-快递名称  <br>**样例** : `"string"`|string|
|**deliveryOrderCode**  <br>*可选*|deliveryOrderCode包裹信息-订单编号  <br>**样例** : `"string"`|string|
|**deliveryRemark**  <br>*可选*|deliveryRemark包裹信息-备注  <br>**样例** : `"string"`|string|
|**deliveryReward**  <br>*可选*|deliveryReward包裹信息-包裹的赏金  <br>**样例** : `"string"`|string|
|**deliverySexImageUrl**  <br>*可选*|deliverySexImageUrl包裹信息-包裹收货人性别图片地址  <br>**样例** : `"string"`|string|
|**deliveryStatus**  <br>*可选*|deliveryStatus包裹信息-包裹是否被接单  <br>**样例** : `true`|boolean|
|**deliveryUserId**  <br>*可选*|deliveryUserId包裹信息-发布者的安全用户唯一标识  <br>**样例** : `"string"`|string|
|**deliveryUserName**  <br>*可选*|deliveryUserName包裹信息-包裹的收货人名字  <br>**样例** : `"string"`|string|
|**deliveryUserPhone**  <br>*可选*|deliveryUserPhone包裹信息-包裹的收货人手机号  <br>**样例** : `"string"`|string|
|**deliveryUserSex**  <br>*可选*|deliveryUserSex包裹信息-包裹收货人性别  <br>**样例** : `"string"`|string|
|**deliveryWeight**  <br>*可选*|deliveryWeight包裹信息-包裹重量  <br>**样例** : `"string"`|string|


<a name="result"></a>
### Result
响应数据和信息的封装


|名称|说明|类型|
|---|---|---|
|**data**  <br>*可选*|响应数据  <br>**样例** : `"object"`|object|
|**message**  <br>*可选*|响应信息  <br>**样例** : `"string"`|string|


<a name="review"></a>
### Review
审核信息


|名称|说明|类型|
|---|---|---|
|**reviewDate**  <br>*可选*|reviewDate审核信息-审核日期  <br>**样例** : `"string"`|string (date-time)|
|**reviewId**  <br>*可选*|reviewId审核信息的唯一标识  <br>**样例** : `"string"`|string|
|**reviewIsBy**  <br>*可选*|reviewIsBy审核信息-审核是否已通过  <br>**样例** : `true`|boolean|
|**reviewMessage**  <br>*可选*|reviewMessage审核信息-审核说明  <br>**样例** : `"string"`|string|
|**userId**  <br>*可选*|userId审核信息-审核用户的唯一标识  <br>**样例** : `"string"`|string|


<a name="suggest"></a>
### Suggest
建议反馈


|名称|说明|类型|
|---|---|---|
|**suggestContent**  <br>*可选*|suggestContent建议反馈-反馈内容  <br>**样例** : `"string"`|string|
|**suggestDate**  <br>*可选*|suggestDate建议反馈-反馈时间  <br>**样例** : `"string"`|string (date-time)|
|**suggestId**  <br>*可选*|suggestId建议反馈的唯一标识  <br>**样例** : `"string"`|string|
|**suggestUserId**  <br>*可选*|suggestUserId建议反馈-建议者的安全用户唯一标识  <br>**样例** : `"string"`|string|


<a name="user"></a>
### User
安全账户


|名称|说明|类型|
|---|---|---|
|**reviewId**  <br>*可选*|reviewId该帐号对应的审核信息唯一标识  <br>**样例** : `"string"`|string|
|**userId**  <br>*可选*|userId安全用户唯一标识  <br>**样例** : `"string"`|string|
|**userInfoId**  <br>*可选*|userInfoId该帐号对应用户信息的唯一标识  <br>**样例** : `"string"`|string|
|**userIsAccountNonExpired**  <br>*可选*|userIsAccountNonExpired该安全用户帐号是否未过期  <br>**样例** : `true`|boolean|
|**userIsAccountNonLocked**  <br>*可选*|userIsAccountNonLocked该安全用户帐号是否未锁定  <br>**样例** : `true`|boolean|
|**userIsByReview**  <br>*可选*|userIsByReview该安全用户帐号是否已通过审核  <br>**样例** : `true`|boolean|
|**userIsCredentialsNonExpired**  <br>*可选*|userIsCredentialsNonExpired该安全用户帐号凭证是否未过期  <br>**样例** : `true`|boolean|
|**userIsEnabled**  <br>*可选*|userIsEnabled该安全用户帐号是否启用  <br>**样例** : `true`|boolean|
|**userIsSubmitReview**  <br>*可选*|userIsSubmitReview该安全用户账户是否已提交用户审核  <br>**样例** : `true`|boolean|
|**userIsValid**  <br>*可选*|userIsValid该安全用户帐号是否已注册  <br>**样例** : `true`|boolean|
|**userPassword**  <br>*可选*|userPassword安全用户的密码  <br>**样例** : `"string"`|string|
|**userPhone**  <br>*可选*|userPhone安全用户帐号（手机号）  <br>**样例** : `"string"`|string|


<a name="userinfo"></a>
### UserInfo
用户详细信息


|名称|说明|类型|
|---|---|---|
|**userInfoClass**  <br>*可选*|userInfoClass用户信息-班级  <br>**样例** : `"string"`|string|
|**userInfoCollege**  <br>*可选*|userInfoCollege用户信息-学院  <br>**样例** : `"string"`|string|
|**userInfoId**  <br>*可选*|userInfoId用户信息的唯一标识  <br>**样例** : `"string"`|string|
|**userInfoName**  <br>*可选*|userInfoName用户信息—名字  <br>**样例** : `"string"`|string|
|**userInfoPhotoUrl**  <br>*可选*|userInfoPhotoUrl用户信息-审核图片  <br>**样例** : `"string"`|string|
|**userInfoSex**  <br>*可选*|userInfoSex用户信息—性别  <br>**样例** : `"string"`|string|
|**userInfoStudentNumber**  <br>*可选*|userInfoStudentNumber用户信息-学号  <br>**样例** : `"string"`|string|
|**userInfoSubject**  <br>*可选*|userInfoSubject用户信息-专业  <br>**样例** : `"string"`|string|
|**userInfoThumbPhotoUrl**  <br>*可选*|userInfoThumbPhotoUrl用户信息-审核缩略图片  <br>**样例** : `"string"`|string|





