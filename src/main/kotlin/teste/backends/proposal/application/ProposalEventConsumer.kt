package teste.backends.proposal.application

import teste.backends.proposal.application.messages.MessageFactory
import teste.backends.proposal.core.ProposalHandler
import teste.backends.proposal.core.events.Event

object ProposalEventConsumer {

    fun consume(messages: List<String>) = messages
        .asSequence()
        .mapNotNull { MessageFactory.create(it) }
        .map { it.toEvent() }
        .fold(mutableMapOf<String, List<Event>>()) { map, event -> mapEvents(map, event) }
        .mapValues { ProposalHandler.validateProposal(it.value) }
        .filter { it.value }
        .keys
        .joinToString(",") { it }

    private fun mapEvents(map: MutableMap<String, List<Event>>, event: Event): MutableMap<String, List<Event>> {
        val currentEvents = map.getOrDefault(event.proposalId, emptyList())

        if (currentEvents.none { it.id == event.id }) {
            map[event.proposalId] = currentEvents.plus(event)
        }

        return map
    }
}
