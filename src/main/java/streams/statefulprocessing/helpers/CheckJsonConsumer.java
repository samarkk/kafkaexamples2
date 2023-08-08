package streams.statefulprocessing.helpers;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import streams.statefulprocessing.model.Player;
import streams.statefulprocessing.serialization.json.JsonDeserializer;
import streams.statefulprocessing.serialization.json.JsonSerdes;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class CheckJsonConsumer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "master:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "ngr4");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Collections.singletonList("players"));


        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10000));
        System.out.println(records.count());

        for (ConsumerRecord<String, String> record : records) {
            System.out.println(record.value());
        }


    }
}
