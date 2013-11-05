package com.nicoinc.system.ibms.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

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
import com.nicoinc.system.ibms.command.GenerationGetList;
import com.nicoinc.system.ibms.command.GenerationUpdate;
import com.nicoinc.system.ibms.command.RequestResult;
import com.nicoinc.system.ibms.main.Application;
import com.nicoinc.system.ibms.model.Generation;
import com.nicoinc.system.ibms.model.Member;

public class ViewGenerationEdit extends JPanel implements CommandListener {
    private static final SimpleDateFormat sDateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    private static final long serialVersionUID = -8213291145000189731L;
    private JTextField mName;
    private JButton mButtonSave;
    private JComboBox<Member> mLeaderList;
    private JProgressBar mProgressBar;
    private JLabel mStartDate;
    private FrameHome mHome;
    private Generation mGeneration;

    public ViewGenerationEdit(FrameHome home, Generation generation) {
        mHome = home;
        mGeneration = generation;

        JLabel lblNewLabel = new JLabel("ALTERAR GERA\u00C7\u00C3O");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));

        mButtonSave = new JButton("Salvar");
        mButtonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Generation generation = checkData();
                if (generation != null) {
                    disableFields();
                    new GenerationUpdate(generation, ViewGenerationEdit.this).run();
                }
            }
        });
        mButtonSave.setIcon(new ImageIcon(ViewGenerationEdit.class
                .getResource("/com/nicoinc/system/ibms/resources/button_save.png")));
        mButtonSave.setFont(new Font("Arial", Font.PLAIN, 14));

        mProgressBar = new JProgressBar();
        mProgressBar.setIndeterminate(true);
        mProgressBar.setVisible(false);

        JLabel lblNewLabel_1 = new JLabel("Nome");
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));

        mName = new JTextField();
        mName.setFont(new Font("Arial", Font.PLAIN, 14));
        mName.setColumns(10);
        mName.setText(mGeneration.mName);

        JLabel lblLder = new JLabel("L\u00EDder");
        lblLder.setFont(new Font("Arial", Font.PLAIN, 14));

        mLeaderList = new JComboBox<Member>();
        mLeaderList.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblDataDaCriao = new JLabel("Data da Cria\u00E7\u00E3o");
        lblDataDaCriao.setFont(new Font("Arial", Font.PLAIN, 14));

        mStartDate = new JLabel(sDateFormatter.format(mGeneration.mStartDate));
        mStartDate.setFont(new Font("Arial", Font.BOLD, 14));

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
                groupLayout
                        .createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                groupLayout
                                        .createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1108,
                                                Short.MAX_VALUE).addComponent(lblNewLabel_1)
                                        .addComponent(mName, GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
                                        .addComponent(lblLder).addComponent(mLeaderList, 0, 1110, Short.MAX_VALUE)
                                        .addComponent(lblDataDaCriao).addComponent(mStartDate)
                                        .addComponent(mButtonSave)
                                        .addComponent(mProgressBar, GroupLayout.DEFAULT_SIZE, 1108, Short.MAX_VALUE))
                        .addContainerGap()));
        groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
                groupLayout
                        .createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblNewLabel)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addGap(18)
                        .addComponent(lblNewLabel_1)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(mName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(lblLder)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(mLeaderList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addGap(18)
                        .addComponent(lblDataDaCriao)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(mStartDate)
                        .addGap(18)
                        .addComponent(mButtonSave)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(mProgressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE).addContainerGap(357, Short.MAX_VALUE)));
        setLayout(groupLayout);

        // Fill leader list
        Member invalid = new Member();
        invalid.mName = "NENHUM";
        mLeaderList.addItem(invalid);

        for (Member item : Application.getInstance().getLeaderAllList()) {
            mLeaderList.addItem(item);
            if (item.mId == mGeneration.mLeaderId) {
                mLeaderList.setSelectedItem(item);
            }
        }
    }

    @Override
    public void onCommandFinished(RequestResult result) {
        switch (result.getCode()) {
        case WITHOUT_CONNECTION:
            JOptionPane.showMessageDialog(this, "Sem conexão com a internet.\nNão foi possível completar a ação.");
            enableFields();
            break;
        case SERVER_ERROR:
            JOptionPane.showMessageDialog(this, "Erro no servidor.\nTente mais tarde.");
            enableFields();
            break;
        case UNKNOWN:
            JOptionPane.showMessageDialog(this, "Erro desconhecido.\nFeche o aplicativo e tente novamente.");
            enableFields();
            break;
        case OK:
            switch (result.getCommand()) {
            case GENERATION_GET_LIST:
                JOptionPane.showMessageDialog(this, "Geração alterada com sucesso.");
                mHome.showSelectGeneration();
                break;

            case GENERATION_UPDATE:
                new GenerationGetList(this).start();
                break;

            default:
                break;
            }
        }
    }

    private void enableFields() {
        mName.setEnabled(true);
        mLeaderList.setEnabled(true);
        mButtonSave.setEnabled(true);
        mProgressBar.setVisible(false);
    }

    private void disableFields() {
        mName.setEnabled(false);
        mLeaderList.setEnabled(false);
        mButtonSave.setEnabled(false);
        mProgressBar.setVisible(true);
    }

    private Generation checkData() {
        String name = mName.getText().trim().toUpperCase();
        if (name.length() == 0) {
            JOptionPane.showMessageDialog(this, "O nome da geração deve ser preenchido.");
            return null;
        }

        for (Generation item : Application.getInstance().getGenerationAllList()) {
            if (name.equals(item.mName) && (mGeneration.mId != item.mId)) {
                JOptionPane.showMessageDialog(this, "O nome da geração já existe.");
                return null;
            }
        }

        Member leader = (Member) mLeaderList.getSelectedItem();
        if (leader == null) {
            JOptionPane.showMessageDialog(this, "Selecione algum líder.");
            return null;
        }

        for (Generation item : Application.getInstance().getGenerationAllList()) {
            if ((leader.mId != 0) && (leader.mId == item.mLeaderId) && (item.mId != mGeneration.mId)) {
                JOptionPane.showMessageDialog(this, "O líder já é líder de uma geração.");
                return null;
            }
        }

        Generation generation = new Generation();
        generation.mId = mGeneration.mId;
        generation.mName = name;
        generation.mLeaderId = leader.mId;
        generation.mStartDate = mGeneration.mStartDate;
        generation.mEndDate = mGeneration.mEndDate;
        return generation;
    }
}
