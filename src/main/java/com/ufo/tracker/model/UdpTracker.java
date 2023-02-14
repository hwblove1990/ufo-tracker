package com.ufo.tracker.model;

import lombok.Data;

@Data
public class UdpTracker {


    private long connectionId;

    private int action;

    private int transactionId;

    /**
     * 种子文件info字段的SHA1值（20字节）
     * */
    private  byte[] infoHash;

    /**
     * 节点标识，由BT客户端每次启动时随机生成
     * */
    private String peerId;

    /**
     * 节点端口，主要用于跟其他节点交互
     * */
    private int port;

    /**
     * 总共上传的字节数，初始值为0
     * */
    private long uploaded;

    /**
     * 总共下载的字节数，初始值为0
     * */
    private long downloaded;

    /**
     * 文件剩余的待下载字节数
     * */
    private long left;

    /**
     * BT客户端期望得到的节点数
     * */
    private long  numWant;

    /**
     * BT客户端IP，选填的原因是Tracker可以得到请求的IP地址，不需要客户端直接上传
     * */
    private String  ip;

    /**
     * started/stopped/completed/空。当BT客户端开始种子下载时，第一个发起的请求为started，
     *
     * 在下载过程中，该值一直为空，直到下载完成后才发起completed请求。做种过程中，发送
     *
     * 的event也为空。如果BT客户端停止做种或退出程序，则会发起stopped请求。
     * */
    private long event;

    private String privateKey;

    private Integer torrent;
}
