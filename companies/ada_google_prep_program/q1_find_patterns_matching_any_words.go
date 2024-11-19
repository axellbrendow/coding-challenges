package main

import "reflect"

func matches(word string, pattern string) bool {
	if len(word) != len(pattern) {
		return false
	}
	for i := 0; i < len(pattern); i++ {
		if word[i] != pattern[i] && pattern[i] != '.' {
			return false
		}
	}
	return true
}

func findPatternsThatMatchAnyWords(words []string, patterns []string) []string {
	patternsThatMatchAnyWords := make([]string, 0, len(patterns))
	for _, pattern := range patterns {
		for _, word := range words {
			if matches(word, pattern) {
				patternsThatMatchAnyWords = append(patternsThatMatchAnyWords, pattern)
				break
			}
		}
	}
	return patternsThatMatchAnyWords
}

func main() {
	if !reflect.DeepEqual(
		findPatternsThatMatchAnyWords([]string{"apple", "aws"}, []string{"a..le", "...", "....", "aws", "aws.", "aw"}),
		[]string{"a..le", "...", "aws"},
	) {
		panic("")
	}
}
