package org.selenide;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.junit.TextReport;
import io.qameta.allure.selenide.AllureSelenide;

import org.junit.Rule;
import org.junit.Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;

public class AuthentificationTest
{
    @Rule
    public TextReport textReport = new TextReport();

    @Test
    public void userCanSignIn()
    {
        new WebTools().signIn();
        $("a[href='#@seride']").shouldHave(Condition.text("seride"));
    }

    @Test
    public void canUserSignOut() {
        new WebTools().signIn();

        $(By.linkText("Settings")).click();
        $(".btn-outline-danger").scrollTo();
        $(".btn-outline-danger").click();

        $("a[href='#login']").shouldBe(Condition.visible);
    }

    @Test
    public void signInWithAPI(){

    }

    @BeforeClass
    public static void setUp() {
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().screenshots(true).savePageSource(true));
    }

    @AfterClass
    public static void tearDown(){
        $(By.linkText("Settings")).click();
        $(".btn-outline-danger").scrollTo();
        $(".btn-outline-danger").click();
    }
}
