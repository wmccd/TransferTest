package com.wmcc.farmyardrewards.view;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.wmcc.farmyardrewards.R;
import com.wmcc.farmyardrewards.controller.QuestionsDBHandler;
import com.wmcc.farmyardrewards.model.Question;

public class Home extends Activity implements OnClickListener  {

	
	private Button btnVisitTheFarm; 
	private Button btnRewards;
    private Button btnBestSubjects;
	private ImageView imgCow;
	private QuestionsDBHandler questionsDBHandler;
	private String TAG = "Home";
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        getViewById();
        setOnClickListeners();

        Thread thread = new Thread() {
            @Override
            public void run() {
                synchronized (this) {
                    createQuestionsDatabase();
                }

            }
        };
        thread.start();



    }

    @Override
    protected void onResume() {
        super.onResume();

        btnVisitTheFarm.setVisibility(View.GONE);

        SharedPreferences preferences = getSharedPreferences(Constants.PrefFarmyardRewards, MODE_PRIVATE);
        if (preferences != null){

            boolean bestSubjectsAvailable = preferences.getBoolean(Constants.prefBestSubjectsAvailable,false);
            btnBestSubjects.setVisibility( bestSubjectsAvailable? View.VISIBLE: View.GONE);

            boolean questionsAvailable = preferences.getBoolean(Constants.prefQuestionsAvailable,false);
            btnVisitTheFarm.setVisibility( questionsAvailable? View.VISIBLE: View.GONE);

            String rewardDescription = preferences.getString(Constants.PrefRewardDescription, "");
            if( rewardDescription != null && rewardDescription.length() > 0 && questionsAvailable == false)
                Toast.makeText(this, getString(R.string.databaseStillLoading), Toast.LENGTH_LONG).show();

        }

    }

    private void getViewById(){
    	btnVisitTheFarm = (Button) findViewById(R.id.main_btnTheFarm);
    	btnRewards = (Button) findViewById(R.id.main_btnRewards);
    	imgCow = (ImageView) findViewById(R.id.main_imgCow);
        btnBestSubjects = (Button)findViewById(R.id.main_btnBestSubject);

    }
    
    private void setOnClickListeners(){
    	btnVisitTheFarm.setOnClickListener(this);
    	btnRewards.setOnClickListener(this);
        btnBestSubjects.setOnClickListener(this);

        imgCow.setOnClickListener(this);
    }

    
    private void createQuestionsDatabase() {

        questionsDBHandler = new QuestionsDBHandler(this.getApplicationContext());
        //questionsDBHandler.dropDbQuestionTable();

        if (questionsDBHandler.getNumOfDbQuestionRecords() == 0) {

            uploadFileContentsToDatabase(Constants.QuestionTypeCostPerLitre, Constants.QuestionInstructionCostPerLitre, Constants.QuestionFileNameCostPerLitre);
            uploadFileContentsToDatabase(Constants.QuestionTypeSynonyms, Constants.QuestionInstructionSynonyms, Constants.QuestionFileNameSynonyms);
            uploadFileContentsToDatabase(Constants.QuestionTypeAntonyms, Constants.QuestionInstructionAntonyms, Constants.QuestionFileNameAntonyms);
            uploadFileContentsToDatabase(Constants.QuestionTypeCollectiveNouns, Constants.QuestionInstructionCollectiveNouns, Constants.QuestionFileNameCollectiveNouns);
            uploadFileContentsToDatabase(Constants.QuestionTypeTriangles, Constants.QuestionInstructionTriangles, Constants.QuestionFileNameTriangles);
            uploadFileContentsToDatabase(Constants.QuestionTypeTwoDimensionalShapes, Constants.QuestionInstructionTwoDimensionalShapes, Constants.QuestionFileNameTwoDimensionalShapes);
            uploadFileContentsToDatabase(Constants.QuestionTypeSolidFigures, Constants.QuestionInstructionSolidFigures, Constants.QuestionFileNameSolidFigures);
            uploadFileContentsToDatabase(Constants.QuestionTypeDecimalsToFractions, Constants.QuestionInstructionDecimalsToFractions, Constants.QuestionFileNameDecimalsToFractions);
            uploadFileContentsToDatabase(Constants.QuestionTypeCostPerKilo, Constants.QuestionInstructionCostPerKilo, Constants.QuestionFileNameCostPerKilo);
            uploadFileContentsToDatabase(Constants.QuestionTypeRounding, Constants.QuestionInstructionRounding, Constants.QuestionFileNameRounding);
            uploadFileContentsToDatabase(Constants.QuestionTypeGramsKilo, Constants.QuestionInstructionGramsKilo, Constants.QuestionFileNameGramsKilo);
            uploadFileContentsToDatabase(Constants.QuestionTypeFractionsDecimal, Constants.QuestionInstructionFractionsDecimal, Constants.QuestionFileNameFractionsDecimal);
            uploadFileContentsToDatabase(Constants.QuestionTypeLitresMillilitres, Constants.QuestionInstructionLitresMillilitres, Constants.QuestionFileNameLitresMillilitres);
            uploadFileContentsToDatabase(Constants.QuestionTypeClocks1224, Constants.QuestionInstructionClocks1224, Constants.QuestionFileNameClocks1224);
            uploadFileContentsToDatabase(Constants.QuestionTypeAngles, Constants.QuestionInstructionAngles, Constants.QuestionFileNameAngles);
            uploadFileContentsToDatabase(Constants.QuestionTypeSequences, Constants.QuestionInstructionSequences, Constants.QuestionFileNameSequences);
            uploadFileContentsToDatabase(Constants.QuestionTypeTensHundreds, Constants.QuestionInstructionTensHundreds, Constants.QuestionFileNameTensHundreds);
            uploadFileContentsToDatabase(Constants.QuestionTypeFewestCoins, Constants.QuestionInstructionFewestCoins, Constants.QuestionFileNameFewestCoins);
            uploadFileContentsToDatabase(Constants.QuestionTypeEquivalentMmCmM, Constants.QuestionInstructionEquivalentMmCmM, Constants.QuestionFileNameEquivalentMmCmM);
            uploadFileContentsToDatabase(Constants.QuestionTypeMoneyChange, Constants.QuestionInstructionMoneyChange, Constants.QuestionFileNameMoneyChange);
            uploadFileContentsToDatabase(Constants.QuestionTypeEquivalentFractions, Constants.QuestionInstructionEquivalentFractions, Constants.QuestionFileNameEquivalentFractions);
            uploadFileContentsToDatabase(Constants.QuestionTypeDecimalAddition, Constants.QuestionInstructionDecimalAddition, Constants.QuestionFileNameDecimalAddition);
            uploadFileContentsToDatabase(Constants.QuestionTypeCubedNumbers, Constants.QuestionInstructionCubedNumbers, Constants.QuestionFileNameCubedNumbers);
            uploadFileContentsToDatabase(Constants.QuestionTypeFraction, Constants.QuestionInstructionFraction, Constants.QuestionFileNameFraction);
            uploadFileContentsToDatabase(Constants.QuestionTypeNotAFactor, Constants.QuestionInstructionNotAFactor, Constants.QuestionFileNameNotAFactor);
            uploadFileContentsToDatabase(Constants.QuestionTypeParentheses, Constants.QuestionInstructionParentheses, Constants.QuestionFileNameParentheses);
            uploadFileContentsToDatabase(Constants.QuestionTypePrimeNumbers, Constants.QuestionInstructionPrimeNumbers, Constants.QuestionFileNamePrimeNumbers);
            uploadFileContentsToDatabase(Constants.QuestionTypeRemainder, Constants.QuestionInstructionRemainder, Constants.QuestionFileNameRemainder);
            uploadFileContentsToDatabase(Constants.QuestionTypeSquareNumbers, Constants.QuestionInstructionSquareNumbers, Constants.QuestionFileNameSquareNumbers);
            uploadFileContentsToDatabase(Constants.QuestionTypeSquareRootOf, Constants.QuestionInstructionSquareRootOf, Constants.QuestionFileNameSquareRootOf);
            uploadFileContentsToDatabase(Constants.QuestionTypeTenthsAndHundredths, Constants.QuestionInstructionTenthsAndHundredths, Constants.QuestionFileNameTenthsAndHundredths);
            uploadFileContentsToDatabase(Constants.QuestionTypeTimesBigger, Constants.QuestionInstructionTimesBigger, Constants.QuestionFileNameTimesBigger);
            uploadFileContentsToDatabase(Constants.QuestionTypeTimesSmaller, Constants.QuestionInstructionTimesSmaller, Constants.QuestionFileNameTimesSmaller);
            uploadFileContentsToDatabase(Constants.QuestionTypeValueOfY, Constants.QuestionInstructionValueOfY, Constants.QuestionFileNameValueOfY);
            uploadFileContentsToDatabase(Constants.QuestionTypeDates, Constants.QuestionInstructionDates, Constants.QuestionFileNameDates);
            uploadFileContentsToDatabase(Constants.QuestionTypeTimetable, Constants.QuestionInstructionTimetable, Constants.QuestionFileNameTimetable);
            uploadFileContentsToDatabase(Constants.QuestionTypeEnglishDefinitions, Constants.QuestionInstructionEnglishDefinitions, Constants.QuestionFileNameEnglishDefinitions);
            uploadFileContentsToDatabase(Constants.QuestionTypeNouns, Constants.QuestionInstructionNouns, Constants.QuestionFileNameNouns);
            uploadFileContentsToDatabase(Constants.QuestionTypeFractionAddition, Constants.QuestionInstructionFractionAddition, Constants.QuestionFileNameFractionAddition);
            uploadFileContentsToDatabase(Constants.QuestionTypeVerbs, Constants.QuestionInstructionVerbs, Constants.QuestionFileNameVerbs);
            uploadFileContentsToDatabase(Constants.QuestionTypeAdverbs, Constants.QuestionInstructionAdverbs, Constants.QuestionFileNameAdverbs);
            uploadFileContentsToDatabase(Constants.QuestionTypeAdjectives, Constants.QuestionInstructionAdjectives, Constants.QuestionFileNameAdjectives);
            uploadFileContentsToDatabase(Constants.QuestionTypeMultiplicationBiggerNumbers, Constants.QuestionInstructionMultiplicationBiggerNumbers, Constants.QuestionFileNameMultiplicationBiggerNumbers);
            uploadFileContentsToDatabase(Constants.QuestionTypeDivisionBiggerNumbers, Constants.QuestionInstructionDivisionBiggerNumbers, Constants.QuestionFileNameDivisionBiggerNumbers);
            uploadFileContentsToDatabase(Constants.QuestionTypeAverage, Constants.QuestionInstructionAverage, Constants.QuestionFileNameAverage);
            uploadFileContentsToDatabase(Constants.QuestionTypeAdverbsAdjectivesNouns, Constants.QuestionInstructionAdverbsAdjectivesNouns, Constants.QuestionFileNameAdverbsAdjectivesNouns);
            uploadFileContentsToDatabase(Constants.QuestionTypeVerbStates, Constants.QuestionInstructionVerbStates, Constants.QuestionFileNameVerbStates);
            uploadFileContentsToDatabase(Constants.QuestionTypeProbability, Constants.QuestionInstructionProbability, Constants.QuestionFileNameProbability);
            uploadFileContentsToDatabase(Constants.QuestionTypeEquivalentMultiplication, Constants.QuestionInstructionEquivalentMultiplication, Constants.QuestionFileNameEquivalentMultiplication);

            //MADS questions created automatically rather than loaded so create entries in summary table here
            questionsDBHandler.addQuestionType(Constants.QuestionTypeMultiplication);
            questionsDBHandler.addQuestionType(Constants.QuestionTypeAddition);
            questionsDBHandler.addQuestionType(Constants.QuestionTypeDivision);
            questionsDBHandler.addQuestionType(Constants.QuestionTypeSubtraction);

        }

        questionsDBHandler.close();


        SharedPreferences preferences = getSharedPreferences(Constants.PrefFarmyardRewards, 0);
        if (preferences != null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(Constants.prefQuestionsAvailable, true);
            editor.apply();
        }
    }
    
    private void uploadFileContentsToDatabase(String questionType, String questionInstruction, String questionFileName) {
	  	
    	//read contents from file
    	String fileContents = Utility.readTextFile(this, questionFileName);
    	fileContents = fileContents.replace("$", "\u00A3");
        
    	//split file contents into question array
    	String[] questionArray = Utility.splitString(fileContents, Constants.questionTextFileNewLine);

        for (String question : questionArray) {

            //split the question array into question and multiple choice answers
            String[] questionFields = Utility.splitString(question, Constants.questionTextFileDeliminator);


            //do we have the correct number of fields
            if (questionFields.length == Constants.textFileNumFieldsWithImage || questionFields.length == Constants.textFileNumFieldsWithoutImage) {

                //prepare the Question object for database insertion
                Question q = new Question();
                q.setQuestionType(questionType);
                q.setQuestionInstruction(questionInstruction);
                q.setQuestionAsked(questionFields[Constants.textFileQuestionAskedPosition]);

                String[] choices = new String[Constants.MultipleChoiceQuestionCount];
                System.arraycopy(questionFields, 1, choices, 0, Constants.MultipleChoiceQuestionCount);

                q.setQuestionChoices(choices);

                //was there an image filename added
                if (questionFields.length == Constants.textFileNumFieldsWithImage)
                    q.setQuestionImage(questionFields[Constants.textFileImagePosition]);
                else
                    q.setQuestionImage("");

                questionsDBHandler.addQuestion(q);

            }
        }
        
        questionsDBHandler.addQuestionType(questionType);
        
        //Log.d(TAG, "uploadFileContentsToDatabase " + questionFileName + " / " + questionsDBHandler.getNumOfDbQuestionRecords());
    	
	}


	@Override
	public void onClick(View view) {
		
		Intent intent;
		
		switch (view.getId()) {
	    case R.id.main_btnTheFarm:
			intent = new Intent(getApplicationContext(),Selection.class);
			startActivity(intent);
	        break;
	    case R.id.main_btnRewards:
			intent = new Intent(getApplicationContext(),Rewards.class);
			startActivity(intent);
	        break;
        case R.id.main_btnBestSubject:
            intent = new Intent(getApplicationContext(), BestSubjects.class);
            startActivity(intent);
            break;

	    case R.id.main_imgCow:
	    	String credits = getString(R.string.creditDeveloper) + "\n\n" + getString(R.string.creditImages) + "\n\n" + getString(R.string.creditSounds);
	    	
	    	Toast.makeText(this, credits, Toast.LENGTH_LONG).show();
	    	
		    Thread thread=  new Thread(){
		        @Override
		        public void run(){
		            try {
		                synchronized(this){
		                    wait(3000);
		            	    Utility.playSoundFile(getApplicationContext(), "cow.mp3");
		                }
		            }
		            catch(InterruptedException ex){                    
		            }
	             
		        }
		    };
		    thread.start(); 
	    	
	    } 
	}
    
    
}
