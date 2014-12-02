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

Question: why choose **MongoDB** over MySQL?

Because I know NoSQL is distribute-friendly for sure, don't know about SQL.
Also, NoSQL is easier to setup for document based storage, which fits perfectly
for this assignment.

## Setup the DB

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
So I could fetch it from DB for the first time. After the first time fetch,
I have put it into LRU cache to avoid future wasteful operations.

See [codes](./src) for implementation details.

Anyway, please see [demo](./demo/demo.mp4)


#Reference
[Creating a simple Web application and deploying it to Tomcat]
(http://goo.gl/WPcz40)

[Configuring & Using Apache Tomcat]
(http://www.coreservlets.com/Apache-Tomcat-Tutorial/)

[Getting Started with Java Driver]
(http://docs.mongodb.org/ecosystem/tutorial/getting-started-with-java-driver/)

[Inserting and updating]
(http://mongodb.github.io/node-mongodb-native/markdown-docs/insert.html)

[Remove Documents]
(http://docs.mongodb.org/manual/tutorial/remove-documents/)

[db.collection.remove]
(http://docs.mongodb.org/manual/reference/method/db.collection.remove/)
