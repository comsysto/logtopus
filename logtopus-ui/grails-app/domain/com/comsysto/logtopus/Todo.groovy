package com.comsysto.logtopus

class Todo {

    static constraints = {
    }

    static mapWith = "mongo"

    static mapping = {
        collection "todos"
    }


    String title;
    boolean done;
}
