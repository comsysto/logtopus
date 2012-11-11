package com.comsysto.logtopus

import org.apache.log4j.Level
import org.bson.types.ObjectId

class EventAggregate {

    ObjectId id
    int n
    String applicationName
    String exceptionName
    String level

    // TODO: Use codes application-wide
    public String getColorCode(){
        switch (level){
            case Level.ERROR.toString():
                return "#f08080" //lightcoral
            case Level.WARN.toString():
                return "#ee905e" //lightorange
            case Level.INFO.toString():
                return "#90ee90" //lightgreen
            case Level.DEBUG.toString():
                return "#d3d3d3" //grey
            default:
                return ""
        }
    }

    static mapping = {
        collection "aggregates"
    }

    static constraints = {
    }

    static mapWith = "mongo"
}
