package teste.backends.proposal.application.messages

import teste.backends.proposal.application.InputValidator
import teste.backends.proposal.core.events.ProponentRemovedEvent
import java.time.ZonedDateTime
import javax.validation.constraints.NotBlank

class ProponentRemovedMessage(
    @field:NotBlank(message = "{field.not.blank}")
    private val eventId: String?,
    @field:NotBlank(message = "{field.not.blank}")
    private val timestamp: String?,
    @field:NotBlank(message = "{field.not.blank}")
    private val proposalId: String?,
    @field:NotBlank(message = "{field.not.blank}")
    private val proponentId: String?
) : Message {

    init {
        InputValidator.validate(this)
    }

    override fun toEvent(): ProponentRemovedEvent = ProponentRemovedEvent(
        id = eventId!!,
        timestamp = ZonedDateTime.parse(timestamp!!),
        proposalId = proposalId!!,
        proponentId = proponentId!!
    )

    companion object {
        fun create(messageValues: List<String>) = ProponentRemovedMessage(
            eventId = messageValues[0],
            timestamp = messageValues[3],
            proposalId = messageValues[4],
            proponentId = messageValues[5]
        )
    }
}
