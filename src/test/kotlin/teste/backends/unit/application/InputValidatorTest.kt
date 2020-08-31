package teste.backends.unit.application

import org.assertj.core.api.Assertions.assertThatCode
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import teste.backends.proposal.application.InputValidator
import javax.validation.constraints.NotBlank

@DisplayName("When validating an input object")
class InputValidatorTest {

    @Test
    fun `given invalid object should throw Exception`() {
        val obj = ValidationObject("")

        assertThatExceptionOfType(RuntimeException::class.java)
            .isThrownBy { InputValidator.validate(obj) }
            .withMessage("Message has 1 violation(s)")
    }

    @Test
    fun `given valid object should throw nothing`() {
        val obj = ValidationObject("valid")

        assertThatCode { InputValidator.validate(obj) }.doesNotThrowAnyException()
    }
}

internal class ValidationObject(
    @field:NotBlank
    private val field: String
)
