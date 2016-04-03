package com.wmcc.farmyardrewards.view;


public class Constants {

	public static final int HowManyQuestionsMin = 15;
	public static final int HowManyQuestionsRange = 30;
	public static final int HowManyQuestionsStartPosition = 10;
	
	public static final int MultipleChoiceQuestionCount = 5;
			
	public static final int MilliSecondsPerQuestion = 30000; //20 seconds
	public static final String NumOfQuestionsSelected = "NumOfQuestionsSelected";
	public static final String QuestionArrayList = "QuestionArrayList";
		
	public static final int MaximumMultiplier = 12;
	public static final int MultiplicationAnswerChoiceRange = 20;
	
	public static final int MaximumAddition = 200;
	public static final int AdditionAnswerChoiceRange = 40;

	public static final String questionTextFileDeliminator = ",";
	public static final String questionTextFileNewLine = "\n";

	public static final int textFileQuestionAskedPosition = 0;//question will be the first field
	public static final int textFileImagePosition = 6; //image file listed last in file
	public static final int textFileNumFieldsWithoutImage = 6;
	public static final int textFileNumFieldsWithImage = 7;
	
	public static final String bestSubjectsDeliminator = ",";
	public static final int bestSubjectsQuestionType = 0;
	public static final int bestSubjectsQuestionCount = 1;
	public static final int bestSubjectsQuestionCorrectPercentage = 2;
	
	
	public static final String QuestionTypeMultiplication = "Multiplication";
	public static final String QuestionInstructionMultiplication = "Calculate the product.";
	
	public static final String QuestionTypeDivision = "Division";
	public static final String QuestionInstructionDivision = "Find the result.";

	public static final String QuestionTypeAddition = "Addition";
	public static final String QuestionInstructionAddition = "Calculate the sum.";

	public static final String QuestionTypeSubtraction = "Subtraction";
	public static final String QuestionInstructionSubtraction = "Find the result.";

	public static final String QuestionTypeSynonyms = "Synonyms";
	public static final String QuestionInstructionSynonyms = "Choose the synonym for:";
	public static final String QuestionFileNameSynonyms = "Synonyms.csv";
	
	public static final String QuestionTypeAntonyms = "Antonyms";
	public static final String QuestionInstructionAntonyms = "Choose the antonym for:";
	public static final String QuestionFileNameAntonyms = "Antonyms.csv";

	
	public static final String QuestionTypeCollectiveNouns = "Collective Nouns";
	public static final String QuestionInstructionCollectiveNouns = "A name for a number of people or things";
	public static final String QuestionFileNameCollectiveNouns = "CollectiveNouns.csv";
	
	public static final String QuestionTypeTriangles = "Triangles";
	public static final String QuestionInstructionTriangles = "Which type of triangle has the following properties";
	public static final String QuestionFileNameTriangles = "triangles.csv";

	public static final String QuestionTypeTwoDimensionalShapes = "Two Dimensional Shapes";
	public static final String QuestionInstructionTwoDimensionalShapes = "Which shape has the following properties";
	public static final String QuestionFileNameTwoDimensionalShapes = "TwoDimensionalShapes.csv";
	
	public static final String QuestionTypeSolidFigures = "Solid Figures";
	public static final String QuestionInstructionSolidFigures = "Which word defines the following";
	public static final String QuestionFileNameSolidFigures = "SolidFigures.csv";
	
	public static final String QuestionTypeDecimalsToFractions = "Decimals to Fractions";
	public static final String QuestionInstructionDecimalsToFractions = "Convert the following decimal to a fraction:";
	public static final String QuestionFileNameDecimalsToFractions = "DecimalToFraction.csv";
	
	public static final String QuestionTypeCostPerLitre = "Cost Per Litre";
	public static final String QuestionInstructionCostPerLitre = "Calculate the cost";
	public static final String QuestionFileNameCostPerLitre = "CostPerLitre.csv";
	
	public static final String QuestionTypeCostPerKilo = "Cost Per Kilo";
	public static final String QuestionInstructionCostPerKilo = "Calculate the cost";
	public static final String QuestionFileNameCostPerKilo = "CostPerKilogram.csv";

	public static final String QuestionTypeRounding = "Rounding";
	public static final String QuestionInstructionRounding = "Round to the correct value";
	public static final String QuestionFileNameRounding = "Rounding.csv";

	public static final String QuestionTypeGramsKilo = "Grams and Kilos";
	public static final String QuestionInstructionGramsKilo = "Calculate the correct weight";
	public static final String QuestionFileNameGramsKilo = "GramsKilos.csv";
	
	public static final String QuestionTypeFractionsDecimal = "Fractions to Decimal ";
	public static final String QuestionInstructionFractionsDecimal = "Convert the fraction to a decimal";
	public static final String QuestionFileNameFractionsDecimal = "FractionsToDecimal.csv";

	public static final String QuestionTypeLitresMillilitres = "Litres and Millilitres ";
	public static final String QuestionInstructionLitresMillilitres = "Find the liquid amount";
	public static final String QuestionFileNameLitresMillilitres = "LitresMillilitres.csv";

