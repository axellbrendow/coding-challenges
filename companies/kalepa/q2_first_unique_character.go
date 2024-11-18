package main

func getUniqueCharacter(s string) int {
	count := make(map[byte]int)
	for i := 0; i < len(s); i++ {
		count[s[i]] += 1
	}
	for i := 0; i < len(s); i++ {
		if count[s[i]] == 1 {
			return i + 1
		}
	}
	return -1
}

func main() {
	if getUniqueCharacter("falafal") != -1 {
		panic("")
	}

	if getUniqueCharacter("statistics") != 3 {
		panic("")
	}

	if getUniqueCharacter("hackthegame") != 3 {
		panic("")
	}
}
