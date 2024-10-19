def isMatch(s: str, p: str) -> bool:
    memo = [[None for _ in range(len(p))] for _ in range(len(s) + 1)]

    def is_match_rec(s_pos, p_pos):
        if s_pos >= len(s) and p_pos >= len(p):
            return True

        if p_pos >= len(p):
            return False

        if memo[s_pos][p_pos] != None:
            return memo[s_pos][p_pos]

        match_curr_char = s_pos < len(s) and (
            p[p_pos] == '.' or s[s_pos] == p[p_pos])

        if p_pos + 1 < len(p) and p[p_pos + 1] == '*':
            memo[s_pos][p_pos] = (
                is_match_rec(s_pos, p_pos + 2)
                or match_curr_char and is_match_rec(s_pos + 1, p_pos)
            )
            return memo[s_pos][p_pos]

        elif match_curr_char:
            memo[s_pos][p_pos] = is_match_rec(s_pos + 1, p_pos + 1)
            return memo[s_pos][p_pos]

        else:
            memo[s_pos][p_pos] = False
            return memo[s_pos][p_pos]

    return is_match_rec(0, 0)


assert isMatch("a", ".") == True
assert isMatch("a", ".*") == True
assert isMatch("a", "a") == True
assert isMatch("aa", "a") == False
assert isMatch("aa", "a*") == True
assert isMatch("aa", "a*c*") == True
assert isMatch("aa", "b*a*c*") == True
assert isMatch("ab", ".*") == True
assert isMatch("aaab", "a.*b") == True
assert isMatch("aabcbcbcaccbcaabc", ".*a*aa*.*b*.c*.*a*") == True
