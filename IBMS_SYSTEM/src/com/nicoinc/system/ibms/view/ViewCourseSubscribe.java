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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import com.nicoinc.system.ibms.command.CommandListener;
import com.nicoinc.system.ibms.command.CourseGetSubscribeList;
import com.nicoinc.system.ibms.command.CourseSaveSubscribe;
import com.nicoinc.system.ibms.command.RequestResult;
import com.nicoinc.system.ibms.main.Application;
import com.nicoinc.system.ibms.model.Course;
import com.nicoinc.system.ibms.model.CourseSubscribe;
import com.nicoinc.system.ibms.model.CourseType;
import com.nicoinc.system.ibms.model.Member;

public class ViewCourseSubscribe extends JPanel implements CommandListener {
    private static final SimpleDateFormat sDateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    private static final long serialVersionUID = -8213291145000189731L;

    private JComboBox mCourseList;
    private JLabel mCourseType;
    private JLabel mStartDate;
    private JLabel mEndDate;
    private JLabel mTotalLessons;
    private DefaultListModel mStudentListModel; 
    private JList mStudentList;
    private JComboBox mMemberList;
    private JButton mButtonSave;
    private JProgressBar mProgressBar;
    private boolean mSubscribeAction = false;

    public ViewCourseSubscribe() {

        JLabel lblCursoASer = new JLabel("Curso");
        lblCursoASer.setFont(new Font("Arial", Font.PLAIN, 14));

        mCourseList = new JComboBox();
        mCourseList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                mStudentListModel.removeAllElements();

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
                    new CourseGetSubscribeList(currentCourse, ViewCourseSubscribe.this).run();
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

        JLabel lblAlunosInscritos = new JLabel("Alunos Inscritos");
        lblAlunosInscritos.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPaneStudent = new JScrollPane();
        scrollPaneStudent.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneStudent.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        mStudentListModel = new DefaultListModel();
        mStudentList = new JList(mStudentListModel);
        mStudentList.setFont(new Font("Arial", Font.BOLD, 14));
        scrollPaneStudent.setViewportView(mStudentList);

        JLabel lblLderes = new JLabel("Membro a ser inscrito");
        lblLderes.setFont(new Font("Arial", Font.PLAIN, 14));

        mMemberList = new JComboBox();
        mMemberList.setFont(new Font("Arial", Font.PLAIN, 14));

        mButtonSave = new JButton("Inscrever");
        mButtonSave.setIcon(new ImageIcon(ViewCourseSubscribe.class.getResource("/com/nicoinc/system/ibms/resources/button_subscribe.png")));
        mButtonSave.setFont(new Font("Arial", Font.PLAIN, 14));
        mButtonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Course course = (Course) mCourseList.getSelectedItem();
                Member member = (Member) mMemberList.getSelectedItem();
                if ((course != null) && (member != null)) {
                    CourseSubscribe subscribe = new CourseSubscribe();
                    subscribe.mCourseId = course.mId;
                    subscribe.mMemberId = member.mId;

                    disableFields();
                    new CourseSaveSubscribe(subscribe, ViewCourseSubscribe.this).run();
                }
            }
        });

        mProgressBar = new JProgressBar();
        mProgressBar.setIndeterminate(true);
        mProgressBar.setVisible(false);

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblCursoASer, GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
                        .addComponent(mCourseList, 0, 1110, Short.MAX_VALUE)
                        .addComponent(mMemberList, GroupLayout.PREFERRED_SIZE, 1110, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblLderes)
                        .addComponent(mButtonSave)
                        .addComponent(mProgressBar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE))
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
                    .addComponent(panel, GroupLayout.PREFERRED_SIZE, 396, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addComponent(lblLderes, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(mMemberList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addComponent(mButtonSave)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(mProgressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(39))
        );

        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                        .addComponent(scrollPaneStudent, GroupLayout.DEFAULT_SIZE, 1088, Short.MAX_VALUE)
                        .addGroup(gl_panel.createSequentialGroup()
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
                                .addComponent(mTotalLessons, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)))
                        .addComponent(lblAlunosInscritos))
                    .addContainerGap())
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
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(lblAlunosInscritos, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(scrollPaneStudent, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addContainerGap())
        );
        panel.setLayout(gl_panel);
        setLayout(groupLayout);

        // Fill course list
        long currentTime = Calendar.getInstance().getTimeInMillis();
        for (Course item : Application.getInstance().getCourseList()) {
            if (item.mEndDate.getTime() > currentTime) {
                item.mStringFormat = Course.COMPLETE_INFORMATION;
                mCourseList.addItem(item);
            }
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
                mMemberList.removeAllItems();
                mStudentListModel.removeAllElements();

                List<CourseSubscribe> subscribeList = (List<CourseSubscribe>) result.getData(CourseGetSubscribeList.SUBSCRIBE_LIST);
                for (CourseSubscribe subscribe : subscribeList) {
                    if (!subscribe.mIsTeacher) {
                        mStudentListModel.addElement(subscribe);
                    }
                }

                for (Member member : Application.getInstance().getMemberList()) {
                    if (member.mEndDate.getTime() != 0) {
                        continue;
                    }

                    boolean found = false;
                    for (CourseSubscribe subscribe : subscribeList) {
                        if (member.mId == subscribe.mMemberId) {
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        mMemberList.addItem(member);
                    }
                }

                if (mSubscribeAction) {
                    JOptionPane.showMessageDialog(this,"Aluno inscrito com sucesso.");
                    mSubscribeAction = false;
                }
                enableFields();
                break;

            case SUBSCRIBE_COURSE:
                mSubscribeAction = true;
                new CourseGetSubscribeList((Course)mCourseList.getSelectedItem(), ViewCourseSubscribe.this).run();
                break;

            default:
                break;
            }
        }
    }

    private void enableFields() {
        mCourseList.setEnabled(true);
        mMemberList.setEnabled(true);
        mButtonSave.setEnabled(true);
        mProgressBar.setVisible(false);
    }

    private void disableFields() {
        mCourseList.setEnabled(false);
        mMemberList.setEnabled(false);
        mButtonSave.setEnabled(false);
        mProgressBar.setVisible(true);
    }
}
