package com.yjf.mqttssl.callbacks;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @createTime : 2020/09/03 10:40
 * @autho : hx
 * @describe : null
 */
@Slf4j
public class PublishCallback implements MqttCallbackExtended {

    @Override
    public void connectionLost(Throwable cause) {
        // 连接丢失后，一般在这里面进行重连
        log.info("[PublishCallback] 连接断开");
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        log.info("deliveryComplete---------" + token.isComplete());
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
    }

    @Override
    public void connectComplete(boolean b, String s) {
        log.info("connected");
    }


}