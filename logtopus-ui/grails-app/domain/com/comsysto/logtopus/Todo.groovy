package com.comsysto.logtopus

import org.bson.types.ObjectId

class Todo {

    ObjectId id
    String title;
    boolean done;

    static constraints = {
    }

    static mapWith = "mongo"

    static mapping = {
        collection "todos"
    }
}
