package eu.hobbydev.bracheus;


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import eu.hobbydev.bracheus.actions.LoginAction;
import eu.hobbydev.bracheus.actions.StoppingSeleniagramAction;
import eu.hobbydev.bracheus.classes.LanguageHolder;
import eu.hobbydev.bracheus.interfaces.ConfigurationHolder;
import eu.hobbydev.bracheus.listeners.DMListener;
import eu.hobbydev.bracheus.manager.ActionThreadManager;
import eu.hobbydev.bracheus.manager.ListenerThreadManager;
import eu.hobbydev.bracheus.manager.OpenAIManager;
import eu.hobbydev.bracheus.manager.SeleniumManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Thread.sleep;

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
 * The `Seleniagram` class serves as the entry point of the application.
 * It initializes the `SeleniumManager` and starts its execution.
 * This class is responsible for setting up the necessary components and managing the Selenium tasks.
 * It also handles configuration loading and creation, language settings, and the execution of various actions.
 */
public class Seleniagram {

    public static ActionThreadManager actionThreadManager;
    public static ListenerThreadManager listenerThreadManager;
    public static ConfigurationHolder configurationHolder;
    public static LanguageHolder languageHolder;
    public static OpenAIManager openAIManager;
    private static String jarPath;
    static boolean running = true;

    /**
     * The main method serves as the entry point to the application.
     * It sets up the necessary configuration, creates instances of required managers,
     * and initiates the main threads for action and listener management.
     * It also handles user input for controlling the execution of the program.
     *
     * @param args Command-line arguments (not used in this implementation).
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        Logger logger = LoggerFactory.getLogger(Seleniagram.class);
        boolean config = false;
        try {
            config = checkConfig();
        } catch (URISyntaxException e) {
            logger.error("Cannot find config path!");
        }
        if (!config) {
            eu.hobbydev.bracheus.classes.ConfigurationHolder newConfig = new eu.hobbydev.bracheus.classes.ConfigurationHolder();
            newConfig.setApiKey("apiKey");
            newConfig.setLang("EN");
            newConfig.setUsername("username");
            newConfig.setPassword("password");
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.writeValue(new File(jarPath, "config.xml"), newConfig);
            logger.info("Config created. Shutdown!");
            return;
        }
        XmlMapper xmlMapper = new XmlMapper();
        configurationHolder = xmlMapper.readValue(new File(jarPath, "config.xml"), eu.hobbydev.bracheus.classes.ConfigurationHolder.class);
        logger.info("Configuration loaded:");
        logger.info(configurationHolder.toString());

        if (!checkLanguageHolder(configurationHolder.getLang())) {
            languageHolder = new LanguageHolder();
            languageHolder.setUsernameField("username");
            languageHolder.setPasswordField("password");
            languageHolder.setLoginField("Log in");
            languageHolder.setAllowAllCookiesButton("Allow all cookies");
            languageHolder.setSearchSpan("Search");
            languageHolder.setProfileSpan("Profile");
            languageHolder.setDirectMessagingAria("Direct messaging");
            languageHolder.setWrongPassword("Sorry, your password was incorrect. Please double-check your password.");
            languageHolder.setUnreadMessage("Unread");
            languageHolder.setConversationWithAria("Conversation with");
            languageHolder.setMessagePlaceholder("Message...");
            languageHolder.setSendText("Send");
            xmlMapper = new XmlMapper();
            xmlMapper.writeValue(new File(jarPath, configurationHolder.getLang() + ".xml"), languageHolder);
            logger.info("Language config created! This is used to store Instagram labels.");
            return;
        }
        xmlMapper = new XmlMapper();
        languageHolder = xmlMapper.readValue(new File(jarPath, configurationHolder.getLang() + ".xml"), LanguageHolder.class);

        openAIManager = new OpenAIManager(configurationHolder.getApiKey());
        SeleniumManager seleniumManager = new SeleniumManager();
        seleniumManager.start();
        configurationHolder = new eu.hobbydev.bracheus.classes.ConfigurationHolder();
        actionThreadManager = new ActionThreadManager(seleniumManager);
        actionThreadManager.setName("ActionThreadManager");
        actionThreadManager.start();
        listenerThreadManager = new ListenerThreadManager(seleniumManager);
        listenerThreadManager.setName("ListenerThreadManager");
        listenerThreadManager.start();
        sleep(10000);
        actionThreadManager.registerActions(new LoginAction());
        listenerThreadManager.addListener(new DMListener());
        Scanner scanner = new Scanner(System.in);
        while (running) {
            String input = scanner.next();
            if (input.equals("stop")) {
                running = false;
            }
        }
        actionThreadManager.registerActions(new StoppingSeleniagramAction());
        while (actionThreadManager.isAlive()) {
            sleep(1000);
        }
        seleniumManager.stop();
        logger.info("Bye!");
    }

    /**
     * Checks whether the configuration file exists.
     *
     * @return `true` if the configuration file exists, otherwise `false`.
     */
    private static boolean checkConfig() throws URISyntaxException {
        String path = Seleniagram.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        File jarFile = new File(path);
        jarPath = jarFile.getParent();

        File file = new File(jarPath, "config.xml");
        return file.exists();
    }

    /**
     * Checks whether the language configuration file for the specified language exists.
     *
     * @param lang The language code (e.g., "EN", "DE") to check for the corresponding language file.
     * @return `true` if the language file exists, otherwise `false`.
     */
    private static boolean checkLanguageHolder(String lang) {
        File file = new File(jarPath, lang + ".xml");
        return file.exists();
    }
}