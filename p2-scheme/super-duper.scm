(define (super-duper source count)
  (if (not (pair? source))
      source
      ; if source is not an atom then perform duplication
      (mycons (duplicate (car source) count) (super-duper (cdr source) count))))

(define (duplicate source count)
    (if (= count 0)
        '()
        (mycons source (duplicate source (- count 1)))))

(define (mycons a b)
  (if (list? a)
      ; makes sure cdr is not empty
      (if (null? a)
          ; if so, return b
          b
          ; if not, cons cdr
          (mycons (cdr a) (cons (car a) b)))
      ; if a is not a list, cons a and b
      (cons a b)))
