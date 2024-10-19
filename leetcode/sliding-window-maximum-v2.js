class Heap {
  constructor(args) {
    this._values = [];
    this._comparator = args?.comparator || ((a, b) => a - b);
    if (args?.values) this.pushAll(args.values);
  }

  left = (i) => (i << 1) + 1;
  hasLeft = (i) => this.left(i) < this._values.length;
  leftValue = (i) => this._values[this.left(i)];

  right = (i) => (i << 1) + 2;
  hasRight = (i) => this.right(i) < this._values.length;
  rightValue = (i) => this._values[this.right(i)];

  parent = (i) => (i - 1) >> 1;
  parentValue = (i) => this._values[this.parent(i)];

  swap(i, j) {
    const tmp = this._values[i];
    this._values[i] = this._values[j];
    this._values[j] = tmp;
  }

  isEmpty = () => this._values.length == 0;

  peek() {
    if (!this.isEmpty()) {
      return this._values[0];
    }
  }

  bubbleUp() {
    let i = this._values.length - 1;
    while (i > 0) {
      if (this._comparator(this._values[i], this.parentValue(i)) >= 0) break;
      this.swap(i, this.parent(i));
      i = this.parent(i);
    }
  }

  push(value) {
    this._values.push(value);
    this.bubbleUp();
  }

  pushAll = (values) => values.forEach(this.push, this);

  bubbleDown() {
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

  pop() {
    if (this.isEmpty()) return;
    const value = this.peek();
    const lastValue = this._values.pop();
    if (!this.isEmpty()) {
      this._values[0] = lastValue;
    }
    this.bubbleDown();
    return value;
  }
}

const maximumValueOfEachSubarray = (array, k) => {
  if (k <= 0) return [];

  const output = [];
  const heap = new Heap({ comparator: (a, b) => b.value - a.value });

  for (let i = 0; i < k - 1; i++) {
    heap.push({ value: array[i], index: i });
  }

  for (let i = k - 1; i < array.length; i++) {
    heap.push({ value: array[i], index: i });
    while (heap.peek().index <= i - k) {
      heap.pop();
    }
    output.push(heap.peek().value);
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
