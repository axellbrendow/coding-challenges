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

    def can_purchase(self, player_gems: CollectionOfGems):
        for color in self.gem_colors_iterator():
            if not (player_gems.get_color_count(color) >= self.get_gem_color_count(color)):
                return False
        return True


class Player:
    def __init__(self, gems: CollectionOfGems):
        self._gems = gems
        self._cards: list[Card] = []

    @property
    def gems(self):
        return self._gems

    def has_card(self, card: Card):
        return card in self._cards

    def purchase(self, card: Card):
        if not card.can_purchase(self._gems):
            return False

        for color in card.gem_colors_iterator():
            if not self._gems.has_color(color):
                continue
            current_color_count = self._gems.get_color_count(color)
            self._gems.update_color_count(
                color, current_color_count - card.get_gem_color_count(color))

        self._cards.append(card)
        return True


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
    colors={Color.BLUE: 0, Color.GREEN: 9}))
player = Player(gems=CollectionOfGems(colors={Color.BLUE: 1, Color.GREEN: 8}))
assert player.purchase(card) == False
assert not player.has_card(card)
assert player.gems.get_color_count(Color.BLUE) == 1
assert player.gems.get_color_count(Color.GREEN) == 8

card = Card(color=Color.BLUE, gems=CollectionOfGems(
    colors={Color.BLUE: 0, Color.GREEN: 9}))
player = Player(gems=CollectionOfGems(colors={Color.BLUE: 0, Color.GREEN: 9}))
assert player.purchase(card) == True
assert player.has_card(card)
assert player.gems.get_color_count(Color.BLUE) == 0
assert player.gems.get_color_count(Color.GREEN) == 0

card = Card(color=Color.BLUE, gems=CollectionOfGems(
    colors={Color.BLUE: 0, Color.GREEN: 9, Color.RED: 0}))
player = Player(gems=CollectionOfGems(colors={Color.BLUE: 0, Color.GREEN: 9}))
assert player.purchase(card) == True
assert player.has_card(card)
assert player.gems.get_color_count(Color.BLUE) == 0
assert player.gems.get_color_count(Color.GREEN) == 0
