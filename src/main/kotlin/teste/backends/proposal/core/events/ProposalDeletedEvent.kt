package teste.backends.proposal.core.events

import java.time.ZonedDateTime
import teste.backends.proposal.core.domain.Proposal
import teste.backends.proposal.core.domain.ProposalStatus

class ProposalDeletedEvent(
    override val id: String,
    override val timestamp: ZonedDateTime,
    override val proposalId: String
) : Event {

    override fun apply(proposal: Proposal): Proposal = proposal.copy(
        status = ProposalStatus.DELETED
    )
}
