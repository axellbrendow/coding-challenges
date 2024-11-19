package main

import "container/heap"

// --------------------------- MIN HEAP

type IntMinHeap []int

func (h IntMinHeap) Len() int               { return len(h) }
func (h IntMinHeap) Less(i int, j int) bool { return h[i] < h[j] }
func (h IntMinHeap) Swap(i int, j int)      { h[i], h[j] = h[j], h[i] }

func (h *IntMinHeap) Push(x any) { *h = append(*h, x.(int)) }

// the function Pop() from the heap module swaps positions 0 and n-1 before calling my Pop() function
func (h *IntMinHeap) Pop() any {
	prevRoot := (*h)[len(*h)-1]
	*h = (*h)[0 : len(*h)-1]
	return prevRoot
}

// --------------------------- MAX HEAP

type IntMaxHeap []int

func (h IntMaxHeap) Len() int           { return len(h) }
func (h IntMaxHeap) Less(i, j int) bool { return h[i] > h[j] }
func (h IntMaxHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }

func (h *IntMaxHeap) Push(x any) { *h = append(*h, x.(int)) }

// the function Pop() from the heap module swaps positions 0 and n-1 before calling my Pop() function
func (h *IntMaxHeap) Pop() any {
	prevRoot := (*h)[len(*h)-1]
	*h = (*h)[0 : len(*h)-1]
	return prevRoot
}

// --------------------------- MedianFinder

type MedianFinder struct {
	smallestValues IntMaxHeap // reverse order
	biggestValues  IntMinHeap // natural order
}

func (medianFinder *MedianFinder) addNum(num int) {
	if medianFinder.biggestValues.Len() > 0 && num > medianFinder.biggestValues[0] {
		heap.Push(&medianFinder.biggestValues, num)
		if medianFinder.biggestValues.Len() > medianFinder.smallestValues.Len() {
			heap.Push(&medianFinder.smallestValues, heap.Pop(&medianFinder.biggestValues))
		}
	} else {
		heap.Push(&medianFinder.smallestValues, num)
		if medianFinder.smallestValues.Len()-medianFinder.biggestValues.Len() == 2 {
			heap.Push(&medianFinder.biggestValues, heap.Pop(&medianFinder.smallestValues))
		}
	}
}

func (medianFinder *MedianFinder) findMedian() float64 {
	if medianFinder.smallestValues.Len() == medianFinder.biggestValues.Len() {
		return float64(medianFinder.smallestValues[0]+medianFinder.biggestValues[0]) / 2.0
	}
	return float64(medianFinder.smallestValues[0])
}

func main() {
	var medianFinder *MedianFinder = nil

	medianFinder = &MedianFinder{}
	medianFinder.addNum(1)
	medianFinder.addNum(2)
	if medianFinder.findMedian() != (1+2)/2.0 {
		panic("")
	}
	medianFinder.addNum(3)
	if medianFinder.findMedian() != (1+2+3)/3.0 {
		panic("")
	}
}
