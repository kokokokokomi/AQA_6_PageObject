package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.Condition;
import ru.netology.data.DataHelper;

public class CardBalanceAddPage {
    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fromCardField = $("[data-test-id=from] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private SelenideElement cancelButton = $("[data-test-id=action-cancel]");
    private SelenideElement errorNotification = $("[data-test-id=error-notification]");
    public static String amountTransferred = DataHelper.shouldReturnRandomAmount();

    public PersonAccountPage transferMoney(DataHelper.CardInfo info) {
        amountField.setValue(amountTransferred);
        fromCardField.setValue(info.getNumber());
        transferButton.click();
        return new PersonAccountPage();
    }

    public PersonAccountPage transferMoneyDoubleAmount(DataHelper.CardInfo info) {
        amountField.setValue(DataHelper.shouldReturnDoubleAmount());
        fromCardField.setValue(info.getNumber());
        transferButton.click();
        return new PersonAccountPage();
    }

    public CardBalanceAddPage() {
        amountField.shouldBe(Condition.visible);
    }

    public void transferMoneyError(DataHelper.CardInfo info) {
        amountField.setValue(amountTransferred);
        transferButton.click();
        errorNotification.shouldBe(Condition.visible);
    }
}
