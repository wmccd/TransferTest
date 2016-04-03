package com.wmcc.farmyardrewards.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.wmcc.farmyardrewards.model.Question;
import com.wmcc.farmyardrewards.view.Constants;
import com.wmcc.farmyardrewards.view.Utility;

public class QuestionsDBHandler extends SQLiteOpenHelper {
	
	private final String TAG = "QuestionsDBHandler";
	private int numOfDbQuestionRecords = 0;
	
	// Database details
	private static final int DATABASE_VERSION = 30;
	private static final String DATABASE_NAME = "questions.db";
	
	//Questions 
	private final String TABLE_NAME = "questionTable";
	private final String ID = "ID";						private final int colID = 0;
	private final String TYPE = "TYPE";					private final int colTYPE = 1;
	private final String INSTRUCTION = "INSTRUCTION";	private final int colINSTRUCTION = 2;
	private final String QUESTION = "QUESTION";			private final int colQUESTION = 3;
	private final String IMAGEFILE = "IMAGEFILE";		private final int colIMAGEFILE = 4;
	private final String CHOICE1 = "CHOICE1";			private final int colCHOICE1 = 5;
	private final String CHOICE2 = "CHOICE2";			private final int colCHOICE2 = 6;
	private final String CHOICE3 = "CHOICE3";			private final int colCHOICE3 = 7;
	private final String CHOICE4 = "CHOICE4";			private final int colCHOICE4 = 8;
	private final String CHOICE5 = "CHOICE5";			private final int colCHOICE5 = 9;
	private final int colsInQuestionTable = 10;
	
	
	//Summary of how well each type of question is answered
	private final String SUMMARY_TABLE_NAME = "questionSummaryTable";
	private final String SUMMARY_ID = "ID";				private int colSUMMARY_ID = 0;
	private final String SUMMARY_TYPE = "TYPE";			private final int colSUMMARY_TYPE = 1;
	private final String REWARD_ASKED = "REWARD_ASKED"; private final int colREWARD_ASKED = 2;
	private final String REWARD_POINTS = "REWARD_POINTS"; private final int colREWARD_POINTS = 3;
	private final String LIFETIME_ASKED = "LIFETIME_ASKED"; private final int colLIFETIME_ASKED = 4;
	private final String LIFETIME_POINTS = "LIFETIME_POINTS"; private final int colLIFETIME_POINTS = 5;
	
	private SQLiteDatabase db;

