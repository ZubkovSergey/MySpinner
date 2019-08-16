import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.Serializable;

import static com.sun.javafx.fxml.expression.Expression.add;

public class ZSpinner extends JPanel implements ChangeListener {

    private JComponent editor;
    private ChangeListener modelListener;
    private transient ChangeEvent changeEvent;
    private boolean editorExplicitlySet = false;


    private SpinnerModel model = null;
    JTextField editField;
    JButton bLeft;
    JButton bRight;
    final int  componentHeight = 24;

    public ZSpinner(SpinnerModel model) {
        if(model == null)
            model = new SpinnerNumberModel(100,0,null,10);
        else this.model = model;

        setLayout(new BorderLayout());
        editField = new JTextField();
        editField.setPreferredSize(new Dimension(100,componentHeight));
//        editField.setMaximumSize(new Dimension(75,componentHeight));
        editField.setEditable(false);

        JButton bLeft = new JButton("<");
        bLeft.setPreferredSize(new Dimension(componentHeight,componentHeight));
        bLeft.setBackground(Color.LIGHT_GRAY);
        JButton bRight = new JButton(">");
        bRight.setPreferredSize(new Dimension(componentHeight,componentHeight));
        bRight.setBackground(Color.LIGHT_GRAY);
        add(bLeft, BorderLayout.WEST);
        add(editField, BorderLayout.CENTER);
        add(bRight, BorderLayout.EAST);

        setMinimumSize(new Dimension(75,componentHeight));
        setMaximumSize(new Dimension(150,componentHeight));

        editField.setText(model.getValue().toString());

        bLeft.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
//                model.getPreviousValue();
            }
        });

        bRight.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {

            }
        });

    }


    public void stateChanged(ChangeEvent e) {
        System.out.println("---");
    }

    class Action extends AbstractAction{

        public void actionPerformed(ActionEvent e) {
            System.out.println("dddd");
        }
    }




    private class ModelListener implements ChangeListener, Serializable {
        public void stateChanged(ChangeEvent e) {
            fireStateChanged();
        }
    }

    protected void fireStateChanged() {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ChangeListener.class) {
                if (changeEvent == null) {
                    changeEvent = new ChangeEvent(this);
                }
                ((ChangeListener)listeners[i+1]).stateChanged(changeEvent);
            }
        }
    }

}
