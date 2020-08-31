package teste.backends.proposal.application.messages

import teste.backends.proposal.application.InputValidator
import teste.backends.proposal.core.events.ProposalUpdatedEvent
import java.time.ZonedDateTime
import javax.validation.constraints.NotBlank

class ProposalUpdatedMessage(
  @field:NotBlank(message = "{field.not.blank}")
  private val eventId: String?,
  @field:NotBlank(message = "{field.not.blank}")
  private val timestamp: String?,
  @field:NotBlank(message = "{field.not.blank}")
  private val proposalId: String?,
  @field:NotBlank(message = "{field.not.blank}")
  private val proposalLoanValue: String?,
  @field:NotBlank(message = "{field.not.blank}")
  private val proposalNumberOfMonthlyInstallments: String?
) : Message {

    init {
        InputValidator.validate(this)
    }

    override fun toEvent() = ProposalUpdatedEvent(
      id = eventId!!,
      timestamp = ZonedDateTime.parse(timestamp!!),
      proposalId = proposalId!!,
      proposalLoanValue = proposalLoanValue!!.toBigDecimal(),
      proposalNumberOfMonthlyInstallments = proposalNumberOfMonthlyInstallments!!.toInt()
    )

    companion object {
        fun create(messageValues: List<String>) = ProposalUpdatedMessage(
          eventId = messageValues[0],
          timestamp = messageValues[3],
          proposalId = messageValues[4],
          proposalLoanValue = messageValues[5],
          proposalNumberOfMonthlyInstallments = messageValues[6]
        )
    }
}
