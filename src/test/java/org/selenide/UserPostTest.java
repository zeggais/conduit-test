package org.selenide;

import com.codeborne.selenide.Condition;
import org.junit.Test;
import static com.codeborne.selenide.Selenide.*;

public class UserPostTest extends AbstractTestSetUp{

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
