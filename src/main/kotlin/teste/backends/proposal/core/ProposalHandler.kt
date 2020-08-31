package teste.backends.proposal.core

import teste.backends.proposal.core.domain.Proposal
import teste.backends.proposal.core.events.Event

object ProposalHandler {

    fun validateProposal(events: List<Event>) = events
        .fold(Proposal.empty()) { proposal, event -> event.apply(proposal) }
        .isValid()
}
