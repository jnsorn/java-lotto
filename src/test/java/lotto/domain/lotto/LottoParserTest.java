package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.number.LottoNumber;

class LottoParserTest {
	@DisplayName("WinningLottoParser에 정상값을 입력하면 number list 반환")
	@Test
	void parser_StringInputWinningLotto_CreateWinningLotto() {
		String inputWinningLotto = "1, 2, 3, 4, 5, 6";

		Set<LottoNumber> winningLottoNumbers = LottoParser.parser(inputWinningLotto);

		Set<LottoNumber> expected = new HashSet<>(Arrays.asList(
			LottoNumber.valueOf("1"),
			LottoNumber.valueOf("2"),
			LottoNumber.valueOf("3"),
			LottoNumber.valueOf("4"),
			LottoNumber.valueOf("5"),
			LottoNumber.valueOf("6")));

		assertThat(winningLottoNumbers).isEqualTo(expected);
	}
}
