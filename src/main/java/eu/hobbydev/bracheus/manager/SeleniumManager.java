package eu.hobbydev.bracheus.manager;


import eu.hobbydev.bracheus.exceptions.SeleniagramNoSuchElementException;
import eu.hobbydev.bracheus.settings.SeleniagramChrome;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

/**
 * Copyright (C) 2025 Bracheus
 * <p>
 * All rights reserved. Unauthorized copying, distribution, or modification
 * of this code without express permission from Bracheus is
 * strictly prohibited.
 * <p>
 * This project is the result of personal development and innovation by Bracheus.
 * By using or modifying this code, you acknowledge the ownership of Bracheus
 * and agree to comply with the terms outlined in the project’s
 * licensing agreement.
 * <p>
 * Created by: Bracheus (GitHub: https://github.com/Bracheus-DE)
 * <p>
 * For inquiries, collaborations, or licensing information, contact:
 * bracheus@hobbydev.eu
 */

/**
 * The `SeleniumManager` class provides methods for managing interactions with a Selenium WebDriver.
 * It is responsible for tasks such as initializing the WebDriver, starting and stopping the WebDriver session,
 * and finding elements on a webpage using various locators such as ID, class name, name, text, XPath, and CSS selectors.
 * This class supports automation tasks and interaction with different HTML elements on a webpage.
 * It also provides helper methods for JavaScript execution and page navigation.
 */
public class SeleniumManager {

    private WebDriver seleniumDriver;

    /**
     * Starts the Selenium WebDriver.
     * If the WebDriver is not already initialized, it calls the setup method to initialize it.
     */
    public void start() {
        if (seleniumDriver == null) {
            setup();
        }
    }

    /**
     * Sets up the Selenium WebDriver with ChromeOptions for browser configuration.
     * Configures the WebDriver with options such as headless mode, user-agent, and other settings.
     * Initializes the WebDriver instance with these options.
     */
    private void setup() {
        ChromeOptions chromeOptions = SeleniagramChrome.getChromeOptions();
        seleniumDriver = new ChromeDriver(chromeOptions);
    }

    /**
     * Stops the Selenium WebDriver session.
     * If the WebDriver is initialized, it terminates the session and quits the browser.
     */
    public void stop() {
        if (seleniumDriver != null) {
            seleniumDriver.close();
            seleniumDriver.quit();
        }
    }

    /**
     * Returns the current instance of the Selenium WebDriver.
     *
     * @return the Selenium WebDriver instance.
     */
    private WebDriver getSeleniumDriver() {
        return seleniumDriver;
    }

    /**
     * Opens the specified URL in the current WebDriver instance.
     *
     * @param url the URL to open.
     */
    public void open(String url) {
        getSeleniumDriver().get(url);
    }

    /**
     * Finds an element by its class name.
     *
     * @param className the class name of the element to find.
     * @return the WebElement found by the specified class name.
     * @throws SeleniagramNoSuchElementException if the element is not found.
     */
    public WebElement findElementByClass(String className) {
        WebElement webElement;
        try {
            webElement = getSeleniumDriver().findElement(By.className(className));
        } catch (NoSuchElementException elementException) {
            throw new SeleniagramNoSuchElementException("Can't find Element with Class: " + className);
        }
        return webElement;
    }

    /**
     * Finds an element by its ID.
     *
     * @param id the ID of the element to find.
     * @return the WebElement found by the specified ID.
     * @throws SeleniagramNoSuchElementException if the element is not found.
     */
    public WebElement findElementByID(String id) {
        WebElement webElement;
        try {
            webElement = getSeleniumDriver().findElement(By.id(id));
        } catch (NoSuchElementException elementException) {
            throw new SeleniagramNoSuchElementException("Can't find Element with ID: " + id);
        }
        return webElement;
    }

