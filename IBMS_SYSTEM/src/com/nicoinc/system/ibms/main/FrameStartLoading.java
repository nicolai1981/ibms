package com.nicoinc.system.ibms.main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.nicoinc.system.ibms.command.CommandListener;
import com.nicoinc.system.ibms.command.CourseGetList;
import com.nicoinc.system.ibms.command.CourseTypeGetList;
import com.nicoinc.system.ibms.command.GenerationGetList;
import com.nicoinc.system.ibms.command.LeaderGetList;
import com.nicoinc.system.ibms.command.RequestResult;
import com.nicoinc.system.ibms.view.FrameHome;

public class FrameStartLoading extends JFrame implements CommandListener {
    private static final long serialVersionUID = -1343380208031960886L;
    private JPanel contentPane;
    private JLabel mMessage;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrameStartLoading frame = new FrameStartLoading();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public FrameStartLoading() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 630, 272);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        mMessage = new JLabel("Carregando lista de gerações");
        mMessage.setHorizontalAlignment(SwingConstants.CENTER);
        mMessage.setFont(new Font("Arial", Font.BOLD, 16));

        JProgressBar progressBar = new JProgressBar();
        
        JButton btnNewButton = new JButton("New button");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    new FrameHome();
                    FrameStartLoading.this.dispose();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        });
        
        JButton btnNewButton_1 = new JButton("");
        btnNewButton_1.setForeground(new Color(240, 240, 240));
        btnNewButton_1.setIcon(new ImageIcon(FrameStartLoading.class.getResource("/com/nicoinc/system/ibms/resources/logo.png")));
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(mMessage, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
                        .addComponent(progressBar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                            .addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
                            .addGap(130)))
                    .addContainerGap())
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(257)
                    .addComponent(btnNewButton)
                    .addContainerGap(258, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.TRAILING)
                .addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnNewButton_1)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(mMessage)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(btnNewButton)
                    .addContainerGap(111, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);

        new GenerationGetList(this).start();
    }

    @Override
    public void onCommandFinished(RequestResult result) {
        switch (result.getCode()) {
        case WITHOUT_CONNECTION:
            JOptionPane.showMessageDialog(this,"Sem conexão com a internet.\nNão foi possível completar a ação.");
            break;
        case SERVER_ERROR:
            JOptionPane.showMessageDialog(this,"Erro no servidor.\nTente mais tarde.");
            break;
        case UNKNOWN:
            JOptionPane.showMessageDialog(this,"Erro desconhecido.\nFeche o aplicativo e tente novamente.");
            break;
        case OK:
            switch (result.getCommand()) {
            case GET_GENERATION_LIST:
                new LeaderGetList(this).start();
                mMessage.setText("Carregando lista de líderes");
                break;
            case GET_LEADER_LIST:
                new CourseTypeGetList(this).start();
                mMessage.setText("Carregando lista de tipos de curso");
                break;
            case GET_COURSE_TYPE_LIST:
                new CourseGetList(this).start();
                mMessage.setText("Carregando lista de cursos");
                break;
            case GET_COURSE_LIST:
                try {
                    new FrameHome();
                    FrameStartLoading.this.dispose();
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
