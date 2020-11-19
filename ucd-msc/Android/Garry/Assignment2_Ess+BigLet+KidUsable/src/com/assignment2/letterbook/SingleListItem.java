/**
 * COMP30510 - Mobile Application Development (Android) Online 
 * Student: Garry Davitt 
 * Student ID:13205364 
 * Course: M.Sc Computer Science (Software Eng.Stream) 
 * Lecturer: Dr. Abraham Campbell
 */

package com.assignment2.letterbook;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("DefaultLocale")
public class SingleListItem extends Activity {
	private int currentFruit = 0;
	private int[] images;
	private ImageView fruitImageView;
	private TextView fruitNameText;
	private TextView fruitBigLetterText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.single_item);

		fruitNameText = (TextView) findViewById(R.id.fruit_label_id);
		fruitBigLetterText = (TextView) findViewById(R.id.fruit_big_letter_label_id);
		fruitImageView = (ImageView) findViewById(R.id.fruitImage);
		Intent intent = getIntent();
		// getting attached intent data

		int currentFruitIndex = intent.getIntExtra("fruitIndex", 0);
		final String[] fruitNames = getIntent().getStringArrayExtra(
				"allFruitNames");
		images = getIntent().getIntArrayExtra("allFruitImages");

		currentFruit = currentFruitIndex;
		setFruit(fruitNames);

		Button nextButton = (Button) findViewById(R.id.nextButtonId);
		nextButton.setOnClickListener(new View.OnClickListener() {
			@SuppressLint("DefaultLocale")
			@Override
			public void onClick(View v) {
				//Increase Counter to move to next Image
				currentFruit++;
				reset(fruitNames);
				setFruit(fruitNames);
			}
		});

		final Button prevButton = (Button) findViewById(R.id.prevButtonId);
		prevButton.setOnClickListener(new View.OnClickListener() {
			@SuppressLint("DefaultLocale")
			@Override
			public void onClick(View v) {

				//Decrease Counter to move to prev Image
				currentFruit--;
				reset(fruitNames);
				setFruit(fruitNames);
			}
		});

		final Button menuButton = (Button) findViewById(R.id.menuButtonId);
		menuButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});

	}

	private void reset(final String[] fruitNames) {
		if (currentFruit == fruitNames.length) {
			currentFruit = 0;
		} else if (currentFruit == -1) {
			currentFruit = fruitNames.length - 1;
		}
	}

	private void setFruit(final String[] fruitNames) {
		fruitImageView.setImageResource(images[currentFruit]);
		fruitNameText.setText(fruitNames[currentFruit]);
		fruitBigLetterText.setText(fruitNames[currentFruit].substring(0, 1)
				+ fruitNames[currentFruit].substring(0, 1).toLowerCase());
	}

}
