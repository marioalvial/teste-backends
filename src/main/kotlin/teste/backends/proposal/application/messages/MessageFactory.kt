package teste.backends.proposal.application.messages

import teste.backends.proposal.infrastructure.DLQPublisher

object MessageFactory {

    fun create(message: String): Message? {
        val messageValues = message.split(",")

        return runCatching {
            val eventSchema = messageValues[1]
            val eventAction = messageValues[2]

            MessageType.of(eventSchema, eventAction)?.createMessage(messageValues)
        }
            .onFailure { DLQPublisher.send(message) }
            .getOrNull()
    }
}
