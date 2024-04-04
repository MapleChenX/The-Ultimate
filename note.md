1 String和byte数组之间的转换
    · byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
    · String str = new String(bytes, StandardCharsets.UTF_8);
2 心跳机制只会在连接没有读写操作的时候才会触发，感觉就是做杂事的，没看到太大的实际用途