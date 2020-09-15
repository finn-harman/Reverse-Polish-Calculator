package ic.doc;

import java.util.Stack;

public class Model {

  private final Updatable reversePolishCalculatorGui;

  private Stack<Integer> stack = new Stack<>();
  private Integer output;
  private boolean justReset = false;

  public Model(Updatable reversePolishCalculatorGui) {
    this.reversePolishCalculatorGui = reversePolishCalculatorGui;
  }

  public void incrementNumber(int number) {
    stack.push(number);
  }

  public Integer getOutput() {
    return output;
  }

  public void incrementOperator(String operator) {

    if (stack.size() >= 2) {
      int second = stack.pop();
      int first = stack.pop();

      if (operator.equals("+")) {
        output = first + second;
      }

      if (operator.equals("-")) {
        output = first - second;
      }

      if (operator.equals("x")) {
        output = first * second;
      }

      stack.push(output);
    } else {
      output = null;
    }

    reversePolishCalculatorGui.update(this);
  }

  public void reset() {
    output = null;
    stack = new Stack<>();

    justReset = true;
    reversePolishCalculatorGui.update(this);
    justReset = false;
  }

  public boolean getJustReset() {
    return justReset;
  }

  public Stack<Integer> getStack() {
    return stack;
  }
}
