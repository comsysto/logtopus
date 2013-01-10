import grails.converters.JSON
import com.comsysto.logtopus.Todo

class BootStrap {

    def init = {servletContext ->
        JSON.registerObjectMarshaller(Todo.class, {
            def returnArray = [:]
            returnArray['id'] = it.id.toString()
            returnArray['title'] = it.title
            returnArray['done'] = it.done
            return returnArray
        })
    }
    def destroy = {
    }
}
