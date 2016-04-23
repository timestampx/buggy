package com.auroraplatform.buggy.tests;

import com.auroraplatform.buggy.pages.LoginPage;
import com.auroraplatform.buggy.pages.MembersPage;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import qa.Config;

/**
 * Значение счетчика после обновления страницы
 *
 * @author timestamp <n.chufyrina@gmail.com>
 */
public class CounterValueAfterRefresh_Test extends BaseTestClass {

    @Test
    public void counter_value_after_refresh() {

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

        assertTrue("Сообщение об авторизации должно отображаться",
                membersPage.isMessageAboutLoggedInPresent());

        // Проверяем отображение блока Counter
        assertTrue("Блок Counter должен отображаться",
                membersPage.isBlocCounterPresent());

        // Получаем значение счетчика
        String counter = membersPage.getCounterValue();

        // Обновляем страницу
        driver.navigate().refresh();

        // Проверяем значение счетчика
        assertEquals("Значение счетчика отличается от ожидаемого",
                counter, membersPage.getCounterValue());
    }
}
