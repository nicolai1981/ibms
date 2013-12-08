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
import com.nicoinc.system.ibms.command.MemberGetList;
import com.nicoinc.system.ibms.command.MemberGetMember;
import com.nicoinc.system.ibms.command.MemberUpdate;
import com.nicoinc.system.ibms.command.RequestResult;
import com.nicoinc.system.ibms.main.Application;
import com.nicoinc.system.ibms.model.Member;

public class ViewMemberSelect extends JPanel implements CommandListener {
    private static final long serialVersionUID = -8213291145000189731L;
    private JButton mButtonEdit;
    private JButton mButtonEnable;
    private JButton mButtonView;
    private JProgressBar mProgressBar;
    private JTable mMemberList;
    private FrameHome mHome;
    private boolean mUpdateData = false;
    private JButton mButtonNew;

    public ViewMemberSelect(FrameHome home) {
        mHome = home;

        JLabel lblNewLabel = new JLabel("SELECIONE O MEMBRO");
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
                    Member member = Application.getInstance().getMemberAllList().get(mMemberList.getSelectedRow());
                    mButtonEdit.setEnabled(member.mEndDate.getTime() == 0);
                    mButtonEnable.setText((member.mEndDate.getTime() == 0) ? "Desativar" : "Ativar");
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
                    Member member = Application.getInstance().getMemberAllList().get(mMemberList.getSelectedRow());
                    new MemberGetMember(member.mId, ViewMemberSelect.this).start();
                }
            }
        });

        mProgressBar = new JProgressBar();
        mProgressBar.setIndeterminate(true);
        mProgressBar.setVisible(false);

        mButtonEnable = new JButton("Ativar");
        mButtonEnable.setIcon(new ImageIcon(ViewMemberSelect.class
                .getResource("/com/nicoinc/system/ibms/resources/button_deactived.png")));
        mButtonEnable.setFont(new Font("Arial", Font.PLAIN, 14));
        mButtonEnable.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (mMemberList.getSelectedRow() != -1) {
                    disableFields();
                    mUpdateData = true;
                    Member member = Application.getInstance().getMemberAllList().get(mMemberList.getSelectedRow());
                    new MemberGetMember(member.mId, ViewMemberSelect.this).start();
                }
            }
        });

        mButtonView = new JButton("Detalhar");
        mButtonView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (mMemberList.getSelectedRow() != -1) {
                    Member member = Application.getInstance().getMemberAllList().get(mMemberList.getSelectedRow());
                    mHome.showDetailsMember(member);
                }
            }
        });
        mButtonView.setIcon(new ImageIcon(ViewMemberSelect.class
                .getResource("/com/nicoinc/system/ibms/resources/button_details.png")));
        mButtonView.setFont(new Font("Arial", Font.PLAIN, 14));

        mButtonNew = new JButton("Novo");
        mButtonNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mHome.showNewMember();
            }
        });
        mButtonNew.setIcon(new ImageIcon(ViewMemberSelect.class
                .getResource("/com/nicoinc/system/ibms/resources/button_new.png")));
        mButtonNew.setFont(new Font("Arial", Font.PLAIN, 14));

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
                groupLayout
                        .createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                groupLayout
                                        .createParallelGroup(Alignment.TRAILING)
                                        .addGroup(
                                                Alignment.LEADING,
                                                groupLayout
                                                        .createSequentialGroup()
                                                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 981,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                        .addGroup(
                                                                groupLayout
                                                                        .createParallelGroup(Alignment.TRAILING)
                                                                        .addComponent(mButtonNew,
                                                                                GroupLayout.DEFAULT_SIZE, 263,
                                                                                Short.MAX_VALUE)
                                                                        .addComponent(mButtonEdit,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                        .addComponent(mButtonEnable, Alignment.LEADING,
                                                                                GroupLayout.DEFAULT_SIZE, 263,
                                                                                Short.MAX_VALUE)
                                                                        .addComponent(mButtonView,
                                                                                GroupLayout.DEFAULT_SIZE, 263,
                                                                                Short.MAX_VALUE)))
                                        .addComponent(mProgressBar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1110,
                                                Short.MAX_VALUE)
                                        .addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE))
                        .addContainerGap()));
        groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
                groupLayout
                        .createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblNewLabel)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addGroup(
                                groupLayout
                                        .createParallelGroup(Alignment.LEADING)
                                        .addGroup(
                                                groupLayout
                                                        .createSequentialGroup()
                                                        .addComponent(mButtonNew, GroupLayout.PREFERRED_SIZE, 33,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                        .addComponent(mButtonEdit)
                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                        .addComponent(mButtonEnable, GroupLayout.PREFERRED_SIZE, 33,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                        .addComponent(mButtonView, GroupLayout.PREFERRED_SIZE, 33,
                                                                GroupLayout.PREFERRED_SIZE))
                                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE))
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
            case MEMBER_GET_LIST:
                JOptionPane.showMessageDialog(this, "Dados do membro alterados com sucesso.");
                mMemberList.invalidate();
                enableFields();
                if (mMemberList.getSelectedRow() != -1) {
                    Member member = Application.getInstance().getMemberAllList().get(mMemberList.getSelectedRow());
                    mButtonEdit.setEnabled(member.mEndDate.getTime() == 0);
                    mButtonEnable.setText((member.mEndDate.getTime() == 0) ? "Desativar" : "Ativar");
                }
                break;
            case MEMBER_UPDATE:
                new MemberGetList(this).start();
                break;
            case MEMBER_GET_MEMBER:
                Member member = (Member) result.getData("MEMBER");
                if (mUpdateData) {
                    mUpdateData = false;
                    if (member.mEndDate.getTime() == 0) {
                        member.mEndDate = Calendar.getInstance().getTime();
                    } else {
                        member.mEndDate = new Date(0);
                    }
                    new MemberUpdate(member, ViewMemberSelect.this).start();
                } else {
                    if (member != null) {
                        mHome.showEditMember(member);
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "Não foi possível receber os dados do membro.\nFeche o aplicativo e tente novamente.");
                    }
                    enableFields();
                }
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
        private static final String[] COLUMN_NAMES = { "NOME", "LÍDER", "GERAÇÃO", "ESTADO" };

        @Override
        public int getColumnCount() {
            return COLUMN_NAMES.length;
        }

        @Override
        public int getRowCount() {
            return Application.getInstance().getMemberAllList().size();
        }

        @Override
        public String getColumnName(int column) {
            return COLUMN_NAMES[column];
        }

        @Override
        public Object getValueAt(int row, int column) {
            if (row > Application.getInstance().getMemberAllList().size()) {
                return null;
            }
            Member member = Application.getInstance().getMemberAllList().get(row);
            switch (column) {
            case 0:
                return member.mName;
            case 1:
                return member.mLeaderName;
            case 2:
                return member.mGenerationName;
            case 3:
                return member.mEndDate.getTime() == 0 ? "ATIVO" : "DESLIGADO";
            }
            return null;
        }
    }
}
