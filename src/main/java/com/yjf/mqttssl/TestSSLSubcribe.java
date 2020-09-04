package com.yjf.mqttssl;

import com.yjf.mqttssl.clients.MQTTSubcribeClient;

/**
 * @createTime : 2020/09/03 10:41
 * @autho : hx
 * @describe : null
 */
public class TestSSLSubcribe {

    public static void main(String[] args) {
        MQTTSubcribeClient mqttReceiver = new MQTTSubcribeClient("ssl://127.0.0.1:8883", String.valueOf(Math.random()));
    }
}