package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditCardTest {

    @BeforeAll
    static void setUpAll() {

        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {

        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {

        open("http://localhost:8080");
    }

    @BeforeEach
    void clearDataBase() {

        SQLHelper.cleanDataBase();
    }

    @Test
    public void shouldValidValues() {
        var startPage = new StartPage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingCreditCardPaymentForm(DataHelper.getCardInfoValidValues());
        cardPayment.successfulPayment();
        var paymentStatus = SQLHelper.getStatusCreditCard();
        assertEquals("APPROVED", paymentStatus);
    }

    @Test
    public void shouldIfCardNumberDeclined() {
        var startPage = new StartPage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingCreditCardPaymentForm(DataHelper.getCardInfoCardNumberDeclined());
        cardPayment.errorPayment();
        var paymentStatus = SQLHelper.getStatusCreditCard();
        assertEquals("DECLINED", paymentStatus);
    }

    @Test
    public void shouldIfCardNumberEmpty() {
        var startPage = new StartPage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingCreditCardPaymentForm(DataHelper.getCardInfoCardNumberEmpty());
        cardPayment.invalidFormatField();
    }

    @Test
    public void shouldIfCardNumberLess() {
        var startPage = new StartPage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingCreditCardPaymentForm(DataHelper.getCardInfoCardNumberLess());
        cardPayment.invalidFormatField();
    }

    @Test
    public void shouldIfCardNumberNull() {
        var startPage = new StartPage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingCreditCardPaymentForm(DataHelper.getCardInfoCardNumberNull());
        cardPayment.invalidFormatField();
    }

    @Test
    public void shouldIfMonthEmpty() {
        var startPage = new StartPage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingCreditCardPaymentForm(DataHelper.getCardInfoMonthEmpty());
        cardPayment.invalidFormatField();
    }

    @Test
    public void shouldIfMonthNull() {
        var startPage = new StartPage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingCreditCardPaymentForm(DataHelper.getCardInfoMonthNull());
        cardPayment.invalidFormatField();
    }

    @Test
    public void shouldIfMonthMore() {
        var startPage = new StartPage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingCreditCardPaymentForm(DataHelper.getCardInfoMonthMore());
        cardPayment.expiredLastMonth();
    }

    @Test
    public void shouldIfExpiredLastMonth() {
        var startPage = new StartPage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingCreditCardPaymentForm(DataHelper.getCardInfoExpiredLastMonth());
        cardPayment.expiredLastMonth();
    }

    @Test
    public void shouldIfYearEmpty() {
        var startPage = new StartPage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingCreditCardPaymentForm(DataHelper.getCardInfoYearEmpty());
        cardPayment.invalidFormatField();
    }

    @Test
    public void shouldIfYearNull() {
        var startPage = new StartPage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingCreditCardPaymentForm(DataHelper.getCardInfoYearNull());
        cardPayment.invalidFormatField();
    }

    @Test
    public void shouldIfExpiredLastYear() {
        var startPage = new StartPage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingCreditCardPaymentForm(DataHelper.getCardInfoExpiredLastYear());
        cardPayment.expiredLastYear();
    }

    @Test
    public void shouldIfOwnerEmpty() {
        var startPage = new StartPage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingCreditCardPaymentForm(DataHelper.getCardInfoOwnerEmpty());
        cardPayment.requiredField();
    }

    @Test
    public void shouldIfCyrillicName() {
        var startPage = new StartPage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingCreditCardPaymentForm(DataHelper.getCardInfoCyrillicName());
        cardPayment.invalidFormatField();
    }

    @Test
    public void shouldIfSymbolName() {
        var startPage = new StartPage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingCreditCardPaymentForm(DataHelper.getCardInfoSymbolName());
        cardPayment.invalidFormatField();
    }

    @Test
    public void shouldIfCVCEmpty() {
        var startPage = new StartPage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingCreditCardPaymentForm(DataHelper.getCardInfoCVCEmpty());
        cardPayment.invalidFormatField();
    }

    @Test
    public void shouldIfCVCNull() {
        var startPage = new StartPage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingCreditCardPaymentForm(DataHelper.getCardInfoCVCNull());
        cardPayment.invalidFormatField();
    }

}
