package teste.backends.proposal.core.events

import teste.backends.proposal.core.domain.Proposal
import java.time.ZonedDateTime

class WarrantyRemovedEvent(
    override val id: String,
    override val timestamp: ZonedDateTime,
    override val proposalId: String,
    val warrantyId: String
) : Event() {

    override fun apply(proposal: Proposal): Proposal = proposal
        .warranties
        .filter { it.id != warrantyId }
        .let { proposal.copy(warranties = it) }
}
