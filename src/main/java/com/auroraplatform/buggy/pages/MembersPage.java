package com.auroraplatform.buggy.pages;

import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Основная страница
 *
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
    public WebElement counterBlocSelector;
    @FindBy(css = ".btn-primary[type='submit']")
    public WebElement incrementButton;
    @FindBy(css = ".row [action='/increment'] #counter_value")
    public WebElement counterValueSelector;

    @FindBy(css = ".row .col-md-6:nth-of-type(2)")
    public WebElement itemsListBlocSelector;
    @FindBy(xpath = "//*[@class='col-md-6'][2]/ol/li")
    public WebElement itemsListElement;
    @FindBy(css = ".form-control[name='title']")
    public WebElement titleField;
    @FindBy(css = ".btn-success[type='submit']")
    public WebElement addButton;

    String itemElement = "//*[@class='col-md-6'][2]/ol/li[contains(text(),'%s')]";
    String removeButton = "//*[@class='col-md-6'][2]/ol/li[%d]/a";
    String titleItemElement = "//*[@class='col-md-6'][2]/ol/li[%d]";

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
        return waitForElementPresent(counterBlocSelector);
    }

    /**
     * Проверяем наличие блока Items List
     *
     * @return boolean
     */
    public boolean isBlocItemsListPresent() {
        return waitForElementPresent(itemsListBlocSelector);
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
     *
     * @return
     */
    public String getCounterValue() {
        return counterValueSelector.getText();
    }

    /**
     * Проверяем отображение списка значений
     *
     * @return boolean
     */
    public boolean isItemsListPresent() {
        return waitForElementPresent(itemsListElement);
    }

    /**
     * Заполняем поле заголовка значения
     *
     * @param password
     */
    public void setTitleField(String password) {
        titleField.clear();
        titleField.sendKeys(password);
    }

    /**
     * Клик по кнопке Add
     */
    public void clickAddButton() {
        addButton.click();
    }

    /**
     * Проверяем отображение элемента в списке
     *
     * @param item
     * @return
     */
    public boolean isItemInListPresent(String item) {
        String locator = String.format(itemElement, item);
        return waitForElementPresent(By.xpath(locator));
    }

    /**
     * Нажимаем удалить для элемента в списке (по номеру)
     *
     * @param number
     */
    public void clickDeteteItemFromListByNumber(int number) {
        String locator = String.format(removeButton, number);
        $(locator).click();
    }

    /**
     * Получаем имя элемента в списке (по номеру)
     *
     * @param number
     * @return
     */
    public String getTitleItemFromListByNumber(int number) {
        String locator = String.format(titleItemElement, number);
        return $(locator).getText();
    }
}
