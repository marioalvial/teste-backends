package teste.backends.proposal.core.domain

import java.math.BigDecimal

class Proponent(
   val id: String,
   val name: String,
   val age: Int,
   val monthlyIncome: BigDecimal,
   val isMain: Boolean
)
