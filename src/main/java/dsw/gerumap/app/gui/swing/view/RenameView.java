package dsw.gerumap.app.gui.swing.view;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("FieldCanBeLocal")
public class RenameView extends JDialog {

    private final JLabel lbName = new JLabel("Name:");
    private final JTextField tfName = new JTextField();
    private final JButton btnDone = new JButton("Done");
    private final JButton btnClose = new JButton("Close");

    public RenameView(Frame owner, boolean modal, String name) {

        super(owner, modal);

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        this.setSize((int)screenSize.getWidth()/8, (int)screenSize.getHeight()/8);
        this.setLocationRelativeTo(null);

        tfName.setText(name);

        this.setTitle("Rename Dialog");
        this.setLayout(new GridLayout(4, 4));
        this.add(lbName);
        this.add(tfName);
        this.add(btnDone);
        this.add(btnClose);

        btnDone.addActionListener(e -> {
            if(!tfName.getText().isEmpty()) {
                MainFrame.getInstance().getTreeMap().getSelectedNode().getMapNode().setName(tfName.getText());
                super.dispose();
            }

        });
        btnClose.addActionListener(e -> super.dispose());

    }
}
