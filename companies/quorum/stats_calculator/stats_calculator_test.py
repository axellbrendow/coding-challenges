import io
import unittest
import pandas as pd

import stats_calculator


BILLS = pd.read_csv(io.StringIO("""
id,title,sponsor_id
1,Bill 1,1
2,Bill 2,
3,Bill 3,2
"""))

LEGISLATORS = pd.read_csv(io.StringIO("""
id,name
1,Rep. Don Bacon (R-NE-2)
2,Rep. Jamaal Bowman (D-NY-16)
3,Rep. Cori Bush (D-MO-1)
"""))

VOTES = pd.read_csv(io.StringIO("""
id,bill_id
1,1
2,2
3,3
4,2
"""))

VOTE_RESULTS = pd.read_csv(io.StringIO("""
id,legislator_id,vote_id,vote_type
1,1,1,1
2,1,2,1
3,1,3,2
4,2,4,1
"""))

EXPECTED_LEGISLATORS_SUPPORT_OPPOSE_COUNT = pd.read_csv(io.StringIO("""
id,name,num_supported_bills,num_opposed_bills
1,Rep. Don Bacon (R-NE-2),2,1
2,Rep. Jamaal Bowman (D-NY-16),1,0
3,Rep. Cori Bush (D-MO-1),0,0
"""))

EXPECTED_BILLS_SUPPORT_OPPOSE_COUNT = pd.read_csv(io.StringIO("""
id,title,supporter_count,opposer_count,primary_sponsor
1,Bill 1,1,0,Rep. Don Bacon (R-NE-2)
2,Bill 2,2,0,Unknown
3,Bill 3,0,1,Rep. Jamaal Bowman (D-NY-16)
"""))


class StatsCalculatorTestCase(unittest.TestCase):
    def test_compute_legislators_support_oppose_count(self):
        legislators_support_oppose_count_df = stats_calculator.compute_legislators_support_oppose_count(
            LEGISLATORS, VOTE_RESULTS)

        self.assertTrue(legislators_support_oppose_count_df.equals(
            EXPECTED_LEGISLATORS_SUPPORT_OPPOSE_COUNT))

    def test_compute_bills_support_oppose_count(self):
        bills_support_oppose_count_df = stats_calculator.compute_bills_support_oppose_count(
            BILLS, LEGISLATORS, VOTES, VOTE_RESULTS)

        self.assertTrue(bills_support_oppose_count_df.equals(
            EXPECTED_BILLS_SUPPORT_OPPOSE_COUNT))


if __name__ == '__main__':
    unittest.main()
