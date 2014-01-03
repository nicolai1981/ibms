package com.nicoinc.system.ibms.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import com.nicoinc.system.ibms.command.CommandListener;
import com.nicoinc.system.ibms.command.GenerationCreate;
import com.nicoinc.system.ibms.command.GenerationGetList;
import com.nicoinc.system.ibms.command.MemberGetList;
import com.nicoinc.system.ibms.command.RequestResult;
import com.nicoinc.system.ibms.main.Application;
import com.nicoinc.system.ibms.model.Generation;
import com.nicoinc.system.ibms.model.Member;

public class ViewGenerationNew extends JPanel implements CommandListener {
    private static final long serialVersionUID = 5607527208663662381L;
    private FrameHome mHome;
    private JTextField mName;
    private JButton mButtonSave;
    private JProgressBar mProgressBar;
    private JComboBox mLeader;

    public ViewGenerationNew(FrameHome home) {
        mHome = home;
        JLabel lblNewLabel = new JLabel("CRIAR GERA\u00C7\u00C3O");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel lblNewLabel_1 = new JLabel("Nome");
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));

        mName = new JTextField();
        mName.setFont(new Font("Arial", Font.PLAIN, 14));
        mName.setColumns(10);

        JLabel lblLder = new JLabel("L\u00EDder");
        lblLder.setFont(new Font("Arial", Font.PLAIN, 14));

        mLeader = new JComboBox();
        mLeader.setFont(new Font("Arial", Font.PLAIN, 14));

        mButtonSave = new JButton("Criar");
        mButtonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Generation generation = checkData();
                if (generation != null) {
                    disableFields();
                    new GenerationCreate(generation, ViewGenerationNew.this).run();
                }
            }
        });
        mButtonSave.setSelectedIcon(null);
        mButtonSave.setIcon(new ImageIcon(ViewGenerationNew.class.getResource("/com/nicoinc/system/ibms/resources/button_save.png")));
        mButtonSave.setFont(new Font("Arial", Font.PLAIN, 14));

        mProgressBar = new JProgressBar();
        mProgressBar.setIndeterminate(true);
        mProgressBar.setVisible(false);

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.TRAILING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addComponent(mProgressBar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                        .addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                        .addComponent(lblNewLabel_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                        .addComponent(lblLder, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mLeader, Alignment.LEADING, 0, 430, Short.MAX_VALUE)
                        .addComponent(mButtonSave, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(10)
                    .addComponent(lblNewLabel)
                    .addGap(15)
                    .addComponent(lblNewLabel_1)
                    .addGap(5)
                    .addComponent(mName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(10)
                    .addComponent(lblLder)
                    .addGap(5)
                    .addComponent(mLeader, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                    .addComponent(mButtonSave, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                    .addGap(5)
                    .addComponent(mProgressBar, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                    .addGap(10))
        );
        setLayout(groupLayout);

        // Fill leader list
        Member invalid = new Member();
        invalid.mName = "NENHUM";
        mLeader.addItem(invalid);
        for (Member item : Application.getInstance().getLeaderActivatedList()) {
            boolean found = false;
            for (Generation generation : Application.getInstance().getGenerationActivatedList()) {
                if (generation.mLeaderId == item.mId) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                mLeader.addItem(item);
            }
        }
    }

    @Override
    public void onCommandFinished(RequestResult result) {
        switch(result.getCode()) {
        case WITHOUT_CONNECTION:
            JOptionPane.showMessageDialog(this,"Sem conex�o com a internet.\nN�o foi poss�vel completar a a��o.");
            enableFields();
            break;
        case SERVER_ERROR:
            JOptionPane.showMessageDialog(this, "Erro no servidor. C�digo: " + result.getData("ERROR_CODE") + "\nTente mais tarde.");
            enableFields();
            break;
        case UNKNOWN:
            JOptionPane.showMessageDialog(this,"Erro desconhecido.\nFeche o aplicativo e tente novamente.");
            enableFields();
            break;
        case OK:
            switch (result.getCommand()) {
            case GENERATION_GET_LIST:
                new MemberGetList(this).start();
                break;

            case MEMBER_GET_LIST:
                JOptionPane.showMessageDialog(this,"Gera��o criada com sucesso.");

                // Update leader list
                mLeader.removeAllItems();
                Member invalid = new Member();
                invalid.mName = "NENHUM";
                mLeader.addItem(invalid);

                for (Member item : Application.getInstance().getLeaderActivatedList()) {
                    boolean found = false;
                    for (Generation generation : Application.getInstance().getGenerationActivatedList()) {
                        if (generation.mLeaderId == item.mId) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        mLeader.addItem(item);
                    }
                }
                enableFields();
                mHome.showSelectGeneration();
                break;

            case GENERATION_CREATE:
                mName.setText("");
                mLeader.setSelectedIndex(0);
                new GenerationGetList(this).start();
                break;

            default:
                break;
            }
        }
    }

    private void enableFields() {
        mName.setEnabled(true);
        mLeader.setEnabled(true);
        mButtonSave.setEnabled(true);
        mProgressBar.setVisible(false);
    }

    private void disableFields() {
        mName.setEnabled(false);
        mLeader.setEnabled(false);
        mButtonSave.setEnabled(false);
        mProgressBar.setVisible(true);
    }

    private Generation checkData() {
        String name = mName.getText().trim().toUpperCase();
        if (name.length() == 0) {
            JOptionPane.showMessageDialog(this,"O nome da gera��o deve ser preenchido.");
            return null;
        }

        for (Generation item : Application.getInstance().getGenerationAllList()) {
            if (name.equals(item.mName)) {
                JOptionPane.showMessageDialog(this,"O nome da gera��o j� existe.");
                return null;
            }
        }

        Member leader = (Member) mLeader.getSelectedItem();
        if (leader == null) {
            JOptionPane.showMessageDialog(this,"Selecione algum l�der.");
            return null;
        }

        for (Generation item : Application.getInstance().getGenerationActivatedList()) {
            if ((leader.mId != 0) && (leader.mId == item.mLeaderId)) {
                JOptionPane.showMessageDialog(this,"O l�der j� � l�der de uma gera��o.");
                return null;
            }
        }

        Generation generation = new Generation();
        generation.mName = name;
        generation.mLeaderId = leader.mId;
        generation.mStartDate = Calendar.getInstance().getTime();
        return generation;
    }
}
