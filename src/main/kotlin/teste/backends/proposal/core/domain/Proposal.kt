package teste.backends.proposal.core.domain

import java.math.BigDecimal
import java.math.RoundingMode.HALF_UP

data class Proposal(
    val id: String,
    val loanValue: BigDecimal,
    val status: ProposalStatus,
    val numberOfMonthlyInstallments: Int,
    val warranties: List<Warranty>,
    val proponents: List<Proponent>
) {

    fun isValid(): Boolean {
        val errors = listOfNotNull(
            assertLoanValueInValidRange(),
            assertNumberOfMonthlyInstallmentsInValidInterval(),
            assertMinimumNumberOfProponents(),
            assertNumberOfMainProponents(),
            assertMinimumNumberOfWarranties(),
            assertMinimumAgeOfProponents(),
            assertWarrantiesValueSumGreaterThanLoanValue(),
            assertWarrantiesProvincesAccepted(),
            assertLoanValueIsProportionalToProponentIncomeBasedOnAge(),
            assertProposalIsNotDeleted()
        )

        return errors.isEmpty()
    }

    private fun assertLoanValueInValidRange(): ProposalError? {
        val minimumValue = BigDecimal.valueOf(30_000)
        val maximumValue = BigDecimal.valueOf(3_000_000)

        return when {
            loanValue >= minimumValue && loanValue <= maximumValue -> null
            else -> ProposalError()
        }
    }

    private fun assertNumberOfMonthlyInstallmentsInValidInterval(): ProposalError? {
        val minimumNumber = 24
        val maximumNumber = 180

        return when (numberOfMonthlyInstallments) {
            in minimumNumber..maximumNumber -> null
            else -> ProposalError()
        }
    }

    private fun assertMinimumNumberOfProponents(): ProposalError? {
        val minimumNumberOfProponents = 2

        return when {
            proponents.size >= minimumNumberOfProponents -> null
            else -> ProposalError()
        }
    }

    private fun assertNumberOfMainProponents(): ProposalError? {
        val mandatoryNumberOfMainProponents = 1

        return when (proponents.count { it.isMain }) {
            mandatoryNumberOfMainProponents -> null
            else -> ProposalError()
        }
    }

    private fun assertMinimumNumberOfWarranties(): ProposalError? {
        val minimumNumberOfWarranties = 1

        return when {
            warranties.size >= minimumNumberOfWarranties -> null
            else -> ProposalError()
        }
    }

    private fun assertMinimumAgeOfProponents(): ProposalError? {
        val minimumAge = 18

        return when (proponents.none { it.age < minimumAge }) {
            true -> null
            false -> ProposalError()
        }
    }

    private fun assertWarrantiesValueSumGreaterThanLoanValue(): ProposalError? {
        val sum = warranties.fold(BigDecimal.ZERO) { accumulator, warranty -> accumulator.plus(warranty.value) }
        val twiceLoanValue = loanValue * BigDecimal.valueOf(2)

        return when {
            sum >= twiceLoanValue -> null
            else -> ProposalError()
        }
    }

    private fun assertWarrantiesProvincesAccepted(): ProposalError? = when (warranties.all { it.provinceAccepted() }) {
        true -> null
        else -> ProposalError()
    }

    private fun assertLoanValueIsProportionalToProponentIncomeBasedOnAge(): ProposalError? {
        val mainProponent = getMainProponent() ?: return ProposalError()
        val installmentValue = calculateInstallmentValue()
        val factor = when (mainProponent.age) {
            in 18..24 -> 4
            in 24..50 -> 3
            else -> 2
        }

        return when {
            mainProponent.monthlyIncome >= factor.toBigDecimal() * installmentValue -> null
            else -> ProposalError()
        }
    }

    private fun assertProposalIsNotDeleted(): ProposalError? = when (status) {
        ProposalStatus.DELETED -> ProposalError()
        else -> null
    }

    private fun calculateInstallmentValue(): BigDecimal = loanValue
        .divide(numberOfMonthlyInstallments.toBigDecimal(), HALF_UP)

    private fun getMainProponent(): Proponent? = proponents.find { it.isMain }

    companion object {
        fun empty(): Proposal = Proposal(
            id = "",
            loanValue = BigDecimal.ZERO,
            numberOfMonthlyInstallments = 0,
            status = ProposalStatus.EMPTY,
            warranties = emptyList(),
            proponents = emptyList()
        )
    }
}
