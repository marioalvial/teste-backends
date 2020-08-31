package teste.backends.proposal.core.events

import java.math.BigDecimal
import java.time.ZonedDateTime
import teste.backends.proposal.core.domain.Proponent
import teste.backends.proposal.core.domain.Proposal

class ProponentUpdatedEvent(
    override val id: String,
    override val timestamp: ZonedDateTime,
    override val proposalId: String,
    val proponentId: String,
    val proponentName: String,
    val proponentAge: Int,
    val proponentMonthlyIncome: BigDecimal,
    val proponentIsMain: Boolean
) : Event {

    override fun apply(proposal: Proposal): Proposal = proposal
        .proponents
        .map { if (it.id == proponentId) updateProponent(it) else it }
        .let { proposal.copy(proponents = it) }

    private fun updateProponent(proponent: Proponent): Proponent = Proponent(
        id = proponent.id,
        name = proponentName,
        age = proponentAge,
        monthlyIncome = proponentMonthlyIncome,
        isMain = proponentIsMain
    )
}
