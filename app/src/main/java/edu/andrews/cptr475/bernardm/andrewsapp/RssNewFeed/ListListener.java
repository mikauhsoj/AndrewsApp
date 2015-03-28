package edu.andrews.cptr475.bernardm.andrewsapp.RssNewFeed;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import java.util.List;

import edu.andrews.cptr475.bernardm.andrewsapp.RssNewFeed.RssItem;

/**
 * @author Shemaiah Telemaque
 * @version 0.1
 */
public class ListListener implements OnItemClickListener {
    /**
     * @param listItems List item's reference.
     * @param activity Calling activity reference.
     */
    List<RssItem> listItems;
    Activity activity;

    public ListListener(List<RssItem> aListItems, Activity anActivity) {
        listItems = aListItems;
        activity = anActivity;
    }

    /**
     * Start a browser with url from the rss item.
     */
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(listItems.get(pos).getLink()));

        activity.startActivity(i);

    }

}
