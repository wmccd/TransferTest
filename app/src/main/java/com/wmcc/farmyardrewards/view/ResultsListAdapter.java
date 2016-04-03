package com.wmcc.farmyardrewards.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wmcc.farmyardrewards.R;
import com.wmcc.farmyardrewards.model.Question;


class ResultsListAdapter extends BaseAdapter{

	private final Question[] questionArray;
	private final Context context;
	private final LayoutInflater layoutInflater;
	
	public ResultsListAdapter(Context context, Question[] questionInfoArray ){
		this.context = context;
		questionArray = questionInfoArray;
		layoutInflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		return questionArray.length;
	}
	@Override
	public Object getItem(int position) {
		return null;
	}
	@Override
	public long getItemId(int position) {
		return position;
	}
	@SuppressLint("ResourceAsColor")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		convertView = layoutInflater.inflate(R.layout.results_list_row, null);

		//display question number
		TextView questionNum = (TextView) convertView.findViewById(R.id.answerList_questionTitle);
		questionNum.setText("Question " + Integer.toString(position + 1) + ":");
		
		//display question asked
		TextView questionWording = (TextView) convertView.findViewById(R.id.answerList_question);
		questionWording.setText(Html.fromHtml(questionArray[position].getQuestionInstruction() + " " + 
								questionArray[position].getQuestionAsked()));
		
		//display correct answer
		TextView questionCorrectAnswer = (TextView) convertView.findViewById(R.id.answerList_correctAnswer);
		questionCorrectAnswer.setText((questionArray[position].getQuestionChoices())[0] );
		
		//display selected answer
		TextView questionYourAnswer = (TextView) convertView.findViewById(R.id.answerList_yourAnswer);
		questionYourAnswer.setText( questionArray[position].getSelectedAnswer() );

		//are they the same answers?
		TextView questionRewards = (TextView) convertView.findViewById(R.id.answerList_rewards);
		
		switch( Utility.answerEvaluation(questionCorrectAnswer.getText().toString(), questionYourAnswer.getText().toString(), questionArray[position].getTimeRemaining())){
			case Constants.ResultCorrect:
				questionRewards.setTextColor(context.getResources().getColor(R.color.green));
				questionRewards.setText( context.getResources().getString(R.string.oneRewardPointAwarded) + " Correct answer - " + Integer.toString(questionArray[position].getTimeRemaining()) + " seconds left");
			break;
			case Constants.ResultCorrectTimeout:
				questionRewards.setTextColor(context.getResources().getColor(R.color.brown));
				questionRewards.setText( " Correct answer - timed out.");
			break;
			case Constants.ResultIncorrect:
				questionRewards.setTextColor(context.getResources().getColor(R.color.red));
				questionRewards.setText( context.getResources().getString(R.string.oneRewardPointRemoved) + " Incorrect answer.");
			break;	
		}

		return convertView;
	}
	

	
	
}
