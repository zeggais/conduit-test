package org.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.junit.TextReport;
import com.codeborne.selenide.logevents.SelenideLogger;

import io.qameta.allure.selenide.AllureSelenide;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.*;

public class FavoriteArticleListTest {

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
    public void canUserAddOtherUserArticleToFavorites(){

        $(By.linkText("Global Feed")).click();

        // Getting the first article found
        WebElement firstArticle = $$(".article-preview").first().getWrappedElement();
        String title = firstArticle.findElement(By.cssSelector("h1")).getText();
        firstArticle.findElement(By.cssSelector("button")).click();

        // Back to favorite article profile page
        $("a[href='#@seride']").click();
        $(By.linkText("Favorited Articles")).click();

        // The first article should be the one we previously liked
        firstArticle = $$(".article-preview").first().getWrappedElement();
        String favoriteTitle = firstArticle.findElement(By.cssSelector("h1")).getText();
        Assert.assertEquals(favoriteTitle, title);
    }

    @Test
    public void canUserRemoveArticleFromFavorites() {

        // Go to favorite article profile page
        $("a[href='#@seride']").click();
        $(By.linkText("Favorited Articles")).click();

        int nbArticle = $$(".article-preview").size();

        // Unlinking the first article
        WebElement firstArticle = $$(".article-preview").first().getWrappedElement();
        String title = firstArticle.findElement(By.cssSelector("h1")).getText();
        firstArticle.findElement(By.cssSelector("button")).click();

        Selenide.refresh();

        if (nbArticle > 1){
            firstArticle = $$(".article-preview").first().getWrappedElement();
            Assert.assertNotEquals(firstArticle.findElement(By.cssSelector("h1")).getText(), title);
        }
        else {
            $(".article-preview").shouldBe(Condition.text("No articles are here... yet."));
        }
    }
}
