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
import com.nicoinc.system.ibms.command.CourseTypeGetList;
import com.nicoinc.system.ibms.command.CourseTypeUpdate;
import com.nicoinc.system.ibms.command.RequestResult;
import com.nicoinc.system.ibms.main.Application;
import com.nicoinc.system.ibms.model.CourseType;

public class ViewCourseTypeSelect extends JPanel implements CommandListener {
    private static final long serialVersionUID = -8213291145000189731L;
    private JButton mButtonEdit;
    private JButton mButtonEnable;
    private JProgressBar mProgressBar;
    private JTable mList;
    private FrameHome mHome;
    private JButton mButtonDetails;

    public ViewCourseTypeSelect(FrameHome home) {
        mHome = home;

        JLabel lblNewLabel = new JLabel("SELECIONE O TIPO DE CURSO");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        mList = new JTable(new TableModel());
        mList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel listSelectionModel = mList.getSelectionModel();
        listSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    CourseType courseType = Application.getInstance().getCourseTypeAllList()
                            .get(mList.getSelectedRow());
                    mButtonEdit.setEnabled(courseType.mEndDate.getTime() == 0);
                    mButtonEnable.setText((courseType.mEndDate.getTime() == 0) ? "Desativar" : "Ativar");
                }
            }
        });
        scrollPane.setViewportView(mList);

        mButtonEdit = new JButton("Editar");
        mButtonEdit.setIcon(new ImageIcon(ViewMemberSelect.class
                .getResource("/com/nicoinc/system/ibms/resources/button_save.png")));
        mButtonEdit.setFont(new Font("Arial", Font.PLAIN, 14));
        mButtonEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (mList.getSelectedRow() != -1) {
                    disableFields();
                    CourseType courseType = Application.getInstance().getCourseTypeAllList()
                            .get(mList.getSelectedRow());
                    mHome.showEditCourseType(courseType);
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
                if (mList.getSelectedRow() != -1) {
                    disableFields();
                    CourseType courseType = Application.getInstance().getCourseTypeAllList()
                            .get(mList.getSelectedRow());
                    if (courseType.mEndDate.getTime() == 0) {
                        courseType.mEndDate = Calendar.getInstance().getTime();
                    } else {
                        courseType.mEndDate = new Date(0);
                    }
                    new CourseTypeUpdate(courseType, ViewCourseTypeSelect.this).start();
                }
            }
        });
        
        mButtonDetails = new JButton("Detalhes");
        mButtonDetails.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (mList.getSelectedRow() != -1) {
                    CourseType courseType = Application.getInstance().getCourseTypeAllList()
                            .get(mList.getSelectedRow());
                    mHome.showDetailsCourseType(courseType);
                }
            }
        });
        mButtonDetails.setIcon(new ImageIcon(ViewCourseTypeSelect.class.getResource("/com/nicoinc/system/ibms/resources/button_details.png")));
        mButtonDetails.setFont(new Font("Arial", Font.PLAIN, 14));

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(mProgressBar, GroupLayout.DEFAULT_SIZE, 1116, Short.MAX_VALUE)
                            .addContainerGap())
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(mButtonEdit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(79)
                            .addComponent(mButtonEnable, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                            .addGap(30)
                            .addComponent(mButtonDetails, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
                            .addGap(697))
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                .addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1116, Short.MAX_VALUE)
                                .addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 1116, Short.MAX_VALUE))
                            .addContainerGap())))
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblNewLabel)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(mButtonEdit)
                        .addComponent(mButtonEnable, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mButtonDetails, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(mProgressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
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
            case COURSE_TYPE_GET_LIST:
                JOptionPane.showMessageDialog(this, "Dados do tipo de curso alterados com sucesso.");
                mList.invalidate();
                enableFields();
                if (mList.getSelectedRow() != -1) {
                    CourseType courseType = Application.getInstance().getCourseTypeAllList()
                            .get(mList.getSelectedRow());
                    mButtonEdit.setEnabled(courseType.mEndDate.getTime() == 0);
                    mButtonEnable.setText((courseType.mEndDate.getTime() == 0) ? "Desativar" : "Ativar");
                }
                break;
            case COURSE_TYPE_UPDATE:
                new CourseTypeGetList(this).start();
                break;

            default:
                break;
            }
        }
    }

    private void enableFields() {
        mButtonEdit.setEnabled(true);
        mButtonEnable.setEnabled(true);
        mButtonDetails.setEnabled(true);
        mList.setEnabled(true);
        mProgressBar.setVisible(false);
    }

    private void disableFields() {
        mButtonEdit.setEnabled(false);
        mButtonEnable.setEnabled(false);
        mButtonDetails.setEnabled(false);
        mList.setEnabled(false);
        mProgressBar.setVisible(true);
    }

    private static class TableModel extends AbstractTableModel {
        private static final long serialVersionUID = 2139100891339821696L;
        private static final String[] COLUMN_NAMES = { "NOME", "ESTADO" };

        @Override
        public int getColumnCount() {
            return COLUMN_NAMES.length;
        }

        @Override
        public int getRowCount() {
            return Application.getInstance().getCourseTypeAllList().size();
        }

        @Override
        public String getColumnName(int column) {
            return COLUMN_NAMES[column];
        }

        @Override
        public Object getValueAt(int row, int column) {
            if (row > Application.getInstance().getCourseTypeAllList().size()) {
                return null;
            }
            CourseType courseType = Application.getInstance().getCourseTypeAllList().get(row);
            switch (column) {
            case 0:
                return courseType.mName;
            case 1:
                return courseType.mEndDate.getTime() == 0 ? "ATIVO" : "DESATIVADO";
            }
            return null;
        }
    }
}
