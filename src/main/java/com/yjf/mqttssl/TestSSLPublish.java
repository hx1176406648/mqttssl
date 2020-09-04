package com.yjf.mqttssl;

import com.alibaba.fastjson.JSON;
import com.yjf.mqttssl.clients.MQTTPublishClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * @createTime : 2020/09/03 10:41
 * @autho : hx
 * @describe : null
 */
public class TestSSLPublish {

    public static void main(String[] args) throws Exception {
        //mqtt发送端
        MQTTPublishClient mqttClientSend = new MQTTPublishClient("ssl://39.102.64.202:1883", String.valueOf(Math.random()));
        MqttMessage message = new MqttMessage();
        Map msg = new HashMap<String,String>();
        msg.put("tag", "hello  ssl");
        message.setPayload(JSON.toJSONString(msg).getBytes("UTF-8"));
        message.setQos(0);
        message.setRetained(false);
        System.out.println("MQTT发送消息");
        mqttClientSend.publish("com/iot/init/ssl",message);
    }
}