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

    public static final String LOGGER_FIELD_KEY = "logger";
    public static final String LEVEL_FIELD_KEY = "level";
    public static final String MESSAGE_FIELD_KEY = "message";
    public static final String HOST_IP_FIELD_KEY = "hostIp";
    public static final String HOST_NAME_FIELD_KEY = "hostName";
    public static final String APPLICATION_VERSION_FIELD_KEY = "applicationVersion";
    public static final String APPLICATION_NAME_FIELD_KEY = "applicationName";
    public static final String LOCATION_FIELD_KEY = "location";
    public static final String SHA_1_FIELD_KEY = "sha1";
    public static final String TIME_FIELD_KEY = "time";
    public static final String STACKTRACE_FIELD_KEY = "stacktrace";
    public static final String EXCEPTION_NAME_FIELD_KEY = "exceptionName";
    private boolean logLocation = false;

    private String host = "localhost";
    private int port = 27017;
    private String databaseName = "logtopus";
    private String applicationName = "undefined";
    private String hostName;
    private String ipAddress;
    private String version = "undefined";
    private String buildNumber = "";

    private Mongo mongo;
    private DB database;
    private String userName;
    private String password;

    private String versionKey;
    private String appNameKey;
    private String buildNrKey;


    @Override
    public void activateOptions() {
        super.activateOptions();
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

    public String getVersionKey() {
        return versionKey;
    }

    public void setVersionKey(String versionKey) {
        this.versionKey = versionKey;
    }

    public String getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(String buildNumber) {
        this.buildNumber = buildNumber;
    }

    public String getAppNameKey() {
        return appNameKey;
    }

    public void setAppNameKey(String appNameKey) {
        this.appNameKey = appNameKey;
    }

    public String getBuildNrKey() {
        return buildNrKey;
    }

    public void setBuildNrKey(String buildNrKey) {
        this.buildNrKey = buildNrKey;
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
     * <p>Set to true, if you need full code location info in the log messages.
     * <b>Very inefficient!</b> Don't use it if runtime matters... </p>
     * default = false
     */
    public void setLogLocation(boolean logLocation) {
        this.logLocation = logLocation;
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
            System.out.println("[LOGTOPUS] Logtopus initialized with development credentials for MongoDB.");
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
            properties.load(ClassLoader.getSystemResourceAsStream("META-INF/MANIFEST.MF"));

            if(StringUtils.isNotBlank(versionKey)){
                version = properties.getProperty(versionKey, version);
            }
            if(StringUtils.isNotBlank(appNameKey)){
                applicationName = properties.getProperty(appNameKey, applicationName);
            }
            if(StringUtils.isNotBlank(buildNrKey)){
                buildNumber = properties.getProperty(buildNrKey, buildNumber);
            }
        } catch (IOException e) {
            System.out.println("[LOGTOPUS] No MANIFEST.MF found for reading Application Version, Name and Build Number. Please set these values manually. ");
        }
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
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream("/logtopus.properties"));
            userName = properties.getProperty("username", "");
            password = properties.getProperty("password", "");
        } catch (Exception e) {
            userName = "";
            password = "";
        }
    }

    @Override
    protected void append(LoggingEvent event) {

        checkDB();

        String level = event.getLevel().toString();
        String shaString = createSha1(event);

        DBObject logEntry = new BasicDBObject();
        logEntry.put(LOGGER_FIELD_KEY, event.getLogger().getName());
        logEntry.put(LEVEL_FIELD_KEY, level);
        logEntry.put(MESSAGE_FIELD_KEY, event.getRenderedMessage());
        logEntry.put(HOST_IP_FIELD_KEY, ipAddress);
        logEntry.put(HOST_NAME_FIELD_KEY, hostName);
        logEntry.put(APPLICATION_VERSION_FIELD_KEY, version);
        logEntry.put(APPLICATION_NAME_FIELD_KEY, applicationName);
        logEntry.put(SHA_1_FIELD_KEY, shaString);

        appendLocation(event, logEntry);
        appendTimeStamp(event, logEntry);
        appendStackTrace(event, logEntry);

        DBObject aggregate = new BasicDBObject();
        aggregate.put(SHA_1_FIELD_KEY, shaString);
        aggregate.put(APPLICATION_NAME_FIELD_KEY, applicationName);
        aggregate.put(LEVEL_FIELD_KEY, level);
        aggregate.put(EXCEPTION_NAME_FIELD_KEY, logEntry.get(EXCEPTION_NAME_FIELD_KEY));
        DBObject increment = new BasicDBObject("n", 1);
        BasicDBObject update = new BasicDBObject("$inc",increment);

        database.getCollection("logs").insert(logEntry, WriteConcern.NORMAL);
        database.getCollection("aggregates").update(aggregate, update, true, false);

    }

    private void appendLocation(LoggingEvent event, DBObject dbObject) {
        if(logLocation){
            dbObject.put(LOCATION_FIELD_KEY, event.getLocationInformation().fullInfo);
        }
    }

    /**
     * NEVER CHANGE ORDER OF ELEMENTS! THIS CAUSES A SHA MISMATCH WITH ALREADY EXISTING LOG ENTRIES!
     * FIX ORDER IS:
     *  - app name
     *  - log level
     *  - error message
     *  - topmost exception name (if existing)
     *  - topmost exception occurrence (if existing))
     */
    private void appendSha1(LoggingEvent event, DBObject dbObject) {
        String shaString = createSha1(event);
        dbObject.put(SHA_1_FIELD_KEY, shaString);
    }

    private String createSha1(LoggingEvent event) {
        StringBuilder hashString = new StringBuilder();
        /*1*/
        hashString.append(applicationName);
        /*2*/
        hashString.append(event.getLevel().toString());
        /*3*/
        hashString.append(event.getRenderedMessage());

        String[] throwableStrRep = event.getThrowableStrRep();
        if(notEmpty(throwableStrRep)){
            /*4*/hashString.append(extractExceptionName(throwableStrRep[0]));
            /*5*/hashString.append(throwableStrRep[1].trim());
        }

        byte[] hash = DigestUtils.sha(hashString.toString());
        return new String(Hex.encodeHex(hash));
    }


    private void appendTimeStamp(LoggingEvent event, DBObject dbObject) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(event.getTimeStamp());
        dbObject.put(TIME_FIELD_KEY, calendar.getTime());
    }

    private void appendStackTrace(LoggingEvent event, DBObject dbObject) {
        String[] stackTrace = event.getThrowableStrRep();
        if (notEmpty(stackTrace)) {
            BasicDBList list = new BasicDBList();
            list.addAll(Arrays.asList(stackTrace));
            dbObject.put(STACKTRACE_FIELD_KEY, list);

            String exceptionName = extractExceptionName(stackTrace[0]);
            dbObject.put(EXCEPTION_NAME_FIELD_KEY, exceptionName);
        }
    }

    private String extractExceptionName(String topStackEntry) {
        String fullPackagePath = StringUtils.substringBefore(topStackEntry, ":");
        String[] packageSplit = StringUtils.split(fullPackagePath, ".");
        return packageSplit[packageSplit.length - 1];
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
