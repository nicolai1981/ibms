package com.nicoinc.system.ibms.view;

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
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import com.nicoinc.system.ibms.command.CommandListener;
import com.nicoinc.system.ibms.command.CourseGetSubscribeList;
import com.nicoinc.system.ibms.command.CourseSaveTeacherList;
import com.nicoinc.system.ibms.command.RequestResult;
import com.nicoinc.system.ibms.main.Application;
import com.nicoinc.system.ibms.model.Course;
import com.nicoinc.system.ibms.model.CourseSubscribe;
import com.nicoinc.system.ibms.model.Member;

public class ViewCourseTeacher extends JPanel implements CommandListener {
    private static final SimpleDateFormat sDateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    private static final long serialVersionUID = -8213291145000189731L;

    private Course mCourse;
    private FrameHome mHome;
    private JList mLeaderList;
    private DefaultListModel mLeaderListModel;
    private JList mTeacherList;
    private DefaultListModel mTeacherListModel;
    private JButton mButtonAdd;
    private JButton mButtonRemove;
    private JButton mButtomClear;
    private JButton mButtonSave;
    private JProgressBar mProgressBar;

    public ViewCourseTeacher(FrameHome home, Course course) {
        mHome = home;
        mCourse = course;

        JLabel lblNewLabel = new JLabel("DEFINIR PROFESSORES DO CURSO");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));

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
                Member[] selectedList = (Member[])mLeaderList.getSelectedValues();
                for (Member item : selectedList) {
                    CourseSubscribe teacher = new CourseSubscribe();
                    teacher.mCourseId = mCourse.mId;
                    teacher.mMemberId = item.mId;
                    teacher.mMemberName = item.mName;
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
                CourseSubscribe[] selectedList = (CourseSubscribe[])mTeacherList.getSelectedValues();
                for (CourseSubscribe teacher : selectedList) {
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
        mButtonSave.setIcon(new ImageIcon(ViewCourseView.class
                .getResource("/com/nicoinc/system/ibms/resources/button_save.png")));
        mButtonSave.setFont(new Font("Arial", Font.PLAIN, 14));
        mButtonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (mCourse != null) {
                    List<CourseSubscribe> teacherList = new ArrayList<CourseSubscribe>();

                    for (int i = 0; i < mTeacherListModel.size(); i++) {
                        teacherList.add((CourseSubscribe) mTeacherListModel.get(i));
                    }

                    disableFields();
                    new CourseSaveTeacherList(mCourse, teacherList, ViewCourseTeacher.this).run();
                }
            }
        });

        mProgressBar = new JProgressBar();
        mProgressBar.setIndeterminate(true);
        mProgressBar.setVisible(false);

        JLabel lblGerao = new JLabel("Tipo do curso");
        lblGerao.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblNewLabel_1 = new JLabel("Data inicial");
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblDataFinal = new JLabel("Data final");
        lblDataFinal.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblTotalDeAulas = new JLabel("Total de Aulas");
        lblTotalDeAulas.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel courseType = new JLabel(mCourse.mCourseTypeName);
        courseType.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel startDate = new JLabel(sDateFormatter.format(mCourse.mStartDate));
        startDate.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel endDate = new JLabel(sDateFormatter.format(mCourse.mEndDate));
        endDate.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel totalLessons = new JLabel(String.valueOf(mCourse.mTotalLessons));
        totalLessons.setFont(new Font("Arial", Font.BOLD, 14));

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(mProgressBar, GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE)
                        .addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(scrollPaneLeaders, GroupLayout.PREFERRED_SIZE, 510, GroupLayout.PREFERRED_SIZE)
                            .addGap(5)
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addComponent(mButtonAdd, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                    .addComponent(mButtomClear, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mButtonRemove, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)))
                            .addGap(5)
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addComponent(lblProfessores)
                                .addComponent(scrollPaneTeacher, GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)))
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(courseType, GroupLayout.PREFERRED_SIZE, 480, GroupLayout.PREFERRED_SIZE)
                                    .addGap(10)
                                    .addComponent(startDate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(lblGerao, GroupLayout.PREFERRED_SIZE, 480, GroupLayout.PREFERRED_SIZE)
                                    .addGap(10)
                                    .addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)))
                            .addGap(10)
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(lblDataFinal, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                                    .addGap(10)
                                    .addComponent(lblTotalDeAulas, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(endDate, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                                    .addGap(10)
                                    .addComponent(totalLessons, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))))
                        .addComponent(lblLderes, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mButtonSave, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(10)
                    .addComponent(lblNewLabel)
                    .addGap(15)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblGerao)
                        .addComponent(lblNewLabel_1)
                        .addComponent(lblDataFinal)
                        .addComponent(lblTotalDeAulas))
                    .addGap(5)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(courseType)
                        .addComponent(startDate)
                        .addComponent(endDate)
                        .addComponent(totalLessons))
                    .addGap(10)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblLderes, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblProfessores, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(scrollPaneTeacher, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(mButtonAdd, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                            .addGap(5)
                            .addComponent(mButtonRemove, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                            .addGap(5)
                            .addComponent(mButtomClear, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
                        .addComponent(scrollPaneLeaders, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
                    .addGap(10)
                    .addComponent(mButtonSave)
                    .addGap(5)
                    .addComponent(mProgressBar, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                    .addGap(10))
        );
        setLayout(groupLayout);

        disableFields();
        new CourseGetSubscribeList(mCourse, this).run();
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
            case COURSE_GET_SUBSCRIBE_LIST:
                List<CourseSubscribe> subscribeList = (List<CourseSubscribe>) result
                        .getData(CourseGetSubscribeList.SUBSCRIBE_LIST);
                for (CourseSubscribe subscribe : subscribeList) {
                    if (subscribe.mIsTeacher) {
                        mTeacherListModel.addElement(subscribe);
                    }
                }
                fillLeaderList();
                enableFields();
                break;

            case COURSE_SAVE_TEACHER_LIST:
                JOptionPane.showMessageDialog(this, "Curso alterado com sucesso.");
                enableFields();
                mHome.showSelectCourse();
                break;
            default:
                break;
            }
        }
    }

    private void fillLeaderList() {
        mLeaderListModel.removeAllElements();

        List<Member> leaderList = Application.getInstance().getLeaderActivatedList();
        for (Member leader : leaderList) {
            boolean found = false;
            for (int i = 0; i < mTeacherListModel.getSize(); i++) {
                CourseSubscribe teacher = (CourseSubscribe) mTeacherListModel.get(i);
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
        mLeaderList.setEnabled(true);
        mTeacherList.setEnabled(true);
        mButtonAdd.setEnabled(true);
        mButtonRemove.setEnabled(true);
        mButtomClear.setEnabled(true);
        mButtonSave.setEnabled(true);
        mProgressBar.setVisible(false);
    }

    private void disableFields() {
        mLeaderList.setEnabled(false);
        mTeacherList.setEnabled(false);
        mButtonAdd.setEnabled(false);
        mButtonRemove.setEnabled(false);
        mButtomClear.setEnabled(false);
        mButtonSave.setEnabled(false);
        mProgressBar.setVisible(true);
    }
}
