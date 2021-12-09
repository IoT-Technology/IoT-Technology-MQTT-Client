package iot.technology.mqtt.client;

import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.mqtt3.Mqtt3AsyncClient;
import lombok.extern.slf4j.Slf4j;

/**
 * @author mushuwei
 */
@Slf4j
public class ClientApplication {

	public static void main(String[] args) {
		String clientId = "my-mqtt-client-id";
		Mqtt3AsyncClient client = MqttClient.builder()
				.useMqttVersion3()
				.identifier(clientId)
				.serverHost("0.0.0.0")
				.serverPort(1883)
				.buildAsync();
		client.connectWith()
				.keepAlive(15)
				.send()
				.whenComplete((mqtt3ConnAck, throwable) -> {
					if (throwable != null) {
						log.error("client:{} connect fail", clientId);
						//TODO: doSomething
					} else {
						log.info("client:{} connect success", clientId);
					}
				});

	}
}
