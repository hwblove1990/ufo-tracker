package com.ufo.tracker.handler;


import com.ufo.tracker.service.TrackerUdpServer;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class TrackerUdpHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    public static TrackerUdpHandler trackerUdpHandler;
    @Resource
    private TrackerUdpServer trackerUdpServer;

    @PostConstruct
    public void init() {
        trackerUdpHandler = this;
        trackerUdpHandler.trackerUdpServer = this.trackerUdpServer;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {

        channelHandlerContext.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(trackerUdpHandler.trackerUdpServer.trackeResponse(datagramPacket)), datagramPacket.sender()));

    }

}
