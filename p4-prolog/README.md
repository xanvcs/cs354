# Project 4: Prolog

-   Author: xian ma
-   Class: CS354 Section #002
-   Semester: spring 2024

## Overview

The prolog program finds the overlapping slots between schedules. The overlapping slots are more than zero in length. The program will
return the overlapping slots in the form of a list. The list is truncated with the uniq formatter in order to ensure that all list elements are unique. In other words, the program will select a set of acceptable meeting times for a set of people based on the provided dataset.

## Reflection

The prolog project challenged me to think differently about how I would write code. I thought Prolog was extremely unique and interesting to work with. It felt alien to me and I felt like how I felt when I first started learning programming. I felt like ripping my hair out at every step when I encountered a problem. The way objects were defined in the code forced me to have to think about the problem in a different way. I thought the process of debugging the program went well. Once I found out about "trace" and "spy", I was able to see where the program was failing and why. This allowed me to quickly go to the part of the code that the error appeared from and fix it. The only challenge that I faced in using this method was that I had to manually move through each step. Some problems were too large to be able to manually go through which posed a problem.

One of the problems that I encountered was figuring out how to compare the times. I had initially thought about using the typical relational operators, however, that did not work for "am vs pm". I then found out that you can compare the strings with "@<,@>". Another problem that I encountered was figuring out how to properly use "all_free" to iterate and compare times. I modeled it after the "sum.pl" example, but I wasn't sure how to properly use it in the code. After some research and looking at Piazza, I was able implement the necessary code in the predicate, "meet" and output the correct results. The last problem that I encountered was figuring out how to return a value. I couldn't wrap my head around it for a while and I struggled to figure this out. I didn't know how the program would know what to return.

## Compiling and Using
to get into gprolog
```
$ gprolog
```

to run meet.pl (program)
```
| ?- [meet].
```

to run meetone.pl
```
| ?- [meetone].
```

to run test.pl
```
| ?- [test].
```
## Results

```
[slot(time(8,0,am),time(8,30,am)),slot(time(10,0,am),time(10,15,am)),slot(time(8,0,pm),time(8,30,pm))]
```

## Sources used

- [Piazza Post About Iteration](https://piazza.com/class/lqy2fccxq9o4xf/post/268)
- [GNU Prolog Manual](http://www.gprolog.org/manual/gprolog.html)
- [Prolog Iteration Examples](https://www-users.york.ac.uk/~sjh1/courses/L334css/complete/complete2su6.html)

---
