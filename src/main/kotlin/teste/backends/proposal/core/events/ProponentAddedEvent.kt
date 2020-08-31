package teste.backends.proposal.core.events

import java.math.BigDecimal
import java.time.ZonedDateTime
import teste.backends.proposal.core.domain.Proponent
import teste.backends.proposal.core.domain.Proposal

class ProponentAddedEvent(
    override val id: String,
    override val timestamp: ZonedDateTime,
    override val proposalId: String,
    private val proponentId: String,
    private val proponentName: String,
    private val proponentAge: Int,
    private val proponentMonthlyIncome: BigDecimal,
    private val proponentIsMain: Boolean
) : Event {

    override fun apply(proposal: Proposal): Proposal {
        val proponent = Proponent(proponentId, proponentName, proponentAge, proponentMonthlyIncome, proponentIsMain)

        return proposal.copy(proponents = proposal.proponents.plus(proponent))
    }
}
