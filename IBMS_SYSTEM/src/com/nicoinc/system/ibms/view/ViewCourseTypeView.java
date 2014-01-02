package com.nicoinc.system.ibms.view;

import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
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
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import com.nicoinc.system.ibms.command.CommandListener;
import com.nicoinc.system.ibms.command.CourseGetSubscribeList;
import com.nicoinc.system.ibms.command.CourseTypeGetCourseList;
import com.nicoinc.system.ibms.command.RequestResult;
import com.nicoinc.system.ibms.model.Course;
import com.nicoinc.system.ibms.model.CourseSubscribe;
import com.nicoinc.system.ibms.model.CourseType;

public class ViewCourseTypeView extends JPanel implements CommandListener {
    private static final long serialVersionUID = -8908763683014288749L;
    private static final SimpleDateFormat sDateFormatter = new SimpleDateFormat("dd/MM/yyyy");

    private JLabel mStartDate;
    private JLabel mStatus;
    private JLabel mEndDate;
    private JLabel mCourse;
    private JLabel mTotalCourse;
    private JLabel mTotalTeachers;
    private JLabel mTotalStudents;
    private JLabel mTotalComplete;
    private JLabel mTotalIncomplete;
    private JLabel mTotalEnd;

    private JTable mHistoryList;
    private HistoryModel mHistoryListModel;

    private JTable mTeacherList;
    private TeacherModel mTeacherListModel;

    private JTable mStudentCompleteList;
    private StudentModel mStudentCompleteListModel;

    private JTable mStudentIncompleteList;
    private StudentModel mStudentIncompleteListModel;

    private JTable mStudentEndList;
    private StudentModel mStudentEndListModel;

    private JProgressBar mProgressBar;

