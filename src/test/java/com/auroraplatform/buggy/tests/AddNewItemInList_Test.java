package com.auroraplatform.buggy.tests;

import com.auroraplatform.buggy.pages.LoginPage;
import com.auroraplatform.buggy.pages.MembersPage;
import java.util.UUID;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import qa.Config;

/**
 * Добавляем новый элемент в список
 *
 * @author timestamp <n.chufyrina@gmail.com>
 */
public class AddNewItemInList_Test extends BaseTestClass {

    @Test
    public void add_new_item_in_list() {

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

        // Проверяем отображение блока ItemsList
        assertTrue("Блок ItemsList должен отображаться",
                membersPage.isBlocItemsListPresent());

        // Заполняем поле ввода значения
        String title = UUID.randomUUID().toString();
        membersPage.setTitleField(title);

        // Нажимаем кнопку Добавить
        membersPage.clickAddButton();

        // Проверяем, что значение добавилось
        assertTrue(String.format("Значение %s должно отображаться", title),
                membersPage.isItemInListPresent(title));
    }
}
