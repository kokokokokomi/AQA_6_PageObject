package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;
import ru.netology.page.CardBalanceAddPage;
import ru.netology.page.DashboardPage;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PersonAccountPage {
    private ElementsCollection depositButtons = $$("[data-test-id=action-deposit]");
    private SelenideElement firstCardInfo = $(withText("**** **** **** 0002"));
    private SelenideElement secondCardInfo = $(withText("**** **** **** 0001"));
    private SelenideElement codeFieldValue = $("[class='input__box'] [type='text']");
    private SelenideElement codeFieldFrom = $("[class='input__box'] [type='tel']");
    private SelenideElement codeButton = $("[data-test-id='action-transfer'] [class='button__text']");
    private SelenideElement heading = $(byText("Пополнение карты"));

    public CardBalanceAddPage validDeposit1() {
        depositButtons.first().click();
        return new CardBalanceAddPage();
    }

    public CardBalanceAddPage validDeposit2 () {
        depositButtons.last().click();
        return new CardBalanceAddPage();
    }

    public DashboardPage validVerify1(DataHelper.TransferInfo transferInfo, int value) {
        codeFieldValue.doubleClick();
        codeFieldValue.sendKeys(Keys.BACK_SPACE);
        codeFieldValue.setValue(String.valueOf(value));
        codeFieldFrom.doubleClick();
        codeFieldFrom.sendKeys(Keys.DELETE);
        codeFieldFrom.setValue(transferInfo.getCardNumber());
        codeButton.click();
        return new DashboardPage();
    }

    public PersonAccountPage() {
        heading.shouldBe(Condition.visible);
    }

    public int returnFirstCardInfo() {
        String text = firstCardInfo.text();
        String[] textArray = text.split(" ");
        return Integer.parseInt(textArray[5]);
    }

    public int returnSecondCardInfo() {
        String text = secondCardInfo.text();
        String[] textArray = text.split(" ");
        return Integer.parseInt(textArray[5]);
    }
}
