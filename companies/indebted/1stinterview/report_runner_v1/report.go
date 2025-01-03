package report_runner_v1

type Account struct {
	AccountID   string
	DaysPastDue int
}

type Result struct {
	Description string
	Count       int
}

var commissionRateDescriptions = []string{
	"10% commission rate",
	"15% commission rate",
	"18% commission rate",
	"20% commission rate",
}

func Run(accounts []Account) []Result {
	commissionRateMap := map[string]int{}
	for _, account := range accounts {
		if account.DaysPastDue < 30 {
			commissionRateMap["10% commission rate"]++
		} else if account.DaysPastDue <= 90 {
			commissionRateMap["15% commission rate"]++
		} else if account.DaysPastDue <= 120 {
			commissionRateMap["18% commission rate"]++
		} else {
			commissionRateMap["20% commission rate"]++
		}
	}
	results := []Result{}
	for _, description := range commissionRateDescriptions {
		count, exists := commissionRateMap[description]
		if exists {
			results = append(results, Result{Description: description, Count: count})
		}
	}
	return results
}
