package com.wmcc.farmyardrewards.view;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;

import com.wmcc.farmyardrewards.model.Question;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Utility {

	
	public static Boolean isNullorEmpty(String s)
	{
        return s == null || s.trim().length() == 0;
    }
	
    //put the answers in a random positions
    //returns the randomized array
    public static String[] randomizeMultipleChoices(String[] choices ) {
    	
    	//put 0-4 into a list - shuffle them
    	List<Integer> positions = new ArrayList<Integer>();
    	for(int i = 0; i < Constants.MultipleChoiceQuestionCount; i++)
    		positions.add(i);
    	Collections.shuffle(positions);
    	Collections.shuffle(positions);

    	//place shuffled values into array and return
    	String[] tempChoices = new String[Constants.MultipleChoiceQuestionCount];
       	for(int i = 0; i < Constants.MultipleChoiceQuestionCount; i++)
       		tempChoices[i] = choices[positions.get(i)];
    	
       return tempChoices;
    }
	
	public static Question createAdditionQuestion() {
		Question q = new Question();
	    String[] choices = new String[Constants.MultipleChoiceQuestionCount];

		//pick the numbers - make sure at least one is large 
        int firstNumber = Utility.getRandomNumberBetween1AndX(Constants.MaximumAddition);
        if (firstNumber < Constants.AdditionAnswerChoiceRange)
        	firstNumber += Constants.AdditionAnswerChoiceRange;
        int secondNumber = Utility.getRandomNumberBetween1AndX(firstNumber);
          
        //set the question description fields
        q.setQuestionType(Constants.QuestionTypeAddition);
        q.setQuestionInstruction(Constants.QuestionInstructionAddition);
        q.setQuestionAsked(Integer.toString(firstNumber) + " + " + Integer.toString(secondNumber) );
        q.setFirstNumber(firstNumber);
        q.setSecondNumber(secondNumber);
 
        //set the question answer fields
        int correctAnswer = firstNumber + secondNumber;
        choices[0] = Integer.toString(correctAnswer);     
        for( int i = 1; i < (Constants.MultipleChoiceQuestionCount); i++){
        	
        	//create other answer choices that are near to the correct answer
        	int offset = Utility.getRandomNumberBetween1AndX(Constants.AdditionAnswerChoiceRange);
        	int otherAnswer = correctAnswer - (Constants.AdditionAnswerChoiceRange/2) + offset;
        	
        	//make sure it is a positive number
        	if(otherAnswer < 0)
        		otherAnswer = offset;
        	
        	//make sure it is not a duplicate of the correct answer
        	if(otherAnswer == correctAnswer)
        		otherAnswer++;
      	
        	choices[i] = Integer.toString(otherAnswer);
        }
        
        //mix up the location of the right answer
        //choices = randomizeMultipleChoices(choices);
        
        //set the answer choices
        q.setQuestionChoices(choices);
        
		return q;		
	}
	
	

	public static Question createSubtractionQuestion() {
		Question q = new Question();
	    String[] choices = new String[Constants.MultipleChoiceQuestionCount];
		
		//pick the numbers - make sure at least one is large 
        int firstNumber = Utility.getRandomNumberBetween1AndX(Constants.MaximumAddition);
        if (firstNumber < Constants.AdditionAnswerChoiceRange)
        	firstNumber += Constants.AdditionAnswerChoiceRange;
        int secondNumber = Utility.getRandomNumberBetween1AndX(firstNumber);
          
        //set the question description fields
        q.setQuestionType(Constants.QuestionTypeSubtraction);
        q.setQuestionInstruction(Constants.QuestionInstructionSubtraction);
        q.setQuestionAsked(Integer.toString(firstNumber) + " - " + Integer.toString(secondNumber) );
        q.setFirstNumber(firstNumber);
        q.setSecondNumber(secondNumber);

        
        //set the question answer fields
        int correctAnswer = firstNumber - secondNumber;
        choices[0] = Integer.toString(correctAnswer);     
        for( int i = 1; i < (Constants.MultipleChoiceQuestionCount); i++){
        	
        	//create other answer choices that are near to the correct answer
        	int offset = Utility.getRandomNumberBetween1AndX(Constants.AdditionAnswerChoiceRange);
        	int otherAnswer = correctAnswer - (Constants.AdditionAnswerChoiceRange/2) + offset;
        	
        	//make sure it is a positive number
        	if(otherAnswer < 0)
        		otherAnswer = offset;
        	
        	//make sure it is not a duplicate of the correct answer
        	if(otherAnswer == correctAnswer)
        		otherAnswer++;
      	
        	choices[i] = Integer.toString(otherAnswer);
        }
        
        //mix up the location of the right answer
        //choices = randomizeMultipleChoices(choices);
        
        //set the answer choices
        q.setQuestionChoices(choices);
        
		return q;	
	}

	public static  Question createDivisionQuestion() {   	
		Question q = new Question();

			
		//pick the multipliers - making sure that both values are at least 5 so not too easy
        int firstNumber = Utility.getRandomNumberBetween1AndX(Constants.MaximumMultiplier-5) + 5 ;
        int secondNumber = Utility.getRandomNumberBetween1AndX(Constants.MaximumMultiplier-5) + 5;
         
        int bigNumber = firstNumber * secondNumber;

        //set the question description fields
        q.setQuestionType(Constants.QuestionTypeDivision);
        q.setQuestionInstruction(Constants.QuestionInstructionDivision);
        q.setQuestionAsked(Integer.toString(bigNumber) + " / " + Integer.toString(secondNumber) );
        q.setFirstNumber(firstNumber);
        q.setSecondNumber(secondNumber);

        
        String[] choices = new String[Constants.MultipleChoiceQuestionCount ]; 
        choices[0] = Integer.toString(firstNumber);
        //put values in the remaining slots
        for( int i = 1; i < Constants.MultipleChoiceQuestionCount; i++){
        	while( Utility.isNullorEmpty(choices[i])){
        		int rand = Utility.getRandomNumberBetween1AndX(Constants.MaximumMultiplier-5) + 5;
        		
        		//check the random value isn't already in one of the slots
        		choices[i] = Integer.toString(rand);
        		for(int j = 0; j<i; j++){
        			if(choices[i].equalsIgnoreCase(choices[j]) )
        				choices[i] = "";
        		}
        	}
        	
        }
        
        
        //set the answer choices
        q.setQuestionChoices(choices);
        
        return q;
	}

	
    //Create a Question structure that will hold a Multiplication question
	public static  Question createMultiplicationQuestion() {
		Question q = new Question();
        String[] choices = new String[Constants.MultipleChoiceQuestionCount];
		
		//pick the multipliers 
	   	Random rand = new Random();
        int firstNumber = rand.nextInt(Constants.MaximumMultiplier);
        int secondNumber = rand.nextInt(Constants.MaximumMultiplier);
	
        //make sure they are not the smallest numbers
        if (firstNumber < 3) firstNumber +=rand.nextInt(Constants.MaximumMultiplier - 2); 
        
        //set the question description fields
        q.setQuestionType(Constants.QuestionTypeMultiplication);
        q.setQuestionInstruction(Constants.QuestionInstructionMultiplication);
        q.setQuestionAsked(Integer.toString(firstNumber) + " * " + Integer.toString(secondNumber) );
        q.setFirstNumber(firstNumber);
        q.setSecondNumber(secondNumber);

        
        //set the question answer fields
        int correctAnswer = firstNumber * secondNumber;
        choices[0] = Integer.toString(correctAnswer);     
        for( int i = 1; i < (Constants.MultipleChoiceQuestionCount); i++){
        	
        	//create other answer choices that are near to the correct answer
        	int offset = rand.nextInt(Constants.MultiplicationAnswerChoiceRange);
        	int otherAnswer = correctAnswer - (Constants.MultiplicationAnswerChoiceRange/2) + offset;
        	
        	//make sure it is a positive number
        	if(otherAnswer < 0)
        		otherAnswer = offset;
        	
        	//make sure it is not a duplicate of the correct answer
        	if(otherAnswer == correctAnswer)
        		otherAnswer++;
      	
        	choices[i] = Integer.toString(otherAnswer);
        }
        
        //mix up the location of the right answer
        //choices = randomizeMultipleChoices(choices);
        
        //set the answer choices
        q.setQuestionChoices(choices);
        
		return q;		
    }
	
	public static String readTextFile(Context context, String fileName){

		String s = "";
		AssetManager assetManager = context.getAssets();
				
        InputStream input;
        try {
            input = assetManager.open(fileName);
             
             int size = input.available();
             byte[] buffer = new byte[size];
             int iRead = input.read(buffer);
             input.close();
 
             // byte buffer into a string
             s = new String(buffer);
             
        } catch (IOException e) {
            e.printStackTrace();
        }

		return s;
	
	}
	
	public static String[] splitString( String s, String deliminator){
        return s.split(deliminator);
	}
	
	public static int getRandomNumberBetween0AndXMinus1(int x){
		//This will return  between 0 and x-1
		Random random = new Random(); 
		return (random.nextInt(x) );
	}
	
	public static int getRandomNumberBetween1AndX(int x){
		//Random normally returns between 0 and x-1
		//This will return between 1 and x
		Random random = new Random(); 
		return (random.nextInt(x-1) + 1);
	}
		
	public static int answerEvaluation( String correctAnswer, String selectedAnswer, int timeRemaining){
		
		if( correctAnswer.equalsIgnoreCase( selectedAnswer) )
			if (timeRemaining > 0 )
				return Constants.ResultCorrect;
			else
				return Constants.ResultCorrectTimeout;
		else
			return Constants.ResultIncorrect;

	}

	public static void playSoundFile(Context context, String filename){
		
		try{
			MediaPlayer mp = new MediaPlayer();
			AssetFileDescriptor descriptor;
			descriptor = context.getResources().getAssets().openFd( filename );
			mp.setDataSource( descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength() );
			descriptor.close();
			mp.prepare();
			mp.start();
		}
		catch(Exception e){}
	}
	
	public static float floatTwoDecimalPlaces( float fIn){
		NumberFormat format = NumberFormat.getNumberInstance();
		format.setMaximumFractionDigits(2);
		format.setMinimumFractionDigits(2);
		String s = format.format(fIn);
		s = s.replace(",", "");

        return Float.parseFloat(s);
	
	}


	
	
	
}
