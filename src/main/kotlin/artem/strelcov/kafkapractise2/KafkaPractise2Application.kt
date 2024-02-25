package artem.strelcov.kafkapractise2

import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.concurrent.CompletableFuture


@SpringBootApplication
class KafkaPractise2Application(
    val consumer: KafkaConsumer<String, Any>
) {

    @Bean
    fun topic() = NewTopic("topic1", 10, 1)
    @Bean
    fun topic2() = NewTopic("topic2", 10, 1)

    @Bean
    fun check(): Boolean {
        // subscribe to the topics.
        val topics = listOf("topic1", "topic2")
        val customKafkaListener = CustomKafkaListener(topics, consumer)
        CompletableFuture.runAsync(customKafkaListener) // заместо обычного run, так как в таком случае мы не блокируем выполение основной программы
        println("customKafkaListener отработал")
        return false
    }

}
fun main(args: Array<String>) {
    runApplication<KafkaPractise2Application>(*args)
}
