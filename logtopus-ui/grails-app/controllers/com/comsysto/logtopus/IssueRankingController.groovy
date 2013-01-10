package com.comsysto.logtopus

import com.mongodb.BasicDBObject
import com.mongodb.DBCollection
import com.mongodb.DBObject

class IssueRankingController {

    private static final DBObject sorting = new BasicDBObject("n", -1)

    def index() { }


    def top10() {
        // did not find the possibility to limit the result with GORM,
        // so "low" level gmongo API is my friend :)...
        def DBCollection aggregateColl = EventAggregate.collection;
        def top10 = aggregateColl.find().sort((DBObject)sorting).limit(10);
        def result = []

        for (Object o : top10) {
            result.add((EventAggregate) o)
        }
//TODO: Update to 2.2.0 broke that, FIX!
//        def sortedResult = result.sort(new Comparator<EventAggregate>() {
//            @Override
//            int compare(EventAggregate o1, EventAggregate o2) {
//                return LogLevelUtil.levelToPriority(o2.level).compareTo(LogLevelUtil.levelToPriority(o1.level))
//            }
//        })

        [top10List: result]
    }
}
