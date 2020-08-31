package teste.backends.unit.application

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import teste.backends.proposal.application.messages.MessageType
import teste.backends.proposal.application.messages.ProponentAddedMessage
import teste.backends.proposal.application.messages.ProponentRemovedMessage
import teste.backends.proposal.application.messages.ProponentUpdatedMessage
import teste.backends.proposal.application.messages.ProposalCreatedMessage
import teste.backends.proposal.application.messages.ProposalDeletedMessage
import teste.backends.proposal.application.messages.ProposalUpdatedMessage
import teste.backends.proposal.application.messages.WarrantyAddedMessage
import teste.backends.proposal.application.messages.WarrantyRemovedMessage
import teste.backends.proposal.application.messages.WarrantyUpdatedMessage

class MessageTypeTest {

    @Nested
    @DisplayName("When creating a MessageType")
    inner class MessageTypeCreation {

        @Test
        fun `given proposal schema and created action should return PROPOSAL_CREATED`() {
            assertThat(MessageType.of("proposal", "created")).isEqualTo(MessageType.PROPOSAL_CREATED)
        }

        @Test
        fun `given proposal schema and updated action should return PROPOSAL_UPDATED`() {
            assertThat(MessageType.of("proposal", "updated")).isEqualTo(MessageType.PROPOSAL_UPDATED)
        }

        @Test
        fun `given proposal schema and deleted action should return PROPOSAL_DELETED`() {
            assertThat(MessageType.of("proposal", "deleted")).isEqualTo(MessageType.PROPOSAL_DELETED)
        }

        @Test
        fun `given warranty schema and added action should return WARRANTY_ADDED`() {
            assertThat(MessageType.of("warranty", "added")).isEqualTo(MessageType.WARRANTY_ADDED)
        }

        @Test
        fun `given warranty schema and updated action should return WARRANTY_UPDATED`() {
            assertThat(MessageType.of("warranty", "updated")).isEqualTo(MessageType.WARRANTY_UPDATED)
        }

        @Test
        fun `given warranty schema and removed action should return WARRANTY_REMOVED`() {
            assertThat(MessageType.of("warranty", "removed")).isEqualTo(MessageType.WARRANTY_REMOVED)
        }

        @Test
        fun `given proponent schema and added action should return PROPONENT_ADDED`() {
            assertThat(MessageType.of("proponent", "added")).isEqualTo(MessageType.PROPONENT_ADDED)
        }

        @Test
        fun `given proponent schema and updated action should return PROPOSAL_UPDATED`() {
            assertThat(MessageType.of("proponent", "updated")).isEqualTo(MessageType.PROPONENT_UPDATED)
        }

        @Test
        fun `given proponent schema and removed action should return PROPONENT_REMOVED`() {
            assertThat(MessageType.of("proponent", "removed")).isEqualTo(MessageType.PROPONENT_REMOVED)
        }

        @Test
        fun `given unknown schema and unknown action should return null`() {
            assertThat(MessageType.of("unknown", "unknown")).isNull()
        }
    }

