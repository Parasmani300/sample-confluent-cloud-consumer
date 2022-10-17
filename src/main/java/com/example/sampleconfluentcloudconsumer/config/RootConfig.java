package com.example.sampleconfluentcloudconsumer.config;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class RootConfig {

    public static final String GROUP_ID = "sales-101";

    @Value("${spring.kafka.properties.bootstrap.servers}")
    String bootStrapServer;

    @Value("${spring.profiles.active}")
    String activeProfile;

    @Value("${kafka.consumer.auto.commit.interval.ms}")
    int autoCommitValue;

    @Value("${kafka.max.poll.records}")
    int maxPollRecords;

    @Value("${kafka.max.poll.interval.ms}")
    int maxPoolInterval;

    @Value("${kafka.consumer.enable.auto.commit}")
    boolean kafkaConsumerEnableAutoCommit;

    @Value("${kafka.session.timeout.ms}")
    int kafkaSessionTimeOut;

    @Value("${kafka.heartbeat.interval.ms}")
    int heartbeatInterval;





    @Bean
    public ConsumerFactory<String,Object> consumerFactory()
    {
        JsonDeserializer<Object> deserializer = new JsonDeserializer<>(Object.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        String topic = "sample-topic";
        String sasl_username = "Mani";
        String sasl_password = "12345678";
        String truststore_location = "C:\\pass\\kafka.truststore.jks";
        String truststore_password = "mk5od0WM";
        String keystore_location = "C:\\pass\\kafka.keystore.jks";
        String keystore_password = "mk5od0WM";

        String jaasTemplate = "org.apache.kafka.common.security.plain.PlainLoginModule   required username='QGW3TV47IPDQKKHY'   password='nvjo7LiGulN131E7to6ZsC4YJep4X9jlevfiMOKbt+6+bpyORGWo//lqeCjG2RqX';";
        String jaasConfig = jaasTemplate;

        Map<String,Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootStrapServer);
        props.put(ConsumerConfig.GROUP_ID_CONFIG,GROUP_ID);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"latest");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,autoCommitValue);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,kafkaConsumerEnableAutoCommit);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,kafkaSessionTimeOut);
        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG,heartbeatInterval);
        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG,heartbeatInterval);
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,maxPollRecords);
        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG,maxPoolInterval);
//        if(activeProfile.equals("prod")) {
            props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
            props.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
            props.put(SaslConfigs.SASL_JAAS_CONFIG, jaasConfig);
//        }
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,deserializer);
//        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS,deserializer);
//        props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS,StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props,
                new StringDeserializer(),
                deserializer
        );
    }

    @Bean(name = "my-container")
    public ConcurrentKafkaListenerContainerFactory<String,Object> concurrentKafkaListenerContainerFactory()
    {
        ConcurrentKafkaListenerContainerFactory<String,Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        return  factory;
    }


}
