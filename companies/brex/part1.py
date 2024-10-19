# Using test driven development would have helped me a lot in all parts of the interview

from enum import Enum


class Color(Enum):
    BLUE = 1
    WHITE = 2
    YELLOW = 3
    RED = 4
    GREEN = 5


class CollectionOfGems:
    def __init__(self, colors: 'dict[Color, int]'):
        self._colors = colors

    def get_color_count(self, color: Color, default=0):
        return self._colors.get(color, default)

    def __iter__(self):
        for color in self._colors:
            yield color


class Card:
    def __init__(self, color: Color, gems: CollectionOfGems):
        self._color = color
        self._gems = gems

    def get_gem_color_count(self, color: Color):
        return self._gems.get_color_count(color)

    def gem_colors_iterator(self):
        yield from self._gems


class Player:
    def __init__(self, gems: CollectionOfGems):
        self._gems = gems


def can_purchase(card: Card, player_gems: CollectionOfGems):
    for color in card.gem_colors_iterator():
        if not (player_gems.get_color_count(color) >= card.get_gem_color_count(color)):
            return False
    return True


# Asked the interviewer if I should handle null inputs and the interviewer said all inputs would be valid.
# If that's not the case, then I should validate many things like colors, types of objects, Nones etc.

card = Card(color='B', gems=CollectionOfGems(colors={}))
player_gems = CollectionOfGems(colors={})
assert can_purchase(card, player_gems) == True

card = Card(color='B', gems=CollectionOfGems(colors={'B': 0}))
player_gems = CollectionOfGems(colors={})
assert can_purchase(card, player_gems) == True

card = Card(color='B', gems=CollectionOfGems(colors={}))
player_gems = CollectionOfGems(colors={'B': 0})
assert can_purchase(card, player_gems) == True

card = Card(color='B', gems=CollectionOfGems(colors={'B': 1}))
player_gems = CollectionOfGems(colors={'B': 0})
assert can_purchase(card, player_gems) == False

card = Card(color='B', gems=CollectionOfGems(colors={'B': 0}))
player_gems = CollectionOfGems(colors={'B': 1})
assert can_purchase(card, player_gems) == True

card = Card(color='B', gems=CollectionOfGems(colors={'B': 0, 'C': 9}))
player_gems = CollectionOfGems(colors={'B': 1, 'C': 8})
assert can_purchase(card, player_gems) == False

card = Card(color='B', gems=CollectionOfGems(colors={'B': 0, 'C': 9}))
player_gems = CollectionOfGems(colors={'B': 0, 'C': 9})
assert can_purchase(card, player_gems) == True

card = Card(color='B', gems=CollectionOfGems(colors={'B': 0, 'C': 9, 'D': 0}))
player_gems = CollectionOfGems(colors={'B': 0, 'C': 9})
assert can_purchase(card, player_gems) == True
