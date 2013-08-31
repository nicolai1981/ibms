package com.nicoinc.system.ibms.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.nicoinc.system.ibms.command.CommandListener;
import com.nicoinc.system.ibms.command.CourseGetTeacherList;
import com.nicoinc.system.ibms.command.CourseSaveTeacherList;
import com.nicoinc.system.ibms.command.RequestResult;
import com.nicoinc.system.ibms.main.Application;
import com.nicoinc.system.ibms.model.Course;
import com.nicoinc.system.ibms.model.CourseSubscribe;
import com.nicoinc.system.ibms.model.CourseType;
import com.nicoinc.system.ibms.model.Member;

public class ViewCourseTeacher extends JPanel implements CommandListener {
    private static final SimpleDateFormat sDateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    private static final long serialVersionUID = -8213291145000189731L;

    private JComboBox mCourseList;
    private JLabel mCourseType;
    private JLabel mStartDate;
    private JLabel mEndDate;
    private JLabel mTotalLessons;
    private JList mLeaderList;
    private DefaultListModel mLeaderListModel; 
    private JList mTeacherList;
    private DefaultListModel mTeacherListModel; 
    private JButton mButtonAdd;
    private JButton mButtonRemove;
    private JButton mButtomClear;
    private JButton mButtonSave;
    private JProgressBar mProgressBar;

