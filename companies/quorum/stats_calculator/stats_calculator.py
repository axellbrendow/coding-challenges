from typing import Any, Iterable
import pandas as pd
from enum import Enum


class __VoteType(Enum):
    YEA = 1
    NAY = 2


def __first_or_default(iterable: Iterable, default: Any):
    return next(iter(iterable), default)


def __extract_num_positive_and_negative_votes(
    vote_types_counted: pd.DataFrame,
    positive_votes_column_name: str,
    negative_votes_column_name: str
):
    positive_votes_filter = vote_types_counted['vote_result_vote_type'] == __VoteType.YEA.value
    negative_votes_filter = vote_types_counted['vote_result_vote_type'] == __VoteType.NAY.value
    return pd.Series({
        positive_votes_column_name: __first_or_default(
            vote_types_counted[positive_votes_filter]['count'], 0),
        negative_votes_column_name: __first_or_default(
            vote_types_counted[negative_votes_filter]['count'], 0),
    })


def compute_legislators_support_oppose_count(
    legislators: pd.DataFrame,
    vote_results: pd.DataFrame
) -> pd.DataFrame:
    """
    Output format:

    |      attr_name      | attr_type |                           description                            |
    | :------------------ | :-------- | :--------------------------------------------------------------- |
    | id                  | integer   | The id of the legislator                                         |
    | name                | string    | The name of the legislator                                       |
    | num_supported_bills | integer   | The number of bills the legislator voted Yea on from the dataset |
    | num_opposed_bills   | integer   | The number of bills the legislator voted Nay on from the dataset |
    """

    def extract_num_supported_and_opposed_bills(legislator_vote_types_counted: pd.DataFrame):
        return __extract_num_positive_and_negative_votes(
            legislator_vote_types_counted,
            positive_votes_column_name='num_supported_bills',
            negative_votes_column_name='num_opposed_bills'
        )

    return legislators.add_prefix('legislator_').merge(
        vote_results.add_prefix('vote_result_'),
        left_on='legislator_id',
        right_on='vote_result_legislator_id',
        how='left'
    ).groupby(
        ['legislator_id', 'legislator_name', 'vote_result_vote_type'],
        dropna=False
    ).size().reset_index(name='count').groupby(
        ['legislator_id', 'legislator_name'],
        dropna=False
    ).apply(
        extract_num_supported_and_opposed_bills
    ).reset_index().rename(columns={
        'legislator_id': 'id',
        'legislator_name': 'name',
    })


def compute_bills_support_oppose_count(
    bills: pd.DataFrame,
    legislators: pd.DataFrame,
    votes: pd.DataFrame,
    vote_results: pd.DataFrame
) -> pd.DataFrame:
    """
    Output format:

    |    attr_name    | attr_type |                                                              description                                                              |
    | :-------------- | :-------- | :------------------------------------------------------------------------------------------------------------------------------------ |
    | id              | integer   | The id of the bill                                                                                                                    |
    | title           | string    | The title of the bill                                                                                                                 |
    | supporter_count | integer   | The number of legislators that supported this bill in the vote for it                                                                 |
    | opposer_count   | integer   | The number of legislators that opposed this bill in the vote for it                                                                   |
    | primary_sponsor | string    | The name of the primary sponsor of the bill. If the name of the sponsor is not available in the dataset, the cell should be "Unknown" |
    """

    def extract_num_supporters_and_opposers(bill_vote_types_counted: pd.DataFrame):
        return __extract_num_positive_and_negative_votes(
            bill_vote_types_counted,
            positive_votes_column_name='supporter_count',
            negative_votes_column_name='opposer_count'
        )

    return bills.add_prefix('bill_').merge(
        legislators.add_prefix('legislator_'),
        left_on='bill_sponsor_id',
        right_on='legislator_id',
        how='left'
    ).merge(
        votes.add_prefix('vote_'),
        left_on='bill_id',
        right_on='vote_bill_id',
        how='left'
    ).merge(
        vote_results.add_prefix('vote_result_'),
        left_on='vote_id',
        right_on='vote_result_vote_id',
        how='inner'
    ).groupby(
        ['bill_id', 'bill_title', 'legislator_name', 'vote_result_vote_type'],
        dropna=False
    ).size().reset_index(name='count').groupby(
        ['bill_id', 'bill_title', 'legislator_name'],
        dropna=False
    ).apply(
        extract_num_supporters_and_opposers
    ).reset_index()[
        ['bill_id', 'bill_title', 'supporter_count',
            'opposer_count', 'legislator_name']
    ].fillna({'legislator_name': 'Unknown'}).rename(columns={
        'bill_id': 'id',
        'bill_title': 'title',
        'legislator_name': 'primary_sponsor'
    })
