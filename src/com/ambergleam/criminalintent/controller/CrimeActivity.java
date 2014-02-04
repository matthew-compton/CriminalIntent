package com.ambergleam.criminalintent.controller;

import java.util.UUID;

import com.ambergleam.criminalintent.helper.SingleFragmentActivity;

import android.support.v4.app.Fragment;

public class CrimeActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		UUID crimeId = (UUID) getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
		return CrimeFragment.newInstance(crimeId);
	}

}
