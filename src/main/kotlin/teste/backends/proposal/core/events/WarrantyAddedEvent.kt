package teste.backends.proposal.core.events

import java.math.BigDecimal
import java.time.ZonedDateTime
import teste.backends.proposal.core.domain.Proposal
import teste.backends.proposal.core.domain.Province
import teste.backends.proposal.core.domain.Warranty

class WarrantyAddedEvent(
    override val id: String,
    override val timestamp: ZonedDateTime,
    override val proposalId: String,
    val warrantyId: String,
    val warrantyValue: BigDecimal,
    val warrantyProvince: Province
) : Event {

    override fun apply(proposal: Proposal): Proposal {
        val warranty = Warranty(warrantyId, warrantyValue, warrantyProvince)

        return proposal.copy(warranties = proposal.warranties.plus(warranty))
    }
}
