package teste.backends.proposal.core.events

import java.math.BigDecimal
import java.time.ZonedDateTime
import teste.backends.proposal.core.domain.Proposal
import teste.backends.proposal.core.domain.Province
import teste.backends.proposal.core.domain.Warranty

class WarrantyUpdatedEvent(
    override val id: String,
    override val timestamp: ZonedDateTime,
    override val proposalId: String,
    val warrantyId: String,
    val warrantyValue: BigDecimal,
    val warrantyProvince: Province
) : Event {

    override fun apply(proposal: Proposal): Proposal = proposal
        .warranties
        .map { if (it.id == warrantyId) updateWarranty(it) else it }
        .let { proposal.copy(warranties = it) }

    private fun updateWarranty(warranty: Warranty): Warranty = Warranty(
        id = warranty.id,
        value = warrantyValue,
        province = warrantyProvince
    )
}
