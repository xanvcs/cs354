// # SavingsAccount.go
// represents a Savings Account object. It "inherits" the functions
// from account.go. It contains the function Accrue() unlike CheckingAccount.go
package account

import c "local/customer"

// # ISavingsAccount interface
// the method signatures for SavingsAccount objects
type ISavingsAccount interface {
	IAccount
}

// # SavingsAccount struct
// defines the different fields for a SavingsAccount object
type SavingsAccount struct {
	Account
	interest float64
}

// # NewSavingsAccount()
// creates a new SavingsAccount object
func NewSavingsAccount(number int, customer c.Customer, balance float64) (s *SavingsAccount) {
	s = new(SavingsAccount)
	s.Init(number, customer, balance)
	return
}

// # Init()
// initializes the fields of a SavingsAccount object
func (s *SavingsAccount) Init(number int, customer c.Customer, balance float64) {
	s.Account.Init(number, customer, balance)
}

// # String()
// returns a string representation of a SavingsAccount object
func (s *SavingsAccount) Accrue(c chan float64, rate float64) {
	s.interest += s.balance * rate
	s.balance += s.balance * rate

	c <- s.interest
}
