package teste.backends.proposal.core.events

import teste.backends.proposal.core.domain.Proposal
import java.math.BigDecimal
import java.time.ZonedDateTime

class ProposalUpdatedEvent(
    override val id: String,
    override val timestamp: ZonedDateTime,
    override val proposalId: String,
    val proposalLoanValue: BigDecimal,
    val proposalNumberOfMonthlyInstallments: Int
) : Event() {

    override fun apply(proposal: Proposal): Proposal = proposal.copy(
        loanValue = proposalLoanValue,
        numberOfMonthlyInstallments = proposalNumberOfMonthlyInstallments
    )
}
