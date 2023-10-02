package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.maprepository.implementation.Element;
import dsw.gerumap.app.maprepository.implementation.Node;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("FieldCanBeLocal")
public class EditNodeView  extends JDialog {

    private final JColorChooser colorPicker = new JColorChooser();

    private final JLabel lbText = new JLabel("Text:");
    private final JTextField tfText = new JTextField();
    private final JLabel lbStroke = new JLabel("Stroke:");
    private final JTextField tfStroke = new JTextField();

    private final JButton btnDone = new JButton("Done");
    private final JButton btnClose = new JButton("Close");

    public EditNodeView(MapTab mapTab) {
        setUpWindow();
        setUpComponents();
        setUpButtons(mapTab);
    }

    private void setUpWindow(){
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        this.setSize((int)screenSize.getWidth()/4, (int)screenSize.getHeight()/2);
        this.setLocationRelativeTo(null);
        super.setModal(true);
        this.setSize(750, 500);

        this.setTitle("Edit Element");
        this.setLayout(new BorderLayout());
        this.add(colorPicker, BorderLayout.NORTH);
    }

    private void setUpComponents(){
        tfText.setPreferredSize(new Dimension(150, 20));
        tfStroke.setPreferredSize(new Dimension(150, 20));

        JPanel editPanel = new JPanel();
        editPanel.setLayout(new GridBagLayout());

        JPanel containerLeft = new JPanel();
        containerLeft.setLayout(new GridBagLayout());
        containerLeft.add(lbText);
        containerLeft.add(Box.createHorizontalStrut(10));
        containerLeft.add(tfText);
        editPanel.add(containerLeft);

        JPanel containerRight = new JPanel();
        containerRight.setLayout(new GridBagLayout());
        containerRight.add(lbStroke);
        containerRight.add(Box.createHorizontalStrut(10));
        containerRight.add(tfStroke);
        editPanel.add(containerRight);

        this.add(editPanel);

        JPanel containerButton = new JPanel();
        containerButton.setLayout(new GridBagLayout());
        containerButton.add(btnClose);
        containerButton.add(Box.createHorizontalStrut(10));
        containerButton.add(btnDone);
        this.add(containerButton, BorderLayout.SOUTH);
    }

    private void setUpButtons(MapTab mapTab){
        btnDone.addActionListener(e -> {
            for (Element selectedNode : mapTab.getSelectedNodes()) {
                selectedNode.setElementColor(new int[] {colorPicker.getColor().getRed(), colorPicker.getColor().getGreen(), colorPicker.getColor().getBlue()});
                try{
                    selectedNode.setStroke(Integer.parseInt(tfStroke.getText()));
                }
                catch(Exception exception) {
                    selectedNode.setStroke(2);
                }
                if (selectedNode instanceof Node)
                    ((Node)selectedNode).setText(tfText.getText());
            }
            mapTab.resetSelection();
            super.dispose();
        });

        btnClose.addActionListener(e -> super.dispose());
    }

}
