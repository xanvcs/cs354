// # main.go
// the package where the other packages are tested and ran.
// the outputs of the other packages are tested in this file
package main

import (
	"fmt"
	"local/account"
	a "local/account"
	b "local/bank"
	c "local/customer"
	"sync"
)

func main() {

	bank := *b.NewBank()

	ann := *c.CreateNewCustomer("ann")
	bob := *c.CreateNewCustomer("bob")

	bank.Add(a.NewCheckingAccount(1, ann, 100.00))
	bank.Add(a.NewSavingsAccount(2, ann, 200.00))
	bank.Add(a.NewSavingsAccount(3, bob, 150.00))

	totalInterest := bank.Accrue(0.02)

	fmt.Print(bank.String())
	fmt.Printf("Total Interest: %0.2f\n", totalInterest)

	bobsChecking := account.NewCheckingAccount(4, bob, 0)

	wg := &sync.WaitGroup{}
	mu := &sync.Mutex{}

	wg.Add(1000)

	for i := 0; i < 1000; i++ {
		go bobsChecking.Deposit(1.00, wg, mu)
	}

	wg.Wait()

	fmt.Println(bobsChecking.String())
}
