package teste.backends.proposal.core.events

import java.math.BigDecimal
import java.time.ZonedDateTime
import teste.backends.proposal.core.domain.Proposal
import teste.backends.proposal.core.domain.ProposalStatus

class ProposalCreatedEvent(
    override val id: String,
    override val timestamp: ZonedDateTime,
    override val proposalId: String,
    private val proposalLoanValue: BigDecimal,
    private val proposalNumberOfMonthlyInstallments: Int
) : Event {

    override fun apply(proposal: Proposal): Proposal = proposal.copy(
        id = proposalId,
        loanValue = proposalLoanValue,
        numberOfMonthlyInstallments = proposalNumberOfMonthlyInstallments,
        status = ProposalStatus.CREATED
    )
}
