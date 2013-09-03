package com.nicoinc.system.ibms.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class FrameHome {

    private JFrame mFrame;
    private JPanel mCurrentJPanel = null;

    /**
     * Create the application.
     */
    public FrameHome() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        mFrame = new JFrame();
        mFrame.setBounds(100, 100, 1150, 700);
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        mFrame.setJMenuBar(menuBar);

        JMenu mnMembros = new JMenu("Membro");
        menuBar.add(mnMembros);

        JMenuItem mntmCadastrar = new JMenuItem("Novo Membro");
        mntmCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (mCurrentJPanel != null) {
                    mFrame.getContentPane().remove(mCurrentJPanel);
                }
                mCurrentJPanel = new ViewMemberNew();
                mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
                mFrame.validate();
            }
        });
        mnMembros.add(mntmCadastrar);

        JMenuItem mntmEditar = new JMenuItem("Editar");
        mnMembros.add(mntmEditar);

        JMenuItem mntmExcluir = new JMenuItem("Excluir");
        mnMembros.add(mntmExcluir);

        JMenuItem mntmVisualizar = new JMenuItem("Visualizar");
        mnMembros.add(mntmVisualizar);

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

        JMenuItem mntmInscrio_1 = new JMenuItem("Inscri\u00E7\u00E3o");
        mntmInscrio_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (mCurrentJPanel != null) {
                    mFrame.getContentPane().remove(mCurrentJPanel);
                }
                mCurrentJPanel = new ViewCourseSubscribe();
                mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
                mFrame.validate();
            }
        });
        mnEpb.add(mntmInscrio_1);

        JMenu mnCurso = new JMenu("Curso");
        mnEpb.add(mnCurso);

        JMenuItem mntmNovoCurso = new JMenuItem("Novo");
        mntmNovoCurso.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (mCurrentJPanel != null) {
                    mFrame.getContentPane().remove(mCurrentJPanel);
                }
                mCurrentJPanel = new ViewCourseNew();
                mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
                mFrame.validate();
            }
        });
        mnCurso.add(mntmNovoCurso);

        JMenuItem mntmEditarCurso = new JMenuItem("Editar");
        mntmEditarCurso.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (mCurrentJPanel != null) {
                    mFrame.getContentPane().remove(mCurrentJPanel);
                }
                mCurrentJPanel = new ViewCourseEdit();
                mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
                mFrame.validate();
            }
        });
        mnCurso.add(mntmEditarCurso);
        
        JMenuItem mntmProfessores = new JMenuItem("Professores");
        mntmProfessores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (mCurrentJPanel != null) {
                    mFrame.getContentPane().remove(mCurrentJPanel);
                }
                mCurrentJPanel = new ViewCourseTeacher();
                mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
                mFrame.validate();
            }
        });
        mnCurso.add(mntmProfessores);

        JMenuItem mntmAlunos = new JMenuItem("Alunos");
        mntmAlunos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (mCurrentJPanel != null) {
                    mFrame.getContentPane().remove(mCurrentJPanel);
                }
                mCurrentJPanel = new ViewCourseStudent();
                mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
                mFrame.validate();
            }
        });
        mnCurso.add(mntmAlunos);

        JMenuItem mntmVisualizarCurso = new JMenuItem("Visualizar");
        mntmVisualizarCurso.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (mCurrentJPanel != null) {
                    mFrame.getContentPane().remove(mCurrentJPanel);
                }
                mCurrentJPanel = new ViewCourseView();
                mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
                mFrame.validate();
            }
        });
        mnCurso.add(mntmVisualizarCurso);

        JMenu mnTipoDeCurso = new JMenu("Tipo de Curso");
        mnEpb.add(mnTipoDeCurso);

        JMenuItem mntmNovoTipoDe_1 = new JMenuItem("Novo");
        mntmNovoTipoDe_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (mCurrentJPanel != null) {
                    mFrame.getContentPane().remove(mCurrentJPanel);
                }
                mCurrentJPanel = new ViewCourseTypeNew();
                mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
                mFrame.validate();
            }
        });
        mnTipoDeCurso.add(mntmNovoTipoDe_1);

        JMenuItem mntmEditarTipoDe_1 = new JMenuItem("Editar");
        mntmEditarTipoDe_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (mCurrentJPanel != null) {
                    mFrame.getContentPane().remove(mCurrentJPanel);
                }
                mCurrentJPanel = new ViewCourseTypeEdit();
                mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
                mFrame.validate();
            }
        });
        mnTipoDeCurso.add(mntmEditarTipoDe_1);

        JMenuItem mntmExcluirTipoDe_1 = new JMenuItem("Ativar/Desativar");
        mntmExcluirTipoDe_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (mCurrentJPanel != null) {
                    mFrame.getContentPane().remove(mCurrentJPanel);
                }
                mCurrentJPanel = new ViewCourseTypeDeactivation();
                mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
                mFrame.validate();
            }
        });
        mnTipoDeCurso.add(mntmExcluirTipoDe_1);

        JMenuItem mntmVisualizar_1 = new JMenuItem("Visualizar");
        mntmVisualizar_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (mCurrentJPanel != null) {
                    mFrame.getContentPane().remove(mCurrentJPanel);
                }
                mCurrentJPanel = new ViewCourseTypeView();
                mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
                mFrame.validate();
            }
        });
        mnTipoDeCurso.add(mntmVisualizar_1);

        JMenu mnRelatrios_1 = new JMenu("Relat\u00F3rios");
        mnEpb.add(mnRelatrios_1);

        JMenu mnGeraes = new JMenu("Gera\u00E7\u00E3o");
        menuBar.add(mnGeraes);

        JMenuItem mntmCriarGerao = new JMenuItem("Criar Gera\u00E7\u00E3o");
        mntmCriarGerao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (mCurrentJPanel != null) {
                    mFrame.getContentPane().remove(mCurrentJPanel);
                }
                mCurrentJPanel = new ViewGenerationNew();
                mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
                mFrame.validate();
            }
        });
        mnGeraes.add(mntmCriarGerao);

        JMenuItem mntmEditarGerao = new JMenuItem("Editar Gera\u00E7\u00E3o");
        mntmEditarGerao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (mCurrentJPanel != null) {
                    mFrame.getContentPane().remove(mCurrentJPanel);
                }
                mCurrentJPanel = new ViewGenerationEdit();
                mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
                mFrame.validate();
            }
        });
        mnGeraes.add(mntmEditarGerao);

        JMenuItem mntmDesativarGerao = new JMenuItem(
                "Ativar/Desativar Gera\u00E7\u00E3o");
        mntmDesativarGerao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (mCurrentJPanel != null) {
                    mFrame.getContentPane().remove(mCurrentJPanel);
                }
                mCurrentJPanel = new ViewGenerationDeactivation();
                mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
                mFrame.validate();
            }
        });
        mnGeraes.add(mntmDesativarGerao);

        JMenuItem mntmVisualizarGerao = new JMenuItem(
                "Visualizar Gera\u00E7\u00E3o");
        mntmVisualizarGerao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (mCurrentJPanel != null) {
                    mFrame.getContentPane().remove(mCurrentJPanel);
                }
                mCurrentJPanel = new ViewGenerationView();
                mFrame.getContentPane().add(mCurrentJPanel, BorderLayout.NORTH);
                mFrame.validate();
            }
        });
        mnGeraes.add(mntmVisualizarGerao);

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

}
