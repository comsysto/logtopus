package com.comsysto.logtopus



import org.junit.*
import grails.test.mixin.*

@TestFor(LogEventController)
@Mock(LogEvent)
class LogEventControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/logEvent/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.logEventInstanceList.size() == 0
        assert model.logEventInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.logEventInstance != null
    }

    void testSave() {
        controller.save()

        assert model.logEventInstance != null
        assert view == '/logEvent/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/logEvent/show/1'
        assert controller.flash.message != null
        assert LogEvent.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/logEvent/list'


        populateValidParams(params)
        def logEvent = new LogEvent(params)

        assert logEvent.save() != null

        params.id = logEvent.id

        def model = controller.show()

        assert model.logEventInstance == logEvent
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/logEvent/list'


        populateValidParams(params)
        def logEvent = new LogEvent(params)

        assert logEvent.save() != null

        params.id = logEvent.id

        def model = controller.edit()

        assert model.logEventInstance == logEvent
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/logEvent/list'

        response.reset()


        populateValidParams(params)
        def logEvent = new LogEvent(params)

        assert logEvent.save() != null

        // test invalid parameters in update
        params.id = logEvent.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/logEvent/edit"
        assert model.logEventInstance != null

        logEvent.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/logEvent/show/$logEvent.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        logEvent.clearErrors()

        populateValidParams(params)
        params.id = logEvent.id
        params.version = -1
        controller.update()

        assert view == "/logEvent/edit"
        assert model.logEventInstance != null
        assert model.logEventInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/logEvent/list'

        response.reset()

        populateValidParams(params)
        def logEvent = new LogEvent(params)

        assert logEvent.save() != null
        assert LogEvent.count() == 1

        params.id = logEvent.id

        controller.delete()

        assert LogEvent.count() == 0
        assert LogEvent.get(logEvent.id) == null
        assert response.redirectedUrl == '/logEvent/list'
    }
}
