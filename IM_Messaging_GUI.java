
import java.awt.event.KeyEvent;
import static java.lang.System.exit;

public class IM_Messaging_GUI extends javax.swing.JFrame {

    String message;
    Client client;
    
    public IM_Messaging_GUI(Client client) {
        initComponents();
        this.client = client;
    }
    
    public void set_ChatRoom(String msg) {
        ChatRoom_Area.append(msg + '\n');
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ChatRoom_ScrollPane = new javax.swing.JScrollPane();
        ChatRoom_Area = new javax.swing.JTextArea();
        Message_ScrollPane = new javax.swing.JScrollPane();
        Message_Area = new javax.swing.JTextArea();
        SendPrompt_Label = new javax.swing.JLabel();
        EscPrompt_Label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Instant Messaging Client");

        ChatRoom_Area.setEditable(false);
        ChatRoom_Area.setColumns(20);
        ChatRoom_Area.setRows(5);
        ChatRoom_ScrollPane.setViewportView(ChatRoom_Area);

        Message_ScrollPane.setPreferredSize(new java.awt.Dimension(166, 45));

        Message_Area.setColumns(20);
        Message_Area.setRows(5);
        Message_Area.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Message_AreaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Message_AreaKeyTyped(evt);
            }
        });
        Message_ScrollPane.setViewportView(Message_Area);

        SendPrompt_Label.setText("Press [ Enter ] to send message");

        EscPrompt_Label.setText("Press [ esc ] to exit");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ChatRoom_ScrollPane)
            .addComponent(Message_ScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(EscPrompt_Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                .addComponent(SendPrompt_Label))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ChatRoom_ScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Message_ScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SendPrompt_Label)
                    .addComponent(EscPrompt_Label)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Message_AreaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Message_AreaKeyTyped

    }//GEN-LAST:event_Message_AreaKeyTyped

    private void Message_AreaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Message_AreaKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            {
                message = Message_Area.getText();
                client.sendMessage(new ChatMessage(ChatMessage.message, this.message));
                Message_Area.setText("");
            }
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE)
            {
                client.sendMessage(new ChatMessage(ChatMessage.logout, null));
                client.disconnect();
                this.setVisible(false);
                exit(0);
            }
    }//GEN-LAST:event_Message_AreaKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea ChatRoom_Area;
    private javax.swing.JScrollPane ChatRoom_ScrollPane;
    private javax.swing.JLabel EscPrompt_Label;
    private javax.swing.JTextArea Message_Area;
    private javax.swing.JScrollPane Message_ScrollPane;
    private javax.swing.JLabel SendPrompt_Label;
    // End of variables declaration//GEN-END:variables
}
