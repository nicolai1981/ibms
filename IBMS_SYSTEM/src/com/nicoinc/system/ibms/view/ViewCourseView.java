package com.nicoinc.system.ibms.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.nicoinc.system.ibms.command.CommandListener;
import com.nicoinc.system.ibms.command.CourseGetSubscribeList;
import com.nicoinc.system.ibms.command.RequestResult;
import com.nicoinc.system.ibms.main.Application;
import com.nicoinc.system.ibms.model.Course;
import com.nicoinc.system.ibms.model.CourseSubscribe;
import com.nicoinc.system.ibms.model.CourseType;

public class ViewCourseView extends JPanel implements CommandListener {
    private static final SimpleDateFormat sDateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    private static final long serialVersionUID = -8213291145000189731L;

    private JComboBox mCourseList;
    private JLabel mCourseType;
    private JLabel mStartDate;
    private JLabel mEndDate;
    private JLabel mTotalLessons;
    private JList mTeacherList;
    private DefaultListModel mTeacherListModel; 
    private JList mStudentList;
    private DefaultListModel mStudentListModel; 
    private JLabel mStudentName;
    private JLabel mStudentStatus;
    private JLabel mStudentStartDate;
    private JLabel mStudentEndDate;
    private JLabel mStudentTotalLesson;
    
    private JProgressBar mProgressBar;

