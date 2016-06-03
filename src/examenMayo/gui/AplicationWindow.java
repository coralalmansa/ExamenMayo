package examenMayo.gui;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;
import java.awt.event.ActionEvent;

import javax.swing.filechooser.FileNameExtensionFilter;

import examenMayo.funcionalidad.ContinuarAbortar;
import examenMayo.funcionalidad.ErrorAlGuardarException;
import examenMayo.funcionalidad.ErrorAlLeerException;
import examenMayo.funcionalidad.Fecha;
import examenMayo.funcionalidad.General;



import java.io.File;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;

/**
 * Para las operaciones del tiempo debes usar las clases LocalDate,
 * 
 * @author MaríaLourdes
 *
 */
public class AplicationWindow {
	private static final String SIN_NOMBRE = "Sin nombre";
	static FileNameExtensionFilter filter = new FileNameExtensionFilter("Binarios", "fec");
	static JFileChooser jFileChooser = new JFileChooser();

	static {
		jFileChooser.setFileFilter(filter);
	}
	private JFrame frmExamenMayo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AplicationWindow window = new AplicationWindow();
					window.frmExamenMayo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AplicationWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmExamenMayo = new JFrame();
		frmExamenMayo.setTitle("Examen Mayo 2016");
		actualizarTitulo(AplicationWindow.SIN_NOMBRE);

		frmExamenMayo.setBounds(100, 100, 450, 300);
		frmExamenMayo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frmExamenMayo.setJMenuBar(menuBar);

		JMenu mnHola = new JMenu("Hola");
		mnHola.setMnemonic(KeyEvent.VK_H);
		menuBar.add(mnHola);

