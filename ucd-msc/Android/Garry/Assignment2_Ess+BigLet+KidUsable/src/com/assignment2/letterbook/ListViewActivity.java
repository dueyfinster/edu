/**
 * COMP30510 - Mobile Application Development (Android) Online 
 * Student: Garry Davitt 
 * Student ID:13205364 
 * Course: M.Sc Computer Science (Software Eng.Stream) 
 * Lecturer: Dr. Abraham Campbell
 */

package com.assignment2.letterbook;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ListViewActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.app_name);

		ListView listView;
		CustomAdapter adapter;

		final int[] fruitImages = { R.drawable.apple, R.drawable.banana,
				R.drawable.orange, R.drawable.pear };

		final String[] fruitNames = getResources().getStringArray(
				R.array.fruit_names);

		listView = getListView();
		adapter = new CustomAdapter(this, fruitNames, fruitImages);
		listView.setAdapter(adapter);

		//listener on item click
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent intent = new Intent(getApplicationContext(),
						SingleListItem.class);
				// sending data to new activity
				intent.putExtra("fruitIndex", position);
				intent.putExtra("allFruitNames", fruitNames);
				intent.putExtra("allFruitImages", fruitImages);
				startActivity(intent);
			}
		});
	}

}
