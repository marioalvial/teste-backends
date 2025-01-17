package teste.backends.proposal.core.events

import java.time.ZonedDateTime
import teste.backends.proposal.core.domain.Proposal

class WarrantyRemovedEvent(
    override val id: String,
    override val timestamp: ZonedDateTime,
    override val proposalId: String,
    private val warrantyId: String
) : Event {

    override fun apply(proposal: Proposal): Proposal = proposal
        .warranties
        .filter { it.id != warrantyId }
        .let { proposal.copy(warranties = it) }
}
