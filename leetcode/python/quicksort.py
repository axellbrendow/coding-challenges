def partition(arr, left, right):
    pivot_val = arr[right]
    left_count = 0
    for i in range(left, right):
        if arr[i] <= pivot_val:
            arr[left + left_count], arr[i] = arr[i], arr[left + left_count]
            left_count += 1
    arr[left + left_count], arr[right] = pivot_val, arr[left + left_count]
    return left + left_count


def quicksort_rec(arr, left, right):
    if left < right:
        pivot = partition(arr, left, right)
        quicksort_rec(arr, left, pivot - 1)
        quicksort_rec(arr, pivot + 1, right)


def quicksort(arr):
    quicksort_rec(arr, 0, len(arr) - 1)


arr = [1]
quicksort(arr)
assert arr == [1]

arr = [3, 10]
quicksort(arr)
assert arr == [3, 10]

arr = [10, 3]
quicksort(arr)
assert arr == [3, 10]

arr = [10, 3, 5]
quicksort(arr)
assert arr == [3, 5, 10]

arr = [10, 3, 5, 70, 6, 5]
quicksort(arr)
assert arr == [3, 5, 5, 6, 10, 70]

arr = [6, 1, 7, 9, 3, 8, 2, 5, 4, 0]
quicksort(arr)
assert arr == [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
