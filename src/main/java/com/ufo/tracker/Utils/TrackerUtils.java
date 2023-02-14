package com.ufo.tracker.Utils;

import cn.hutool.core.util.ArrayUtil;
import io.netty.channel.socket.DatagramPacket;

public class TrackerUtils {

    public static long bytToLong(byte[] bs){
        int bytes = bs.length;

        switch(bytes) {
            case 0:
                return 0;
            case 1:
                return (long)((bs[0] & 0xff));
            case 2:
                return (long)((bs[0] & 0xff) <<8 | (bs[1] & 0xff));
            case 3:
                return (long)((bs[0] & 0xff) <<16 | (bs[1] & 0xff) <<8 | (bs[2] & 0xff));
            case 4:
                return (long)((bs[0] & 0xffL) <<24 | (bs[1] & 0xffL) << 16 | (bs[2] & 0xffL) <<8 | (bs[3] & 0xffL));
            case 5:
                return (long)((bs[0] & 0xffL) <<32 | (bs[1] & 0xffL) <<24 | (bs[2] & 0xffL) << 16 | (bs[3] & 0xffL) <<8 | (bs[4] & 0xffL));
            case 6:
                return (long)((bs[0] & 0xffL) <<40 | (bs[1] & 0xffL) <<32 | (bs[2] & 0xffL) <<24 | (bs[3] & 0xffL) << 16 | (bs[4] & 0xffL) <<8 | (bs[5] & 0xffL));
            case 7:
                return (long)((bs[0] & 0xffL) <<48 | (bs[1] & 0xffL) <<40 | (bs[2] & 0xffL) <<32 | (bs[3] & 0xffL) <<24 | (bs[4] & 0xffL) << 16 | (bs[5] & 0xffL) <<8 | (bs[6] & 0xffL));
            case 8:
                return (long)((bs[0] & 0xffL) <<56 | (bs[1] & 0xffL) << 48 | (bs[2] & 0xffL) <<40 | (bs[3] & 0xffL)<<32 |
                        (bs[4] & 0xffL) <<24 | (bs[5] & 0xffL) << 16 | (bs[6] & 0xffL) <<8 | (bs[7] & 0xffL));
            default:
                return 0;
        }
    }


    public static byte[] intToByte(Integer res) {
        byte[] result = new byte[4];
        // 由高位到低位
        result[0] = (byte) ((res >> 24) & 0xFF);
        result[1] = (byte) ((res >> 16) & 0xFF);
        result[2] = (byte) ((res >> 8) & 0xFF);
        result[3] = (byte) (res & 0xFF);
        return result;
    }

    public static byte[] shortTobytes2(Integer number) {
        byte[] abyte = new byte[2];
        abyte[0] = (byte) ((number >> 8) & 0xFF);
        abyte[1] = (byte) (0xFF & number);
        return abyte;
    }
}
