package demo.practice;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class CreditcalculatorStepTest {

    @Test
    @Feature("Расчет кредита")
    @Story("Краткий расчет кредита")
    @Owner("Iana Ionova")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Рассчет ежемесячного платежа по кредиту")
    public void creditCalculationVerificationLambdaStepTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        SelenideElement fieldDesiredLoan = $("[name='field-desired-loan']");
        SelenideElement initialPaymentInput = $("[name='field-initial-payment']");
        SelenideElement creditTermInput = $("[name='field-credit-term']");
        SelenideElement resultForm = $("#credit-short-result-form");
        SelenideElement monthlyPayment = $(".monthly-payment-short");

        step("Открываем главную страницу кредитного калькулятора", () -> {
                open("https://creditcalculator.pointschool.ru/");
        });

        step("Вводим значения в поля", () -> {
            fieldDesiredLoan.click();
            fieldDesiredLoan.clear();
            fieldDesiredLoan.setValue("200000");

            initialPaymentInput.click();
            initialPaymentInput.clear();
            initialPaymentInput.setValue("100000");

            creditTermInput.click();
            creditTermInput.clear();
            creditTermInput.setValue("36");
        });

        step("Нажимаем на кнопку \"РАССЧИТАТЬ\"", () -> {
            $("[data-btn-action='do-result']").click();
        });

        step("Проверяем, что блок с результатом расчета появился", () -> {
            resultForm.shouldBe(Condition.visible);
        });

        step("Проверяем, что ежемесячный платеж отображается с ожидаемым текстом", () -> {
            monthlyPayment.shouldHave(Condition.text("3 314 - 5 303 P"));
        });
    }

//    @Test
//    //такой подход больше подходит для переиспользования этих степов в других тестах
//    public void creditCalculationVerificationWebStepTest() {
//        SelenideLogger.addListener("allure", new AllureSelenide());
//        CreditcalculatorWebStepTest steps = new CreditcalculatorWebStepTest();
//
//        steps.openMainPage();
//        steps.fillCreditDetails();
//        steps.calculateCredit();
//        steps.verifyResultFormIsVisible();
//        steps.verifyMonthlyPaymentText();
//    }
}
