package com.wmcc.farmyardrewards.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.wmcc.farmyardrewards.view.Constants;

public class Question implements Parcelable {
	
	/**
	 * 
	 */
	private int ID;
	private String questionAsked;
	private String questionType;
	private String questionInstruction;
	private String questionImage;
	private int firstNumber;
	private int secondNumber;
	private String[] questionChoices= new String[Constants.MultipleChoiceQuestionCount];
	private String selectedAnswer;
	private int timeRemaining;
	
	public Question( ){

	}
	
    public Question(Parcel source){
   
        //Reconstruct from the Parcel      
    	ID = source.readInt();
    	questionAsked = source.readString();
    	questionType = source.readString();
    	questionInstruction = source.readString();
    	questionImage = source.readString();
    	firstNumber = source.readInt();
    	secondNumber = source.readInt();
        source.readStringArray(questionChoices);
        selectedAnswer = source.readString();
        timeRemaining = source.readInt();
    }
    
    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
        public Question createFromParcel(Parcel source) {
            return new Question(source);
      }
      public Question[] newArray(int size) {
            return new Question[size];
      }
    };
    

    
	
	public Question( String questionAsked, String questionType, String questionInstruction, String[] questionChoices){
		this.questionAsked = questionAsked;
		this.questionType = questionType;
		this.questionInstruction = questionInstruction;
		this.questionChoices = questionChoices;
	}
	
	public String getQuestionAsked() {
		return questionAsked;
	}
	public void setQuestionAsked(String questionAsked) {
		this.questionAsked = questionAsked;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public String getQuestionInstruction() {
		return questionInstruction;
	}
	public void setQuestionInstruction(String questionInstruction) {
		this.questionInstruction = questionInstruction;
	}
	public String[] getQuestionChoices() {
		return questionChoices;
	}
	public void setQuestionChoices(String[] questionChoices) {
		this.questionChoices = questionChoices;
	}

	public int getFirstNumber() {
		return firstNumber;
	}

	public void setFirstNumber(int firstNumber) {
		this.firstNumber = firstNumber;
	}

	public int getSecondNumber() {
		return secondNumber;
	}

	public void setSecondNumber(int secondNumber) {
		this.secondNumber = secondNumber;
	}

	public String getQuestionImage() {
		return questionImage;
	}

	public void setQuestionImage(String questionImage) {
		String s = questionImage;
		if( s!=null)
			if(s.length()>0)
				if (s.contains("\r") || s.contains("\n") )
					s = s.replace("\r", "").replace("\n", "");		
				
		this.questionImage = s;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getSelectedAnswer() {
		return selectedAnswer;
	}

	public void setSelectedAnswer(String selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}

	public int getTimeRemaining() {
		return timeRemaining;
	}

	public void setTimeRemaining(int timeRemaining) {
		this.timeRemaining = timeRemaining;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		
	      dest.writeInt(ID);
	      dest.writeString(questionAsked);
	      dest.writeString(questionType);
	      dest.writeString(questionInstruction);
	      dest.writeString(questionImage);
	      dest.writeInt(firstNumber);
	      dest.writeInt(secondNumber);
	      dest.writeStringArray(questionChoices);
	      dest.writeString(selectedAnswer);
	      dest.writeInt(timeRemaining);
	
	}


	
	
	

}
