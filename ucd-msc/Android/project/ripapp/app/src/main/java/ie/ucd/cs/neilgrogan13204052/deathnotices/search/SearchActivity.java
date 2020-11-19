package ie.ucd.cs.neilgrogan13204052.deathnotices.search;

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

public class SearchActivity extends ActionBarActivity {

    public static String SEARCH_TYPE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new SearchFragment())
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
    public static class SearchFragment extends Fragment{

        private static final String TAG = "SearchFragment";

        public SearchFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_search, container, false);
            addListenerOnButton(rootView);
            return rootView;
        }

        public void addListenerOnButton(View v) {

            ImageButton searchNoticesButton = (ImageButton) v.findViewById(R.id.searchNameButton);

            searchNoticesButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    Intent intent = new Intent(getActivity(), SearchSpecificActivity.class);
                    intent.putExtra(SEARCH_TYPE, SearchType.NAME.getName());
                    startActivity(intent);

                }

            });


            ImageButton searchAddressButton = (ImageButton) v.findViewById(R.id.searchAddressButton);

            searchAddressButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    Intent intent = new Intent(getActivity(), SearchSpecificActivity.class);
                    intent.putExtra(SEARCH_TYPE, SearchType.ADDRESS.getName());
                    startActivity(intent);

                }

            });

        }


    }
}
