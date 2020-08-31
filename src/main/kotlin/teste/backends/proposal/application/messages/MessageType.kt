package teste.backends.proposal.application.messages

enum class MessageType(val schema: String, val action: String) {

    PROPOSAL_CREATED("proposal", "created") {
        override fun createMessage(messageValues: List<String>) = ProposalCreatedMessage.create(messageValues)
    },
    PROPOSAL_UPDATED("proposal", "updated") {
        override fun createMessage(messageValues: List<String>) = ProposalUpdatedMessage.create(messageValues)
    },
    PROPOSAL_DELETED("proposal", "deleted") {
        override fun createMessage(messageValues: List<String>) = ProposalDeletedMessage.create(messageValues)
    },
    WARRANTY_ADDED("warranty", "added") {
        override fun createMessage(messageValues: List<String>) = WarrantyAddedMessage.create(messageValues)
    },
    WARRANTY_UPDATED("warranty", "updated") {
        override fun createMessage(messageValues: List<String>) = WarrantyUpdatedMessage.create(messageValues)
    },
    WARRANTY_REMOVED("warranty", "removed") {
        override fun createMessage(messageValues: List<String>) = WarrantyRemovedMessage.create(messageValues)
    },
    PROPONENT_ADDED("proponent", "added") {
        override fun createMessage(messageValues: List<String>) = ProponentAddedMessage.create(messageValues)
    },
    PROPONENT_UPDATED("proponent", "updated") {
        override fun createMessage(messageValues: List<String>) = ProponentUpdatedMessage.create(messageValues)
    },
    PROPONENT_REMOVED("proponent", "removed") {
        override fun createMessage(messageValues: List<String>) = ProponentRemovedMessage.create(messageValues)
    };

    abstract fun createMessage(messageValues: List<String>): Message

    companion object {
        fun of(schema: String, action: String) = values()
            .find { it.schema == schema && it.action == action }
    }
}
