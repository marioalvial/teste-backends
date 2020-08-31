package teste.backends.proposal.core.events

import java.time.ZonedDateTime
import teste.backends.proposal.core.domain.Proposal

interface Event {
    val id: String
    val timestamp: ZonedDateTime
    val proposalId: String

    fun apply(proposal: Proposal): Proposal
}