    public ViewCourseTypeView(CourseType courseType) {
        JLabel lblDataDeCriao = new JLabel("Data de Cria\u00E7\u00E3o");
        lblDataDeCriao.setFont(new Font("Arial", Font.PLAIN, 14));

        mStartDate = new JLabel(sDateFormatter.format(courseType.mStartDate));
        mStartDate.setHorizontalAlignment(SwingConstants.LEFT);
        mStartDate.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblSituao = new JLabel("Situa\u00E7\u00E3o");
        lblSituao.setFont(new Font("Arial", Font.PLAIN, 14));

        mStatus = new JLabel(courseType.mEndDate.getTime() == 0 ? "ATIVO" : "DESATIVADO");
        mStatus.setHorizontalAlignment(SwingConstants.LEFT);
        mStatus.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblDataDeDesativao = new JLabel("Data de Desativa\u00E7\u00E3o");
        lblDataDeDesativao.setFont(new Font("Arial", Font.PLAIN, 14));

        mEndDate = new JLabel(courseType.mEndDate.getTime() == 0 ? "-" : sDateFormatter.format(courseType.mEndDate));
        mEndDate.setHorizontalAlignment(SwingConstants.LEFT);
        mEndDate.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblLderes = new JLabel("Cursos Ministrados");
        lblLderes.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblTotal_1 = new JLabel("Total:");
        lblTotal_1.setFont(new Font("Arial", Font.PLAIN, 14));

        mTotalCourse = new JLabel("0");
        mTotalCourse.setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane scrollPane_leader = new JScrollPane();
        scrollPane_leader.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_leader.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        mHistoryListModel = new HistoryModel();
        mHistoryList = new JTable(mHistoryListModel);
        mHistoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel listSelectionModel = mHistoryList.getSelectionModel();
        listSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    clearFields();
                    Course course = mHistoryListModel.getItem(mHistoryList.getSelectedRow());
                    if (course != null) {
                        mCourse.setText(sDateFormatter.format(course.mStartDate) + " - "
                                + sDateFormatter.format(course.mEndDate));
                        disableFields();
                        new CourseGetSubscribeList(course, ViewCourseTypeView.this).start();
                    }
                }
            }
        });
        mHistoryList.setFont(new Font("Arial", Font.BOLD, 14));
        scrollPane_leader.setViewportView(mHistoryList);

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(0, 0, 0)));

        JLabel lblCurso = new JLabel("Data do curso:");
        lblCurso.setFont(new Font("Arial", Font.PLAIN, 14));

        mCourse = new JLabel("-");
        mCourse.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblTotal_2 = new JLabel("Total de professores:");
        lblTotal_2.setFont(new Font("Arial", Font.PLAIN, 14));

        mTotalTeachers = new JLabel("0");
        mTotalTeachers.setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane scrollPane_teachers = new JScrollPane();
        scrollPane_teachers.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_teachers.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        mTeacherListModel = new TeacherModel();
        mTeacherList = new JTable(mTeacherListModel);
        mTeacherList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mTeacherList.setFont(new Font("Arial", Font.BOLD, 14));
        scrollPane_teachers.setViewportView(mTeacherList);

        JLabel lblTotalInscritos = new JLabel("Total Inscritos:");
        lblTotalInscritos.setFont(new Font("Arial", Font.PLAIN, 14));

        mTotalStudents = new JLabel("0");
        mTotalStudents.setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane scrollPane_student_complete = new JScrollPane();
        scrollPane_student_complete.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_student_complete.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JLabel lblTotal_3 = new JLabel("Total de Alunos Formados:");
        lblTotal_3.setFont(new Font("Arial", Font.PLAIN, 14));

        mTotalComplete = new JLabel("0");
        mTotalComplete.setFont(new Font("Arial", Font.BOLD, 14));

        mStudentCompleteListModel = new StudentModel();
        mStudentCompleteList = new JTable(mStudentCompleteListModel);
        mStudentCompleteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mStudentCompleteList.setFont(new Font("Arial", Font.BOLD, 14));
        scrollPane_student_complete.setViewportView(mStudentCompleteList);

        JLabel lblTotalDeAlunos = new JLabel("Total de Alunos N\u00E3o Formados:");
        lblTotalDeAlunos.setFont(new Font("Arial", Font.PLAIN, 14));

        mTotalIncomplete = new JLabel("0");
        mTotalIncomplete.setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        mStudentIncompleteListModel = new StudentModel();
        mStudentIncompleteList = new JTable(mStudentIncompleteListModel);
        mStudentIncompleteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mStudentIncompleteList.setFont(new Font("Arial", Font.BOLD, 14));
        scrollPane.setViewportView(mStudentIncompleteList);

        JLabel lblTotalDesistentes = new JLabel("Total de Alunos Desistentes:");
        lblTotalDesistentes.setFont(new Font("Arial", Font.PLAIN, 14));

        mTotalEnd = new JLabel("0");
        mTotalEnd.setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane scrollPane_student_incomplete = new JScrollPane();
        scrollPane_student_incomplete.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_student_incomplete.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        mStudentEndListModel = new StudentModel();
        mStudentEndList = new JTable(mStudentEndListModel);
        mStudentEndList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mStudentEndList.setFont(new Font("Arial", Font.BOLD, 14));
        scrollPane_student_incomplete.setViewportView(mStudentEndList);

        mProgressBar = new JProgressBar();
        mProgressBar.setIndeterminate(true);
        mProgressBar.setVisible(false);

        JLabel lblNome = new JLabel("Nome");
        lblNome.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblTetetete = new JLabel(courseType.mName);
        lblTetetete.setHorizontalAlignment(SwingConstants.LEFT);
        lblTetetete.setFont(new Font("Arial", Font.BOLD, 14));

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(mProgressBar, GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(lblTetetete, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblNome, GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE))
                            .addGap(10)
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addComponent(lblDataDeCriao, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                                .addComponent(mStartDate, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
                            .addGap(10)
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(lblSituao, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                .addComponent(mStatus, Alignment.TRAILING, 0, 0, Short.MAX_VALUE))
                            .addGap(10)
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(mEndDate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblDataDeDesativao, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                            .addPreferredGap(ComponentPlacement.RELATED, 165, Short.MAX_VALUE))
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(lblTotal_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                    .addGap(5)
                                    .addComponent(mTotalCourse, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
                                .addComponent(lblLderes, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                .addComponent(scrollPane_leader, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                            .addGap(5)
                            .addComponent(panel, GroupLayout.PREFERRED_SIZE, 895, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(10)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDataDeCriao, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSituao, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDataDeDesativao, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addGap(5)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblTetetete, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mStartDate, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mStatus, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mEndDate, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                            .addGap(12)
                            .addComponent(lblLderes, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                            .addGap(5)
                            .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
                                .addComponent(lblTotal_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                                .addComponent(mTotalCourse, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                            .addGap(5)
                            .addComponent(scrollPane_leader, GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE))
                        .addGroup(groupLayout.createSequentialGroup()
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGap(5)
                    .addComponent(mProgressBar, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                    .addGap(10))
        );

        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(lblCurso, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                            .addGap(5)
                            .addComponent(mCourse, GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
                            .addGap(10)
                            .addComponent(lblTotalInscritos, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                            .addGap(5)
                            .addComponent(mTotalStudents, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_panel.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                .addGroup(gl_panel.createSequentialGroup()
                                    .addComponent(lblTotal_2, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                                    .addGap(5)
                                    .addComponent(mTotalTeachers, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                                .addGroup(gl_panel.createSequentialGroup()
                                    .addComponent(lblTotalDeAlunos, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
                                    .addGap(5)
                                    .addComponent(mTotalIncomplete, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
                                .addComponent(scrollPane_teachers, GroupLayout.PREFERRED_SIZE, 433, GroupLayout.PREFERRED_SIZE)
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 433, GroupLayout.PREFERRED_SIZE))
                            .addGap(5)
                            .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                .addComponent(scrollPane_student_incomplete, GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                                .addGroup(gl_panel.createSequentialGroup()
                                    .addComponent(lblTotalDesistentes, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
                                    .addGap(5)
                                    .addComponent(mTotalEnd, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                                .addGroup(gl_panel.createSequentialGroup()
                                    .addComponent(lblTotal_3, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
                                    .addGap(5)
                                    .addComponent(mTotalComplete, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                                .addComponent(scrollPane_student_complete, GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE))))
                    .addContainerGap())
        );
        gl_panel.setVerticalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(10)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblCurso, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mCourse, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mTotalStudents)
                        .addComponent(lblTotalInscritos, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addGap(5)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblTotal_2)
                        .addComponent(mTotalTeachers)
                        .addComponent(lblTotal_3)
                        .addComponent(mTotalComplete, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addGap(5)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(scrollPane_teachers, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
                        .addComponent(scrollPane_student_complete, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE))
                    .addGap(5)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblTotalDeAlunos, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mTotalIncomplete, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTotalDesistentes, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mTotalEnd, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addGap(5)
                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                        .addComponent(scrollPane_student_incomplete, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE))
                    .addGap(10))
        );
        panel.setLayout(gl_panel);
        setLayout(groupLayout);

        new CourseTypeGetCourseList(courseType, this).run();
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
            case COURSE_TYPE_COURSE_LIST:
                List<Course> list = (List<Course>) result.getData(CourseTypeGetCourseList.HISTORY_LIST);
                mHistoryListModel.setCouseList(list);
                mTotalCourse.setText(String.valueOf(list.size()));
                enableFields();
                break;

            case COURSE_GET_SUBSCRIBE_LIST:
                List<CourseSubscribe> listSubscribe = (List<CourseSubscribe>) result
                        .getData(CourseGetSubscribeList.SUBSCRIBE_LIST);
                mTeacherListModel.clearList();

                for (CourseSubscribe subscribe : listSubscribe) {
                    if (subscribe.mIsTeacher) {
                        mTeacherListModel.add(subscribe);
                    } else if (subscribe.mCompleted) {
                        mStudentCompleteListModel.add(subscribe);
                    } else if (subscribe.mEndDate.getTime() == 0) {
                        mStudentIncompleteListModel.add(subscribe);
                    } else {
                        mStudentEndListModel.add(subscribe);
                    }
                }

                mTotalTeachers.setText(String.valueOf(mTeacherListModel.getRowCount()));
                mTotalStudents.setText(String.valueOf(mStudentCompleteListModel.getRowCount()
                        + mStudentIncompleteListModel.getRowCount() + mStudentEndListModel.getRowCount()));
                mTotalComplete.setText(String.valueOf(mStudentCompleteListModel.getRowCount()));
                mTotalIncomplete.setText(String.valueOf(mStudentIncompleteListModel.getRowCount()));
                mTotalEnd.setText(String.valueOf(mStudentEndListModel.getRowCount()));

                mTeacherList.invalidate();
                mStudentCompleteList.invalidate();
                mStudentIncompleteList.invalidate();
                mStudentEndList.invalidate();

                enableFields();
                break;

            default:
                break;
            }
        }
    }

    private void enableFields() {
        mHistoryList.setEnabled(true);
        mTeacherList.setEnabled(true);
        mStudentCompleteList.setEnabled(true);
        mStudentIncompleteList.setEnabled(true);
        mStudentEndList.setEnabled(true);
        mProgressBar.setVisible(false);
    }

    private void disableFields() {
        mHistoryList.setEnabled(false);
        mTeacherList.setEnabled(false);
        mStudentCompleteList.setEnabled(false);
        mStudentIncompleteList.setEnabled(false);
        mStudentEndList.setEnabled(false);
        mProgressBar.setVisible(true);
    }

    private void clearFields() {
        mTotalTeachers.setText("0");
        mTotalStudents.setText("0");
        mTotalComplete.setText("0");
        mTotalIncomplete.setText("0");
        mTotalEnd.setText("0");
        mTeacherListModel.clearList();
        mTeacherList.invalidate();
        mStudentCompleteListModel.clearList();
        mStudentCompleteList.invalidate();
        mStudentIncompleteListModel.clearList();
        mStudentIncompleteList.invalidate();
        mStudentEndListModel.clearList();
        mStudentEndList.invalidate();
    }

    private static class HistoryModel extends AbstractTableModel {
        private static final long serialVersionUID = 2139100891339821696L;
        private static final String[] COLUMN_NAMES = { "DATA DO CURSO" };
        private List<Course> mCourseList = new ArrayList<Course>();

        public void setCouseList(List<Course> courseList) {
            mCourseList.clear();
            mCourseList.addAll(courseList);
        }

        public Course getItem(int index) {
            if (index < mCourseList.size()) {
                return mCourseList.get(index);
            }
            return null;
        }

        @Override
        public int getColumnCount() {
            return COLUMN_NAMES.length;
        }

        @Override
        public int getRowCount() {
            return mCourseList.size();
        }

        @Override
        public String getColumnName(int column) {
            return COLUMN_NAMES[column];
        }

        @Override
        public Object getValueAt(int row, int column) {
            if (row > mCourseList.size()) {
                return null;
            }
            Course course = mCourseList.get(row);
            switch (column) {
            case 0:
                return sDateFormatter.format(course.mStartDate) + " - " + sDateFormatter.format(course.mEndDate);
            }
            return null;
        }
    }

    private static class TeacherModel extends AbstractTableModel {
        private static final long serialVersionUID = 2139100891339821696L;
        private static final String[] COLUMN_NAMES = { "NOME" };
        private List<CourseSubscribe> mList = new ArrayList<CourseSubscribe>();

        public void clearList() {
            mList.clear();
        }

        public void add(CourseSubscribe item) {
            mList.add(item);
        }

        @Override
        public int getColumnCount() {
            return COLUMN_NAMES.length;
        }

        @Override
        public int getRowCount() {
            return mList.size();
        }

        @Override
        public String getColumnName(int column) {
            return COLUMN_NAMES[column];
        }

        @Override
        public Object getValueAt(int row, int column) {
            if (row < mList.size()) {
                CourseSubscribe item = mList.get(row);
                switch (column) {
                case 0:
                    return item.mMemberName;
                }
            }
            return null;
        }
    }

    private static class StudentModel extends AbstractTableModel {
        private static final long serialVersionUID = 2139100891339821696L;
        private static final String[] COLUMN_NAMES = { "NOME", "AULAS" };
        private List<CourseSubscribe> mList = new ArrayList<CourseSubscribe>();

        public void clearList() {
            mList.clear();
        }

        public void add(CourseSubscribe item) {
            mList.add(item);
        }

        @Override
        public int getColumnCount() {
            return COLUMN_NAMES.length;
        }

        @Override
        public int getRowCount() {
            return mList.size();
        }

        @Override
        public String getColumnName(int column) {
            return COLUMN_NAMES[column];
        }

        @Override
        public Object getValueAt(int row, int column) {
            if (row < mList.size()) {
                CourseSubscribe item = mList.get(row);
                switch (column) {
                case 0:
                    return item.mMemberName;
                case 1:
                    return String.valueOf(item.mTotalLessons);
                }
            }
            return null;
        }
    }
}
