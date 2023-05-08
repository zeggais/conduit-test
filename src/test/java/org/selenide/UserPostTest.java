package org.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.junit.TextReport;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class UserPostTest {

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

    @Test
    public void userCanAddPost() {
        OutputPost post = new WebTools().createPost();

        $("h1").shouldBe(Condition.text(post.getTitle()));
        $("p").shouldBe(Condition.text(post.getText()));
    }

    @Test
    public void userCanDeleteHisPost() {

        $("a[href='#@seride']").click();
        int nbArticle = $$(".article-preview").size();
        String title = new WebTools().deleteFirstPost();
        // Go back to HomePage
        $("a[href='#@seride']").click();

        if (nbArticle > 1){
            $$(".article-preview").first().shouldNotBe(Condition.text(title));
        }
        else {
            $(".article-preview").shouldBe(Condition.text("No articles are here... yet."));
        }

    }
}
