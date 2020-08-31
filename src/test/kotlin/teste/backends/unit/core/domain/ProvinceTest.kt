package teste.backends.unit.core.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import teste.backends.proposal.core.domain.Province

@DisplayName("When finding a province")
class ProvinceTest {

    @Test
    fun `given RO value should return RO province`() {
        val province = Province.of("RO")

        assertThat(province).isEqualTo(Province.RO)
        assertThat(province.isAccepted).isTrue()
    }

    @Test
    fun `given AC value should return AC province`() {
        val province = Province.of("AC")

        assertThat(province).isEqualTo(Province.AC)
        assertThat(province.isAccepted).isTrue()
    }

    @Test
    fun `given AM value should return AM province`() {
        val province = Province.of("AM")

        assertThat(province).isEqualTo(Province.AM)
        assertThat(province.isAccepted).isTrue()
    }

    @Test
    fun `given RR value should return RR province`() {
        val province = Province.of("RR")

        assertThat(province).isEqualTo(Province.RR)
        assertThat(province.isAccepted).isTrue()
    }

    @Test
    fun `given PA value should return PA province`() {
        val province = Province.of("PA")

        assertThat(province).isEqualTo(Province.PA)
        assertThat(province.isAccepted).isTrue()
    }

    @Test
    fun `given AP value should return AP province`() {
        val province = Province.of("AP")

        assertThat(province).isEqualTo(Province.AP)
        assertThat(province.isAccepted).isTrue()
    }

    @Test
    fun `given TO value should return TO province`() {
        val province = Province.of("TO")

        assertThat(province).isEqualTo(Province.TO)
        assertThat(province.isAccepted).isTrue()
    }

    @Test
    fun `given MA value should return MA province`() {
        val province = Province.of("MA")

        assertThat(province).isEqualTo(Province.MA)
        assertThat(province.isAccepted).isTrue()
    }

    @Test
    fun `given PI value should return PI province`() {
        val province = Province.of("PI")

        assertThat(province).isEqualTo(Province.PI)
        assertThat(province.isAccepted).isTrue()
    }

    @Test
    fun `given CE value should return CE province`() {
        val province = Province.of("CE")

        assertThat(province).isEqualTo(Province.CE)
        assertThat(province.isAccepted).isTrue()
    }

    @Test
    fun `given RN value should return RN province`() {
        val province = Province.of("RN")

        assertThat(province).isEqualTo(Province.RN)
        assertThat(province.isAccepted).isTrue()
    }

    @Test
    fun `given PB value should return PB province`() {
        val province = Province.of("PB")

        assertThat(province).isEqualTo(Province.PB)
        assertThat(province.isAccepted).isTrue()
    }

    @Test
    fun `given PE value should return PE province`() {
        val province = Province.of("PE")

        assertThat(province).isEqualTo(Province.PE)
        assertThat(province.isAccepted).isTrue()
    }

    @Test
    fun `given AL value should return AL province`() {
        val province = Province.of("AL")

        assertThat(province).isEqualTo(Province.AL)
        assertThat(province.isAccepted).isTrue()
    }

    @Test
    fun `given SE value should return SE province`() {
        val province = Province.of("SE")

        assertThat(province).isEqualTo(Province.SE)
        assertThat(province.isAccepted).isTrue()
    }

    @Test
    fun `given BA value should return BA province`() {
        val province = Province.of("BA")

        assertThat(province).isEqualTo(Province.BA)
        assertThat(province.isAccepted).isTrue()
    }

    @Test
    fun `given MG value should return MG province`() {
        val province = Province.of("MG")

        assertThat(province).isEqualTo(Province.MG)
        assertThat(province.isAccepted).isTrue()
    }

    @Test
    fun `given ES value should return ES province`() {
        val province = Province.of("ES")

        assertThat(province).isEqualTo(Province.ES)
        assertThat(province.isAccepted).isTrue()
    }

    @Test
    fun `given RJ value should return RJ province`() {
        val province = Province.of("RJ")

        assertThat(province).isEqualTo(Province.RJ)
        assertThat(province.isAccepted).isTrue()
    }

    @Test
    fun `given SP value should return SP province`() {
        val province = Province.of("SP")

        assertThat(province).isEqualTo(Province.SP)
        assertThat(province.isAccepted).isTrue()
    }

    @Test
    fun `given PR value should return PR province`() {
        val province = Province.of("PR")

        assertThat(province).isEqualTo(Province.PR)
        assertThat(province.isAccepted).isFalse()
    }

    @Test
    fun `given SC value should return SC province`() {
        val province = Province.of("SC")

        assertThat(province).isEqualTo(Province.SC)
        assertThat(province.isAccepted).isFalse()
    }

    @Test
    fun `given RS value should return RS province`() {
        val province = Province.of("RS")

        assertThat(province).isEqualTo(Province.RS)
        assertThat(province.isAccepted).isFalse()
    }

    @Test
    fun `given MS value should return MS province`() {
        val province = Province.of("MS")

        assertThat(province).isEqualTo(Province.MS)
        assertThat(province.isAccepted).isTrue()
    }

    @Test
    fun `given MT value should return MT province`() {
        val province = Province.of("MT")

        assertThat(province).isEqualTo(Province.MT)
        assertThat(province.isAccepted).isTrue()
    }

    @Test
    fun `given GO value should return GO province`() {
        val province = Province.of("GO")

        assertThat(province).isEqualTo(Province.GO)
        assertThat(province.isAccepted).isTrue()
    }

    @Test
    fun `given DF value should return DF province`() {
        val province = Province.of("DF")

        assertThat(province).isEqualTo(Province.DF)
        assertThat(province.isAccepted).isTrue()
    }
}
