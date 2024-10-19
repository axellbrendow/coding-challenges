import json
import heapq

"""
642. Design Search Autocomplete System
Design a search autocomplete system for a search engine. Users may input a sentence
(at least one word and end with a special character '#'). For each character they type
except '#', you need to return the top 3 historical hot sentences that have prefix the
same as the part of sentence already typed. Here are the specific rules:

- The hot degree for a sentence is defined as the number of times a user typed the
    exactly same sentence before.

- The returned top 3 hot sentences should be sorted by hot degree (The first is the
    hottest one). If several sentences have the same degree of hot, you need to use
    ASCII-code order (smaller one appears first).

- If less than 3 hot sentences exist, then just return as many as you can.

- When the input is a special character, it means the sentence ends, and in this case,
    you need to return an empty list.

Your job is to implement the following functions:

The constructor function:

AutocompleteSystem(String[] sentences, int[] times): This is the constructor. The input
is historical data. Sentences is a string array consists of previously typed sentences.
Times is the corresponding times a sentence has been typed. Your system should record
these historical data.

Now, the user wants to input a new sentence. The following function will provide the next
character the user types:

List<String> input(char c): The input c is the next character typed by the user. The
character will only be lower-case letters ('a' to 'z'), blank space (' ') or a special
character ('#'). Also, the previously typed sentence should be recorded in your system.
The output will be the top 3 historical hot sentences that have prefix the same as the
part of sentence already typed.

Example:
Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
The system have already tracked down the following sentences and their corresponding times:
"i love you" : 5 times
"island" : 3 times
"ironman" : 2 times
"i love leetcode" : 2 times
Now, the user begins another search:

Operation: input('i')
Output: ["i love you", "island","i love leetcode"]
Explanation:
There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode"
have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114,
"i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot
sentences, so "ironman" will be ignored.

Operation: input(' ')
Output: ["i love you","i love leetcode"]
Explanation:
There are only two sentences that have prefix "i ".

Operation: input('a')
Output: []
Explanation:
There are no sentences that have prefix "i a".

Operation: input('#')
Output: []
Explanation:
The user finished the input, the sentence "i a" should be saved as a historical sentence
in system. And the following input will be counted as a new search.

 
Note:

The input sentence will always start with a letter and end with '#', and only one blank
space will exist between two words. The number of complete sentences that to be searched
won't exceed 100. The length of each sentence including those in the historical data
won't exceed 100. Please use double-quote instead of single-quote when you write test cases
even for a character input. Please remember to RESET your class variables declared in class
AutocompleteSystem, as static/class variables are persisted across multiple test cases.
Please see here for more details.
"""


class SentenceFrequency:
    def __init__(self, sentence: str, frequency: int) -> None:
        self.sentence = sentence
        self.frequency = frequency

    def __lt__(self, other: 'SentenceFrequency'):
        return (
            self.frequency < other.frequency or (
                self.frequency == other.frequency and self.sentence > other.sentence
            )
        )


class TrieNode:
    def __init__(
            self,
            character: str,
            prefix: str = None,
            num_of_strs_to_return: int = 3
    ) -> None:
        self._character = character
        self._prefix = prefix
        self._represents_a_sentence = False
        self._sentence_frequency = 0
        self._num_of_strs_to_return = num_of_strs_to_return
        self._children: dict[str, TrieNode] = {}
        # min heap that stores the greatest values
        self._highest_frequency_sentences: list[SentenceFrequency] = []

    @property
    def character(self):
        return self._character

    @property
    def prefix(self):
        return self._prefix

    @property
    def represents_a_sentence(self):
        return self._represents_a_sentence

    @represents_a_sentence.setter
    def represents_a_sentence(self, represents_a_sentence):
        self._represents_a_sentence = represents_a_sentence

    @property
    def sentence_frequency(self):
        return self._sentence_frequency

    @property
    def highest_frequency_sentences(self):
        return self._highest_frequency_sentences

    def add_child(self, child: 'TrieNode'):
        self._children[child._character] = child

    def get_child(self, child_character: str):
        return self._children[child_character]

    def has_child(self, child_character: str):
        return child_character in self._children

    def increment_sentence_frequency(self, value_to_add: int):
        self._sentence_frequency += value_to_add

    def cache_sentence_if_higher_frequency(self, sentence: str, sentence_frequency: int):
        if len(self._highest_frequency_sentences) < self._num_of_strs_to_return:
            heapq.heappush(self._highest_frequency_sentences,
                           SentenceFrequency(sentence, sentence_frequency))
        else:
            min_sentence_frequency_obj = self._highest_frequency_sentences[0]

            if sentence == min_sentence_frequency_obj.sentence:
                self._highest_frequency_sentences[0].frequency = sentence_frequency
            elif (
                sentence_frequency > min_sentence_frequency_obj.frequency
                or (
                    sentence_frequency == min_sentence_frequency_obj.frequency
                    and sentence < min_sentence_frequency_obj.sentence
                )
            ):
                heapq.heappop(self._highest_frequency_sentences)
                heapq.heappush(self._highest_frequency_sentences,
                               SentenceFrequency(sentence, sentence_frequency))

    def to_json(self):
        return json.dumps(self, default=lambda obj: obj.__dict__, indent=2)

    def __iter__(self):
        for child in self._children:
            yield self._children[child]

    def __lt__(self, other):
        if not isinstance(other, TrieNode):
            raise TypeError('TrieNode is only comparable to TrieNode')
        return self.character < other.character


