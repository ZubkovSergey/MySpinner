import javax.accessibility.Accessible;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
    private int borderThickness = 3;


    private SpinnerNumberModel model = null;
    JTextField editField;
    JButton bLeft;
    JButton bRight;
    final int  componentHeight = 24;

    public ZSpinner(SpinnerNumberModel model) {
        if(model == null)
            model = new SpinnerNumberModel(100,0,null,10);
        else this.model = model;
        EmptyBorder border = new EmptyBorder(borderThickness,borderThickness,borderThickness,borderThickness);
        setBorder(border);

        setLayout(new BorderLayout());
        editField = new JTextField();
        editField.setPreferredSize(new Dimension(50,componentHeight - borderThickness));
//        editField.setMaximumSize(new Dimension(75,componentHeight));
        editField.setEditable(false);

        JButton bLeft = new JButton(new ImageIcon(SpinnerNumberSample.class.getResource("left_small.png")));
        bLeft.setPreferredSize(new Dimension(componentHeight - borderThickness,componentHeight - 2*borderThickness));
        bLeft.setBackground(Color.LIGHT_GRAY);
        JButton bRight = new JButton(new ImageIcon(SpinnerNumberSample.class.getResource("right_small.png")));
        bRight.setPreferredSize(new Dimension(componentHeight - borderThickness,componentHeight - 2*borderThickness));
        bRight.setBackground(Color.LIGHT_GRAY);
        add(bLeft, BorderLayout.WEST);
        add(editField, BorderLayout.CENTER);
        add(bRight, BorderLayout.EAST);

        setMinimumSize(new Dimension(75,componentHeight));
        setMaximumSize(new Dimension(150,componentHeight));

        model.setValue(model.getNextValue());
        editField.setText(model.getPreviousValue().toString() + " - " + model.getValue().toString());

        bLeft.addActionListener( new PreviousPage() );

        bRight.addActionListener( new NextPage());
    }


    public void stateChanged(ChangeEvent e) {
//        System.out.println("---");
    }

    //
    class NextPage extends AbstractAction{
        public void actionPerformed(ActionEvent e) {
//            putValue(Action.NAME,"Вызов следующей страницы");
//            System.out.println(this.getValue(NAME));
            try {
                model.setValue(model.getNextValue());
                editField.setText(model.getPreviousValue().toString() + " - " + model.getValue().toString());
            }catch (IllegalArgumentException ia){
//                ia.printStackTrace();
            }
        }
    }

    class PreviousPage extends AbstractAction{
        public void actionPerformed(ActionEvent e) {
//            putValue(Action.NAME,"Вызов предыдущей страницы");
//            System.out.println(this.getValue(NAME));
            try {
                model.setValue(model.getPreviousValue());
                editField.setText(model.getPreviousValue().toString() + " - " + model.getValue().toString());
            }catch (IllegalArgumentException  ia){
//                ia.printStackTrace();
            }catch( NullPointerException np){
//                np.printStackTrace();
            }
        }
    }


//    private class ModelListener implements ChangeListener, Serializable {
//        public void stateChanged(ChangeEvent e) {
//            fireStateChanged();
//        }
//    }
//
//    protected void fireStateChanged() {
//        Object[] listeners = listenerList.getListenerList();
//        for (int i = listeners.length - 2; i >= 0; i -= 2) {
//            if (listeners[i] == ChangeListener.class) {
//                if (changeEvent == null) {
//                    changeEvent = new ChangeEvent(this);
//                }
//                ((ChangeListener)listeners[i+1]).stateChanged(changeEvent);
//            }
//        }
//    }

    class ZButton extends AbstractButton implements Accessible{
        ZButton(){

        }

        ZButton(String title){

        }



    }

    class  LeftArrow extends Canvas{
        int whidth;
        int higth;

        LeftArrow(int whidth,int higth){}

        public void paint(Graphics g){
            int w = getSize().width;
            int h = getSize().height;
            g.drawLine(5,5,5,15);
        }
    }

}
