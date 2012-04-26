package com.comsysto.logtopus

import org.springframework.dao.DataIntegrityViolationException

class LogEventController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [logEventInstanceList: LogEvent.list(params), logEventInstanceTotal: LogEvent.count()]
    }

    def filterList() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [logEventInstanceList: LogEvent.list(params), logEventInstanceTotal: LogEvent.count()]
    }

    def create() {
        [logEventInstance: new LogEvent(params)]
    }

    def save() {
        def logEventInstance = new LogEvent(params)
        if (!logEventInstance.save(flush: true)) {
            render(view: "create", model: [logEventInstance: logEventInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'logEvent.label', default: 'LogEvent'), logEventInstance.id])
        redirect(action: "show", id: logEventInstance.id)
    }

    def show() {
        def logEventInstance = LogEvent.get(params.id)
        if (!logEventInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'logEvent.label', default: 'LogEvent'), params.id])
            redirect(action: "list")
            return
        }

        [logEventInstance: logEventInstance]
    }

    def edit() {
        def logEventInstance = LogEvent.get(params.id)
        if (!logEventInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'logEvent.label', default: 'LogEvent'), params.id])
            redirect(action: "list")
            return
        }

        [logEventInstance: logEventInstance]
    }

    def update() {
        def logEventInstance = LogEvent.get(params.id)
        if (!logEventInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'logEvent.label', default: 'LogEvent'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (logEventInstance.version > version) {
                logEventInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'logEvent.label', default: 'LogEvent')] as Object[],
                          "Another user has updated this LogEvent while you were editing")
                render(view: "edit", model: [logEventInstance: logEventInstance])
                return
            }
        }

        logEventInstance.properties = params

        if (!logEventInstance.save(flush: true)) {
            render(view: "edit", model: [logEventInstance: logEventInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'logEvent.label', default: 'LogEvent'), logEventInstance.id])
        redirect(action: "show", id: logEventInstance.id)
    }

    def delete() {
        def logEventInstance = LogEvent.get(params.id)
        if (!logEventInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'logEvent.label', default: 'LogEvent'), params.id])
            redirect(action: "list")
            return
        }

        try {
            logEventInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'logEvent.label', default: 'LogEvent'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'logEvent.label', default: 'LogEvent'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
