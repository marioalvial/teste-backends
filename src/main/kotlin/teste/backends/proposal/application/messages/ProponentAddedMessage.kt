package teste.backends.proposal.application.messages

import java.time.ZonedDateTime
import javax.validation.constraints.NotBlank
import teste.backends.proposal.application.InputValidator
import teste.backends.proposal.core.events.ProponentAddedEvent

class ProponentAddedMessage(
    @field:NotBlank(message = "{field.not.blank}")
    private val eventId: String?,
    @field:NotBlank(message = "{field.not.blank}")
    private val timestamp: String?,
    @field:NotBlank(message = "{field.not.blank}")
    private val proposalId: String?,
    @field:NotBlank(message = "{field.not.blank}")
    private val proponentId: String?,
    @field:NotBlank(message = "{field.not.blank}")
    private val proponentName: String?,
    @field:NotBlank(message = "{field.not.blank}")
    private val proponentAge: String?,
    @field:NotBlank(message = "{field.not.blank}")
    private val proponentMonthlyIncome: String?,
    @field:NotBlank(message = "{field.not.blank}")
    private val proponentIsMain: String?
) : Message {

    init {
        InputValidator.validate(this)
    }

    override fun toEvent(): ProponentAddedEvent = ProponentAddedEvent(
        id = eventId!!,
        timestamp = ZonedDateTime.parse(timestamp!!),
        proposalId = proposalId!!,
        proponentId = proponentId!!,
        proponentName = proponentName!!,
        proponentAge = proponentAge!!.toInt(),
        proponentMonthlyIncome = proponentMonthlyIncome!!.toBigDecimal(),
        proponentIsMain = proponentIsMain!!.toBoolean()
    )

    companion object {
        fun create(messageValues: List<String>) = ProponentAddedMessage(
            eventId = messageValues[0],
            timestamp = messageValues[3],
            proposalId = messageValues[4],
            proponentName = messageValues[5],
            proponentId = messageValues[6],
            proponentAge = messageValues[7],
            proponentMonthlyIncome = messageValues[8],
            proponentIsMain = messageValues[9]
        )
    }
}
