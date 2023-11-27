package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AuthorsPanelRepository {
    public JPanel GetCustomJPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(createTitleLabel(), BorderLayout.NORTH);
        panel.add(createAuthorPanel("Gonçalo Neves nº 22204044"), BorderLayout.CENTER);
        panel.add(createAuthorPanel("Lucas Botelho nº 22201202 "), BorderLayout.SOUTH);
        return panel;
    }

    private JLabel createTitleLabel() {
        JLabel lblTitle = new JLabel("Autores do projeto:");
        lblTitle.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 36));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        return lblTitle;
    }

    private JPanel createAuthorPanel(String authorInfo) {
        JLabel lblAuthor = new JLabel(authorInfo+"          ", SwingConstants.CENTER);
        lblAuthor.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 15));

        JButton btnAuthor = new JButton("Chumbar aluno!");
        btnAuthor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        btnAuthor.setBackground(Color.WHITE);
        btnAuthor.setFocusPainted(false);

        JPanel pnlAuthor = new JPanel();
        pnlAuthor.setLayout(new BorderLayout());
        pnlAuthor.add(lblAuthor, BorderLayout.WEST);
        pnlAuthor.add(btnAuthor, BorderLayout.CENTER);

        addMouseHoverEffect(btnAuthor, pnlAuthor, lblAuthor);

        return pnlAuthor;
    }

    private void addMouseHoverEffect(JButton button, JPanel panel, JLabel label) {
        button.addMouseListener(new MouseAdapter() {
            int hover = 0;

            @Override
            public void mouseEntered(MouseEvent e) {
                if (hover == 0) {
                    panel.add(label, BorderLayout.CENTER);
                    panel.add(button, BorderLayout.WEST);
                    panel.repaint();
                    panel.revalidate();
                    hover = 1;
                } else {
                    panel.add(label, BorderLayout.WEST);
                    panel.add(button, BorderLayout.CENTER);
                    panel.repaint();
                    panel.revalidate();
                    hover = 0;
                }
            }
        });
    }
}
