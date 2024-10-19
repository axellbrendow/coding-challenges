def get_abbreviation(word, mask):
    abbr = ''
    count = 0
    for i in range(len(word)):
        if mask & 1 == 1:
            count += 1
        else:
            if count > 0:
                abbr += str(count)
            abbr += word[i]
            count = 0
        mask >>= 1
    if count > 0:
        abbr += str(count)
    return abbr

def any_abbreviation_conflicts(mask, target_abbr, target, dictionary):
    for word in dictionary:
        if get_abbreviation(word, mask) == target_abbr: return True
    return False

def get_minimum_unique_word_abbreviation(target, dictionary):
    # For each abbreviation of target, check if words in dictionary have the same abbreviation,
    # if so, skip, else, save this abbreviation as the minimum if it is
    dictionary = [word for word in dictionary if len(word) == len(target)]
    smallest_abbr = target
    num_abbreviations = 1 << len(target)
    for mask in range(num_abbreviations):
        target_abbr = get_abbreviation(target, mask)
        if any_abbreviation_conflicts(mask, target_abbr, target, dictionary): continue
        if len(target_abbr) < len(smallest_abbr):
            smallest_abbr = target_abbr
    return smallest_abbr

target = 'apple'
dictionary = ["blade"]
output = ['a4']
assert get_minimum_unique_word_abbreviation(target, dictionary) in output

target = 'apple'
dictionary = ["plain","amber","blade"]
output = ["1p3", "ap3", "a3e", "2p2", "3le", "3l1"]
assert get_minimum_unique_word_abbreviation(target, dictionary) in output
