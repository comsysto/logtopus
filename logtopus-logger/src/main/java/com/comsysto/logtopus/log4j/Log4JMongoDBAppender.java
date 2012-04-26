package com.comsysto.logtopus.log4j;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.WriteConcern;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Properties;

/**
 * Appender for enabling log event persistence to MongoDB.
 * Supports various fields to be stored with the documents.
 *
 * @author Benjamin Steinert
 */
@SuppressWarnings("UnusedDeclaration")
public class Log4JMongoDBAppender extends AppenderSkeleton {

    private String host = "localhost";
    private int port = 27017;
    private String databaseName = "logtopus";
    private String applicationName;
    private String hostName;
    private String ipAddress;
    private String version;

    private Mongo mongo;
    private DB database;

    // not yet in use...
    private String userName;
    private String password;


    public Log4JMongoDBAppender() {
        super();
        loadConnectionProperties();
        lookupMachineDetails();
        lookupApplicationDetailsFromManifest();
    }

    /**
     * Define host address of the MongoDB server.
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * Define port MongoDB is listening to.
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Returns application name either retrieved from MANIFEST.MF of set via log4j config.
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * Returns version of the logging application. Either fetched from manifest
     * or set via log4j configuration.
     */
    public String getVersion() {
        return version;
    }

    /**
     * Version of the application, using this Appender.
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * The owning application identifier.
     */
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    /**
     * Returns MongoDB database name used for storing log entries.
     */
    public String getDatabaseName() {
        return databaseName;
    }

    /**
     * This database name is used when connecting to MongoDB.
     */
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    /**
     * Username for MongoDB authentication.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Password to be used for connecting to defined MongoDB database.
     */
    public void setPassword(String password) {
        this.password = password;
    }


    private void connect() throws UnknownHostException {
        mongo = new Mongo(host, port);
        database = mongo.getDB(databaseName);
        if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)){
            System.out.println("Logtopus initialized with development credentials for MongoDB.");
        } else {
            boolean authenticated = database.authenticate(userName, password.toCharArray());
            if(!authenticated){
                throw new RuntimeException("Invalid username/password for logtopus database!");
            }
        }
    }

    private void lookupApplicationDetailsFromManifest() {

        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream("/META-INF/MANIFEST.MF"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        version = properties.getProperty("version", version);
        applicationName = properties.getProperty("applicationName", applicationName);

    }

    private void lookupMachineDetails() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            hostName = localHost.getHostName();
            ipAddress = localHost.getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }


    private void loadConnectionProperties() {
        //TODO: Load MongoDB credentials from an additional file.
        userName = "";
        password = "";
    }

    @Override
    protected void append(LoggingEvent event) {

        checkDB();

        DBObject dbObject = new BasicDBObject();

        dbObject.put("logger", event.getLogger().getName());
        dbObject.put("level", event.getLevel().toString());
        dbObject.put("location", event.getLocationInformation().fullInfo);
        dbObject.put("message", event.getRenderedMessage());
        dbObject.put("host.ip", ipAddress);
        dbObject.put("host.name", hostName);
        appendVersion(dbObject);
        appendApplicationName(dbObject);
        appendTimeStamp(event, dbObject);
        appendStackTrace(event, dbObject);
        appendSha1(event, dbObject);

        database.getCollection("logs").insert(dbObject, WriteConcern.NORMAL);
    }

    /**
     * NEVER CHANGE ORDER OF ELEMENTS! THIS CAUSES A SHA MISMATCH WITH ALREADY EXISTING LOG ENTRIES!
     * FIX ORDER IS:
     *  - app name
     *  - log level
     *  - error message
     *  - topmost exception (if existing))
     *  - topmost exception occurrence (if existing))
     */
    private void appendSha1(LoggingEvent event, DBObject dbObject) {
        StringBuilder hashString = new StringBuilder();
        /*1*/hashString.append(applicationName);
        /*2*/hashString.append(event.getLevel().toString());
        /*3*/hashString.append(event.getRenderedMessage());

        String[] throwableStrRep = event.getThrowableStrRep();
        if(notEmpty(throwableStrRep)){
            /*4*/hashString.append(throwableStrRep[0]);
            /*5*/hashString.append(throwableStrRep[1]);
        }

        byte[] hash = DigestUtils.sha(hashString.toString());
        String shaString = new String(Hex.encodeHex(hash));
        dbObject.put("sha1", shaString);
    }

    private void appendVersion(DBObject dbObject) {
        if(StringUtils.isNotEmpty(version)){
            dbObject.put("app.version",version);
        }
    }

    private void appendApplicationName(DBObject dbObject) {
        if (StringUtils.isNotEmpty(applicationName)) {
            dbObject.put("app.name", applicationName);
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
        if (database == null) {
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
