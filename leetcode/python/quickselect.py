# Quickselect is the partial version of quicksort

def partition(arr, left, right):
    pivot_val = arr[right]
    left_count = 0
    for i in range(left, right):
        if arr[i] <= pivot_val:
            arr[left + left_count], arr[i] = arr[i], arr[left + left_count]
            left_count += 1
    arr[left + left_count], arr[right] = pivot_val, arr[left + left_count]
    return left + left_count


def quickselect_rec(arr, left, right, num_elems):
    if left < right:
        pivot = partition(arr, left, right)
        quickselect_rec(arr, left, pivot - 1, num_elems)
        if not (pivot + 1 >= num_elems):
            quickselect_rec(arr, pivot + 1, right, num_elems)


def quickselect(arr, num_elems):
    quickselect_rec(arr, 0, len(arr) - 1, num_elems)


arr = [10, 3, 5]
quickselect(arr, num_elems=1)
assert arr[0] == 3

arr = [10, 3, 5, 70, 6, 5]
quickselect(arr, num_elems=3)
assert arr[0] == 3 and arr[1] == 5 and arr[2] == 5

arr = [6, 1, 7, 9, 3, 8, 2, 5, 4, 0]
quickselect(arr, num_elems=3)
assert arr[0] == 0 and arr[1] == 1 and arr[2] == 2
