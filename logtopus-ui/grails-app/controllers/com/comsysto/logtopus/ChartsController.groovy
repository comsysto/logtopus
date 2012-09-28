package com.comsysto.logtopus

import java.text.SimpleDateFormat
import org.apache.commons.lang.time.DateUtils
import grails.converters.JSON
import org.apache.log4j.Priority

class ChartsController {

    static DEBUG = Priority.DEBUG.toString();
    static INFO = Priority.INFO.toString();
    static WARN = Priority.WARN.toString();
    static ERROR = Priority.ERROR.toString();
    static FATAL = Priority.FATAL.toString();

    def index() {
        redirect(url: "/")
    }

    def levelPieChart() {
        def debug = LevelAggregate.findByLevel(DEBUG)
        def info = LevelAggregate.findByLevel(INFO)
        def warn = LevelAggregate.findByLevel(WARN)
        def error = LevelAggregate.findByLevel(ERROR)
        def fatal = LevelAggregate.findByLevel(FATAL)
        [debugCount: debug == null ? 0 : debug.n,
                infoCount: info == null ? 0 : info.n,
                warnCount: warn == null ? 0 : warn.n,
                errorCount: error == null ? 0 : error.n,
                fatalCount: fatal == null ? 0 : fatal.n]
    }

    def distributionBarChart() {
        def incidentCount  = [];
        def slots = [];
        def timeRange = params.get('time', '1h');
        def currentTime = Calendar.getInstance(Locale.GERMAN).getTime();
        def timeUnit = '';

        // TODO: maybe some parsing one day?

        switch (timeRange) {
            case "1h": // default splitting is in 5 minutes steps
                def formatter = new SimpleDateFormat("H:mm");
                def lowerTime1 = DateUtils.addHours(currentTime,-1);
                for (def i = 1; i<=6; i++){
                    def lowerTime2 = DateUtils.addMinutes(lowerTime1, 10);
                    getCountForTimeSpan(incidentCount, lowerTime1, lowerTime2)
                    slots.add(formatter.format(lowerTime1) + " - " + formatter.format(lowerTime2))
                    lowerTime1 = lowerTime2;
                }
                timeUnit = 'minutes';
                break;
            case "1d":
                def formatter = new SimpleDateFormat("H:mm");
                def lowerTime1 = DateUtils.addDays(currentTime,-1);
                for (def i = 1; i<=6; i++){
                    def lowerTime2 = DateUtils.addHours(lowerTime1, 4);
                    getCountForTimeSpan(incidentCount, lowerTime1, lowerTime2)
                    slots.add(formatter.format(lowerTime1) + " bis " + formatter.format(lowerTime2))
                    lowerTime1 = lowerTime2;
                }
                timeUnit = 'hours';
                break;
            case "1w":
                def formatter = new SimpleDateFormat("d.M");
                def lowerTime1 = DateUtils.addWeeks(currentTime,-1);
                for (def i = 1; i<=7; i++){
                    def lowerTime2 = DateUtils.addDays(lowerTime1, 1);
                    getCountForTimeSpan(incidentCount, lowerTime1, lowerTime2)
                    slots.add(formatter.format(lowerTime1) + " bis " + formatter.format(lowerTime2))
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
}
