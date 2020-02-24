package lotto;

import lotto.controller.LottoController;
import lotto.domain.WinningLotto;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoParser;
import lotto.domain.lotto.Lottos;
import lotto.domain.lottomoney.InvalidLottoMoneyException;
import lotto.domain.lottomoney.LottoMoney;
import lotto.domain.lottonumber.LottoNumber;
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

		Lotto inputWinningLotto = new Lotto(LottoParser.parser(inputWinningLottoNumber()));
		LottoNumber inputBonusNumber = LottoNumber.valueOf(inputBonusLottoNumber());
		WinningLotto winningLotto = new WinningLotto(inputWinningLotto, inputBonusNumber);

		Map<LottoRank, Integer> lottoRankCount =
				lottoController.calculateLottoRankCount(lottos, winningLotto);
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
