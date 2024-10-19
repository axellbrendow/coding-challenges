type Comparator<T> = (val1: T, val2: T) => number;

interface HeapConstructorArgs<T> {
  values?: T[];
  comparator?: Comparator<T>;
}

class Heap<T> {
  private _values: T[];
  private _comparator: Comparator<T>;

  constructor(args?: HeapConstructorArgs<T>) {
    this._values = [];
    this._comparator = args?.comparator || ((a: any, b: any) => a - b);
    if (args?.values) this.pushAll(args.values);
  }

  private left = (i: number) => (i << 1) + 1;
  private hasLeft = (i: number) => this.left(i) < this._values.length;
  private leftValue = (i: number) => this._values[this.left(i)];

  private right = (i: number) => (i << 1) + 2;
  private hasRight = (i: number) => this.right(i) < this._values.length;
  private rightValue = (i: number) => this._values[this.right(i)];

  private parent = (i: number) => (i - 1) >> 1;
  private parentValue = (i: number) => this._values[this.parent(i)];

  private swap(i: number, j: number) {
    const tmp = this._values[i];
    this._values[i] = this._values[j];
    this._values[j] = tmp;
  }

  public isEmpty = () => this._values.length == 0;

  public peek() {
    if (!this.isEmpty()) {
      return this._values[0];
    }
  }

  private bubbleUp() {
    let i = this._values.length - 1;
    while (i > 0) {
      if (this._comparator(this._values[i], this.parentValue(i)) >= 0) break;
      this.swap(i, this.parent(i));
      i = this.parent(i);
    }
  }

  public push(value: T) {
    this._values.push(value);
    this.bubbleUp();
  }

  public pushAll = (values: T[]) => values.forEach(this.push, this);

  private bubbleDown() {
    let i = 0;
    while (this.hasLeft(i)) {
      if (
        this.hasRight(i) &&
        this._comparator(this._values[i], this.rightValue(i)) > 0 &&
        this._comparator(this.rightValue(i), this.leftValue(i)) <= 0
      ) {
        this.swap(i, this.right(i));
        i = this.right(i);
      } else if (this._comparator(this._values[i], this.leftValue(i)) > 0) {
        this.swap(i, this.left(i));
        i = this.left(i);
      } else {
        break;
      }
    }
  }

  public pop() {
    if (this.isEmpty()) return;
    const value = this.peek();
    const lastValue = this._values.pop() as T;
    if (!this.isEmpty()) {
      this._values[0] = lastValue;
    }
    this.bubbleDown();
    return value;
  }
}

interface ValueAndIndex {
  value: number;
  index: number;
}

const maximumValueOfEachSubarray = (array: number[], k: number) => {
  if (k <= 0) return [];

  const output: number[] = [];
  const heap = new Heap<ValueAndIndex>({
    comparator: (a, b) => b.value - a.value,
  });

  for (let i = 0; i < k - 1; i++) {
    heap.push({ value: array[i], index: i });
  }

  for (let i = k - 1; i < array.length; i++) {
    heap.push({ value: array[i], index: i });
    // @ts-ignore
    while (heap.peek()?.index <= i - k) {
      heap.pop();
    }
    // @ts-ignore
    output.push(heap.peek()?.value);
  }

  return output;
};

let testNumber = 0;

let nums = [10, 5, 2, 7, 8, 7];
let k = 1;
let expectedOutput = [10, 5, 2, 7, 8, 7];
let output = maximumValueOfEachSubarray(nums, k);
let testPassed = JSON.stringify(output) == JSON.stringify(expectedOutput);
console.log("Test " + ++testNumber + (testPassed ? " succeeded" : " failed"));

nums = [10, 5, 2, 7, 8, 7];
k = 3;
expectedOutput = [10, 7, 8, 8];
output = maximumValueOfEachSubarray(nums, k);
testPassed = JSON.stringify(output) == JSON.stringify(expectedOutput);
console.log("Test " + ++testNumber + (testPassed ? " succeeded" : " failed"));
