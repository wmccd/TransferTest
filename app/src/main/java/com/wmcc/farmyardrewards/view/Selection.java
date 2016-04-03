package com.wmcc.farmyardrewards.view;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.wmcc.farmyardrewards.R;

public class Selection extends Activity implements OnClickListener, OnSeekBarChangeListener {
	
	private SeekBar sbHowManyQuestions;
	private Button btnStart;
	private TextView tvQuestionsSelectedCount;
	private int numOfQuestionsSelected;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection);

        getViewById();
        setListeners();

        sbHowManyQuestions.setMax(Constants.HowManyQuestionsRange);
        sbHowManyQuestions.setProgress(Constants.HowManyQuestionsStartPosition);


        SharedPreferences preferences = getSharedPreferences(Constants.PrefFarmyardRewards, 0);
        if (preferences != null) {
            String rewardGoalPoints = preferences.getString(Constants.PrefRewardPointsScored, "0");
            String rewardPoints = preferences.getString(Constants.PrefRewardPoints, "10");

            Toast.makeText(this, "You have " + rewardGoalPoints + " of the " + rewardPoints + " points you need to claim your reward.", Toast.LENGTH_LONG ).show();

        }
    }

    
    private void getViewById(){
    	sbHowManyQuestions = (SeekBar) findViewById(R.id.seletion_sbHowManyQuestions);
    	btnStart = (Button) findViewById(R.id.selection_btnStart);
    	tvQuestionsSelectedCount = (TextView)findViewById(R.id.seletion_tvQuestionsSelectedCount);
    }
    
    private void setListeners(){
    	sbHowManyQuestions.setOnSeekBarChangeListener(this);
    	btnStart.setOnClickListener(this);
    }


	@Override
	public void onClick(View view) {
		switch (view.getId()) {
	    case R.id.selection_btnStart:
			Intent intent = new Intent(getApplicationContext(),Questions.class);
			intent.putExtra(Constants.NumOfQuestionsSelected,numOfQuestionsSelected);
			startActivity(intent);
	        break;

	    } 
		
	}


	
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		numOfQuestionsSelected = progress + Constants.HowManyQuestionsMin;
		tvQuestionsSelectedCount.setText(Integer.toString(numOfQuestionsSelected));
	}


	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
	}


	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {		
	}

    
}
