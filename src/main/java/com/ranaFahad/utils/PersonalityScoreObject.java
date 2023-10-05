package com.ranaFahad.utils;

//A personality score object contains two fields:
//// 1. An IntelligenceTypes enum type that make sure only the eight types are mentioned throughout the program
//// 2. An integer field that contains the percentage score for that specific intelligence type

//Purpose of this class:
//// This class takes two inputs:
//// 1. The intelligence type name
//// 2. The sum of the scores of the 10 questions for that intelligence type

//// The constructor's tasks:
//// 1. Set the intelligenceType
//// 2. Call the setter function setScorePercentage() whose tasks are described below.

//// The tasks of setScorePercentage(int sumOfScores):
//// 1. It checks if the sum is 0, in which case percentage is automatically 0.
//// 2. It computes the score percentage using the formula (score/maximum) * 100. Max possible in this case is 200 since the score starting
//// -point is 100.
//// 3. It will turn that score percentage into a rounded integer.
//// 4. The setter will set the scorePercentage field to the final percentage.
public class PersonalityScoreObject implements Comparable<PersonalityScoreObject>{
    private IntelligenceTypesEnum intelligenceType;
    private int scorePercentage;

    //Constructor
    public PersonalityScoreObject(String intelligenceTypeName, int sumOfScores)
    {
        //Set the intelligence type
        setIntelligenceType(intelligenceTypeName);

        //Set the scorePercentage
        setScorePercentage(sumOfScores);
    }


    //Getter for the intelligence type name
    public String getName()
    {
        return intelligenceType.getIntelligenceTypeName();
    }

    //Getter for the scorePercentage
    public int getScorePercentage()
    {
        return scorePercentage;
    }

    //intelligenceType setter
    //Checks if the value is a valid intelligence type, then assign the corresponding enum value
    public void setIntelligenceType(String intelligenceTypeGiven)
    {
        switch (intelligenceTypeGiven.toLowerCase()){

            case "naturalist":
                this.intelligenceType = IntelligenceTypesEnum.NATURALIST;
                break;

            case "musical":
                this.intelligenceType = IntelligenceTypesEnum.MUSICAL;
                break;

            case "linguistic":
                this.intelligenceType = IntelligenceTypesEnum.LINGUISTIC;
                break;

            case "intrapersonal":
                this.intelligenceType = IntelligenceTypesEnum.INTRAPERSONAL;
                break;

            case "logical":
                this.intelligenceType = IntelligenceTypesEnum.LOGICAL;
                break;

            case "interpersonal":
                this.intelligenceType = IntelligenceTypesEnum.INTERPERSONAL;
                break;

            case "kinesthetic":
                this.intelligenceType = IntelligenceTypesEnum.KINESTHETIC;
                break;

            case "spatial":
                this.intelligenceType = IntelligenceTypesEnum.SPATIAL;
                break;

            default:
                System.out.println("ERROR: INVALID INTELLIGENCE TYPE!");
        }
    }

    //ScorePercentage setter
    private void setScorePercentage(int sumOfScores){

        final double maximumScore = 200; //Maximum score constant
        int percentageCalculated;

        //Check if the sum is 0. If so, set the percentage to 0.
        if(sumOfScores == 0)
        {
            percentageCalculated = 0;
        }

        //If it isn't 0, then compute the score percentage using score/maximum * 100.
        //We first calculate the percentage using the formula which returns a floating point value.
        //Then we use Math.round() to round that off to the nearest long (Integer) number.
        percentageCalculated = (int) Math.round((sumOfScores / maximumScore) * 100);

        //Set the scorePercentage field to the percentage calculated
        this.scorePercentage = percentageCalculated;
    }

    //The comparable interface
    @Override
    public int compareTo(PersonalityScoreObject personalityScoreObject)
    {
        if(this.getScorePercentage() == personalityScoreObject.getScorePercentage()){
            return 0;
        }
        else if(this.getScorePercentage() > personalityScoreObject.getScorePercentage()){
            return -1;
        }
        else{
            return 1;
        }
    }

    //ToString override
    @Override
    public String toString()
    {
        return this.getName() + ": " + this.getScorePercentage() + "%";
    }

}
