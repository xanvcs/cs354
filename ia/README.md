# Project 2: Interpreter Assignment Part 3

* Author: xian ma
* Class: CS354 Section # 002
* Semester: spring 2024

## Overview

#### ia part 1
The program is the scanning portion of an interpreter. It scans for identifiers,
numbers, and operaters. Identifiers can be any string that does not start with a
number or illegal characters. Numbers can be integers or floating point numbers.
Operators consists of '+', '-', '*', '/', ';', '(', ')'. When any of the aforementioned
things are scanned, it is treated as a token. Whitespace and comments are ignored by the interpreter.

#### ia part 2
The program acts as the parsing portion of an interpreter. It parses an entered string and
parses out the string into the grammar provided. It will then break down the grammar. The parser will
account for SyntaxErrors and order of operations that are associated with parenthesis and PEMDAS.

#### ia part 3
The program acts as a functioning interpreter. It will take either a string with an assignment, i.e. 'x = 3' or an expr that starts with 'wr' such as 'wr 3 + 4'. The program will then evaluate the expression and return the result. The program will also account for SyntaxErrors and order of operations that are associated with parenthesis and PEMDAS.

#### ia part 4
The program acts as a functioning interpreter. It will take either a string with an assignment, i.e. 'x = 3' or an expr that starts with 'wr' such as 'wr 3 + 4'. The program will also evaluate "if then", "if then else", "while do", "begin end", and read from system in. In additional to the aforementioned, the program contains relational operators and previous features.

## Reflection

#### ia part 1
The first instance where I really struggled was figuring out to implement identifiers to only
accept numbers if they aren't the first character. I tried many solutions before coming to one where I would
consume a set of letters before consuming a set of letters and numbers. This allowed for use of numbers anywhere
within the string. Without the set of letters and numbers, I ran into issues with it recognizing a letter after a
number in the identifier. The second instance where I struggled was implementing the floating point numbers. I had
tried a solution much like the identifier one, but I wasn't able to get it to work.

I really enjoyed learning about how to write the scanner for an interpreter. It was a really interesting experience
that allowed me to learn more about using the scanner and how it can be manipulated to ignore certain characters while
parsing others. I also really liked learning about how to implement methods within the scanner. I had never really
thought about how the functions worked under the "hood". Being able to write unit tests was great experience since it gave me more practice. The less desirable part of the project was just getting back into the groove of programming in Java again.

#### ia part 2
Part 2 of the interpreter assignment was really fun in my opinion. I enjoyed working on the tests and reading out the parse trees. It was
interesting to see how different expressions would lead to certain outcomes in the parse trees. The way the format string was written
made it really simple to intuitively move down the tree to check how the expr expanded. The parentheses issue that I encountered was also
interesting in that it taught me that the problem may lie elsewhere in the code and not what my intuition may say. There wasn't anything that I would consider less desirable in the project since the entirety of part 2 taught me a lot about debugging and writing unit tests.

I really struggled with approaching part 2 of the project at first. It felt imtimidating since there were a lot of things to implement. As I went through the project and referenced the other files, I got a better understanding of how everything worked. I didn't really understand how the node.NodeFact subclasses worked at first. I wasn't quite sure how I would create something for each production of fact. I had thought that I would use a string for representing an id and an num for representing a num. After inspecting the parse tree examples provided in the assignment documentation, I realized that I had to use a token due to the output of "id" being <id, x>.

#### ia part 3
Part 3 of the interpreter assignment was extremely fun to work on. I really enjoyed tinkering with the program to figure out solutions to issues. The tests were extremely helpful in determining what was going wrong and it helped drive the point home of why there is a prevalence of test driven development in the tech industry. Before the ia assignment, I never really employed test driven development nor did I find it all that useful when I did. After the assignment, I can see why it is so important to have tests for your code. It really helps to see where the code failed and at what steps. It felt good to know that I intuitively knew where the issue might be based on what part of the evaluation failed. There wasn't anything that I found undesirable in the project. Part 3 taught me a lot about how to approach a problem and how to debug it.

