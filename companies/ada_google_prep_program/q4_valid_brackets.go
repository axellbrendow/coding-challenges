package main

/*-
You are given a string s containing characters from a set of three types of brackets: '(', ')', '{', '}', '[', ']'.
Additionally, the string s contains alphabetic characters. Write an algorithm to determine if the string is
brackets-balanced. A string is bracket-balanced if all brackets are closed in the correct order and the correct
type and there are no orphan brackets.

Example 1
Input: s = "a{b(c)d}e"
Output: True
Explanation: Both brackets open and close in the right sequence.

Example 2
Input: s = "a{bc)d}e[fgh{i}]j"
Output: False
Explanation: There is a closing parenthesis without a corresponding opening one.
*/

func validBrackets(str string) bool {
	stack := make([]byte, 0, len(str))
	for i := 0; i < len(str); i++ {
		if str[i] == '(' || str[i] == '{' || str[i] == '[' {
			stack = append(stack, str[i])
		} else if str[i] == ')' {
			if len(stack) == 0 || stack[len(stack)-1] != '(' {
				return false
			}
			stack = stack[:len(stack)-1]
		} else if str[i] == ']' {
			if len(stack) == 0 || stack[len(stack)-1] != '[' {
				return false
			}
			stack = stack[:len(stack)-1]
		} else if str[i] == '}' {
			if len(stack) == 0 || stack[len(stack)-1] != '{' {
				return false
			}
			stack = stack[:len(stack)-1]
		}
	}
	return len(stack) == 0
}

func main() {
	if validBrackets("") != true {
		panic("")
	}

	if validBrackets("(") != false {
		panic("")
	}

	if validBrackets("()") != true {
		panic("")
	}

	if validBrackets(")") != false {
		panic("")
	}

	if validBrackets("{{}") != false {
		panic("")
	}

	if validBrackets("a{b(c)d}e") != true {
		panic("")
	}

	if validBrackets("a{bc)d}e[fgh{i}]j") != false {
		panic("")
	}
}
