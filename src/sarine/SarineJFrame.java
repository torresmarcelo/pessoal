package sarine;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import components.FileChooserDemo;

public class SarineJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField arqibm;
	private final Action action = new SwingAction();
	final JFileChooser fc = new JFileChooser();
	private JTextField arqerwin;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (InstantiationException e) {
	        e.printStackTrace();
	    } catch (IllegalAccessException e) {
	        e.printStackTrace();
	    } catch (UnsupportedLookAndFeelException e) {
	        e.printStackTrace();
	    }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SarineJFrame frame = new SarineJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SarineJFrame() {
		setTitle("Migra\u00E7\u00E3o ERWin");
		setDefaultCloseOperation(SarineJFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 491, 128);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Arquivo IBM");
		
		arqibm = new JTextField();
		arqibm.setText("C:\\Users\\Sarine\\Desktop\\ESP-COM-MLD001DDProduto.xls");
		arqibm.setColumns(10);
		
		JButton btnNewButton = new JButton("Pesquisar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				        int returnVal = fc.showOpenDialog(new FileChooserDemo());

				        if (returnVal == JFileChooser.APPROVE_OPTION) {
				            File file = fc.getSelectedFile();
				            //This is where a real application would open the file.
				            arqibm.setText(file.getPath());
				        } else {
				            
				        }
	
				
			}
		});
		
		JButton btnNewButton_1 = new JButton("Executar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				Comparador comp = new Comparador();
				
				comp.leIBM(arqibm.getText());
				String arquivoFinal = comp.compara(arqerwin.getText());
				
				JOptionPane.showMessageDialog(contentPane,"Comparação finalizada. Arquivo gerado: " + arquivoFinal, "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
				
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(contentPane,"Erro: " + e, "ERRO", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton button = new JButton("Pesquisar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        int returnVal = fc.showOpenDialog(new FileChooserDemo());

		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            //This is where a real application would open the file.
		            arqerwin.setText(file.getPath());
		        } else {
		            
		        }
			}
		});
		
		arqerwin = new JTextField();
		arqerwin.setText("C:\\Users\\Sarine\\Desktop\\Erwin 8 - Entidade x Atributo.xls");
		arqerwin.setColumns(10);
		
		JLabel lblArquivoErwin = new JLabel("Arquivo ERWin");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblArquivoErwin, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(arqibm, Alignment.TRAILING)
								.addComponent(arqerwin, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(button, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(158)
							.addComponent(btnNewButton_1)))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(arqibm, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(4)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblArquivoErwin)
						.addComponent(arqerwin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnNewButton_1))
		);
		panel.setLayout(gl_panel);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
