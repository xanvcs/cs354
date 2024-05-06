# Project 5: Javascript

-   Author: xian ma
-   Class: CS354 Section #002
-   Semester: Spring 2024

## Overview

This program is a port of a java program to javascript. It is a simple
parser that reads in a json file and recursively iterates through the json
structure and finds the specified tag. It will keep track of the number of
occurrences for each tag value in a map and the number of features. The driver
program will output the results of the map in a formatted string to stdout.

## Reflection

This project was good exercise in learning how to use javascript. I have used it once before in CS208 last year. I found the syntax to be very similar to languages I already know. It had a lot of similarities with java's syntax. It felt like it was a blend of python and java, but the worst of both worlds. I found the project to be very straightfoward and easy to implement. I was able to get all the classes setup pretty quickly after I looked into how classes and instance variables are declared in javascript.

The most challenging part of this project was figuring out why the `toString` didn't work when it was called in `driver.js`. I tried debugging with print statements, but there wasn't anything that seemed wrong. Everything that needed to be executed, did. I then deduced it to an issue in `parser.js`. Since
`readFile` executes asynchronously, the `toString` method was being called before the `readFile` was finished. I fixed this with the synchronous version called `readFileSync`.

## Compiling and Using

To run the program,

```
$ node driver.js <path to JSON data file> <tag to search for>
```

To run test script,

```
$ ./run-tests.sh
```

## Results

```
=======================================================================
Running Test ./test-cases/map_boise_close.json
created ./test-cases/map_boise_close.json_test.out
 ---- Test ./test-cases/map_boise_close.json output matches! ----
=======================================================================
Running Test ./test-cases/map_boise_far.json
created ./test-cases/map_boise_far.json_test.out
 ---- Test ./test-cases/map_boise_far.json output matches! ----
=======================================================================
Running Test ./test-cases/map_boisenationalforest.json
created ./test-cases/map_boisenationalforest.json_test.out
 ---- Test ./test-cases/map_boisenationalforest.json output matches! ----
=======================================================================
Running Test ./test-cases/map_desert.json
created ./test-cases/map_desert.json_test.out
 ---- Test ./test-cases/map_desert.json output matches! ----
=======================================================================
Running Test ./test-cases/map_prairie.json
created ./test-cases/map_prairie.json_test.out
 ---- Test ./test-cases/map_prairie.json output matches! ----
=======================================================================
Running Test ./test-cases/map_road.json
created ./test-cases/map_road.json_test.out
 ---- Test ./test-cases/map_road.json output matches! ----
=======================================================================
```

## Sources used

-   [Mozilla JS Class Docs](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes)
-   [Recursively Read File](https://www.youtube.com/watch?v=my4EKQvR2w0)
-   [Command Line Args](https://www.geeksforgeeks.org/how-to-parse-command-line-arguments-in-node-js/)
-   [FileReaderSync](https://developer.mozilla.org/en-US/docs/Web/API/FileReaderSync)

---
