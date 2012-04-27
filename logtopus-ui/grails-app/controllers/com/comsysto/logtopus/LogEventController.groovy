package com.comsysto.logtopus

import grails.converters.JSON
import org.apache.commons.lang.time.DateUtils
import org.apache.log4j.Priority
import org.springframework.dao.DataIntegrityViolationException

class LogEventController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    static DEBUG = Priority.DEBUG.toString();
    static INFO = Priority.INFO.toString();
    static WARN = Priority.WARN.toString();
    static ERROR = Priority.ERROR.toString();
    static FATAL = Priority.FATAL.toString();


    def index() {
        redirect(action: "filterList", params: params)
    }

    def levelPieChart() {
        def debug = LogEvent.countByLevel(DEBUG)
        def info = LogEvent.countByLevel(INFO)
        def warn = LogEvent.countByLevel(WARN)
        def error = LogEvent.countByLevel(ERROR)
        def fatal = LogEvent.countByLevel(FATAL)
        [debugCount: debug, infoCount: info, warnCount:warn, errorCount:error, fatalCount:fatal]
    }

    def distributionBarChart() {
        def incidentCount  = [];
        def slots = [];
        def timeRange = params.get('time', '1d');
        def currentTime = Calendar.getInstance(Locale.GERMAN).getTime();
        def Date lowerTime2;
        def timeUnit;
        // TODO: maybe some parsing one day?

        switch (timeRange) {
            case "1h": // default splitting is in 5 minutes steps
                def lowerTime1 = DateUtils.addHours(currentTime,-1);
                for (def i = 1; i<12; i++){
                    lowerTime2 = DateUtils.addMinutes(lowerTime1, i*5);
                    getCountForTimeSpan(incidentCount, lowerTime1, lowerTime2)
                    slots.add(lowerTime1.getTimeString() + " - " + lowerTime2.getTimeString())
                    lowerTime1 = lowerTime2;
                }
                timeUnit = 'minutes';
                break;
            case "1d":
                def lowerTime1 = DateUtils.addDays(currentTime,-1);
                for (def i = 1; i<12; i++){
                    lowerTime2 = DateUtils.addHours(lowerTime1, i*2);
                    getCountForTimeSpan(incidentCount, lowerTime1, lowerTime2)
                    slots.add(lowerTime1.getTimeString() + " - " + lowerTime2.getTimeString())
                    lowerTime1 = lowerTime2;
                }
                timeUnit = 'hours';
                break;
            case "1w":
                def lowerTime1 = DateUtils.addWeeks(currentTime,-1);
                for (def i = 1; i<7; i++){
                    lowerTime2 = DateUtils.addDays(lowerTime1, i);
                    getCountForTimeSpan(incidentCount, lowerTime1, lowerTime2)
                    slots.add(lowerTime1.getDateString() + " - " + lowerTime2.getDateString())
                    lowerTime1 = lowerTime2;
                }
                timeUnit = 'days';
                break;
        };

        [countOverTime: incidentCount , timeUnit: timeUnit , timeSlots: slots as JSON]
    }

    private void getCountForTimeSpan(ArrayList incidentCount, Date lowerTime1, Date lowerTime2) {
        incidentCount.add(LogEvent.countByLevelAndTimeBetween(DEBUG, lowerTime1, lowerTime2));
        incidentCount.add(LogEvent.countByLevelAndTimeBetween(INFO, lowerTime1, lowerTime2));
        incidentCount.add(LogEvent.countByLevelAndTimeBetween(WARN, lowerTime1, lowerTime2));
        incidentCount.add(LogEvent.countByLevelAndTimeBetween(ERROR, lowerTime1, lowerTime2));
        incidentCount.add(LogEvent.countByLevelAndTimeBetween(FATAL, lowerTime1, lowerTime2));
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [logEventInstanceList: LogEvent.list(params), logEventInstanceTotal: LogEvent.count()]
    }

    def filterList() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        //keep these values so we can rerender on the filters
        flash.level = params.level
        flash.hostName = params.hostName
        flash.applicationVersion = params.applicationVersion
        flash.applicationName = params.applicationName
        flash.logMessage = params.logMessage

        def query
        def criteria = LogEvent.createCriteria()
        def results
        query = {
            and {
                like ("level", params.level + '%')
                like ("hostName", params.hostName + '%')
                like ("applicationVersion", params.applicationVersion + '%')
                like ("applicationName", params.applicationName + '%')
                like ("message", params.logMessage + '%')
//                if(params.level){
//                    eq('level', params.level )
//                }
            }
        }
        results = criteria.list(params, query)

        [logEventInstanceList: results]
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
            redirect(action: "filterList")
            return
        }

        [logEventInstance: logEventInstance]
    }

    def edit() {
        def logEventInstance = LogEvent.get(params.id)
        if (!logEventInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'logEvent.label', default: 'LogEvent'), params.id])
            redirect(action: "filterList")
            return
        }

        [logEventInstance: logEventInstance]
    }

    def update() {
        def logEventInstance = LogEvent.get(params.id)
        if (!logEventInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'logEvent.label', default: 'LogEvent'), params.id])
            redirect(action: "filterList")
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
            redirect(action: "filterList")
            return
        }

        try {
            logEventInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'logEvent.label', default: 'LogEvent'), params.id])
            redirect(action: "filterList")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'logEvent.label', default: 'LogEvent'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
