package teste.backends.proposal.application.messages

import java.time.ZonedDateTime
import javax.validation.constraints.NotBlank
import teste.backends.proposal.application.InputValidator
import teste.backends.proposal.core.events.WarrantyRemovedEvent

class WarrantyRemovedMessage(
    @field:NotBlank(message = "{field.not.blank}")
    private val eventId: String?,
    @field:NotBlank(message = "{field.not.blank}")
    private val timestamp: String?,
    @field:NotBlank(message = "{field.not.blank}")
    private val proposalId: String?,
    @field:NotBlank(message = "{field.not.blank}")
    private val warrantyId: String?
) : Message {

    init {
        InputValidator.validate(this)
    }

    override fun toEvent(): WarrantyRemovedEvent = WarrantyRemovedEvent(
        id = eventId!!,
        timestamp = ZonedDateTime.parse(timestamp!!),
        proposalId = proposalId!!,
        warrantyId = warrantyId!!
    )

    companion object {
        fun create(messageValues: List<String>) = WarrantyRemovedMessage(
            eventId = messageValues[0],
            timestamp = messageValues[3],
            proposalId = messageValues[4],
            warrantyId = messageValues[5]
        )
    }
}
