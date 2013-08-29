package com.nicoinc.system.ibms.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import javax.swing.border.LineBorder;

import com.google.gson.JsonObject;
import com.nicoinc.system.ibms.command.CommandListener;
import com.nicoinc.system.ibms.command.GenerationGetList;
import com.nicoinc.system.ibms.command.RequestResult;
import com.nicoinc.system.ibms.command.GenerationUpdate;
import com.nicoinc.system.ibms.main.Application;
import com.nicoinc.system.ibms.model.Generation;
import com.nicoinc.system.ibms.model.Member;

public class ViewGenerationEdit extends JPanel implements CommandListener {
    private static final SimpleDateFormat sDateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    private static final long serialVersionUID = -8213291145000189731L;
    private JTextField mName;
    private JButton mButtonSave;
    private JComboBox mLeaderList;
    private JComboBox mGenerationList;
    private JProgressBar mProgressBar;
    private Generation mCurrentGeneration;
    private JLabel mStartDate;

    public ViewGenerationEdit() {
        JLabel lblNewLabel = new JLabel("ALTERAR GERA\u00C7\u00C3O");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel lblGerao = new JLabel("Gera\u00E7\u00E3o a ser alterada");
        lblGerao.setFont(new Font("Arial", Font.PLAIN, 14));

        mGenerationList = new JComboBox();
        mGenerationList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                mCurrentGeneration = (Generation) mGenerationList.getSelectedItem();
                if (mCurrentGeneration != null) {
                    mName.setText(mCurrentGeneration.mName);
                    mStartDate.setText(sDateFormatter.format(mCurrentGeneration.mStartDate));
                    Member leader = Application.getInstance().getLeader(mCurrentGeneration.mLeaderId);
                    if (leader == null) {
                        mLeaderList.setSelectedIndex(-1);
                    } else {
                        mLeaderList.setSelectedItem(leader);
                    }
                }
            }
        });
        mGenerationList.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(0, 0, 0)));

        JLabel lblNewLabel_1 = new JLabel("Nome");
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));

        mName = new JTextField();
        mName.setFont(new Font("Arial", Font.PLAIN, 14));
        mName.setColumns(10);

        JLabel lblLder = new JLabel("L\u00EDder");
        lblLder.setFont(new Font("Arial", Font.PLAIN, 14));

        mLeaderList = new JComboBox();
        mLeaderList.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblDataDaCriao = new JLabel("Data da Cria\u00E7\u00E3o");
        lblDataDaCriao.setFont(new Font("Arial", Font.PLAIN, 14));

        mStartDate = new JLabel("DD/MM/AAAA");
        mStartDate.setFont(new Font("Arial", Font.BOLD, 14));

        mButtonSave = new JButton("Salvar");
        mButtonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Generation generation = checkData();
                if (generation != null) {
                    mName.setEnabled(false);
                    mGenerationList.setEnabled(false);
                    mLeaderList.setEnabled(false);
                    mButtonSave.setEnabled(false);
                    mProgressBar.setVisible(true);
                    new GenerationUpdate(generation, ViewGenerationEdit.this).run();
                }
            }
        });
        mButtonSave.setIcon(new ImageIcon(ViewGenerationEdit.class.getResource("/com/nicoinc/system/ibms/resources/button_save.png")));
        mButtonSave.setFont(new Font("Arial", Font.PLAIN, 14));

        mProgressBar = new JProgressBar();
        mProgressBar.setIndeterminate(true);
        mProgressBar.setVisible(false);

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.TRAILING)
                .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(mProgressBar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
                        .addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
                        .addComponent(lblGerao, GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
                        .addComponent(mGenerationList, 0, 1110, Short.MAX_VALUE)
                        .addComponent(panel, GroupLayout.PREFERRED_SIZE, 1108, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mButtonSave))
                    .addContainerGap())
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblNewLabel)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(lblGerao, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(mGenerationList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(mButtonSave)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(mProgressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(369, Short.MAX_VALUE))
        );

        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                            .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                .addGroup(gl_panel.createSequentialGroup()
                                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                        .addComponent(mName, GroupLayout.PREFERRED_SIZE, 539, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNewLabel_1))
                                    .addPreferredGap(ComponentPlacement.UNRELATED)
                                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblLder)
                                        .addComponent(mLeaderList, 0, 535, Short.MAX_VALUE)))
                                .addComponent(lblDataDaCriao))
                            .addContainerGap())
                        .addGroup(gl_panel.createSequentialGroup()
                            .addComponent(mStartDate)
                            .addContainerGap(1004, Short.MAX_VALUE))))
        );
        gl_panel.setVerticalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNewLabel_1)
                        .addComponent(lblLder))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                        .addComponent(mLeaderList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(lblDataDaCriao)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(mStartDate)
                    .addContainerGap(213, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);
        setLayout(groupLayout);

        // Fill generation list
        for (Generation item : Application.getInstance().getGenerationList()) {
            if (item.mEndDate.getTime() == 0) {
                mGenerationList.addItem(item);
            }
        }

        // Fill leader list
        Member invalid = new Member();
        invalid.mName = "NENHUM";
        mLeaderList.addItem(invalid);
        for (Member item : Application.getInstance().getLeaderList()) {
            mLeaderList.addItem(item);
        }
    }

    @Override
    public void onCommandFinished(RequestResult result) {
        switch(result.getCode()) {
        case WITHOUT_CONNECTION:
            JOptionPane.showMessageDialog(this,"Sem conexão com a internet.\nNão foi possível completar a ação.");
            enableFields();
            break;
        case SERVER_ERROR:
            JOptionPane.showMessageDialog(this,"Erro no servidor.\nTente mais tarde.");
            enableFields();
            break;
        case UNKNOWN:
            JOptionPane.showMessageDialog(this,"Erro desconhecido.\nFeche o aplicativo e tente novamente.");
            enableFields();
            break;
        case OK:
            switch (result.getCommand()) {
            case GET_GENERATION_LIST:
                mGenerationList.removeAllItems();
                for (Generation item : Application.getInstance().getGenerationList()) {
                    if (item.mEndDate.getTime() == 0) {
                        mGenerationList.addItem(item);
                    }
                }
                JOptionPane.showMessageDialog(this,"Geração alterada com sucesso.");
                enableFields();
                break;
            case UPDATE_GENERATION:
                JsonObject root = result.getJSON();
                if (root.has("ERROR_CODE")) {
                    switch (root.getAsInt()) {
                    case 0:
                        JOptionPane.showMessageDialog(this,"Erro");
                        enableFields();
                        break;
                    default:
                        JOptionPane.showMessageDialog(this,"Código de erro desconhecido. Código: " + root.getAsInt());
                        enableFields();
                        break;
                    }
                } else {
                    new GenerationGetList(this).start();
                }
                break;
            default:
                break;
            }
        }
    }

    private void enableFields() {
        mName.setEnabled(true);
        mGenerationList.setEnabled(true);
        mLeaderList.setEnabled(true);
        mButtonSave.setEnabled(true);
        mProgressBar.setVisible(false);
    }

    private Generation checkData() {
        String name = mName.getText().trim().toUpperCase();
        if (name.length() == 0) {
            JOptionPane.showMessageDialog(this,"O nome da geração deve ser preenchido.");
            return null;
        }

        for (Generation item : Application.getInstance().getGenerationList()) {
            if (name.equals(item.mName) && !name.equals(mCurrentGeneration.mName)) {
                JOptionPane.showMessageDialog(this,"O nome da geração já existe.");
                return null;
            }
        }

        Member leader = (Member) mLeaderList.getSelectedItem();
        if (leader == null) {
            JOptionPane.showMessageDialog(this,"Selecione algum líder.");
            return null;
        }

        for (Generation item : Application.getInstance().getGenerationList()) {
            if ((leader.mId != 0) && (leader.mId == item.mLeaderId) && (leader.mId != mCurrentGeneration.mLeaderId)) {
                JOptionPane.showMessageDialog(this,"O líder já é líder de uma geração.");
                return null;
            }
        }

        Generation generation = new Generation();
        generation.mId = mCurrentGeneration.mId;
        generation.mName = name;
        generation.mLeaderId = leader.mId;
        generation.mStartDate = new Date(mCurrentGeneration.mStartDate.getTime());
        return generation;
    }
}
