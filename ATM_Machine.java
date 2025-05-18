import java.awt.CardLayout;
import java.awt.Color;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.print.DocFlavor.STRING;
import javax.swing.*;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionListener;

public class ATM_Machine 
{
    public static void main(String[] args) 
    {
        Proceed go = new Proceed();
        go.Direction(); 
    }
}

class Proceed
{
    //Global declaration section start
    JFrame jr;
    JPanel Back_Screen_Panel, Pin_Screen_Panel,Back_Keypad_Panel,Keypad_Panel,Back_Receipt_Panel,Receipt_Panel,Back_CashCard_panel,Option_Screen_Panel;
    JPanel CurrentPanel,Welcome_Screen_Panel,Menu_screen_panel,Check_Balance_panel,Change_Pin_Panel,Withdrawal_Panel,Deposit_Panel,ReceiptPanel,Printed_Receipt_Panel;
    JLabel set_icon,Title,Pin_label,Set_Card_Field,Set_Cash_Field,Avail_Balance_Label,New_Pin_Label,Confirm_Pin_Label,Withdrawal_Label,Deposit_Label;
    ImageIcon icon;
    JButton K1,K2,K3,K4,K5,K6,K7,K8,K9,K0,K00,K,Enter,Delete,Clear,Back,Receipt,Card;
    JButton L1, L2, L3, R1, R2, R3;
    JButton LO1,LO2,LO3,RO1,RO2,RO3;
    JButton[] keyButtons;
    JButton[] Side_Buttons;
    JButton[] Screen_options;
    boolean IsCardInserted=false,Login_Flag=false,Pin_Card=false,ChangePin_Key=false,new_pass=true,Confirm_pass=false,Next=false,Change_Pin_Flag=false,Withdrawal_key=false,Withdrawal_Flag=false,Deposit_Flag=false,IsCashInsert=false,Done=false;
    JPasswordField pin,New_Pin,Confirm_Pin;
    CardLayout cardLayout,ReceiptCard;
    JLabel Welcome_msg;
    String code="",Current_Card="",Transc_Type="",Receipt_Format="";
    int[] Password;
    char[] Pin_Digit,Current_Digits;
    int Attempts=3,Digit=0,limit=0,Deposit_Amt,valid_digit=0,Amount;
    Double Balance=2000.0,Deposit,Withdrawal;
    JTextField Avail_Balance,Withdrawal_Field,Deposit_Field;
    //Global declaration section end

    //Direction function start 
    void Direction()
    {
        jr = new JFrame("Infinity ATM Simulator");

        Build_Screen_panel();
        jr.add(Back_Screen_Panel);

        Build_Keypad_panel();
        jr.add(Back_Keypad_Panel);
        jr.add(Back_Receipt_Panel);

        Build_CashCard_panel();
        jr.add(Back_CashCard_panel);

        jr.setSize(900, 750);
        jr.setLayout(null);
        jr.setVisible(true);
        jr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Insert_Card();
    }
    //Direction function end

    //Build ATM logo and Bank Name function start
    void Build_ATM_Logo_Name(JPanel Panel)
    {
        icon = new ImageIcon("Icons/ATM_Logo.png");
        set_icon = new JLabel(icon);
        set_icon.setBounds(15, 20, icon.getIconWidth(), icon.getIconHeight());
        Panel.add(set_icon);

        Title = new JLabel("INFINITY ATM SIMULATOR");
        Title.setBounds(100, 6, 400, 100);
        Title.setFont(new Font("Times New Roman", Font.BOLD, 25));
        Panel.add(Title);
    }
    //Build ATM logo and Bank Name function end

