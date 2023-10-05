package com.ranaFahad;

import com.ranaFahad.utils.PersonalityScoreObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MultipleIntelligencesTest {

    //Make a variable for a MultipleIntelligences object
    private MultipleIntelligences multipleIntelligences;

    //This will be executed before each unit test
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        multipleIntelligences = new MultipleIntelligences("src/test/resources/testconfig.json");
    }

    //Test to ensure there are exactly eight keys in the resultsHashMap
    ////The given method returns the size of the keyset of the aforementioned HashMap
    @Test
    public void eightKeysInHashMap(){
        assertEquals(8, multipleIntelligences.testPersonalityAmount("HashMap"));
    }

    //Test to ensure there are exactly eight PersonalityScoreObjects in the personality score object array list
    ////The given method returns the size of the ArrayList containing the aforementioned Personality Score Objects
    @Test
    public void eightPersonalityScoreObjectsInArrayList(){
        assertEquals(8,multipleIntelligences.testPersonalityAmount("ArrayList"));
    }

    //Test to check if the score of the 5th personality score object in the ArrayList is less than or equal to the first but more than or equal to the last personality score object
    @Test
    public void arrayListSortingCheck(){
        //Get the arraylist and store it in a variable
        ArrayList<PersonalityScoreObject> personalityScoreObjects = multipleIntelligences.getPersonalityScoreObjects();

        //Confirm that the score of the 5th score object is less than or equal to that of the first object
        assertTrue( (personalityScoreObjects.get(4).getScorePercentage()) <= (personalityScoreObjects.get(0).getScorePercentage()) );

        //Confirm that the score of the 5th score object is greater than or equal to that of the last object
        assertTrue( (personalityScoreObjects.get(4).getScorePercentage()) >= (personalityScoreObjects
                .get( personalityScoreObjects.size()-1 )    //Get the object at the last index
                .getScorePercentage()) );
    }


    //Test to confirm that the jobsHashMap is storing descriptions properly.
    //This one checks if the description for intelligence type "Naturalist" is the same as the description in the testjobs.json file
    @Test
    public void checkJobsHashMapDescriptions()
    {
        //Get the jobsHashMap and store it in a HashMap variable
        HashMap<String,String[]> jobsHashMap = multipleIntelligences.getJobsHashMap();

        assertTrue(jobsHashMap.get("Naturalist")[0].equals(
                "You possess the ability to differentiate, categorize, and utilize attributes within your surroundings.\nIndividuals such as gardeners, florists, and geologists exhibit this type of intelligence when it comes to understanding the natural world."
        ));
    }

    //Test to confirm that the jobsHashMap is storing jobs properly.
    //This one checks if the jobs for intelligence type "Spatial" are the same as the jobs in the testjobs.json file.
    @Test
    public void checkJobsHashMapJobs(){
        //Get the jobsHashMap and store it in a HashMap variable
        HashMap<String,String[]> jobsHashMap = multipleIntelligences.getJobsHashMap();

        assertTrue(
                jobsHashMap.get("Spatial")[1].equals("Air Traffic Controller") &&
                        jobsHashMap.get("Spatial")[2].equals("Architect")
        );


    }
}