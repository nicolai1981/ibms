package com.nicoinc.system.ibms.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import com.nicoinc.system.ibms.command.CommandListener;
import com.nicoinc.system.ibms.command.GenerationGetList;
import com.nicoinc.system.ibms.command.GenerationUpdate;
import com.nicoinc.system.ibms.command.RequestResult;
import com.nicoinc.system.ibms.main.Application;
import com.nicoinc.system.ibms.model.Generation;

public class ViewGenerationSelect extends JPanel implements CommandListener {
    private static final long serialVersionUID = -8213291145000189731L;
    private JButton mButtonEdit;
    private JButton mButtonEnable;
    private JButton mButtonView;
    private JProgressBar mProgressBar;
    private JTable mMemberList;
    private FrameHome mHome;

    public ViewGenerationSelect(FrameHome home) {
        mHome = home;

        JLabel lblNewLabel = new JLabel("SELECIONE A GERAÇÃO");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        mMemberList = new JTable(new MemberTableModel());
        mMemberList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel listSelectionModel = mMemberList.getSelectionModel();
        listSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    Generation generation = Application.getInstance().getGenerationAllList()
                            .get(mMemberList.getSelectedRow());
                    mButtonEdit.setEnabled(generation.mEndDate.getTime() == 0);
                    mButtonEnable.setText((generation.mEndDate.getTime() == 0) ? "Desativar" : "Ativar");
                }
            }
        });
        scrollPane.setViewportView(mMemberList);

        mButtonEdit = new JButton("Editar");
        mButtonEdit.setIcon(new ImageIcon(ViewMemberSelect.class
                .getResource("/com/nicoinc/system/ibms/resources/button_save.png")));
        mButtonEdit.setFont(new Font("Arial", Font.PLAIN, 14));
        mButtonEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (mMemberList.getSelectedRow() != -1) {
                    disableFields();
                    Generation generation = Application.getInstance().getGenerationAllList()
                            .get(mMemberList.getSelectedRow());
                    mHome.showEditGeneration(generation);
                }
            }
        });

        mProgressBar = new JProgressBar();
        mProgressBar.setIndeterminate(true);
        mProgressBar.setVisible(false);

        mButtonEnable = new JButton("Ativar");
        mButtonEnable.setFont(new Font("Arial", Font.PLAIN, 14));
        mButtonEnable.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (mMemberList.getSelectedRow() != -1) {
                    disableFields();
                    Generation generation = Application.getInstance().getGenerationAllList()
                            .get(mMemberList.getSelectedRow());
                    if (generation.mEndDate.getTime() == 0) {
                        generation.mEndDate = Calendar.getInstance().getTime();
                    } else {
                        generation.mEndDate = new Date(0);
                    }
                    new GenerationUpdate(generation, ViewGenerationSelect.this).start();
                }
            }
        });

        mButtonView = new JButton("Detalhar");
        mButtonView.setFont(new Font("Arial", Font.PLAIN, 14));

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
                groupLayout
                        .createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                groupLayout
                                        .createParallelGroup(Alignment.TRAILING)
                                        .addGroup(
                                                groupLayout
                                                        .createSequentialGroup()
                                                        .addComponent(mProgressBar, GroupLayout.DEFAULT_SIZE, 1110,
                                                                Short.MAX_VALUE).addContainerGap())
                                        .addGroup(
                                                groupLayout
                                                        .createSequentialGroup()
                                                        .addComponent(mButtonEdit, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGap(79)
                                                        .addComponent(mButtonEnable, GroupLayout.PREFERRED_SIZE, 97,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addGap(68)
                                                        .addComponent(mButtonView, GroupLayout.PREFERRED_SIZE, 97,
                                                                GroupLayout.PREFERRED_SIZE).addGap(688))
                                        .addGroup(
                                                groupLayout
                                                        .createSequentialGroup()
                                                        .addGroup(
                                                                groupLayout
                                                                        .createParallelGroup(Alignment.TRAILING)
                                                                        .addComponent(scrollPane, Alignment.LEADING,
                                                                                GroupLayout.DEFAULT_SIZE, 1110,
                                                                                Short.MAX_VALUE)
                                                                        .addComponent(lblNewLabel,
                                                                                GroupLayout.DEFAULT_SIZE, 1110,
                                                                                Short.MAX_VALUE)).addContainerGap()))));
        groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
                groupLayout
                        .createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblNewLabel)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addGroup(
                                groupLayout
                                        .createParallelGroup(Alignment.LEADING)
                                        .addGroup(
                                                groupLayout
                                                        .createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(mButtonEdit)
                                                        .addComponent(mButtonEnable, GroupLayout.PREFERRED_SIZE, 33,
                                                                GroupLayout.PREFERRED_SIZE))
                                        .addComponent(mButtonView, GroupLayout.PREFERRED_SIZE, 33,
                                                GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(mProgressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE).addContainerGap()));
        setLayout(groupLayout);
    }

    @Override
    public void onCommandFinished(RequestResult result) {
        switch (result.getCode()) {
        case WITHOUT_CONNECTION:
            JOptionPane.showMessageDialog(this, "Sem conexão com a internet.\nNão foi possível completar a ação.");
            enableFields();
            break;
        case SERVER_ERROR:
            JOptionPane.showMessageDialog(this, "Erro no servidor. Código: " + result.getData("ERROR_CODE")
                    + "\nTente mais tarde.");
            enableFields();
            break;
        case UNKNOWN:
            JOptionPane.showMessageDialog(this, "Erro desconhecido.\nFeche o aplicativo e tente novamente.");
            enableFields();
            break;
        case OK:
            switch (result.getCommand()) {
            case GENERATION_GET_LIST:
                JOptionPane.showMessageDialog(this, "Dados da geração alterados com sucesso.");
                mMemberList.invalidate();
                enableFields();
                if (mMemberList.getSelectedRow() != -1) {
                    Generation generation = Application.getInstance().getGenerationAllList()
                            .get(mMemberList.getSelectedRow());
                    mButtonEdit.setEnabled(generation.mEndDate.getTime() == 0);
                    mButtonEnable.setText((generation.mEndDate.getTime() == 0) ? "Desativar" : "Ativar");
                }
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
        mButtonEdit.setEnabled(true);
        mButtonEnable.setEnabled(true);
        mButtonView.setEnabled(true);
        mMemberList.setEnabled(true);
        mProgressBar.setVisible(false);
    }

    private void disableFields() {
        mButtonEdit.setEnabled(false);
        mButtonEnable.setEnabled(false);
        mButtonView.setEnabled(false);
        mMemberList.setEnabled(false);
        mProgressBar.setVisible(true);
    }

    private static class MemberTableModel extends AbstractTableModel {
        private static final long serialVersionUID = 2139100891339821696L;
        private static final String[] COLUMN_NAMES = { "NOME", "LÍDER", "ESTADO" };

        @Override
        public int getColumnCount() {
            return COLUMN_NAMES.length;
        }

        @Override
        public int getRowCount() {
            return Application.getInstance().getGenerationAllList().size();
        }

        @Override
        public String getColumnName(int column) {
            return COLUMN_NAMES[column];
        }

        @Override
        public Object getValueAt(int row, int column) {
            if (row > Application.getInstance().getGenerationAllList().size()) {
                return null;
            }
            Generation generation = Application.getInstance().getGenerationAllList().get(row);
            switch (column) {
            case 0:
                return generation.mName;
            case 1:
                return generation.mLeaderName;
            case 2:
                return generation.mEndDate.getTime() == 0 ? "ATIVO" : "DESATIVADO";
            }
            return null;
        }
    }
}
