package com.nicoinc.system.ibms.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;

import com.nicoinc.system.ibms.command.CommandListener;
import com.nicoinc.system.ibms.command.MemberCreate;
import com.nicoinc.system.ibms.command.MemberGetList;
import com.nicoinc.system.ibms.command.RequestResult;
import com.nicoinc.system.ibms.main.Application;
import com.nicoinc.system.ibms.model.Generation;
import com.nicoinc.system.ibms.model.Member;

public class ViewMemberEdit extends JPanel implements CommandListener {
    private static final SimpleDateFormat sDateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    private static final long serialVersionUID = -8213291145000189731L;

    private JTextField mName;
    private JComboBox<String> mGender;
    private JTextField mBirthday;
    private JComboBox<String> mMaritialStatus;
    private JComboBox<String> mBloodType;
    private JTextField mRG;
    private JTextField mCPF;
    private JTextField mMobile;
    private JTextField mPhoneHome;
    private JTextField mPhoneWork;
    private JTextField mEmail;
    private JTextField mAddress;
    private JTextField mAddressMore;
    private JTextField mDistrict;
    private JTextField mCity;
    private JTextField mZIP;
    private JComboBox<Member> mLeaderList;
    private JLabel mGeneration;
    private JComboBox<String> mIsMember;
    private JTextField mStartDate;
    private JComboBox<String> mStartType;
    private JComboBox<String> mIsLeader;
    private JButton mButtonSave;
    private JProgressBar mProgressBar;
    private Member mMember;
    private FrameHome mHome;