class TrieForAutocompleteSystem:
    def __init__(
        self,
        sentences: 'list[str]',
        times: 'list[int]',
        num_of_strs_to_return: int
    ) -> None:
        self._root = TrieNode(
            character=None, num_of_strs_to_return=num_of_strs_to_return)

        for i in range(len(sentences)):
            self.add_sentence(sentences[i], times[i])

    def add_sentence(self, sentence: str, frequency: int):
        def add_sentence_rec(node: TrieNode, i: int, prefix: str):
            if i == len(sentence):
                node.represents_a_sentence = True
                node.increment_sentence_frequency(frequency)
                return node.sentence_frequency

            prefix += sentence[i]

            if not node.has_child(sentence[i]):
                node.add_child(TrieNode(sentence[i], prefix))

            actual_sentence_frequency = add_sentence_rec(
                node.get_child(sentence[i]), i + 1, prefix)

            node.cache_sentence_if_higher_frequency(
                sentence, actual_sentence_frequency)

            return actual_sentence_frequency

        add_sentence_rec(self._root, i=0, prefix='')

    def get_node_for_last_character_of(self, sentence):
        node = self._root
        for character in sentence:
            if not node.has_child(character):
                return None
            node = node.get_child(character)
        return node


class AutocompleteSystem:
    def __init__(
        self,
        sentences: 'list[str]',
        times: 'list[int]',
        num_of_strs_to_return: int
    ) -> None:
        self._trie = TrieForAutocompleteSystem(
            sentences, times, num_of_strs_to_return)
        self._curr_sentence = ''
        self._num_of_strs_to_return = num_of_strs_to_return

    # time complexity: O(user input) + O(num_of_strs_to_return) which is O(1) most
    # of the times. Ideally we would limit the size of the user input.
    def input(self, character: str):
        if character == '#':
            self._trie.add_sentence(self._curr_sentence, frequency=1)
            self._curr_sentence = ''
            return []

        self._curr_sentence += character
        last_node = self._trie.get_node_for_last_character_of(
            self._curr_sentence
        )
        if last_node == None:
            return []

        # This sort is almost linear because the heap array is near-sorted.
        # Also, there's no problem sorting the array, the heap is still valid
        # as all parents will be less than their children
        last_node.highest_frequency_sentences.sort()
        return [sentence_frequency_obj.sentence
                for sentence_frequency_obj
                in reversed(last_node.highest_frequency_sentences)]


"""
Trie:

ROOT 12 -> 'i' 12 -> ' ' 7 -> 'l' 7 -> 'o' 7 -> 'v' 7 -> 'e' 7 -> ' ' 7 -> 'y' 5 -> 'o' 5 -> 'u' 5
                                                                        -> 'l' 2 -> 'e' 2 -> 'e' 2 -> 't' 2 -> 'c' 2 -> 'o' 2 -> 'd' 2 -> 'e' 2
                  -> 's' 3 -> 'l' 3 -> 'a' 3 -> 'n' 3 -> 'd' 3
                  -> 'r' 2 -> 'o' 2 -> 'n' 2 -> 'm' 2 -> 'a' 2 -> 'n' 2
"""

sentences = ['i love you', 'island', 'ironman', 'i love leetcode']
times = [5, 3, 2, 2]
system = AutocompleteSystem(sentences, times, num_of_strs_to_return=3)
# Uncomment this line to print the tree
# print(system._trie._root.to_json())
assert system.input('i') == ["i love you", "island", "i love leetcode"]
assert system.input(' ') == ["i love you", "i love leetcode"]
assert system.input('a') == []
assert system.input('#') == []
# It's not clear from the question text what I should do in this case
assert system.input('b') == []

sentences = ['aa', 'ab', 'ac']
times = [1, 1, 1]
system = AutocompleteSystem(sentences, times, num_of_strs_to_return=3)
# Uncomment this line to print the tree
# print(system._trie._root.to_json())
assert system.input('a') == ['aa', 'ab', 'ac']
