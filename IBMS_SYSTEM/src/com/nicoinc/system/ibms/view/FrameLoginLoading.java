package com.nicoinc.system.ibms.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.nicoinc.system.ibms.command.CommandListener;
import com.nicoinc.system.ibms.command.CourseGetList;
import com.nicoinc.system.ibms.command.CourseTypeGetList;
import com.nicoinc.system.ibms.command.GenerationGetList;
import com.nicoinc.system.ibms.command.MemberGetList;
import com.nicoinc.system.ibms.command.RequestResult;
import com.nicoinc.system.ibms.model.Member;

public class FrameLoginLoading extends JFrame implements CommandListener {
    private static final long serialVersionUID = -1343380208031960886L;
    private final Member mUser;
    private final int mType;

    private JPanel contentPane;
    private JLabel mMessage;

    /**
     * Create the frame.
     */
    public FrameLoginLoading(int type, Member user) {
        mUser = user;
        mType = type;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 630, 240);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        mMessage = new JLabel("Carregando lista de gerações");
        mMessage.setHorizontalAlignment(SwingConstants.CENTER);
        mMessage.setFont(new Font("Arial", Font.BOLD, 16));

        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);

        JButton btnNewButton_1 = new JButton("");
        btnNewButton_1.setForeground(new Color(240, 240, 240));
        btnNewButton_1.setIcon(new ImageIcon(FrameLoginLoading.class
                .getResource("/com/nicoinc/system/ibms/resources/logo.png")));
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(
                gl_contentPane
                        .createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                gl_contentPane
                                        .createParallelGroup(Alignment.TRAILING)
                                        .addComponent(mMessage, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
                                        .addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
                                        .addGroup(
                                                gl_contentPane
                                                        .createSequentialGroup()
                                                        .addGap(130)
                                                        .addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 330,
                                                                Short.MAX_VALUE).addGap(130))).addContainerGap()));
        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(
                gl_contentPane.createSequentialGroup().addGap(10).addComponent(btnNewButton_1).addGap(15)
                        .addComponent(mMessage).addGap(15)
                        .addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(10, Short.MAX_VALUE)));
        contentPane.setLayout(gl_contentPane);

        new GenerationGetList(this).start();
    }

    @Override
    public void onCommandFinished(RequestResult result) {
        switch (result.getCode()) {
        case WITHOUT_CONNECTION:
            JOptionPane.showMessageDialog(this, "Sem conexão com a internet.\nNão foi possível completar a ação.");
            break;
        case SERVER_ERROR:
            switch (result.getCommand()) {
            case GENERATION_GET_LIST:
                JOptionPane.showMessageDialog(this,
                        "Erro ao receber a lista de gerações. Código de erro:" + result.getData("ERROR_CODE")
                                + "\nFeche e tente novamente.");
                break;
            case MEMBER_GET_LIST:
                JOptionPane.showMessageDialog(this,
                        "Erro ao receber a lista de membros. Código de erro:" + result.getData("ERROR_CODE")
                                + "\nFeche e tente novamente.");
                break;
            case COURSE_TYPE_GET_LIST:
                JOptionPane.showMessageDialog(this, "Erro ao receber a lista de tipos de curso. Código de erro:"
                        + result.getData("ERROR_CODE") + "\nFeche e tente novamente.");
                break;
            case COURSE_GET_LIST:
                JOptionPane.showMessageDialog(this,
                        "Erro ao receber a lista de curso. Código de erro:" + result.getData("ERROR_CODE")
                                + "\nFeche e tente novamente.");
                break;
            default:
                JOptionPane.showMessageDialog(this, "Erro no servidor.\nTente mais tarde.");
            }
            break;
        case UNKNOWN:
            JOptionPane.showMessageDialog(this, "Erro desconhecido.\nFeche o aplicativo e tente novamente.");
            break;
        case OK:
            switch (result.getCommand()) {
            case GENERATION_GET_LIST:
                new MemberGetList(this).start();
                mMessage.setText("Carregando lista de membros");
                break;
            case MEMBER_GET_LIST:
                new CourseTypeGetList(this).start();
                mMessage.setText("Carregando lista de tipos de curso");
                break;
            case COURSE_TYPE_GET_LIST:
                new CourseGetList(this).start();
                mMessage.setText("Carregando lista de cursos");
                break;
            case COURSE_GET_LIST:
                try {
                    new FrameHome(mType, mUser);
                    FrameLoginLoading.this.dispose();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
            }
            break;
        }
    }
}
