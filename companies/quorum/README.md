# Quorum coding challenge

## Running/testing the project

First make sure you setup a virtual environment (I'm using Linux as an example):

```sh
python3 -m venv venv
source ./venv/bin/activate
python -m pip install -r requirements.txt
```

To run the project:

```sh
python main.py
```

To run unit tests:

```sh
python stats_calculator/stats_calculator_test.py
```

## Write up

> 1. Discuss your solution's time complexity. What tradeoffs did you make?

Time complexity to build `legislators-support-oppose-count.csv`:
`(number of legislators * number of vote results)`

Time complexity to build `bills-support-oppose-count.csv`:
`(number of bills * number of legislators * number of votes * number of vote results)`

The main tradeoff I made in this project was: time to build the functionality versus efficiency.
I took 5 hours to study how to do the queries and aggregations more efficiently using pandas.
I did that because I wanted to learn more about Pandas and I took advantage from this exercise
to do this. In a real situation in my job I probably would define a "time box" for this activity,
let's say 2/3 hours and I wouldn't take more than that to complete it, even if it's not the most
optimized. Unless that's a strong requirement.

> 2. How would you change your solution to account for future columns that might be requested,
>    such as "Bill Voted On Date" or "Co-Sponsors"?

It doesn't seem to be difficult, I would need to add more `group by`s or maybe add more `merge`s
between DataFrames.

> 3. How would you change your solution if instead of receiving CSVs of data, you were given a
>    list of legislators or bills that you should generate a CSV for?

I think I didn't understand the question correctly.

I interpreted this question in two ways:

1. In the first way, the input would be all the CSVs **AND** the list of legislators and/or bills,
   for example:

```
bills.csv,
legislators.csv,
vote_results.csv,
votes.csv
legislator_ids = [1, 2, 3, 4, ...]
bill_ids = [1, 2, 3, ...]
```

And then I would need to output only the data related to those legislator_ids and/or bill_ids.
In this case I can re-use the functions I've defined but pre filtering the data set.
Something like `legislators_dataframe = legislators_dataframe[legislators_dataframe['id'].isin(legislator_ids)]`.

2. The second way I understood the question doesn't make sense for me, in this case, the input
   would be only the list of ids, for example:

```
legislator_ids = [1, 2, 3, 4, ...]
bill_ids = [1, 2, 3, ...]
```

But then, I don't know from where I should get the information related to those ids.

> 4. How long did you spend working on the assignment?

+/- 5 hours
