package com.comsysto.logtopus.log4j;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.WriteConcern;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Calendar;

/**
 * @author Benjamin Steinert
 */
public class Log4JMongoDBAppender extends AppenderSkeleton {

    protected String host = "localhost";
    protected int port = 27017;
    protected String databaseName = "logtopus";

    // not yet in use...
    //protected String userName;
    //protected String password;

    protected String applicationName = "";

    protected String collectionPattern = "%c";
    protected PatternLayout collectionLayout = new PatternLayout(collectionPattern);

    protected Mongo mongo;
    protected DB database;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    private void connect() throws UnknownHostException {
        this.mongo = new Mongo(host, port);
        this.database = mongo.getDB(databaseName);
    }

    public Log4JMongoDBAppender() {
        super();
        loadConnectionProperties();
    }

    private void loadConnectionProperties() {
        //TODO: Load MongoDB credentials from an additional file.
    }

    @Override
    protected void append(LoggingEvent event) {

        checkDB();

        DBObject dbObject = new BasicDBObject();

        dbObject.put("logger", event.getLogger().getName());
        dbObject.put("level", event.getLevel().toString());
        dbObject.put("message", event.getMessage());

        appendApplicationName(dbObject);
        appendTimeStamp(event, dbObject);
        appendStackTrace(event, dbObject);

        database.getCollection("logs").insert(dbObject, WriteConcern.NORMAL);
    }

    private void appendApplicationName(DBObject dbObject) {
        if (null != applicationName) {
            dbObject.put("app", applicationName);
        }
    }

    private void appendTimeStamp(LoggingEvent event, DBObject dbObject) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(event.getTimeStamp());
        dbObject.put("time", calendar.getTime());
    }

    private void appendStackTrace(LoggingEvent event, DBObject dbObject) {
        String[] stackTrace = event.getThrowableStrRep();

        if (notEmpty(stackTrace)) {
            BasicDBList list = new BasicDBList();
            list.addAll(Arrays.asList(stackTrace));
            dbObject.put("stacktrace", list);
        }
    }

    private boolean notEmpty(String[] stackTrace) {
        return null != stackTrace && stackTrace.length > 0;
    }

    private void checkDB() {
        if (null == database) {
            try {
                connect();
            } catch (UnknownHostException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }

    @Override
    public void close() {
        mongo.close();
    }

    @Override
    public boolean requiresLayout() {
        return true;
    }
}
