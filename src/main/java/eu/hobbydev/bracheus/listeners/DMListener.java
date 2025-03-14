package eu.hobbydev.bracheus.listeners;


import eu.hobbydev.bracheus.Seleniagram;
import eu.hobbydev.bracheus.classes.LanguageHolder;
import eu.hobbydev.bracheus.exceptions.SeleniagramNoSuchElementException;
import eu.hobbydev.bracheus.interfaces.Listener;
import eu.hobbydev.bracheus.manager.SeleniumManager;
import eu.hobbydev.bracheus.utils.HumanizerTools;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * The `DMListener` class is responsible for listening to and interacting with direct messages (DMs) on Instagram.
 * It continuously checks for new messages and responds automatically using the OpenAI-powered response generation.
 * This class is designed to work with Selenium to automate the process of opening chats, reading unread messages,
 * and sending replies. It also supports a blacklist mechanism to ensure that repeated or spammy messages are avoided.
 * <p>
 * The `DMListener` implements the `Listener` interface to run checks and the `HumanizerTools` interface for handling
 * human-like message behaviors. It uses Selenium to interact with the Instagram Direct Message interface to fetch unread
 * messages, process them, and send automated responses based on the content of the conversation.
 * <p>
 * This class is also responsible for managing interactions with the blacklist, ensuring that certain users' messages
 * are not answered if previously handled.
 */
public class DMListener implements Listener, HumanizerTools {

    private static final Logger logger = LoggerFactory.getLogger(DMListener.class);

    private Map<String, List<String>> blacklist = new HashMap<>();

    /**
     * Runs the periodic checkup to verify new messages and respond accordingly.
     * This method performs checks for unread messages and, if any are found, triggers the `answerMessages()` method.
     */
    @Override
    public void runCheckup() {
        siteDelay(); // Introduces delay between site actions
        int messages = checkMessageCount(); // Fetches count of unread messages
        if (messages > 0) {
            answerMessages(); // Answers unread messages
        }
    }

    /**
     * Opens Instagram's Direct Message page and processes unread chats by clicking and reading them.
     * For each unread chat, a response is generated and sent.
     */
    private void answerMessages() {
        getSeleniumManager().open("https://www.instagram.com/direct/inbox/");
        siteDelay();
        List<WebElement> chats = getSeleniumManager().findElementsByCss("div[role='button']");

        for (WebElement chat : chats) {
            WebElement unreadBadge = null;
            try {
                unreadBadge = getSeleniumManager().findByXpath(".//div[contains(text(), '" + getLanguageHolder().getUnreadMessage() + "')]");
            } catch (SeleniagramNoSuchElementException e) {
                logger.info("This chat isn't marked as unread!");
            }

            if (unreadBadge != null) {
                unreadBadge = (WebElement) getSeleniumManager().getJavaScJavascriptExecutor().executeScript("return arguments[0].parentNode;", unreadBadge);
                unreadBadge.click();
                logger.info("Open unread chat!");
                siteDelay();

                try {
                    writeMessage(); // Writes and sends a response
                } catch (SeleniagramNoSuchElementException e) {
                    logger.error("Error writing message: " + e);
                }
            }
        }
    }

    /**
     * Retrieves the last 3 messages from the current chat, processes them, and sends a generated response.
     * If the user is blacklisted, their messages will be skipped.
     * The response is typed character by character in a human-like manner.
     *
     * @throws SeleniagramNoSuchElementException if any required elements are not found while interacting with the page.
     */
    private void writeMessage() throws SeleniagramNoSuchElementException {
        getSeleniumManager().reloadPage();
        siteDelay();
        WebElement chatElement = getSeleniumManager().findByXpath("//div[contains(@aria-label, '" + getLanguageHolder().getConversationWithAria() + "')]");
        String conversationWith = chatElement.getAttribute("aria-label").replace(getLanguageHolder().getConversationWithAria() + " ", "");
        logger.info("You are writing with {}.", conversationWith);

        List<WebElement> messages = getSeleniumManager().findElementsByCss(".html-div.xexx8yu.x4uap5.x18d9i69.xkhd6sd.x1gslohp.x11i5rnm.x12nagc.x1mh8g0r.x1yc453h.x126k92a.x18lvrbx");
        List<String> chat = new ArrayList<>();

        if (messages.size() > 3) {
            for (int i = 0; i < 3; i++) {
                chat.add(messages.get(messages.size() - (3 - i)).getText());
            }
        } else {
            for (WebElement message : messages) {
                chat.add(message.getText());
            }
        }

        handleBlacklist(chat, conversationWith); // Avoid answering blacklisted users
        String response = Seleniagram.openAIManager.answerMessages(chat); // Get AI-generated response

        WebElement input = getSeleniumManager().findByXpath("//div[@aria-placeholder='" + getLanguageHolder().getMessagePlaceholder() + "']");
        input.click();
        inputDelay();

        for (char c : response.toCharArray()) {
            input.sendKeys(String.valueOf(c)); // Simulate typing message
        }

        WebElement send = getSeleniumManager().findDivByText(getLanguageHolder().getSendText());
        send.click(); // Send the message
        siteDelay();

        getSeleniumManager().open("https://instagram.com/"); // Navigate back to Instagram home page
        siteDelay();
    }

    /**
     * Manages the blacklist by tracking the conversations with users.
     * If a user has already been blacklisted, new messages from that user will be checked
     * to ensure that no duplicate messages are processed.
     *
     * @param chat the list of messages from the current conversation.
     * @param user the username of the person the conversation is with.
     */
    private void handleBlacklist(List<String> chat, String user) {
        if (!this.blacklist.containsKey(user)) {
            blacklist.put(user, chat);
            logger.info("All messages passed the blacklist check.");
            return;
        }

        List<String> oldList = blacklist.get(user);
        List<String> newBlacklist = new ArrayList<>(chat);
        chat.removeIf(oldList::contains); // Remove messages from old list
        this.blacklist.remove(user);
        this.blacklist.put(user, newBlacklist);
    }

    /**
     * Checks and returns the number of new messages available in the Instagram Direct inbox.
     *
     * @return the number of new unread messages.
     */
    private int checkMessageCount() {
        if (!getSeleniumManager().getUrl().equals("https://instagram.com/")) {
            getSeleniumManager().open("https://instagram.com/");
            siteDelay();
        }
        WebElement directMessageLink = getSeleniumManager().findByXpath("//a[contains(@aria-label, '" + getLanguageHolder().getDirectMessagingAria() + "')]");
        String ariaLabelText = directMessageLink.getAttribute("aria-label");
        String messageCount = ariaLabelText.replaceAll("\\D+", "");
        logger.info("You have {} new Messages!", messageCount);
        return Integer.parseInt(messageCount);
    }

    /**
     * Returns the name of the listener, which is used for logging and identification purposes.
     *
     * @return the name of the listener, "DMListener".
     */
    @Override
    public String getName() {
        return "DMListener";
    }

    /**
     * Gets the SeleniumManager instance used for interacting with the Instagram website.
     *
     * @return the SeleniumManager instance.
     */
    private SeleniumManager getSeleniumManager() {
        return Seleniagram.listenerThreadManager.getSeleniumManager();
    }

    /**
     * Gets the LanguageHolder instance used for managing localized strings.
     *
     * @return the LanguageHolder instance.
     */
    private LanguageHolder getLanguageHolder() {
        return Seleniagram.languageHolder;
    }
}