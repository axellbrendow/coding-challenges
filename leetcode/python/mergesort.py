def merge(arr, left, mid, right):
    left_pos, right_pos = 0, 0
    left_copy = arr[left:mid+1]
    right_copy = arr[mid+1:right+1]
    for i in range(left, right + 1):
        if right_pos >= len(right_copy):
            arr[i] = left_copy[left_pos]
            left_pos += 1

        elif left_pos >= len(left_copy):
            arr[i] = right_copy[right_pos]
            right_pos += 1

        elif left_copy[left_pos] <= right_copy[right_pos]:
            arr[i] = left_copy[left_pos]
            left_pos += 1

        else:
            arr[i] = right_copy[right_pos]
            right_pos += 1


def mergesort_rec(arr, left, right):
    if left < right:
        mid = (left + right) // 2
        mergesort_rec(arr, left, mid)
        mergesort_rec(arr, mid + 1, right)
        merge(arr, left, mid, right)


def mergesort(arr):
    mergesort_rec(arr, 0, len(arr) - 1)


arr = [1]
mergesort(arr)
assert arr == [1]

arr = [3, 10]
mergesort(arr)
assert arr == [3, 10]

arr = [10, 3]
mergesort(arr)
assert arr == [3, 10]

arr = [10, 3, 5]
mergesort(arr)
assert arr == [3, 5, 10]

arr = [10, 3, 5, 70, 6, 5]
mergesort(arr)
assert arr == [3, 5, 5, 6, 10, 70]

arr = [6, 1, 7, 9, 3, 8, 2, 5, 4, 0]
mergesort(arr)
assert arr == [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
