package com.comsysto.logtopus

import org.bson.types.ObjectId

class LogEvent {

    ObjectId id

    String name
    String level
    String message
    String applicationId
    Date timestamp

    static constraints = {
    }
}
