package com.lqr;

/**
 * @创建者 CSDN_LQR
 * @描述 一个简单的消息封装
 */
public class Message {
    private String content;
    private boolean isFromMe;

    public Message(String content, boolean isFromMe) {
        this.content = content;
        this.isFromMe = isFromMe;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isFromMe() {
        return isFromMe;
    }

    public void setFromMe(boolean fromMe) {
        isFromMe = fromMe;
    }
}
