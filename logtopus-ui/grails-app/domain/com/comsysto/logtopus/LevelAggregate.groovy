package com.comsysto.logtopus

import org.bson.types.ObjectId

class LevelAggregate {

    ObjectId id
    String level
    int n

    static constraints = {
    }

    static mapping = {
        collection "levels"
        n attr:"count"
    }

    static mapWith = "mongo"
}
