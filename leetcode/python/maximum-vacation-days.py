def take_max_vacation_days(flights, days):
	num_cities = len(flights)
	num_weeks = len(days[0])
	cache = {}

	def stay_or_travel(city, week):
		if week >= num_weeks: return 0
		if (city, week) in cache: return cache[(city, week)]
		max_days = 0
		for next_city in range(num_cities):
			if city == next_city or flights[city][next_city] == 1:
				max_days = max(
					max_days,
					days[next_city][week] + stay_or_travel(next_city, week + 1)
				)
		cache[(city, week)] = max_days
		return max_days

	return stay_or_travel(0, 0)

flights = [
	[0,1,1],
	[1,0,1],
	[1,1,0]
]
days = [
	[1,3,1],
	[6,0,3],
	[3,3,3]
]
assert take_max_vacation_days(flights, days) == 12

flights = [
	[0,0,0],
	[0,0,0],
	[0,0,0]
]
days = [
	[1,1,1],
	[7,7,7],
	[7,7,7]
]
assert take_max_vacation_days(flights, days) == 3

flights = [
	[0,1,1],
	[1,0,1],
	[1,1,0]
]
days = [
	[7,0,0],
	[0,7,0],
	[0,0,7]
]
assert take_max_vacation_days(flights, days) == 21
