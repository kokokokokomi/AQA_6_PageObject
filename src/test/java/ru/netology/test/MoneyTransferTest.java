package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;

public class MoneyTransferTest {

    @BeforeEach
    void openBrowser() {
        open("http://localhost:9999");
    }

    DashboardPage sendLogin() {
        val loginPage = new LoginPage();
        val authInfo = getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = getVerificationCode();
        return verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldTransferFromSecondToFirst() {
        val dashboardPage = sendLogin();
        val firstCardInfo = getFirstCardInfo();
        val secondCardInfo = getSecondCardInfo();
        val firstCardStartBalance = dashboardPage.getCardBalance(firstCardInfo.getNumber());
        val secondCardStartBalance = dashboardPage.getCardBalance(secondCardInfo.getNumber());
        val transferPage = dashboardPage.chooseCardToTransfer(firstCardInfo.getNumber());
        val amount = getRandomIntAmount(secondCardStartBalance);
        val firstCardExpectedBalance = firstCardStartBalance + amount;
        val secondCardExpectedBalance = secondCardStartBalance - amount;
        transferPage.transferMoney(secondCardInfo.getNumber(), String.valueOf(amount));
        assertEquals(firstCardExpectedBalance, dashboardPage.getCardBalance(firstCardInfo.getNumber()));
        assertEquals(secondCardExpectedBalance, dashboardPage.getCardBalance(secondCardInfo.getNumber()));
    }

    @Test
    void shouldTransferDoubleAmountFromFirstToSecond() {
        val dashboardPage = sendLogin();
        val firstCardInfo = getFirstCardInfo();
        val secondCardInfo = getSecondCardInfo();
        val firstCardStartBalance = dashboardPage.getCardBalance(firstCardInfo.getNumber());
        val secondCardStartBalance = dashboardPage.getCardBalance(secondCardInfo.getNumber());
        val transferPage = dashboardPage.chooseCardToTransfer(secondCardInfo.getNumber());
        val amount = getRandomDoubleAmount(secondCardStartBalance);
        val firstCardExpectedBalance = firstCardStartBalance - amount;
        val secondCardExpectedBalance = secondCardStartBalance + amount;
        transferPage.transferMoney(firstCardInfo.getNumber(), String.valueOf(amount));
        assertEquals(firstCardExpectedBalance, dashboardPage.getCardBalance(firstCardInfo.getNumber()));
        assertEquals(secondCardExpectedBalance, dashboardPage.getCardBalance(secondCardInfo.getNumber()));
    }
}
