package lotto;

import lotto.controller.LottoController;
import lotto.domain.Lotto.Lotto;
import lotto.domain.Lotto.LottoParser;
import lotto.domain.Lotto.Lottos;
import lotto.domain.LottoMoney.InvalidLottoMoneyException;
import lotto.domain.LottoMoney.LottoMoney;
import lotto.domain.LottoNumber.LottoNumber;
import lotto.domain.LottoRank;

import java.util.Map;

import static lotto.view.ConsoleInputView.*;
import static lotto.view.ConsoleOutputView.*;

public class ConsoleUILottoApplication {
	public static void main(String[] args) {
		LottoController lottoController = new LottoController();
		LottoMoney inputLottoMoney = receiveInputMoney();
		int numberOfLotto = inputLottoMoney.calculateNumberOfLotto();
		printPurchaseCompleteMessage(numberOfLotto);

		Lottos lottos = lottoController.purchaseLotto(numberOfLotto);
		printPurchasedLotto(lottos);

		Lotto winningLotto = new Lotto(LottoParser.parser(inputWinningLottoNumber()));
		LottoNumber bonusLottoNumber = LottoNumber.valueOf(inputBonusLottoNumber());
		Map<LottoRank, Integer> lottoRankCount =
				lottoController.calculateLottoRankCount(lottos, winningLotto, bonusLottoNumber);
		printStatisticsMessage();
		printWinningResult(lottoRankCount);

		int winningRatio = lottoController.calculateWinningRatio(lottoRankCount, inputLottoMoney);
		printWinningRatio(winningRatio);
	}

	private static LottoMoney receiveInputMoney() {
		try {
			return new LottoMoney(inputMoney());
		} catch (InvalidLottoMoneyException ime) {
			printExceptionMessage(ime.getMessage());
			return receiveInputMoney();
		}
	}
}
