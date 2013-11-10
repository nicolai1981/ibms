package com.nicoinc.system.ibms.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.nicoinc.system.ibms.command.CommandListener;
import com.nicoinc.system.ibms.command.CourseGetSubscribeList;
import com.nicoinc.system.ibms.command.CourseTypeGetCourseList;
import com.nicoinc.system.ibms.command.RequestResult;
import com.nicoinc.system.ibms.main.Application;
import com.nicoinc.system.ibms.model.Course;
import com.nicoinc.system.ibms.model.CourseSubscribe;
import com.nicoinc.system.ibms.model.CourseType;

public class ViewCourseTypeView extends JPanel implements CommandListener {
    private static final long serialVersionUID = -8908763683014288749L;
    private static final SimpleDateFormat sDateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    private JComboBox<CourseType> mCourseTypeList;
    private JLabel mStartDate;
    private JLabel mStatus;
    private JLabel mEndDate;
    private JLabel mCourse;
    private JList<Course> mHistoryList;
    private JLabel mTotalCourse;
    private JLabel mTotalTeachers;
    private JLabel mTotalStudents;
    private JLabel mTotalComplete;
    private JLabel mTotalIncomplete;
    private JLabel mTotalEnd;
    private JProgressBar mProgressBar;
    private DefaultListModel<Course> mHistoryListModel; 
    private DefaultListModel<CourseSubscribe> mTeacherListModel; 
    private DefaultListModel<CourseSubscribe> mStudentCompleteListModel; 
    private DefaultListModel<CourseSubscribe> mStudentIncompleteListModel; 
    private DefaultListModel<CourseSubscribe> mStudentEndListModel; 

