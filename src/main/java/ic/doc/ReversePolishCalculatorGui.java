package ic.doc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ReversePolishCalculatorGui implements Updatable {

  private Model press = new Model(this);

  private final JTextField textField = new JTextField(13);

  public ReversePolishCalculatorGui(int numberOfNumbers) {
    JFrame frame = new JFrame();
    frame.setSize(300, 300);
    JPanel panel = new JPanel();

    addNumberButtons(numberOfNumbers, panel);
    addOperatorButtons(panel);
    addResetButton(panel);
    panel.add(textField);

    frame.getContentPane().add(panel);
    frame.setVisible(true);
  }

  private void addOperatorButtons(JPanel panel) {
    String[] operators = new String[] {"+", "-", "x"};
    for (String operator : operators) {
      JButton button = new JButton(operator);
      button.addActionListener(actionEvent -> press.incrementOperator(button.getText()));
      panel.add(button);
    }
  }

  private void addResetButton(JPanel panel) {
    JButton button = new JButton("Reset");
    button.addActionListener(actionEvent -> press.reset());
    panel.add(button);
  }

  private void addNumberButtons(int numberOfNumbers, JPanel panel) {
    for (int i = 0; i < numberOfNumbers; i++) {
      JButton button = new JButton(Integer.toString(i));
      button.addActionListener(
          actionEvent -> press.incrementNumber(Integer.parseInt(button.getText())));
      panel.add(button);
    }
  }

  @Override
  public void update(Model model) {
    if (model.getOutput() != null) {
      textField.setText(String.valueOf(model.getOutput()));
    } else if (!model.getJustReset()) {
      textField.setText("Error: Invalid Operation");
    } else {
      textField.setText("");
    }
  }

  public static void main(String[] args) {
    new ReversePolishCalculatorGui(10);
  }
}
