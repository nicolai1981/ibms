package com.nicoinc.system.ibms.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
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
import com.nicoinc.system.ibms.command.CourseGetList;
import com.nicoinc.system.ibms.command.CourseUpdate;
import com.nicoinc.system.ibms.command.RequestResult;
import com.nicoinc.system.ibms.main.Application;
import com.nicoinc.system.ibms.model.Course;

public class ViewCourseSelect extends JPanel implements CommandListener {
    private static final SimpleDateFormat sDateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    private static final long serialVersionUID = -8213291145000189731L;
    private JButton mButtonEdit;
    private JButton mButtonEnable;
    private JProgressBar mProgressBar;
    private JTable mList;
    private FrameHome mHome;
    private JButton mButtonTeacher;
    private JButton mButtonSubscribe;
    private JButton mButtonStudent;
    private JButton mButtonDetails;

    public ViewCourseSelect(FrameHome home) {
        mHome = home;

        JLabel lblNewLabel = new JLabel("GERENCIAMENTO DE CURSOS");
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
                    Course course = Application.getInstance().getCourseAllList().get(mList.getSelectedRow());
                    mButtonEdit.setEnabled(course.mDeactivateDate.getTime() == 0);
                    mButtonEnable.setText((course.mDeactivateDate.getTime() == 0) ? "Desativar" : "Ativar");
                }
            }
        });
        scrollPane.setViewportView(mList);

        mButtonEdit = new JButton("Editar");
        mButtonEdit.setIcon(new ImageIcon(ViewCourseSelect.class.getResource("/com/nicoinc/system/ibms/resources/button_edit.png")));
        mButtonEdit.setFont(new Font("Arial", Font.PLAIN, 14));
        mButtonEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (mList.getSelectedRow() != -1) {
                    disableFields();
                    Course course = Application.getInstance().getCourseAllList().get(mList.getSelectedRow());
                    mHome.showEditCourse(course);
                }
            }
        });

        mProgressBar = new JProgressBar();
        mProgressBar.setIndeterminate(true);

        mButtonEnable = new JButton("Ativar");
        mButtonEnable.setIcon(new ImageIcon(ViewCourseSelect.class.getResource("/com/nicoinc/system/ibms/resources/button_deactived.png")));
        mButtonEnable.setFont(new Font("Arial", Font.PLAIN, 14));
        mButtonEnable.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (mList.getSelectedRow() != -1) {
                    disableFields();
                    Course course = Application.getInstance().getCourseAllList().get(mList.getSelectedRow());
                    if (course.mDeactivateDate.getTime() == 0) {
                        course.mDeactivateDate = Calendar.getInstance().getTime();
                    } else {
                        course.mDeactivateDate = new Date(0);
                    }
                    new CourseUpdate(course, ViewCourseSelect.this).start();
                }
            }
        });

        mButtonTeacher = new JButton("Professores");
        mButtonTeacher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (mList.getSelectedRow() != -1) {
                    disableFields();
                    Course course = Application.getInstance().getCourseAllList().get(mList.getSelectedRow());
                    mHome.showTeacherCourse(course);
                }
            }
        });
        mButtonTeacher.setIcon(new ImageIcon(ViewCourseSelect.class
                .getResource("/com/nicoinc/system/ibms/resources/button_teacher.png")));
        mButtonTeacher.setFont(new Font("Arial", Font.PLAIN, 14));

        mButtonSubscribe = new JButton("Inscri\u00E7\u00E3o");
        mButtonSubscribe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (mList.getSelectedRow() != -1) {
                    disableFields();
                    Course course = Application.getInstance().getCourseAllList().get(mList.getSelectedRow());
                    mHome.showSubscribeCourse(course);
                }
            }
        });
        mButtonSubscribe.setIcon(new ImageIcon(ViewCourseSelect.class
                .getResource("/com/nicoinc/system/ibms/resources/button_subscribe.png")));
        mButtonSubscribe.setFont(new Font("Arial", Font.PLAIN, 14));

        mButtonStudent = new JButton("Alunos");
        mButtonStudent.setIcon(new ImageIcon(ViewCourseSelect.class.getResource("/com/nicoinc/system/ibms/resources/button_student.png")));
        mButtonStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (mList.getSelectedRow() != -1) {
                    disableFields();
                    Course course = Application.getInstance().getCourseAllList().get(mList.getSelectedRow());
                    mHome.showStudentCourse(course);
                }
            }
        });
        mButtonStudent.setFont(new Font("Arial", Font.PLAIN, 14));

        mButtonDetails = new JButton("Detalhes");
        mButtonDetails.setIcon(new ImageIcon(ViewCourseSelect.class.getResource("/com/nicoinc/system/ibms/resources/button_details.png")));
        mButtonDetails.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (mList.getSelectedRow() != -1) {
                    disableFields();
                    Course course = Application.getInstance().getCourseAllList().get(mList.getSelectedRow());
                    mHome.showDetailsCourse(course);
                }
            }
        });
        mButtonDetails.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JButton mButtonNew = new JButton("Novo");
        mButtonNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                mHome.showNewCourse();
            }
        });
        mButtonNew.setIcon(new ImageIcon(ViewCourseSelect.class.getResource("/com/nicoinc/system/ibms/resources/button_new.png")));
        mButtonNew.setFont(new Font("Arial", Font.PLAIN, 14));

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(mProgressBar, GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
                        .addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 966, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(mButtonNew, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(mButtonEdit, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(mButtonEnable, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(mButtonDetails, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(mButtonSubscribe, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(mButtonStudent, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(mButtonTeacher, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)))))
                    .addContainerGap())
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblNewLabel)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(mButtonNew, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(mButtonEdit)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(mButtonEnable, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(mButtonDetails, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                            .addGap(36)
                            .addComponent(mButtonSubscribe, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(mButtonStudent, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(mButtonTeacher, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(mProgressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        setLayout(groupLayout);

        mProgressBar.setVisible(false);
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
            case COURSE_GET_LIST:
                JOptionPane.showMessageDialog(this, "Dados do curso alterados com sucesso.");
                mList.invalidate();
                enableFields();
                if (mList.getSelectedRow() != -1) {
                    Course course = Application.getInstance().getCourseAllList().get(mList.getSelectedRow());
                    mButtonEdit.setEnabled(course.mDeactivateDate.getTime() == 0);
                    mButtonEnable.setText((course.mDeactivateDate.getTime() == 0) ? "Desativar" : "Ativar");
                }
                break;
            case COURSE_UPDATE:
                new CourseGetList(this).start();
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
        mButtonStudent.setEnabled(true);
        mList.setEnabled(true);
        mProgressBar.setVisible(false);
    }

    private void disableFields() {
        mButtonEdit.setEnabled(false);
        mButtonEnable.setEnabled(false);
        mButtonDetails.setEnabled(false);
        mButtonStudent.setEnabled(false);
        mList.setEnabled(false);
        mProgressBar.setVisible(true);
    }

    private static class TableModel extends AbstractTableModel {
        private static final long serialVersionUID = 2139100891339821696L;
        private static final String[] COLUMN_NAMES = { "NOME", "EDIÇÃO", "DATA INICIAL", "DATA FINAL", "ESTADO" };

        @Override
        public int getColumnCount() {
            return COLUMN_NAMES.length;
        }

        @Override
        public int getRowCount() {
            return Application.getInstance().getCourseAllList().size();
        }

        @Override
        public String getColumnName(int column) {
            return COLUMN_NAMES[column];
        }

        @Override
        public Object getValueAt(int row, int column) {
            if (row > Application.getInstance().getCourseAllList().size()) {
                return null;
            }
            Course course = Application.getInstance().getCourseAllList().get(row);
            switch (column) {
            case 0:
                return course.mCourseTypeName;
            case 1:
                return course.mVersion;
            case 2:
                return sDateFormatter.format(course.mStartDate);
            case 3:
                return sDateFormatter.format(course.mEndDate);
            case 4:
                if (course.mDeactivateDate.getTime() == 0) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.DATE, 1);
                    if (calendar.getTimeInMillis() < course.mEndDate.getTime()) {
                        return "ABERTO";
                    } else {
                        return "TERMINADO";
                    }
                } else {
                    return "DESATIVADO";
                }
            }
            return null;
        }
    }
}
