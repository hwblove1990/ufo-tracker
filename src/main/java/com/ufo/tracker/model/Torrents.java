package com.ufo.tracker.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Torrents {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 种子哈希
     */
    private byte[] infoHash;

    /**
     * 名称
     */
    private String name;

    /**
     * 文件名
     */
    private String filename;

    /**
     * 存储名称
     */
    private String saveAs;

    /**
     * 封面
     */
    private String cover;

    /**
     * 描述
     */
    private String descr;

    /**
     * 简介副标题
     */
    private String smallDescr;

    /**
     * 原始描述
     */
    private String oriDescr;

    /**
     * 类别
     */
    private Short category;

    /**
     * 来源
     */
    private Byte source;

    /**
     *
     */
    private Byte medium;

    /**
     *
     */
    private Byte codec;

    /**
     *
     */
    private Byte standard;

    /**
     *
     */
    private Byte processing;

    /**
     *
     */
    private Byte team;

    /**
     *
     */
    private Byte audiocodec;

    /**
     * 大小
     */
    private Long size;

    /**
     * 添加日期
     */
    private Date added;

    /**
     * 类型 1'single',2'multi'
     */
    private String type;

    /**
     * 文件数量
     */
    private Short numfiles;

    /**
     * 评论数
     */
    private Integer comments;

    /**
     * 浏览次数
     */
    private Integer views;

    /**
     * 点击次数
     */
    private Integer hits;

    /**
     * 完成次数
     */
    private Integer timesCompleted;

    /**
     * 下载数
     */
    private Integer leechers;

    /**
     * 做种数
     */
    private Integer seeders;

    /**
     * 最后操作日期
     */
    private Date lastAction;

    /**
     * 可见性
     */
    private Boolean visible;

    /**
     *
     */
    private String banned;

    /**
     * 拥有者
     */
    private Integer owner;

    /**
     *
     */
    private Byte spState;

    /**
     * 促销时间类型
     */
    private Byte promotionTimeType;

    /**
     * 促销截止日期
     */
    private Date promotionUntil;

    /**
     * 匿名
     */
    private Object anonymous;

    /**
     *
     */
    private Integer url;

    /**
     * 位置状态
     */
    private String posState;

    /**
     * 位置状态截止日期
     */
    private Date posStateUntil;

    /**
     *
     */
    private Byte cacheStamp;

    /**
     * 推荐类型
     */
    private Object picktype;

    /**
     * 推荐日期
     */
    private Date picktime;

    /**
     * 最后做种日期
     */
    private Date lastReseed;

    /**
     * ptgen生成内容
     */
    private String ptGen;

    /**
     *
     */
    private String technicalInfo;

    /**
     *
     */
    private Byte hr;

    /**
     * 审批状态
     */
    private Byte approvalStatus;
}

