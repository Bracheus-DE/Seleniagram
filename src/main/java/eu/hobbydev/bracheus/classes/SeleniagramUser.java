package eu.hobbydev.bracheus.classes;


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
 * The SeleniagramUser class extends the InstaUser class and represents a user on Instagram with an additional password attribute.
 * This class adds functionality to handle and retrieve the user's password.
 * It inherits all methods and properties from the InstaUser class, including the username, number of subscribers,
 * subscriptions, and posts.
 */
public class SeleniagramUser extends InstaUser {

    private final String password;

    /**
     * Constructor to create a SeleniagramUser with a given username and password.
     *
     * @param username the username of the user.
     * @param password the password of the user.
     */
    public SeleniagramUser(String username, String password) {
        super(username);
        this.password = password;
    }

    /**
     * Gets the password of the Seleniagram user.
     *
     * @return the password as a String.
     */
    public String getPassword() {
        return password;
    }
}