    public ViewCourseView() {

        JLabel lblCursoASer = new JLabel("Curso");
        lblCursoASer.setFont(new Font("Arial", Font.PLAIN, 14));

        mCourseList = new JComboBox();
        mCourseList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                mStudentListModel.removeAllElements();
                mTeacherListModel.removeAllElements();
                mStudentName.setText("-");
                mStudentStatus.setText("-");
                mStudentStartDate.setText("-");
                mStudentEndDate.setText("-");
                mStudentTotalLesson.setText("-");

                Course currentCourse = (Course) mCourseList.getSelectedItem();
                if (currentCourse != null) {
                    mStartDate.setText(sDateFormatter.format(currentCourse.mStartDate));
                    mEndDate.setText(sDateFormatter.format(currentCourse.mEndDate));
                    mTotalLessons.setText(String.valueOf(currentCourse.mTotalLessons));
                    for (CourseType item : Application.getInstance().getCourseTypeList()) {
                        if (item.mId == currentCourse.mCourseTypeId) {
                            mCourseType.setText(item.mName);
                            break;
                        }
                    }

                    disableFields();
                    new CourseGetSubscribeList(currentCourse, ViewCourseView.this).run();
                }
            }
        });
        mCourseList.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(0, 0, 0)));

        JLabel lblGerao = new JLabel("Tipo do curso");
        lblGerao.setFont(new Font("Arial", Font.PLAIN, 14));

        mCourseType = new JLabel("-");
        mCourseType.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblNewLabel_1 = new JLabel("Data inicial");
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));

        mStartDate = new JLabel("-");
        mStartDate.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblDataFinal = new JLabel("Data final");
        lblDataFinal.setFont(new Font("Arial", Font.PLAIN, 14));

        mEndDate = new JLabel("-");
        mEndDate.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblTotalDeAulas = new JLabel("Total de Aulas");
        lblTotalDeAulas.setFont(new Font("Arial", Font.PLAIN, 14));

        mTotalLessons = new JLabel("-");
        mTotalLessons.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblProfessores = new JLabel("Professores");
        lblProfessores.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPaneTeacher = new JScrollPane();
        scrollPaneTeacher.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneTeacher.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        mTeacherListModel = new DefaultListModel(); 
        mTeacherList = new JList(mTeacherListModel);
        mTeacherList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mTeacherList.setFont(new Font("Arial", Font.BOLD, 14));
        scrollPaneTeacher.setViewportView(mTeacherList);

        JLabel lblAlunospresena = new JLabel("Alunos");
        lblAlunospresena.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPaneComplete = new JScrollPane();
        scrollPaneComplete.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneComplete.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        mStudentListModel = new DefaultListModel();
        mStudentList = new JList(mStudentListModel);
        mStudentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mStudentList.setFont(new Font("Arial", Font.BOLD, 14));
        mStudentList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    CourseSubscribe student = (CourseSubscribe) mStudentList.getSelectedValue();
                    if (student != null) {
                        mStudentName.setText(student.mMemberName);
                        mStudentStartDate.setText(sDateFormatter.format(student.mStartDate));
                        if (student.mEndDate.getTime() == 0) {
                            mStudentEndDate.setText("-");
                        } else {
                            mStudentEndDate.setText(sDateFormatter.format(student.mEndDate));
                        }
                        mStudentTotalLesson.setText(String.valueOf(student.mTotalLessons));
                        if (student.mCompleted) {
                            mStudentStatus.setText("Formado");
                        } else if (student.mEndDate.getTime() > 0) {
                            mStudentStatus.setText("Desistente");
                        } else if (Calendar.getInstance().getTimeInMillis() > ((Course)mCourseList.getSelectedItem()).mEndDate.getTime()) {
                            mStudentStatus.setText("Incompleto");
                        } else {
                            mStudentStatus.setText("Cursando");
                        }
                    }
                }
            }
        });
        scrollPaneComplete.setViewportView(mStudentList);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));

        JLabel lblNomeDoAluno = new JLabel("Nome do aluno");
        lblNomeDoAluno.setFont(new Font("Arial", Font.PLAIN, 14));

        mStudentName = new JLabel("-");
        mStudentName.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblSituao = new JLabel("Situa\u00E7\u00E3o");
        lblSituao.setFont(new Font("Arial", Font.PLAIN, 14));

        mStudentStatus = new JLabel("-");
        mStudentStatus.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblDataDeInscrio = new JLabel("Data de inscri\u00E7\u00E3o");
        lblDataDeInscrio.setFont(new Font("Arial", Font.PLAIN, 14));

        mStudentStartDate = new JLabel("-");
        mStudentStartDate.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblDataDeDesistncia = new JLabel("Data de desist\u00EAncia");
        lblDataDeDesistncia.setFont(new Font("Arial", Font.PLAIN, 14));

        mStudentEndDate = new JLabel("-");
        mStudentEndDate.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblAulasPresente = new JLabel("Aulas Presente");
        lblAulasPresente.setFont(new Font("Arial", Font.PLAIN, 14));

        mStudentTotalLesson = new JLabel("-");
        mStudentTotalLesson.setFont(new Font("Arial", Font.BOLD, 14));

        mProgressBar = new JProgressBar();
        mProgressBar.setIndeterminate(true);
        mProgressBar.setVisible(false);

        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
            gl_panel_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_1.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblNomeDoAluno, GroupLayout.PREFERRED_SIZE, 476, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mStudentName, GroupLayout.PREFERRED_SIZE, 476, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
                        .addComponent(mStudentStatus, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblSituao, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                    .addGap(18)
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
                        .addComponent(mStudentStartDate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblDataDeInscrio, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                    .addGap(18)
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
                        .addComponent(mStudentEndDate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblDataDeDesistncia, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(18)
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblAulasPresente, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mStudentTotalLesson, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
                    .addGap(105))
        );
        gl_panel_1.setVerticalGroup(
            gl_panel_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_1.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNomeDoAluno)
                        .addComponent(lblSituao, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDataDeInscrio)
                        .addComponent(lblDataDeDesistncia)
                        .addComponent(lblAulasPresente, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                        .addComponent(mStudentName, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mStudentEndDate, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mStudentStatus, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mStudentStartDate, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mStudentTotalLesson, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(14, Short.MAX_VALUE))
        );
        panel_1.setLayout(gl_panel_1);

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblCursoASer, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
                        .addComponent(mCourseList, Alignment.TRAILING, 0, 1110, Short.MAX_VALUE)
                        .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addComponent(lblProfessores)
                                .addComponent(scrollPaneTeacher, GroupLayout.PREFERRED_SIZE, 529, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addComponent(lblAlunospresena, GroupLayout.PREFERRED_SIZE, 358, GroupLayout.PREFERRED_SIZE)
                                .addComponent(scrollPaneComplete, GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)))
                        .addComponent(panel_1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 1110, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mProgressBar, GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE))
                    .addContainerGap())
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblCursoASer, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(mCourseList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(panel, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblProfessores, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblAlunospresena, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(scrollPaneComplete)
                        .addComponent(scrollPaneTeacher, GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(mProgressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(17))
        );

        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
                        .addGroup(gl_panel.createSequentialGroup()
                            .addComponent(lblGerao, GroupLayout.PREFERRED_SIZE, 476, GroupLayout.PREFERRED_SIZE)
                            .addGap(18)
                            .addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_panel.createSequentialGroup()
                            .addComponent(mCourseType, GroupLayout.PREFERRED_SIZE, 476, GroupLayout.PREFERRED_SIZE)
                            .addGap(18)
                            .addComponent(mStartDate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGap(18)
                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
                        .addComponent(lblDataFinal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mEndDate, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                    .addGap(18)
                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblTotalDeAulas, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mTotalLessons, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(220, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblGerao)
                        .addComponent(lblNewLabel_1)
                        .addComponent(lblDataFinal)
                        .addComponent(lblTotalDeAulas, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(mCourseType, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mStartDate, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mEndDate, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mTotalLessons, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(14, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);
        setLayout(groupLayout);

        // Fill course list
        for (Course item : Application.getInstance().getCourseList()) {
            item.mStringFormat = Course.COMPLETE_INFORMATION;
            mCourseList.addItem(item);
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
            case GET_COURSE_SUBSCRIBE_LIST:
                List<CourseSubscribe> subscriptionList = (List<CourseSubscribe>) result.getData(CourseGetSubscribeList.SUBSCRIBE_LIST);
                for (CourseSubscribe subscription : subscriptionList) {
                    if (subscription.mIsTeacher) {
                        mTeacherListModel.addElement(subscription);
                    } else {
                        mStudentListModel.addElement(subscription);
                    }
                }
                enableFields();
                break;
            default:
                break;
            }
        }
    }

    private void enableFields() {
        mCourseList.setEnabled(true);
        mStudentList.setEnabled(true);
        mProgressBar.setVisible(false);
    }

    private void disableFields() {
        mCourseList.setEnabled(false);
        mStudentList.setEnabled(false);
        mProgressBar.setVisible(true);
    }
}
