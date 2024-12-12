/*-
I got this question from another person

Create an algorithm to simulate the execution of a traffic light.

It must have a start method, which starts the color transitions.

A stop method, which stops the transitions.

When stopping and restarting the traffic light, it must restart in the same color it stopped in before.

The start method must receive as a parameter which colors, and the transition time between one color and another.

The traffic light must remain running forever, and only stops when the stop method is activated.
*/

interface ColorTransition {
  color: string;
  duration: number;
  nextColor: string;
}

class Semaphore {
  private color?: string;
  private colorTransitionsMap?: Record<string, ColorTransition>;
  private remainingDuration?: number;
  private timeoutStart?: number;
  private timeoutId?: number;

  public getColor = () => this.color;

  public start = (colorTransitions: ColorTransition[]) => {
    console.log("start");
    this.colorTransitionsMap = {};
    for (const colorTransition of colorTransitions) {
      this.colorTransitionsMap[colorTransition.color] = colorTransition;
    }
    if (this.remainingDuration) this.restart();
    else this.setColor(colorTransitions[0].color);
  };

  public stop = () => {
    console.log("stop");
    clearTimeout(this.timeoutId);
    const timePassed = Date.now() - (this.timeoutStart as number);
    this.remainingDuration =
      (this.colorTransitionsMap?.[this.color as string].duration as number) -
      timePassed;
  };

  private setColor = (newColor: string, duration?: number) => {
    this.color = newColor;
    this.remainingDuration = 0;
    this.timeoutStart = Date.now();
    console.log(
      newColor.padEnd(6, " "),
      new Date(this.timeoutStart).toISOString().substring(0, 19)
    );
    // @ts-ignore
    this.timeoutId = setTimeout(
      () =>
        this.setColor(this.colorTransitionsMap?.[newColor].nextColor as string),
      duration || this.colorTransitionsMap?.[newColor].duration
    );
  };

  private restart = () => {
    this.setColor(this.color as string, this.remainingDuration);
  };
}

const semaphore = new Semaphore();

const colorTransitions: ColorTransition[] = [
  { color: "green", duration: 4000, nextColor: "yellow" },
  { color: "yellow", duration: 1000, nextColor: "red" },
  { color: "red", duration: 3000, nextColor: "green" },
];

semaphore.start(colorTransitions);

let assertCount = 1;

console.assert(semaphore.getColor() === "green", `${assertCount++}`); // green for 4 seconds

setTimeout(() => {
  console.assert(semaphore.getColor() === "green", `${assertCount++}`); // green for 4 seconds
}, 1000);

setTimeout(() => {
  console.assert(semaphore.getColor() === "green", `${assertCount++}`); // green for 4 seconds
}, 2000);

setTimeout(() => {
  console.assert(semaphore.getColor() === "green", `${assertCount++}`); // green for 4 seconds
}, 3000);

setTimeout(() => {
  console.assert(semaphore.getColor() === "green", `${assertCount++}`); // green for 4 seconds
}, 3990);

// I need to add/remove 10ms because timers are not 100% accurate

setTimeout(() => {
  console.assert(semaphore.getColor() === "yellow", `${assertCount++}`); // yellow for 1 second
}, 4010);

setTimeout(() => {
  semaphore.stop(); // Stop after 300ms. When we start again, yellow should be the color for more 700ms only.
}, 4300);

setTimeout(() => {
  console.assert(semaphore.getColor() === "yellow", `${assertCount++}`); // After 5.5 seconds, the color is still yellow as expected
  semaphore.start(colorTransitions); // Restart semaphore
}, 10000);

setTimeout(() => {
  console.assert(semaphore.getColor() === "yellow", `${assertCount++}`); // After 510ms the color should still be yellow
}, 10510);

setTimeout(() => {
  console.assert(semaphore.getColor() === "red", `${assertCount++}`); // After 710ms the color changed to red
}, 10710);
