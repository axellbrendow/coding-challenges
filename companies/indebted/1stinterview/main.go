package main

import (
	"fmt"
	"goaxell/report_runner_v2"
)

/*
Commission Rate
Problem description
The collection commission rate of an account based on their due date.

If account is less than 30 days since it is due -> commission 10%
If account is between 30 - 90 days since it is due -> commission 15%
If account is between 91 - 120 days since it is due -> commission 18%
If account is above 120 days since it is due -> commission 20%
Print a list with the number of accounts receiving 10%, 15%, 18% and 20% commission

For example, given the input:
{
  "accounts": [
    {
      "AccountID": "A001",
      "DaysPastDue": 30
    },
    {
      "AccountID": "A002",
      "DaysPastDue": 29
    },
    {
      "AccountID": "A003",
      "DaysPastDue": 67
    },
    {
      "AccountID": "A004",
      "DaysPastDue": 100
    },
    {
      "AccountID": "A005",
      "DaysPastDue": 273
    },
    {
      "AccountID": "A006",
      "DaysPastDue": 120
    },
    {
      "AccountID": "A007",
      "DaysPastDue": 101
    },
    {
      "AccountID": "A008",
      "DaysPastDue": 97
    },
    {
      "AccountID": "A009",
      "DaysPastDue": 55
    },
    {
      "AccountID": "A0010",
      "DaysPastDue": 1000
    }
  ]
}
We should produce the following information:

10% commission rate: 1
15% commission rate: 3
18% commission rate: 4
20% commission rate: 2
*/

type commissionRate10 struct{}

func (c commissionRate10) AppliesFor(account report_runner_v2.Account) bool {
	return account.DaysPastDue >= 0 && account.DaysPastDue < 30
}
func (c commissionRate10) Description() string { return "10% commission rate" }

//

type commissionRate15 struct{}

func (c commissionRate15) AppliesFor(account report_runner_v2.Account) bool {
	return account.DaysPastDue >= 30 && account.DaysPastDue <= 90
}
func (c commissionRate15) Description() string { return "15% commission rate" }

//

type commissionRate18 struct{}

func (c commissionRate18) AppliesFor(account report_runner_v2.Account) bool {
	return account.DaysPastDue >= 91 && account.DaysPastDue <= 120
}
func (c commissionRate18) Description() string { return "18% commission rate" }

//

type commissionRate20 struct{}

func (c commissionRate20) AppliesFor(account report_runner_v2.Account) bool {
	return account.DaysPastDue > 120
}
func (c commissionRate20) Description() string { return "20% commission rate" }

//

func main() {
	reportRunner := report_runner_v2.New(
		[]report_runner_v2.CommissionRate{
			commissionRate20{},
			commissionRate10{},
			commissionRate15{},
			commissionRate18{},
		},
		[]report_runner_v2.Account{
			{AccountID: "A001", DaysPastDue: 29},
			{AccountID: "A002", DaysPastDue: 30},
			{AccountID: "A003", DaysPastDue: 90},
			{AccountID: "A004", DaysPastDue: 91},
			{AccountID: "A005", DaysPastDue: 120},
			{AccountID: "A006", DaysPastDue: 121},
		},
	)

	fmt.Printf("%#v\n", reportRunner.Run())
}
