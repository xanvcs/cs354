# Project 1: Go

* Author: xian ma
* Class: CS354 Section # 002
* Semester: spring 2024

## Overview

A port of a banking application from Java to Go. It allows for the creation both a checking and savings account alongside the functions that each require. The application can calculate interest, deposit, withdraw, check the balance.

## Reflection

The first instance that I struggled when porting the Java code to Go was trying to figure out why my packages weren't being recognized. After doing some digging, I was able to realize that the issue was because there was no `go.mod` in my project directory. I quickly fixed this through running the command, `go mod init local`. The other instance where I really struggled was trying to figure out how the channel worked. I wasn't quite sure how to utilize it properly even after looking at the `sleepsort.go` file. After taking a break and letting it sit, I was able to figure out how to utilize the channel in a way that would work for this particular project.

I really enjoyed learning Go and experimenting with it. It taught me a lot that I otherwise wouldn't have learned by just reading material. I thought it was really interesting that Go employed two ways to declare a variable. I could either declare it with a type or have the compiler infer it. The less desirable part of using Go in this project was getting into the mindset of putting the "instance" variables into a struct at the beginning of the file.

## Compiling and Using

To compile
```
$ go build
```
To run
```
$ go run main.go
```
To run test script
```
$ ./run-test.sh
```

## Results

```
1: ann: 100.00
2: ann: 204.00
3: bob: 153.00
Total Interest: 7.00
4: bob: 1000.00
```

## Sources used

- [Video Explaining GoRoutine and Channels](https://www.youtube.com/watch?v=LGVRPFZr548)
- [Video Explaining sync.WaitGroup](https://www.youtube.com/watch?v=IHpOuqNwZE8)
- [StackOverflow for GoRoutines Not All Completing](https://stackoverflow.com/questions/38980565/why-does-this-waitgroup-sometimes-not-wait-for-all-goroutines)
- [GoLang Documentation](https://go.dev/doc/)

----------