	public static final String QuestionTypeClocks1224 = "24 Hour Clock";
	public static final String QuestionInstructionClocks1224 = "Work out the correct time";
	public static final String QuestionFileNameClocks1224 = "Clocks1224.csv";

	public static final String QuestionTypeAngles = "Angles";
	public static final String QuestionInstructionAngles = "What type of angle is it?";
	public static final String QuestionFileNameAngles = "Angles.csv";

	public static final String QuestionTypeSequences = "Sequences";
	public static final String QuestionInstructionSequences = "What is the next number in the sequence?";
	public static final String QuestionFileNameSequences = "Sequences.csv";

	public static final String QuestionTypeTensHundreds = "Tens and Hundreds";
	public static final String QuestionInstructionTensHundreds = "What is the answer?";
	public static final String QuestionFileNameTensHundreds = "TensHundreds.csv";

	public static final String QuestionTypeFewestCoins = "Fewest Coins";
	public static final String QuestionInstructionFewestCoins = "What is fewset number of coins to make this amount?";
	public static final String QuestionFileNameFewestCoins = "FewestCoins.csv";

	public static final String QuestionTypeEquivalentMmCmM = "Equivalent Measurements";
	public static final String QuestionInstructionEquivalentMmCmM = "Find the matching length";
	public static final String QuestionFileNameEquivalentMmCmM = "EquivalentMmCmM.csv";

	public static final String QuestionTypeMoneyChange = "Money change";
	public static final String QuestionInstructionMoneyChange = "How much change would you get?";
	public static final String QuestionFileNameMoneyChange = "MoneyChange.csv";

	public static final String QuestionTypeEquivalentFractions = "Equivalent Fractions";
	public static final String QuestionInstructionEquivalentFractions = "Find the fraction with the same value";
	public static final String QuestionFileNameEquivalentFractions = "EquivalentFractions.csv";

	public static final String QuestionTypeDecimalAddition = "Decimal Addition";
	public static final String QuestionInstructionDecimalAddition = "Calculate the sum of the decimal numbers ";
	public static final String QuestionFileNameDecimalAddition = "Decimal Addition.csv";

	public static final String QuestionTypeWriteInFigures = "Decimal Addition";
	public static final String QuestionInstructionWriteInFigures = "Calculate the sum of the decimal numbers ";
	public static final String QuestionFileNameWriteInFigures = "WriteInFigures.csv";
	
	public static final String QuestionTypeCubedNumbers = "Cubed Numbers";
	public static final String QuestionInstructionCubedNumbers = "Find the cubed total ";
	public static final String QuestionFileNameCubedNumbers = "CubedNumbers.csv";

	public static final String QuestionTypeFraction = "Fractions";
	public static final String QuestionInstructionFraction = "Find the value of the fraction ";
	public static final String QuestionFileNameFraction = "FractionOf.csv";
	
	public static final String QuestionTypeNotAFactor = "Not a Factor";
	public static final String QuestionInstructionNotAFactor = "Which is not a factor of: ";
	public static final String QuestionFileNameNotAFactor = "NotAFactor.csv";
	
	public static final String QuestionTypeParentheses = "Parentheses";
	public static final String QuestionInstructionParentheses = "Find the answer to the sum ";
	public static final String QuestionFileNameParentheses = "Parentheses.csv";
	
	public static final String QuestionTypePrimeNumbers = "PrimeNumbers";
	public static final String QuestionInstructionPrimeNumbers = "Find the prime number ";
	public static final String QuestionFileNamePrimeNumbers = "PrimeNumbers.csv";
	
	public static final String QuestionTypeRemainder = "Remainder";
	public static final String QuestionInstructionRemainder = "Find the remainder ";
	public static final String QuestionFileNameRemainder = "Remainder.csv";

	public static final String QuestionTypeSquareNumbers = "Square Numbers";
	public static final String QuestionInstructionSquareNumbers = "Find the square number value ";
	public static final String QuestionFileNameSquareNumbers = "SquareNumbers.csv";

	public static final String QuestionTypeSquareRootOf = "Square Root";
	public static final String QuestionInstructionSquareRootOf = "Find the square root value ";
	public static final String QuestionFileNameSquareRootOf = "SquareRootOf.csv";

	public static final String QuestionTypeTenthsAndHundredths = "Tenths And Hundredths";
	public static final String QuestionInstructionTenthsAndHundredths = "Find the Equivalent value ";
	public static final String QuestionFileNameTenthsAndHundredths = "TenthsAndHundredths.csv";

	public static final String QuestionTypeTimesBigger = "Times Bigger";
	public static final String QuestionInstructionTimesBigger = "How many times bigger is the first number ";
	public static final String QuestionFileNameTimesBigger = "TimesBigger.csv";

	public static final String QuestionTypeTimesSmaller = "Times Smaller";
	public static final String QuestionInstructionTimesSmaller = "How many times smaller is the first number ";
	public static final String QuestionFileNameTimesSmaller = "TimesSmaller.csv";

