package com.comsysto.logtopus

import org.apache.log4j.Level
import org.bson.types.ObjectId

class LogEvent {

    public static final ArrayList<String> logLevels = [
            Level.DEBUG.toString(),
            Level.INFO.toString(),
            Level.WARN.toString(),
            Level.ERROR.toString(),
            Level.FATAL.toString()
    ]

    ObjectId id
    String logger
    String level
    String message
    String location
    String hostIp
    String hostName
    String applicationVersion
    String applicationName
    String sha1
    String exceptionName
    List<String> stacktrace
    Date time

    static mapping = {
        collection "logs"
    }

    static constraints = {
    }

    static mapWith = "mongo"

}
