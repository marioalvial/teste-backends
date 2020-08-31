package teste.backends.proposal.application.messages

import teste.backends.proposal.core.events.Event

interface Message {

    fun toEvent(): Event
}
