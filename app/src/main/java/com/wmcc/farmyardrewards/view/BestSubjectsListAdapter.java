package com.wmcc.farmyardrewards.view;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wmcc.farmyardrewards.R;

class BestSubjectsListAdapter extends BaseAdapter {
	
	private final String[] subjectArray;
	private final Context context;
	private final LayoutInflater layoutInflater;
	private static final String TAG = "BestSubjectsListAdapter";


	public BestSubjectsListAdapter(Context context, String[] subjectInfoArray ){
		this.context = context;
		subjectArray = subjectInfoArray;
		layoutInflater = LayoutInflater.from(context);
	}

	
	@Override
	public int getCount() {
		return subjectArray.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	
		

		try{
			
		String[] subject = subjectArray[position].split(Constants.bestSubjectsDeliminator); 
			
			
		if(subject.length != 3)
			return null;
		
		convertView = layoutInflater.inflate(R.layout.best_subjects_list_row, null);

		//display question type
		TextView questionType = (TextView) convertView.findViewById(R.id.bestSubjectsListRow_questionType);
		questionType.setText(subject[Constants.bestSubjectsQuestionType]);
		
		//display question count
		TextView questionCount = (TextView) convertView.findViewById(R.id.bestSubjectsListRow_questionCount);
		questionCount.setText(subject[Constants.bestSubjectsQuestionCount]);

		//display question %
		TextView questionCorrectPercentage = (TextView) convertView.findViewById(R.id.bestSubjectsListRow_questionCorrectPercentage);
		questionCorrectPercentage.setText(subject[Constants.bestSubjectsQuestionCorrectPercentage]);

		return convertView;
		}
		catch(Exception e){
			//Log.d(TAG, e.getMessage());
			return null;
		}
		
	}

}