    public ViewCourseTypeView() {
        JLabel lblGerao = new JLabel("Tipo do curso");
        lblGerao.setFont(new Font("Arial", Font.PLAIN, 14));

        mCourseTypeList = new JComboBox<CourseType>();
        mCourseTypeList.setFont(new Font("Arial", Font.PLAIN, 14));
        mCourseTypeList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                mHistoryListModel.removeAllElements();
                mTotalCourse.setText("0");
                clearFields();

                CourseType currentCourseType = (CourseType) mCourseTypeList.getSelectedItem();
                if (currentCourseType != null) {
                    mStartDate.setText(sDateFormatter.format(currentCourseType.mStartDate));
                    if (currentCourseType.mEndDate.getTime() == 0) {
                        mStatus.setText("ATIVO");
                        mEndDate.setText("-");
                    } else {
                        mStatus.setText("INATIVO");
                        mEndDate.setText(sDateFormatter.format(currentCourseType.mEndDate));
                    }
                }

                disableFields();
                new CourseTypeGetCourseList(currentCourseType, ViewCourseTypeView.this).start();
            }
        });

        JLabel lblDataDeCriao = new JLabel("Data de Cria\u00E7\u00E3o");
        lblDataDeCriao.setFont(new Font("Arial", Font.PLAIN, 14));

        mStartDate = new JLabel("DD/MM/AAAA");
        mStartDate.setHorizontalAlignment(SwingConstants.CENTER);
        mStartDate.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblSituao = new JLabel("Situa\u00E7\u00E3o");
        lblSituao.setFont(new Font("Arial", Font.PLAIN, 14));

        mStatus = new JLabel("-");
        mStatus.setHorizontalAlignment(SwingConstants.LEFT);
        mStatus.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblDataDeDesativao = new JLabel("Data de Desativa\u00E7\u00E3o");
        lblDataDeDesativao.setFont(new Font("Arial", Font.PLAIN, 14));

        mEndDate = new JLabel("DD/MM/AAAA");
        mEndDate.setHorizontalAlignment(SwingConstants.CENTER);
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

        mHistoryListModel = new DefaultListModel<Course>(); 
        mHistoryList = new JList<Course>(mHistoryListModel);
        mHistoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mHistoryList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    clearFields();
                    Course currentCourse = (Course) mHistoryList.getSelectedValue();
                    if (currentCourse != null) {
                        mCourse.setText(currentCourse.toString());
                        disableFields();
                        new CourseGetSubscribeList(currentCourse, ViewCourseTypeView.this).start();
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

        mTeacherListModel = new DefaultListModel<CourseSubscribe>(); 
        JList<CourseSubscribe> teacherList = new JList<CourseSubscribe>(mTeacherListModel);
        teacherList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        teacherList.setFont(new Font("Arial", Font.BOLD, 14));
        scrollPane_teachers.setViewportView(teacherList);

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

        mStudentCompleteListModel = new DefaultListModel<CourseSubscribe>(); 
        JList<CourseSubscribe> studentCompleteList = new JList<CourseSubscribe>(mStudentCompleteListModel);
        studentCompleteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentCompleteList.setFont(new Font("Arial", Font.BOLD, 14));
        scrollPane_student_complete.setViewportView(studentCompleteList);

        JLabel lblTotalDeAlunos = new JLabel("Total de Alunos N\u00E3o Formados:");
        lblTotalDeAlunos.setFont(new Font("Arial", Font.PLAIN, 14));
        
        mTotalIncomplete = new JLabel("0");
        mTotalIncomplete.setFont(new Font("Arial", Font.BOLD, 14));
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        mStudentIncompleteListModel = new DefaultListModel<CourseSubscribe>(); 
        JList<CourseSubscribe> mStudentIncompleteList = new JList<CourseSubscribe>(mStudentIncompleteListModel);
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

        mStudentEndListModel = new DefaultListModel<CourseSubscribe>(); 
        JList<CourseSubscribe> studentEndList = new JList<CourseSubscribe>(mStudentEndListModel);
        studentEndList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentEndList.setFont(new Font("Arial", Font.BOLD, 14));
        scrollPane_student_incomplete.setViewportView(studentEndList);

        mProgressBar = new JProgressBar();
        mProgressBar.setIndeterminate(true);
        mProgressBar.setVisible(false);

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(mCourseTypeList, 0, 1110, Short.MAX_VALUE)
                        .addComponent(lblGerao)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
                                .addComponent(mStartDate, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblDataDeCriao, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18)
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(lblSituao, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(mStatus, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                            .addGap(18)
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(mEndDate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblDataDeDesativao, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(lblTotal_1)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(mTotalCourse, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
                                .addComponent(lblLderes, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(scrollPane_leader, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(panel, GroupLayout.DEFAULT_SIZE, 902, Short.MAX_VALUE))
                        .addComponent(mProgressBar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE))
                    .addContainerGap())
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblGerao)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(mCourseTypeList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblDataDeCriao, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSituao, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDataDeDesativao, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(mStartDate, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mStatus, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mEndDate, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(lblLderes, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblTotal_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                                .addComponent(mTotalCourse, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(scrollPane_leader, GroupLayout.PREFERRED_SIZE, 434, GroupLayout.PREFERRED_SIZE))
                        .addComponent(panel, GroupLayout.PREFERRED_SIZE, 483, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(mProgressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(12, Short.MAX_VALUE))
        );

        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
                    .addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
                        .addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
                            .addGap(10)
                            .addComponent(lblCurso)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(mCourse, GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(lblTotalInscritos)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(mTotalStudents, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
                        .addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                                .addGroup(gl_panel.createSequentialGroup()
                                    .addComponent(lblTotal_2)
                                    .addPreferredGap(ComponentPlacement.UNRELATED)
                                    .addComponent(mTotalTeachers, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
                                .addGroup(gl_panel.createSequentialGroup()
                                    .addComponent(lblTotalDeAlunos)
                                    .addPreferredGap(ComponentPlacement.UNRELATED)
                                    .addComponent(mTotalIncomplete, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
                                .addComponent(scrollPane_teachers))
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                .addGroup(gl_panel.createSequentialGroup()
                                    .addComponent(lblTotalDesistentes)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(mTotalEnd, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
                                .addComponent(scrollPane_student_incomplete, GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                                .addGroup(gl_panel.createSequentialGroup()
                                    .addComponent(lblTotal_3)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(mTotalComplete, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
                                .addComponent(scrollPane_student_complete, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE))
                            .addPreferredGap(ComponentPlacement.RELATED)))
                    .addContainerGap())
        );
        gl_panel.setVerticalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(5)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblCurso, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mCourse, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mTotalStudents)
                        .addComponent(lblTotalInscritos, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblTotal_2)
                        .addComponent(mTotalTeachers)
                        .addComponent(lblTotal_3)
                        .addComponent(mTotalComplete, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(scrollPane_teachers, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
                        .addComponent(scrollPane_student_complete, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblTotalDeAlunos, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mTotalIncomplete, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTotalDesistentes, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mTotalEnd, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                        .addComponent(scrollPane_student_incomplete, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
        );
        panel.setLayout(gl_panel);
        setLayout(groupLayout);

        // Fill course type list
        for (CourseType item : Application.getInstance().getCourseTypeAllList()) {
            mCourseTypeList.addItem(item);
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
            JOptionPane.showMessageDialog(this,"Erro no servidor.\nTente mais tarde.");
            enableFields();
            break;
        case UNKNOWN:
            JOptionPane.showMessageDialog(this,"Erro desconhecido.\nFeche o aplicativo e tente novamente.");
            enableFields();
            break;
        case OK:
            switch (result.getCommand()) {
            case COURSE_TYPE_COURSE_LIST:
                List<Course> list = (List<Course>) result.getData(CourseTypeGetCourseList.HISTORY_LIST);
                for (Course course : list) {
                    mHistoryListModel.addElement(course);
                }

                mTotalCourse.setText(String.valueOf(list.size()));
                enableFields();
                break;

            case COURSE_GET_SUBSCRIBE_LIST:
                List<CourseSubscribe> listSubscribe = (List<CourseSubscribe>) result.getData(CourseGetSubscribeList.SUBSCRIBE_LIST);
                int teacher = 0;
                int complete = 0;
                int incomplete = 0;
                int end = 0;
                for (CourseSubscribe subscribe : listSubscribe) {
                    if (subscribe.mIsTeacher) {
                        mTeacherListModel.addElement(subscribe);
                        teacher++;
                    } else if (subscribe.mCompleted) {
                        mStudentCompleteListModel.addElement(subscribe);
                        complete++;
                    } else if (subscribe.mEndDate.getTime() == 0) {
                        incomplete++;
                        mStudentIncompleteListModel.addElement(subscribe);
                    } else {
                        end++;
                        mStudentEndListModel.addElement(subscribe);
                    }
                }

                mTotalTeachers.setText(String.valueOf(teacher));
                mTotalStudents.setText(String.valueOf(complete + incomplete + end));
                mTotalComplete.setText(String.valueOf(complete));
                mTotalIncomplete.setText(String.valueOf(incomplete));
                mTotalEnd.setText(String.valueOf(end));
                enableFields();
                break;

            default:
                break;
            }
        }
    }

    private void enableFields() {
        mCourseTypeList.setEnabled(true);
        mHistoryList.setEnabled(true);
        mProgressBar.setVisible(false);
    }

    private void disableFields() {
        mCourseTypeList.setEnabled(true);
        mHistoryList.setEnabled(true);
        mProgressBar.setVisible(false);
    }

    private void clearFields() {
        mTotalTeachers.setText("0");
        mTeacherListModel.removeAllElements();
        mTotalStudents.setText("0");
        mTotalComplete.setText("0");
        mStudentCompleteListModel.removeAllElements();
        mTotalIncomplete.setText("0");
        mStudentIncompleteListModel.removeAllElements();
        mTotalEnd.setText("0");
        mStudentEndListModel.removeAllElements();
    }
}