    @Nested
    @DisplayName("When creating message by message type")
    inner class CreateMessageByType {

        @Test
        fun `given valid message should create ProposalCreatedMessage`() {
            val messageValues: List<String> = ("c2d06c4f-e1dc-4b2a-af61-ba15bc6d8610,proposal,created," +
                    "2019-11-11T13:26:04Z,bd6abe95-7c44-41a4-92d0-edf4978c9f4e,684397.0,72").split(",")

            val event = MessageType.PROPOSAL_CREATED.createMessage(messageValues)

            assertThat(event).isInstanceOf(ProposalCreatedMessage::class.java)
        }

        @Test
        fun `given valid message should create ProposalUpdatedMessage`() {
            val messageValues: List<String> = ("c2d06c4f-e1dc-4b2a-af61-ba15bc6d8610,proposal,updated," +
                    "2019-11-11T13:26:04Z,bd6abe95-7c44-41a4-92d0-edf4978c9f4e,684397.0,72").split(",")

            val event = MessageType.PROPOSAL_UPDATED.createMessage(messageValues)

            assertThat(event).isInstanceOf(ProposalUpdatedMessage::class.java)
        }

        @Test
        fun `given valid message should create ProposalDeletedMessage`() {
            val messageValues: List<String> = ("c2d06c4f-e1dc-4b2a-af61-ba15bc6d8610,proposal,deleted," +
                    "2019-11-11T13:26:04Z,bd6abe95-7c44-41a4-92d0-edf4978c9f4e").split(",")

            val event = MessageType.PROPOSAL_DELETED.createMessage(messageValues)

            assertThat(event).isInstanceOf(ProposalDeletedMessage::class.java)
        }

        @Test
        fun `given valid message should create WarrantyAddedMessage`() {
            val messageValues: List<String> = ("b51b30af-ab54-412c-b510-74a763cbf6e1,warranty,added," +
                    "2019-11-11T14:39:25Z,c5f8087d-2e5c-4e8f-9b9f-04d7016b7535," +
                    "446bd477-3a09-446a-95d5-17ac9b2a53ad,3600105.62,DF").split(",")

            val event = MessageType.WARRANTY_ADDED.createMessage(messageValues)

            assertThat(event).isInstanceOf(WarrantyAddedMessage::class.java)
        }

        @Test
        fun `given valid message should create WarrantyUpdatedMessage`() {
            val messageValues: List<String> = ("b51b30af-ab54-412c-b510-74a763cbf6e1,warranty,updated," +
                    "2019-11-11T14:39:25Z,c5f8087d-2e5c-4e8f-9b9f-04d7016b7535," +
                    "446bd477-3a09-446a-95d5-17ac9b2a53ad,3600105.62,DF").split(",")

            val event = MessageType.WARRANTY_UPDATED.createMessage(messageValues)

            assertThat(event).isInstanceOf(WarrantyUpdatedMessage::class.java)
        }

        @Test
        fun `given valid message should create WarrantyRemovedMessage`() {
            val messageValues: List<String> = ("c2d06c4f-e1dc-4b2a-af61-ba15bc6d8610,warranty,removed," +
                    "2019-11-11T13:26:04Z,bd6abe95-7c44-41a4-92d0-edf4978c9f4e, 446bd477-3a09-446a-95d5-17ac9b2a53ad")
                .split(",")

            val event = MessageType.WARRANTY_REMOVED.createMessage(messageValues)

            assertThat(event).isInstanceOf(WarrantyRemovedMessage::class.java)
        }

        @Test
        fun `given valid message should create ProponentAddedMessage`() {
            val messageValues: List<String> = ("2e0a60c7-581a-47f3-8060-60bf2edfba25,proponent,added," +
                    "2019-11-11T14:39:25Z,82391a60-e9bc-46e6-832b-eaf851cba017,10a6406f-8f6f-4291-8daa-c50320084d63," +
                    "Shane Conroy Jr.,53,68671.14,true").split(",")

            val event = MessageType.PROPONENT_ADDED.createMessage(messageValues)

            assertThat(event).isInstanceOf(ProponentAddedMessage::class.java)
        }

        @Test
        fun `given valid message should create ProponentUpdatedMessage`() {
            val messageValues: List<String> = ("2e0a60c7-581a-47f3-8060-60bf2edfba25,proponent,updated," +
                    "2019-11-11T14:39:25Z,82391a60-e9bc-46e6-832b-eaf851cba017,10a6406f-8f6f-4291-8daa-c50320084d63," +
                    "Shane Conroy Jr.,53,68671.14,true").split(",")

            val event = MessageType.PROPONENT_UPDATED.createMessage(messageValues)

            assertThat(event).isInstanceOf(ProponentUpdatedMessage::class.java)
        }

        @Test
        fun `given valid message should create ProponentRemovedMessage`() {
            val messageValues: List<String> = ("c2d06c4f-e1dc-4b2a-af61-ba15bc6d8610,proponent,removed," +
                    "2019-11-11T13:26:04Z,bd6abe95-7c44-41a4-92d0-edf4978c9f4e, 10a6406f-8f6f-4291-8daa-c50320084d63")
                .split(",")

            val event = MessageType.PROPONENT_REMOVED.createMessage(messageValues)

            assertThat(event).isInstanceOf(ProponentRemovedMessage::class.java)
        }
    }
}
