package teste.backends.proposal.application

import teste.backends.proposal.application.messages.MessageFactory
import teste.backends.proposal.core.ProposalHandler
import teste.backends.proposal.core.events.Event

object ProposalEventConsumer {

    fun consume(messages: List<String>): String = messages
        .asSequence()
        .let { createEventMap(it) }
        .mapValues { ProposalHandler.validateProposal(it.value) }
        .filter { it.value }
        .keys
        .joinToString(",") { it }

    private fun createEventMap(messages: Sequence<String>): MutableMap<String, List<Event>> = messages
        .mapNotNull { MessageFactory.create(it) }
        .map { it.toEvent() }
        .fold(mutableMapOf()) { map, event -> mapEvents(map, event) }


    private fun mapEvents(map: MutableMap<String, List<Event>>, event: Event): MutableMap<String, List<Event>> {
        val currentEvents = map.getOrDefault(event.proposalId, emptyList())

        if (isEligibleToConsume(currentEvents, event)) {
            map[event.proposalId] = currentEvents.plus(event)
        }

        return map
    }

    private fun isEligibleToConsume(currentEvents: List<Event>, event: Event) = currentEvents.none {
        val eventAlreadyAdded = it.id == event.id
        val eventTypeAlreadyExists = it::class.isInstance(event::class)
        val eventIsOlderThanTheOneAlreadyAdded = it.timestamp.isAfter(event.timestamp)

        eventAlreadyAdded || (eventTypeAlreadyExists && eventIsOlderThanTheOneAlreadyAdded)
    }
}