I really struggled with implementing the evaluation methods at first because I wasn't able to wrap my head around the idea of what to evaluate. I believe it was an issue that stemmed from overthinking it. I was able to resolve it after I took a break. Part of my debugging involved me eliminating things that could be wrong. I updated my `syntax.Scanner.java` to the latest file in CS354's repository. I redid my methods and I stumbled a little on figuring out how to reimplement what the past method had done with new advance method. I was able to figure it out after a little bit of tinkering. Another instance where I struggled was figuring out how to implement node.NodeStmt in the same way I did with node.NodeFact and its subclasses. Like with evaluation, I couldn't wrap my head around it at first. I overthought about how to do it and I overcomplicated things.

#### ia part 4
Part 4 of the interpreter assignment like the previous parts was fun to do since I got to add more features into the interpreter. I enjoyed debugging and figuring out the minute issues that my program had. Since this part of the assignment was similar to the previous parts, I was able to get through initial part quickly. I was able to write all the code that was required. I even refactored certain sections of `Scanner.java` when I saw better approaches to a certain method in the provided ia3 solution. Whenever I encountered an issue I used techniques that I developed and used in previous parts of the interpreter assignment. I didn't find anything undesirable in the project. Part 4 helped reinforce how to approach a problem and how to debug it.

I really struggled with implementing the relational operators at first. I didn't know how to account for the double characters. Since I got stuck for a while, I went with the more "bruteforce" solution. When I encountered the first character, I would match it and then use if statements to check for the next character. This solution worked well and passed tests, however, during the process of writing tests I was able to come up with a solution to finding the relational operators in one go. The new approach allowed for easier detection of relational operators when writing tests for the scanner. This solution involved modifying the way that the method nextOp in `Scanner.java` worked. I was able to get the new approach to work and pass tests. Another instance where I struggled was figuring out how to have the system.in instance not close. I read on piazza that we should aim to have the system.in instance be the same one for all calls to NodeRd and after some deliberation and trial and error I was able to figure out how to do it.

#### ia part 5
Part 5 of the interpreter assignment stumped me at first. I wasn't quite sure what to do. I started off with the tasks list that was provided in the assignment document, however, I wasn't sure if there were any addition code that I had to write outside of "add keyword 'var' to scanner", "add parseDeclare()", and "update the environment". I was able to implement the tasks quickly, however, I ran into the issue of confusing my data structures. For some reason I had thought that a stack was FIFO when in reality it is LIFO so I had initially stored my scope maps in a queue. I was able to resolve the issue by taking a break from the project. The second issue that I ran into was due to the confusing where each new scope started. I had completely glossed over the fact that the assignment document stated that a new scope began at each "begin" keyword and ended at each "end" keyword. It took me a bit of debugging with print statements, the debugger, and piazza before I was able to figure out where the issue was. I then went into `NodeBeginEnd` and added in the scope creation methods. I removed them from `NodeBlock` and the tests started working.

I thought this part of the interpreter assignment was really fun. I learned a lot about how static scope works by implementing it myself. Like the other parts, I enjoyed debugging and figuring out the errors that my program had. The one that took me the longest to fix was the issue with creating the scope maps at the wrong place. This one was interesting since there were no glaring issues with the code. I had to really think about where the issue might be and I had to use the debugger to figure out where the issue was. My first attempt at fixing it didn't work since I didn't remove the scope creation code from `NodeBlock`. I didn't find anything undesirable in the project. Part 5 helped reinforce how to approach a problem and how to debug it. I also learned that stack in Java inherits methods from `java.util.List` so you can iterate through it instead of popping and putting it back on the stack when searching.

## Compiling and Using

To Compile

```
$ javac *.java

```

To Run
```
$ java Interpreter.java
```

## Results

## Sources used

[Searching Stack](https://docs.oracle.com/javase/6/docs/api/java/util/Stack.html)

----------