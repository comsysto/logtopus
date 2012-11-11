package com.comsysto.logtopus

import org.apache.log4j.Level

 class LogLevelUtil {

    public static String getColorCode(String level){
        switch (level){
            case Level.ERROR.toString():
                return "#f08080" //lightcoral
            case Level.WARN.toString():
                return "#ee905e" //lightorange
            case Level.INFO.toString():
                return "#90ee90" //lightgreen
            case Level.DEBUG.toString():
                return "#d3d3d3" //grey
            default:
                return ""
        }
    }


    public static int levelToPriority(String level){
        Level.toLevel(level).toInt();
    }

    public static final ArrayList<String> logLevels = [
            Level.DEBUG.toString(),
            Level.INFO.toString(),
            Level.WARN.toString(),
            Level.ERROR.toString(),
            Level.FATAL.toString()
    ]
}
