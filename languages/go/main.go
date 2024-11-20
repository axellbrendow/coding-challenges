package main

import (
	"container/heap"
	"container/list"
	"encoding/json"
	"errors"
	"fmt"
	"goaxell/subpkg/subsubpkg"
	"io"
	"log"
	"math"
	"math/rand"
	"net/http"
	"regexp"
	"runtime"
	"slices"
	"strings"
	"time"
)

/*
Must-read for Go programmers: "Effective Go" https://go.dev/doc/effective_go.html
Go specification: https://go.dev/ref/spec
*/

func main() {
	fmt.Println()
	fmt.Println(">>> ༼ つ ◕_◕ ༽つ how to print https://pkg.go.dev/fmt")
	fmt.Println()

	fmt.Printf("Hi 👋, current time: %v\n", time.Now()) // prints -> {World}

	fmt.Printf("%v\n", struct{ name string }{name: "World"}) // prints -> {World}

	// %+v shows struct fields names
	fmt.Printf("%+v\n", struct{ name string }{name: "World"}) // prints -> {name:World}

	// Go-syntax representation of the value
	fmt.Printf("%#v\n", struct{ name string }{name: "World"}) // prints -> struct { name string }{name:"World"}

	// Go-syntax representation of the type of the value
	fmt.Printf("%T\n", struct{ name string }{name: "World"}) // prints -> struct { name string }

	fmt.Printf("|%+9.2f|\n", 7.654) // width 9, precision 2, prints -> |    +7.65|
	fmt.Printf("|%-9.2f|\n", 7.654) // width 9, precision 2, prints -> |7.65     |
	fmt.Printf("|%09.2f|\n", 7.655) // width 9, precision 2, prints -> |000007.66|

	fmt.Println()
	fmt.Println(">>> ༼ つ ◕_◕ ༽つ all primitive types")
	fmt.Println()

	fmt.Printf(`bool, uint8, int8, byte
uint16, int16
uint32, int32, rune
uint64, int64
int, uint
float32
float64
complex64
complex128
uintptr, *T, unsafe.Pointer
%v:%T`, 1-0.707i, 1-0.707i)

	fmt.Println()
	fmt.Println(">>> ༼ つ ◕_◕ ༽つ strings, indexOf, cmp, replace, split, concat, lower, upper, template strings, unicode")
	fmt.Println()

	fmt.Println(`strings.Index("abcd", "bc")`, strings.Index("abcd", "bc"))
	fmt.Println(`strings.LastIndex("aaa", "a")`, strings.LastIndex("aaa", "a"))
	fmt.Println(`strings.Compare("abcd", "abc")`, strings.Compare("abcd", "abc"))
	fmt.Println(`strings.ReplaceAll("abcd", "abc", "")`, strings.ReplaceAll("abcd", "abc", ""))
	fmt.Println(`strings.Split("abcd", "")`, strings.Split("abcd", ""))
	fmt.Println(`"a" + "a" ->`, "a"+"a")
	sb := strings.Builder{}
	_ /*writtenLength*/, err := sb.WriteString("ab")
	if err != nil {
		panic(err)
	}
	_ /*writtenLength*/, err = sb.WriteString("ab")
	if err != nil {
		panic(err)
	}
	fmt.Println(`strings.Builder.WriteString("ab").WriteString("ab")`, sb.String())
	fmt.Println(`strings.ToUpper("ããã")`, strings.ToUpper("ããã"))
	fmt.Println(`strings.ToLower("ÃÃÃ")`, strings.ToLower("ÃÃÃ"))

	msg := fmt.Sprintf("Hi, %v", "Axell")
	fmt.Println(`"Hi, %v", "Axell" ->`, msg)

	fmt.Println(`for pos, char := range "日本語"`)
	for pos, char := range "日本語" {
		fmt.Printf("character %c starts at byte position %d\n", char, pos)
		/*
			character 日 starts at byte position 0
			character 本 starts at byte position 3
			character 語 starts at byte position 6
		*/
	}

	fmt.Println()
	fmt.Println(">>> ༼ つ ◕_◕ ༽つ Boxing/Unboxing/Wrapper types")
	fmt.Println()

	fmt.Println("Go has no boxing/unboxing/wrapper types")

	fmt.Println()
	fmt.Println(">>> ༼ つ ◕_◕ ༽つ assert keyword")
	fmt.Println()

	fmt.Println("There's no assert keyword, but you can write an unit test or run `if !condition { panic(' ') }`")

	fmt.Println()
	fmt.Println(">>> ༼ つ ◕_◕ ༽つ MAX_INT, MIN_INT")
	fmt.Println()

	// Int max: 9223372036854775807 (2^63 - 1), int min: -9223372036854775808 (-2^63)
	fmt.Printf("Int max: %v, int min: %v\n", math.MaxInt, math.MinInt)

	fmt.Println()
	fmt.Println(">>> ༼ つ ◕_◕ ༽つ all operators")
	fmt.Println()

	fmt.Println(`Expression = UnaryExpr | Expression binary_op Expression .
UnaryExpr  = PrimaryExpr | unary_op UnaryExpr .
unary_op   = "+" | "-" | "!" | "^" | "*" | "&" | "<-" .
binary_op  = "||" | "&&" | rel_op | add_op | mul_op .
rel_op     = "==" | "!=" | "<" | "<=" | ">" | ">=" .
add_op     = "+" | "-" | "|" | "^" .
mul_op     = "*" | "/" | "%" | "<<" | ">>" | "&" | "&^" .`)

	fmt.Println()
	fmt.Println(">>> ༼ つ ◕_◕ ༽つ unsigned bit shift operator")
	fmt.Println()

	fmt.Println("1 << 1 ->", 1<<1)                                         // 1 << 1 -> 2
	fmt.Println("1 >> 1 ->", 1>>1)                                         // 1 >> 1 -> 0
	fmt.Println("-1 << 1 ->", -1<<1)                                       // -1 << 1 -> -2
	fmt.Println("-1 >> 1 ->", -1>>1, "the operator >> keeps the sign bit") // -1 >> 1 -> -1

	fmt.Println()
	fmt.Println(">>> ༼ つ ◕_◕ ༽つ if, else, for, while, switch")
	fmt.Println()

	if 3 > 2 {
		fmt.Println("if 3 > 2")
	}

	if 2 > 2 {
		fmt.Println("if 2 > 2")
	} else if 3 > 2 {
		fmt.Println("else if 3 > 2")
	}

	for i := 0; i < 5; i++ {
		fmt.Printf("Testando for i = %v\n", i)
	}

	i := 0
	for i < 5 { // while
		fmt.Printf("Testando while i = %v\n", i)
		i++
	}

	fmt.Println()
	fmt.Println(">>> ༼ つ ◕_◕ ༽つ date types (sum/subtract, convert from and to unix epoch, parse strings)")
	fmt.Println()

	now := time.Now()
	fmt.Println("now                   ", now) // 2024-11-20 11:00:15.858881362 -0300 -03 m=+0.000611981
	// Sub() works with another time object
	fmt.Println("now - now             ", now.Sub(now)) // 0s
	// Add() works with the duration type
	fmt.Println("now + 24h             ", now.Add(time.Hour*24)) // 2024-11-21 11:00:15.858881362 -0300 -03 m=+86400.000611981
	// Add() works with the duration type
	fmt.Println("now - 24h             ", now.Add(-time.Hour*24)) // 2024-11-19 11:00:15.858881362 -0300 -03 m=-86399.999388019
	fmt.Println("now unix secs         ", now.Unix())             //1732111215
	fmt.Println("now unix millis       ", now.UnixMilli())        //1732111215858
	// ISO 8601 ~ RFC 3339
	timeWithoutMillis, timeWithoutMillisErr := time.Parse(time.RFC3339, "2024-11-20T10:50:50-03:00")
	if timeWithoutMillisErr != nil {
		panic(timeWithoutMillisErr)
	}
	fmt.Println("parse without millis  ", timeWithoutMillis)                      // 2024-11-20 10:50:50 -0300 -03
	fmt.Println("format without millis ", timeWithoutMillis.Format(time.RFC3339)) // 2024-11-20T10:50:50-03:00

	timeWithMillis, timeWithMillisErr := time.Parse(time.RFC3339Nano, "2024-11-20T10:50:50.567-03:00")
	if timeWithMillisErr != nil {
		panic(timeWithMillisErr)
	}
	fmt.Println("parse with millis     ", timeWithMillis)                          // 2024-11-20 10:50:50.567 -0300 -03
	fmt.Println("format with millis    ", timeWithMillis.Format(time.RFC3339Nano)) // 2024-11-20T10:50:50.567-03:00

	fmt.Println()
	fmt.Println(">>> ༼ つ ◕_◕ ༽つ struct (stack allocated), class (heap allocated), record (simpler class), interface")
	fmt.Println()

	// Go doesn't have classes, only structs. The Go spec doesn't mention anything related to heap or stack.

	type MyStruct struct{ name string }

	fmt.Printf("%#v\n", MyStruct{"Axell"})

	fmt.Println(Talk{}) // implements ITalk

	fmt.Println()
	fmt.Println(">>> ༼ つ ◕_◕ ༽つ access modifiers (public, private, protected, internal (within .dll or .jar), file)")
	fmt.Println()

	fmt.Println()
	fmt.Println(">>> ༼ つ ◕_◕ ༽つ best practices around fields, properties, constructors, getters, and setters")
	fmt.Println()

	type Strut struct {
		onlyVisibleWithinTheFile any // lower case start, only visible within the file
		VisibleOutsideTheFile    any // Upper case start, visible outside the file
	}

	fmt.Printf("%#v\n", Strut{onlyVisibleWithinTheFile: 1, VisibleOutsideTheFile: 2})

	fmt.Println()
	fmt.Println(">>> ༼ つ ◕_◕ ༽つ inheritance, overriding, polymorphism")
	fmt.Println()

	fmt.Println("There's no official inheritance or polymorphism. You can use interfaces and composition.")
	myList, myStack := &MyList{}, &MyStack{}
	fmt.Printf("Take a look at %T and %T in the code\n", myList, myStack)
	myList.size = 3
	myStack.size = 5
	myStack.count = 7
	fmt.Printf("myList.Size() -> %v, myStack.Size() -> %v\n", myList.Size(), myStack.Size())

	fmt.Println()
	fmt.Println(">>> ༼ つ ◕_◕ ༽つ enums  https://pkg.go.dev/syscall#SIGABRT https://gobyexample.com/enums")
	fmt.Println()

	fmt.Printf("%v:%T %v:%T %v:%T\n", SMALL, SMALL, MEDIUM, MEDIUM, LARGE, LARGE)

	func() {
		fmt.Println(Size(88))
		defer func() {
			recover()
		}()
	}()

	fmt.Println()
	fmt.Println(">>> ༼ つ ◕_◕ ༽つ collections (list, stack, map, set, queue, deque / doubly linked list, min heap, max heap)")
	fmt.Println()

	fmt.Println()
	fmt.Println("> lists")
	gmyList := []int{0, 0, 0, 7, 8, 9} // literal form
	gmyList = make([]int, 3, 100)      // make(slice type (required), length (required), capacity)
	gmyList = append(gmyList, 7, 8, 9)
	fmt.Println("gmyList", gmyList)         // [0 0 0 7 8 9]
	fmt.Println("gmyList[:]", gmyList[:])   // [0 0 0 7 8 9]
	fmt.Println("gmyList[:2]", gmyList[:2]) // [0 0]
	fmt.Println("gmyList[2:]", gmyList[2:]) // [0 7 8 9]

	fmt.Println("for value := range gmyList")
	for value := range gmyList {
		fmt.Println(value)
	}

	fmt.Println()
	fmt.Println("> stacks")
	gmyStack := make([]int, 0)
	fmt.Println(gmyStack) // []

	gmyStack = append(gmyStack, 7)
	fmt.Println(gmyStack) // [7]

	gmyStack = append(gmyStack, 8)
	fmt.Println(gmyStack) // [7 8]

	gmyStack = append(gmyStack, 9)
	fmt.Println(gmyStack) // [7 8 9]

	gmyStack = gmyStack[:len(gmyStack)-1]
	fmt.Println(gmyStack) // [7 8]

	gmyStack = gmyStack[:len(gmyStack)-1]
	fmt.Println(gmyStack) // [7]

	gmyStack = gmyStack[:len(gmyStack)-1]
	fmt.Println(gmyStack) // []

	fmt.Println()
	fmt.Println("> maps")
	gmyMap := map[string]int{"one": 1}
	gmyMap = make(map[string]int) // make(map type (required), capacity)
	gmyMap["one"] = 1
	gmyMap["two"] = 2
	gmyMap["three"] = 3
	fmt.Println("gmyMap", gmyMap)

	delete(gmyMap, "two")
	fmt.Println(`delete(gmyMap, "two")`)
	fmt.Println(gmyMap)

	// check if key exists
	_, exists := gmyMap["non existent key"]
	if !exists {
		fmt.Println(`gmyMap["non existent key"] doesn't exist !!`)
	}

	// looping over entries
	fmt.Println("for key, value := range gmyMap")
	for key, value := range gmyMap {
		fmt.Println(key, value)
	}

	fmt.Println()
	fmt.Println("> sets")
	fmt.Println("just use maps !!!")

	fmt.Println()
	fmt.Println("> queue")
	gmyQueue := make([]int, 0)
	gmyQueue = append(gmyQueue, 1)
	gmyQueue = append(gmyQueue, 2)
	gmyQueue = append(gmyQueue, 3)
	fmt.Println("gmyQueue", gmyQueue)
	gmyQueue = gmyQueue[1:]
	fmt.Println("gmyQueue poll", gmyQueue)
	gmyQueue = gmyQueue[1:]
	fmt.Println("gmyQueue poll", gmyQueue)
	gmyQueue = gmyQueue[1:]
	fmt.Println("gmyQueue poll", gmyQueue)

	fmt.Println()
	fmt.Println("> deque / doubly linked list using container.list")
	gmyDeque := list.New()

	printList := func(list *list.List) {
		if list.Len() == 0 {
			fmt.Println("[]")
			return
		}

		fmt.Print("[")
		node := list.Front()
		fmt.Print(node.Value)
		for node = node.Next(); node != nil; node = node.Next() {
			fmt.Print(" ", node.Value)
		}
		fmt.Print("]")
		fmt.Println()
	}

	fmt.Println("gmyDeque.PushBack(4)")
	gmyDeque.PushBack(4)
	printList(gmyDeque)

	fmt.Println("gmyDeque.PushBack(5)")
	gmyDeque.PushBack(5)
	printList(gmyDeque)

	fmt.Println("gmyDeque.PushFront(3)")
	gmyDeque.PushFront(3)
	printList(gmyDeque)

	fmt.Println("gmyDeque.PushFront(2)")
	gmyDeque.PushFront(2)
	printList(gmyDeque)

	fmt.Println("gmyDeque.PushFront(1)")
	gmyDeque.PushFront(1)
	printList(gmyDeque)

	gmyDeque.Remove(gmyDeque.Front())
	fmt.Print("gmyDeque.Remove(gmyDeque.Front()) ")
	printList(gmyDeque)

	gmyDeque.Remove(gmyDeque.Front())
	fmt.Print("gmyDeque.Remove(gmyDeque.Front()) ")
	printList(gmyDeque)

	gmyDeque.Remove(gmyDeque.Back())
	fmt.Print("gmyDeque.Remove(gmyDeque.Back()) ")
	printList(gmyDeque)

	gmyDeque.Remove(gmyDeque.Back())
	fmt.Print("gmyDeque.Remove(gmyDeque.Back()) ")
	printList(gmyDeque)

	fmt.Println()
	fmt.Println("> min heap, max heap")

	gmyHeap := MinHeap{}
	fmt.Println("gmyHeap", gmyHeap)

	heap.Push(&gmyHeap, 3)
	fmt.Println("heap.Push(gmyHeap, 3)", gmyHeap)

	heap.Push(&gmyHeap, 2)
	fmt.Println("heap.Push(gmyHeap, 2)", gmyHeap)

	heap.Push(&gmyHeap, 1)
	fmt.Println("heap.Push(gmyHeap, 1)", gmyHeap)

	fmt.Println("heap.Pop(gmyHeap)", heap.Pop(&gmyHeap), "->", gmyHeap)
	fmt.Println("heap.Pop(gmyHeap)", heap.Pop(&gmyHeap), "->", gmyHeap)
	fmt.Println("heap.Pop(gmyHeap)", heap.Pop(&gmyHeap), "->", gmyHeap)

	fmt.Println()
	fmt.Println(">>> ༼ つ ◕_◕ ༽つ generics https://go.dev/blog/intro-generics https://gobyexample.com/generics")
	fmt.Println()

	fmt.Println(`The expression [E ~string] means the set of all types whose underlying type is string.

func MyCmp[E comparable](v1, v2 E) int {
  if v1 < v2 { return -1 } else if v1 > v2 { return 1 }
  return 0
}`)

	fmt.Println()
	fmt.Println(">>> ༼ つ ◕_◕ ༽つ sorting")
	fmt.Println()

	myints := []int{3, 2, 1}
	slices.Sort(myints)
	fmt.Println(`slices.Sort([]int{3, 2, 1}) ->`, myints)

	myints = []int{1, 2, 3}
	slices.SortFunc(myints, func(a, b int) int { return b - a })
	fmt.Println(`slices.Sort([]int{1,2,3}, DESCENDING) ->`, myints)

	fmt.Println()
	fmt.Println(">>> ༼ つ ◕_◕ ༽つ Exceptions and errors")
	fmt.Println()

	func() {
		defer func() {
			panicErr := recover()
			fmt.Println("Recovered from:", panicErr) // prints "Recovered from: BIG ERROR"
		}()
		panic(errors.New("BIG ERROR"))
	}()

	fmt.Println()
	fmt.Println(">>> ༼ つ ◕_◕ ༽つ unit tests and mocks")
	fmt.Println()

	fmt.Println("Take a look at languages/go/subpkg/subsubpkg/sws_test.go")

	fmt.Println()
	fmt.Println(">>> ༼ つ ◕_◕ ༽つ async/await")
	fmt.Println()

	fmt.Print("There's no async/await in Golang. Most of the time, you'll pass a channel to a function or")
	fmt.Println("a function will return a channel to you.")

	fmt.Println()
	fmt.Println(">>> ༼ つ ◕_◕ ༽つ http requests")
	fmt.Println()

	fmt.Println()
	fmt.Println(">>> ༼ つ ◕_◕ ༽つ json parsing")
	fmt.Println()

	type MovieResponse struct {
		Title  string
		Year   int
		ImdbID string `json:"imdbID"`
	}

	// It's important to make the attributes visible outside the main package by starting their names with
	// upper case letters

	type MoviesResponse struct {
		Page       int             `json:"page"`
		PerPage    int             `json:"per_page"`
		Total      int             `json:"total"`
		TotalPages int             `json:"total_pages"`
		Data       []MovieResponse `json:"data"`
	}

	countMovies := func(title string) int {
		resp, httpErr := http.Get(fmt.Sprintf("https://jsonmock.hackerrank.com/api/moviesdata/search/?Title=%v", title))
		if httpErr != nil {
			log.Fatal(httpErr)
		}
		defer resp.Body.Close()

		body, bodyErr := io.ReadAll(resp.Body)
		if bodyErr != nil {
			log.Fatal(bodyErr)
		}

		var moviesResponse MoviesResponse
		jsonUnmarshalErr := json.Unmarshal(body, &moviesResponse)
		if jsonUnmarshalErr != nil {
			log.Fatal(jsonUnmarshalErr)
		}

		return moviesResponse.Total
	}

	countMovies("ab")

	fmt.Println()
	fmt.Println(">>> ༼ つ ◕_◕ ༽つ regex and groups")
	fmt.Println()

	compiledRegex := regexp.MustCompile(`.*"total":(?P<total>\d+).*`)
	fmt.Println(
		"regexp.Compile(`.*\"total\":(\\d+).*`).MatchString(`{\"total\":7}`) -> ",
		compiledRegex.MatchString(`{"total":7}`),
	)
	fmt.Printf("%#v\n", compiledRegex.FindStringSubmatch(`{"total":7}`)) // []string{"{\"total\":7}", "7"}
	fmt.Printf("%#v\n", compiledRegex.SubexpNames())                     // []string{"", "total"}
	fmt.Printf("%#v\n", compiledRegex.FindStringSubmatch(`{"total:7}`))  // []string(nil)

	fmt.Println()
	fmt.Println(">>> ༼ つ ◕_◕ ༽つ hashing functions")
	fmt.Println()

	fmt.Println("I'll not prioritize this for now")

	fmt.Println()
	fmt.Println(">>> ༼ つ ◕_◕ ༽つ file api")
	fmt.Println()

	fmt.Println()
	fmt.Println(">>> ༼ つ ◕_◕ ༽つ timers, sleep")
	fmt.Println()

	fmt.Println()
	fmt.Println(">>> ༼ つ ◕_◕ ༽つ concurrency, threads, virtual threads, mutexes, locks, volatile https://pkg.go.dev/sync")
	fmt.Println()

	// https://stackoverflow.com/a/20994420/4267880
	// Prior to Go 1.5, goroutines run on a single processor, only using multiple threads for blocking system calls.
	// Unless you tell the runtime to use more processors by using runtime.GOMAXPROCS(int).
	// As of Go 1.5 runtime.GOMAXPROCS() is set to the number of CPUs.
	// My current Go version is 1.23.3 which is greater than 1.5 !!!

	fmt.Println("Num of CPUs:", runtime.NumCPU())
	runtime.GOMAXPROCS(runtime.NumCPU())

	valuesChan := make(chan int)
	count := 5
	for i := 0; i < count; i++ { // Starting from Go 1.22, each iteration declares a new i variable
		// A closure is a function value that references variables from outside its body
		// Go closures capture variables by reference
		go func() {
			delay := rand.Intn(500) // [0, 500)
			time.Sleep(time.Millisecond * time.Duration(delay))
			// fmt.Println(&i) // shows a different value at each iteration
			valuesChan <- i
		}()
	}

	timeout := time.After(time.Duration(400) * time.Millisecond)

	func() {
		for i := 0; i < count; i++ {
			select { // "select" matches "send to / receive from channel" operations https://go.dev/ref/spec#Select_statements
			case value := <-valuesChan:
				fmt.Println(value)
			case <-timeout:
				fmt.Println("TIMEOUT")
				return
			}
		}
	}()

	fmt.Println()
	fmt.Println(">>> ༼ つ ◕_◕ ༽つ How to create projects/modules/packages https://go.dev/doc/tutorial/create-module")
	fmt.Println()

	fmt.Println()
	fmt.Println(">>> ༼ つ ◕_◕ ༽つ how to organize and import files https://go.dev/doc/code")
	fmt.Println()

	fmt.Println(subsubpkg.ImportName())

	fmt.Println()
	fmt.Println(">>> ༼ つ ◕_◕ ༽つ Memory model")
	fmt.Println()

	fmt.Println()
	fmt.Println(">>> ༼ つ ◕_◕ ༽つ access sqlite/mysql/postgres database avoiding sql injection")
	fmt.Println()

	fmt.Println()
	fmt.Println(">>> ༼ つ ◕_◕ ༽つ create an http server")
	fmt.Println()

	greet := func(w http.ResponseWriter, r *http.Request) {
		fmt.Fprintf(w, "Hello World! %s", time.Now())
	}

	http.HandleFunc("/", greet)
	fmt.Println("Listening on port :8080")
	http.ListenAndServe(":8080", nil)
}

