package ru.netology.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import lombok.val;
import ru.netology.data.*;
import ru.netology.page.*;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @BeforeEach
    void openBrowser() { open("http://localhost:9999"); }

    @BeforeEach
    void sendLogin() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldTransferBetweenOwnCardsFromSecondToFirst() {
        val personAccount = new PersonAccountPage();
        val cardInfo = DataHelper.getCardInfo2();
        val firstBalance = DashboardPage.getFirstCardBalance();
        val cardBalance = personAccount.validDeposit1();
        cardBalance.transferMoney(cardInfo);
        val newBalance = personAccount.returnFirstCardInfo();
        val expected = DataHelper.expectAmount(firstBalance, Integer.parseInt(CardBalanceAddPage.amountTransferred));
        assertEquals(expected, newBalance);
    }

    @Test
    void shouldTransferBetweenOwnCardsFromFirstToSecond() {
        val personAccount = new PersonAccountPage();
        val cardInfo = DataHelper.getCardInfo1();
        val firstBalance = DashboardPage.getSecondCardBalance();
        personAccount.validDeposit2();
        val cardBalance = new CardBalanceAddPage();
        cardBalance.transferMoney(cardInfo);
        val newBalance = personAccount.returnSecondCardInfo();
        val expected = DataHelper.expectAmount(firstBalance, Integer.parseInt(CardBalanceAddPage.amountTransferred));
        assertEquals(expected, newBalance);
    }

    @Test
    void shouldMakeErrorWithEmptyField() {
        val personAccount = new PersonAccountPage();
        val cardInfo = DataHelper.getCardInfo1();
        personAccount.validDeposit2();
        val cardBalance = new CardBalanceAddPage();
        cardBalance.transferMoneyError(cardInfo);
    }

    @Test
    void shouldTransferDoubleAmountBetweenOwnCardsFromFirstToSecond() {
        val personAccount = new PersonAccountPage();
        val cardInfo = DataHelper.getCardInfo1();
        val firstBalance = DashboardPage.getSecondCardBalance();
        personAccount.validDeposit2();
        val cardBalance = new CardBalanceAddPage();
        cardBalance.transferMoneyDoubleAmount(cardInfo);
        val newBalance = personAccount.returnSecondCardInfo();
        val expected = (int) (firstBalance - Double.parseDouble(DataHelper.shouldReturnDoubleAmount()));
        assertEquals(expected, newBalance);
    }
}
