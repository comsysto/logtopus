package com.comsysto.logtopus

import org.bson.types.ObjectId

class DynamicLogEvent {

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