    /**
     * Finds an element by its name.
     *
     * @param name the name attribute of the element to find.
     * @return the WebElement found by the specified name.
     * @throws SeleniagramNoSuchElementException if the element is not found.
     */
    public WebElement findElementByName(String name) {
        WebElement webElement;
        try {
            webElement = getSeleniumDriver().findElement(By.name(name));
        } catch (NoSuchElementException elementException) {
            throw new SeleniagramNoSuchElementException("Can't find Element with Name: " + name);
        }
        return webElement;
    }

    /**
     * Finds a button element by its ID.
     *
     * @param id the ID of the button element to find.
     * @return the WebElement representing the button.
     * @throws SeleniagramNoSuchElementException if the button is not found.
     */
    public WebElement findButtonByID(String id) {
        WebElement webElement;
        try {
            webElement = getSeleniumDriver().findElement(By.xpath("//button[@id='" + id + "']"));
        } catch (NoSuchElementException elementException) {
            throw new SeleniagramNoSuchElementException("Can't find Button with ID: " + id);
        }
        return webElement;
    }

    /**
     * Finds a button element by its visible text.
     *
     * @param text the visible text of the button.
     * @return the WebElement representing the button.
     * @throws SeleniagramNoSuchElementException if the button is not found.
     */
    public WebElement findButtonByText(String text) {
        WebElement webElement;
        try {
            webElement = getSeleniumDriver().findElement(By.xpath("//button[text()='" + text + "']"));
        } catch (NoSuchElementException elementException) {
            throw new SeleniagramNoSuchElementException("Can't find Button with Text: " + text);
        }
        return webElement;
    }

    /**
     * Finds an input element by its ID.
     *
     * @param id the ID of the input element to find.
     * @return the WebElement representing the input.
     * @throws SeleniagramNoSuchElementException if the input is not found.
     */
    public WebElement findInputByID(String id) {
        WebElement webElement;
        try {
            webElement = getSeleniumDriver().findElement(By.xpath("//input[@id='" + id + "']"));
        } catch (NoSuchElementException elementException) {
            throw new SeleniagramNoSuchElementException("Can't find Input with ID: " + id);
        }
        return webElement;
    }

    /**
     * Finds an input element by its name.
     *
     * @param name the name attribute of the input element to find.
     * @return the WebElement representing the input.
     * @throws SeleniagramNoSuchElementException if the input is not found.
     */
    public WebElement findInputByName(String name) {
        WebElement webElement;
        try {
            webElement = getSeleniumDriver().findElement(By.xpath("//input[@name='" + name + "']"));
        } catch (NoSuchElementException elementException) {
            throw new SeleniagramNoSuchElementException("Can't find Input with Name: " + name);
        }
        return webElement;
    }

    /**
     * Finds an input element by its class name.
     *
     * @param className the class name of the input element to find.
     * @return the WebElement representing the input.
     * @throws SeleniagramNoSuchElementException if the input is not found.
     */
    public WebElement findInputByClass(String className) {
        WebElement webElement;
        try {
            webElement = getSeleniumDriver().findElement(By.xpath("//input[@class='" + className + "']"));
        } catch (NoSuchElementException elementException) {
            throw new SeleniagramNoSuchElementException("Can't find Input with Class: " + className);
        }
        return webElement;
    }

    /**
     * Finds an input element by its placeholder text.
     *
     * @param placeholder the placeholder text of the input element to find.
     * @return the WebElement representing the input.
     * @throws SeleniagramNoSuchElementException if the input is not found.
     */
    public WebElement findInputByPlaceholder(String placeholder) {
        WebElement webElement;
        try {
            webElement = getSeleniumDriver().findElement(By.xpath("//input[@placeholder='" + placeholder + "']"));
        } catch (NoSuchElementException elementException) {
            throw new SeleniagramNoSuchElementException("Can't find Input with Placeholder: " + placeholder);
        }
        return webElement;
    }

