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
public class SubcribeCallBack implements MqttCallbackExtended {

    @Override
    public void connectionLost(Throwable throwable) {
        log.warn("client lost connection,reconnecting");
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {

        try {
            // subscribe后得到的消息会执行到这里面
            log.info("接收消息主题 : " + s);
            log.info("接收消息Qos : " + mqttMessage.getQos());
            log.info("接收消息内容 : " + new String(mqttMessage.getPayload()));
            String str = mqttMessage.toString();
            log.info("从MQTT收到的消息为:" + str + " ;TOPIC:" + s);
        } catch (Exception e) {
            log.error("SubcribeCallBack error:" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        log.info("deliveryComplete---------" + iMqttDeliveryToken.isComplete());
    }

    @Override
    public void connectComplete(boolean b, String s) {
        log.info("receive connectted");
    }

}
