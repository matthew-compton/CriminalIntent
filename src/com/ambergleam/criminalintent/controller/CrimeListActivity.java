package com.ambergleam.criminalintent.controller;

import com.ambergleam.criminalintent.helper.SingleFragmentActivity;

import android.support.v4.app.Fragment;

public class CrimeListActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		return new CrimeListFragment();
	}

}