    /**
     * Finds a div element by its placeholder text.
     *
     * @param placeholder the placeholder text of the div element to find.
     * @return the WebElement representing the div.
     * @throws SeleniagramNoSuchElementException if the div is not found.
     */
    public WebElement findDivByPlaceholder(String placeholder) {
        WebElement webElement;
        try {
            webElement = getSeleniumDriver().findElement(By.xpath("//div[@placeholder='" + placeholder + "']"));
        } catch (NoSuchElementException elementException) {
            throw new SeleniagramNoSuchElementException("Can't find div with Placeholder: " + placeholder);
        }
        return webElement;
    }

    /**
     * Finds a div element by its ID.
     *
     * @param id the ID of the div element to find.
     * @return the WebElement representing the div.
     * @throws SeleniagramNoSuchElementException if the div is not found.
     */
    public WebElement findDivByID(String id) {
        WebElement webElement;
        try {
            webElement = getSeleniumDriver().findElement(By.xpath("//div[@id='" + id + "']"));
        } catch (NoSuchElementException elementException) {
            throw new SeleniagramNoSuchElementException("Can't find Div with ID: " + id);
        }
        return webElement;
    }

    /**
     * Finds a div element by its class name.
     *
     * @param className the class name of the div element to find.
     * @return the WebElement representing the div.
     * @throws SeleniagramNoSuchElementException if the div is not found.
     */
    public WebElement findDivByClass(String className) {
        WebElement webElement;
        try {
            webElement = getSeleniumDriver().findElement(By.xpath("//div[@class='" + className + "']"));
        } catch (NoSuchElementException elementException) {
            throw new SeleniagramNoSuchElementException("Can't find Div with Class: " + className);
        }
        return webElement;
    }

    /**
     * Finds a div element by its text content.
     *
     * @param text the text content of the div element to find.
     * @return the WebElement representing the div.
     * @throws SeleniagramNoSuchElementException if the div is not found.
     */
    public WebElement findDivByText(String text) {
        WebElement webElement;
        try {
            webElement = getSeleniumDriver().findElement(By.xpath("//div[text()='" + text + "']"));
        } catch (NoSuchElementException elementException) {
            throw new SeleniagramNoSuchElementException("Can't find Div with text: " + text);
        }
        return webElement;
    }

    /**
     * Finds a span element by its class name.
     *
     * @param className the class name of the span element to find.
     * @return the WebElement representing the span.
     * @throws SeleniagramNoSuchElementException if the span is not found.
     */
    public WebElement findSpanByClass(String className) {
        WebElement webElement;
        try {
            webElement = getSeleniumDriver().findElement(By.xpath("//span[@class='" + className + "']"));
        } catch (NoSuchElementException elementException) {
            throw new SeleniagramNoSuchElementException("Can't find Span with Class: " + className);
        }
        return webElement;
    }

    /**
     * Finds a link element by its link text.
     *
     * @param linkText the link text of the anchor element to find.
     * @return the WebElement representing the link.
     * @throws SeleniagramNoSuchElementException if the link is not found.
     */
    public WebElement findLinkByText(String linkText) {
        WebElement webElement;
        try {
            webElement = getSeleniumDriver().findElement(By.linkText(linkText));
        } catch (NoSuchElementException elementException) {
            throw new SeleniagramNoSuchElementException("Can't find Link with Text: " + linkText);
        }
        return webElement;
    }

    /**
     * Finds a checkbox element by its ID.
     *
     * @param id the ID of the checkbox element to find.
     * @return the WebElement representing the checkbox.
     * @throws SeleniagramNoSuchElementException if the checkbox is not found.
     */
    public WebElement findCheckboxByID(String id) {
        WebElement webElement;
        try {
            webElement = getSeleniumDriver().findElement(By.xpath("//input[@type='checkbox' and @id='" + id + "']"));
        } catch (NoSuchElementException elementException) {
            throw new SeleniagramNoSuchElementException("Can't find Checkbox with ID: " + id);
        }
        return webElement;
    }

