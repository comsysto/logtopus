package com.comsysto.logtopus

import org.bson.types.ObjectId

class Aggregate {
    ObjectId id
    int n
    String applicationName
    String exceptionName
    String level


    static mapping = {
        collection "aggregates"
    }

    static constraints = {
    }

    static mapWith = "mongo"
}
