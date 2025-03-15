package morse.code.translator;

import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;
import java.awt.Toolkit;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

public class Desgin extends javax.swing.JFrame {

    private final Map<String, String> morseMap = new HashMap<>();

    public Desgin() {
        initComponents();
        setTitle("Morse translator made by NguyenMinh1301");

        //Upper case
        morseMap.put("A", ".-");
        morseMap.put("B", "-...");
        morseMap.put("C", "-.-.");
        morseMap.put("D", "-..");
        morseMap.put("E", ".");
        morseMap.put("F", "..-.");
        morseMap.put("G", "--.");
        morseMap.put("H", "....");
        morseMap.put("I", "..");
        morseMap.put("J", ".---");
        morseMap.put("K", "-.-");
        morseMap.put("L", ".-..");
        morseMap.put("M", "--");
        morseMap.put("N", "-.");
        morseMap.put("O", "---");
        morseMap.put("P", ".--.");
        morseMap.put("Q", "--.-");
        morseMap.put("R", ".-.");
        morseMap.put("S", "...");
        morseMap.put("T", "-");
        morseMap.put("U", "..-");
        morseMap.put("V", "...-");
        morseMap.put("W", ".--");
        morseMap.put("X", "-..-");
        morseMap.put("Y", "-.--");
        morseMap.put("Z", "--..");

        //Lower case
        morseMap.put("a", ".-");
        morseMap.put("b", "-...");
        morseMap.put("c", "-.-.");
        morseMap.put("d", "-..");
        morseMap.put("e", ".");
        morseMap.put("f", "..-.");
        morseMap.put("g", "--.");
        morseMap.put("h", "....");
        morseMap.put("i", "..");
        morseMap.put("j", ".---");
        morseMap.put("k", "-.-");
        morseMap.put("l", ".-..");
        morseMap.put("m", "--");
        morseMap.put("n", "-.");
        morseMap.put("o", "---");
        morseMap.put("p", ".--.");
        morseMap.put("q", "--.-");
        morseMap.put("r", ".-.");
        morseMap.put("s", "...");
        morseMap.put("t", "-");
        morseMap.put("u", "..-");
        morseMap.put("v", "...-");
        morseMap.put("w", ".--");
        morseMap.put("x", "-..-");
        morseMap.put("y", "-.--");
        morseMap.put("z", "--..");

        //Numbers
        morseMap.put("0", "-----");
        morseMap.put("1", ".----");
        morseMap.put("2", "..---");
        morseMap.put("3", "...--");
        morseMap.put("4", "....-");
        morseMap.put("5", ".....");
        morseMap.put("6", "-....");
        morseMap.put("7", "--...");
        morseMap.put("8", "---..");
        morseMap.put("9", "----.");

        //Special characters
        morseMap.put(" ", "/");
        morseMap.put(".", ".-.-.-");
        morseMap.put(",", "--..--");
        morseMap.put(":", "---...");
        morseMap.put(";", "-.-.-.");
        morseMap.put("?", "..--..");
        morseMap.put("!", "-.-.--");
        morseMap.put("-", "-...-");
        morseMap.put("_", "..--.-");
        morseMap.put("/", "-..-.");
        morseMap.put("(", "-.--.");
        morseMap.put(")", "-.--.-");
        morseMap.put(" ", ".-..-.");
        morseMap.put("=", "-...-");
        morseMap.put("+", ".-.-.");
        morseMap.put("*", "-..-");
        morseMap.put("@", ".--.-.");
        morseMap.put("&", ".-...");

        //Create reverse mapping from Morse -> English
        List<Object> values = Arrays.asList(morseMap.values().toArray());
        List<Object> keys = Arrays.asList(morseMap.keySet().toArray());
        for (int i = 0; i < values.size(); i++) {
            morseMap.put(values.get(i).toString(), keys.get(i).toString());
        }
    }

