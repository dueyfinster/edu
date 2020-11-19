package ie.ucd.cs.neilgrogan13204052.deathnotices;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import ie.ucd.cs.neilgrogan13204052.deathnotices.R;
import ie.ucd.cs.neilgrogan13204052.deathnotices.maps.MapsActivity;
import ie.ucd.cs.neilgrogan13204052.deathnotices.notices.DeathNoticeListActivity;
import ie.ucd.cs.neilgrogan13204052.deathnotices.search.SearchActivity;

public class DeathNotices extends ActionBarActivity {
    public static final String SERVER_URL = "http://10.0.1.18:8000/death_notices.json";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_death_notices);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_death_notices, container, false);
            addListenerOnButton(rootView);
            return rootView;

        }

        public void addListenerOnButton(View v) {

            ImageButton viewNoticesListButton = (ImageButton) v.findViewById(R.id.viewNoticesListButton);

            viewNoticesListButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    Intent intent = new Intent(getActivity(), DeathNoticeListActivity.class);
                    startActivity(intent);

                }

            });


            ImageButton viewNoticesMapButton = (ImageButton) v.findViewById(R.id.viewNoticesMapButton);

            viewNoticesMapButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    Intent intent = new Intent(getActivity(), MapsActivity.class);
                    startActivity(intent);

                }

            });


            ImageButton searchNoticesButton = (ImageButton) v.findViewById(R.id.searchNoticesButton);

            searchNoticesButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    Intent intent = new Intent(getActivity(), SearchActivity.class);
                    startActivity(intent);

                }

            });

        }
    }
}
