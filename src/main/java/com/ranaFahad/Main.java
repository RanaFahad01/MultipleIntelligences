package com.ranaFahad;

//MAIN DIFFERENCE BETWEEN DEVELOPMENT VERSION AND RELEASE VERSION IS THAT THE CONFIG PATH IS CHANGED FROM AN ABSOLUTE MAVEN DIRECTORY PATH TO A RELATIVE JAR PATH
//IT ALSO REMOVES THE CONFIG FILES INSIDE THE PROJECT IN FAVOR OF CONFIG FILES OUTSIDE THE JAR FILE
public class Main {
    public static void main(String[] args) {

        //Create a new MultipleIntelligences object
        MultipleIntelligences multipleIntelligences = new MultipleIntelligences("config.json");

        //Call the printResults() method
        multipleIntelligences.printResults();



    }
}