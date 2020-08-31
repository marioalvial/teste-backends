package teste.backends.proposal.core.events

import teste.backends.proposal.core.domain.Proposal
import teste.backends.proposal.core.domain.ProposalStatus
import java.time.ZonedDateTime

class ProposalDeletedEvent(
    override val id: String,
    override val timestamp: ZonedDateTime,
    override val proposalId: String
) : Event() {

    override fun apply(proposal: Proposal): Proposal = proposal.copy(
        status = ProposalStatus.DELETED
    )
}