    //Check if user has entered Input or not
    public boolean checkInput() {
        try {
            if (txtInput.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Input cannot be empty", "Error", 0);
                //Return false if no content
                return false;
            }
            //Returns true if content exists
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex, "Error", 0);
            return false;
        }
    }

    //Check if string is morse code or not
    public boolean isMorse(String text) {
        //Morse contains only '.', '-', '/', ' '
        return text.trim().matches("[.\\-/\\s]+");
    }

    //Convert a string from English to Morse code
    public String convertToMorse(String text) {
        StringBuilder buffer = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (c == ' ') {
                //Add the "/" symbol to separate words
                buffer.append(" / ");
            } else {
                String morse = morseMap.getOrDefault(String.valueOf(c).toUpperCase(), "??");
                buffer.append(morse).append(" ");
            }
        }

        return buffer.toString().trim();
    }

    //Convert a string from Morse code to English
    public String convertToEnglish(String morseText) {
        StringBuilder buffer = new StringBuilder();
        //Split by "/" to get each word
        String[] words = morseText.split(" / ");

        for (String word : words) {
            String[] letters = word.split(" ");
            for (String letter : letters) {
                buffer.append(morseMap.getOrDefault(letter, "?"));
            }
            //Add spaces between words
            buffer.append(" ");
        }

        return buffer.toString().trim();
    }

    //Handling when clicking translate button
    public void translate() {
        if (checkInput()) {
            String input = txtInput.getText().trim();

            if (isMorse(input)) {
                txtOutput.setText(convertToEnglish(input)); //Morse Code Translation -> English
            } else {
                txtOutput.setText(convertToMorse(input)); //Translate English -> Morse
            }
        }
    }

    //Copy the current content to the clipboard
    public void copyInput() {
        if (checkInput()) {
            String text = txtInput.getText();

            StringSelection selection = new StringSelection(text);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, null);

            JOptionPane.showMessageDialog(this, "Copied to clipboard", "Success", 1);
        }
    }

    //Copy the current content to the clipboard
    public void copyOutput() {
        if (checkInput()) {
            String text = txtOutput.getText();
            
            StringSelection selection = new StringSelection(text);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, null);
            
            JOptionPane.showMessageDialog(this, "Copied to clipboard", "Success", 1);
        }
    }

    //Clear input text
    public void clearInput() {
        txtInput.setText("");
    }

    //Clear Output text
    public void clearOutput() {
        txtOutput.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        txtInput = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtOutput = new javax.swing.JTextArea();
        lblInput = new javax.swing.JLabel();
        lblOutput = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        btnClearInput = new javax.swing.JButton();
        btnCopyInput = new javax.swing.JButton();
        btnClearOutput = new javax.swing.JButton();
        btnCopyOutput = new javax.swing.JButton();
        btnTranslate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtInput.setColumns(20);
        txtInput.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtInput.setRows(5);
        txtInput.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane2.setViewportView(txtInput);

        txtOutput.setEditable(false);
        txtOutput.setColumns(20);
        txtOutput.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtOutput.setRows(5);
        txtOutput.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane4.setViewportView(txtOutput);

        lblInput.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblInput.setText("INPUT");

        lblOutput.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblOutput.setText("OUTPUT");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("- Enter English on the left click \"to Morse\" button to translate to Morse code\n- Enter the Morse code on the left and click the \"to English\" button to translate to English\n- The clear button is used to delete all existing text on the frame.\n\nThis project made by NguyenMinh1301                                                                Date: 16/03/2025");
        jScrollPane1.setViewportView(jTextArea1);

        btnClearInput.setText("CLEAR");
        btnClearInput.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClearInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearInputActionPerformed(evt);
            }
        });

        btnCopyInput.setText("COPY");
        btnCopyInput.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCopyInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopyInputActionPerformed(evt);
            }
        });

        btnClearOutput.setText("CLEAR");
        btnClearOutput.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClearOutput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearOutputActionPerformed(evt);
            }
        });

        btnCopyOutput.setText("COPY");
        btnCopyOutput.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCopyOutput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopyOutputActionPerformed(evt);
            }
        });

        btnTranslate.setText("TRANSLATE");
        btnTranslate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTranslate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTranslateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblInput)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCopyInput)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnClearInput))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(lblOutput)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnTranslate)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCopyOutput)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClearOutput)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInput)
                    .addComponent(btnClearInput)
                    .addComponent(btnCopyInput))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOutput)
                    .addComponent(btnTranslate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClearOutput)
                    .addComponent(btnCopyOutput))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblInput, lblOutput});

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTranslateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTranslateActionPerformed
        translate();
    }//GEN-LAST:event_btnTranslateActionPerformed

    private void btnCopyInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopyInputActionPerformed
        copyInput();
    }//GEN-LAST:event_btnCopyInputActionPerformed

    private void btnCopyOutputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopyOutputActionPerformed
        copyOutput();
    }//GEN-LAST:event_btnCopyOutputActionPerformed

    private void btnClearInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearInputActionPerformed
        clearInput();
    }//GEN-LAST:event_btnClearInputActionPerformed

    private void btnClearOutputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearOutputActionPerformed
        clearOutput();
    }//GEN-LAST:event_btnClearOutputActionPerformed

    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Desgin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Desgin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Desgin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Desgin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Desgin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClearInput;
    private javax.swing.JButton btnClearOutput;
    private javax.swing.JButton btnCopyInput;
    private javax.swing.JButton btnCopyOutput;
    private javax.swing.JButton btnTranslate;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblInput;
    private javax.swing.JLabel lblOutput;
    private javax.swing.JTextArea txtInput;
    private javax.swing.JTextArea txtOutput;
    // End of variables declaration//GEN-END:variables
}
