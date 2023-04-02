package ru.netology.data;


import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {

    private DataHelper() {
    }

    public static String getApprovedCardNumber() {
        return "4444444444444441";
    }

    public static String getDeclinedCardNumber() {
        return "4444444444444442";
    }

    public static String getInvalidCardNumber() {
        return "4444";
    }

    public static String getCurrentMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getLastMonth() {
        return LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getCurrentYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getNextYear() {
        return LocalDate.now().plusYears(2).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getLastYear() {
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getName() {
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getCVC() {
        return Integer.toString(faker.number().numberBetween(100, 999));
    }

    static Faker faker = new Faker(new Locale("en"));

    @Value
    public static class CardInfo {
        private String cardNumber;
        private String month;
        private String year;
        private String name;
        private String cvc;
    }

    public static CardInfo getCardInfoValidValues() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getNextYear(), getName(), getCVC());
    }

    public static CardInfo getCardInfoCardNumberDeclined() {
        return new CardInfo(getDeclinedCardNumber(), getCurrentMonth(), getNextYear(), getName(), getCVC());
    }

    public static CardInfo getCardInfoCardNumberEmpty() {
        return new CardInfo(" ", getCurrentMonth(), getNextYear(), getName(), getCVC());
    }

    public static CardInfo getCardInfoCardNumberLess() {
        return new CardInfo(getInvalidCardNumber(), getCurrentMonth(), getNextYear(), getName(), getCVC());
    }

    public static CardInfo getCardInfoCardNumberNull() {
        return new CardInfo("0000000000000000", getCurrentMonth(), getNextYear(), getName(), getCVC());
    }

    public static CardInfo getCardInfoMonthEmpty() {
        return new CardInfo(getApprovedCardNumber(), " ", getNextYear(), getName(), getCVC());
    }

    public static CardInfo getCardInfoMonthNull() {
        return new CardInfo(getApprovedCardNumber(), "00", getNextYear(), getName(), getCVC());
    }

    public static CardInfo getCardInfoMonthMore() {
        return new CardInfo(getApprovedCardNumber(), "13", getNextYear(), getName(), getCVC());
    }

    public static CardInfo getCardInfoExpiredLastMonth() {
        return new CardInfo(getApprovedCardNumber(), getLastMonth(), getCurrentYear(), getName(), getCVC());
    }

    public static CardInfo getCardInfoYearEmpty() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), " ", getName(), getCVC());
    }

    public static CardInfo getCardInfoYearNull() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), "00", getName(), getCVC());
    }

    public static CardInfo getCardInfoExpiredLastYear() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getLastYear(), getName(), getCVC());
    }

    public static CardInfo getCardInfoOwnerEmpty() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getNextYear(), " ", getCVC());
    }

    public static CardInfo getCardInfoCyrillicName() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getNextYear(), "Иван Иванов", getCVC());
    }

    public static CardInfo getCardInfoSymbolName() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getNextYear(), "!@#123#$%", getCVC());
    }

    public static CardInfo getCardInfoCVCEmpty() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getNextYear(), getName(), " ");
    }

    public static CardInfo getCardInfoCVCNull() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getNextYear(), getName(), "000");
    }

}
