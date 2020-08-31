package teste.backends.unit.application

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import teste.backends.proposal.application.messages.MessageFactory

@DisplayName("When creating a message")
class MessageFactoryTest {

    @Test
    fun `given valid message string should create a Message object`() {
        val message = "72ff1d14-756a-4549-9185-e60e326baf1b,proposal,created,2019-11-11T14:28:01Z," +
                "80921e5f-4307-4623-9ddb-5bf826a31dd7,1141424.0,240"

        assertThat(MessageFactory.create(message)).isNotNull
    }

    @Test
    fun `given valid message string but not existent message type should return null`() {
        val message = "72ff1d14-756a-4549-9185-e60e326baf1b,proposal,unknown,2019-11-11T14:28:01Z," +
                "80921e5f-4307-4623-9ddb-5bf826a31dd7,1141424.0,240"

        assertThat(MessageFactory.create(message)).isNull()
    }

    @Test
    fun `given invalid message string should not create a Message object and return null`() {
        assertThat(MessageFactory.create("")).isNull()
    }
}
