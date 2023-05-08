package org.selenide;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AuthentificationTest.class,
        UserPostTest.class,
        FavoriteArticleListTest.class
})
public class TestSuite {
}
