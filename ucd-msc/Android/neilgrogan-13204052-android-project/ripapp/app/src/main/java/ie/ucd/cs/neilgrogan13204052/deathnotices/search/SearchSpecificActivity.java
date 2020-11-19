package ie.ucd.cs.neilgrogan13204052.deathnotices.search;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import ie.ucd.cs.neilgrogan13204052.deathnotices.R;
import ie.ucd.cs.neilgrogan13204052.deathnotices.database.NoticeORM;
import ie.ucd.cs.neilgrogan13204052.deathnotices.model.Notice;
import ie.ucd.cs.neilgrogan13204052.deathnotices.notices.DeathNoticeListActivity;

public class SearchSpecificActivity extends ActionBarActivity {

    private static final String TAG = "SearchSpecificActivity";
    public static final String SEARCH_RESULTS = "SearchResults";
    private static SearchType searchType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_search_specific);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new SearchSpecificFragment())
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
    public static class SearchSpecificFragment extends Fragment {

        public SearchSpecificFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_search_specific, container, false);

            //Intent to see type of search
            Intent intent = getActivity().getIntent();
            String searchTypePassed = intent.getStringExtra(SearchActivity.SEARCH_TYPE);
            Log.v(TAG, "Type of search is: " + searchTypePassed);
            TextView searchTextHelp = (TextView) rootView.findViewById(R.id.searchHelpText);
            if(SearchType.ADDRESS.getName().equals(searchTypePassed)){
                searchTextHelp.setText(R.string.enter_address);
                searchType = SearchType.ADDRESS;
            }else{
                searchTextHelp.setText(R.string.enter_name);
                searchType = SearchType.NAME;
            }

            addListenerOnButton(rootView);

            return rootView;
        }

        public void addListenerOnButton(View v) {

            Button searchNoticesButton = (Button) v.findViewById(R.id.dn_search_button);
            Button clearSearchButton = (Button) v.findViewById(R.id.clearButton);

            clearSearchButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    EditText searchNoticesButton = (EditText) getView().findViewById(R.id.searchQuery);
                    searchNoticesButton.setText("");

                }

            });

            searchNoticesButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    EditText searchNoticesButton = (EditText) getView().findViewById(R.id.searchQuery);
                    String searchQuery = searchNoticesButton.getText().toString().trim();

                    List<Notice> searchResults = NoticeORM.findNoticeByName(getActivity().getBaseContext(), searchQuery, searchType);

                    for(Notice n : searchResults){
                        Log.v(TAG, "Result Name: " + n.toString());
                    }

                    if(searchResults.isEmpty()){

                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage(getString(R.string.no_results));
                        builder.setCancelable(true);
                        builder.setPositiveButton(getString(R.string.ok),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                    }else{

                        Intent intent = new Intent(getActivity(), DeathNoticeListActivity.class);
                        // Create a Bundle and Put Bundle in to it
                        Bundle bundleObject = new Bundle();
                        bundleObject.putSerializable(SEARCH_RESULTS, (java.io.Serializable) searchResults);

                        // Put Bundle in to Intent and call start Activity
                        intent.putExtras(bundleObject);
                        startActivity(intent);

                    }
                }

            });

        }
    }
}
