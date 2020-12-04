import org.junit.jupiter.api.Test;
import lombok.val;
import ru.netology.data.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @Test
    void shouldTransferBetweenOwnCardsFromSecondToFirst() {
        TestHelper.openBrowser();
        TestHelper.sendLogin();
        val personAccount = new PersonAccountPage();
        val cardInfo = DataHelper.getCardInfo2();
        int firstBalance = DataHelper.getBalance();
        personAccount.validDeposit1();
        val cardBalance = new CardBalanceAddPage();
        cardBalance.transferMoney(cardInfo);
        int newBalance = personAccount.returnBalance();
        int expected = DataHelper.expectAmount(firstBalance, Integer.parseInt(CardBalanceAddPage.amountTransferred));
        assertEquals(expected, newBalance);
    }

    @Test
    void shouldTransferBetweenOwnCardsFromFirstToSecond() {
        TestHelper.openBrowser();
        TestHelper.sendLogin();
        val personAccount = new PersonAccountPage();
        val cardInfo = DataHelper.getCardInfo1();
        int firstBalance = DataHelper.getOtherBalance();
        personAccount.validDeposit2();
        val cardBalance = new CardBalanceAddPage();
        cardBalance.transferMoney(cardInfo);
        int newBalance = personAccount.returnOtherBalance();
        int expected = DataHelper.expectAmount(firstBalance, Integer.parseInt(CardBalanceAddPage.amountTransferred));
        assertEquals(expected, newBalance);
    }

    @Test
    void shouldMakeErrorWithEmptyField() {
        TestHelper.openBrowser();
        TestHelper.sendLogin();
        val personAccount = new PersonAccountPage();
        val cardInfo = DataHelper.getCardInfo1();
        personAccount.validDeposit2();
        val cardBalance = new CardBalanceAddPage();
        cardBalance.transferMoneyError(cardInfo);
    }
}
