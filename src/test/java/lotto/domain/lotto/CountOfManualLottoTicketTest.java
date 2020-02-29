package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CountOfManualLottoTicketTest {
	@DisplayName("CountOfManualLottoTicketTest 생성자에 정상적인 입력값과 전체 로또 갯수가 들어오면 객체 생성")
	@Test
	void constructor_validRawCountAndAllLottoCount_createCountOfManualLottoTickets() {
		assertThat(new CountOfManualLottoTicket("3", 7)).isInstanceOf(CountOfManualLottoTicket.class);
	}

	@DisplayName("CountOfManualLottoTicketTest 생성자에 비정상적인 입력값과 정상적인 전체 로또 갯수가 들어오면 예외 발생")
	@Test
	void constructor_InvalidRawCountAndValidAllLottoCount_ThrownException() {
		assertThatThrownBy(() -> new CountOfManualLottoTicket("s", 7))
			.isInstanceOf(InvalidCountOfManualLottoTicketException.class);
	}

	@DisplayName("CountOfManualLottoTicketTest 생성자에 정상적인 입력값과 비정상적인 전체 로또 갯수가 들어오면 예외 발생")
	@ParameterizedTest
	@CsvSource(value = {"3:-1", "3:1"}, delimiter = ':')
	void constructor_ValidRawCountAndInvalidAllLottoCount_ThrownException(String rawCount, int countOfAllLotto) {
		assertThatThrownBy(() -> new CountOfManualLottoTicket(rawCount, countOfAllLotto))
			.isInstanceOf(InvalidCountOfManualLottoTicketException.class);
	}

	@DisplayName("수동 로또 티켓의 갯수가 양수일 때 isNotZero()를 호출하면 true 반환")
	@Test
	void isNotZero_isPositive_returnTrue() {
		assertThat(new CountOfManualLottoTicket("3", 5).isNotZero()).isEqualTo(true);
	}

	@DisplayName("수동 로또 티켓의 갯수가 0개일 때 isNotZero()를 호출하면 false 반환")
	@Test
	void isNotZero_isZero_returnFalse() {
		CountOfManualLottoTicket countOfManualLottoTicket = new CountOfManualLottoTicket("3", 5);
		for (int i = 0; i < 3; i++) {
			countOfManualLottoTicket.isNotZero();
		}
		assertThat(countOfManualLottoTicket.isNotZero()).isEqualTo(false);
	}
}
