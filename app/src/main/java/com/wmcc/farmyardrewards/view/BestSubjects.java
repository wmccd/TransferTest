package com.wmcc.farmyardrewards.view;


import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.wmcc.farmyardrewards.R;
import com.wmcc.farmyardrewards.controller.QuestionsDBHandler;

public class BestSubjects extends Activity {
	
	private String TAG = "BestSubjects";
	private ListView bestSubjectsLV;
	private QuestionsDBHandler questionsDBHandler;
	private String[] bestSubjectsArray;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.best_subjects);
        
        questionsDBHandler = new QuestionsDBHandler(this);
        getViewById();
        getBestSubjectData();
        
        BestSubjectsListAdapter bsla = new BestSubjectsListAdapter(this, bestSubjectsArray);
        bestSubjectsLV.setAdapter(bsla);     
    }


	private void getBestSubjectData() {
		bestSubjectsArray = questionsDBHandler.getQuestionTypeSummary();		
	}


	private void getViewById() {
		
		bestSubjectsLV = (ListView)findViewById(R.id.bestSubjects_subjectsLV);
		
	}

}
