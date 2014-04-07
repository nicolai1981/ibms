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
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.nicoinc.system.ibms.command.CommandListener;
import com.nicoinc.system.ibms.command.CourseCreateSubscribe;
import com.nicoinc.system.ibms.command.CourseGetSubscribeList;
import com.nicoinc.system.ibms.command.RequestResult;
import com.nicoinc.system.ibms.main.Application;
import com.nicoinc.system.ibms.model.Course;
import com.nicoinc.system.ibms.model.CourseSubscribe;
import com.nicoinc.system.ibms.model.Member;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ViewCourseSubscribe extends JPanel implements CommandListener {
    private static final SimpleDateFormat sDateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    private static final long serialVersionUID = -8213291145000189731L;

    private Course mCourse;
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

    public ViewCourseSubscribe(Course course) {
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

        mCourse = course;
        mStudentListModel = new DefaultListModel();

        JLabel lblLderes = new JLabel("Membro a ser inscrito");
        lblLderes.setFont(new Font("Arial", Font.PLAIN, 14));

        mMemberList = new JComboBox();
        mMemberList.setFont(new Font("Arial", Font.PLAIN, 14));

        mButtonSave = new JButton("Inscrever");
        mButtonSave.setIcon(new ImageIcon(ViewCourseSubscribe.class
                .getResource("/com/nicoinc/system/ibms/resources/button_subscribe.png")));
        mButtonSave.setFont(new Font("Arial", Font.PLAIN, 14));
        mButtonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Member member = (Member) mMemberList.getSelectedItem();
                if ((mCourse != null) && (member != null)) {
                    CourseSubscribe subscribe = new CourseSubscribe();
                    subscribe.mCourseId = mCourse.mId;
                    subscribe.mMemberId = member.mId;

                    disableFields();
                    new CourseCreateSubscribe(subscribe, ViewCourseSubscribe.this).run();
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

        mCourseType = new JLabel(mCourse.mCourseTypeName);
        mCourseType.setFont(new Font("Arial", Font.BOLD, 14));
        mCourseType.setBorder(border);

        mStartDate = new JLabel(sDateFormatter.format(mCourse.mStartDate));
        mStartDate.setFont(new Font("Arial", Font.BOLD, 14));
        mStartDate.setBorder(border);

        mEndDate = new JLabel(sDateFormatter.format(mCourse.mEndDate));
        mEndDate.setFont(new Font("Arial", Font.BOLD, 14));
        mEndDate.setBorder(border);

        mTotalLessons = new JLabel(String.valueOf(mCourse.mTotalLessons));
        mTotalLessons.setFont(new Font("Arial", Font.BOLD, 14));
        mTotalLessons.setBorder(border);

        JLabel lblAlunosInscritos = new JLabel("Alunos Inscritos");
        lblAlunosInscritos.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPaneStudent = new JScrollPane();
        scrollPaneStudent.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneStudent.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        mStudentList = new JList(mStudentListModel);
        mStudentList.setFont(new Font("Arial", Font.BOLD, 14));
        scrollPaneStudent.setViewportView(mStudentList);

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(scrollPaneStudent, GroupLayout.PREFERRED_SIZE, 1110, GroupLayout.PREFERRED_SIZE)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(mCourseType, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
                                    .addGap(5)
                                    .addComponent(mStartDate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(lblGerao, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
                                    .addGap(5)
                                    .addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)))
                            .addGap(5)
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(lblDataFinal, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                    .addGap(5)
                                    .addComponent(lblTotalDeAulas, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(mEndDate, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                    .addGap(5)
                                    .addComponent(mTotalLessons, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))))
                        .addComponent(mProgressBar, GroupLayout.PREFERRED_SIZE, 1110, GroupLayout.PREFERRED_SIZE)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(lblLderes, GroupLayout.PREFERRED_SIZE, 975, GroupLayout.PREFERRED_SIZE)
                                .addComponent(mMemberList, GroupLayout.PREFERRED_SIZE, 975, GroupLayout.PREFERRED_SIZE))
                            .addGap(5)
                            .addComponent(mButtonSave, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
                        .addComponent(lblAlunosInscritos, GroupLayout.PREFERRED_SIZE, 1110, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(10)
                    .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblGerao)
                                .addComponent(lblNewLabel_1)
                                .addComponent(lblDataFinal)
                                .addComponent(lblTotalDeAulas))
                            .addGap(1)
                            .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                .addComponent(mCourseType, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                .addComponent(mStartDate, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                .addComponent(mEndDate, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                .addComponent(mTotalLessons, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
                            .addGap(5)
                            .addComponent(lblLderes, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                            .addGap(1)
                            .addComponent(mMemberList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addComponent(mButtonSave, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
                    .addGap(5)
                    .addComponent(lblAlunosInscritos)
                    .addGap(1)
                    .addComponent(scrollPaneStudent, GroupLayout.PREFERRED_SIZE, 490, GroupLayout.PREFERRED_SIZE)
                    .addGap(5)
                    .addComponent(mProgressBar, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(10, Short.MAX_VALUE))
        );
        setLayout(groupLayout);

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
                mMemberList.removeAllItems();
                mStudentListModel.removeAllElements();

                List<CourseSubscribe> subscribeList = (List<CourseSubscribe>) result
                        .getData(CourseGetSubscribeList.SUBSCRIBE_LIST);
                for (CourseSubscribe subscribe : subscribeList) {
                    if (!subscribe.mIsTeacher) {
                        mStudentListModel.addElement(subscribe);
                    }
                }

                for (Member member : Application.getInstance().getMemberActivatedList()) {
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
                    JOptionPane.showMessageDialog(this, "Aluno inscrito com sucesso.");
                    mSubscribeAction = false;
                }
                enableFields();
                break;

            case COURSE_CREATE_SUBSCRIBE:
                mSubscribeAction = true;
                new CourseGetSubscribeList(mCourse, ViewCourseSubscribe.this).run();
                break;

            default:
                break;
            }
        }
    }

    private void enableFields() {
        mMemberList.setEnabled(true);
        mButtonSave.setEnabled(true);
        mProgressBar.setVisible(false);
    }

    private void disableFields() {
        mMemberList.setEnabled(false);
        mButtonSave.setEnabled(false);
        mProgressBar.setVisible(true);
    }
}
