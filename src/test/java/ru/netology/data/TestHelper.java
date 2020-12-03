package ru.netology.data;
import lombok.val;
import static com.codeborne.selenide.Selenide.open;

public class TestHelper {

    public static void openBrowser() { open("http://localhost:9999"); }

    public static void sendLogin() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        verificationPage.validVerify(verificationCode);
    }
}
