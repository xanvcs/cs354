#!/bin/gprolog --consult-file

:- include('data.pl').

% checks if time1 is less than or equal to time2
lte(time(Hr1, Min1, Meridiem1), time(Hr2, Min2, Meridiem2)) :-
	% checks for am vs pm
	(Meridiem1 @< Meridiem2); 
	(Meridiem1 = Meridiem2, Hr1 < Hr2); 
	(Meridiem1 = Meridiem2, Hr1 = Hr2, Min1 =< Min2).

% checks if there is overlap in the two time slots
overlaps(slot(Start, End), slot(PersonStart, PersonEnd)) :-
	% ensures meeting doesnt start after person free time ends
	lte(Start, PersonEnd),
	% ensures meeting doesnt end before person free time starts
	lte(PersonStart, End),
	% ensures meeting does not start at end of person free time
	Start \== PersonEnd.

% finds if a person is free during a time slot
meetone(Person, slot(Start, End)) :-
	% checks if person is free
	free(Person, slot(PersonStart, PersonEnd)),
	% checks if there is overlap
	overlaps(slot(Start, End), slot(PersonStart, PersonEnd)).

main :- findall(Person,
		meetone(Person,slot(time(8,30,am),time(8,45,am))),
		People),
	write(People), nl, halt.

:- initialization(main).
