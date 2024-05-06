// # bank.go
// represents the bank object. It contains the functions
// necessary to a bank such as accrue and adding accounts
package bank

import (
	a "local/account"
)

// # IBank interface
// the method signatures for bank objects
type IBank interface {
	Add()
	Accrue()
	String()
}

// # Bank struct
// defines the different fields for a bank object
type Bank struct {
	a.Account
	accounts map[*a.IAccount]a.IAccount
}

// # NewBank()
// creates a new map object for a bank
func NewBank() *Bank {
	return &Bank{
		accounts: make(map[*a.IAccount]a.IAccount),
	}
}

// # Add()
// adds an account to the bank map
func (b *Bank) Add(account a.IAccount) {
	b.accounts[&account] = account
}

// # Accrue()
// calculates the total interest of all the accounts in the bank
func (b *Bank) Accrue(rate float64) float64 {
	c := make(chan float64)

	for _, a := range b.accounts {
		go a.Accrue(c, rate)
	}

	totalInterest := 0.0
	for i := 0; i < 2; i++ {
		totalInterest += <-c
	}

	return totalInterest
}

// # String()
// returns a string representation of a bank object
func (b *Bank) String() string {
	var retString string

	for _, a := range b.accounts {
		retString += a.String() + "\n"
	}

	return retString
}
