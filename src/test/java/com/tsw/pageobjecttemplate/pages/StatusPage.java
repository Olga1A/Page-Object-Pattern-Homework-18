package com.tsw.pageobjecttemplate.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class StatusPage {

    public SelenideElement logoutButton = $x("//*[@data-name='logout-button']");
    public SelenideElement statusButton = $x("//*[@data-name='openStatusPopup-button']");
    public SelenideElement languageButton = $x("//*[@data-name='language__button language__button_active']");

}
