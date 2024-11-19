package demo.practice;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CreditcalculatorTest {

    @Test
    public void creditCalculationVerificationTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        SelenideElement fieldDesiredLoan = $("[name='field-desired-loan']");
        SelenideElement initialPaymentInput = $("[name='field-initial-payment']");
        SelenideElement creditTermInput = $("[name='field-credit-term']");
        SelenideElement resultForm = $("#credit-short-result-form");
        SelenideElement monthlyPayment = $(".monthly-payment-short");

        // Открываем страницу
        open("https://creditcalculator.pointschool.ru/");

        // Вводим значения в поля
        fieldDesiredLoan.click();
        fieldDesiredLoan.clear();
        fieldDesiredLoan.setValue("200000");

        initialPaymentInput.click();
        initialPaymentInput.clear();
        initialPaymentInput.setValue("100000");

        creditTermInput.click();
        creditTermInput.clear();
        creditTermInput.setValue("36");

        // Нажимаем на кнопку "РАССЧИТАТЬ"
        $("[data-btn-action='do-result']").click();

        // Проверяем, что блок с результатом расчета появился
        resultForm.shouldBe(Condition.visible);

        // Проверяем, что ежемесячный платеж отображается с ожидаемым текстом
        monthlyPayment.shouldHave(Condition.text("3 314 - 5 303 P"));
    }
}
