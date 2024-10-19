def find_element1(arr):
	nums_map = {}

	for elem in arr:
		nums_map[elem] = elem in nums_map

	for k in nums_map:
		if not nums_map[k]:
			return k

def find_element2(arr):
    for i in range(len(arr)):
        found = False
        for j in range(len(arr)):
            if j == i: continue
            if arr[i] == arr[j]:
                found = True
                break
        if not found:
            return arr[i]

def find_element3(arr):
    return 2 * sum(set(arr)) - sum(arr)

def find_element4(arr):
	value = 0
	for elem in arr:
		value ^= elem
	return value

assert find_element1([2, 3, 3]) == 2
assert find_element1([2]) == 2
assert find_element1([2, 3, 2, 3, 1]) == 1
assert find_element1([99999999, 99999999, 127122812]) == 127122812

assert find_element2([2, 3, 3]) == 2
assert find_element2([2]) == 2
assert find_element2([2, 3, 2, 3, 1]) == 1
assert find_element2([99999999, 99999999, 127122812]) == 127122812

assert find_element3([2, 3, 3]) == 2
assert find_element3([2]) == 2
assert find_element3([2, 3, 2, 3, 1]) == 1
assert find_element3([99999999, 99999999, 127122812]) == 127122812

assert find_element4([2, 3, 3]) == 2
assert find_element4([2]) == 2
assert find_element4([2, 3, 2, 3, 1]) == 1
assert find_element4([99999999, 99999999, 127122812]) == 127122812
