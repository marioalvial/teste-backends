package teste.backends.unit.core.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import teste.backends.proposal.core.domain.Province
import teste.backends.proposal.core.domain.Warranty
import java.math.BigDecimal

@DisplayName("When verifying if province is accepted")
class WarrantyTest {

    @Test
    fun `given accepted province should return true`(){
        val warranty = Warranty("bb0822e7-11ef-45c1-b272-1445aa7cb308", BigDecimal.TEN, Province.SP)

        assertThat(warranty.provinceAccepted()).isTrue()
    }

    @Test
    fun `given not accepted province should return false`(){
        val warranty = Warranty("bb0822e7-11ef-45c1-b272-1445aa7cb308", BigDecimal.TEN, Province.PR)

        assertThat(warranty.provinceAccepted()).isFalse()
    }
}
