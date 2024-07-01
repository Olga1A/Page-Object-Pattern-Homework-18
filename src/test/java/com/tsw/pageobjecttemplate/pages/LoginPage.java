package com.tsw.pageobjecttemplate.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

// page_url = https://www.jetbrains.com/
public class LoginPage {

    public SelenideElement loginField = $(By.id("username"));
    public SelenideElement passwordField = $(By.id("password"));

    public SelenideElement signInButton = $x("//*[@data-name='signIn-button']");
    public SelenideElement loginErrorMessage = $x("//*[@data-name='authorizationError-popup']");
    public SelenideElement popupCloseButton = $x("//*[@data-name='authorizationError-popup-close-button']");

    public OrderPage performLoginAndReturnOrderPage() {
        loginField.setValue("al-olga");
        passwordField.setValue("pgd3VbfT2nt");
        signInButton.click();
        return new OrderPage();
    }
}

