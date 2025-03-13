package eu.hobbydev.bracheus.interfaces;

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
 * The `ConfigurationHolder` interface is designed to provide a contract for holding and managing configuration
 * data related to an application. It defines methods for retrieving and setting configuration values such as the
 * username, password, language, and API key. Implementations of this interface are expected to provide access to
 * these settings, enabling their use across the application.
 *
 * The interface includes getter and setter methods for each configuration item, ensuring that the values can be
 * easily accessed and updated. The `toString` method allows for a textual representation of the configuration
 * information.
 *
 * Implementing classes should provide the actual functionality for retrieving and storing the configuration data,
 * either from a file, a database, or some other source.
 */
public interface ConfigurationHolder {

    /**
     * Retrieves the username stored in the configuration.
     *
     * @return the username.
     */
    String getUsername();

    /**
     * Retrieves the password stored in the configuration.
     *
     * @return the password.
     */
    String getPassword();

    /**
     * Retrieves the language setting from the configuration.
     *
     * @return the language code (e.g., "en", "de").
     */
    String getLang();

    /**
     * Retrieves the API key stored in the configuration.
     *
     * @return the API key.
     */
    String getApiKey();

    /**
     * Sets the username in the configuration.
     *
     * @param username the username to set.
     */
    void setUsername(String username);

    /**
     * Sets the password in the configuration.
     *
     * @param password the password to set.
     */
    void setPassword(String password);

    /**
     * Sets the language in the configuration.
     *
     * @param lang the language code (e.g., "en", "de") to set.
     */
    void setLang(String lang);

    /**
     * Sets the API key in the configuration.
     *
     * @param apiKey the API key to set.
     */
    void setApiKey(String apiKey);

    /**
     * Returns a string representation of the configuration object, typically
     * containing the current settings such as the username, password, language,
     * and API key. The specific format is implementation-dependent.
     *
     * @return a string representation of the configuration.
     */
    @Override
    String toString();
}
