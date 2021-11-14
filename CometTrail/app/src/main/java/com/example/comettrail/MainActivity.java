package com.example.comettrail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

	Spinner dropdownMajor = findViewById(R.id.spinnerMajor);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//String[] items = new String[]{"Computer Science","Computer Engineering", "Electrical Engineering"};

		//ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, items);
		//dropdownMajor.setAdapter(adapter);
	}

	public void generateClassSchedules(View view){
		// This function should pass along the informaiton gathered in the
		// Text boxes and classes to the next activity and start the new activity

		// Load other information here to be transfered over

		Intent intent = new Intent(this, RecommendedSchedules.class);
		startActivity(intent);
	}
}