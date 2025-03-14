package eu.hobbydev.bracheus.manager;


import eu.hobbydev.bracheus.classes.InstaUser;
import eu.hobbydev.bracheus.classes.SeleniagramUser;

import java.util.ArrayList;
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
 * The {@code UserManager} class is responsible for managing both regular Instagram users and the Seleniagram user.
 * It provides methods to register, unregister, and query information about known and followed Instagram users.
 * The class also allows access to the Seleniagram user.
 * <p>
 * This class manages two distinct lists of Instagram users: one for known users and one for followed users.
 * It also provides functionality to register and unregister users in these lists, as well as check if a user is known or followed.
 * </p>
 *
 * @see InstaUser
 * @see SeleniagramUser
 */
public class UserManager {

    private SeleniagramUser seleniagramUser;
    private final List<InstaUser> knownInstaUsers = new ArrayList<>();
    private final List<InstaUser> followedInstaUsers = new ArrayList<>();

    /**
     * Gets the Seleniagram user managed by this {@code UserManager}.
     *
     * @return The {@link SeleniagramUser} instance associated with this {@code UserManager}.
     */
    public SeleniagramUser getSeleniagramUser() {
        return seleniagramUser;
    }

    /**
     * Retrieves a followed Instagram user by their username.
     *
     * @param user The username of the Instagram user to retrieve.
     * @return The {@link InstaUser} corresponding to the username, or {@code null} if no followed user is found.
     */
    public InstaUser getFollowedInstaUser(String user) {
        for (InstaUser searched : followedInstaUsers) {
            if (searched.getUsername().equals(user)) {
                return searched;
            }
        }
        return null;
    }

    /**
     * Retrieves a known Instagram user by their username.
     *
     * @param user The username of the Instagram user to retrieve.
     * @return The {@link InstaUser} corresponding to the username, or {@code null} if no known user is found.
     */
    public InstaUser getKnownInstaUser(String user) {
        for (InstaUser searched : knownInstaUsers) {
            if (searched.getUsername().equals(user)) {
                return searched;
            }
        }
        return null;
    }

    /**
     * Checks if a given Instagram user is known by this {@code UserManager}.
     *
     * @param user The {@link InstaUser} to check.
     * @return {@code true} if the user is known, otherwise {@code false}.
     */
    public boolean isKnownInstaUser(InstaUser user) {
        return knownInstaUsers.contains(user);
    }

    /**
     * Checks if a given Instagram user, identified by their username, is known by this {@code UserManager}.
     *
     * @param user The username of the Instagram user to check.
     * @return {@code true} if the user is known, otherwise {@code false}.
     */
    public boolean isKnownInstaUser(String user){
        for(InstaUser instaUser : knownInstaUsers){
            if(instaUser.getUsername().equals(user)){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a given Instagram user is followed by this {@code UserManager}.
     *
     * @param user The {@link InstaUser} to check.
     * @return {@code true} if the user is followed, otherwise {@code false}.
     */
    public boolean isFollowedInstaUser(InstaUser user) {
        return followedInstaUsers.contains(user);
    }

    /**
     * Checks if a given Instagram user, identified by their username, is followed by this {@code UserManager}.
     *
     * @param user The username of the Instagram user to check.
     * @return {@code true} if the user is followed, otherwise {@code false}.
     */
    public boolean isFollowedInstaUser(String user){
        for(InstaUser instaUser : followedInstaUsers){
            if(instaUser.getUsername().equals(user)){
                return true;
            }
        }
        return false;
    }

    /**
     * Registers an Instagram user as a known user.
     *
     * @param user The {@link InstaUser} to register as a known user.
     */
    public void registerInstaUser(InstaUser user) {
        knownInstaUsers.add(user);
    }

    /**
     * Unregisters an Instagram user from the list of known users.
     *
     * @param user The {@link InstaUser} to unregister from the known users list.
     */
    public void unregisterInstaUser(InstaUser user) {
        knownInstaUsers.remove(user);
    }

    /**
     * Registers an Instagram user as a followed user.
     *
     * @param user The {@link InstaUser} to register as a followed user.
     */
    public void registerFollowedInstaUser(InstaUser user) {
        followedInstaUsers.add(user);
    }

    /**
     * Unregisters an Instagram user from the list of followed users.
     *
     * @param user The {@link InstaUser} to unregister from the followed users list.
     */
    public void unregisterFollowedInstaUser(InstaUser user) {
        followedInstaUsers.remove(user);
    }

    /**
     * Sets the Seleniagram user for this {@code UserManager}.
     *
     * @param seleniagramUser The {@link SeleniagramUser} to set as the managed user.
     */
    public void setSeleniagramUser(SeleniagramUser seleniagramUser) {
        this.seleniagramUser = seleniagramUser;
    }
}