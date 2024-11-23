package report_runner_v2

import (
	"reflect"
	"testing"
)

func TestNew(t *testing.T) {
	type args struct {
		commissionRates []CommissionRate
		accounts        []Account
	}
	tests := []struct {
		name string
		args args
		want ReportRunner
	}{
		{
			name: "test new",
			args: args{
				commissionRates: nil,
				accounts:        nil,
			},
			want: ReportRunner{
				commissionRates: nil,
				accounts:        nil,
			},
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := New(tt.args.commissionRates, tt.args.accounts); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("New() = %v, want %v", got, tt.want)
			}
		})
	}
}

type commissionRate10 struct{}

func (c commissionRate10) AppliesFor(account Account) bool {
	return account.DaysPastDue >= 0 && account.DaysPastDue < 30
}
func (c commissionRate10) Description() string { return "10% commission rate" }

//

type commissionRate15 struct{}

func (c commissionRate15) AppliesFor(account Account) bool {
	return account.DaysPastDue >= 30 && account.DaysPastDue <= 90
}
func (c commissionRate15) Description() string { return "15% commission rate" }

//

type commissionRate18 struct{}

func (c commissionRate18) AppliesFor(account Account) bool {
	return account.DaysPastDue >= 91 && account.DaysPastDue <= 120
}
func (c commissionRate18) Description() string { return "18% commission rate" }

//

type commissionRate20 struct{}

func (c commissionRate20) AppliesFor(account Account) bool {
	return account.DaysPastDue > 120
}
func (c commissionRate20) Description() string { return "20% commission rate" }

func TestReportRunner_Run(t *testing.T) {
	type fields struct {
		commissionRates []CommissionRate
		accounts        []Account
	}
	tests := []struct {
		name   string
		fields fields
		want   []Result
	}{
		{
			name: "test 1",
			fields: fields{
				commissionRates: []CommissionRate{
					commissionRate20{},
					commissionRate10{},
					commissionRate15{},
					commissionRate18{},
				},
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
			reportRunner := &ReportRunner{
				commissionRates: tt.fields.commissionRates,
				accounts:        tt.fields.accounts,
			}
			if got := reportRunner.Run(); !reflect.DeepEqual(got, tt.want) {
				t.Errorf("ReportRunner.Run() = %v, want %v", got, tt.want)
			}
		})
	}
}
