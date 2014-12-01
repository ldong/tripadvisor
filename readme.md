# README

## Environment
1. Mac OS: 10.9.5
2. Intellij 14.0.1
3. Java 8
4. Tomcat: 8.0.15
5. ~~MySQL 5.6.21~~, Firebase

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

