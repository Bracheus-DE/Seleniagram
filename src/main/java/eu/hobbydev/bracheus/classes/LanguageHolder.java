package eu.hobbydev.bracheus.classes;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

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
 * The `LanguageHolder` class is responsible for storing and providing language-specific keys
 * that are used in the Instagram interface. The keys are dynamic and depend on the user's selected
 * language, allowing the application to adjust to different languages for Instagram interactions.
 *
 * The class uses annotations to bind the values of each key to XML properties, which are read during
 * the initialization process to configure the application. Each field corresponds to a specific
 * component of the Instagram UI (e.g., login fields, direct message labels, buttons, etc.) and is
 * localized based on the language settings.
 *
 * This class is essential for enabling multi-language support in the application, making it easier
 * to interact with Instagram in various languages. It abstracts the language-specific elements,
 * so users can easily select and use their preferred language.
 *
 * Example use case:
 * - The `LanguageHolder` can be used to retrieve language-specific labels like the "Send" button
 *   text, "Username" field label, "Password" field label, etc., based on the current language configuration.
 */
public class LanguageHolder {

    @JacksonXmlProperty(localName = "usernameField")
    private String usernameField;

    @JacksonXmlProperty(localName = "passwordField")
    private String passwordField;

    @JacksonXmlProperty(localName = "wrongPassword")
    private String wrongPassword;

    @JacksonXmlProperty(localName = "loginField")
    private String loginField;

    @JacksonXmlProperty(localName = "allowAllCookiesButton")
    private String allowAllCookiesButton;

    @JacksonXmlProperty(localName = "profileSpan")
    private String profileSpan;

    @JacksonXmlProperty(localName = "searchSpan")
    private String searchSpan;

    @JacksonXmlProperty(localName = "directMessageAria")
    private String directMessagingAria;

    @JacksonXmlProperty(localName = "unreadMessageText")
    private String unreadMessage;

    @JacksonXmlProperty(localName = "conversationWithAria")
    private String conversationWithAria;

    @JacksonXmlProperty(localName = "messagePlaceholder")
    private String messagePlaceholder;

    @JacksonXmlProperty(localName = "textSend")
    private String sendText;

    /**
     * Retrieves the label text for the "Send" button in the selected language.
     *
     * @return the localized "Send" button text.
     */
    public String getSendText() {
        return sendText;
    }

    /**
     * Retrieves the placeholder text for the message input field in the selected language.
     *
     * @return the localized message input field placeholder.
     */
    public String getMessagePlaceholder() {
        return messagePlaceholder;
    }

    /**
     * Retrieves the label for the "Conversation With" aria element in the selected language.
     *
     * @return the localized text for "Conversation With" aria element.
     */
    public String getConversationWithAria() {
        return conversationWithAria;
    }

    /**
     * Retrieves the text for the "Allow All Cookies" button in the selected language.
     *
     * @return the localized "Allow All Cookies" button text.
     */
    public String getAllowAllCookiesButton() {
        return allowAllCookiesButton;
    }

    /**
     * Retrieves the aria-label for direct messaging in the selected language.
     *
     * @return the localized aria-label for direct messaging.
     */
    public String getDirectMessagingAria() {
        return directMessagingAria;
    }

    /**
     * Retrieves the text for unread message indicator in the selected language.
     *
     * @return the localized unread message indicator text.
     */
    public String getUnreadMessage() {
        return unreadMessage;
    }

    /**
     * Retrieves the text for the "Wrong Password" message in the selected language.
     *
     * @return the localized "Wrong Password" message.
     */
    public String getWrongPassword() {
        return wrongPassword;
    }

    /**
     * Retrieves the label for the "Login" field in the selected language.
     *
     * @return the localized "Login" field text.
     */
    public String getLoginField() {
        return loginField;
    }

    /**
     * Retrieves the label for the "Password" field in the selected language.
     *
     * @return the localized "Password" field text.
     */
    public String getPasswordField() {
        return passwordField;
    }

    /**
     * Retrieves the text for the profile section in the selected language.
     *
     * @return the localized text for the profile section.
     */
    public String getProfileSpan() {
        return profileSpan;
    }

    /**
     * Retrieves the text for the search section in the selected language.
     *
     * @return the localized text for the search section.
     */
    public String getSearchSpan() {
        return searchSpan;
    }

    /**
     * Retrieves the label for the "Username" field in the selected language.
     *
     * @return the localized "Username" field text.
     */
    public String getUsernameField() {
        return usernameField;
    }

    /**
     * Sets the localized text for the "Send" button.
     *
     * @param sendText the localized text for the "Send" button.
     */
    public void setSendText(String sendText) {
        this.sendText = sendText;
    }

    /**
     * Sets the localized placeholder text for the message input field.
     *
     * @param messagePlaceholder the localized message input field placeholder.
     */
    public void setMessagePlaceholder(String messagePlaceholder) {
        this.messagePlaceholder = messagePlaceholder;
    }

    /**
     * Sets the localized text for the "Wrong Password" message.
     *
     * @param wrongPassword the localized "Wrong Password" message.
     */
    public void setWrongPassword(String wrongPassword) {
        this.wrongPassword = wrongPassword;
    }

    /**
     * Sets the localized text for the "Conversation With" aria element.
     *
     * @param conversationWithAria the localized text for the "Conversation With" aria element.
     */
    public void setConversationWithAria(String conversationWithAria) {
        this.conversationWithAria = conversationWithAria;
    }

    /**
     * Sets the localized text for the unread message indicator.
     *
     * @param unreadMessage the localized unread message indicator text.
     */
    public void setUnreadMessage(String unreadMessage) {
        this.unreadMessage = unreadMessage;
    }

    /**
     * Sets the localized text for the "Allow All Cookies" button.
     *
     * @param allowAllCookiesButton the localized "Allow All Cookies" button text.
     */
    public void setAllowAllCookiesButton(String allowAllCookiesButton) {
        this.allowAllCookiesButton = allowAllCookiesButton;
    }

    /**
     * Sets the localized aria-label for direct messaging.
     *
     * @param directMessagingAria the localized aria-label for direct messaging.
     */
    public void setDirectMessagingAria(String directMessagingAria) {
        this.directMessagingAria = directMessagingAria;
    }

    /**
     * Sets the localized label for the "Login" field.
     *
     * @param loginField the localized "Login" field text.
     */
    public void setLoginField(String loginField) {
        this.loginField = loginField;
    }

    /**
     * Sets the localized label for the "Password" field.
     *
     * @param passwordField the localized "Password" field text.
     */
    public void setPasswordField(String passwordField) {
        this.passwordField = passwordField;
    }

    /**
     * Sets the localized text for the profile section.
     *
     * @param profileSpan the localized text for the profile section.
     */
    public void setProfileSpan(String profileSpan) {
        this.profileSpan = profileSpan;
    }

    /**
     * Sets the localized text for the search section.
     *
     * @param searchSpan the localized text for the search section.
     */
    public void setSearchSpan(String searchSpan) {
        this.searchSpan = searchSpan;
    }

    /**
     * Sets the localized label for the "Username" field.
     *
     * @param usernameField the localized "Username" field text.
     */
    public void setUsernameField(String usernameField) {
        this.usernameField = usernameField;
    }
}