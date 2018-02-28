/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicios_todos;

import java.awt.Frame;
import java.io.IOException;
import java.net.URL;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.Document;

/**
 *
 * @author AnaMariana
 **/
public class ActivatedHyperlinkListener implements HyperlinkListener {

  JEditorPane htmlPane;
  Frame frame;

  public ActivatedHyperlinkListener( Frame RecebeHome, JEditorPane RecebeHtmlPane ) {
    this.frame = RecebeHome;
    this.htmlPane = RecebeHtmlPane;
  }

  public void hyperlinkUpdate(HyperlinkEvent hyperlinkEvent) {
    HyperlinkEvent.EventType type = hyperlinkEvent.getEventType();
    final URL url = hyperlinkEvent.getURL();

    if (type == HyperlinkEvent.EventType.ENTERED) {
      System.out.println("URL: " + url);
    } else if (type == HyperlinkEvent.EventType.ACTIVATED) {
      System.out.println("Activated");

      Runnable runner = new Runnable() {
        public void run() {
          // Retain reference to original
          Document doc = htmlPane.getDocument();
          try {

            htmlPane.setPage(url);
          } catch (IOException ioException) {
            JOptionPane.showMessageDialog(frame,
                "Error following link", "Link Inválido",
                JOptionPane.ERROR_MESSAGE);
            htmlPane.setDocument(doc);
          }
        }
      };
      SwingUtilities.invokeLater(runner);
    }
  }
}
