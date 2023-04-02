package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CardPaymentPage {

    private SelenideElement formPayment = $(byText("Купить"));
    private SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthField = $("[placeholder='04']");
    private SelenideElement yearField = $("[placeholder='23']");
    private SelenideElement ownerField = $(By.xpath("//span[text()='Владелец']/..//input"));
    private SelenideElement cvcField = $("[placeholder='999']");
    private SelenideElement continueButton = $(byText("Продолжить"));
    private SelenideElement successNotification = $(byText("Операция была успешно проведена и одобрена банком"));
    private SelenideElement errorNotification = $(byText("Ошибка!!! Операция была отклонена банком"));
    private SelenideElement formatError = $(byText("Неверный формат"));
    private SelenideElement ownerRequiredField = $(byText("Заполните обязательные поля"));
    private SelenideElement expiredLastMonthField = $(byText("Срок действия карты указан неверно"));
    private SelenideElement expiredLastYearField = $(byText("Срок действия карты закончился"));
    public CardPaymentPage() {
        formPayment.shouldBe(visible);
    }

    public void fillingPaymentForm(DataHelper.CardInfo cardInfo) {
        cardNumberField.setValue(cardInfo.getCardNumber());
        monthField.setValue(cardInfo.getMonth());
        yearField.setValue(cardInfo.getYear());
        ownerField.setValue(cardInfo.getName());
        cvcField.setValue(cardInfo.getCvc());
        continueButton.click();
    }

    public void successfulPayment() {
        successNotification.shouldBe(visible, Duration.ofSeconds(10));
    }

    public void errorPayment() {
        errorNotification.shouldBe(visible, Duration.ofSeconds(10));
    }

    public void requiredField() {
        ownerRequiredField.shouldBe(visible);
    }

    public void invalidFormatField() {
        formatError.shouldBe(visible);
    }

    public void expiredLastMonth() {
        expiredLastMonthField.shouldBe(visible);
    }

    public void expiredLastYear() {
        expiredLastYearField.shouldBe(visible);
    }


}
