/**
 * COMP30510 - Mobile Application Development (Android) Online 
 * Student: Garry Davitt 
 * Student ID:13205364 
 * Course: M.Sc Computer Science (Software Eng.Stream) 
 * Lecturer: Dr. Abraham Campbell
 */

package com.assignment2.letterbook;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
	public String fruitLabel[];
	public int fruitImage[];

	public Activity context;
	public LayoutInflater inflater;

	public CustomAdapter(Activity context, String[] fruitLabels,
			int[] fruitImages) {
		super();

		this.context = context;
		this.fruitLabel = fruitLabels;
		this.fruitImage = fruitImages;

		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return fruitLabel.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	public static class ViewHolder {
		ImageView imageViewFruit;
		TextView textViewFruit;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list_row, null);

			holder.imageViewFruit = (ImageView) convertView
					.findViewById(R.id.imageIcon);
			holder.textViewFruit = (TextView) convertView
					.findViewById(R.id.imageLabel);
			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();
		holder.imageViewFruit.setImageResource(fruitImage[position]);
		holder.textViewFruit.setText(fruitLabel[position]);
		return convertView;
	}
}