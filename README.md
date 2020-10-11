# DistributedComputationExperiments

#### 分布式计算相关内容的实验

1. ATM 模拟器
    - 服务端是使用了主要基于 Socket, ServerSocket, Thread 等类实现的分布式框架
    - 用户端基于 JetBrains 提供的基于 XML 的 GUI Form 组件
    - 使用 JSON 格式的配置文件实现了简单的热部署和 AOP
2. 分布式订票平台
    - 大致沿用了前一个实验的框架和支付服务
    - 机票信息使用 Redis 进行缓存，基于求交集功能实现复杂查询
    - 用户端使用 Vue.JS 框架和 Bootstrap-Vue 实现响应式单页面应用
