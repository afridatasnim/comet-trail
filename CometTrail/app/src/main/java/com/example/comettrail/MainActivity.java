package com.example.comettrail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

	Spinner spinnerM, spinnerT;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// The Spinner for the major
		spinnerM = (Spinner) findViewById(R.id.spinnerMajor);

		ArrayAdapter<CharSequence> staticAdapter0 = ArrayAdapter.createFromResource(this,
				R.array.majors_array, android.R.layout.simple_spinner_item);

		staticAdapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinnerM.setAdapter(staticAdapter0);

		// The spinner for the term
		spinnerT = (Spinner) findViewById(R.id.spinnerTerm);

		ArrayAdapter<CharSequence> staticAdapter1 = ArrayAdapter.createFromResource(this,
				R.array.terms_array, android.R.layout.simple_spinner_item);

		staticAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinnerT.setAdapter(staticAdapter1);
	}

	public void generateClassSchedules(View view){
		// This function should pass along the informaiton gathered in the
		// Text boxes and classes to the next activity and start the new activity

		// Load other information here to be transfered over

		Intent intent = new Intent(this, RecommendedSchedules.class);
		startActivity(intent);
	}
}