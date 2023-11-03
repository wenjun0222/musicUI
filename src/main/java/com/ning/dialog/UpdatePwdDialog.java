package com.ning.dialog;

import com.ning.DiaLogParent;
import com.ning.common_component.CodeButton;
import com.ning.common_component.Buttons;
import com.ning.dialog.listener.UpdatePwdListener;
import com.ning.dialog.register.downPanel_component.InputPanel;
import com.ning.dialog.register.downPanel_component.PasswordPanel;
import com.ning.utils.LocalImageUtil;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.locks.ReentrantLock;

public class UpdatePwdDialog extends DiaLogParent {
    private final static UpdatePwdDialog INSTANCE=new UpdatePwdDialog();

    public static UpdatePwdDialog getUpdatePwdDialog() {
        return INSTANCE;
    }

    private final static ReentrantLock lock=new ReentrantLock();
    private UpdatePwdListener updatePwdListener=new UpdatePwdListener();
    /**
     * 顶部面板
     * */
    private JPanel topPanel=new JPanel();
    /**
     * 登录窗口的宽度
     * */
    private final static int WIDTH=400;
    /**
     * 登录窗口的高度
     * */
    private final static int HEIGHT=350;

    /**
     * 底部面板
     * */
    private JPanel bottomPanel=new JPanel();
    /**
     * 账号输入框面板,距离底部面板上侧距离45，左侧距离为80，自身高度40，宽度240
     * */
    private InputPanel inputPanel=new InputPanel("image/icon/用户.png","输入账号",
            80,45,240,40);
    /**
     * 密码输入框面板,距离底部面板上侧距离110，左侧距离为80，自身高度40，宽度240
     * */
    private PasswordPanel passwordPanel=new PasswordPanel("输入新密码",
            80,110,240,40);
    /**
     * 验证码输入面板,距离底部面板上侧距离175，左侧距离为80，自身高度40，宽度140
     * */
    private InputPanel codePanel=new InputPanel("image/icon/验证码.png","输入验证码",
            80,175,140,40);
    /**
     * 获取验证码按钮
     * */
    private CodeButton codeButton=new CodeButton();
    /**
     * 确认修改按钮
     * */
    private Buttons sureButton=new Buttons();
    /**
     * 窗体关闭图标面板
     * */
    private JPanel closePanel=new JPanel();
    /**
     * 窗体关闭图标，高度为20，正方形
     * */
    private JLabel closeIcon=new JLabel();
    /**
     * 窗体名称
     * */
    private JLabel windowName=new JLabel();
    /**
     * 初始化修改密码窗体，添加顶部面板以及底部面板
     * */
    public void updateDialogInit(){
        updatePwdListener.setUpdatePwdDialog(this);
        Point p = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        this.setBounds(p.x - WIDTH / 2, p.y - HEIGHT / 2, WIDTH,HEIGHT);
        this.setBackground(new Color(255,250,250));
        ImageIcon windowIcon= LocalImageUtil.getLocalSquareImage("image/熊猫.png",40);
        this.setIconImage(windowIcon.getImage().getScaledInstance(40,40,Image.SCALE_REPLICATE));
        this.setTitle("修改密码");
        topPanelInit();
        this.add(topPanel);
        bottomPanelInit();
        this.add(bottomPanel);
        this.setAlwaysOnTop(true);
//        this.addMouseListener(updatePwdListener.onTopListener());
        this.setVisible(true);
    }
    /**
     * 初始化登录窗体顶部面板，位置距离窗口上、左侧距离为0，长度为400，高为40,背景为深天蓝色
     * */
    private void topPanelInit(){
        topPanel.setBounds(0,0,400,40);
        topPanel.setLayout(null);
        topPanel.setBackground(new Color(30,144,255));
        closePanelInit();
        topPanel.add(closePanel);
        windowNameInit();
        topPanel.add(windowName);
    }
    /**初始化窗体关闭图标面板，高度为40，距离登录窗体顶部面板右侧距离为40，离上侧距离为0，，边框为深天蓝色
     * 关闭图标位置离面板上、左侧距离为10
     * */
    private void closePanelInit(){
        closePanel.setLayout(null);
        closePanel.setBackground(new Color(30,144,255));
        closePanel.setBounds(getWidth()-40,0,40,40);
        ImageIcon imageIcon= LocalImageUtil.
                getLocalSquareImage("image/icon/关闭白色.png",20);
        closeIcon.setIcon(imageIcon);
        closeIcon.setBounds(10,10,20,20);
        closeIcon.addMouseListener(updatePwdListener.closeIconListener());
        closePanel.add(closeIcon);
    }
    /**
     * 初始化软件名称标签，距离窗口顶部面板上侧距离为0、左侧距离为20，长160，宽40
     * 字体大小为24，颜色为白色,加粗
     * */
    private void windowNameInit(){
        windowName.setText("修改密码");
        windowName.setBounds(20,0,160,40);
        windowName.setForeground(Color.white);
        windowName.setFont(new Font(null,Font.BOLD,24));
    }
    /**
     * 初始化底部面板,宽为400，
     * */
    public void bottomPanelInit(){
        bottomPanel.setLayout(null);
        bottomPanel.setBackground(new Color(245,245,245));
        bottomPanel.setBounds(0,40,400,310);
        bottomPanel.add(inputPanel);
        bottomPanel.add(passwordPanel);
        bottomPanel.add(codePanel);
        codeButtonInit();
        bottomPanel.add(codeButton);
        sureButtonInit();
        bottomPanel.add(sureButton);
        promptLabelInit();
    }
    /**
     * 初始化获取验证码按钮,宽为98，高为40，距离左距离为222，上侧为175，背景为深天蓝色
     * 字体颜色为白烟色，大小为24，加粗
     * */
    public void codeButtonInit(){
        codeButton.setBounds(222,175,98,40);
        codeButton.setDiaLogParent(this);
        codeButton.setInputPanel(inputPanel);
        codeButton.setLock(lock);
    }
    /**
     * 初始化确认修改按钮,宽为240，高为40，距离左距离为80，上侧为240，背景为深天蓝色
     * 字体颜色为白烟色，大小为24，加粗
     * */
    public void sureButtonInit(){
        sureButton.setBounds(80,240,240,45);
        sureButton.setText("确认修改");
        sureButton.setColor(new Color(0,191,255));
        sureButton.setFont(new Font(null,Font.BOLD,24));
        sureButton.setForeground(new Color(245,245,245));
        sureButton.addMouseListener(updatePwdListener.sureListener());
        sureButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    /**
     * 各个输入框的文字提示
     * 用户账号输入提示，距离底部面板上侧距离为20
     * 用户密码输入提示，距离底部面板上侧距离为85
     * 验证码输入提示，距离底部面板上侧距离为150
     * */
    public void promptLabelInit(){
        JLabel accountPromptLabel=getPromptLabel(20,"(*必填)用户账号");
        bottomPanel.add(accountPromptLabel);
        JLabel passwordPromptLabel=getPromptLabel(85,"(*必填)密码6-10个长度，禁止空格");
        bottomPanel.add(passwordPromptLabel);
        JLabel codePromptLabel=getPromptLabel(150,"(*必填)验证码不得为空");
        bottomPanel.add(codePromptLabel);
    }
    /**
     * 用户名、账号，密码，验证码输入框以及性别单选按钮提示标签
     * 宽为240，高为20，距离主面板左侧距离为90。
     * 字体大小为10，红色，加粗。背景色为白色，
     * */
    private JLabel getPromptLabel(int y,String prompt){
        JLabel promptLabel=new JLabel();
        promptLabel.setBounds(80,y,290,25);
        promptLabel.setText(prompt);
        promptLabel.setFont(new Font(null,Font.BOLD,15));
        promptLabel.setForeground(Color.red);
        promptLabel.setBackground(Color.white);
        return promptLabel;
    }
    /**
     * 外部鼠标监听方法可以通过此方法来让输入框恢复原样
     * */
    public void clearCache(){
        this.dispose();
        inputPanel.clearCache();
        passwordPanel.clearCache();
        codePanel.clearCache();
    }
    public InputPanel getInputPanel() {
        return inputPanel;
    }
    public PasswordPanel getPasswordPanel() {
        return passwordPanel;
    }
    public InputPanel getCodePanel() {
        return codePanel;
    }
    public JPanel getClosePanel() {
        return closePanel;
    }
    public Buttons getSureButton() {
        return sureButton;
    }
}
