package teste.backends.proposal.application

import javax.validation.Validation

object InputValidator {

    private val validator = Validation.buildDefaultValidatorFactory().validator

    fun validate(obj: Any) {
        val violations = validator.validate(obj)

        require(violations.isEmpty()) {
            throw RuntimeException("Message has ${violations.size} violation(s)")
        }
    }
}