		JMenuItem mntmError = new JMenuItem("Error");
		mntmError.setMnemonic('E');
		mntmError.setMnemonic(KeyEvent.VK_E);
		mntmError.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(mntmError.getRootPane(),
						"Error", "No puedes ser negativo",
						JOptionPane.ERROR_MESSAGE);		
			}
		});
		mnHola.add(mntmError);

		JMenuItem mntmAdvierte = new JMenuItem("Advierte");
		mntmAdvierte.setMnemonic(KeyEvent.VK_A);
		mntmAdvierte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(mntmAdvierte.getRootPane(),
						"Cuidadín", "Esto es una advertencia",	
						JOptionPane.WARNING_MESSAGE);
			
			}
		});
		mnHola.add(mntmAdvierte);

		JMenuItem mntmInforma = new JMenuItem("Informa");
		mntmInforma.setMnemonic(KeyEvent.VK_I);
		mntmInforma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(mntmInforma.getRootPane(),
						"Esto va bien", "Mensaje",	
						JOptionPane.INFORMATION_MESSAGE);		
			}
		});
		mnHola.add(mntmInforma);

		JMenu mnPtrhgunys = new JMenu("Pregunta");
		mnPtrhgunys.setMnemonic(KeyEvent.VK_P);
		menuBar.add(mnPtrhgunys);

		JMenuItem mntmDosOpciones = new JMenuItem("Dos opciones...");
		mntmDosOpciones.setMnemonic(KeyEvent.VK_D);
		mntmDosOpciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(frmExamenMayo, "¿Estás seguro?", "Indeciso", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
		
			}
		});
		mnPtrhgunys.add(mntmDosOpciones);

		JMenuItem mntmTresOpciones = new JMenuItem("Tres opciones...");
		mntmTresOpciones.setMnemonic(KeyEvent.VK_T);
		mntmTresOpciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generarMenuTresOpciones();
			}
		});
		mnPtrhgunys.add(mntmTresOpciones);

		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de... ");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AcercaDe acercaDe = new AcercaDe();
				acercaDe.setVisible(true);
			}
		});
		mntmAcercaDe.setMnemonic(KeyEvent.VK_A);
		mnPtrhgunys.add(mntmAcercaDe);

		JMenu mnHoras = new JMenu("Fechas");
		mnHoras.setMnemonic(KeyEvent.VK_F);
		menuBar.add(mnHoras);

		JMenuItem mntmNueva = new JMenuItem("Nueva");
		mntmNueva.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mntmNueva.setMnemonic(KeyEvent.VK_N);
		mnHoras.add(mntmNueva);

		JMenuItem mntmAbre = new JMenuItem("Abrir");
		mntmAbre.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mntmAbre.setMnemonic(KeyEvent.VK_A);
		mnHoras.add(mntmAbre);

		JSeparator separator_1 = new JSeparator();
		mnHoras.add(separator_1);

		JMenuItem mntmGuarda = new JMenuItem("Guardar");
		mntmGuarda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
		mntmGuarda.setMnemonic(KeyEvent.VK_G);
		mntmGuarda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		mnHoras.add(mntmGuarda);

		JMenuItem mntmGuardarComo = new JMenuItem("Guardar como...");
		mntmGuardarComo.setMnemonic(KeyEvent.VK_O);
		mntmGuardarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarComo();
			}
		});
		mnHoras.add(mntmGuardarComo);

		JSeparator separator_2 = new JSeparator();
		mnHoras.add(separator_2);

		JMenuItem mntmModificar = new JMenuItem("Modificar");
		mntmModificar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, InputEvent.CTRL_MASK));
		mntmModificar.setMnemonic(KeyEvent.VK_M);
		mntmModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		});
		mnHoras.add(mntmModificar);

		JSeparator separator = new JSeparator();
		mnHoras.add(separator);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mntmSalir.setMnemonic(KeyEvent.VK_L);
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salir();
			}
		});
		mnHoras.add(mntmSalir);
	}

	protected void salir() {
		if (guardarSiModificado() == ContinuarAbortar.ABORTAR)
			return;
		System.exit(0);
	}

	protected void modificar() {
		Modificar.enlazar(General.getLocalDate());
	}

	/**
	 * @return
	 * 
	 */
	protected ContinuarAbortar guardarComo() {
		File file;
		do {
			try {
				switch (jFileChooser.showSaveDialog(frmExamenMayo)) {
				case JFileChooser.CANCEL_OPTION:
				case JFileChooser.ERROR_OPTION:
					return ContinuarAbortar.ABORTAR;
				}
				file = jFileChooser.getSelectedFile();
				if (!file.exists() || deseaReemplazarlo(file)) {
					General.guardar(file);
					actualizarTitulo(General.getNombreFile());
					return ContinuarAbortar.CONTINUAR;
				}
			} catch (ErrorAlGuardarException e) {
				JOptionPane.showMessageDialog(frmExamenMayo, e.getMessage(), "Examen Mayo 2016",
						JOptionPane.ERROR_MESSAGE);
			}
		} while (true);
	}

	/**
	 * Indica si se desea reemplazar el fichero existente en caso de existir
	 * previamente.
	 * 
	 * @param file
	 *            Fichero que va a reemplazarse
	 * @return true si el fichero existe y se desea reemplazar. false si no se
	 *         desea reemplazar o el fichero no existe
	 */
	private boolean deseaReemplazarlo(File file) {
		if (file.exists()) {
			switch (JOptionPane.showConfirmDialog(frmExamenMayo, file.getName() + " ya existe. ¿Desea reemplazarlo?",
					"Confirmar Guardar Como", JOptionPane.YES_NO_OPTION)) {
			case JOptionPane.YES_OPTION:
				return true;
			case JOptionPane.NO_OPTION:
			case JOptionPane.CLOSED_OPTION:
				return false;
			}
		}

		return false;
	}

	/**
	 * 
	 * @return ContinuarAbortar.ABORTAR si se aborta la operación.
	 *         ContinuarAbortar.CONTINUAR si se continúa
	 */
	protected ContinuarAbortar guardar() {
		if (General.getFile() == null)
			return guardarComo();
		try {
			General.guardar();
			return ContinuarAbortar.CONTINUAR;
		} catch (ErrorAlGuardarException e) {
			JOptionPane.showMessageDialog(frmExamenMayo, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return ContinuarAbortar.ABORTAR;// error al guardar
		}
	}


	/**
	 * Pregunta al usuario si guardar al ser modificada la fecha.
	 * 
	 * @return ContinuarAbortar.ABORTAR si se aborta la operación.
	 *         ContinuarAbortar.CONTINUAR si se continúa
	 * @throws IOException
	 *             Cuando hay un error en la salida de datos
	 */
	ContinuarAbortar guardarSiModificado() {
		if (General.isModificado()) {
			switch (JOptionPane.showConfirmDialog(frmExamenMayo,
					"¿Desea guardar los cambios hechos a " + General.getNombreFile() + "?", "Examen Mayo 2016",
					JOptionPane.YES_NO_CANCEL_OPTION)) {
			case JOptionPane.YES_OPTION:
				return guardar();
			case JOptionPane.NO_OPTION:
				break;
			default:
				return ContinuarAbortar.ABORTAR;// case
												// JOptionPane.CANCEL_OPTION
												// case
			}
		}
		return ContinuarAbortar.CONTINUAR;
	}

	private void actualizarTitulo(String string) {
		frmExamenMayo.setTitle("Examen Mayo 2016: " + string);
	}

	static {
		Locale.setDefault(new Locale("es", "ES"));// para que me muestre los
													// meses/días en español
	}

	/**
	 * Obtiene el mes según el idioma español de España.
	 * 
	 * @return Mes según el idioma español de España.
	 */
	String getMes(LocalDate localDate) {
		return localDate.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
	}

	/**
	 * Obtiene el día de la semana según el idioma español de España.
	 * 
	 * @param localDate
	 * 
	 * @return Día de la semana según el idioma español de España.
	 */
	String getDiaDeLaSemana(TemporalAccessor localDate) {
		return DayOfWeek.from(localDate).getDisplayName(TextStyle.FULL, Locale.getDefault());
	}
	
	
	private void generarMenuTresOpciones() {
		int opcion = JOptionPane.showConfirmDialog(frmExamenMayo, "¿Quieres saber en qué día vives?",
				"Seleccionar una Opción", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (opcion == JOptionPane.YES_OPTION)
			JOptionPane.showMessageDialog(frmExamenMayo,
				"Hoy es " + General.getLocalDate().getDayOfMonth() + " de "
						+ Fecha.getMes(General.getLocalDate()) + " de " + General.getLocalDate().getYear() + ".",
				"Selecciona una Opción", JOptionPane.INFORMATION_MESSAGE);

		else if (opcion == JOptionPane.CANCEL_OPTION || opcion == JOptionPane.CLOSED_OPTION)
			JOptionPane.showMessageDialog(frmExamenMayo, "Cancelado", "Mensaje",
					JOptionPane.INFORMATION_MESSAGE);
		else if (opcion == JOptionPane.NO_OPTION)
			JOptionPane.showMessageDialog(frmExamenMayo,
				"Pues tú te lo pierdes. Pero que sepas que hoy es: "
						+ Fecha.getDiaDeLaSemana(General.getLocalDate().getDayOfWeek()) + ".",
				"Mensaje", JOptionPane.INFORMATION_MESSAGE);
	}
}