package teste.backends.integration

import java.io.File
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import teste.backends.processMessages

@DisplayName("When consuming events in order to validate proposals")
class ProposalIntegrationTest {

    @Test
    fun `given valid input files should return all valid proposals`() {
        File(ClassLoader.getSystemResource("input").toURI())
            .walk()
            .filter { it.isFile }
            .sortedBy { it.name }
            .map { it.name to it.readText().split("\n") }
            .forEach {
                val (fileName, messageValues) = it
                val outputFileName = fileName.replace("input", "output")
                val expected = ClassLoader.getSystemResource("output/$outputFileName").readText()

                val actual = processMessages(messageValues)

                assertThat(actual).isEqualToIgnoringWhitespace(expected)
            }
    }
}
