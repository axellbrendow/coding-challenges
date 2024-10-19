def iterate_n_arrays(arrays):
	i = 0  # 4
	while True:
		found = False  # False
		for array in arrays:  # array = [*1,*2], array = [*3,*4,*5,*6]
			if i < len(array):
				found = True
				yield array[i]
		if not found: break
		i += 1

def iterate(v1, v2):
	return iterate_n_arrays([v1, v2])

for value in iterate([1,2], [3,4,5,6]):
    print(value)
