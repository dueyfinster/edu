package ie.ucd.cs.neilgrogan13204052.deathnotices.notices;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ie.ucd.cs.neilgrogan13204052.deathnotices.R;

import ie.ucd.cs.neilgrogan13204052.deathnotices.database.NoticeORM;
import ie.ucd.cs.neilgrogan13204052.deathnotices.maps.MapsActivity;
import ie.ucd.cs.neilgrogan13204052.deathnotices.model.Notice;

/**
 * A fragment representing a single DeathNotice detail screen.
 * This fragment is either contained in a {@link DeathNoticeListActivity}
 * in two-pane mode (on tablets) or a {@link DeathNoticeDetailActivity}
 * on handsets.
 */
public class DeathNoticeDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item id that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    public static final String SHOW_ON_MAP = "ShowOnMap";
    private final String URL_BASE = "http://rip.ie/showdn.php?dn=";

    /**
     * The dummy content this fragment is presenting.
     */
    private Notice mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DeathNoticeDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            String s =  (String) getArguments().get(ARG_ITEM_ID);
            long id = Long.valueOf(s);
            mItem = NoticeORM.findNoticeById(getActivity().getBaseContext(),id);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_deathnotice_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {

            DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getActivity().getBaseContext());
            String date_death = dateFormat.format(mItem.getDate_of_death());
            String date_published = dateFormat.format(mItem.getDate_published());

            getActivity().setTitle(mItem.getFirst_name() + " " + mItem.getLast_name());

            ((TextView) rootView.findViewById(R.id.dateDeath)).setText(date_death);
            ((TextView) rootView.findViewById(R.id.datePosted)).setText(date_published);
            ((TextView) rootView.findViewById(R.id.address)).setText(mItem.getAddress());
            ((TextView) rootView.findViewById(R.id.deathnotice_detail)).setText(mItem.getText());

        }

        addListenerOnButton(rootView);

        return rootView;
    }

    public void addListenerOnButton(View v) {

        Button viewOnMapButton = (Button) v.findViewById(R.id.viewNoticeOnMapButton);

        viewOnMapButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                List<Notice> notices = new ArrayList<Notice>();
                notices.add(mItem);

                Intent intent = new Intent(getActivity(), MapsActivity.class);
                // Create a Bundle and Put Bundle in to it
                Bundle bundleObject = new Bundle();
                bundleObject.putSerializable(SHOW_ON_MAP, (java.io.Serializable) notices);

                // Put Bundle in to Intent and call start Activity
                intent.putExtras(bundleObject);
                startActivity(intent);

            }

        });


        Button addCalendarButton = (Button) v.findViewById(R.id.addDateDeathToCalButton);

        addCalendarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String calendarEventTitle = getString(R.string.death_of) + " " + mItem.getFirst_name() + " " + mItem.getLast_name();
                long date = mItem.getDate_of_death().getTime();

                if (Build.VERSION.SDK_INT >= 14) {
                    Intent intent = new Intent(Intent.ACTION_INSERT)
                            .setData(CalendarContract.Events.CONTENT_URI)
                            .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, date)
                            .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, date)
                            .putExtra(CalendarContract.Events.TITLE, calendarEventTitle)
                            .putExtra(CalendarContract.Events.DESCRIPTION, mItem.getText())
                            .putExtra(CalendarContract.Events.EVENT_LOCATION, mItem.getAddress())
                            .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
                    startActivity(intent);
                }

                else {
                    //Calendar cal = Calendar.getInstance();
                    Intent intent = new Intent(Intent.ACTION_EDIT);
                    intent.setType("vnd.android.cursor.item/event");
                    intent.putExtra("beginTime", date);
                    intent.putExtra("allDay", true);
                    intent.putExtra("endTime", date);
                    intent.putExtra("title", calendarEventTitle);
                    startActivity(intent);
                }

            }

        });


        Button shareNoticeButton = (Button) v.findViewById(R.id.shareNoticeButton);

        shareNoticeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(Intent.ACTION_SEND);

                intent.setType("text/plain");

                String dnTitle = getString(R.string.death_of) + " " + mItem.getFirst_name() + " " + mItem.getLast_name();

                intent.putExtra(Intent.EXTRA_TEXT, dnTitle + " - " + URL_BASE + mItem.getId());

                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, dnTitle);

                startActivity(Intent.createChooser(intent, "Share"));

            }

        });
    }
}
