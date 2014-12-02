# README

## Environment
1. Mac OS: 10.9.5
2. Intellij 14.0.1
3. Java 8
4. Tomcat: 8.0.15
5. MongoDB

## Instruction
1. Install the above packages are essential
2. Then just import the package in your Intellij
3. Configure the path properly, i.e. tomcat path, java path

## Design
User:

1. Pick a date and website URL

System:

1. If the URL+date has been cached into the memory

       -> If yes, it will render that specific version

       -> If no, it will fetch the data from the database for that specific version

            -> if found, render and load it into memory for future use.

            -> if not found, find the old website version of that specific date.

   i.e.

   1. 19th Sep 2014 will fetch a version from 15th Sep 2014
   2. 7st Sep 2014 will fetch a version from 1th Sep 2014

2. Cache algorithm can various from LRU, MRU, and FIFO. In our case, for the
purpose of simplicity, I will use LinkedHashMap with fixed size 10.

Alternatives:

    1. [Guava Cache](http://goo.gl/PDVz9).
    2. [Apache MemLRU](http://goo.gl/4MLpWJ).

3. Sample NoSQL DB schema is [here](./resource/document.json)

``` bash
mkdir -p ~/data/db
mongod --dbpath ~/data/db
mongo
```

Once in Mongo
``` bash
show dbs
use webs
db

```

Insert DB
``` javascript
var a =  {
    "web": "tripadvisor.com",
    "versions": {
      "11/01/2014": "someContent1",
      "11/02/2014": "someContent2",
      "11/03/2014": "someContent3",
      "11/04/2014": "someContent4",
      "current": "content"
    }
  };
var b = {
    "web": "tripadvisor2.com",
    "versions": {
      "11/01/2014": "someContent1",
      "11/02/2014": "someContent2",
      "11/03/2014": "someContent3",
      "11/04/2014": "someContent4",
      "current": "content"
    }
  };
db.webs.insert(a);
db.webs.insert(b);
db.webs.find();
```

Clean DB
```
db.webs.remove({})
```

## Walk through
There's one assumption I have for crawler will dump the data into the DB.
So I could fetch it from DB for the first time, after the first time fetch.
I have put it into LRU cache. See codes for implementation details.

Anyway, please see [demo](./demo/demo.mp4)


#Reference
[Remove Documents]
(http://docs.mongodb.org/manual/tutorial/remove-documents/)
