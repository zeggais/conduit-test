package org.selenide;

import com.codeborne.selenide.junit.TextReport;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AbstractTestSetUp {
    @Rule
    public TextReport textReport = new TextReport();

    @BeforeClass
    public static void setUp(){

        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().screenshots(true).savePageSource(true));

        open("https://react-redux.realworld.io/");

        $("a[href='#login']").click();
        $("input[placeholder='Email']").setValue("said.zeggai@protonmail.com");
        $("input[placeholder='Password']").setValue("SVMgk5myru7akr8");
        $("button[type='submit']").click();
    }

    @AfterClass
    public static void tearDown(){
        $(By.linkText("Settings")).click();
        $(".btn-outline-danger").scrollTo();
        $(".btn-outline-danger").click();
    }
}
