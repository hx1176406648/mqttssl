package com.yjf.mqttssl.clients;

import com.yjf.mqttssl.callbacks.PublishCallback;
import com.yjf.mqttssl.util.SslUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * @createTime : 2020/09/03 10:40
 * @autho : hx
 * @describe : null
 */
@Slf4j
public class MQTTPublishClient {
    //tcp://MQTT安装的服务器地址:MQTT定义的端口号
    public String HOST;
    //定义MQTT的ID，可以在MQTT服务配置中指定
    private String clientid ;

    private MqttClient client;
    @Setter
    @Getter
    private MqttTopic mqttTopic;

    /**
     * 构造函数
     * @throws MqttException
     */
    public MQTTPublishClient(String host,String serverId){
        log.info("MQTTPublishClient instance");
        HOST = host;
        clientid = serverId;
        // MemoryPersistence设置clientid的保存形式，默认为以内存保存
        try {
            if (client == null){
                client = new MqttClient(HOST, clientid, new MemoryPersistence());
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
        connect();
    }

    /**
     *  用来连接服务器
     */
    private void connect() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        // 设置超时时间
        options.setConnectionTimeout(20);
        // 设置会话心跳时间
        options.setKeepAliveInterval(10);
        options.setAutomaticReconnect(true);//设置自动重连
        try {
            options.setSocketFactory(SslUtil.getSocketFactory("D:/ProgramFiles/emqtt/emqttd/etc/certs/mycert/cacert.pem", "D:/ProgramFiles/emqtt/emqttd/etc/certs/mycert/clientcert.pem", "D:/ProgramFiles/emqtt/emqttd/etc/certs/mycert/clientkey.pem", "123456"));
            client.setCallback(new PublishCallback());
            client.connect(options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //发送消息并获取回执
    public void publish(String topic, MqttMessage message) throws MqttPersistenceException,
            MqttException, InterruptedException {
        log.info("publish   topic:  "+topic);
        mqttTopic = client.getTopic(topic);
        MqttDeliveryToken token = mqttTopic.publish(message);
        token.waitForCompletion();
        log.info("message is published completely! "
                + token.isComplete());
        log.info("messageId:" + token.getMessageId());
        token.getResponse();
        /*if (client.isConnected())
            client.disconnect(10000);*/
        log.info("Disconnected: delivery token \"" + token.hashCode()
                + "\" received: " + token.isComplete());
    }
}
