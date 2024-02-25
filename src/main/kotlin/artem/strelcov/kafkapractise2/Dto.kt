package artem.strelcov.kafkapractise2

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeName
import com.fasterxml.jackson.databind.annotation.JsonNaming


class TransferObject(
    @JsonProperty("message")
    val message: String? = null,
    @JsonProperty("version")
    val version: Int? = null
)

class TransferObjectAnother(
    @JsonProperty("quality")
    val quality: Int? = null,
    @JsonProperty("essential")
    val isEssential: Boolean? = null
)

