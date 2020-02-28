package lotto.domain.lottomoney;

import java.util.Objects;

public class LottoMoney {
	private static final long ZERO = 0;
	private static final int PERCENT = 100;

	private final long money;

	public LottoMoney(long money) {
		this.money = money;
	}

	public LottoMoney(String money) {
		this.money = validate(money);
	}

	private long validate(String money) {
		validateNullOrEmpty(money);
		long parsedMoney = parseToLong(money);
		validatePositive(parsedMoney);
		validateUnit(parsedMoney);
		return parsedMoney;
	}

	private void validateNullOrEmpty(String money) {
		if (Objects.isNull(money) || money.isEmpty()) {
			throw new InvalidLottoMoneyException(InvalidLottoMoneyException.NULL_OR_EMPTY);
		}
	}

	private long parseToLong(String money) {
		try {
			return Long.parseLong(money);
		} catch (NumberFormatException ne) {
			throw new InvalidLottoMoneyException(InvalidLottoMoneyException.NOT_INTEGER);
		}
	}

	private void validatePositive(long parsedMoney) {
		if (parsedMoney <= ZERO) {
			throw new InvalidLottoMoneyException(InvalidLottoMoneyException.NOT_POSITIVE);
		}
	}

	private void validateUnit(long parsedMoney) {
		if (parsedMoney % LottoPrice.UNIT != ZERO) {
			throw new InvalidLottoMoneyException(InvalidLottoMoneyException.INVALID_UNIT);
		}
	}

	int calculateCountOfLotto(int unit) {
		return (int)(money / unit);
	}

	public long getMoney() {
		return money;
	}

	public LottoMoney add(LottoMoney addedLottoMoney) {
		return new LottoMoney(this.money + addedLottoMoney.money);
	}

	public LottoMoney multiply(int multiplyCount) {
		return new LottoMoney(this.money * multiplyCount);
	}

	public int getWinningRate(LottoMoney inputLottoMoney) {
		if (inputLottoMoney.money == 0) {
			return 0;
		}
		return (int)((this.money * PERCENT) / inputLottoMoney.money);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		LottoMoney that = (LottoMoney)o;
		return money == that.money;
	}

	@Override
	public int hashCode() {
		return Objects.hash(money);
	}
}
