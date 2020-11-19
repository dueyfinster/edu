package ie.ucd.cs.neilgrogan13204052.deathnotices.notices;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import ie.ucd.cs.neilgrogan13204052.deathnotices.R;
import ie.ucd.cs.neilgrogan13204052.deathnotices.database.NoticeORM;
import ie.ucd.cs.neilgrogan13204052.deathnotices.model.Notice;

/**
 * An activity representing a list of Death Notices. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link DeathNoticeDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link DeathNoticeListFragment} and the item details
 * (if present) is a {@link DeathNoticeDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link DeathNoticeListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class DeathNoticeListActivity extends FragmentActivity
        implements DeathNoticeListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private List<Notice> notices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deathnotice_list);

        if (findViewById(R.id.deathnotice_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((DeathNoticeListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.deathnotice_list))
                    .setActivateOnItemClick(true);
        }
    }

    /**
     * Callback method from {@link DeathNoticeListFragment.Callbacks}
     * indicating that the item with the given id was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(DeathNoticeDetailFragment.ARG_ITEM_ID, id);
            DeathNoticeDetailFragment fragment = new DeathNoticeDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.deathnotice_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item id.
            Intent detailIntent = new Intent(this, DeathNoticeDetailActivity.class);
            detailIntent.putExtra(DeathNoticeDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }



}
