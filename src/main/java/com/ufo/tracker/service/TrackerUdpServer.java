package com.ufo.tracker.service;

import com.ufo.tracker.model.UdpTracker;
import io.netty.buffer.ByteBuf;
import io.netty.channel.socket.DatagramPacket;

import java.util.Map;

public interface TrackerUdpServer {


    byte[] trackeResponse(DatagramPacket datagramPacket);

    byte[] firstRe(ByteBuf buf);

    Map<String , Object> bufMap(ByteBuf buf);

    byte[] annouce(DatagramPacket datagramPacket);

    void peesUpdate(UdpTracker udpTracker);

    byte[] annouceRes(UdpTracker udpTracker);

}
