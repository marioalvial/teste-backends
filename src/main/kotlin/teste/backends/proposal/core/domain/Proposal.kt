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
        return try {
            assertLoanValueInValidRange()
            assertNumberOfMonthlyInstallmentsInValidInterval()
            assertMinimumNumberOfProponents()
            assertNumberOfMainProponents()
            assertMinimumNumberOfWarranties()
            assertMinimumAgeOfProponents()
            assertWarrantiesValueSumGreaterThanLoanValue()
            assertWarrentiesProvincesIsAccepted()
            assertLoanValueIsProportionalToProponentIncomeBasedOnAge()
            assertProposalIsNotDeleted()
            true
        } catch (ex: Exception) {
            false
        }
    }

    private fun assertProposalIsNotDeleted() {
        require(status != ProposalStatus.DELETED) {
            throw RuntimeException("Proposta deletada")
        }
    }

    private fun assertLoanValueIsProportionalToProponentIncomeBasedOnAge() {
        val mainProponent = getMainProponent()
        val installmentValue = calculateInstallmentValue()
        val factor = when (mainProponent.age) {
            in 18..24 -> 4
            in 24..50 -> 3
            else -> 2
        }

        require(mainProponent.monthlyIncome >= (factor.toBigDecimal() * installmentValue)) {
            throw RuntimeException("Main proponent não possui renda suficiente")
        }
    }

    private fun calculateInstallmentValue() = loanValue.divide(numberOfMonthlyInstallments.toBigDecimal(), HALF_UP)

    private fun getMainProponent() = proponents
        .find { it.isMain }
        ?: throw RuntimeException("Não foi encontrado nenhum main proponent")

    private fun assertWarrentiesProvincesIsAccepted() {
        require(warranties.all { it.provinceIsAccepted() }) {
            throw RuntimeException("Existem garantias de pronvicias não aceitas")
        }
    }

    private fun assertMinimumAgeOfProponents() {
        val minimumAge = 18

        require(proponents.none { it.age < minimumAge }) {
            throw RuntimeException("Existem proponentes menores de idade")
        }
    }

    private fun assertWarrantiesValueSumGreaterThanLoanValue() {
        val sum = warranties.fold(BigDecimal.ZERO) { accumulator, warranty -> accumulator.plus(warranty.value) }
        val twiceLoanValue = loanValue * BigDecimal.valueOf(2)

        require(sum >= twiceLoanValue) {
            throw RuntimeException("Valor da garantia não é suficiente")
        }
    }

    private fun assertMinimumNumberOfWarranties() {
        val minimumNumberOfWarranties = 1

        require(warranties.size >= minimumNumberOfWarranties) {
            throw RuntimeException("Não possui número de garantias suficiente")
        }
    }

    private fun assertNumberOfMainProponents() {
        val mandatoryNumberOfMainProponents = 1

        require(proponents.count { it.isMain } == mandatoryNumberOfMainProponents) {
            throw RuntimeException("Não possui número de main proponents")
        }
    }

    private fun assertMinimumNumberOfProponents() {
        val minimumNumberOfProponents = 2

        require(proponents.size >= minimumNumberOfProponents) {
            throw RuntimeException("Não possui número de pessoas suficiente")
        }
    }

    private fun assertLoanValueInValidRange() {
        val minimumValue = BigDecimal.valueOf(30000)
        val maximumValue = BigDecimal.valueOf(3000000)

        require(loanValue >= minimumValue && loanValue <= maximumValue) {
            throw RuntimeException("Valor da proposta não está dentro do intervalo válido")
        }
    }

    private fun assertNumberOfMonthlyInstallmentsInValidInterval() {
        val minimumNumber = 24
        val maximumNumber = 180

        require(numberOfMonthlyInstallments in minimumNumber..maximumNumber) {
            throw RuntimeException("Valor da proposta não está dentro do intervalo válido")
        }
    }

    companion object {
        fun empty() = Proposal(
            id = "",
            loanValue = BigDecimal.ZERO,
            numberOfMonthlyInstallments = 0,
            status = ProposalStatus.EMPTY,
            warranties = emptyList(),
            proponents = emptyList()
        )
    }
}
