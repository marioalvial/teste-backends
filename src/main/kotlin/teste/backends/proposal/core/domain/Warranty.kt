package teste.backends.proposal.core.domain

import java.math.BigDecimal

class Warranty(
    val id: String,
    val value: BigDecimal,
    val province: Province
) {

    fun provinceAccepted(): Boolean = province.isAccepted
}
