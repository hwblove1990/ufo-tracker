package com.ufo.tracker.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TrackerUdpEnum {


    connectionId(0, 8, 0),
    action(8,4,0),
    transactionId(12,4,0),
    infoHash(16,20, 2),
    peerId(36,20, 1),
    downloaded(56,8, 0),
    left(64,8, 0),
    uploaded(72,8, 0),
    event(80,4, 0),
    ip(84,4, 1),
    key(88,4, 0),
    numWant(92,4, 0),
    port(96,2, 0),
    privateKey(101,32, 1);

    private int offset;
    private int len;
    private int type;




}
