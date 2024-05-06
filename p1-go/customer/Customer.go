// # Customer.go
// represents a customer object and the functions necessary for a customer
package customer

// # ICustomer interface
// the method signatures for customer objects
type ICustomer interface {
	Customer(name string)
	String() string
}

// # Customer struct
// defines the different fields for a customer object
type Customer struct {
	name string
}

// # CreateNewCustomer()
// creates a new customer object
func CreateNewCustomer(name string) (c *Customer) {
	c = new(Customer)
	c.Init(name)
	return
}

// # Init()
// initializes the fields of a customer object
func (c *Customer) Init(name string) {
	c.name = name
}

// # String()
// returns a string representation of a customer object
func (c *Customer) String() string {
	return c.name
}
