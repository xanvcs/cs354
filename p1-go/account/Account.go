// # Account.go
// represents an account object. It is the "interface" for SavingsAccount.go and
// CheckingAccount.go. The functions that will be "inherited" in the other files
// are defined with the exception of Accrue().
package account

import (
	"fmt"
	c "local/customer"
	"sync"
)

// # IAccount interface
// the method signatures for account objects
type IAccount interface {
	Balance() float64
	Accrue(c chan float64, rate float64)
	Deposit(amount float64, wg *sync.WaitGroup, mu *sync.Mutex)
	Withdraw(amount float64)
	String() string
}

// # Account struct
// defines the different fields for an account object
type Account struct {
	number   int
	customer c.Customer
	balance  float64
}

// # NewAccount()
// creates a new account object
func NewAccount(number int, customer c.Customer, balance float64) (a *Account) {
	a = new(Account)
	a.Init(number, customer, balance)
	return
}

// # Init()
// initializes the fields of an account object
func (a *Account) Init(number int, customer c.Customer, balance float64) {
	a.number = number
	a.customer = customer
	a.balance = balance
}

// # Balance()
// returns the balance of an account object
func (a *Account) Balance() float64 {
	return a.balance
}

// # Accrue()
// empty function body; later used in SavingsAccount.go
func (a *Account) Accrue(c chan float64, rate float64) {
}

// # Deposit()
// deposits money into an account object
func (a *Account) Deposit(amount float64, wg *sync.WaitGroup, mu *sync.Mutex) {
	defer wg.Done()
	defer mu.Unlock()

	mu.Lock()
	a.balance += amount
}

// # Withdraw()
// withdraws money from an account object
func (a *Account) Withdraw(amount float64) {
	a.balance -= amount
}

// # String()
// returns a string representation of an account object
func (a *Account) String() string {
	return fmt.Sprintf("%d: %s: %0.2f", a.number, a.customer.String(), a.Balance())
}
