package subsubpkg

import (
	"testing"
)

func TestImportName(t *testing.T) {
	tests := []struct {
		name string
		want string
	}{
		{name: "should return module path", want: "goaxell/subpkg/subsubpkg"},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if got := ImportName(); got != tt.want {
				t.Errorf("ImportName() = %v, want %v", got, tt.want)
			}
		})
	}
}
