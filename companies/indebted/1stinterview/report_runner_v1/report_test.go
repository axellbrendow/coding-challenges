package report_runner_v1

import (
	"reflect"
	"testing"
)

/*
what if I receive two accounts with the same id ?
Should I validate AccountID != nil and DaysPastDue between 0,+inf ?
*/

func TestRun(t *testing.T) {
	type args struct {
		accounts []Account
	}
	tests := []struct {
		name string
		args args
		want []Result
	}{
		{
			name: `given accounts with 29, 30, 90, 91, 120, 121 days past their due
when I run the report
then a 10%, 15%, 18%, and 20% commission rate should be returned`,
			args: args{
				accounts: []Account{
					{AccountID: "A001", DaysPastDue: 29},
					{AccountID: "A002", DaysPastDue: 30},
					{AccountID: "A003", DaysPastDue: 90},
					{AccountID: "A004", DaysPastDue: 91},
					{AccountID: "A005", DaysPastDue: 120},
					{AccountID: "A006", DaysPastDue: 121},
				},
			},
			want: []Result{
				{Description: "10% commission rate", Count: 1},
				{Description: "15% commission rate", Count: 2},
				{Description: "18% commission rate", Count: 2},
				{Description: "20% commission rate", Count: 1},
			},
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := Run(tt.args.accounts); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("Run() = %v, want %v", got, tt.want)
			}
		})
	}
}
