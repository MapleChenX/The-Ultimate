1 String和byte数组之间的转换
    · byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
    · String str = new String(bytes, StandardCharsets.UTF_8);
2 心跳机制只会在连接没有读写操作的时候才会触发，感觉就是做杂事的，没看到太大的实际用途
3 in.markReaderIndex(); in.readableBytes(); in.resetReaderIndex();三者联合使用，保证decode不出错
    · in.markReaderIndex(); // 标记当前读取位置
    · in.readableBytes(); // 获取可读字节数
    · in.resetReaderIndex(); // 重置读取位置
    总结：markReaderIndex只在读取最前面的时候使用，后续操作字节数不够了就直接回档，就像事务一样，保证原子化，保证in的数据不会丢失
4 私有协议
    · 协议
        · cmdType(4字节) + filenameLen(4字节) + filename + contentLen(4字节) + content
    · 脚本
        · bytes(filename, 'utf-8') // string转为byte数组
        · filenameLen.to_bytes(4, byteorder='big') // int转为4字节的byte数组
5 登录
    · 流程：客户端发来命令和包体 -> 服务端解析命令 -> 封装该channel的session -> 多端T人 -> 服务端返回登录结果
6 登出
    · 流程：客户端发来命令和包体 -> 服务端解析命令 -> 删除session -> mq通知 -> 断开连接
7 mq通知
    · 主要机制：命令驱动型，通过cmd来选择发送到哪个交换机，并且根据cmd组装消息体
    · mq：
        · channel(被封装了，底层自动创建和关闭)--rabbitTemplate.convertAndSend(Const.CODE_SEND_EXCHANGER, Const.ROUTING_KEY_OF_REGISTER, jsonString);
        · exchange
        · queue
        总结：exchange --key--> queue
8 通知机制
    · 底层还是用的NioChannel，直接向channel写数据
    · 生产消息 -> mq发送 -> mq接受 -> mq解析(选择NioChannel和封装data) -> NioChannel.writeAndFlush(data)
9 服务之间通信
    · mq：不需要返回结果
    · feign：等待结果才下一步
10 消息发送
    · TCP -> 服务端 -> mq -> TCP
    · tcp -> service    一堆
    · service -> tcp    一个
    · 消息发送需要收到ack才持久化
11 实时通讯系统
    · 只处理消息业务：单聊、群聊；其余业务一律走service接口
    · 发送消息之前双方肯定要建立与服务端的连接，于是就有了登录和登出
    · 至于PING，一直更新着channel时间，虽然没感觉有什么用
12 优化
    · 实时性
        · 校验前置到TCP
        · 多线程分发
        · 异步持久化
    · 有序性
        · seq
    · 可靠性
        · 双重ACK
        · 定时重传，除非收到两个ack
    · 幂等性
        · redis缓存，比对成功就不入库；消息再次发送给目标用户，如果消息重复，自己做去重
13 增量拉取
    · 通过比对seq来向redis拉取最新信息(小批量多次循环拉取)
14 状态订阅
    · 上下线会给订阅者发送自己的登录状态
    · 数据结构采用redis的hash
15 消息撤回
    · 发送一个撤回包，目标客户端收到以后根据messageKey不显示该条消息
16 分布式
    · zookeeper做服务注册，采用zookeeper的唯一理由是有服务感知
    · 访问login接口后通过自定义算法实现负载均衡
    · 返回TCP节点地址，后续用户就根据该节点地址进行通信

问题：
    · 如何保证mq的可靠？如何保证mq业务处理的可靠？ -- 业务处理完成之后 channel.basicAck(deliveryTag, false);





    


    
