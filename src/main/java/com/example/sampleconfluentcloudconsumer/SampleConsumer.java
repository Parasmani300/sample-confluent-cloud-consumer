package com.example.sampleconfluentcloudconsumer;

import com.example.sampleconfluentcloudconsumer.model.SampleModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
public class SampleConsumer {

    @KafkaListener(topics = "sample-topic",groupId = "sales-101",containerFactory = "my-container")
    public void consumeMessage(Object message) throws JsonProcessingException {
        ConsumerRecord<String,Object> consumerRecord = (ConsumerRecord<String, Object>) message;
//        System.out.println(consumerRecord.value());
        String jsonVal = consumerRecord.value().toString();

        ObjectMapper objectMapper = new ObjectMapper();
        SampleModel sampleModel =  objectMapper.convertValue(consumerRecord.value(), SampleModel.class);
        System.out.println(sampleModel);

    }
}
