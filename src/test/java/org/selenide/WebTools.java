package org.selenide;

import com.codeborne.selenide.Condition;
import de.svenjacobs.loremipsum.LoremIpsum;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class WebTools {
    final String REACT_REDUX_WEBSITE = "https://react-redux.realworld.io/";
    private final LoremIpsum loremIpsum = new LoremIpsum();

    public WebTools() {}

    public void signIn() {
        open(REACT_REDUX_WEBSITE);

        $("a[href='#login']").click();
        $("input[placeholder='Email']").setValue("said.zeggai@protonmail.com");
        $("input[placeholder='Password']").setValue("SVMgk5myru7akr8");
        $("button[type='submit']").click();
    }

    public void signOut(){
        $(By.linkText("Settings")).click();
        $(".btn-outline-danger").scrollTo();
        $(".btn-outline-danger").click();
    }

    public OutputPost createPost() {

        String title = "Lorem Ipsum";
        String randomText = loremIpsum.getWords(10);
        String tags = "#lorem #ipsum";

        OutputPost post = new OutputPost(title, randomText);

        $("a[href='#editor']").click();

        $$("input").get(0).setValue(title);
        $$("input").get(1).setValue(title);
        $$("input").get(2).setValue(tags);
        $("textarea").setValue(randomText);
        $("button").click();

        return post;
    }

    public String deleteFirstPost() {

        $$(".article-preview").first().click();
        String title = $("h1").getText();
        $("button.btn.btn-outline-danger.btn-sm").click();
        return title;
    }

}
