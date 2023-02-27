package com.ufo.tracker.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Peers {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 种子ID
     */
    private Integer torrent;

    /**
     * peerid
     */
    private String peerId;

    /**
     * IP
     */
    private String ip;

    /**
     * 端口
     */
    private int port;

    /**
     * 已上传
     */
    private long uploaded;

    /**
     * 已下载
     */
    private long downloaded;

    /**
     *
     */
    private long toGo;

    /**
     * 做种
     */
    private Boolean seeder;

    /**
     * 开始时间
     */
    private Date started;

    /**
     * 最后活动
     */
    private Date lastAction;

    /**
     * 上次活动
     */
    private Date prevAction;

    /**
     * 可连接
     */
    private Boolean connectable;

    /**
     * 用户ID
     */
    private Integer userid;

    /**
     * 用户客户端
     */
    private String agent;

    /**
     * 完成于
     */
    private Integer finishedat;

    /**
     *
     */
    private long downloadoffset;

    /**
     *
     */
    private long uploadoffset;

    /**
     *
     */
    private String passkey;

    /**
     *
     */
    private String ipv4;

    /**
     *
     */
    private String ipv6;

    /**
     * 是否盒子
     */
    private Boolean seedBox;

    /**
     * 0 – none
     * 1 – completed
     * 2 – started
     * 3 – stopped
     */
    private long event;
}

