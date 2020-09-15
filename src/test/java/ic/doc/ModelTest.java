package ic.doc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ModelTest {

  private final Model model = new Model(model -> { });

  @Test
  public void stackEmptyAtInitialisation() {
    assertThat(model.getStack().empty(), is(true));
  }

  @Test
  public void resetCreatesEmptyStack() {
    model.incrementNumber(1);
    model.reset();
    assertThat(model.getStack().empty(), is(true));
  }

  @Test
  public void addNumberAddsNumberToStack() {
    model.incrementNumber(1);

    assertThat(model.getStack().peek(), is(1));
  }

  @Test
  public void resetResetsStack() {
    model.incrementNumber(1);

    assertThat(model.getStack().peek(), is(1));
  }

  @Test
  public void addTwoNumbersAddsNumbersToStack() {
    model.incrementNumber(1);
    model.incrementNumber(2);

    assertThat(model.getStack().peek(), is(2));
    model.reset();
  }

  @Test
  public void simpleAdditionWorks() {
    model.incrementNumber(3);
    model.incrementNumber(2);
    model.incrementOperator("+");

    assertThat(model.getOutput(), is(5));
    model.reset();
  }

  @Test
  public void simpleSubtractionWorks() {
    model.incrementNumber(3);
    model.incrementNumber(2);
    model.incrementOperator("-");

    assertThat(model.getOutput(), is(1));
    model.reset();
  }

  @Test
  public void simpleMultiplicationWorks() {
    model.incrementNumber(3);
    model.incrementNumber(2);
    model.incrementOperator("x");

    assertThat(model.getOutput(), is(6));
    model.reset();
  }

  @Test
  public void multipleOperationsWork() {
    model.incrementNumber(3);
    model.incrementNumber(2);
    model.incrementOperator("-");
    model.incrementNumber(3);
    model.incrementNumber(2);
    model.incrementOperator("x");
    model.incrementOperator("+");

    assertThat(model.getOutput(), is(7));
    model.reset();
  }
}
