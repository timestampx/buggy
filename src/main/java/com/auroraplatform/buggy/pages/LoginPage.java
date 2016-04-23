package com.auroraplatform.buggy.pages;

import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Страница авторизации
 *
 * @author timestamp <n.chufyrina@gmail.com>
 */
@DefaultUrl("https://buggy.auroraplatform.com/login")
public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".form .col-lg-4")
    public WebElement loginPageSelector;
    @FindBy(css = ".form-control[name='email']")
    public WebElement usernameField;
    @FindBy(css = ".form-control[name='password']")
    public WebElement passwordField;
    @FindBy(css = ".btn-success[type='submit']")
    public WebElement loginButton;

    /**
     * Проверяем, что страница авторизации открылась
     *
     * @return boolean
     */
    public boolean isloginPagePresent() {
        return waitForElementPresent(loginPageSelector);
    }

    /**
     * Заполняем поле логина
     *
     * @param login
     */
    public void setLogin(String login) {
        usernameField.clear();
        usernameField.sendKeys(login);
    }

    /**
     * Заполняем поле пароля
     *
     * @param password
     */
    public void setPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    /**
     * Клик по кнопке Войти
     */
    public void clickSignIn() {
        loginButton.click();
    }
}
