#!/bin/gprolog --consult-file

:- include('meet.pl').

% tests the lte function in meet.pl
test_lte(Time1, Time2, TestName) :-
	lte(Time1, Time2),
	format('~w: ~w <= ~w~n', [TestName, Time1, Time2]), nl.

% tests the meetone function in meet.pl
test_meetone(Slot, TestName) :-
	setof(Person, meetone(Person, Slot), AvailablePeople),
	(
		AvailablePeople = [] -> 
		format('~w: No one is available for ~w~n', [TestName, Slot]); 
		format('~w: ~w is available for ~w~n', [TestName, Slot, AvailablePeople])
	), nl.

% tests all_free function in meet.pl
test_all_free(People, Slot, TestName) :-
	(all_free(People, Slot),
	format('~w: Everyone is available for ~w~n', [TestName, Slot]);
	format('~w: Not everyone is available for ~w~n', [TestName, Slot])),
	nl.

% tests the overlaps function in meet.pl
test_overlaps(Slot1, Slot2, ExpectedOverlap, TestName) :-
    (   overlaps(Slot1, Slot2, ActualOverlap)
    ->  (   ActualOverlap = ExpectedOverlap
        ->  format('~w: PASS - ~w overlaps with ~w, resulting in ~w~n', [TestName, Slot1, Slot2, ActualOverlap])
        ;   format('~w: FAIL - Expected overlap ~w, but got ~w~n', [TestName, ExpectedOverlap, ActualOverlap])
        )
    ),
    nl.

% tests the meet function in meet.pl
test_meet(Slot) :-
	meet(Slot),
	format('Everyone is available for ~w~n', [Slot]),
	nl.

main :-
	test_lte(time(8,30,am), time(8,45,am), 'lte'),
	test_lte(time(8,30,am), time(8,30,am), 'lte'),
	test_lte(time(3,50,pm), time(9,29,pm), 'lte'),

	test_meetone(slot(time(8,0,am), time(9,0,am)), 'test meetone'),
	test_meetone(slot(time(8,0,pm), time(8,30,pm)), 'test meetone'),
	test_meetone(slot(time(10,0,am), time(10,15,am)), 'test meetone'),
	test_meetone(slot(time(8,0,am), time(8,30,am)), 'test meetone'),

	test_all_free([ann, bob, carla], slot(time(9,30,pm), time(10,15,pm)), 'test all_free'),
	test_all_free([ann, bob, carla], slot(time(8,0,am), time(8,30,am)), 'test all_free'),
	test_all_free([ann, bob, carla], slot(time(10,0,am), time(10,15,am)), 'test all_free'),
	test_all_free([ann, bob, carla], slot(time(8,0,pm), time(8,30,pm)), 'test all_free'),
	test_all_free([ann, bob, carla], slot(time(8,20,pm), time(8,26,pm)), 'test all_free'),

	test_overlaps(slot(time(8,0,am), time(9,0,am)), slot(time(8,30,am), time(9,0,am)), slot(time(8,30,am), time(9,0,am)), 'test overlaps'),
	
	% PersonStart - End
	test_overlaps(slot(time(7,30,am), time(9,0,am)), slot(time(8,0,am), time(9,30,am)), slot(time(8,0,am), time(9,0,am)), 'test overlaps'),
	% Start - PersonEnd
	test_overlaps(slot(time(8,0,am), time(9,0,am)), slot(time(7,30,am), time(8,30,am)), slot(time(8,0,am), time(8,30,am)), 'test overlaps'),
	% PersonStart - PersonEnd
	test_overlaps(slot(time(7,0,am), time(9,0,am)), slot(time(8,30,am), time(8,45,am)), slot(time(8,30,am), time(8,45,am)), 'test overlaps'),
	% Start - End
	test_overlaps(slot(time(8,0,am), time(9,0,am)), slot(time(7,30,am), time(9,30,am)), slot(time(8,0,am), time(9,0,am)), 'test overlaps'),

	% tests for 8:00am - 8:30am
	test_meet(slot(time(8,0,am), time(8,30,am))),
	% tests for 8:00pm - 8:30pm
	test_meet(slot(time(8,0,pm), time(8,30,pm))),
	% tests for 10:00am - 10:15am
	test_meet(slot(time(10,0,am), time(10,15,am))),
	% since all predicates pass, then meet is correct

	halt.

:- initialization(main).
