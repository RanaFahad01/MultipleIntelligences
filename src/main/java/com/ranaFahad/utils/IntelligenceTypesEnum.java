package com.ranaFahad.utils;

// ENUM class that includes all the intelligence types.
// The purpose of this class is to make sure all mentions of intelligence types are within these restricted words.
public enum IntelligenceTypesEnum {
    NATURALIST("Naturalist"),
    MUSICAL("Musical"),
    LINGUISTIC("Linguistic"),
    INTRAPERSONAL("Intrapersonal"),
    LOGICAL("Logical"),
    INTERPERSONAL("Interpersonal"),
    KINESTHETIC("Kinesthetic"),
    SPATIAL("Spatial");

    private final String intelligenceTypeName;

    IntelligenceTypesEnum(String intelligenceTypeNameGiven){
        this.intelligenceTypeName = intelligenceTypeNameGiven;
    }

    public String getIntelligenceTypeName(){
        return this.intelligenceTypeName;
    }

    @Override
    public String toString(){
        return this.intelligenceTypeName;
    }

}
