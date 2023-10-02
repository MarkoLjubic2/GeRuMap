package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.maprepository.implementation.Project;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("FieldCanBeLocal")
public class AddAuthorView extends JDialog {

    private final JLabel lbAuthor = new JLabel("Author");
    private final JTextField tfAuthor = new JTextField();
    private final JButton btnDone = new JButton("Done");
    private final JButton btnClose = new JButton("Close");

    public AddAuthorView(Frame owner, boolean modal, String author) {
        super(owner, modal);

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        this.setSize((int)screenSize.getWidth()/8, (int)screenSize.getHeight()/8);
        this.setLocationRelativeTo(null);

        tfAuthor.setText(author);

        this.setTitle("Add Author Dialog");
        this.setLayout(new GridLayout(4, 4));
        this.add(lbAuthor);
        this.add(tfAuthor);
        this.add(btnDone);
        this.add(btnClose);

        btnDone.addActionListener(e -> {
            ((Project) MainFrame.getInstance().getTreeMap().getSelectedNode().getMapNode()).setAuthor(tfAuthor.getText());
            if(!tfAuthor.getText().isEmpty())
                super.dispose();
        });
        btnClose.addActionListener(e -> super.dispose());

    }
}
