import os
import pandas as pd

from stats_calculator import stats_calculator


def main():
    legislators = pd.read_csv(os.path.join('input', 'legislators.csv'))
    bills = pd.read_csv(os.path.join('input', 'bills.csv'))
    votes = pd.read_csv(os.path.join('input', 'votes.csv'))
    vote_results = pd.read_csv(os.path.join('input', 'vote_results.csv'))

    legislators_support_oppose_count_df = \
        stats_calculator.compute_legislators_support_oppose_count(
            legislators, vote_results)

    bills_support_oppose_count_df = \
        stats_calculator.compute_bills_support_oppose_count(
            bills, legislators, votes, vote_results)

    legislators_support_oppose_count_df.to_csv(
        os.path.join('output', 'legislators-support-oppose-count.csv'), index=False)
    bills_support_oppose_count_df.to_csv(
        os.path.join('output', 'bills-support-oppose-count.csv'), index=False)


if __name__ == '__main__':
    main()
