# Project 2: Scheme

* Author: xian ma
* Class: CS354 Section #002
* Semester: spring 2024

## Overview

A scheme program that returns a copy of the inputted list with every element inside duplicated a `n` number of times. If the input is an atom (number, string, symbol, boolean, character), then it will be immediately
returned without duplication.

## Reflection

I really enjoyed doing this project because it was my first interaction with functional programming. I found it very interesting to program in a way where everything is dependent on recursion. I found it very challenging at times to think of solutions due to being stuck in the mindset of imperative programming. I would approach problems with the mindset of loops, but I had to change it to recursion. I found it very rewarding when I was able to come up with a recursive solution to a problem. The project really forced me to break problems down into smaller sub problems.

I struggled with removing the parenthesis from the outputted lists. I understood that `cons` wasn't doing anything that it wasn't told to do but I was still confused on how to resolve it. I used `display` statements to see how each lists were combined together. It gave me a better idea of how `cons` worked. I then thought about it some more and I ended up creating a solution by utilizing `cdr` and `car`. I checked if the first input was a list, if it wasn't I would `cons` it with b. If it was then I would check if it was null. If it wasn't null then I would recursively call the function on the `cdr` of a while `cons`ing the `car` of a with b. If it was null then I would return b.

## Compiling and Using

to test
```
$ (load "test.scm")
```

to run
```
$ (load "super-duper.scm")
```

## Results

```
passed
passed
passed
passed
passed
passed
passed
passed
failed
passed
passed
passed
passed
passed
passed
passed
passed
passed
passed
passed
passed
passed
passed
passed
passed
```

## Sources used

- [Scheme Reference Manual](https://www.gnu.org/software/mit-scheme/documentation/stable/mit-scheme-ref.pdf)
- [Piazza Thread](https://piazza.com/class/lqy2fccxq9o4xf/post/91)
----------
