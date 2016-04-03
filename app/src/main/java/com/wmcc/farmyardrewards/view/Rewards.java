package com.wmcc.farmyardrewards.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.wmcc.farmyardrewards.R;


public class Rewards extends Activity implements OnItemSelectedListener, OnClickListener  {

	private Spinner rewardListSpin; 
	private Spinner rewardPointsSpin;
	private EditText rewardOtherET;
	private String[] pointsArray;
	private String[] rewardsArray;
	private Button resetRewards;
    private Button saveRewards;

	ArrayAdapter<String> rewardsAdapter;
	ArrayAdapter<String> rewardPointsAdapter;

	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rewards);
        
        getViewById();
        setListeners();
        
        //get list of rewards
        rewardsArray = getResources().getStringArray(R.array.rewards);
        //rewardsAL = new ArrayList<String>(Arrays.asList(rewardsArray));
        SimpleRowItemAdapter rewardsAdapter = new SimpleRowItemAdapter(this, rewardsArray);
        rewardListSpin.setAdapter(rewardsAdapter);


        //add the points
        pointsArray = new String[11];
        for(int i =0; i<pointsArray.length; i++)
            pointsArray[i] = String.valueOf((i * 10) + 10);

        SimpleRowItemAdapter pointsAdapter = new SimpleRowItemAdapter(this, pointsArray);
        rewardPointsSpin.setAdapter(pointsAdapter);

        getRewardPreferences();

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); 
    }

        
	private void getRewardPreferences() {
		
		
		SharedPreferences preferences = getSharedPreferences(Constants.PrefFarmyardRewards, MODE_PRIVATE);
		
		if (preferences != null) {
			String rewardDescription = preferences.getString(Constants.PrefRewardDescription, "");
			String rewardOther = preferences.getString(Constants.PrefRewardOther, "");
			String rewardPoints = preferences.getString(Constants.PrefRewardPoints, "10");

			
			//display previously selected description
			for(int i=0; i< rewardsArray.length; i++){
				String s = rewardsArray[i];
				if(rewardDescription.equalsIgnoreCase(s)){
					rewardListSpin.setSelection(i, false);
					break;
				}
			}
			
			//Other reward was chosen
			if(rewardDescription.equalsIgnoreCase(getString(R.string.other))){
				rewardOtherET.setVisibility(View.VISIBLE);
				rewardOtherET.setText(rewardOther);
			}
			
			//display previously selected points
			for(int i=0; i< pointsArray.length; i++){
				String s = pointsArray[i];
				if(rewardPoints.equalsIgnoreCase(s)){
					rewardPointsSpin.setSelection(i, false);
					break;
				}
			}
	
			
		}
	}

	private void getViewById(){
		rewardListSpin = (Spinner) findViewById(R.id.rewards_rewardListSpin);
		rewardPointsSpin = (Spinner) findViewById(R.id.rewards_rewardPointsSpin);
		rewardOtherET = (EditText) findViewById(R.id.rewards_rewardOther);
		resetRewards = (Button) findViewById(R.id.rewards_resetRewardsBtn);
        saveRewards = (Button) findViewById(R.id.rewards_saveRewardsBtn);
    }
    
    private void setListeners(){
    	rewardListSpin.setOnItemSelectedListener(this);
    	rewardPointsSpin.setOnItemSelectedListener(this);
    	resetRewards.setOnClickListener(this);
        saveRewards.setOnClickListener(this);
    }

	@Override
	public void onItemSelected(AdapterView<?> parentView, View view, int position,long arg3) {
		
		switch( parentView.getId()){
		case R.id.rewards_rewardListSpin:
			rewardOtherET.setVisibility(rewardsArray[position].equalsIgnoreCase(getString(R.string.other)) ? View.VISIBLE : View.GONE );
			break;
		case R.id.rewards_rewardPointsSpin:
            //String s = pointsArray[position];
			break;

		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
	}


    public void onBackPressed(){

        if (isDatabaseLoadComplete())
            finish();

    }

    private boolean isDatabaseLoadComplete() {
        SharedPreferences preferences = getSharedPreferences(Constants.PrefFarmyardRewards, MODE_PRIVATE);

        if (preferences == null) {
            Toast.makeText(this, getString(R.string.databaseStillLoading), Toast.LENGTH_LONG).show();
            return false;
        }

        boolean questionsAvailable = preferences.getBoolean(Constants.prefQuestionsAvailable, false);
        if( questionsAvailable == false ){
            Toast.makeText(this, getString(R.string.databaseStillLoading), Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }


    @Override
	public void onClick(View v) {

		switch( v.getId()){
		case R.id.rewards_resetRewardsBtn:
				resetRewardsAlertMessage();
			break;

        case R.id.rewards_saveRewardsBtn:
            //save preferences
            SharedPreferences preferences = this.getSharedPreferences(Constants.PrefFarmyardRewards, MODE_PRIVATE);


            String rewardSelected = rewardsArray[rewardListSpin.getSelectedItemPosition()];
            String pointsSelected = pointsArray[rewardPointsSpin.getSelectedItemPosition()];
            String rewardOther = rewardOtherET.getText().toString();

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(Constants.PrefRewardDescription, rewardSelected);
            editor.putString(Constants.PrefRewardPoints, pointsSelected);
            editor.putString(Constants.PrefRewardOther, rewardOther);
            editor.apply();


            if (isDatabaseLoadComplete() == false)
                return;


            //go to home screen
            Intent intent= new Intent(getApplicationContext(),Home.class);
            startActivity(intent);
            finish();
            break;
		}
		
	}

	
	private void resetRewards(){
		SharedPreferences preferences = this.getSharedPreferences(Constants.PrefFarmyardRewards, 0);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString(Constants.PrefRewardPointsScored, "0");
		editor.apply();
	}

	void resetRewardsAlertMessage() {


			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Do you wish to reset the rewards total to zero? You would normally do this after a reward has been attained and you are creating a new one.")
			.setCancelable(false)
			.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            })
			.setNegativeButton("Reset", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    resetRewards();
                }
            });
			AlertDialog alert = builder.create();
			alert.setTitle("Reset Rewards");
			alert.setIcon(android.R.drawable.ic_dialog_alert);
			alert.show();

	}
	
}