    public ViewMemberEdit(FrameHome home, Member member) {
        mHome = home;
        mMember = member;

        JLabel lblNewLabel = new JLabel("EDITAR MEMBRO");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel lblGerao = new JLabel("Nome (*)");
        lblGerao.setFont(new Font("Arial", Font.PLAIN, 14));

        mName = new JTextField();
        mName.setFont(new Font("Arial", Font.PLAIN, 14));
        mName.setText(mMember.mName);

        JLabel lblNewLabel_1 = new JLabel("Sexo (*)");
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
        mGender = new JComboBox<String>();
        mGender.addItem("-");
        mGender.addItem("M");
        mGender.addItem("F");
        mGender.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblDataFinal = new JLabel("Data de nasc. (*)");
        lblDataFinal.setFont(new Font("Arial", Font.PLAIN, 14));
        mBirthday = new JTextField();
        mBirthday.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblEstadoCivil = new JLabel("Estado civil (*)");
        lblEstadoCivil.setFont(new Font("Arial", Font.PLAIN, 14));

        mMaritialStatus = new JComboBox<String>();
        mMaritialStatus.addItem("-");
        mMaritialStatus.addItem("SOLTEIRO");
        mMaritialStatus.addItem("CASADO");
        mMaritialStatus.addItem("VIUVO");
        mMaritialStatus.addItem("DESQUITADO");
        mMaritialStatus.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblSangue = new JLabel("Sangue");
        lblSangue.setFont(new Font("Arial", Font.PLAIN, 14));

        mBloodType = new JComboBox<String>();
        mBloodType.addItem("-");
        mBloodType.addItem("O+");
        mBloodType.addItem("O-");
        mBloodType.addItem("A+");
        mBloodType.addItem("A-");
        mBloodType.addItem("B+");
        mBloodType.addItem("B-");
        mBloodType.addItem("AB+");
        mBloodType.addItem("AB-");
        mBloodType.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblRg = new JLabel("RG");
        lblRg.setFont(new Font("Arial", Font.PLAIN, 14));

        mRG = new JTextField();
        mRG.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel label = new JLabel("CPF");
        label.setFont(new Font("Arial", Font.PLAIN, 14));

        mCPF = new JTextField();
        mCPF.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblCelular = new JLabel("Celular");
        lblCelular.setFont(new Font("Arial", Font.PLAIN, 14));

        mMobile = new JTextField();
        mMobile.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblTelResidencial = new JLabel("Tel. Residencial");
        lblTelResidencial.setFont(new Font("Arial", Font.PLAIN, 14));

        mPhoneHome = new JTextField();
        mPhoneHome.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblTelComercial = new JLabel("Tel. Comercial");
        lblTelComercial.setFont(new Font("Arial", Font.PLAIN, 14));

        mPhoneWork = new JTextField();
        mPhoneWork.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblEmail = new JLabel("E-mail");
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 14));

        mEmail = new JTextField();
        mEmail.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblEndereo = new JLabel("Endere\u00E7o");
        lblEndereo.setFont(new Font("Arial", Font.PLAIN, 14));

        mAddress = new JTextField();
        mAddress.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblComplemento = new JLabel("Complemento");
        lblComplemento.setFont(new Font("Arial", Font.PLAIN, 14));

        mAddressMore = new JTextField();
        mAddressMore.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblBairro = new JLabel("Bairro");
        lblBairro.setFont(new Font("Arial", Font.PLAIN, 14));

        mDistrict = new JTextField();
        mDistrict.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblCidade = new JLabel("Cidade");
        lblCidade.setFont(new Font("Arial", Font.PLAIN, 14));

        mCity = new JTextField();
        mCity.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblCep = new JLabel("CEP");
        lblCep.setFont(new Font("Arial", Font.PLAIN, 14));

        mZIP = new JTextField();
        mZIP.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(0, 0, 0)));

        JLabel lblLder = new JLabel("L\u00EDder");
        lblLder.setFont(new Font("Arial", Font.PLAIN, 14));

        mLeaderList = new JComboBox<Member>();
        mLeaderList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Member leader = (Member)mLeaderList.getSelectedItem();
                mGeneration.setText("-");
                for (Generation generation : Application.getInstance().getGenerationList()) {
                    if (generation.mId == leader.mGenerationId) {
                        mGeneration.setText(generation.mName);
                        break;
                    }
                }
            }
        });
        mLeaderList.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblGerao_1 = new JLabel("Gera\u00E7\u00E3o");
        lblGerao_1.setFont(new Font("Arial", Font.PLAIN, 14));

        mGeneration = new JLabel("-");
        mGeneration.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblMembro = new JLabel("Membro");
        lblMembro.setFont(new Font("Arial", Font.PLAIN, 14));

        mIsMember = new JComboBox<String>();
        mIsMember.addItem("NÃO");
        mIsMember.addItem("SIM");
        mIsMember.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblDataDeEntrada = new JLabel("Data de entrada");
        lblDataDeEntrada.setFont(new Font("Arial", Font.PLAIN, 14));

        mStartDate = new JTextField();
        mStartDate.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblModoDeEntrada = new JLabel("Modo de entrada");
        lblModoDeEntrada.setFont(new Font("Arial", Font.PLAIN, 14));

        mStartType = new JComboBox<String>();
        mStartType.addItem("-");
        mStartType.addItem("BATISMO");
        mStartType.addItem("ACLAMAÇÃO");
        mStartType.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblLder_1 = new JLabel("\u00C9 L\u00EDder?");
        lblLder_1.setFont(new Font("Arial", Font.PLAIN, 14));

        mIsLeader = new JComboBox<String>();
        mIsLeader.addItem("NÃO");
        mIsLeader.addItem("SIM");
        mIsLeader.setFont(new Font("Arial", Font.PLAIN, 14));

        mButtonSave = new JButton("Criar");
        mButtonSave.setIcon(new ImageIcon(ViewMemberEdit.class.getResource("/com/nicoinc/system/ibms/resources/button_save.png")));
        mButtonSave.setFont(new Font("Arial", Font.PLAIN, 14));
        mButtonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Member member = checkData();
                if (member != null) {
                    disableFields();
                    new MemberCreate(member, ViewMemberEdit.this).run();
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
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 1110, GroupLayout.PREFERRED_SIZE)
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblGerao)
                                        .addComponent(mName, GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE))
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(mBirthday, 0, 0, Short.MAX_VALUE)
                                        .addComponent(lblDataFinal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(mGender, 0, 0, Short.MAX_VALUE)
                                        .addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(ComponentPlacement.UNRELATED)
                                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(mMaritialStatus, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblEstadoCivil))
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
                                        .addComponent(mBloodType, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblSangue, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(lblRg, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(label, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(lblCelular, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(lblTelResidencial, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(lblTelComercial, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(lblEmail, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE))
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(mRG, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(mCPF, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(mMobile, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(mPhoneHome, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(mPhoneWork, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(mEmail, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)))
                            .addContainerGap())
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(mProgressBar, GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
                            .addContainerGap())
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(mButtonSave, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(1029))
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(lblEndereo, GroupLayout.PREFERRED_SIZE, 700, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(lblComplemento, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                            .addContainerGap())
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(mAddress, GroupLayout.PREFERRED_SIZE, 700, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(mAddressMore, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                            .addContainerGap())
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(lblBairro, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                            .addComponent(lblCidade, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(lblCep, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(mDistrict, GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(mCity, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(mZIP, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addComponent(panel, GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(mLeaderList, GroupLayout.PREFERRED_SIZE, 750, GroupLayout.PREFERRED_SIZE)
                                    .addGap(6)
                                    .addComponent(mGeneration, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)))
                            .addContainerGap())
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(lblLder, GroupLayout.PREFERRED_SIZE, 750, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(lblGerao_1, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                            .addContainerGap())
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(lblMembro, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(lblDataDeEntrada, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(lblModoDeEntrada, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(mIsMember, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(mStartDate, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(mStartType, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(717, Short.MAX_VALUE))
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(lblLder_1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(1040, Short.MAX_VALUE))
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(mIsLeader, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(1040, Short.MAX_VALUE))))
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblNewLabel)
                    .addGap(18)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblDataFinal, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSangue, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblEstadoCivil, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblGerao, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(mBloodType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mMaritialStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mBirthday, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mGender, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblRg, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCelular, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTelResidencial, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTelComercial, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                .addComponent(mRG, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                .addComponent(mCPF, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                .addComponent(mMobile, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblEndereo, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblComplemento, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                            .addComponent(mPhoneHome, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                            .addComponent(mPhoneWork, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                            .addComponent(mEmail, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(mAddress, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mAddressMore, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblBairro, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCep, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCidade, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(mDistrict, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                        .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                            .addComponent(mZIP, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                            .addComponent(mCity, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
                    .addGap(18)
                    .addComponent(panel, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblLder, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblGerao_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(mLeaderList, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mGeneration, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblMembro, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDataDeEntrada, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblModoDeEntrada, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(mIsMember, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mStartDate, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                        .addComponent(mStartType, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(lblLder_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(mIsLeader, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                    .addComponent(mButtonSave)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(mProgressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        setLayout(groupLayout);

        // Fill leader list
        Member memberNone = new Member();
        memberNone.mName = "NENHUM";
        mLeaderList.addItem(memberNone);
        for (Member item : Application.getInstance().getLeaderList()) {
            if (item.mEndDate.getTime() == 0) {
                mLeaderList.addItem(item);
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
            case MEMBER_GET_LIST:
                JOptionPane.showMessageDialog(this,"Membro criado com sucesso.");
                enableFields();
                break;

            case MEMBER_CREATE:
                clearFields();
                new MemberGetList(this).start();
                break;

            default:
                break;
            }
        }
    }

    private void enableFields() {
        mName.setEnabled(true);
        mGender.setEnabled(true);
        mBirthday.setEnabled(true);
        mMaritialStatus.setEnabled(true);
        mBloodType.setEnabled(true);
        mRG.setEnabled(true);
        mCPF.setEnabled(true);
        mMobile.setEnabled(true);
        mPhoneHome.setEnabled(true);
        mPhoneWork.setEnabled(true);
        mEmail.setEnabled(true);
        mAddress.setEnabled(true);
        mAddressMore.setEnabled(true);
        mDistrict.setEnabled(true);
        mCity.setEnabled(true);
        mZIP.setEnabled(true);
        mLeaderList.setEnabled(true);
        mGeneration.setEnabled(true);
        mIsMember.setEnabled(true);
        mStartDate.setEnabled(true);
        mStartType.setEnabled(true);
        mIsLeader.setEnabled(true);
        mButtonSave.setEnabled(true);
        mProgressBar.setVisible(false);
    }

    private void disableFields() {
        mName.setEnabled(false);
        mGender.setEnabled(false);
        mBirthday.setEnabled(false);
        mMaritialStatus.setEnabled(false);
        mBloodType.setEnabled(false);
        mRG.setEnabled(false);
        mCPF.setEnabled(false);
        mMobile.setEnabled(false);
        mPhoneHome.setEnabled(false);
        mPhoneWork.setEnabled(false);
        mEmail.setEnabled(false);
        mAddress.setEnabled(false);
        mAddressMore.setEnabled(false);
        mDistrict.setEnabled(false);
        mCity.setEnabled(false);
        mZIP.setEnabled(false);
        mLeaderList.setEnabled(false);
        mGeneration.setEnabled(false);
        mIsMember.setEnabled(false);
        mStartDate.setEnabled(false);
        mStartType.setEnabled(false);
        mIsLeader.setEnabled(false);
        mButtonSave.setEnabled(false);
        mProgressBar.setVisible(true);
    }

    private void clearFields() {
        mName.setText("");
        mGender.setSelectedIndex(0);
        mBirthday.setText("");;
        mMaritialStatus.setSelectedIndex(0);
        mBloodType.setSelectedIndex(0);
        mRG.setText("");
        mCPF.setText("");
        mMobile.setText("");
        mPhoneHome.setText("");
        mPhoneWork.setText("");
        mEmail.setText("");
        mAddress.setText("");;
        mAddressMore.setText("");
        mDistrict.setText("");
        mCity.setText("");
        mZIP.setText("");
        mLeaderList.setSelectedIndex(0);
        mGeneration.setText("-");
        mIsMember.setSelectedIndex(0);
        mStartDate.setText("");
        mStartType.setSelectedIndex(0);
        mIsLeader.setSelectedIndex(0);
    }

    private Member checkData() {
        Member member = new Member();

        String text = mName.getText().trim().toUpperCase();
        if (text.length() == 0) {
            JOptionPane.showMessageDialog(this,"O nome deve ser preenchido.");
            return null;
        }
        member.mName = text;

        text = mBirthday.getText().trim().toUpperCase();
        if (text.length() == 0) {
            JOptionPane.showMessageDialog(this,"A data de nascimento deve ser preenchida.");
            return null;
        }
        if (text.length() != 10) {
            JOptionPane.showMessageDialog(this,"A data de nascimento deve estar no formato DD/MM/YYYY");
            return null;
        }
        try {
            member.mBirthday = sDateFormatter.parse(text);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this,"A data de nascimento deve estar no formato DD/MM/YYYY");
            return null;
        }
        if (member.mBirthday.getTime() > Calendar.getInstance().getTimeInMillis()) {
            JOptionPane.showMessageDialog(this,"A data de nascimento não pode ser depois de hoje.");
            return null;
        }

        if (mGender.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this,"O sexo deve ser preenchido.");
            return null;
        }
        member.mGender = mGender.getSelectedIndex();

        if (mMaritialStatus.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this,"O estado civil deve ser preenchido.");
            return null;
        }
        member.mMaritialStatus = mMaritialStatus.getSelectedIndex();
        member.mBloodType = mBloodType.getSelectedIndex();

        text = mRG.getText().trim().toUpperCase();
        if (text.length() != 0) {
            member.mRG = text;
        }

        text = mCPF.getText().trim().toUpperCase();
        if (text.length() != 0) {
            CPFValidator cpfValidator = new CPFValidator();
            List<ValidationMessage> msgList = cpfValidator.invalidMessagesFor(text);
            if (msgList.size() == 0) {
                member.mCPF = text;
            } else {
                for (ValidationMessage item : msgList) {
                    if ("CPFError : INVALID FORMAT".equals(item.getMessage())) {
                        JOptionPane.showMessageDialog(this,"O formato do CPF deve ser XXX.XXX.XXX-XX.\nOs zeros devem ser colocados ex. 0004.270.720-00.");
                        return null;
                    } else if ("CPFError : INVALID CHECK DIGITS".equals(item.getMessage())) {
                        JOptionPane.showMessageDialog(this,"Digito de verificação do CPF está errado.");
                        return null;
                    }
                }
                JOptionPane.showMessageDialog(this,"O número do CPF está errado.");
                return null;
            }
        }

        text = mMobile.getText().trim().toUpperCase();
        if (text.length() != 0) {
            member.mMobile = text;
        }

        text = mPhoneHome.getText().trim().toUpperCase();
        if (text.length() != 0) {
            member.mPhoneHome = text;
        }

        text = mPhoneWork.getText().trim().toUpperCase();
        if (text.length() != 0) {
            member.mPhoneWork = text;
        }

        text = mEmail.getText().trim().toUpperCase();
        if (text.length() != 0) {
            member.mEmail = text;
        }

        text = mAddress.getText().trim().toUpperCase();
        if (text.length() != 0) {
            member.mAddress = text;
        }

        text = mAddressMore.getText().trim().toUpperCase();
        if (text.length() != 0) {
            member.mAddressMore = text;
        }

        text = mDistrict.getText().trim().toUpperCase();
        if (text.length() != 0) {
            member.mDistrict = text;
        }

        text = mCity.getText().trim().toUpperCase();
        if (text.length() != 0) {
            member.mCity = text;
        }

        text = mZIP.getText().trim().toUpperCase();
        if (text.length() != 0) {
            member.mZIP = text;
        }

        Member leader = (Member) mLeaderList.getSelectedItem();
        member.mLeaderId = leader.mId;
        member.mGenerationId = leader.mGenerationId;

        if (mIsMember.getSelectedIndex() == 1) {
            String startDate = mStartDate.getText().trim().toUpperCase();
            if (startDate.length() == 0) {
                JOptionPane.showMessageDialog(this,"A data quando virou membro deve ser preenchida.");
                return null;
            }
            if (startDate.length() != 10) {
                JOptionPane.showMessageDialog(this,"A data quando virou membro deve estar no formato DD/MM/YYYY");
                return null;
            }
            try {
                member.mStartDate = sDateFormatter.parse(startDate);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this,"A data quando virou membro deve estar no formato DD/MM/YYYY");
                return null;
            }
            if (member.mStartDate.getTime() > Calendar.getInstance().getTimeInMillis()) {
                JOptionPane.showMessageDialog(this,"A data quando virou membro não pode ser depois de hoje.");
                return null;
            }

            if (mStartType.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this,"O modo de entrada deve ser definido.");
                return null;
            }
            
        }

        member.mIsLeader = mIsLeader.getSelectedIndex() == 1;

        return member;
    }
}
