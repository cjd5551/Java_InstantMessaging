
import java.awt.Dimension;
import java.awt.event.ActionListener;
import static java.lang.System.exit;


public class IM_Components_GUI extends javax.swing.JFrame {
    
    private String name, ip;
    IM_Messaging_GUI gui2;
    
    public IM_Components_GUI() {
        initComponents();
    }

    public void set_Room(String msg) {
        String message = msg;
        gui2.set_ChatRoom(msg + '\n');
    }
    
    public String getName()
        { return name; }
    
    public String getIP()
        { return ip; }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Title_Panel = new javax.swing.JPanel();
        Title_Label = new javax.swing.JLabel();
        Button_Panel = new javax.swing.JPanel();
        Okay_Button = new javax.swing.JButton();
        Clear_Button = new javax.swing.JButton();
        Cancel_Button = new javax.swing.JButton();
        Input_Panel = new javax.swing.JPanel();
        Name_Prompt = new javax.swing.JLabel();
        Name_Field = new javax.swing.JTextField();
        IP_Prompt = new javax.swing.JLabel();
        IP_Field = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Instant Messaging Client");
        setResizable(false);

        Title_Label.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        Title_Label.setText("Instant Messaging System");
        Title_Panel.add(Title_Label);

        getContentPane().add(Title_Panel, java.awt.BorderLayout.PAGE_START);

        Okay_Button.setText("Okay");
        Okay_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Okay_ButtonActionPerformed(evt);
            }
        });
        Button_Panel.add(Okay_Button);

        Clear_Button.setText("Clear");
        Clear_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Clear_ButtonActionPerformed(evt);
            }
        });
        Button_Panel.add(Clear_Button);

        Cancel_Button.setText("Cancel");
        Cancel_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cancel_ButtonActionPerformed(evt);
            }
        });
        Button_Panel.add(Cancel_Button);

        getContentPane().add(Button_Panel, java.awt.BorderLayout.PAGE_END);

        Name_Prompt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Name_Prompt.setText("Your Name :");

        Name_Field.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Name_Field.setPreferredSize(new java.awt.Dimension(200, 23));

        IP_Prompt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        IP_Prompt.setText("Server IP :");

        IP_Field.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        IP_Field.setPreferredSize(new java.awt.Dimension(200, 23));

        javax.swing.GroupLayout Input_PanelLayout = new javax.swing.GroupLayout(Input_Panel);
        Input_Panel.setLayout(Input_PanelLayout);
        Input_PanelLayout.setHorizontalGroup(
            Input_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Input_PanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(Input_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Input_PanelLayout.createSequentialGroup()
                        .addComponent(IP_Prompt)
                        .addGap(5, 5, 5)
                        .addComponent(IP_Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Input_PanelLayout.createSequentialGroup()
                        .addComponent(Name_Prompt)
                        .addGap(5, 5, 5)
                        .addComponent(Name_Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        Input_PanelLayout.setVerticalGroup(
            Input_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Input_PanelLayout.createSequentialGroup()
                .addGroup(Input_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Input_PanelLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(Name_Prompt))
                    .addGroup(Input_PanelLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(Name_Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(Input_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Input_PanelLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(IP_Prompt))
                    .addComponent(IP_Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(Input_Panel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Okay_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Okay_ButtonActionPerformed
        // TODO add your handling code here:
        name   = Name_Field.getText();
        ip     = IP_Field.getText();
        
        this.setVisible(false);
        
        Client client = new Client(ip, name, 5000);
        
        gui2 = new IM_Messaging_GUI(client);
        gui2.setPreferredSize(new Dimension(400,302));
        gui2.setVisible(true);
            
        if (!client.start())
            return;
    }//GEN-LAST:event_Okay_ButtonActionPerformed

    private void Clear_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Clear_ButtonActionPerformed
        // TODO add your handling code here:
        name = null;
        ip   = null;
        Name_Field.setText(null);
        IP_Field.setText(null);
    }//GEN-LAST:event_Clear_ButtonActionPerformed

    private void Cancel_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cancel_ButtonActionPerformed
        // TODO add your handling code here:
        name = null;
        ip   = null;
        Name_Field.setText(null);
        IP_Field.setText(null);
        this.setVisible(false);
        exit(0);
    }//GEN-LAST:event_Cancel_ButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Button_Panel;
    private javax.swing.JButton Cancel_Button;
    private javax.swing.JButton Clear_Button;
    private javax.swing.JTextField IP_Field;
    private javax.swing.JLabel IP_Prompt;
    private javax.swing.JPanel Input_Panel;
    private javax.swing.JTextField Name_Field;
    private javax.swing.JLabel Name_Prompt;
    private javax.swing.JButton Okay_Button;
    private javax.swing.JLabel Title_Label;
    private javax.swing.JPanel Title_Panel;
    // End of variables declaration//GEN-END:variables
}
