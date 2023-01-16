package course_eLearning.course_eLearning.configuration;

import java.util.HashMap;
import java.util.Map;

import course_eLearning.course_eLearning.dto.CommentDTO;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import course_eLearning.course_eLearning.model.Comment;
import org.springframework.kafka.support.serializer.JsonSerializer;
import static course_eLearning.course_eLearning.constants.KafkaConstants.KAFKA_BROKER;

@Configuration
public class KafkaProducerConfig {
    @Bean
    public ProducerFactory<String, CommentDTO> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER);
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, CommentDTO> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}