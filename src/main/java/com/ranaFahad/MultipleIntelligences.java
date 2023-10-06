package com.ranaFahad;

//The class where most of the program logic exists.

//The main fields in this class will be:
// 1. A hashmap of type <String,Int> which stands for "Intelligence type name" and "Score for that intelligence type"
//    respectively.
// 2. Arraylist of type PersonalityScoreObject which will be created from the hashmap before
// 3. HashMap of type <String,String[]> for jobs
// 4. A static ObjectMapper for dealing with JSON data. It's static since we only need one.

//Methods:
// 0. Constructor(String configFilepath):
//// First, it opens the config.json file and stores it in a String variable.
//// Then, it stores the answersFilePath and jobsFilePath in their respective variables.
//// Then, it calls the setResultsHashMap() method (which will then call the setPersonalityScoreObjects() method).
//// Finally, it calls the loadIntelligencesInfo() method which loads the jobs and their descriptions from their file
//// - into the jobs hash map.

// 1. setResultsHashMap(String answersFilePath):
//// This method takes the path of the file where the answers are stored, then opens the file in a try with resources,
//// then for each line, it reads the name of the
//// intelligence name and its corresponding score, then makes an entry for it in the HashMap.
//// Then it calls the setPersonalityScoreObjects() method.

// 1.5. setResultsHashMap's helper function "int choiceScore(int choiceNumber)"
//// This method takes a choice number from 1 to 5 (inclusive) and returns -10, -5, 0, 5, 10 respectively.


// 2. setPersonalityScoreObjects():
//// This method takes the hash map and creates personality score objects out of it for each key:value pair, then puts
//// them in the arraylist.
//// It calls the Collections.sort() method to sort that arraylist using the custom overridden compareTo() method
//// implemented in the PersonalityScoreObject class.

// 3. loadIntelligencesInfo(String jobsFilePath):
//// This method takes the jobsFilePath as argument, then loads the json object from there into a variable.
/// Then, for each key in the json object, it loads it and its jobs array into the jobs Hash Map.

// 4. printResults():
//// EXAMPLE OUTPUT:
//// HERE IS YOUR RESULT FOR THE MULTIPLE INTELLIGENCES APTITUDE TEST:
//// 1. Natural: 85%
//// "You can distinguish among, classify and use features of your environment. Gardeners, florists and geologists have
//// this intelligence about nature."
//// Here are some jobs that may interest you:
//// (Tab separated list of jobs, max 4 per line)

//// HOW IT WORKS:
//// Prints the first line
//// Initialize a counter variable to 1.
//// Create temporary variables for a String List iterator as well as a string List.
//// Starts a for-each loop for each PersonalityScoreObject in the arraylist for them.
////// For each PersonalityScoreObject, print the counter variable, print the name of the PersonalityScoreObject
////// Then it prints percentage right next to it as seen in the example output.
////// Then it uses the PersonalityScoreObject name as a key for the jobs HashMap and loads
////// - the corresponding job info array into memory. It then calls the .asList() method to convert it into a List, so we can get an iterator from it
////// Then it takes the iterator from that temporary List variable and loads it into the temporary Iterator variable.
////// Then it prints the job description, and starts printing the jobs next using a for loop
////// The loop is basically a for(n=0...3) loop nested inside a while(temparray.hasNext()) which adds a NewLine every
////// 4 iterations.

