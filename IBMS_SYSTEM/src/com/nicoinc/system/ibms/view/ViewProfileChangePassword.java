package com.nicoinc.system.ibms.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import com.nicoinc.system.ibms.command.CommandListener;
import com.nicoinc.system.ibms.command.RequestResult;
import com.nicoinc.system.ibms.command.UserChangePassword;

public class ViewProfileChangePassword extends JPanel implements CommandListener {
    private static final long serialVersionUID = 5607527208663662381L;
    private JPasswordField mFieldOldPwd;
    private JProgressBar mProgressBar;
    private JPasswordField mFieldNewPwd;
    private JPasswordField mFieldCheckNewPwd;
    private JButton mButtonSave;

    public ViewProfileChangePassword() {
        JLabel lblNewLabel = new JLabel("ALTERAR SENHA");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel lblNewLabel_1 = new JLabel("Senha atual");
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));

        mFieldOldPwd = new JPasswordField();
        mFieldOldPwd.setFont(new Font("Arial", Font.PLAIN, 14));
        mFieldOldPwd.setColumns(10);

        mButtonSave = new JButton("Salvar");
        mButtonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (checkData()) {
                    disableFields();
                    new UserChangePassword(FrameHome.getUser().mId, new String(mFieldOldPwd.getPassword()).trim(),
                            new String(mFieldNewPwd.getPassword()).trim(), ViewProfileChangePassword.this).run();
                }
            }
        });
        mButtonSave.setSelectedIcon(null);
        mButtonSave.setIcon(new ImageIcon(ViewProfileChangePassword.class
                .getResource("/com/nicoinc/system/ibms/resources/button_save.png")));
        mButtonSave.setFont(new Font("Arial", Font.PLAIN, 14));

        mProgressBar = new JProgressBar();
        mProgressBar.setIndeterminate(true);
        mProgressBar.setVisible(false);

        JLabel lblNovaSenha = new JLabel("Nova senha");
        lblNovaSenha.setFont(new Font("Arial", Font.PLAIN, 14));

        mFieldNewPwd = new JPasswordField();
        mFieldNewPwd.setFont(new Font("Arial", Font.PLAIN, 14));
        mFieldNewPwd.setColumns(10);

        JLabel lblConfirmeANova = new JLabel("Confirme a nova senha");
        lblConfirmeANova.setFont(new Font("Arial", Font.PLAIN, 14));

        mFieldCheckNewPwd = new JPasswordField();
        mFieldCheckNewPwd.setFont(new Font("Arial", Font.PLAIN, 14));
        mFieldCheckNewPwd.setColumns(10);

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(
                groupLayout
                        .createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                groupLayout
                                        .createParallelGroup(Alignment.LEADING)
                                        .addComponent(mFieldOldPwd, GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE)
                                        .addComponent(lblNewLabel_1)
                                        .addComponent(lblNovaSenha, GroupLayout.PREFERRED_SIZE, 76,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(mFieldNewPwd, GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE)
                                        .addComponent(mFieldCheckNewPwd, GroupLayout.DEFAULT_SIZE, 1020,
                                                Short.MAX_VALUE).addComponent(mButtonSave)
                                        .addComponent(mProgressBar, GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE)
                                        .addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE)
                                        .addComponent(lblConfirmeANova)).addContainerGap()));
        groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
                groupLayout
                        .createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblNewLabel)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(lblNewLabel_1)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(mFieldOldPwd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(lblNovaSenha, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(mFieldNewPwd, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(lblConfirmeANova, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(mFieldCheckNewPwd, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                        .addGap(18)
                        .addComponent(mButtonSave, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(mProgressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE).addContainerGap(359, Short.MAX_VALUE)));
        setLayout(groupLayout);
    }

    @Override
    public void onCommandFinished(RequestResult result) {
        enableFields();

        switch (result.getCode()) {
        case WITHOUT_CONNECTION:
            JOptionPane.showMessageDialog(this, "Sem conexão com a internet.\nNão foi possível completar a ação.");
            break;
        case SERVER_ERROR:
            switch ((Integer)result.getData("ERROR_CODE")) {
            case 1:
                JOptionPane.showMessageDialog(this, "A senha não confere.");
                break;
            case 2:
                JOptionPane.showMessageDialog(this, "Não foi possível alterar a senha.\nTente novamente.");
                break;
            default:
                JOptionPane.showMessageDialog(this, "Erro no servidor.\nTente mais tarde.");
            }
            break;
        case UNKNOWN:
            JOptionPane.showMessageDialog(this, "Erro desconhecido.\nFeche o aplicativo e tente novamente.");
            break;
        case OK:
            switch (result.getCommand()) {
            case USER_CHANGE_PASSWORD:
                mFieldOldPwd.setText("");
                mFieldNewPwd.setText("");
                mFieldCheckNewPwd.setText("");
                JOptionPane.showMessageDialog(this, "Senha alterada com sucesso.");
                break;

            default:
                break;
            }
        }
    }

    private void enableFields() {
        mFieldOldPwd.setEnabled(true);
        mFieldNewPwd.setEnabled(true);
        mFieldCheckNewPwd.setEnabled(true);
        mButtonSave.setEnabled(true);
        mProgressBar.setVisible(false);
    }

    private void disableFields() {
        mFieldOldPwd.setEnabled(false);
        mFieldNewPwd.setEnabled(false);
        mFieldCheckNewPwd.setEnabled(false);
        mButtonSave.setEnabled(false);
        mProgressBar.setVisible(true);
    }

    private boolean checkData() {
        String oldPwd = new String(mFieldOldPwd.getPassword()).trim();
        if (oldPwd.length() == 0) {
            JOptionPane.showMessageDialog(this, "A senha deve ser preenchida.");
            return false;
        }

        String newPwd = new String(mFieldNewPwd.getPassword()).trim();
        if (newPwd.length() == 0) {
            JOptionPane.showMessageDialog(this, "A nova senha deve ser preenchida.");
            return false;
        }

        String checkNewPwd = new String(mFieldCheckNewPwd.getPassword()).trim();
        if (checkNewPwd.length() == 0) {
            JOptionPane.showMessageDialog(this, "A confirmação da nova senha deve ser preenchida.");
            return false;
        }

        if (!newPwd.equals(checkNewPwd)) {
            JOptionPane.showMessageDialog(this, "A nova senha e a confirmação não estão iguais.");
            return false;
        }

        if (newPwd.equals(oldPwd)) {
            JOptionPane.showMessageDialog(this, "A nova senha não pode ser a mesma da antiga.");
            return false;
        }

        return true;
    }
}
