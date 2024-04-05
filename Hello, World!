1 String和byte数组之间的转换
    · byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
    · String str = new String(bytes, StandardCharsets.UTF_8);
2 心跳机制只会在连接没有读写操作的时候才会触发，感觉就是做杂事的，没看到太大的实际用途
3 in.markReaderIndex(); in.readableBytes(); in.resetReaderIndex();三者联合使用，保证decode不出错
    · in.markReaderIndex(); // 标记当前读取位置
    · in.readableBytes(); // 获取可读字节数
    · in.resetReaderIndex(); // 重置读取位置
    总结：markReaderIndex只在读取最前面的时候使用，后续操作字节数不够了就直接回档，就像事务一样，保证原子化，保证in的数据不会丢失
