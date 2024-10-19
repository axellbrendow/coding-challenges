def get_new_state(state):  # state = [1, 2]
	new_state = []  # [‘(1,2)’]
	i = 0  # 1
	j = len(state) - 1  # 0
	while i < j:
		new_state.append(f'({state[i]},{state[j]})')
		i += 1
		j -= 1
	return new_state

def get_final_contest_matches(num_teams):  # num_teams = 2
	state = list(range(1, num_teams + 1))  # range(1, 3) = [1, 2]
	new_state = get_new_state(state)  # [‘(1,2)’]
	while len(new_state) > 1:
		state = new_state
		new_state = get_new_state(state)
	return new_state[0]

assert get_final_contest_matches(2) == '(1,2)'
assert get_final_contest_matches(4) == '((1,4),(2,3))'
assert get_final_contest_matches(8) == '(((1,8),(4,5)),((2,7),(3,6)))'
