package com.comsysto.logtopus

import org.bson.types.ObjectId

class LogEvent {

    ObjectId id

    String name
    String level
    String message

    static constraints = {
    }
}
