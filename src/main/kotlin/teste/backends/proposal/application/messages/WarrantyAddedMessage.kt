package teste.backends.proposal.application.messages

import java.time.ZonedDateTime
import javax.validation.constraints.NotBlank
import teste.backends.proposal.application.InputValidator
import teste.backends.proposal.core.domain.Province
import teste.backends.proposal.core.events.WarrantyAddedEvent

class WarrantyAddedMessage(
    @field:NotBlank(message = "{field.not.blank}")
    private val eventId: String?,
    @field:NotBlank(message = "{field.not.blank}")
    private val timestamp: String?,
    @field:NotBlank(message = "{field.not.blank}")
    private val proposalId: String?,
    @field:NotBlank(message = "{field.not.blank}")
    private val warrantyId: String?,
    @field:NotBlank(message = "{field.not.blank}")
    private val warrantyValue: String?,
    @field:NotBlank(message = "{field.not.blank}")
    private val warrantyProvince: String?
) : Message {

    init {
        InputValidator.validate(this)
    }

    override fun toEvent(): WarrantyAddedEvent = WarrantyAddedEvent(
        id = eventId!!,
        timestamp = ZonedDateTime.parse(timestamp!!),
        proposalId = proposalId!!,
        warrantyId = warrantyId!!,
        warrantyValue = warrantyValue!!.toBigDecimal(),
        warrantyProvince = Province.of(warrantyProvince!!)
    )

    companion object {
        fun create(messageValues: List<String>) = WarrantyAddedMessage(
            eventId = messageValues[0],
            timestamp = messageValues[3],
            proposalId = messageValues[4],
            warrantyId = messageValues[5],
            warrantyValue = messageValues[6],
            warrantyProvince = messageValues[7]
        )
    }
}
