package com.wmcc.farmyardrewards.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wmcc.farmyardrewards.R;
import com.wmcc.farmyardrewards.controller.QuestionsDBHandler;
import com.wmcc.farmyardrewards.model.Question;

import java.util.ArrayList;

public class Results extends Activity {

	private int numOfQuestionsSelected;
	private Question[] questions;
	private ListView resultsLV;
	private int rewardsEarnedThisTime = 0;
	private TextView resultCircleThisTime;
	private TextView resultCircleRewardGoal;
	private TextView messageTV;
	private ImageView animalIV; 
	private String soundFile ="";


	private Thread thread;  
	private QuestionsDBHandler questionsDBHandler;

	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);
        
        rewardsEarnedThisTime = 0;
        getViewById();
        
        questionsDBHandler = new QuestionsDBHandler(this);
        
        //get the extras that were passed.
        Intent intent = getIntent();
        numOfQuestionsSelected = intent.getIntExtra(Constants.NumOfQuestionsSelected, 0);
        Object o  =  intent.getParcelableArrayListExtra(Constants.QuestionArrayList);
        ArrayList<Question> qa = (ArrayList<Question>)o;
        
        questions = new Question[numOfQuestionsSelected];
        for(int i=0; i<numOfQuestionsSelected; i++)
        	questions[i] = qa.get(i);
        
        ResultsListAdapter rla = new ResultsListAdapter(this, questions);
        resultsLV.setAdapter(rla);
         
        //how many reward points were earned
        for (int i = 0; i<numOfQuestionsSelected; i++){
        	String correctAnswer = (questions[i].getQuestionChoices())[0];
        	String selectedAnswer = questions[i].getSelectedAnswer();
        	int timeRemaining = questions[i].getTimeRemaining();
        	
        	int result  = Utility.answerEvaluation(correctAnswer, selectedAnswer, timeRemaining);
        	
        	if(	result == Constants.ResultCorrect){
        		rewardsEarnedThisTime++;
        		questionsDBHandler.updateSummaryTotals(questions[i].getQuestionType(), Constants.ResultCorrect );
        	}else if(result == Constants.ResultCorrectTimeout){
        		questionsDBHandler.updateSummaryTotals(questions[i].getQuestionType(), Constants.ResultCorrectTimeout );
        	}else if(result == Constants.ResultIncorrect){
        		rewardsEarnedThisTime--;
        		questionsDBHandler.updateSummaryTotals(questions[i].getQuestionType(), Constants.ResultsNoPoint );
        	}
        }
        
        
        //Populate the reward circles
        if(rewardsEarnedThisTime < 0)
        	rewardsEarnedThisTime = 0;
        resultCircleThisTime.setText(Integer.toString(rewardsEarnedThisTime));
        
		SharedPreferences preferences = getSharedPreferences(Constants.PrefFarmyardRewards, 0);
		if (preferences != null) {
			String rewardGoalPoints = preferences.getString(Constants.PrefRewardPointsScored, "0");
			String lifetimePoints = preferences.getString(Constants.PrefLifetimePoints, "0");

			int iGoalPoints = Integer.parseInt(rewardGoalPoints) + rewardsEarnedThisTime;
			int iLifetimePoints = Integer.parseInt(lifetimePoints) + rewardsEarnedThisTime;

			SharedPreferences.Editor editor = preferences.edit();
			editor.putString(Constants.PrefRewardPointsScored, Integer.toString(iGoalPoints));
			editor.putString(Constants.PrefLifetimePoints, Integer.toString(iLifetimePoints));
            editor.putBoolean(Constants.prefBestSubjectsAvailable, true);
			editor.apply();

			resultCircleRewardGoal.setText(Integer.toString(iGoalPoints));

			//work out percentage of correct answers
			//display image, display message and play sound clip
			String[] punArray;
			int randomPunArrayPosition;
			float resultPercentage =  ((float)rewardsEarnedThisTime/(float)numOfQuestionsSelected) * 100;
			if(resultPercentage >= 75.0){
				soundFile = "cow.mp3";
				animalIV.setImageDrawable(getResources().getDrawable( R.drawable.cow));
				punArray =  getResources().getStringArray(R.array.cowPuns);
			}else if(resultPercentage >= 50.0){
				soundFile = "dog.mp3";
				animalIV.setImageDrawable(getResources().getDrawable( R.drawable.dog));
				punArray =  getResources().getStringArray(R.array.dogPuns);
			}else if(resultPercentage >= 25.0){
				soundFile = "rooster.mp3";
				animalIV.setImageDrawable(getResources().getDrawable( R.drawable.rooster));
				punArray =  getResources().getStringArray(R.array.roosterPuns);
			}else {
				soundFile = "sheep.mp3";
				animalIV.setImageDrawable(getResources().getDrawable( R.drawable.sheep));
				punArray =  getResources().getStringArray(R.array.sheepPuns);
			}
			randomPunArrayPosition = Utility.getRandomNumberBetween0AndXMinus1(punArray.length);
			messageTV.setText(punArray[randomPunArrayPosition]);


			//reached the target number of points
			int rewardPoints = Integer.parseInt(preferences.getString(Constants.PrefRewardPoints, "10"));
			if(iGoalPoints >= rewardPoints ){
				String reward = preferences.getString(Constants.PrefRewardDescription, "");
				if( reward.equalsIgnoreCase(getString(R.string.other) ))
					reward = preferences.getString(Constants.PrefRewardOther , "");
				messageTV.setText(getString(R.string.targetReached) + " " + reward);
			}

			String reward = preferences.getString(Constants.PrefRewardDescription, "");
			if( Utility.isNullorEmpty(reward))
				messageTV.setText(getString(R.string.noRewardsSpecified));

			
			
		}
		
	    thread=  new Thread(){
	        @Override
	        public void run(){
	            try {
	                synchronized(this){
	                    wait(3000);
	            	    Utility.playSoundFile(getApplicationContext(), soundFile);
	                }
	            }
	            catch(InterruptedException ex){                    
	            }
             
	        }
	    };
	    thread.start(); 
		
	    //questionsDBHandler.closeDB();
    }
    

    
    private void getViewById(){
 
    	resultsLV = (ListView)findViewById(R.id.results_resultsLV);
    	resultCircleThisTime = (TextView)findViewById(R.id.resultCircleThisTime);
    	resultCircleRewardGoal = (TextView)findViewById(R.id.resultCircleRewardGoal);
      	messageTV  = (TextView)findViewById(R.id.results_MessageTV);
    	animalIV = (ImageView)findViewById(R.id.results_resultAnimalIV);
    }
    
    
	public void onBackPressed() {
		Intent intent= new Intent(getApplicationContext(),Home.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY);
		startActivity(intent);
		finish();
    }
    



}