    //Build screen panel section start
    void Build_Screen_panel()
    {
    //  ========== Back panel of main screen ==========
        Back_Screen_Panel = new JPanel();
        Back_Screen_Panel.setBackground(Color.gray);
        Back_Screen_Panel.setBounds(10, 10, 625, 390);
        Back_Screen_Panel.setLayout(null);

    // ========== Main panel ==========
        cardLayout = new CardLayout();
        CurrentPanel = new JPanel(cardLayout);
        CurrentPanel.setBounds(85, 20, 450, 350);
        CurrentPanel.setLayout(cardLayout);
        Back_Screen_Panel.add(CurrentPanel);

    // ========== Receipt Panel ==========
        Back_Receipt_Panel=new JPanel();
        Back_Receipt_Panel.setBackground(Color.gray);
        Back_Receipt_Panel.setBounds(645,10,225,390);
        Back_Receipt_Panel.setLayout(null);

        ReceiptCard = new CardLayout();
        ReceiptPanel = new JPanel(ReceiptCard);
        ReceiptPanel.setBounds(12, 60, 200, 300);
        ReceiptPanel.setLayout(ReceiptCard);
        Back_Receipt_Panel.add(ReceiptPanel);

        Receipt_Panel=new JPanel();
        Receipt_Panel.setBackground(Color.white);
        Receipt_Panel.setBounds(12, 60, 200, 300);
        Receipt_Panel.setLayout(null);
        Back_Receipt_Panel.add(Receipt_Panel);

        Receipt=new JButton("RECEIPT");
        Receipt.setBounds(12,20,200,30);
        Receipt.setFont(new Font("Times New Roman",Font.BOLD,20));
        Back_Receipt_Panel.add(Receipt);
        Receipt.setActionCommand("Receipt");

    // ========== Welcome Screen Panel ==========
        Welcome_Screen_Panel = new JPanel();
        Welcome_Screen_Panel.setBackground(new Color(135, 206, 235));
        Welcome_Screen_Panel.setLayout(null);
        Build_ATM_Logo_Name(Welcome_Screen_Panel);

        Welcome_msg=new JLabel("INFINITY BANK WELCOMES YOU");
        Welcome_msg.setBounds(20, 120, 420, 100);
        Welcome_msg.setForeground(Color.blue);
        Welcome_msg.setFont(new Font("Times New Roman", Font.BOLD, 25));
        Welcome_Screen_Panel.add(Welcome_msg);

    // ========== PIN Screen Panel ==========
        Pin_Screen_Panel = new JPanel();
        Pin_Screen_Panel.setBackground(new Color(135, 206, 235));
        Pin_Screen_Panel.setLayout(null);
        Build_ATM_Logo_Name(Pin_Screen_Panel);

        Pin_label = new JLabel("ENTER PIN FOR VERIFICATION");
        Pin_label.setBounds(60, 150, 400, 30);
        Pin_label.setFont(new Font("Times New Roman", Font.BOLD, 22));
        Pin_Screen_Panel.add(Pin_label);

        pin = new JPasswordField();
        pin.setBounds(150, 190, 150, 40); 
        pin.setHorizontalAlignment(JTextField.CENTER);
        pin.setFont(new Font("Times New Roman", Font.PLAIN, 60));
        pin.setEditable(false);
        Pin_Screen_Panel.add(pin);

    // ========== Transcation menu panel ==========
        Menu_screen_panel=new JPanel();
        Menu_screen_panel.setBackground(new Color(135, 206, 235));
        Menu_screen_panel.setLayout(null);
        Build_ATM_Logo_Name(Menu_screen_panel);

        //Left side button options
        LO1=new JButton("WITHDRAWAL");
        LO2=new JButton("CHANGE PIN");
        LO3=new JButton("LOGOUT");

        //Right side button options
        RO1=new JButton("DEPOSIT");
        RO2=new JButton("BALANCE");
        RO3=new JButton("PRINT RECEIPT");

    // ========== Check Balance panel ==========
        Check_Balance_panel=new JPanel();
        Check_Balance_panel.setBackground(new Color(135, 206, 235));
        Check_Balance_panel.setLayout(null);
        Build_ATM_Logo_Name(Check_Balance_panel);

        Avail_Balance_Label=new JLabel("AVAILABLE BALANCE");
        Avail_Balance_Label.setBounds(100, 150, 400, 30);
        Avail_Balance_Label.setFont(new Font("Times New Roman", Font.BOLD, 22));
        Check_Balance_panel.add(Avail_Balance_Label);

        Avail_Balance=new JTextField();
        Avail_Balance.setBounds(80, 190, 290, 40); 
        Avail_Balance.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        Avail_Balance.setEditable(true);
        Avail_Balance.setText(""+Balance);
        Check_Balance_panel.add(Avail_Balance);

    // ========== Change pin panel ==========
        Change_Pin_Panel=new JPanel();
        Change_Pin_Panel.setBackground(new Color(135, 206, 235));
        Change_Pin_Panel.setLayout(null);
        Build_ATM_Logo_Name(Change_Pin_Panel);

        New_Pin_Label=new JLabel("ENTER NEW PIN");
        New_Pin_Label.setBounds(125, 120, 400, 30);
        New_Pin_Label.setFont(new Font("Times New Roman", Font.BOLD, 22));
        Change_Pin_Panel.add(New_Pin_Label);

        New_Pin=new JPasswordField();
        New_Pin.setBounds(150, 160, 150, 40);  
        New_Pin.setHorizontalAlignment(JTextField.CENTER);
        New_Pin.setFont(new Font("Times New Roman", Font.PLAIN, 60));
        New_Pin.setEditable(false);
        Change_Pin_Panel.add(New_Pin);

        Confirm_Pin_Label=new JLabel("CONFIRM NEW PIN");
        Confirm_Pin_Label.setBounds(120, 210, 400, 30);
        Confirm_Pin_Label.setFont(new Font("Times New Roman", Font.BOLD, 22));
        Change_Pin_Panel.add(Confirm_Pin_Label);
        
        Confirm_Pin=new JPasswordField();
        Confirm_Pin.setBounds(150, 250,150, 40); 
        Confirm_Pin.setHorizontalAlignment(JTextField.CENTER);
        Confirm_Pin.setFont(new Font("Times New Roman", Font.PLAIN, 60));
        Confirm_Pin.setEditable(false);
        Change_Pin_Panel.add(Confirm_Pin);

    // ========== Withdrawal panel ==========
        Withdrawal_Panel=new JPanel();
        Withdrawal_Panel.setBackground(new Color(135, 206, 235));
        Withdrawal_Panel.setLayout(null);
        Build_ATM_Logo_Name(Withdrawal_Panel);

        Withdrawal_Label=new JLabel("ENTER WITHDRAWAL AMOUNT");
        Withdrawal_Label.setBounds(55, 150, 400, 30);
        Withdrawal_Label.setFont(new Font("Times New Roman", Font.BOLD, 22));
        Withdrawal_Panel.add(Withdrawal_Label);

        Withdrawal_Field=new JTextField();
        Withdrawal_Field.setBounds(80, 190, 290, 40);
        Withdrawal_Field.setHorizontalAlignment(JTextField.CENTER);
        Withdrawal_Field.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        Withdrawal_Field.setEditable(false);
        Withdrawal_Panel.add(Withdrawal_Field);
        
    // ========== Deposit panel ==========
        Deposit_Panel=new JPanel();
        Deposit_Panel.setBackground(new Color(135, 206, 235));
        Deposit_Panel.setLayout(null);
        Build_ATM_Logo_Name(Deposit_Panel);

        Deposit_Label=new JLabel("ENTER DEPOSIT AMOUNT");
        Deposit_Label.setBounds(85, 150, 400, 30);
        Deposit_Label.setFont(new Font("Times New Roman", Font.BOLD, 22));
        Deposit_Panel.add(Deposit_Label);

        Deposit_Field=new JTextField();
        Deposit_Field.setBounds(80, 190, 290, 40);
        Deposit_Field.setHorizontalAlignment(JTextField.CENTER);
        Deposit_Field.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        Deposit_Field.setEditable(false);
        Deposit_Panel.add(Deposit_Field);

        Screen_options=new JButton[]{LO1,LO2,LO3,RO1,RO2,RO3};
        int[][] Opt_pos={
            {10, 130, 210, 40},{10, 200, 210, 40},{10, 270, 210, 40},
            {230, 130, 210, 40},{230, 200, 210, 40},{230, 270, 210, 40}
        };

        for(int i=0;i<Opt_pos.length;i++)
        {
            Screen_options[i].setBounds(Opt_pos[i][0],Opt_pos[i][1],Opt_pos[i][2],Opt_pos[i][3]);
            Screen_options[i].setEnabled(false);
            Screen_options[i].setBackground(Color.blue);
            Screen_options[i].setFont(new Font("Times New Roman", Font.BOLD, 20));
            Menu_screen_panel.add(Screen_options[i]);
        }

        // Add all panels to card layout container
        CurrentPanel.add(Welcome_Screen_Panel, "Welcome");
        CurrentPanel.add(Pin_Screen_Panel, "Pin");
        CurrentPanel.add(Menu_screen_panel, "Menu");
        CurrentPanel.add(Check_Balance_panel,"Balance");
        CurrentPanel.add(Change_Pin_Panel,"Change Pin");
        CurrentPanel.add(Withdrawal_Panel,"Withdrawal");
        CurrentPanel.add(Deposit_Panel,"Deposit");

        ReceiptPanel.add(Receipt_Panel,"Empty receipt");
       
        // Side buttons
        L1 = new JButton(">");
        L2 = new JButton(">");
        L3 = new JButton(">");
        R1 = new JButton("<");
        R2 = new JButton("<");
        R3 = new JButton("<");

        Side_Buttons = new JButton[]{ L1, L2, L3, R1, R2, R3 };

        for (JButton LR : Side_Buttons)
        {
            LR.setFont(new Font("Times New Roman", Font.BOLD, 25));
        }

        int[][] SetBtnPos = {
            {10, 150, 70, 40}, {10, 220, 70, 40}, {10, 290, 70, 40},
            {540, 150, 70, 40}, {540, 220, 70, 40}, {540, 290, 70, 40}
        };

        for (int i = 0; i < SetBtnPos.length; i++)
        {
            Side_Buttons[i].setBounds(SetBtnPos[i][0], SetBtnPos[i][1], SetBtnPos[i][2], SetBtnPos[i][3]);
            Back_Screen_Panel.add(Side_Buttons[i]);
        }

        L1.addActionListener(e->
        {
            if(Login_Flag==true)
            {
                Withdrawal_key=true;
                cardLayout.show(CurrentPanel, "Withdrawal");
                Side_button_Operator('D');
                Current_Card="Withdrawal";
            }
        });

        L2.addActionListener(e->
        {
            if(Login_Flag==true)
            {
                ChangePin_Key=true;
                cardLayout.show(CurrentPanel, "Change Pin");
                Side_button_Operator('D');
                Current_Card="Change Pin";
            }
        });

        L3.addActionListener(e->
        {
            int Choice=JOptionPane.showConfirmDialog(Menu_screen_panel,
            "Are you sure you want to log out?",
            "Logout Confirmation",
            JOptionPane.YES_NO_OPTION);
            if(Choice==JOptionPane.YES_OPTION)
            {
                JOptionPane.showMessageDialog(Menu_screen_panel,
                "You have successfully logged out. Please take your card.",
                "Confirm Logout",
                JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        });

        R1.addActionListener(e ->
        {
            if(Login_Flag==true)
            {
                limit=0;    
                Side_button_Operator('D');
                cardLayout.show(CurrentPanel, "Deposit");
                Current_Card="Deposit";
            }
        });

        R2.addActionListener(e ->
        {
            if(Login_Flag==true)
            {
                Side_button_Operator('D');
                Avail_Balance.setText(""+Balance);
                cardLayout.show(CurrentPanel, "Balance");
            }
        });
        
        R3.addActionListener(e ->
        {
            if(Login_Flag && Done)
            {
                Receipt_Printer();
                JOptionPane.showMessageDialog(Menu_screen_panel,
                "Your transaction receipt has been printed successfully.",
                "Thank You!",
                JOptionPane.INFORMATION_MESSAGE);
               // ReceiptCard.show(ReceiptPanel,"Empty receipt");
            }
        });
    }
    //Build screen panel section end

    //Build keypad panel function start
    void Build_Keypad_panel()
    {
        //Back panel fo keypad panel
        Back_Keypad_Panel=new JPanel();
        Back_Keypad_Panel.setBackground(Color.gray);
        Back_Keypad_Panel.setBounds(10, 410, 625, 270);
        Back_Keypad_Panel.setLayout(null); 

        //Keypad panel
        Keypad_Panel=new JPanel();
        Keypad_Panel.setBackground(Color.lightGray);
        Keypad_Panel.setBounds(85, 10, 450, 250);
        Keypad_Panel.setLayout(null); 

        //Add keypanel into back panel
        Back_Keypad_Panel.add(Keypad_Panel);

        //Keypad keys section start
            Enter=new JButton("Enter");
            Enter.setBounds(330, 10, 100, 50);
            Enter.setFont(new Font("Times New Roman", Font.BOLD, 20));
            Keypad_Panel.add(Enter);
            Enter.setBackground(Color.green);
            Enter.setActionCommand("Enter");
            Enter.addActionListener(e ->
            {   
                switch(Current_Card)
                {
                    case "Pin":
                    char[] Pin_Digit=pin.getPassword();
                    code=String.valueOf(Pin_Digit);
                    if(code.equals("1379"))
                    {
                        Login_Flag=true;
                        Side_button_Operator('E');
                        cardLayout.show(CurrentPanel, "Menu");
                        return;
                    }
                    else 
                    {
                        Attempts--;
                        if(Attempts!=0)
                        {
                            pin.setText(null);
                            JOptionPane.showMessageDialog(Pin_Screen_Panel,
                            "Incorrect PIN ! You have "+Attempts+" Attempts left!!",
                            "Access Denied",
                            JOptionPane.WARNING_MESSAGE);
                        }
                        else 
                        {
                            Attempts=3;
                            JOptionPane.showMessageDialog(Pin_Screen_Panel,
                            "Account locked for 24 hrs",
                            "Account locked",
                            JOptionPane.WARNING_MESSAGE);
                            System.exit(0);
                        }
                    }
                break;

                case "Withdrawal":
                    String Input_Amt=Withdrawal_Field.getText();
                    int Amt=Integer.parseInt(Input_Amt);
                    if((Input_Amt.length())==0)
                    {
                        JOptionPane.showMessageDialog(Withdrawal_Panel,
                        "Please enter an amount to proceed with the withdrawal",
                        "No Amount Entered",
                        JOptionPane.OK_OPTION);
                    }

                    if(Amt%100==0)
                    {
                        if(Amt<=(Balance-500))
                        {
                            Balance-=Amt;
                            Avail_Balance.setText(""+Balance);
                            Show_Card_Cash_Field("With_Cash");
                            JOptionPane.showMessageDialog(Withdrawal_Panel,
                            "Withdrawal Successful!\nPlease collect your cash.",
                            "Withdrawal Successful",
                            JOptionPane.INFORMATION_MESSAGE);
                            Show_Card_Cash_Field("Without_Cash");
                            Transc_Type="Withdrawal";
                            Amount=Amt;
                            Receipt_Printer();


                            
                            Side_button_Operator('E');
                            cardLayout.show(CurrentPanel,"Menu");
                            Withdrawal_Field.setText(null);
                            valid_digit=0;
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(Withdrawal_Panel,
                            "Invalid withdrawal amount.\nEnsure you have enough funds with ₹500 minimum balance \nEnter amount between ₹100 and ₹50,000 in multiples of ₹100",
                            "Withdrawal Error",JOptionPane.WARNING_MESSAGE);
                            Withdrawal_Field.setText(null);
                            valid_digit=0;
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(Withdrawal_Panel,
                        "Please enter the amount in multiples of ₹100.",
                        "Invalid Amount",JOptionPane.WARNING_MESSAGE);
                        Withdrawal_Field.setText(null);
                        valid_digit=0;
                    }
                break;

                case "Deposit":
                if(!IsCashInsert)
                {
                    String Get_Amt=Deposit_Field.getText();
                    Deposit_Amt=Integer.parseInt(Get_Amt);
                    if(Deposit_Amt==0)
                    {
                        JOptionPane.showMessageDialog(Withdrawal_Panel,
                        "Please enter an amount to proceed with the withdrawal",
                        "No Amount Entered",
                        JOptionPane.OK_OPTION);
                    }
                    if(Deposit_Amt%100==0 && Deposit_Amt<=50000)
                    {
                        JOptionPane.showMessageDialog(Deposit_Panel,
                        "Please insert ₹"+Deposit_Amt+" cash into the slot and press Enter",
                        "Insert Cash",
                        JOptionPane.INFORMATION_MESSAGE);
                        Show_Card_Cash_Field("With_Cash");
                        IsCashInsert=true;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(Deposit_Panel,
                        "Please enter the amount in multiples of ₹100.",
                        "Invalid Amount",JOptionPane.WARNING_MESSAGE);
                        Deposit_Field.setText(null);
                        limit=0;
                    }
                }
                if(IsCashInsert)
                {
                    Balance+=Deposit_Amt;
                    JOptionPane.showMessageDialog(Deposit_Panel,
                    "₹"+Deposit_Amt+" deposited successfully to A/C XXXXXX7890.",
                    "Deposit Successful",
                    JOptionPane.INFORMATION_MESSAGE);
                    Transc_Type="Deposit";
                    Amount=Deposit_Amt;
                    Receipt_Printer();
                    Deposit_Field.setText(null);
                    Show_Card_Cash_Field("Without_Cash");
                    Side_button_Operator('E');
                    cardLayout.show(CurrentPanel, "Menu");
                }
                break;
                }
            });

            Delete=new JButton("Delete");
            Delete.setBounds(330, 70, 100, 50);
            Delete.setFont(new Font("Times New Roman", Font.BOLD, 20));
            Keypad_Panel.add(Delete);
            Delete.setBackground(Color.yellow);
            Delete.setActionCommand("Delete");
            Delete.addActionListener(e ->
            {
                switch(Current_Card)
                {
                    case "Pin":
                    Delete_Opration(pin);
                    break;

                    case "Change Pin":
                        if(new_pass && Digit<=4)
                        {
                            Delete_Opration(New_Pin);
                            Digit--;
                        }
                        else if (Confirm_pass && Digit!=8) 
                        {
                            Delete_Opration(Confirm_Pin);
                            Digit--;
                        }    
                    break;

                    case "Withdrawal":
                        String Input=Withdrawal_Field.getText();
                        int lastIndex=Input.length();
                        if(lastIndex>0)
                        {
                            String Deleted=Input.substring(0, lastIndex-1);
                            Withdrawal_Field.setText(Deleted);
                        }
                    break;

                    case "Deposit":
                        String Inpt=Deposit_Field.getText();
                        int lastIdx=Inpt.length();
                        if(lastIdx>0)
                        {
                            String Deleted=Inpt.substring(0, lastIdx-1);
                            Deposit_Field.setText(Deleted);
                            limit--;
                        }
                    break;
                }
            });

            Clear=new JButton("Clear");
            Clear.setBounds(330,130, 100, 50);
            Clear.setFont(new Font("Times New Roman", Font.BOLD, 20));
            Keypad_Panel.add(Clear);
            Clear.setBackground(Color.red);
            Clear.setActionCommand("Clear");
            Clear.addActionListener(e ->
            {
                switch (Current_Card) 
                {
                    case "Pin":
                        pin.setText(null);
                    break;
                
                    case "Change Pin":
                        new_pass=true;
                        Confirm_pass=false;
                        New_Pin.setText(null);
                        Confirm_Pin.setText(null);
                        Digit=0;
                    break;

                    case "Withdrawal":
                        Withdrawal_Field.setText(null);
                        valid_digit=0;
                        break;

                    case "Deposit":
                        Deposit_Field.setText(null);
                        limit=0;
                    break;
                }
            });

            Back=new JButton("Back");
            Back.setBounds(330, 190, 100, 50);
            Back.setFont(new Font("Times New Roman", Font.BOLD, 20));
            Keypad_Panel.add(Back);
            Back.setBackground(Color.cyan);
            Back.setActionCommand("Back");
            Back.addActionListener(e->
            {
                Login_Flag=true;
                Change_Pin_Flag=true;
                Side_button_Operator('E');
                cardLayout.show(CurrentPanel, "Menu");
            });
           
            K1=new JButton("1");
            K1.setActionCommand("K1");
            K2=new JButton("2");
            K2.setActionCommand("K2");
            K3=new JButton("3");
            K3.setActionCommand("K3");
            K4=new JButton("4");
            K4.setActionCommand("K4");
            K5=new JButton("5");
            K5.setActionCommand("K5");
            K6=new JButton("6");
            K6.setActionCommand("K6");
            K7=new JButton("7");
            K7.setActionCommand("K7");
            K8=new JButton("8");
            K8.setActionCommand("K8");
            K9=new JButton("9");
            K9.setActionCommand("K9");
            K0=new JButton("0");
            K0.setActionCommand("K0");
            K00=new JButton("00");
            K00.setActionCommand("K00");
            K=new JButton(".");
            K.setActionCommand("K");

            keyButtons= new JButton[]{K1,K2,K3,K4,K5,K6,K7,K8,K9,K0,K00,K};
            for(JButton clr:keyButtons)
            {
                clr.setFont(new Font("Times New Roman", Font.BOLD, 20));
            }
        //Keypad keys section end 

        //Key positioning and sizing section start
            int [][] ButtonPositions=
            {
                {20, 10, 80, 50},{110, 10, 80, 50},{200, 10, 80, 50},
                {20, 70, 80, 50},{110, 70, 80, 50},{200, 70, 80, 50},
                {20, 130, 80, 50},{110, 130, 80, 50},{200, 130, 80, 50},
                {20, 190, 80, 50},{110, 190, 80, 50},{200, 190, 80, 50},
            };

            for(int i=0;i<keyButtons.length;i++)
            {
                keyButtons[i].setBounds(ButtonPositions[i][0],ButtonPositions[i][1],ButtonPositions[i][2],ButtonPositions[i][3]);
                Keypad_Panel.add(keyButtons[i]);
                keyButtons[i].addActionListener(Keys);
            }
        //Key positioning and sizing section end
    }
    //Build keypad panel function end

    //Keypad listener for pin panel start
   ActionListener Keys=e->
    {
        switch(Current_Card)
        {
            case "Pin":
                JButton Obj=(JButton)e.getSource();
                char[] Pre_Num=pin.getPassword();
                String value=Obj.getText();
                String Add_Num=new String(Pre_Num)+value;
                pin.setText(Add_Num);
            break;
            
            case "Change Pin":
                if(Digit==4)
                {
                    new_pass=false;
                    Confirm_pass=true;
                }
            
                if(new_pass && Digit!=4)
                {
                    Digit++;
                    JButton Obj1=(JButton)e.getSource();
                    char[] Pin1=New_Pin.getPassword();
                    String val1=Obj1.getText();
                    String add_Num=new String(Pin1)+val1;
                    New_Pin.setText(add_Num);
                }
                else if (Confirm_pass && Digit!=8) 
                {
                    Digit++;
                    JButton Obj2=(JButton)e.getSource();
                    char[] pin2=Confirm_Pin.getPassword();
                    String val2=Obj2.getText();
                    String Add_Conf=new String(pin2)+val2;
                    Confirm_Pin.setText(Add_Conf);
                    if (Digit==8)
                    {
                        char[] pass1=New_Pin.getPassword();
                        String Pass1=new String(pass1);
                        char[] pass2=Confirm_Pin.getPassword();
                        String Pass2=new String(pass2);
                        if(Pass1.equals(Pass2))
                        {
                            JOptionPane.showMessageDialog(Change_Pin_Panel,
                            "Your ATM PIN has been updated. \nDo not share your PIN with anyone !!",
                            "PIN Changed Successfully",
                            JOptionPane.INFORMATION_MESSAGE);
                            cardLayout.show(CurrentPanel, "Menu");
                            Side_button_Operator('E');
                            new_pass=true;
                            Confirm_pass=false;
                            New_Pin.setText(null);
                            Confirm_Pin.setText(null);
                            Digit=0;
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(Change_Pin_Panel,
                            "The Confirm PIN does not match the New PIN.\nPlease re-enter both fields carefully.",
                            "PIN Mismatch",
                            JOptionPane.WARNING_MESSAGE);
                            New_Pin.setText(null);
                            Confirm_Pin.setText(null);
                            new_pass=true;
                            Confirm_pass=false;
                            Digit=0;
                        }
                    }
                }
            break;

            case "Withdrawal":
                valid_digit++;
                if(valid_digit<=5)
                {
                    JButton obj=(JButton)e.getSource();
                    String pre_Num=Withdrawal_Field.getText();
                    String val=obj.getText();
                    String Add=pre_Num+val;
                    Withdrawal_Field.setText(Add);  
                    Withdrawal_Flag=true;
                }
                else
                {
                    JOptionPane.showMessageDialog(Withdrawal_Panel,
                    "Maximum 5 digits allowed. Amount cannot exceed ₹50,000",
                    "Input Limit Exceeded",
                    JOptionPane.OK_OPTION);
                    Withdrawal_Field.setText(null);
                    valid_digit=0;
                }
            break;

             case "Deposit":
                limit++;
                if(limit<=5)
                {
                    JButton Dpst_Obj=(JButton)e.getSource();
                    String Present_Num=Deposit_Field.getText();
                    String Number=Dpst_Obj.getText();
                    String insert=Present_Num+Number;
                    Deposit_Field.setText(insert);  
                    Deposit_Flag=true;
                }
                else
                {
                    JOptionPane.showMessageDialog(Deposit_Panel,
                    "Maximum 5 digits allowed. Amount cannot exceed ₹50,000",
                    "Input Limit Exceeded",
                    JOptionPane.OK_OPTION);
                    limit=0;
                    Deposit_Field.setText(null);
                }
            break;
        }
    };
    //Keypad listener for pin panel end

    //Build receipt panel function start
    void Build_Receipt_Panel()
    {
    }
    //Build receipt panel function end

    //Build cashcard panel function start
    void Build_CashCard_panel()
    {
        //Back panel of cash and card image 
        Back_CashCard_panel=new JPanel();
        Back_CashCard_panel.setBackground(Color.gray);
        Back_CashCard_panel.setBounds(645, 410, 225, 270);
        Back_CashCard_panel.setLayout(null);

        //Card image
        ImageIcon Without_Card=new ImageIcon("Icons/Without_Card.png");
        Set_Card_Field=new JLabel(Without_Card);
        Set_Card_Field.setBounds(20, 15, Without_Card.getIconWidth(), Without_Card.getIconHeight());
        Set_Card_Field.setLayout(null);
        Back_CashCard_panel.add(Set_Card_Field);

        //Cash image
        ImageIcon Without_Cash=new ImageIcon("Icons/Without_Cash.png");
        Set_Cash_Field=new JLabel(Without_Cash);
        Set_Cash_Field.setBounds(20, 180, Without_Cash.getIconWidth(), Without_Cash.getIconHeight());
        Set_Cash_Field.setLayout(null);
        Back_CashCard_panel.add(Set_Cash_Field);
    }
    //Build cashcard panel function end

    //Insert card popup function start  
    void Insert_Card()
    {
        ImageIcon ATM_Icon = new ImageIcon("Icons/ATM_Logo.png"); 
        int Response = JOptionPane.showOptionDialog(jr,
        "Please insert your card & use default password '1379' to proceed.", 
        "Insert Your Card",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.INFORMATION_MESSAGE, 
        ATM_Icon, 
        new String[]{"Proceed", "Cancel"}, "Proceed");
        if (Response == JOptionPane.YES_OPTION) 
        {
            Show_Card_Cash_Field("With_Card");
            IsCardInserted=true;
            Pin_Card=true;
            Side_button_Operator('D');
            cardLayout.show(CurrentPanel, "Pin");
            Current_Card="Pin";
        } 
        else 
        {              
            System.exit(0); 
        }
    }
    //Insert card popup function end

    //Side button Enbale/Disable function start
    void Side_button_Operator(char set)
    {
        if(set=='E')
        {
            for (JButton Obj : Side_Buttons) 
            {
                Obj.setEnabled(true);
            }
        }
        else
        {
           for (JButton Obj : Side_Buttons) 
            {
                Obj.setEnabled(false);
            }
        }
    }
    //Side button Enbale/Disable function end

    //Delete operation function start
    void Delete_Opration(JPasswordField Field)
    {
        char[] currentDigits = Field.getPassword();            
        int lastIndex = currentDigits.length;
        if (lastIndex > 0) 
        {
            char[] delPass = new char[lastIndex - 1];         
            System.arraycopy(currentDigits, 0, delPass, 0, lastIndex - 1);
            Field.setText(new String(delPass));               
        }  
    }
    //Delete operation function end

    //Show/Hide card ans cash function start
    void Show_Card_Cash_Field(String Do)
    {
        if(Do.equals("With_Card"))
        {
            ImageIcon With_Card=new ImageIcon("Icons/With_Card.png");
            Set_Card_Field.setIcon(With_Card);
            Set_Card_Field.setBounds(20, 15, With_Card.getIconWidth(), With_Card.getIconHeight());
        }
        if(Do.equals("With_Cash"))
        {
            ImageIcon With_Cash=new ImageIcon("Icons/With_Cash.png");
            Set_Cash_Field.setIcon(With_Cash);
            Set_Cash_Field.setBounds(20, 180, With_Cash.getIconWidth(), With_Cash.getIconHeight());
        }
        if(Do.equals("Without_Card"))
        {
            ImageIcon Without_Card=new ImageIcon("Icons/Without_Card.png");
            Set_Card_Field.setIcon(Without_Card);
            Set_Card_Field.setBounds(20, 15, Without_Card.getIconWidth(), Without_Card.getIconHeight());
        }
        if(Do.equals("Without_Cash"))
        {
            ImageIcon Without_Cash=new ImageIcon("Icons/Without_Cash.png");
            Set_Cash_Field.setIcon(Without_Cash);
            Set_Cash_Field.setBounds(20, 180, Without_Cash.getIconWidth(), Without_Cash.getIconHeight());
        }
    }
    //Show/Hide card ans cash function end

    //Receipt printer function start
    void Receipt_Printer()
    {
        LocalDateTime now=LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String Currentdate=now.format(dateTimeFormatter);
        DateTimeFormatter TimeFormatter=DateTimeFormatter.ofPattern("hh:mm:ss");
        String CurrentTime=now.format(TimeFormatter);

        Printed_Receipt_Panel=new JPanel();
        Printed_Receipt_Panel.setBackground(Color.white);
        Printed_Receipt_Panel.setBounds(12, 60, 200, 300);
        Printed_Receipt_Panel.setLayout(null);
        Receipt_Format="---------------------------------------------\n"
                      +"*** TRANSACTION RECEIPT ***\n"
                      +"---------------------------------------------\n"
                      +"Date              : "+Currentdate+"\n"
                      +"Time              : "+CurrentTime+"\n"
                      +"ATM id           : ATM8689\n"
                      +"Bank Name : Infinity Bank\n"
                      +"---------------------------------------------\n"
                      +"Transaction type    : "+Transc_Type+"\n"
                      +"A/C no                      :  XXXXXX7890\n"
                      +"Amount                    : "+Amount+"\n"
                      +"Available Balance  :  "+Balance+"\n"
                      +"---------------------------------------------\n"
                      +"  Thanks for using infinity ATM        \n"
                      +"Please collect your cash and card.     \n"
                      +"---------------------------------------------\n";
                    
        JTextArea Print=new JTextArea(Receipt_Format);
        Print.setBounds(12,20,300,400);
        Printed_Receipt_Panel.add(Print);
        Back_Receipt_Panel.add(Printed_Receipt_Panel);
        ReceiptPanel.add(Printed_Receipt_Panel,"Printed receipt");
        ReceiptCard.show(ReceiptPanel,"Printed receipt");
        Done=true;
    }   
    //Receipt printer function end
}