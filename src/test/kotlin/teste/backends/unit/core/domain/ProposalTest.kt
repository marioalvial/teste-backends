package teste.backends.unit.core.domain

import java.math.BigDecimal
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import teste.backends.proposal.core.domain.Proposal
import teste.backends.proposal.core.domain.ProposalStatus

@DisplayName("When creating a empty proposal")
class ProposalTest {

    @Test
    fun `should create an empty proposal`() {
        val expected = Proposal("", BigDecimal.ZERO, ProposalStatus.EMPTY, 0, emptyList(), emptyList())

        assertThat(Proposal.empty()).isEqualTo(expected)
    }
}
