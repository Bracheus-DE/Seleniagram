package eu.hobbydev.bracheus.classes;


import eu.hobbydev.bracheus.interfaces.User;

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
 * The InstaUser class implements the User interface and represents a user on Instagram.
 * It contains the user's username, number of subscribers, number of subscriptions, and number of posts.
 * Methods to retrieve and set these values are provided.
 * <p>
 * This class assumes the user has a username, and the number of subscribers, subscriptions, and posts
 * can be modified as needed.
 * </p>
 */
public class InstaUser implements User {

    private final String username;
    private int subs;
    private int subbed;
    private int posts;

    /**
     * Constructor to create an InstaUser with a given username.
     *
     * @param username the username of the user.
     */
    public InstaUser(String username){
        this.username = username;
    }

    /**
     * Gets the username of the Instagram user.
     *
     * @return the username as a String.
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * Gets the number of subscribers the user has.
     *
     * @return the number of subscribers.
     */
    @Override
    public int getSubs() {
        return this.subs;
    }

    /**
     * Gets the number of users the current user is subscribed to.
     *
     * @return the number of users the current user follows.
     */
    @Override
    public int getSubbed() {
        return this.subbed;
    }

    /**
     * Gets the number of posts made by the user.
     *
     * @return the number of posts.
     */
    @Override
    public int getPosts() {
        return this.posts;
    }

    /**
     * Sets the number of subscribers the user has.
     *
     * @param subs the new number of subscribers.
     */
    @Override
    public void setSubs(int subs) {
        this.subs = subs;
    }

    /**
     * Sets the number of users the current user is subscribed to.
     *
     * @param subbed the new number of subscriptions.
     */
    @Override
    public void setSubbed(int subbed) {
        this.subbed = subbed;
    }

    /**
     * Sets the number of posts made by the user.
     *
     * @param posts the new number of posts.
     */
    @Override
    public void setPosts(int posts) {
        this.posts = posts;
    }
}