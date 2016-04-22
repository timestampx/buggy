package com.auroraplatform.buggy.pages;

import java.util.concurrent.TimeUnit;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.scheduling.ThucydidesFluentWait;
import org.openqa.selenium.*;
import qa.VisibilityOfElement;
import qa.VisibilityOfElementLocated;

/**
 * Базовая страница.
 * Описаны дополнительные методы работы со страницей.
 * @author timestamp <n.chufyrina@gmail.com>
 */
public class BasePage extends PageObject {

    private static final int DEFAULT_IMPLICITLY_WAIT = 15000;
    private final int implicitWait = Integer.parseInt(System.getProperty("webdriver.timeouts.implicitlywait", Integer.toString(DEFAULT_IMPLICITLY_WAIT)));

    public BasePage(WebDriver driver) {
        super(driver, DEFAULT_IMPLICITLY_WAIT);
    }

    protected int getImplicitWait() {
        return implicitWait;
    }

    /**
     * Ожидание видимого элемента
     *
     * @param locator
     * @return
     */
    protected boolean waitForElementPresent(By locator) {
        setImplicitTimeout(0, TimeUnit.MILLISECONDS);
        Boolean result = true;
        ThucydidesFluentWait<WebDriver> wait = waitForCondition().withTimeout(implicitWait, TimeUnit.MILLISECONDS);
        try {
            wait.until(new VisibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            result = false;
        } catch (Throwable t) {
            throw new Error(t);
        } finally {
            resetImplicitTimeout();
        }
        return result;
    }

    /**
     * Ожидание видимого элемента
     *
     * @param locator
     * @param timeout
     * @return
     */
    protected boolean waitForElementPresent(By locator, int timeout) {
        setImplicitTimeout(0, TimeUnit.MILLISECONDS);
        Boolean result = true;
        ThucydidesFluentWait<WebDriver> wait = waitForCondition()
                .withTimeout(timeout, TimeUnit.MILLISECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS);
        try {
            wait.until(new VisibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            result = false;
        } catch (Throwable t) {
            throw new Error(t);
        } finally {
            resetImplicitTimeout();
        }
        return result;
    }

    /**
     * Ожидание видимого элемента
     *
     * @param element
     * @return
     */
    protected boolean waitForElementPresent(WebElement element) {
        Boolean result = true;
        ThucydidesFluentWait<WebDriver> wait = waitForCondition()
                .withTimeout(implicitWait, TimeUnit.MILLISECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS);
        WebElement wef = $(element).withTimeoutOf(100, TimeUnit.MILLISECONDS);
        try {
            wait.until(new VisibilityOfElement(wef));
        } catch (TimeoutException e) {
            result = false;
        } catch (Throwable t) {
            throw new Error(t);
        }
        return result;
    }

    /**
     * Ожидание видимого элемента
     *
     * @param element
     * @param timeout
     * @return
     */
    protected boolean waitForElementPresent(WebElement element, int timeout) {
        setImplicitTimeout(0, TimeUnit.MILLISECONDS);
        Boolean result = true;
        ThucydidesFluentWait<WebDriver> wait = waitForCondition()
                .withTimeout(timeout, TimeUnit.MILLISECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS);
        WebElement wef = $(element).withTimeoutOf(100, TimeUnit.MILLISECONDS);
        try {
            wait.until(new VisibilityOfElement(wef));
        } catch (TimeoutException e) {
            result = false;
        } catch (Throwable t) {
            throw new Error(t);
        } finally {
            resetImplicitTimeout();
        }
        return result;
    }

    /**
     * Return current browser User-Agent
     *
     * @return
     */
    public String getCurrentUserAgent() {
        String getUserAgent = "return navigator.userAgent;";
        JavascriptExecutor js = ((JavascriptExecutor) getDriver());
        return (String) js.executeScript(getUserAgent);
    }

    /**
     * Ожидание по заданному таймауту
     *
     * @param timeout
     */
    public void waitTimeout(int timeout) {
        waitABit(timeout);
    }

}