    public ViewCourseTeacher() {
        JLabel lblNewLabel = new JLabel("DEFINIR PROFESSORES DO CURSO");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel lblCursoASer = new JLabel("Curso");
        lblCursoASer.setFont(new Font("Arial", Font.PLAIN, 14));

        mCourseList = new JComboBox();
        mCourseList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                mLeaderListModel.removeAllElements();
                mTeacherListModel.removeAllElements();

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
                    new CourseGetTeacherList(currentCourse, ViewCourseTeacher.this).run();
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

        JLabel lblLderes = new JLabel("L\u00EDderes");
        lblLderes.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPaneLeaders = new JScrollPane();
        scrollPaneLeaders.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneLeaders.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JLabel lblProfessores = new JLabel("Professores");
        lblProfessores.setFont(new Font("Arial", Font.PLAIN, 14));

        mLeaderListModel = new DefaultListModel(); 
        mLeaderList = new JList(mLeaderListModel);
        mLeaderList.setFont(new Font("Arial", Font.BOLD, 14));
        scrollPaneLeaders.setViewportView(mLeaderList);

        JScrollPane scrollPaneTeacher = new JScrollPane();
        scrollPaneTeacher.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneTeacher.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        mTeacherListModel = new DefaultListModel(); 
        mTeacherList = new JList(mTeacherListModel);
        mTeacherList.setFont(new Font("Arial", Font.BOLD, 14));
        scrollPaneTeacher.setViewportView(mTeacherList);

        mButtonAdd = new JButton(">>");
        mButtonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Course currentCourse = (Course) mCourseList.getSelectedItem();

                Object[] selectedList = mLeaderList.getSelectedValues();
                for (Object item : selectedList) {
                    Member member = (Member) item;

                    CourseSubscribe teacher = new CourseSubscribe();
                    teacher.mCourseId = currentCourse.mId;
                    teacher.mMemberId = member.mId;
                    teacher.mMemberName = member.mName;
                    teacher.mStartDate = Calendar.getInstance().getTime();
                    teacher.mIsTeacher = true;
                    mTeacherListModel.addElement(teacher);
                }

                fillLeaderList();
            }
        });
        mButtonAdd.setFont(new Font("Arial", Font.PLAIN, 14));

        mButtonRemove = new JButton("<<");
        mButtonRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Object[] selectedList = mTeacherList.getSelectedValues();
                for (Object item : selectedList) {
                    CourseSubscribe teacher = (CourseSubscribe) item;
                    mTeacherListModel.removeElement(teacher);
                }

                fillLeaderList();
            }
        });
        mButtonRemove.setFont(new Font("Arial", Font.PLAIN, 14));

        mButtomClear = new JButton("Limpar");
        mButtomClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mTeacherListModel.removeAllElements();
                fillLeaderList();
            }
        });
        mButtomClear.setFont(new Font("Arial", Font.PLAIN, 14));

        mButtonSave = new JButton("Salvar");
        mButtonSave.setIcon(new ImageIcon(ViewCourseView.class.getResource("/com/nicoinc/system/ibms/resources/button_save.png")));
        mButtonSave.setFont(new Font("Arial", Font.PLAIN, 14));
        mButtonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Course currentCourse = (Course) mCourseList.getSelectedItem();
                if (currentCourse != null) {
                    List<CourseSubscribe> teacherList = new ArrayList<CourseSubscribe>();

                    for (int i=0; i < mTeacherListModel.size(); i++) {
                        teacherList.add((CourseSubscribe)mTeacherListModel.get(i));
                    }

                    disableFields();
                    new CourseSaveTeacherList(currentCourse, teacherList, ViewCourseTeacher.this).run();
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
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                .addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
                                .addComponent(mCourseList, Alignment.LEADING, 0, 1110, Short.MAX_VALUE)
                                .addComponent(lblCursoASer, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE))
                            .addContainerGap())
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addComponent(scrollPaneLeaders, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                                .addComponent(lblLderes))
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addComponent(mButtonAdd, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                                .addComponent(mButtonRemove, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                                .addComponent(mButtomClear, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addComponent(scrollPaneTeacher, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                                .addComponent(lblProfessores))
                            .addGap(11))
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(mButtonSave)
                            .addContainerGap(1019, Short.MAX_VALUE))
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(mProgressBar, GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
                            .addContainerGap())))
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblNewLabel)
                    .addGap(18)
                    .addComponent(lblCursoASer, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(mCourseList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(panel, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblLderes, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblProfessores, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(mButtonAdd, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(mButtonRemove, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(mButtomClear, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
                        .addComponent(scrollPaneLeaders, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                        .addComponent(scrollPaneTeacher, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(mButtonSave)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(mProgressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(15))
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
            case GET_COURSE_TEACHER_LIST:
                List<CourseSubscribe> teacherList = (List<CourseSubscribe>) result.getData(CourseGetTeacherList.TEACHER_LIST);
                for (CourseSubscribe teacher : teacherList) {
                    mTeacherListModel.addElement(teacher);
                }
                fillLeaderList();
                enableFields();
                break;

            case SAVE_COURSE_TEACHER_LIST:
                JOptionPane.showMessageDialog(this,"Curso alterado com sucesso.");
                enableFields();
                break;
            default:
                break;
            }
        }
    }

    private void fillLeaderList() {
        mLeaderListModel.removeAllElements();

        List<Member> leaderList = Application.getInstance().getLeaderList();
        for (Member leader : leaderList) {
            boolean found = false;
            for (int i=0; i < mTeacherListModel.getSize(); i++) {
                CourseSubscribe teacher = (CourseSubscribe)mTeacherListModel.get(i);
                if (teacher.mMemberId == leader.mId) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                mLeaderListModel.addElement(leader);
            }
        }
    }

    private void enableFields() {
        mCourseList.setEnabled(true);
        mLeaderList.setEnabled(true);
        mTeacherList.setEnabled(true);
        mButtonAdd.setEnabled(true);
        mButtonRemove.setEnabled(true);
        mButtomClear.setEnabled(true);
        mButtonSave.setEnabled(true);
        mProgressBar.setVisible(false);
    }

    private void disableFields() {
        mCourseList.setEnabled(false);
        mLeaderList.setEnabled(false);
        mTeacherList.setEnabled(false);
        mButtonAdd.setEnabled(false);
        mButtonRemove.setEnabled(false);
        mButtomClear.setEnabled(false);
        mButtonSave.setEnabled(false);
        mProgressBar.setVisible(true);
    }
}