	public QuestionsDBHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		////Log.d(TAG, "QuestionsDBHandler");
		closeDB();
		openDB();
	}
	
	void openDB(){
		////Log.d(TAG, "openDB");
		if( db == null )
			db = getWritableDatabase();
	}
	
	public void closeDB(){
		////Log.d(TAG, "closeDB");
		if( db == null)
			return;
		
		db.close();
		
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		//Log.d(TAG, "onCreate");
		
		String createTable  = "CREATE TABLE " + TABLE_NAME+ "(" +
				ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + 
				TYPE + " TEXT," + 
				INSTRUCTION + " TEXT," + 
				QUESTION + " TEXT," + 
				IMAGEFILE + " TEXT," + 
				CHOICE1 + " TEXT," + 
				CHOICE2 + " TEXT," + 
				CHOICE3 + " TEXT," + 
				CHOICE4 + " TEXT," + 
				CHOICE5 + " TEXT);";
		db.execSQL(createTable);
		
		createTable = "CREATE TABLE " + SUMMARY_TABLE_NAME+ "(" + 
				SUMMARY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + 
				SUMMARY_TYPE + " TEXT," + 
				REWARD_ASKED + " INTEGER," + 
				REWARD_POINTS + " INTEGER," + 
				LIFETIME_ASKED + " INTEGER," + 
				LIFETIME_POINTS + " INTEGER);";
		db.execSQL(createTable);
		
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		////Log.d(TAG, "onUpgrade");
		
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + SUMMARY_TABLE_NAME);

		// Create tables again
		onCreate(db);
		
	}

	
	public boolean addQuestion(Question q){
		
		////Log.d(TAG, "addQuestion");
		
		try{

			ContentValues values = new ContentValues();
			values.put(TYPE, q.getQuestionType());
			values.put(INSTRUCTION, q.getQuestionInstruction());
			values.put(QUESTION, q.getQuestionAsked());
			values.put(CHOICE1, (q.getQuestionChoices())[0]);
			values.put(CHOICE2, (q.getQuestionChoices())[1]);
			values.put(CHOICE3, (q.getQuestionChoices())[2]);
			values.put(CHOICE4, (q.getQuestionChoices())[3]);
			values.put(CHOICE5, (q.getQuestionChoices())[4]);
			
			//images may be included sometimes
			if(!Utility.isNullorEmpty(q.getQuestionImage()))
				values.put(IMAGEFILE, (q.getQuestionImage()));
			
			db.insert(TABLE_NAME, null, values);
			//db.close(); // Closing database connection
			
			return true;
		} catch (Exception e) {
			Log.i(TAG, e.getMessage() + e.getCause()  );
			e.printStackTrace();
			return false;
		}
	}
	
	
	public void addQuestionType(String questionType){
		
		////Log.d(TAG, "addQuestionType");
		
		try{

			ContentValues values = new ContentValues();
			values.put(SUMMARY_TYPE, questionType);
			values.put(REWARD_ASKED, 0 );
			values.put(REWARD_POINTS, 0 );
			values.put(LIFETIME_ASKED,0 );
			values.put(LIFETIME_POINTS, 0 );
						
			db.insert(SUMMARY_TABLE_NAME, null, values);

        } catch (Exception e) {
			//Log.i(TAG, e.getMessage() + e.getCause() );
			e.printStackTrace();
        }
	}

	public void updateSummaryTotals(String questionType, int result ){

		
		int rewardAsked;
		int rewardPoints;
		int lifetimeAsked;
		int lifetimePoints;

        //get the current counts
		String sql = "SELECT * from " + SUMMARY_TABLE_NAME + " where " + SUMMARY_TYPE + " = '" + questionType + "'" ;
		Cursor c = db.rawQuery(sql, null);

		if(c == null || c.getCount() != 1)
			return;
			
		c.moveToFirst();		
		rewardAsked = c.getInt(colREWARD_ASKED);
		rewardPoints = c.getInt(colREWARD_POINTS);
		lifetimeAsked = c.getInt(colLIFETIME_ASKED);
		lifetimePoints = c.getInt(colLIFETIME_POINTS);	
		c.close();
		
		//update the question count
		sql = "UPDATE " + SUMMARY_TABLE_NAME + " SET " + 
				REWARD_ASKED + " = " + (++rewardAsked) + ", " +
				LIFETIME_ASKED + " = " + (++lifetimeAsked) + 
				" where " + SUMMARY_TYPE + " = '" + questionType + "'" ;	
		db.execSQL(sql);	
		
		//update the correct answer count
		if( result == Constants.ResultCorrect ){
			sql = "UPDATE " + SUMMARY_TABLE_NAME + " SET " + 
					REWARD_POINTS + " = " + (++rewardPoints) + ", " +
					LIFETIME_POINTS + " = " + (++lifetimePoints) + 
					" where " + SUMMARY_TYPE + " = '" + questionType + "'" ;
			db.execSQL(sql);	
		}
		
		c.close();
			
	}
	
	public String[] getQuestionTypeSummary(){
		
		////Log.d(TAG, "getQuestionTypeSummary");


        //to get a % when dividing one int by another you have to multiply one of them by a float (eg *1.0)
		String sql = "SELECT * from " + SUMMARY_TABLE_NAME +
                    " WHERE " + LIFETIME_ASKED  + " > 0 " +
                    " ORDER BY ("+ LIFETIME_POINTS + "*1.0/" + LIFETIME_ASKED + ") DESC , "  + LIFETIME_ASKED + " DESC, " +   SUMMARY_TYPE  ;
		Cursor c = db.rawQuery(sql, null);	

		if(c == null || c.getCount() <= 0)
			return null;
			
		String[] summaryArray = new String[c.getCount()];

		
		c.moveToFirst();
		int i = 0;
		do{
			String summary = "";
			summary += c.getString(colSUMMARY_TYPE) + ",";
			summary += c.getInt(colLIFETIME_ASKED) + ",";
			
			float asked =  c.getInt(colLIFETIME_ASKED);	
			int points = c.getInt(colLIFETIME_POINTS);
			float percent =  (float)0.00;
			
			if( asked > 0.00 && points >  0.00){
				percent= ((float)points / asked) * 100;
				percent = Utility.floatTwoDecimalPlaces(percent);
			}
			summary += Float.toString(percent);
			
			summaryArray[i++] = summary;

		}while( c.moveToNext());
		
		c.close();
		
		return summaryArray;

	}
	
	//how many records in the questions table
	public int getNumOfDbQuestionRecords(){
		
		////Log.d(TAG, "getNumOfDbQuestionRecords");
		int i = 0;
		Cursor c ;
		
		try{
			String sql = "select count(*) from " + TABLE_NAME ;
			c = db.rawQuery(sql, null);
			
			if(c!=null && c.getCount()>0){
				c.moveToFirst();
				i = c.getInt(0);
			}

		}catch(Exception e){

		}finally{
			//c.close();
			//c = null;
		}
		return i;
	}

	
	
	//clear out the db
	public void dropDbQuestionTable(){
		
		////Log.d(TAG, "dropDbQuestionTable");
		
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + SUMMARY_TABLE_NAME);

		// Create tables again
		onCreate(db);
	}
	

	
	//get an array of questions 
	public Question[] getQuestionArray(int numOfQuestions){
		
		////Log.d(TAG, "getQuestionArray");
		
		if( numOfDbQuestionRecords == 0 )
			numOfDbQuestionRecords = getNumOfDbQuestionRecords();
		
		Question[] q = new Question[numOfQuestions];
		for(int i = 0; i<numOfQuestions; i++)
			q[i] = getQuestion();
	
		
		return q;		
	}
	
	//get one question chosen at random
	private Question getQuestion(){
		
		//select a random question
        int questionID = Utility.getRandomNumberBetween1AndX(numOfDbQuestionRecords)  ;

        return getQuestionFromDbByID(questionID);
	}

	private Question getQuestionFromDbByID(int id){
		
		////Log.d(TAG, "getQuestionFromDbByID");
		
		Question q = new Question();
		
		String sql = "Select * from " + TABLE_NAME + " where ID =  " + id;
		Cursor c = db.rawQuery(sql, null);
		c.moveToFirst();
		
		//check that there are the right number of fields
		if ( c.getColumnCount() == colsInQuestionTable){
			q.setID( c.getInt( colID) );
			q.setQuestionType(c.getString(colTYPE));
			q.setQuestionInstruction(c.getString(colINSTRUCTION));
			q.setQuestionAsked(c.getString(colQUESTION));
			q.setQuestionImage(c.getString(colIMAGEFILE));
			
			String[] choices = new String[Constants.MultipleChoiceQuestionCount];
			choices[0] = c.getString(colCHOICE1);
			choices[1] = c.getString(colCHOICE2);
			choices[2] = c.getString(colCHOICE3);
			choices[3] = c.getString(colCHOICE4);
			choices[4] = c.getString(colCHOICE5);

			q.setQuestionChoices(choices);
			
		}
		c.close();
		return q;
	
	}
	
	
	
	
}
