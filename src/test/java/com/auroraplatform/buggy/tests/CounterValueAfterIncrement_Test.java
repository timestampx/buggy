package com.auroraplatform.buggy.tests;

import com.auroraplatform.buggy.pages.LoginPage;
import com.auroraplatform.buggy.pages.MembersPage;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import qa.Config;

/**
 * Значение счетчика после нажатия на кнопку Increment
 * @author timestamp <n.chufyrina@gmail.com>
 */
public class CounterValueAfterIncrement_Test extends BaseTestClass {

    @Test
    public void counter_value_after_increment() {

        LoginPage loginPage = new LoginPage(driver);

        // Открываем страницу авторизации
        loginPage.open();
        Assert.assertTrue("Страница авторизации не отображается",
                loginPage.isloginPagePresent());

        // Вводим логин и пароль
        loginPage.setLogin(Config.getLogin());
        loginPage.setPassword(Config.getPassword());

        // Нажимаем кнопку Войти
        loginPage.clickSignIn();

        // Проверяем, что авторизация прошла
        MembersPage membersPage = new MembersPage(driver);

        assertTrue("URL страницы отличается от ожидаемого",
                driver.getCurrentUrl().contains("members"));

        membersPage.isMessageAboutLoggedInPresent();
        
        // Получаем значение счетчика
        String counter = membersPage.getCounterValue();
        int i = Integer.parseInt(counter) + 1;
        String newCounter = Integer.toString(i);
        
        // Увеличиваем счетчик на 1
        membersPage.clickIncrementButton();
        
        // Проверяем значение счетчика
        assertEquals("Значение счетчика отличается от ожидаемого",
                newCounter , membersPage.getCounterValue());        
    }
}
