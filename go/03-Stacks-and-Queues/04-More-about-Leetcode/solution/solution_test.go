package solution

import "testing"

func TestIsValid(t *testing.T) {
	assertIsValid := func(t *testing.T, str string, want bool) {
		t.Helper()
		got := IsValid(str)
		if got != want {
			t.Errorf("got:%v, want:%v",got,want)
		}
	}

	assertIsValid(t,"()[]{}",true)
	assertIsValid(t,"{(]}",false)
}
