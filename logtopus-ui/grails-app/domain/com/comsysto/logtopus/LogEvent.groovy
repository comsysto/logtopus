package com.comsysto.logtopus

import org.bson.types.ObjectId

class LogEvent {

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

    Date time

    static mapping = {
        collection "logs"
    }

    static constraints = {
    }

    static def allLogLevels() {
        ['DEBUG', 'INFO', 'WARN', 'ERROR', 'FATAL']
    }
}
