package lotto.domain.lotto;

public class CountOfManualLotto {
	private static final int MIN_BOUND = 0;
	private static final int LAST_COUNT = 0;

	private int countOfManualLotto;
	private int loop_count;

	public CountOfManualLotto(String rawCount, int countOfAllLotto) {
		validateType(rawCount);
		validateBound(countOfAllLotto);
		loop_count = countOfManualLotto;
	}

	private void validateType(String rawCount) {
		try {
			countOfManualLotto = Integer.parseInt(rawCount);
		} catch (IllegalArgumentException e) {
			throw new InvalidCountOfManualLottoException(InvalidCountOfManualLottoException.WRONG_TYPE);
		}
	}

	private void validateBound(int maxBound) {
		if (countOfManualLotto < MIN_BOUND || countOfManualLotto > maxBound) {
			throw new InvalidCountOfManualLottoException(InvalidCountOfManualLottoException.WRONG_BOUND);
		}
	}

	public boolean isNotZero() {
		if (loop_count <= LAST_COUNT) {
			return false;
		}
		loop_count--;
		return true;
	}

	public int getCountOfManualLotto() {
		return countOfManualLotto;
	}
}