    /**
     * Retrieves the current URL of the web page that the Selenium WebDriver is currently on.
     *
     * @return the current URL as a string.
     */
    public String getUrl() {
        return getSeleniumDriver().getCurrentUrl();
    }

    /**
     * Finds a span element by its text content.
     *
     * @param text the text content of the span element to find.
     * @return the WebElement representing the span.
     * @throws SeleniagramNoSuchElementException if the span is not found.
     */
    public WebElement findSpanByText(String text) {
        WebElement webElement;
        try {
            webElement = getSeleniumDriver().findElement(By.xpath("//span[text()='" + text + "']"));
        } catch (NoSuchElementException elementException) {
            throw new SeleniagramNoSuchElementException("Can't find Span with Text: " + text);
        }
        return webElement;
    }

    /**
     * Finds an element by its XPath.
     *
     * @param xPath the XPath of the element to find.
     * @return the WebElement found by the specified XPath.
     * @throws SeleniagramNoSuchElementException if the element is not found.
     */
    public WebElement findByXpath(String xPath) {
        WebElement webElement;
        try {
            webElement = getSeleniumDriver().findElement(By.xpath(xPath));
        } catch (NoSuchElementException elementException) {
            throw new SeleniagramNoSuchElementException("Can't find XPath: " + xPath);
        }
        return webElement;
    }

    /**
     * Retrieves the JavascriptExecutor from the WebDriver instance for executing JavaScript.
     *
     * @return the JavascriptExecutor instance for the current WebDriver.
     */
    public JavascriptExecutor getJavaScJavascriptExecutor() {
        return (JavascriptExecutor) seleniumDriver;
    }

    /**
     * Reloads the current webpage.
     * Refreshes the page in the current WebDriver instance.
     */
    public void reloadPage() {
        getSeleniumDriver().navigate().refresh();
    }

    /**
     * Finds multiple elements by their CSS selector.
     *
     * @param css the CSS selector used to locate the elements.
     * @return a list of WebElements matching the specified CSS selector.
     * @throws SeleniagramNoSuchElementException if no elements are found.
     */
    public List<WebElement> findElementsByCss(String css) {
        List<WebElement> webElements;
        try {
            webElements = getSeleniumDriver().findElements(By.cssSelector(css));
        } catch (NoSuchElementException elementException) {
            throw new SeleniagramNoSuchElementException("Can't find Elements with CSS: " + css);
        }
        return webElements;
    }

    /**
     * Executes a JavaScript command within the context of the current page.
     *
     * @param script the JavaScript script to execute.
     * @param args   optional arguments for the script.
     * @return the result of executing the JavaScript script.
     */
    public Object executeJavascript(String script, Object... args) {
        return getJavaScJavascriptExecutor().executeScript(script, args);
    }

    /**
     * Finds a list of web elements based on the provided XPath expression.
     *
     * This method uses the WebDriver to search for multiple elements that match the given
     * XPath query. If no elements are found, a custom exception is thrown.
     *
     * @param xpath The XPath expression used to locate the desired elements.
     *              The XPath should be a valid expression used to find elements within the DOM.
     *
     * @return A list of {@link WebElement} objects that match the given XPath expression.
     *         If no elements are found, a {@link SeleniagramNoSuchElementException} is thrown.
     *
     * @throws SeleniagramNoSuchElementException If no elements are found matching the given XPath.
     *                                           This exception is a custom exception indicating the
     *                                           failure to locate any matching elements.
     */
    public List<WebElement> findElementsByXpath(String xpath) {
        List<WebElement> webElements;
        try {
            webElements = getSeleniumDriver().findElements(By.xpath(xpath));
        } catch (NoSuchElementException elementException) {
            throw new SeleniagramNoSuchElementException("Can't find Elements with XPATH: " + xpath);
        }
        return webElements;
    }

}