package edu.andrews.cptr475.bernardm.andrewsapp;

/**
 * This class is used primarily for API keys and secrets to create a central repository.
 * This is useful for having one place to manage several API keys.
 *
 * @author ograycoding.wordpress.com
 */
public class API_Static_Stuff {

    private final String YELP_CONSUMER_KEY ="aQ8gZL2AzoY56SydYOTZ6Q";
    private final String YELP_CONSUMER_SECRET = "SqbynC4ZDAdhNKgptTPAJaS0jf8";
    private final String YELP_TOKEN = "GOVJo7QOG1PBbh_Ew8T_3riJrvyF0mvN";
    private final String YELP_TOKEN_SECRET = "235orswqKOeQ_co-Iosl7_5RvkY";


    public String getYelpConsumerKey(){
        return YELP_CONSUMER_KEY;
    }

    public String getYelpConsumerSecret(){
        return YELP_CONSUMER_SECRET;
    }

    public String getYelpToken(){
        return YELP_TOKEN;
    }

    public String getYelpTokenSecret(){
        return YELP_TOKEN_SECRET;
    }

}