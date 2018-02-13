package com.jeffreycheng.sbkafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;

@Configuration
@EnableKafka
public class KafkaProducerConfig {

	// /resources/application.properties
	@Value("${spring.kafka.bootstrap-servers}")
	private String kafkaBootstrapServers;
	
	@Bean
	public ProducerFactory<String, Object> producerFactory() {
		DefaultKafkaProducerFactory<String, Object> defaultKafkaProducerFactory = new DefaultKafkaProducerFactory<>(producerConfigs());
		defaultKafkaProducerFactory.setTransactionIdPrefix("myTxId");
		return defaultKafkaProducerFactory;
	}
	
	@Bean
	public Map<String, Object> producerConfigs() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServers); // a list of host:port pairs to use for establishing the initial connection to the kafka cluster. This list should be in the form host1:port1,host2:port2,...
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class); // String encoding defaults to UTF8 and can be customized by setting the property key.serializer.encoding, value.serializer.encoding or serializer.encoding
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class); // possible values: IntegerSerializer, LongSerializer, StringSerializer, ByteArraySerializer, BytesSerializer... etc.
		configProps.put(ProducerConfig.ACKS_CONFIG, "ALL"); // the number of acks the producer requires the leader to have received before considering a request is completed. possible values: 0, 1, -1 (ALL)
		configProps.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432); // the bytes of memory the producer can use to buffer records waiting to be sent to the server. 
		configProps.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "none"); // the compression type for all data generated by the producer. The default is "none". Possible values: none, gzip, snappy, lz4
		configProps.put(ProducerConfig.RETRIES_CONFIG, 1); // setting a value greater than 0 will cause the client to resend any record whose send fails with a potentially transient error. Allowing retries without setting "max.in.flight.requests.per.connection" to 1 will potentially change the ordering of records. 
		configProps.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 1); // if retries config is not 0, setting it as 1 can prevent unordered messages
		configProps.put(ProducerConfig.BATCH_SIZE_CONFIG, 32768); // to batch records together into fewer requests (batch size in bytes) whenever multiple records are being sent to the same partition.
		configProps.put(ProducerConfig.CLIENT_ID_CONFIG, "demoSpringKafkaApp"); // An id string to pass to the server when making requests. The purpose is to be able to track the source of requests
		configProps.put(ProducerConfig.CONNECTIONS_MAX_IDLE_MS_CONFIG, 540000); // close an idle connections after the number of milliseconds
		configProps.put(ProducerConfig.LINGER_MS_CONFIG, 5); // The amount of artifical delay in ms to allow other records to be batch together before send.
		configProps.put(ProducerConfig.TRANSACTION_TIMEOUT_CONFIG, 10000); // The maximum amount of time in ms that the transaction coordinator will wait for a transaction status update from the producer. If waiting time exceeded, an "InvalidTransactionTimeout" error will be triggered.
		//configProps.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "demoSpringKafkaAppTransId"); // 
		configProps.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "demoSpringKafkaAppTransId"); // 
		configProps.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true); // If a transaction id is configured, "enable.idempotent" must be config as "true"; Possible value: true, false
		return configProps;
	}
	
	@Bean
	public KafkaTemplate<String, Object> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}
	
	@Bean
	KafkaTransactionManager<String, Object> kafkaTransactionManager() {
		return new KafkaTransactionManager<String, Object>(producerFactory());
		
	}
}