// RELATED TO:
// struct (stack allocated), class (heap allocated), record (simpler class), interface

type ITalk interface {
	Talk() string
}

type Talk struct{}

func (talk *Talk) Talk() string  { return "type ITalk interface" }
func (talk Talk) String() string { return talk.Talk() }

// RELATED TO:
// inheritance, polymorphism

type MyCollection interface {
	Size() int
}

type MyCountableCollection struct { // "BASE CLASS"
	size int
}

func (myCountableCollection *MyCountableCollection) Size() int { return myCountableCollection.size }

type MyList struct {
	MyCountableCollection // Composition
}

type MyStack struct {
	MyCountableCollection // Composition
	count                 int
}

func (myStack *MyStack) Size() int { return myStack.count } // Overring MyCountableCollection.Size()

// RELATED TO:
// enums https://pkg.go.dev/syscall#SIGABRT https://gobyexample.com/enums

type Size int

const ( // iota is set to 0 whenever the const keyword appears in the code
	// SMALL Size = iota + 1
	// MEDIUM
	// LARGE
	SMALL  = Size(1)
	MEDIUM = Size(2)
	LARGE  = Size(3)
)

var sizeNames = map[Size]string{
	SMALL:  "SMALL",
	MEDIUM: "MEDIUM",
	LARGE:  "LARGE",
}

func (size Size) String() string {
	sizeName, exists := sizeNames[size]
	if !exists {
		// Golang captures this message internally and doesn't propagate it to my recover() function.
		// I think this only happens because I'm inside the String() function and probably it has special handling.
		panic(fmt.Sprintf("%T is invalid: %v", size, int(size)))
	}
	return sizeName
}

// // RELATED TO:
// // collections (list, stack, map, set, queue, deque / doubly linked list, min heap, max heap)

type MinHeap []int

func (h MinHeap) Len() int               { return len(h) }
func (h MinHeap) Less(i int, j int) bool { return h[i] < h[j] }
func (h MinHeap) Swap(i int, j int)      { h[i], h[j] = h[j], h[i] }

func (h *MinHeap) Push(x any) { *h = append(*h, x.(int)) }

// the function Pop() from the heap module swaps positions 0 and n-1 before calling my Pop() function
func (h *MinHeap) Pop() any {
	prevRoot := (*h)[len(*h)-1]
	*h = (*h)[:len(*h)-1]
	return prevRoot
}
