package com.beordie.model;

/**
 * @Classname Message
 * @Description 消息的统一相应格式
 * @Date 2021/6/25 12:45
 * @Created 30500
 */
public class Message {
    //状态码: 0成功，-1表示失败
    private Integer status;
    //消息内容
    private String result;
    //消息所携带的一组数据
    private Object data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Message(int status, String result) {
        this.status = status;
        this.result = result;
    }

    public Message(String result) {
        this.result = result;
    }

    public Message(int status) {
        this.status = status;
    }

    public Message() {
    }

    public Message(int status, String result, Object data) {
        this.status = status;
        this.result = result;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Message{" +
                "status=" + status +
                ", result='" + result + '\'' +
                ", data=" + data +
                '}';
    }
}
