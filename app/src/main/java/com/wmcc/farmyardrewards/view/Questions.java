package com.wmcc.farmyardrewards.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wmcc.farmyardrewards.R;
import com.wmcc.farmyardrewards.controller.QuestionsDBHandler;
import com.wmcc.farmyardrewards.model.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Questions extends Activity implements OnClickListener  {
	
	private TextView timeQuestion;
	private TextView questionCount;
	private TextView questionType;
	private TextView questionInstruction;
	private TextView questionAsked;
	private ImageView questionImage;
	private Button next;
	private Button previous;
	private Button quit;
	private CountDownTimer countDownTimer;
	
	private ListView questionLV ;  
	private ArrayAdapter<String> listAdapter ;  
	
	private int numOfQuestionsSelected;
	
	private Question[] questionsRetievedFromDB; 	
	private int currentQuestion = 0;
	
	
	private QuestionsDBHandler questionsDBHandler;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);
        
        //how many questions selected
        Intent intent = getIntent();
        numOfQuestionsSelected = intent.getIntExtra(Constants.NumOfQuestionsSelected, 0);  
                 
        getViewById();
        setOnClickListeners();
        
        questionsDBHandler = new QuestionsDBHandler(this);
        questionsDBHandler.getNumOfDbQuestionRecords();

    	questionsRetievedFromDB = new Question[numOfQuestionsSelected];	
    	//questionTimers = new int[numOfQuestionsSelected];
    	//questionSelectedChoice = new String [numOfQuestionsSelected];

    	//get a full set of questions
    	questionsDBHandler = new QuestionsDBHandler(this);
    	questionsRetievedFromDB = questionsDBHandler.getQuestionArray(numOfQuestionsSelected );
    	questionsDBHandler.close();
    	
    	//we want to ensure there is always a Multiplication, Addition, Division and Subtraction question
    	//overwrite four of the questions at random.
	   	questionsRetievedFromDB[Utility.getRandomNumberBetween0AndXMinus1(numOfQuestionsSelected)] = Utility.createMultiplicationQuestion();
	   	questionsRetievedFromDB[Utility.getRandomNumberBetween0AndXMinus1(numOfQuestionsSelected)] = Utility.createDivisionQuestion();
	   	questionsRetievedFromDB[Utility.getRandomNumberBetween0AndXMinus1(numOfQuestionsSelected)] = Utility.createAdditionQuestion();
	   	questionsRetievedFromDB[Utility.getRandomNumberBetween0AndXMinus1(numOfQuestionsSelected)] = Utility.createSubtractionQuestion();
	   	
    	initQuestionTimers();

    	questionsDBHandler.closeDB();
    	
	   	displayQuestion(currentQuestion);
          
    }
   


	private void initQuestionTimers() {
	 	
		for(int i=0; i<numOfQuestionsSelected; i++)
			questionsRetievedFromDB[i].setTimeRemaining(Constants.MilliSecondsPerQuestion/1000) ;	    
	}



	private void displayQuestion(int requestedQuestion) {
		
		//cancel the currentQuestion timer
		//ignore the first time as timer hasn't been started yet
		if(! getString(R.string.oneHundred).equalsIgnoreCase( timeQuestion.getText().toString())){
			//store whatever time is left this question
			questionsRetievedFromDB[currentQuestion].setTimeRemaining(Integer.parseInt( timeQuestion.getText().toString()) );
			countDownTimer.cancel();
		}
		
		//moving on to new question
		currentQuestion = requestedQuestion;

		
		//if last question then goto result page.
		if( currentQuestion == numOfQuestionsSelected){
			Intent intent = new Intent(getApplicationContext(),Results.class);
			intent.putExtra(Constants.NumOfQuestionsSelected,numOfQuestionsSelected);
			ArrayList<Question> qa = new ArrayList<Question>();
            Collections.addAll(qa, questionsRetievedFromDB);
			intent.putParcelableArrayListExtra(Constants.QuestionArrayList, qa);
			startActivity(intent);
			return;
		}
		
		//hide previous button on first question, hide next button on the last
		previous.setVisibility(currentQuestion <= 0 ? View.INVISIBLE :  View.VISIBLE);
		next.setVisibility(currentQuestion >= numOfQuestionsSelected -1? View.INVISIBLE :  View.VISIBLE );
		
		//get the new question
		Question q = questionsRetievedFromDB[currentQuestion];
    	questionType.setText(q.getQuestionType());
       	questionAsked.setText(Html.fromHtml(q.getQuestionAsked()));
       	
       	if(q.getQuestionInstruction().length() > 0 ){
       		questionInstruction.setText(q.getQuestionInstruction()); 
       		questionInstruction.setVisibility(View.VISIBLE);
       	}else
       		questionInstruction.setVisibility(View.GONE);
       	
       	
       	
       	//is there an image to go with this question?
       	questionImage.setVisibility(View.GONE);
       	if( q.getQuestionImage() != null )
	       	if( q.getQuestionImage().length() > 0 )
	       		if(q.getQuestionImage().equalsIgnoreCase(Constants.QuestionImageNameTimetable)){
	       			questionImage.setImageDrawable(this.getResources().getDrawable(R.drawable.bus_timetable));
	       			questionImage.setVisibility(View.VISIBLE);
	       		}
         	
       	questionCount.setText(Integer.toString(currentQuestion+1) + "/" + Integer.toString(numOfQuestionsSelected) );
    	populateMultipleChoiceList(q.getQuestionChoices());
        startIndividualQuestionCountDownTimer();
        
        
	}


    
    private void startIndividualQuestionCountDownTimer(){
    	//The timer for an individual question
        countDownTimer = new CountDownTimer(questionsRetievedFromDB[currentQuestion].getTimeRemaining() * 1000,1000) {
            public void onTick(long millisUntilFinished) {
            	timeQuestion.setText(Integer.toString((int) (millisUntilFinished/1000)));
             	
            }

            public void onFinish() {
            	timeQuestion.setText("0");
            }
        };countDownTimer.start();
        
    }
    

       
    //populates the ListView with values from String array
	private void populateMultipleChoiceList(String[] choices ) {
		String[] answers =  choices;
		answers = Utility.randomizeMultipleChoices(answers);
		
		ArrayList<String> planetList = new ArrayList<String>();  
		planetList.addAll( Arrays.asList(answers) );
	    listAdapter = new ArrayAdapter<String>(this, R.layout.question_list_row, planetList);  
	    questionLV.setAdapter( listAdapter );
	    
	    questionLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	    	@Override public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3){
	    		Object selectedAnswerObject = questionLV.getItemAtPosition(position);
	    		String selectedAnswer = selectedAnswerObject.toString();
	    		questionsRetievedFromDB[currentQuestion].setSelectedAnswer(selectedAnswer);
	    		displayQuestion(currentQuestion + 1);
	    	}
	    });
	}


    
    private void getViewById(){
    	timeQuestion = (TextView) findViewById(R.id.questions_timeQuestion);
    	questionCount = (TextView) findViewById(R.id.questions_questionCount);
    	questionLV = (ListView) findViewById(R.id.questions_questionLV);
    	questionType = (TextView) findViewById(R.id.questions_questionType);
    	questionInstruction= (TextView) findViewById(R.id.questions_questionInstruction);
    	questionAsked= (TextView) findViewById(R.id.questions_questionAsked);
    	questionImage = (ImageView) findViewById(R.id.questions_questionImage);
    	
    	next = (Button)findViewById(R.id.questions_nextBtn);
    	previous = (Button)findViewById(R.id.questions_previousBtn);
    	quit = (Button)findViewById(R.id.questions_quitBtn);
    }
    
    private void setOnClickListeners(){
    	next.setOnClickListener(this);
    	previous.setOnClickListener(this);
    	quit.setOnClickListener(this);
    }

	@Override
	public void onClick(View view) {
		
		Intent intent;
		
		switch (view.getId()) {
	    case R.id.main_btnTheFarm:
			intent = new Intent(getApplicationContext(),Selection.class);
			startActivity(intent);
	        break;
	    case R.id.questions_nextBtn:
	    	displayQuestion(currentQuestion + 1);
	    	break;
	    case R.id.questions_previousBtn:
	    	displayQuestion(currentQuestion - 1);
	    	break;
	    case R.id.questions_quitBtn:
			intent= new Intent(getApplicationContext(),Home.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY);
			startActivity(intent);
			finish();   
	    	break;    	

	    	

	    } 
	}
	
	


}


