package report_runner_v2

type Account struct {
	AccountID   string
	DaysPastDue int
}

type CommissionRate interface {
	AppliesFor(account Account) bool
	Description() string
}

type Result struct {
	Description string
	Count       int
}

type ReportRunner struct {
	commissionRates []CommissionRate
	accounts        []Account
}

func New(commissionRates []CommissionRate, accounts []Account) ReportRunner {
	return ReportRunner{
		commissionRates: commissionRates,
		accounts:        accounts,
	}
}

func (reportRunner *ReportRunner) Run() []Result {
	commissionRateMap := map[string]int{}
	for _, account := range reportRunner.accounts {
		for _, commissionRate := range reportRunner.commissionRates {
			if commissionRate.AppliesFor(account) {
				commissionRateMap[commissionRate.Description()]++
				break
			}
		}
	}
	results := []Result{}
	for description, count := range commissionRateMap {
		results = append(results, Result{Description: description, Count: count})
	}
	return results
}
