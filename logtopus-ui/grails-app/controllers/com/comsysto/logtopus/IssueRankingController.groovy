package com.comsysto.logtopus

class IssueRankingController {

    def index() { }


    def top10() {
        // did not find the possibility to limit the result with GORM,
        // so low level API is my friend :)...
        //def DBCollection aggregateColl = Aggregate.collection;
        //def top10 = aggregateColl.find().limit(3).sort(({ n : -1 }));

//        for (Object o : top10) {
//            def cast = o as Aggregate
//            println(cast)
//        }

       // def top10 = Aggregate.createCriteria()
        //def result = top10.order("n", "desc")
        [top10List: Aggregate.list()]
    }
}
