(load "super-duper.scm")

; tests the super-duper function
(define (test-super-duper source count expected)
    (if (equal? (super-duper source count) expected)
        (display "passed")
        (display "failed")))

; provided test inputs
(test-super-duper 123 1 123) (newline)
(test-super-duper 123 2 123) (newline)

(test-super-duper '() 1 '()) (newline)
(test-super-duper '() 2 '()) (newline)

(test-super-duper '(x) 1 '(x)) (newline)
(test-super-duper '(x) 2 '(x x)) (newline)

(test-super-duper '(x y) 1 '(x y)) (newline)
(test-super-duper '(x y) 2 '(x x y y)) (newline)

; currently fails
(test-super-duper '((a b) y) 3 '((a a a b b b) (a a a b b b) (a a a b b b) y y y)) (newline)

; additonal test inputs
(test-super-duper '(x y z) 1 '(x y z)) (newline)
(test-super-duper '(x y z) 2 '(x x y y z z)) (newline)

(test-super-duper '(a b c d) 1 '(a b c d)) (newline)
(test-super-duper '(a b c d) 2 '(a a b b c c d d)) (newline)

(test-super-duper '(a b c d e) 1 '(a b c d e)) (newline)
(test-super-duper '(a b c d e) 2 '(a a b b c c d d e e)) (newline)

(test-super-duper '(m i l o) 1 '(m i l o)) (newline)
(test-super-duper '(m i l o) 2 '(m m i i l l o o)) (newline)

; tests duplicate
(define (test-duplicate source count expected)
    (if (equal? (duplicate source count) expected)
        (display "passed")
        (display "failed")))

(test-duplicate '(z) 1 '(z)) (newline)
(test-duplicate '(z) 2 '(z z)) (newline)

(test-duplicate '(z c) 1 '(c z)) (newline)
(test-duplicate '(z c) 2 '(c z c z)) (newline)

; tests mycons
(define (test-mycons a b expected)
    (if (equal? (mycons a b) expected)
        (display "passed")
        (display "failed")))

(test-mycons 'x 'y '(x . y)) (newline)
(test-mycons '(x) '(y) '(x y)) (newline)
(test-mycons '(x y) '(z) '(y x z)) (newline)
(test-mycons '(x y y) '(z y y) '(y y x z y y)) (newline)
