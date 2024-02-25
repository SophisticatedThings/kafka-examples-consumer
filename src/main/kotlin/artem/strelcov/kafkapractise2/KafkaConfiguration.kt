package artem.strelcov.kafkapractise2

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KafkaConfiguration {
    @Bean
    fun kafkaConsumer(): KafkaConsumer<String, Any> {
        val configProps: MutableMap<String, Any> = HashMap()
        configProps[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
        configProps[ConsumerConfig.GROUP_ID_CONFIG] = "group"
        configProps[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        configProps[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = CustomDeserializer::class.java
        return KafkaConsumer(configProps)
    }
}