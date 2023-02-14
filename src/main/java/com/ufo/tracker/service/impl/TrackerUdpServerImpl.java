package com.ufo.tracker.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ufo.tracker.Utils.TrackerUtils;
import com.ufo.tracker.config.TrackerUdpEnum;
import com.ufo.tracker.mapper.PeersMapper;
import com.ufo.tracker.mapper.TorrentsMapper;
import com.ufo.tracker.model.Peers;
import com.ufo.tracker.model.Torrents;
import com.ufo.tracker.model.UdpTracker;
import com.ufo.tracker.service.TrackerUdpServer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.socket.DatagramPacket;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class TrackerUdpServerImpl  implements TrackerUdpServer {

    private final PeersMapper peersMapper;
    private final TorrentsMapper torrentsMapper;

    @Override
    public byte[] trackeResponse(DatagramPacket datagramPacket) {

        ByteBuf  buf  = datagramPacket.content();

        if( buf.readableBytes() == 16){
           return this.firstRe(buf);
        }else {

            return this.annouce(datagramPacket);
        }


    }

    @Override
    public byte[] firstRe(ByteBuf buf) {
        byte[] one =  ByteBufUtil.getBytes(buf,0,8);
        byte[] two =   ByteBufUtil.getBytes(buf, 8,4);
        byte[] three =   ByteBufUtil.getBytes(buf,12,4);
        byte[] newAll = ArrayUtil.addAll(two, three ,one);
        return newAll;
    }

    @Override
    public byte[] annouce(DatagramPacket datagramPacket) {
        ByteBuf  buf  = datagramPacket.content();
        UdpTracker udpTracker = JSONUtil.toBean( JSONUtil.toJsonStr(this.bufMap(buf)), UdpTracker.class);
        udpTracker.setIp(datagramPacket.sender().getAddress().getHostAddress());
        udpTracker.setPort(datagramPacket.sender().getPort());
        Torrents torrents = torrentsMapper.selectOne(Wrappers.<Torrents>lambdaQuery().eq(Torrents::getInfoHash , udpTracker.getInfoHash()));

        if (torrents == null){
            return TrackerUtils.intToByte(udpTracker.getTransactionId());
        }
        Integer torrentId = torrents.getId();
        udpTracker.setTorrent(torrentId);

        this.peesUpdate(udpTracker);

        return this.annouceRes(udpTracker);
    }

    @Override
    public Map<String , Object>   bufMap(ByteBuf buf){
        Map<String , Object> announce = new HashMap<>();
        for ( TrackerUdpEnum nn : TrackerUdpEnum.values() ){
             byte[] one =  ByteBufUtil.getBytes(buf,nn.getOffset(),nn.getLen());
            if(nn.getType() == 0){
                announce.put(nn.name(), TrackerUtils.bytToLong(one));

            }else if(nn.getType() == 1){
                announce.put(nn.name(), new String(one));

            }else {
                announce.put(nn.name(), one);
            }
        }
        return announce;
    }

    @Override
    public void peesUpdate(UdpTracker udpTracker){
        Peers peers = new Peers();
        BeanUtil.copyProperties(udpTracker, peers );
        peers.setPasskey(udpTracker.getPrivateKey());

        Peers tor =  peersMapper.selectOne(Wrappers.<Peers>lambdaQuery().eq(Peers::getTorrent, udpTracker.getTorrent()).eq(Peers::getPasskey, udpTracker.getPrivateKey()));
        if(tor == null){
            peersMapper.insert(peers);
        }else {
            peersMapper.update(peers, Wrappers.<Peers>lambdaQuery().eq(Peers::getTorrent, udpTracker.getTorrent()).eq(Peers::getPasskey, udpTracker.getPrivateKey()));
        }

    }

    @Override
    public byte[] annouceRes(UdpTracker udpTracker) {
        List<Peers> seedersAll = peersMapper.selectList(
                Wrappers.<Peers>lambdaQuery().eq(Peers::getTorrent, udpTracker.getTorrent())
                        .eq(Peers::getEvent,0).or().eq(Peers::getEvent, 1).orderByDesc(Peers::getLastAction).last(" limit " + udpTracker.getNumWant()));
        int seeder = seedersAll.size();
        int leecher = 0;
        if(seedersAll.size() < udpTracker.getNumWant()){
            Long limit =  udpTracker.getNumWant() - seedersAll.size();
            List<Peers> moreSeeders = peersMapper.selectList(
                    Wrappers.<Peers>lambdaQuery().eq(Peers::getTorrent, udpTracker.getTorrent())
                            .eq(Peers::getEvent,2).orderByDesc(Peers::getLastAction).last(" limit " + limit));
            seeder = moreSeeders.size();
            seedersAll.addAll(moreSeeders);
        }

        byte[] action =  TrackerUtils.intToByte(udpTracker.getAction());
        byte[] tranId =  TrackerUtils.intToByte(udpTracker.getTransactionId());
        byte[] interval = TrackerUtils.intToByte(60);
        byte[] leechers = TrackerUtils.intToByte(0);
        byte[] seeders = TrackerUtils.intToByte(seeder);
        byte[] all = ArrayUtil.addAll(action, tranId, interval, leechers, seeders);
        for (Peers p : seedersAll){
            String[] ips = p.getIp().split("\\.");
            byte[] ipss = new byte[4];
            ipss[0] = (byte)Integer.parseInt(ips[0]);
            ipss[1] = (byte)Integer.parseInt(ips[1]);
            ipss[2] = (byte)Integer.parseInt(ips[2]);
            ipss[3] = (byte)Integer.parseInt(ips[3]);

            all = ArrayUtil.addAll(all, ipss);
            all = ArrayUtil.addAll(all, TrackerUtils.shortTobytes2( p.getPort()));

        }

        return all;
    }
}
