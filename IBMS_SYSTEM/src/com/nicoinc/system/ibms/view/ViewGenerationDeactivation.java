package com.nicoinc.system.ibms.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.google.gson.JsonObject;
import com.nicoinc.system.ibms.command.CommandListener;
import com.nicoinc.system.ibms.command.GenerationGetList;
import com.nicoinc.system.ibms.command.RequestResult;
import com.nicoinc.system.ibms.command.GenerationUpdate;
import com.nicoinc.system.ibms.main.Application;
import com.nicoinc.system.ibms.model.Generation;
import com.nicoinc.system.ibms.model.Member;
import javax.swing.SwingConstants;

public class ViewGenerationDeactivation extends JPanel implements CommandListener {
    private static final long serialVersionUID = 7561701453305316660L;

    private static final SimpleDateFormat sDateFormatter = new SimpleDateFormat("dd/MM/yyyy");

    private JLabel mLeader;
    private JComboBox mGenerationList;
    private JLabel mStartDate;
    private JLabel mStatus;
    private JLabel mEndDate;
    private JButton mButtonAction;
    private JProgressBar mProgressBar;

    private Generation mCurrentGeneration = null;
    
    public ViewGenerationDeactivation() {
        JLabel lblAtivarDesativar = new JLabel("ATIVAR / DESATIVAR GERA\u00C7\u00C3O");
        lblAtivarDesativar.setHorizontalAlignment(SwingConstants.CENTER);
        lblAtivarDesativar.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel lblGerao = new JLabel("Gera\u00E7\u00E3o");
        lblGerao.setFont(new Font("Arial", Font.PLAIN, 14));

        mGenerationList = new JComboBox();
        mGenerationList.setFont(new Font("Arial", Font.PLAIN, 14));
        mGenerationList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                mCurrentGeneration = (Generation) mGenerationList.getSelectedItem();
                if (mCurrentGeneration != null) {
                    mStartDate.setText(sDateFormatter.format(mCurrentGeneration.mStartDate));
                    if (mCurrentGeneration.mEndDate.getTime() == 0) {
                        mStatus.setText("ATIVO");
                        mEndDate.setText("-");
                        mButtonAction.setText("DESATIVAR");
                    } else {
                        mStatus.setText("INATIVO");
                        mEndDate.setText(sDateFormatter.format(mCurrentGeneration.mEndDate));
                        mButtonAction.setText("ATIVAR");
                    }
                    Member leader = Application.getInstance().getLeader(mCurrentGeneration.mLeaderId);
                    if (leader == null) {
                        mLeader.setText("-");
                    } else {
                        mLeader.setText(leader.mName);
                    }
                }
            }
        });

        JLabel label = new JLabel("L\u00EDder");
        label.setFont(new Font("Arial", Font.PLAIN, 14));

        mLeader = new JLabel("-");
        mLeader.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblDataDeCriao = new JLabel("Data de Cria\u00E7\u00E3o");
        lblDataDeCriao.setFont(new Font("Arial", Font.PLAIN, 14));

        mStartDate = new JLabel("DD/MM/AAAA");
        mStartDate.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblSituao = new JLabel("Situa\u00E7\u00E3o");
        lblSituao.setFont(new Font("Arial", Font.PLAIN, 14));

        mStatus = new JLabel("-");
        mStatus.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblDataDeDesativao = new JLabel("Data de Desativa\u00E7\u00E3o");
        lblDataDeDesativao.setFont(new Font("Arial", Font.PLAIN, 14));

        mEndDate = new JLabel("DD/MM/AAAA");
        mEndDate.setFont(new Font("Arial", Font.BOLD, 14));

        mButtonAction = new JButton("-");
        mButtonAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (mCurrentGeneration.mEndDate.getTime() == 0) {
                    int result = JOptionPane.showConfirmDialog(ViewGenerationDeactivation.this, "Deseja realmente desativar a geração " + mCurrentGeneration.mName);
                    if (result != 0) {
                        return;
                    }
                }

                disableFields();

                Generation generation = new Generation();
                generation.mId = mCurrentGeneration.mId;
                generation.mName = mCurrentGeneration.mName;
                generation.mLeaderId = mCurrentGeneration.mLeaderId;
                generation.mStartDate = mCurrentGeneration.mStartDate;
                if (mCurrentGeneration.mEndDate.getTime() == 0) {
                    generation.mEndDate = Calendar.getInstance().getTime();
                }
                new GenerationUpdate(generation, ViewGenerationDeactivation.this).start();
            }
        });
        mButtonAction.setIcon(null);
        mButtonAction.setFont(new Font("Arial", Font.PLAIN, 14));

        mProgressBar = new JProgressBar();
        mProgressBar.setIndeterminate(true);
        mProgressBar.setVisible(false);

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblAtivarDesativar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
                                .addComponent(lblGerao, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(mGenerationList, Alignment.LEADING, 0, 555, Short.MAX_VALUE))
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(label, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(ComponentPlacement.RELATED, 517, Short.MAX_VALUE))
                                .addComponent(mLeader, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)))
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addComponent(mStartDate)
                                .addComponent(lblDataDeCriao, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(lblSituao, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(mStatus, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addComponent(lblDataDeDesativao)
                                .addComponent(mEndDate))
                            .addGap(697))
                        .addComponent(mButtonAction)
                        .addComponent(mProgressBar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE))
                    .addContainerGap())
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(lblAtivarDesativar, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                            .addGap(18)
                            .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblGerao)
                                .addComponent(label, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                .addComponent(mGenerationList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(mLeader)))
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGap(105)
                            .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblDataDeCriao, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblSituao, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblDataDeDesativao, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                            .addComponent(mStatus, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                            .addComponent(mEndDate, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                        .addComponent(mStartDate, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(mButtonAction)
                    .addGap(18)
                    .addComponent(mProgressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(432, Short.MAX_VALUE))
        );
        setLayout(groupLayout);

        // Fill generation list
        for (Generation item : Application.getInstance().getGenerationList()) {
            mGenerationList.addItem(item);
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
            case GENERATION_GET_LIST:
                mGenerationList.removeAllItems();
                for (Generation item : Application.getInstance().getGenerationList()) {
                    mGenerationList.addItem(item);
                }
                JOptionPane.showMessageDialog(this,"Geração alterada com sucesso.");
                enableFields();
                break;

            case GENERATION_UPDATE:
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
        mGenerationList.setEnabled(true);
        mButtonAction.setEnabled(true);
        mProgressBar.setVisible(false);
    }

    private void disableFields() {
        mGenerationList.setEnabled(false);
        mButtonAction.setEnabled(false);
        mProgressBar.setVisible(true);
    }
}
