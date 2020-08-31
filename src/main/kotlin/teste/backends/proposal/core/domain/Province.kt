package teste.backends.proposal.core.domain

enum class Province(val isAccepted: Boolean) {
    RO(true),
    AC(true),
    AM(true),
    RR(true),
    PA(true),
    AP(true),
    TO(true),
    MA(true),
    PI(true),
    CE(true),
    RN(true),
    PB(true),
    PE(true),
    AL(true),
    SE(true),
    BA(true),
    MG(true),
    ES(true),
    RJ(true),
    SP(true),
    PR(false),
    SC(false),
    RS(false),
    MS(true),
    MT(true),
    GO(true),
    DF(true);

    companion object {
        fun of(value: String): Province = values()
            .find { it.name == value }
            ?: throw RuntimeException("Não achou a provincia")
    }
}
