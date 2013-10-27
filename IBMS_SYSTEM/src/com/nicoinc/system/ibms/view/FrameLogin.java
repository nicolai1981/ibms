package com.nicoinc.system.ibms.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.nicoinc.system.ibms.command.Command;
import com.nicoinc.system.ibms.command.CommandListener;
import com.nicoinc.system.ibms.command.Login;
import com.nicoinc.system.ibms.command.RequestResult;
import com.nicoinc.system.ibms.model.Member;

public class FrameLogin extends JFrame implements CommandListener {
    private static final long serialVersionUID = -1343380208031960886L;
    private JPanel contentPane;
    private JComboBox<Member> mFieldUser;
    private JPasswordField mFieldPassword;
    private JButton mButtonLogin;
    private JProgressBar mProgress;

    /**
     * Create the frame.
     */
    public FrameLogin(List<Member> userList) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 370);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JButton btnNewButton_1 = new JButton("");
        btnNewButton_1.setForeground(new Color(240, 240, 240));
        btnNewButton_1.setIcon(new ImageIcon(FrameLogin.class
                .getResource("/com/nicoinc/system/ibms/resources/logo.png")));

        JLabel labelUser = new JLabel("Usu\u00E1rio");
        labelUser.setHorizontalAlignment(SwingConstants.LEFT);
        labelUser.setFont(new Font("Arial", Font.BOLD, 16));

        mFieldUser = new JComboBox<Member>();
        mFieldUser.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setHorizontalAlignment(SwingConstants.LEFT);
        lblSenha.setFont(new Font("Arial", Font.BOLD, 16));

        mFieldPassword = new JPasswordField();
        mFieldPassword.setFont(new Font("Arial", Font.PLAIN, 12));

        mButtonLogin = new JButton("Conectar");
        mButtonLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (checkData()) {
                    new Login(((Member) mFieldUser.getSelectedItem()).mId, new String(mFieldPassword.getPassword())
                            .trim(), FrameLogin.this).start();
                    mFieldUser.setEnabled(false);
                    mFieldPassword.setEnabled(false);
                    mButtonLogin.setEnabled(false);
                    mProgress.setVisible(true);
                }
            }
        });
        mButtonLogin.setIcon(new ImageIcon(FrameLogin.class
                .getResource("/com/nicoinc/system/ibms/resources/button_login.png")));
        mButtonLogin.setFont(new Font("Arial", Font.BOLD, 14));

        mProgress = new JProgressBar();
        mProgress.setIndeterminate(true);
        mProgress.setVisible(false);

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane
                .createParallelGroup(Alignment.LEADING)
                .addGroup(
                        gl_contentPane.createSequentialGroup().addContainerGap()
                                .addComponent(labelUser, GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
                                .addContainerGap())
                .addGroup(
                        gl_contentPane.createSequentialGroup().addGap(202)
                                .addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 385, Short.MAX_VALUE)
                                .addGap(187))
                .addGroup(
                        gl_contentPane.createSequentialGroup().addContainerGap()
                                .addComponent(mFieldUser, 0, 754, Short.MAX_VALUE).addContainerGap())
                .addGroup(
                        gl_contentPane.createSequentialGroup().addContainerGap()
                                .addComponent(lblSenha, GroupLayout.PREFERRED_SIZE, 754, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(
                        gl_contentPane.createSequentialGroup().addContainerGap().addComponent(mButtonLogin)
                                .addContainerGap(639, Short.MAX_VALUE))
                .addGroup(
                        Alignment.TRAILING,
                        gl_contentPane.createSequentialGroup().addContainerGap()
                                .addComponent(mProgress, GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
                                .addContainerGap())
                .addGroup(
                        gl_contentPane.createSequentialGroup().addContainerGap()
                                .addComponent(mFieldPassword, GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
                                .addContainerGap()));
        gl_contentPane
                .setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(
                        gl_contentPane
                                .createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnNewButton_1)
                                .addGap(11)
                                .addComponent(labelUser)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(mFieldUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(lblSenha, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(mFieldPassword, GroupLayout.PREFERRED_SIZE, 26,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(mButtonLogin)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(mProgress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        contentPane.setLayout(gl_contentPane);

        // Fill leader list
        Member member = new Member();
        member.mName = "NENHUM";
        mFieldUser.addItem(member);
        for (Member item : userList) {
            if (item.mEndDate.getTime() == 0) {
                mFieldUser.addItem(item);
            }
        }
    }

    private boolean checkData() {
        if (mFieldUser.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Selecione o usu�rio.");
            return false;
        }

        String pwd = new String(mFieldPassword.getPassword()).trim();
        if (pwd.length() == 0) {
            JOptionPane.showMessageDialog(this, "A senha deve ser preenchida.");
            return false;
        }
        return true;
    }

    @Override
    public void onCommandFinished(RequestResult result) {
        mFieldUser.setEnabled(true);
        mFieldPassword.setEnabled(true);
        mButtonLogin.setEnabled(true);
        mProgress.setVisible(false);

        switch (result.getCode()) {
        case WITHOUT_CONNECTION:
            JOptionPane.showMessageDialog(this, "Sem conex�o com a internet.\nN�o foi poss�vel completar a a��o.");
            break;
        case SERVER_ERROR:
            switch ((Integer) result.getData("ERROR_CODE")) {
            case 1:
                JOptionPane.showMessageDialog(this, "Senha n�o confere.");
                break;
            default:
                JOptionPane.showMessageDialog(this, "Erro no servidor.\nTente mais tarde.");
                break;
            }
            break;
        case UNKNOWN:
            JOptionPane.showMessageDialog(this, "Erro desconhecido.\nFeche o aplicativo e tente novamente.");
            break;
        case OK:
            try {
                Command.setToken((String) result.getData("TOKEN"));
                new FrameLoginLoading((Integer) result.getData("TYPE"), (Member) mFieldUser.getSelectedItem())
                        .setVisible(true);
                FrameLogin.this.dispose();
            } catch (Throwable e) {
                e.printStackTrace();
            }
            break;
        }
    }
}