	public static final String QuestionTypeValueOfY = "Value Of Y";
	public static final String QuestionInstructionValueOfY = "What is the value of the missing number";
	public static final String QuestionFileNameValueOfY = "ValueOfY.csv";

	public static final String QuestionTypeWriteInNumbers = "Write In Numbers";
	public static final String QuestionInstructionWriteInNumbers = "What is value in numbers";
	public static final String QuestionFileNameWriteInNumbers = "WriteInNumbers.csv";

	public static final String QuestionTypeDates = "Dates";
	public static final String QuestionInstructionDates = "Dates and Calendars";
	public static final String QuestionFileNameDates = "Dates.csv";

	public static final String QuestionTypeTimetable = "Timetable";
	public static final String QuestionInstructionTimetable = "Read the bus timetable";
	public static final String QuestionFileNameTimetable = "Timetable.csv";
	public static final String QuestionImageNameTimetable = "bus_timetable" ;
	
	public static final String QuestionTypeFractionAddition = "Fraction Addition";
	public static final String QuestionInstructionFractionAddition = "Add the fractions together";
	public static final String QuestionFileNameFractionAddition = "FractionAddition.csv";

	public static final String QuestionTypeEnglishDefinitions = "English Definitions";
	public static final String QuestionInstructionEnglishDefinitions = "Which word matches the definition?";
	public static final String QuestionFileNameEnglishDefinitions = "EnglishDefinitions.csv";

	public static final String QuestionTypeNouns = "Nouns";
	public static final String QuestionInstructionNouns = "Know your nouns?";
	public static final String QuestionFileNameNouns = "Nouns.csv";

	public static final String QuestionTypeVerbs = "Verbs";
	public static final String QuestionInstructionVerbs = "Know your verbs?";
	public static final String QuestionFileNameVerbs = "verbs.csv";
	
	public static final String QuestionTypeAdverbs = "Adverbs";
	public static final String QuestionInstructionAdverbs = "Know your adverbs?";
	public static final String QuestionFileNameAdverbs = "adverbs.csv";
	
	public static final String QuestionTypeAdjectives = "Adjectives";
	public static final String QuestionInstructionAdjectives = "Know your adjectives?";
	public static final String QuestionFileNameAdjectives = "Adjectives.csv";

	public static final String QuestionTypeMultiplicationBiggerNumbers = "Multiplication With Bigger Numbers";
	public static final String QuestionInstructionMultiplicationBiggerNumbers = "Find the result";
	public static final String QuestionFileNameMultiplicationBiggerNumbers = "MultiplicationBiggerNumbers.csv";

	public static final String QuestionTypeDivisionBiggerNumbers = "Division With Bigger Numbers";
	public static final String QuestionInstructionDivisionBiggerNumbers = "Find the product";
	public static final String QuestionFileNameDivisionBiggerNumbers = "DivisionBiggerNumbers.csv";

	public static final String QuestionTypeQuickFox = "Nouns Verbs Adverbs and Adjectives - Quick Fox";
	public static final String QuestionInstructionQuickFox = "The quick fox jumped high over the lazy brown dog";
	public static final String QuestionFileNameQuickFox = "QuickFox.csv";
	
	public static final String QuestionTypeAverage = "Average ";
	public static final String QuestionInstructionAverage = "Know your averages.";
	public static final String QuestionFileNameAverage = "Average.csv";

	public static final String QuestionTypeAdverbsAdjectivesNouns = "Nouns Verbs Adverbs and Adjectives ";
	public static final String QuestionInstructionAdverbsAdjectivesNouns = "";//"Know your nouns, adverbs and adjectives.";
	public static final String QuestionFileNameAdverbsAdjectivesNouns = "AdverbsAdjectivesNouns.csv";

	public static final String QuestionTypeVerbStates = "Verb - States";
	public static final String QuestionInstructionVerbStates = "Know your verbs.";
	public static final String QuestionFileNameVerbStates  = "verbsStates.csv";

	public static final String QuestionTypeProbability = "Probability";
	public static final String QuestionInstructionProbability = "What are the chances?";
	public static final String QuestionFileNameProbability  = "Probability.csv";
	
	public static final String QuestionTypeEquivalentMultiplication = "Equivalent Multiplication";
	public static final String QuestionInstructionEquivalentMultiplication = "Find the matching multiple";
	public static final String QuestionFileNameEquivalentMultiplication  = "EquivalentMultiplication.csv";
	
	public static final int ResultsNoPoint = 0;
	public static final int ResultCorrect = 1;
	public static final int ResultCorrectTimeout= 2;
	public static final int ResultIncorrect = 3;
	
	public static final String PrefFarmyardRewards = "prefFarmyardRewards";
	public static final String PrefRewardDescription = "prefRewardDescription";
	public static final String PrefRewardOther = "prefRewardOther";
	public static final String PrefRewardPoints = "prefRewardPoints";
	public static final String PrefRewardPointsScored = "prefRewardGoalPoints";
	public static final String PrefLifetimePoints = "prefLifetimePoints";
    public static final String prefBestSubjectsAvailable = "prefBestSubjectsAvailable";
    public static final String prefQuestionsAvailable = "prefQuestionsAvailable";

}