//ERROR CATCHING NOTE: EVERY NORMAL EXCEPTION RESULTS IN A NEW UNCAUGHT RUNTIMEEXCEPTION BEING THROWN BECAUSE I WANT THE PROGRAM EXECUTION TO STOP
//THE SECOND AN EXCEPTION ARISES
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ranaFahad.utils.PersonalityScoreObject;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class MultipleIntelligences {

    //The fields
    private HashMap<String,Integer> resultsHashMap;
    private ArrayList<PersonalityScoreObject> personalityScoreObjects;
    private HashMap<String,String[]> jobsHashMap;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    //Constructor
    public MultipleIntelligences(String configFilePath){
        //Open the config.json file and store it in a JsonNode variable
        JsonNode configJSONFile;
        try {
            configJSONFile = objectMapper.readTree(new String(Files.readAllBytes(Paths.get(configFilePath))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Store the answersFilePath and jobsFilePath in their respective variables
        String answersFilePath = configJSONFile.get("answersFilePath").asText();
        String jobsFilePath = configJSONFile.get("jobsFilePath").asText();


        //Call the setResultsHashMap() method and pass in the answersFilePath
        setResultsHashMap(answersFilePath);
        
        //Call the loadIntelligencesInfo() method which loads the intelligences descriptions + jobs
        loadIntelligencesInfo(jobsFilePath);
        
    }

    //setResultsHashMap for setting the resultsHashMap HashMap
    private void setResultsHashMap(String answersFilePath){

        //Create an empty HashMap for the resultsHashMap field
        resultsHashMap = new HashMap<>();     //HashMap is of type <String,Integer>

        //Open the answers file in a Scanner object, in a try with resources block
        try(Scanner scanner = new Scanner(new File(answersFilePath)))
        {
            //Temporary variables
            String currentLine;     //Stores the scanner.nextLine() value
            String[] currentLineSplitByColons;   //Current line split by colons
            String[] choiceNumbersSplitByDashes;     //Choice numbers split by dashes
            ArrayList<Integer> scoreNumbers;  //Resulting array when the choice numbers are replaced by their corresponding scores
            int sumOfScores;    //Sum of the scores of the questions for each Intelligence Type
            String intelligenceTypeName;

            scanner.nextLine();     //Skips the first line

            while(scanner.hasNextLine())    //Goes through all the lines in the answers file
            {
                currentLine = scanner.nextLine();


                //Use the .substring() method to cut off the last character in the line, then .split to split it by colons.
                currentLineSplitByColons = currentLine
                        .substring(0,currentLine.length()-1)    //Cuts off the last character (#)
                        .split(":");    //Splits the line by colons, then returns a String array of the pieces

                //Split the numbers by the dashes between them
                choiceNumbersSplitByDashes = currentLineSplitByColons[2].split("-");
                
                //Throw an error if the number of choice numbers is less than or greater than 10
                    if (choiceNumbersSplitByDashes.length != 10) {
                        throw new RuntimeException("ERROR. NUMBER OF CHOICES MUST BE 10 PER INTELLIGENCE TYPE!");
                    }

                //Initialize the score number ArrayList to an empty ArrayList
                scoreNumbers = new ArrayList<>();   //ArrayList is of type <Integer>

                //Replace each choice number in the array with their corresponding score numbers
                for(String choice: choiceNumbersSplitByDashes)
                {
                    scoreNumbers.add(choiceScore(choice));
                }

                //Calculate the sum of that ArrayList
                sumOfScores = scoreNumbers.stream().mapToInt(Integer::intValue).sum();
                //Add 100 to the sumOfScores since that's where we start counting the score from
                sumOfScores += 100;

                //Assign the intelligence type name
                intelligenceTypeName = currentLineSplitByColons[1];

                resultsHashMap.put(intelligenceTypeName,sumOfScores);
            }
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        //Call the setPersonalityScoreObjects method to initialize the Personality Score Object ArrayList
        setPersonalityScoreObjects();

    }

    //Helper function choiceScore(int choiceNumber)
    private int choiceScore(String choiceNumber){

        return switch (choiceNumber) {
            case "1" -> -10;
            case "2" -> -5;
            case "3" -> 0;
            case "4" -> 5;
            case "5" -> 10;
            default -> throw new RuntimeException("ERROR. INVALID CHOICE NUMBER!");
        };


    }

    //setPersonalityScoreObjects for going through the Hash Map, creating personality score objects and putting them
    // - in the array
    private void setPersonalityScoreObjects(){

        //Initialize the arraylist
        personalityScoreObjects = new ArrayList<>();  //ArrayList is of type <PersonalityScoreObject>

        //Temporary PersonalityScoreObject variable
        PersonalityScoreObject tempPersonalityScoreObject;


        //Goes through all the keys in the results Hash Map
        for(String key: resultsHashMap.keySet())
        {
            //Create a PersonalityScoreObject using the Hash Map data
            tempPersonalityScoreObject = new PersonalityScoreObject(key,resultsHashMap.get(key));
            personalityScoreObjects.add(tempPersonalityScoreObject);
        }

        //Sort the ArrayList using the custom compareTo() method implemented in it
        Collections.sort(personalityScoreObjects);
    }

    //loadIntelligencesInfo for opening the jobs file and loading information for all intelligences into the
    //-jobsHashMap
    private void loadIntelligencesInfo(String jobsFilePath){
        //The concept:
        ////For each intelligence, the function will request the JsonNode object for its corresponding array.
        ////It will take an iterator from that array and then pass the first item into the intelligencesInformation ArrayList<String>
        ////It will take the second item, run the .split(",") function on it to split it by commas, then store it in a jobsSplitByCommas array
        ////For each value in that array, it gets put into the ArrayList.
        ////A new entry in the HashMap is made with the arguments (currentIntelligenceTheLoopIsIteratingOver, theArrayObtainedUsingThe.toArrayMethodOnTheArrayList)

        //Initialize the jobsHashMap to a new HashMap<String,String[]>
        jobsHashMap = new HashMap<>();

        //Open the jobs JSON file, use readTree and put it in a JsonNode object
        //JsonNode.get("{IntelligenceTypeName}") will return the array
        try {

            //Array of the eight intelligence types
            final String[] arrayOfIntelligences = {"Naturalist","Musical","Linguistic","Intrapersonal","Logical","Interpersonal","Kinesthetic","Spatial"};

            //Load the JSON file in
            File jobsJSONFile = new File(jobsFilePath);

            //Read it into a JsonNode object using the objectMapper created above
            JsonNode jsonNode = objectMapper.readTree(jobsJSONFile);

            //Temporary variables for the loop
            Iterator<JsonNode> jsonNodeIterator;
            ArrayList<String> intelligencesInformation;
            String[] jobsSplitByCommas;

            //For each intelligence in the array of intelligences:
            for(String intelligence: arrayOfIntelligences){
                //Initialize the intelligencesInformation ArrayList to an empty ArrayList
                intelligencesInformation = new ArrayList<>();   //Of type <String>

                //Request the JsonNode object for the iterator to the array corresponding to the current intelligence
                jsonNodeIterator = jsonNode.get(intelligence).elements();

                //Add the first element of the jsonNodeIterator (The intelligence description) into the intelligencesInformation ArrayList
                intelligencesInformation.add(jsonNodeIterator.next().asText());

                //Take the second item, run the .split(",") function on it to split it by commas, then store it in the jobsSplitByCommas array
                jobsSplitByCommas = jsonNodeIterator.next()
                        .asText()
                        .split(",");

                //For each value in jobsSplitByCommas, it gets put into the intelligencesInformation ArrayList
                Collections.addAll(intelligencesInformation, jobsSplitByCommas);

                //A new entry in the HashMap is made with the arguments (currentIntelligenceTheLoopIsIteratingOver, theArrayObtainedUsingThe.toArrayMethodOnTheArrayList)
                jobsHashMap.put(intelligence,
                        intelligencesInformation.toArray(new String[0]));

            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }


    }


    //printResults() function that prints results according to the description given above
    public void printResults(){

        //Print first line from example output
        System.out.println("\nHERE IS YOUR RESULT FOR THE MULTIPLE INTELLIGENCES APTITUDE TEST:\n");

        //Initialize the counter variable to 1
        int counter = 1;

        //Temporary variables
        List<String> jobsList;
        Iterator<String> jobsIterator;

        //For each personalityScoreObject in the sorted personality score objects ArrayList
        for(PersonalityScoreObject personalityScoreObject: personalityScoreObjects)
        {
            //Print the first line of results as seen in the example input above
            System.out.println(counter++ + ". " + personalityScoreObject.getName() + ": " + personalityScoreObject.getScorePercentage() + "%");

            //Use the personalityScoreObject name as a key in the HashMap to get an array in return
            //Then use the Arrays.asList() method on it to get a list
            jobsList = Arrays.asList(jobsHashMap.get(personalityScoreObject.getName()));

            //Get the iterator from that list
            jobsIterator = jobsList.iterator();

            //Print the intelligence description (the first member of the list)
            System.out.println(jobsIterator.next() + "\n");

            System.out.println("Here are some professions you might be interested in pursuing:");

            //Loop starts for printing the jobs according to the explanation above
            while(jobsIterator.hasNext()){
                System.out.println("-" + jobsIterator.next());
            }
            System.out.println("-------------------\n");
        }


    }






    //UNIT TESTING METHODS
    //Three unit tests:
    //1. Check if number of keys in the hash map is 8.
    //2. Check if number of personality score objects in the ArrayList is 8.
    //3. Check if the score of the 5th personality score object in the arraylist is less than or equal to  the first but more than or equal to the last.
    //4. Check if the description for Naturalist checks out. Check if the jobs for Linguistic check out.
    public int testPersonalityAmount(String query)
    {
        //For the test: "Check if number of keys in the hash map is 8."
        if(query.equals("HashMap")){
            return resultsHashMap.keySet().size();
        }

        //For the test: "Check if the number of personality score objects in the PSC ArrayList is 8."
        else if(query.equals("ArrayList")){
            return personalityScoreObjects.size();
        }
        else{
            return 0;
        }
    }

    public ArrayList<PersonalityScoreObject> getPersonalityScoreObjects()
    {
        //For the test: "Check if the score of the 5th personality score object in the arraylist is less than or equal to  the first but more than or equal to the last."
        return this.personalityScoreObjects;
    }

    //For the test: "Check if the description for Naturalist checks out. Check if the jobs for Linguistic check out."
    public HashMap<String,String[]> getJobsHashMap()
    {
        return jobsHashMap;
    }

}
