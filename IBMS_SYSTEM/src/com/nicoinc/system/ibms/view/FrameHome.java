package com.nicoinc.system.ibms.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.nicoinc.system.ibms.model.Course;
import com.nicoinc.system.ibms.model.CourseType;
import com.nicoinc.system.ibms.model.Generation;
import com.nicoinc.system.ibms.model.Member;

public class FrameHome {
    private static Member sUser;
    private final int mType;

    private JFrame mFrame;
    private JPanel mCurrentJPanel = null;

    /**
     * Create the application.
     */
    public FrameHome(int type, Member user) {
        sUser = user;
        mType = type;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        mFrame = new JFrame();
        mFrame.setBounds(100, 100, 1150, 700);
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mFrame.setTitle("Sistema IBMS. Usuário: " + sUser.mName);

        JMenuBar menuBar = new JMenuBar();
        mFrame.setJMenuBar(menuBar);

        JMenu mnMembros = new JMenu("Membro");
        menuBar.add(mnMembros);

        JMenuItem mntmEditar = new JMenuItem("Gerenciar");
        mntmEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (mCurrentJPanel != null) {
                    mFrame.getContentPane().remove(mCurrentJPanel);
                }
                mCurrentJPanel = new ViewMemberSelect(FrameHome.this);
                mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
                mFrame.validate();
            }
        });
        mnMembros.add(mntmEditar);

        JMenuItem mntmMudarSenha = new JMenuItem("Mudar senha");
        mntmMudarSenha.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (mCurrentJPanel != null) {
                    mFrame.getContentPane().remove(mCurrentJPanel);
                }
                mCurrentJPanel = new ViewProfileChangePassword();
                mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
                mFrame.validate();
            }
        });
        mnMembros.add(mntmMudarSenha);

        JMenu mnEncontros = new JMenu("Encontro");
        menuBar.add(mnEncontros);

        JMenuItem mntmInscrio = new JMenuItem("Inscri\u00E7\u00E3o");
        mnEncontros.add(mntmInscrio);

        JMenu mnEncontro = new JMenu("Encontro");
        mnEncontros.add(mnEncontro);

        JMenuItem mntmNovoEncontro = new JMenuItem("Novo");
        mnEncontro.add(mntmNovoEncontro);

        JMenuItem mntmEditarEncontro = new JMenuItem("Editar");
        mnEncontro.add(mntmEditarEncontro);

        JMenuItem mntmExcluirEncontro = new JMenuItem("Excluir");
        mnEncontro.add(mntmExcluirEncontro);

        JMenuItem mntmVisualizarEncontro = new JMenuItem("Visualizar");
        mnEncontro.add(mntmVisualizarEncontro);

        JMenu mnTipoDeEncontro = new JMenu("Tipo de Encontro");
        mnEncontros.add(mnTipoDeEncontro);

        JMenuItem mntmNovoTipoDe = new JMenuItem("Novo");
        mnTipoDeEncontro.add(mntmNovoTipoDe);

        JMenuItem mntmEditarTipoDe = new JMenuItem("Editar");
        mnTipoDeEncontro.add(mntmEditarTipoDe);

        JMenuItem mntmExcluirTipoDe = new JMenuItem("Excluir");
        mnTipoDeEncontro.add(mntmExcluirTipoDe);

        JMenuItem mntmVisualizarTipoDe = new JMenuItem("Visualizar");
        mnTipoDeEncontro.add(mntmVisualizarTipoDe);

        JMenu mnRelatrios = new JMenu("Relat\u00F3rios");
        mnEncontros.add(mnRelatrios);

        JMenu mnEpb = new JMenu("EPB");
        menuBar.add(mnEpb);

        JMenuItem mntmEditarCurso = new JMenuItem("Curso");
        mntmEditarCurso.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (mCurrentJPanel != null) {
                    mFrame.getContentPane().remove(mCurrentJPanel);
                }
                mCurrentJPanel = new ViewCourseSelect(FrameHome.this);
                mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
                mFrame.validate();
            }
        });
        mnEpb.add(mntmEditarCurso);

        JMenuItem mntmEditarTipoDe_1 = new JMenuItem("Tipo de Curso");
        mntmEditarTipoDe_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (mCurrentJPanel != null) {
                    mFrame.getContentPane().remove(mCurrentJPanel);
                }
                mCurrentJPanel = new ViewCourseTypeSelect(FrameHome.this);
                mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
                mFrame.validate();
            }
        });
        mnEpb.add(mntmEditarTipoDe_1);

        JMenu mnRelatrios_1 = new JMenu("Relat\u00F3rios");
        mnEpb.add(mnRelatrios_1);

        JMenu mnGeraes = new JMenu("Gera\u00E7\u00E3o");
        menuBar.add(mnGeraes);

        JMenuItem mntmEditarGerao = new JMenuItem("Gerenciar");
        mntmEditarGerao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (mCurrentJPanel != null) {
                    mFrame.getContentPane().remove(mCurrentJPanel);
                }
                mCurrentJPanel = new ViewGenerationSelect(FrameHome.this);
                mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
                mFrame.validate();
            }
        });
        mnGeraes.add(mntmEditarGerao);

        JMenu mnRelatrios_2 = new JMenu("Relat\u00F3rios");
        mnGeraes.add(mnRelatrios_2);

        JMenu mnClula = new JMenu("C\u00E9lula");
        menuBar.add(mnClula);

        JMenuItem mntmCriarClula = new JMenuItem("Criar C\u00E9lula");
        mnClula.add(mntmCriarClula);

        JMenuItem mntmEditarClula = new JMenuItem("Editar C\u00E9lula");
        mnClula.add(mntmEditarClula);

        JMenuItem mntmDesativarClula = new JMenuItem("Desativar C\u00E9lula");
        mnClula.add(mntmDesativarClula);

        JMenuItem mntmVisualizarClula = new JMenuItem("Visualizar C\u00E9lula");
        mnClula.add(mntmVisualizarClula);

        JMenu mnRelatrios_3 = new JMenu("Relat\u00F3rios");
        mnClula.add(mnRelatrios_3);

        mFrame.setVisible(true);
    }

    public void showNewMember() {
        if (mCurrentJPanel != null) {
            mFrame.getContentPane().remove(mCurrentJPanel);
        }
        mCurrentJPanel = new ViewMemberNew(FrameHome.this);
        mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
        mFrame.validate();
    }

    public void showEditMember(Member member) {
        if (mCurrentJPanel != null) {
            mFrame.getContentPane().remove(mCurrentJPanel);
        }
        mCurrentJPanel = new ViewMemberEdit(FrameHome.this, member);
        mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
        mFrame.validate();
    }

    public void showDetailsMember(Member member) {
        if (mCurrentJPanel != null) {
            mFrame.getContentPane().remove(mCurrentJPanel);
        }
        mCurrentJPanel = new ViewMemberDetails(member);
        mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
        mFrame.validate();
    }

    public void showSelectMember() {
        if (mCurrentJPanel != null) {
            mFrame.getContentPane().remove(mCurrentJPanel);
        }
        mCurrentJPanel = new ViewMemberSelect(FrameHome.this);
        mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
        mFrame.validate();
    }

    public void showEditGeneration(Generation generation) {
        if (mCurrentJPanel != null) {
            mFrame.getContentPane().remove(mCurrentJPanel);
        }
        mCurrentJPanel = new ViewGenerationEdit(FrameHome.this, generation);
        mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
        mFrame.validate();
    }

    public void showSelectGeneration() {
        if (mCurrentJPanel != null) {
            mFrame.getContentPane().remove(mCurrentJPanel);
        }
        mCurrentJPanel = new ViewGenerationSelect(FrameHome.this);
        mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
        mFrame.validate();
    }

    public void showNewGeneration() {
        if (mCurrentJPanel != null) {
            mFrame.getContentPane().remove(mCurrentJPanel);
        }
        mCurrentJPanel = new ViewGenerationNew(FrameHome.this);
        mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
        mFrame.validate();
    }

    public void showDetailsGeneration(Generation generation) {
        if (mCurrentJPanel != null) {
            mFrame.getContentPane().remove(mCurrentJPanel);
        }
        mCurrentJPanel = new ViewGenerationView(generation);
        mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
        mFrame.validate();
    }

    public void showEditCourseType(CourseType courseType) {
        if (mCurrentJPanel != null) {
            mFrame.getContentPane().remove(mCurrentJPanel);
        }
        mCurrentJPanel = new ViewCourseTypeEdit(FrameHome.this, courseType);
        mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
        mFrame.validate();
    }

    public void showNewCourseType() {
        if (mCurrentJPanel != null) {
            mFrame.getContentPane().remove(mCurrentJPanel);
        }
        mCurrentJPanel = new ViewCourseTypeNew(FrameHome.this);
        mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
        mFrame.validate();
    }

    public void showSelectCourseType() {
        if (mCurrentJPanel != null) {
            mFrame.getContentPane().remove(mCurrentJPanel);
        }
        mCurrentJPanel = new ViewCourseTypeSelect(FrameHome.this);
        mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
        mFrame.validate();
    }

    public void showNewCourse() {
        if (mCurrentJPanel != null) {
            mFrame.getContentPane().remove(mCurrentJPanel);
        }
        mCurrentJPanel = new ViewCourseNew(FrameHome.this);
        mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
        mFrame.validate();
    }

    public void showEditCourse(Course course) {
        if (mCurrentJPanel != null) {
            mFrame.getContentPane().remove(mCurrentJPanel);
        }
        mCurrentJPanel = new ViewCourseEdit(FrameHome.this, course);
        mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
        mFrame.validate();
    }

    public void showTeacherCourse(Course course) {
        if (mCurrentJPanel != null) {
            mFrame.getContentPane().remove(mCurrentJPanel);
        }
        mCurrentJPanel = new ViewCourseTeacher(FrameHome.this, course);
        mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
        mFrame.validate();
    }

    public void showSubscribeCourse(Course course) {
        if (mCurrentJPanel != null) {
            mFrame.getContentPane().remove(mCurrentJPanel);
        }
        mCurrentJPanel = new ViewCourseSubscribe(course);
        mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
        mFrame.validate();
    }

    public void showStudentCourse(Course course) {
        if (mCurrentJPanel != null) {
            mFrame.getContentPane().remove(mCurrentJPanel);
        }
        mCurrentJPanel = new ViewCourseStudent(course);
        mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
        mFrame.validate();
    }

    public void showDetailsCourse(Course course) {
        if (mCurrentJPanel != null) {
            mFrame.getContentPane().remove(mCurrentJPanel);
        }
        mCurrentJPanel = new ViewCourseView(course);
        mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
        mFrame.validate();
    }

    public void showDetailsCourseType(CourseType courseType) {
        if (mCurrentJPanel != null) {
            mFrame.getContentPane().remove(mCurrentJPanel);
        }
        mCurrentJPanel = new ViewCourseTypeView(courseType);
        mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
        mFrame.validate();
    }

    public void showSelectCourse() {
        if (mCurrentJPanel != null) {
            mFrame.getContentPane().remove(mCurrentJPanel);
        }
        mCurrentJPanel = new ViewCourseSelect(FrameHome.this);
        mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
        mFrame.validate();
    }

    public static Member getUser() {
        return sUser;
    }
}
