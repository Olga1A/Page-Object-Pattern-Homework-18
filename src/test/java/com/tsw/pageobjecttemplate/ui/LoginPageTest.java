package com.tsw.pageobjecttemplate.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.tsw.pageobjecttemplate.pages.LoginPage;
import com.tsw.pageobjecttemplate.pages.OrderPage;
import com.tsw.pageobjecttemplate.pages.StatusPage;
import org.openqa.selenium.chrome.ChromeOptions;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LoginPageTest {
    LoginPage loginPage = new LoginPage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");
        open("https://fe-delivery.tallinn-learning.ee/");
    }

    @Test
    public void loginTestAndCheckErrorMessage() {
        loginPage.loginField.setValue("asdfaasd");
        loginPage.passwordField.setValue("123432343");
        loginPage.signInButton.click();
        loginPage.loginErrorMessage.shouldBe(visible);
        loginPage.popupCloseButton.shouldBe(visible);
    }

    @Test
    public void sucssesfulLogin() {
        OrderPage orderPage = loginPage.performLoginAndReturnOrderPage();
        orderPage.orderButton.shouldBe(visible);
        orderPage.statusButton.shouldBe(visible);
    }

    @Test
    public void createNewOrder() {
        OrderPage orderPage = loginPage.performLoginAndReturnOrderPage();
        orderPage.inputUsername.setValue("Barbara");
        orderPage.inputPhone.setValue("56565656");
        orderPage.inputComment.setValue("Thanks!");
        orderPage.getOrderButton.click();
    }

    //Homework 18

    @Test
    public void statusButtonCheckForValidTrackingCode() {
        OrderPage orderPage = loginPage.performLoginAndReturnOrderPage();
        orderPage.statusButton.click();
        orderPage.inputTrackingCode.setValue("1036");
        orderPage.trackingButton.click();
    }

    @Test
    public void statusButtonCheckForInvalidTrackingCode() {
        OrderPage orderPage = loginPage.performLoginAndReturnOrderPage();
        orderPage.statusButton.click();
        orderPage.inputTrackingCode.setValue("0000");
        orderPage.trackingButton.click();
    }

    @Test
    public void logoutButtonCheckInOrderPage() {
        OrderPage orderPage = loginPage.performLoginAndReturnOrderPage();
        orderPage.logoutButton.click();
        loginPage.loginField.shouldBe(visible);
    }
}
