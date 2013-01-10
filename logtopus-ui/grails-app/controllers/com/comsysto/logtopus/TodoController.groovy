package com.comsysto.logtopus
import grails.converters.JSON
import org.bson.types.ObjectId

class TodoController {

    static allowedMethods = [getTodos: "GET", save: "POST", done: "POST"]

    def getTodos() {
        render Todo.findAllByDone(false) as JSON
    }


    def save() {
        def todo = new Todo(title : request.JSON.title, done : false)
        todo.save()
        getTodos()
    }

    def done() {
        def todo = Todo.get( new ObjectId(params.id) )
        if (todo){
            todo.done = true
            todo.save()
        }
        render(status: 200)
    }

}