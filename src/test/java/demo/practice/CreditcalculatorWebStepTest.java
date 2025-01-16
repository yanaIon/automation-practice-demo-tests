package demo.practice;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CreditcalculatorWebStepTest {
    //такой подход больше подходит для переиспользования этих степов в других тестах
    @Test
    public void testCreditCalculator() {
        openMainPage();
        fillCreditDetails();
        calculateCredit();
        verifyResultFormIsVisible();
        verifyMonthlyPaymentText();
    }
    @Step("Открываем главную страницу кредитного калькулятора")
    public void openMainPage() {
        open("https://creditcalculator.pointschool.ru/");
    }

    @Step("Вводим значения в поля: запрашиваемый кредит, первоначальный взнос и срок кредита")
    public void fillCreditDetails() {
        SelenideElement fieldDesiredLoan = $("[name='field-desired-loan']");
        SelenideElement initialPaymentInput = $("[name='field-initial-payment']");
        SelenideElement creditTermInput = $("[name='field-credit-term']");

        fieldDesiredLoan.click();
        fieldDesiredLoan.clear();
        fieldDesiredLoan.setValue("200000");

        initialPaymentInput.click();
        initialPaymentInput.clear();
        initialPaymentInput.setValue("100000");

        creditTermInput.click();
        creditTermInput.clear();
        creditTermInput.setValue("36");
    }

    @Step("Нажимаем на кнопку 'РАССЧИТАТЬ'")
    public void calculateCredit() {
        $("[data-btn-action='do-result']").click();
    }

    @Step("Проверяем, что блок с результатом расчета появился")
    public void verifyResultFormIsVisible() {
        SelenideElement resultForm = $("#credit-short-result-form");

        resultForm.shouldBe(Condition.visible);
    }

    @Step("Проверяем, что ежемесячный платеж отображается с ожидаемым текстом")
    public void verifyMonthlyPaymentText() {
        SelenideElement monthlyPayment = $(".monthly-payment-short");

        monthlyPayment.shouldHave(Condition.text("3 314 - 5 303 P"));
    }
}
