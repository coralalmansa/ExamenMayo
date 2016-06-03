package examenMayo.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author coral almansa
 * @version 1.0
 *
 */
public class AcercaDe extends JDialog {

	private final JPanel contentPanel = new JPanel();

	
	/**
	 * Create the dialog.
	 */
	public AcercaDe() {
		setModal(true);
		setResizable(false);
		setTitle("Acerca de Examen Mayo 2016");
		setBounds(100, 100, 317, 221);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JTextPane txtpnIesGranCapitn = new JTextPane();
		txtpnIesGranCapitn.setEditable(false);
		txtpnIesGranCapitn.setText("IES GRAN CAPIT\u00C1N.\r\nversi\u00F3n 1.0\r\n\r\nM\u00F3dulo: Programaci\u00F3n\r\nMayo de 2016\r\n\r\nCoral Almansa Dom\u00EDnguez");
		txtpnIesGranCapitn.setBounds(76, 19, 159, 119);
		contentPanel.add(txtpnIesGranCapitn);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
