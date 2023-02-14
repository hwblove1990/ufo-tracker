
package com.ufo.tracker.config;

import com.ufo.tracker.handler.TrackerUdpHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NettyUdpServer implements ApplicationRunner {


    public void initUdp(Integer port){
        EventLoopGroup bossLoopGroup = new NioEventLoopGroup();
        Bootstrap serverBootstrap = new Bootstrap();

        try {
            serverBootstrap.group(bossLoopGroup).
                    channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    .handler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            ChannelPipeline pipeline = channel.pipeline();
//                            pipeline.addLast(new TrackerUdpDecoder());
                            pipeline.addLast(new TrackerUdpHandler());
                        }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            log.info("netty udp start on  port {}", port);
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("netty udp init error", e);
        }finally {
            log.info("netty udp close!");
            bossLoopGroup.shutdownGracefully();
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        NettyUdpServer nettyUdpServer = new NettyUdpServer();
        nettyUdpServer.initUdp(9113);
    }
}
