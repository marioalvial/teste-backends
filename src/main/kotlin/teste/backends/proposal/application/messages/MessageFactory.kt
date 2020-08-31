package teste.backends.proposal.application.messages

object MessageFactory {

    fun create(message: String): Message? {
        val messageValues = message.split(",")

        return runCatching {
            val eventSchema = messageValues[1]
            val eventAction = messageValues[2]

            MessageType.of(eventSchema, eventAction)?.createMessage(messageValues)
        }.getOrNull()
    }
}
