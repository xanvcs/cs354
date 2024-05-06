// # CheckingAccount.go
// represents a checking account object. It "inherits" the functions from
// account.go
package account

import c "local/customer"

// # ICheckingAccount interface
// the method signatures for CheckingAccount objects
type ICheckingAccount interface {
	IAccount
}

// # CheckingAccount struct
// defines the different fields for a CheckingAccount object
type CheckingAccount struct {
	Account
}

// # NewCheckingAccount()
// creates a new CheckingAccount object
func NewCheckingAccount(number int, customer c.Customer, balance float64) (c *CheckingAccount) {
	c = new(CheckingAccount)
	c.Init(number, customer, balance)
	return
}

// # Init()
// initializes the fields of a CheckingAccount object
func (c *CheckingAccount) Init(number int, customer c.Customer, balance float64) {
	c.Account.Init(number, customer, balance)
}
