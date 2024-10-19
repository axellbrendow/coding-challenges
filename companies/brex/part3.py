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

    def has_color(self, color: Color):
        return color in self._colors

    def update_color_count(self, color: Color, count: int):
        self._colors[color] = count

    def num_of_colors(self):
        return len(self._colors)

    def __iter__(self):
        for color in self._colors:
            yield color


class Card:
    def __init__(self, color: Color, gems: CollectionOfGems):
        self._color = color
        self._gems = gems

    @property
    def color(self):
        return self._color

    def get_gem_color_count(self, color: Color):
        return self._gems.get_color_count(color)

    def gem_colors_iterator(self):
        yield from self._gems


class Player:
    def __init__(self, gems: CollectionOfGems):
        self._gems = gems
        self._cards = {color: [] for color in Color}

    @property
    def gems(self):
        return self._gems

    def has_card(self, card: Card):
        return card in self._cards[card.color]

    def can_purchase(self, card: Card):
        for color in card.gem_colors_iterator():
            player_color_count = self.gems.get_color_count(color) + \
                len(self._cards[color])

            if not (player_color_count >= card.get_gem_color_count(color)):
                return False
        return True

    def purchase(self, card: Card):
        if not self.can_purchase(card):
            return False

        for color in card.gem_colors_iterator():
            if not self.gems.has_color(color):
                continue
            discounted_price_for_this_color = card.get_gem_color_count(color) - \
                len(self._cards[color])
            self.gems.update_color_count(
                color, self.gems.get_color_count(color) - discounted_price_for_this_color)

        self._cards[card.color].append(card)
        return True


# Testing new behavior

player = Player(gems=CollectionOfGems(colors={}))
card = Card(color=Color.YELLOW, gems=CollectionOfGems(colors={}))
player.purchase(card)
card = Card(color=Color.YELLOW, gems=CollectionOfGems(colors={}))
player.purchase(card)
card = Card(color=Color.YELLOW, gems=CollectionOfGems(
    colors={Color.YELLOW: 3}))
player.purchase(card)
assert player.purchase(card) == False
assert not player.has_card(card)

player = Player(gems=CollectionOfGems(colors={}))
card = Card(color=Color.YELLOW, gems=CollectionOfGems(colors={}))
player.purchase(card)
card = Card(color=Color.YELLOW, gems=CollectionOfGems(colors={}))
player.purchase(card)
card = Card(color=Color.YELLOW, gems=CollectionOfGems(colors={}))
player.purchase(card)
card = Card(color=Color.YELLOW, gems=CollectionOfGems(
    colors={Color.YELLOW: 3}))
player.purchase(card)
assert player.purchase(card) == True
assert player.has_card(card)

player = Player(gems=CollectionOfGems(colors={Color.YELLOW: 1}))
assert player.gems.get_color_count(Color.YELLOW) == 1
card = Card(color=Color.YELLOW, gems=CollectionOfGems(colors={}))
player.purchase(card)
card = Card(color=Color.YELLOW, gems=CollectionOfGems(colors={}))
player.purchase(card)
card = Card(color=Color.YELLOW, gems=CollectionOfGems(
    colors={Color.YELLOW: 3}))
player.purchase(card)
assert player.purchase(card) == True
assert player.has_card(card)
assert player.gems.get_color_count(Color.YELLOW) == 0


# Testing old behavior from previous versions


player = Player(gems=CollectionOfGems(
    colors={Color.YELLOW: 1, Color.GREEN: 3}))
assert player.gems.get_color_count(Color.YELLOW) == 1
assert player.gems.get_color_count(Color.GREEN) == 3
card = Card(color=Color.YELLOW, gems=CollectionOfGems(
    colors={Color.YELLOW: 1}))
assert player.purchase(card) == True
assert player.gems.get_color_count(Color.YELLOW) == 0
card = Card(color=Color.GREEN, gems=CollectionOfGems(colors={Color.YELLOW: 1}))
assert player.purchase(card) == True
assert player.has_card(card)
assert player.gems.get_color_count(Color.GREEN) == 3

card = Card(color=Color.BLUE, gems=CollectionOfGems(colors={}))
player = Player(gems=CollectionOfGems(colors={}))
assert player.purchase(card) == True
assert player.has_card(card)
assert player.gems.get_color_count(Color.BLUE) == 0

card = Card(color=Color.BLUE, gems=CollectionOfGems(colors={Color.BLUE: 0}))
player = Player(gems=CollectionOfGems(colors={}))
assert player.purchase(card) == True
assert player.has_card(card)
assert player.gems.get_color_count(Color.BLUE) == 0

card = Card(color=Color.BLUE, gems=CollectionOfGems(colors={}))
player = Player(gems=CollectionOfGems(colors={Color.BLUE: 0}))
assert player.purchase(card) == True
assert player.has_card(card)
assert player.gems.get_color_count(Color.BLUE) == 0

card = Card(color=Color.BLUE, gems=CollectionOfGems(colors={Color.BLUE: 1}))
player = Player(gems=CollectionOfGems(colors={Color.BLUE: 0}))
assert player.purchase(card) == False
assert not player.has_card(card)
assert player.gems.get_color_count(Color.BLUE) == 0

card = Card(color=Color.BLUE, gems=CollectionOfGems(colors={Color.BLUE: 0}))
player = Player(gems=CollectionOfGems(colors={Color.BLUE: 1}))
assert player.purchase(card) == True
assert player.has_card(card)
assert player.gems.get_color_count(Color.BLUE) == 1

card = Card(color=Color.BLUE, gems=CollectionOfGems(
    colors={Color.BLUE: 0, Color.YELLOW: 9}))
player = Player(gems=CollectionOfGems(colors={Color.BLUE: 1, Color.YELLOW: 8}))
assert player.purchase(card) == False
assert not player.has_card(card)
assert player.gems.get_color_count(Color.BLUE) == 1
assert player.gems.get_color_count(Color.YELLOW) == 8

card = Card(color=Color.BLUE, gems=CollectionOfGems(
    colors={Color.BLUE: 0, Color.YELLOW: 9}))
player = Player(gems=CollectionOfGems(colors={Color.BLUE: 0, Color.YELLOW: 9}))
assert player.purchase(card) == True
assert player.has_card(card)
assert player.gems.get_color_count(Color.BLUE) == 0
assert player.gems.get_color_count(Color.YELLOW) == 0

card = Card(color=Color.BLUE, gems=CollectionOfGems(
    colors={Color.BLUE: 0, Color.YELLOW: 9, Color.WHITE: 0}))
player = Player(gems=CollectionOfGems(colors={Color.BLUE: 0, Color.YELLOW: 9}))
assert player.purchase(card) == True
assert player.has_card(card)
assert player.gems.get_color_count(Color.BLUE) == 0
assert player.gems.get_color_count(Color.YELLOW) == 0
