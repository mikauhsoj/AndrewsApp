package edu.andrews.cptr475.bernardm.andrewsapp.YelpApi;

/**
 * Created by bernardm on 4/8/2015.
 */
/*
Example code based on code from Nicholas Smith at http://imnes.blogspot.com/2011/01/how-to-use-yelp-v2-from-java-including.html
For a more complete example (how to integrate with GSON, etc) see the blog post above.
*/

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import edu.andrews.cptr475.bernardm.andrewsapp.R;

//   import us.noura.example.R;

/**
 * Example for accessing the Yelp API.
 */
public class Yelp {

    OAuthService service;
    Token accessToken;

    /**
     * Setup the Yelp API OAuth credentials.
     * <p/>
     * OAuth credentials are available from the developer site, under Manage API access (version 2 API).
     *
     * @param consumerKey    Consumer key
     * @param consumerSecret Consumer secret
     * @param token          Token
     * @param tokenSecret    Token secret
     */
    public Yelp(String consumerKey, String consumerSecret, String token, String tokenSecret) {
        this.service = new ServiceBuilder().provider(YelpApi2.class).apiKey(consumerKey).apiSecret(consumerSecret).build();
        this.accessToken = new Token(token, tokenSecret);
    }

    /**
     * Search with term and location.
     *
     * @param term      Search term
     * @param latitude  Latitude
     * @param longitude Longitude
     * @return JSON string response
     */
    public String search(String term, double latitude, double longitude) {
        OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/search");
        request.addQuerystringParameter("term", term);
        request.addQuerystringParameter("ll", latitude + "," + longitude);
        this.service.signRequest(this.accessToken, request);
        Response response = request.send();
        return response.getBody();
    }

    // CLI
    public static String VenuesSearch(String term, double latitude, double longitude) {
        // Update tokens here from Yelp developers site, Manage API access.
        String consumerKey = Integer.toString(R.string.oauth_consumer_key);
        String consumerSecret = Integer.toString(R.string.oauth_consumer_secret);
        String token = Integer.toString(R.string.oauth_token);
        String tokenSecret = Integer.toString(R.string.oauth_token_secret);

        Yelp yelp = new Yelp(consumerKey, consumerSecret, token, tokenSecret);
        String response = yelp.search(term, latitude, longitude);

        return response;
    }

    public static String otherVenuesSearch(String term, double latitude, double longitude,
                                           String consumerKey, String consumerSecret, String token, String tokenSecret) {
        Yelp yelp = new Yelp(consumerKey, consumerSecret, token, tokenSecret);
        String response = yelp.search(term, latitude, longitude);

        return response;
    }
}