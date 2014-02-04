package com.ambergleam.criminalintent.controller;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.UUID;

import com.ambergleam.criminalintent.R;
import com.ambergleam.criminalintent.model.Crime;
import com.ambergleam.criminalintent.model.CrimeLab;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

public class CrimeFragment extends Fragment {

	public static final String EXTRA_CRIME_ID = "com.ambergleam.criminalintent.crime_id";
	
	private Crime mCrime;
	private EditText mTitleField;
	private Button mDateButton;
	private CheckBox mSolvedCheckBox;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		UUID crimeId = (UUID)getArguments().getSerializable(EXTRA_CRIME_ID);
		mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
	}

	public static CrimeFragment newInstance(UUID crimeId) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_CRIME_ID, crimeId);
		
		CrimeFragment fragment = new CrimeFragment();
		fragment.setArguments(args);
		
		return fragment;
	}
	
	public void returnResult() {
		getActivity().setResult(Activity.RESULT_OK, null);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_crime, parent, false);

		mTitleField = (EditText) v.findViewById(R.id.crime_title);
		mTitleField.setText(mCrime.getTitle());
		mTitleField.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				mCrime.setTitle(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// Intentionally left blank
			}

			@Override
			public void afterTextChanged(Editable s) {
				// Intentionally left blank
			}
		});

		mDateButton = (Button) v.findViewById(R.id.crime_date);
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM dd, yyyy", Locale.US);
		String date = sdf.format(mCrime.getDate());
		mDateButton.setText(date);
		mDateButton.setEnabled(false);

		mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
		mSolvedCheckBox.setChecked(mCrime.isSolved());
		mSolvedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// Set the crime's solved property
				mCrime.setSolved(isChecked);
			}

		});

		return v;
	}
}
