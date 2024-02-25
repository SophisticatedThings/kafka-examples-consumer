package artem.strelcov.kafkapractise2

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.errors.SerializationException
import org.apache.kafka.common.serialization.Deserializer
import org.slf4j.LoggerFactory

class CustomDeserializer() : Deserializer<Any> {

    private val objectMapper = ObjectMapper()
    override fun configure(configs: MutableMap<String, *>?, isKey: Boolean) {
    }
    override fun deserialize(topic: String?, data: ByteArray?): Any? {
        try {
            if(data == null) {
                LOGGER.info("Не был передан массив байтов для десериализации")
                return null
            }
            return when(topic){
                "topic1" -> objectMapper.readValue(String(data, charset("UTF-8")), TransferObject::class.java)
                "topic2" -> objectMapper.readValue(String(data, charset("UTF-8")), TransferObjectAnother::class.java)
                else -> {}
            }
        }
        catch (ex : Exception) {
            val info = "Не удалось десериализовать объект"
            LOGGER.info(info)
            throw SerializationException(info)
        }
    }
    override fun close() {
    }

    companion object{
        private val LOGGER = LoggerFactory.getLogger(Companion::class.java)
    }
}