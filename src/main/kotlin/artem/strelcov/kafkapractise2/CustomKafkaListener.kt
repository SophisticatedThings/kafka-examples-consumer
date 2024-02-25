package artem.strelcov.kafkapractise2

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.TopicPartition
import java.time.Duration

class CustomKafkaListener(
    private val topics: List<String>,
    private val consumer: KafkaConsumer<String, Any>
): Runnable {
    override fun run() {
        consumer.subscribe(topics)
        while (true) {
            //consumer.seek(TopicPartition("topic1",1), 2)
            consumer.poll(Duration.ofMillis(100))
                .forEach { consumerRecord ->
                    when(val message = consumerRecord.value()) {
                        is TransferObject -> println(message.message)
                        is TransferObjectAnother -> println(message.quality)
                        else -> {}
                    }
                }
        }
    }

}