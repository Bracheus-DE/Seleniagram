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
 * This interface defines methods for user-related information,
 * including username, the number of subscribers, the number of subscriptions, and the number of posts.
 * It also includes methods for setting these properties and retrieving the user's URL.
 * Implementing classes should provide the actual behavior for these methods.
 */
public interface User {

   /**
    * Gets the username of the user.
    *
    * @return the username as a String.
    */
   String getUsername();

   /**
    * Gets the number of subscribers the user has.
    *
    * @return the number of subscribers.
    */
   int getSubs();

   /**
    * Gets the number of users the current user is subscribed to.
    *
    * @return the number of users the current user follows.
    */
   int getSubbed();

   /**
    * Gets the number of posts made by the user.
    *
    * @return the number of posts.
    */
   int getPosts();

   /**
    * Gets the URL associated with the user's profile.
    *
    * @return the user's profile URL as a String.
    */
   default String getUserURL(){
      return "https://instagram.com/" + getUsername();
   }

   /**
    * Sets the number of subscribers the user has.
    * This method should update the subscriber count accordingly.
    */
   void setSubs(int subs);

   /**
    * Sets the number of users the current user is subscribed to.
    * This method should update the subscription count accordingly.
    */
   void setSubbed(int subbed);

   /**
    * Sets the number of posts made by the user.
    * This method should update the post count accordingly.
    */
   void setPosts(int posts);
}
