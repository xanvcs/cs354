#!/bin/gprolog --consult-file

:- include('data.pl').
:- include('uniq.pl').

% checks if time1 is less than or equal to time2
lte(time(Hr1, Min1, Meridiem1), time(Hr2, Min2, Meridiem2)) :-
	% checks for am vs pm
	(Meridiem1 @< Meridiem2);
    (Meridiem1 = Meridiem2, Hr1 < Hr2);
	(Meridiem1 = Meridiem2, Hr1 = Hr2, Min1 =< Min2).

% checks if there is partial overlap and returns the overlap slot
% |--- slot 1 ----|
%	   |--- slot 2 ---|
overlaps(slot(Start, End), slot(PersonStart, PersonEnd), slot(PersonStart, End)) :-
    lte(Start, PersonStart),
    lte(PersonStart, End),
    lte(End, PersonEnd),
    PersonStart \== End.

% checks if there is partial overlap and returns the overlap slot
%       |--- slot 1 ----|
% |--- slot 2 ---|
overlaps(slot(Start, End), slot(PersonStart, PersonEnd), slot(Start, PersonEnd)) :-
    lte(PersonStart, Start),
    lte(Start, PersonEnd),
    lte(PersonEnd, End),
    Start \== PersonEnd.

% checks if there is full overlap and returns the overlap slot
% |--------- slot 1 ---------|
%       |--- slot 2 ---|
overlaps(slot(Start, End), slot(PersonStart, PersonEnd), slot(PersonStart, PersonEnd)) :-
    lte(Start, PersonStart),
    lte(PersonStart, End),
    lte(PersonEnd, End),
    PersonStart \== PersonEnd.

% checks if there is full overlap and returns the overlap slot
%       |--- slot 1 ---|
% |--------- slot 2 ---------|
overlaps(slot(Start, End), slot(PersonStart, PersonEnd), slot(Start, End)) :-
    lte(PersonStart, Start),
    lte(Start, PersonEnd),
    lte(End, PersonEnd),
    Start \== End.

% if timeslot is free for everyone in list
all_free([Person | Rest], Slot) :-
	meetone(Person, Slot),
	all_free(Rest, Slot).
all_free([], _).

% finds if a person is free during a time slot
meetone(Person, slot(Start, End)) :-
    free(Person, PersonSlot),
    overlaps(PersonSlot, slot(Start, End), slot(Start, End)).

% finds a time slot where there is overlap
meet(Slot) :-
    people(People),
    member(Person1, People),
    member(Person2, People),
    Person1 \== Person2,
    free(Person1, Slot1),
    free(Person2, Slot2),
    overlaps(Slot1, Slot2, Slot),
    all_free(People, Slot).

people([ann,bob,carla]).

main :- findall(Slot, meet(Slot), Slots),
        uniq(Slots, Uniq),
        write(Uniq), nl, halt.

:- initialization(main).