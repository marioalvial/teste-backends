package teste.backends.proposal.core.events

import java.time.ZonedDateTime
import teste.backends.proposal.core.domain.Proposal

class ProponentRemovedEvent(
    override val id: String,
    override val timestamp: ZonedDateTime,
    override val proposalId: String,
    private val proponentId: String
) : Event {

    override fun apply(proposal: Proposal): Proposal = proposal
        .proponents
        .filter { it.id != proponentId }
        .let { proposal.copy(proponents = it) }
}
