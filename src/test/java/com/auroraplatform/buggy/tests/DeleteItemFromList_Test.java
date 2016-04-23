package com.auroraplatform.buggy.tests;

import com.auroraplatform.buggy.pages.LoginPage;
import com.auroraplatform.buggy.pages.MembersPage;
import org.junit.Assert;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import qa.Config;

/**
 * Удаляем элемент из списка
 *
 * @author timestamp <n.chufyrina@gmail.com>
 */
public class DeleteItemFromList_Test extends BaseTestClass {

    @Test
    public void delete_item_from_list() {

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

        // Проверяем отображение значений в списке
        assertTrue("В списке должны быть добавлены значения",
                membersPage.isItemsListPresent());

        // Получаем заголовок первого значения
        String name = membersPage.getTitleItemFromListByNumber(1);
        String title = name.substring(0, name.length() - 7);

        // Проверяем, что значение отображается
        assertTrue(String.format("Значение %s должно отображаться", title),
                membersPage.isItemInListPresent(title));

        // Удаляем
        membersPage.clickDeteteItemFromListByNumber(1);

        // Проверяем, что значение не отображается
        assertFalse(String.format("Значение %s не должно отображаться", title),
                membersPage.isItemInListPresent(title));
    }
}
