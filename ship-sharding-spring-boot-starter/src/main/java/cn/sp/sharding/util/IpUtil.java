package cn.sp.sharding.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author: Ship
 * @Description:
 * @Date: Created in 2023/6/8
 */
public class IpUtil {


    /**
     * 获取ip地址
     *
     * @return
     */
    public static String getLocalIpAddress() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return address.getHostAddress();
    }
}
