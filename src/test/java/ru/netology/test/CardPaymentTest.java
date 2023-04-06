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

public class CardPaymentTest {

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
        var cardPayment = startPage.cardPayment();
        cardPayment.fillingPaymentForm(DataHelper.getCardInfoValidValues());
        cardPayment.successfulPayment();
        var paymentStatus = SQLHelper.getStatusCardPayment();
        assertEquals("APPROVED", paymentStatus);
    }

}
