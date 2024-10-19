"""
leetcode

eleetcode
1 insertion

eleetcodle
2 insertions

eleetcodele
3 insertions

eleetcodeele
4 insertions

eleetcodteele
5 insertions

eleetdcodteele
6 insertions

eleetdcocdteele
7 insertions

---

leetcode

leetcodel
1 insertion

leetcodeel
2 insertions

leetcodteel
3 insertions

leetcodcteel
4 insertions

leetcdodcteel
5 insertions

---


{
    c: 0,
    e: 0,
    l: 0,
    t: 0,
    o: 0,
    d: 0,
    le: 1,
    ee: 0,
    et: 1,
    tc: 1,
    co: 1,
    od: 1,
    de: 1,
    lee: 1, between 'leel' and 'elee', 'leel' is better
}

"""


def minInsertions(s: str) -> int:
    min_steps = {}

    for letter in s:
        min_steps[letter] = 0

    for i in range(len(s) - 1):
        min_steps[s[i:i+2]] = 0 if s[i] == s[i + 1] else 1

    for size in range(3, len(s) + 1):
        for start in range(len(s) - size + 1):
            if s[start] == s[start + size - 1]:
                min_steps[s[start:start + size]] = \
                    min_steps[s[start+1:start + size-1]]
            else:
                min_steps[s[start:start + size]] = 1 + min(
                    min_steps[s[start + 1:start + size]],
                    min_steps[s[start:start + size - 1]],
                )

    return min_steps[s]


assert minInsertions("zzazz") == 0
assert minInsertions("mbadm") == 2
assert minInsertions("leetcode") == 5
