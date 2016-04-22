package com.auroraplatform.buggy.pages;

import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Основная страница
 * @author timestamp <n.chufyrina@gmail.com>
 */
@DefaultUrl("https://buggy.auroraplatform.com/members")
public class MembersPage extends BasePage {

    public MembersPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".alert-success")
    public WebElement alertSuccessSelector;
    @FindBy(css = ".row [action='/increment']")
    public WebElement incrementRowSelector;
    @FindBy(css = ".btn-primary[type='submit']")
    public WebElement incrementButton;
    @FindBy(css = ".row [action='/increment'] #counter_value")
    public WebElement counterValueSelector;

    /**
     * Проверяем наличие уведомления об успешной авторизации
     *
     * @return boolean
     */
    public boolean isMessageAboutLoggedInPresent() {
        return waitForElementPresent(alertSuccessSelector);
    }

    /**
     * Проверяем наличие блока Counter
     *
     * @return boolean
     */
    public boolean isBlocCounterPresent() {
        return waitForElementPresent(incrementRowSelector);
    }

    /**
     * Проверяем наличие кнопки Increment
     *
     * @return boolean
     */
    public boolean isIncrementButtonPresent() {
        return waitForElementPresent(incrementButton);
    }

    /**
     * Клик по кнопке Increment
     */
    public void clickIncrementButton() {
        incrementButton.click();
    }

    /**
     * Клик по кнопке Increment
     * @return
     */
    public String getCounterValue() {
        return counterValueSelector.getText();
    }

}
