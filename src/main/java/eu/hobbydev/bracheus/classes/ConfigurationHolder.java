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
 * Represents a configuration holder that stores user credentials and settings.
 * This class is designed to be serialized/deserialized using Jackson for XML handling.
 * The fields include the username, password, language preference, and API key, which are
 * essential for configuring a user's account and application settings.
 * <p>
 * The class implements the `ConfigurationHolder` interface, ensuring that these fields can
 * be accessed and modified through getter and setter methods.
 * <p>
 * Example use case:
 * - This class can be used to manage user-specific configuration data, such as authentication
 * credentials, language settings, and API keys. It can be loaded from or saved to an XML
 * configuration file for persistence across application sessions.
 */
public class ConfigurationHolder implements eu.hobbydev.bracheus.interfaces.ConfigurationHolder {

    @JacksonXmlProperty(localName = "username")
    private String username;

    @JacksonXmlProperty(localName = "password")
    private String password;

    @JacksonXmlProperty(localName = "lang")
    private String lang;

    @JacksonXmlProperty(localName = "apiKey")
    private String apiKey;

    /**
     * Gets the username stored in this configuration.
     *
     * @return the username
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * Gets the password stored in this configuration.
     *
     * @return the password
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * Gets the language preference stored in this configuration.
     * The language code can be a standard code such as "EN" for English.
     *
     * @return the language code (e.g., "EN")
     */
    @Override
    public String getLang() {
        return this.lang;
    }

    /**
     * Gets the API key stored in this configuration.
     *
     * @return the API key
     */
    @Override
    public String getApiKey() {
        return this.apiKey;
    }

    /**
     * Sets the username in this configuration.
     *
     * @param username the username to set
     */
    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the password in this configuration.
     *
     * @param password the password to set
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the language preference in this configuration.
     *
     * @param lang the language code to set (e.g., "EN")
     */
    @Override
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     * Sets the API key in this configuration.
     *
     * @param apiKey the API key to set
     */
    @Override
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Returns a string representation of this configuration.
     * This method masks sensitive information (password and API key) for security purposes.
     *
     * @return a string containing all configuration fields, with the password and API key masked
     */
    @Override
    public String toString() {
        return "ConfigurationHolder{" +
                "username='" + username + '\'' +
                ", password='****'" +  // Masked for security
                ", lang='" + lang + '\'' +
                ", apiKey='" + (apiKey != null ? "****" : null) + '\'' +  // Mask API key
                '}';
    }
}