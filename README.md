# 苍穹外卖（sky-take-out）

基于 Spring Boot + Vue + 微信小程序的外卖点餐系统，分为**管理端**和**用户端（小程序）**两套界面。

> 本项目为跟随课程学习并自行实现/调试完成，代码结构参考《苍穹外卖》实战课程。

## 技术栈

| 分层 | 技术 |
|---|---|
| 后端 | Spring Boot 2.7、Spring MVC、MyBatis、MySQL 8、Redis、WebSocket |
| 认证 | JWT（管理端 / 用户端双令牌）、拦截器 |
| 文档 | Knife4j（Swagger2） |
| 其他 | 阿里云 OSS、百度地图开放平台、微信小程序登录、POI 导出 Excel |
| 前端 | Vue2 + TypeScript（管理端，见课程前端工程）、微信小程序（用户端） |

## 模块结构

```
sky-take-out
├── sky-common     公共模块：常量、工具类、异常、配置属性、拦截器
├── sky-pojo       实体/DTO/VO
└── sky-server     业务模块：controller / service / mapper / 配置 / 定时任务
```

## 主要功能

**管理端**：员工管理、分类/菜品/套餐管理、图片上传、订单管理（接单/拒单/派送/完成/取消）、店铺营业状态、数据统计（营业额/用户/订单/销量 Top10）、运营数据报表导出。

**用户端（小程序）**：微信登录、菜品浏览、购物车、收货地址簿、下单（含配送范围校验）、模拟支付、订单查询/取消、再来一单。

## 本地运行

1. 准备 MySQL 8、Redis，导入 `sky_take_out` 库的建表与初始数据脚本。
2. 复制配置模板并填入自己的参数：

   ```bash
   cd sky-server/src/main/resources
   cp application-dev.example.yml application-dev.yml
   ```

   需要填：数据库账号密码、Redis 密码、微信小程序 appid/secret、百度地图 AK，以及可选的阿里云 OSS。

3. 启动 `SkyApplication`，默认端口 8080，接口文档：`http://localhost:8080/doc.html`

## 说明

- **微信支付**：本仓库没有真实商户号与证书，`sky.pay.mock: true` 时会跳过与微信的交互，下单后直接把订单置为已支付，便于完整演示"下单 → 待接单 → 派送 → 完成"流程。接入真实支付需把该值改为 `false` 并提供商户证书。
- **敏感配置**：`application-dev.yml` 已被 `.gitignore` 排除，请勿提交真实密钥。
