package teste.backends.proposal.core.events

import teste.backends.proposal.core.domain.Proposal
import java.time.ZonedDateTime

abstract class Event {
    abstract val id: String
    abstract val timestamp: ZonedDateTime
    abstract val proposalId: String

    abstract fun apply(proposal: Proposal): Proposal
}
