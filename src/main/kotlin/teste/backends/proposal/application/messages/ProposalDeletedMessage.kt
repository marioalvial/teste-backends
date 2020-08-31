package teste.backends.proposal.application.messages

import teste.backends.proposal.application.InputValidator
import teste.backends.proposal.core.events.ProposalDeletedEvent
import java.time.ZonedDateTime
import javax.validation.constraints.NotBlank

class ProposalDeletedMessage(
    @field:NotBlank(message = "{field.not.blank}")
    private val eventId: String?,
    @field:NotBlank(message = "{field.not.blank}")
    private val timestamp: String?,
    @field:NotBlank(message = "{field.not.blank}")
    private val proposalId: String?
) : Message {

    init {
        InputValidator.validate(this)
    }

    override fun toEvent(): ProposalDeletedEvent = ProposalDeletedEvent(
        id = eventId!!,
        timestamp = ZonedDateTime.parse(timestamp!!),
        proposalId = proposalId!!
    )

    companion object {
        fun create(messageValues: List<String>) = ProposalDeletedMessage(
            eventId = messageValues[0],
            timestamp = messageValues[3],
            proposalId = messageValues[4]
        )
    }
}
