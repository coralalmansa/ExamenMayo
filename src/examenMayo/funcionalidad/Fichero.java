package examenMayo.funcionalidad;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

/**
 * Se encarga de la lectura/escritura en el sistema de ficheros
 * 
 * @author MaríaLourdes
 *
 */
public class Fichero {
	public static void escribir(LocalDate localDate, File file) throws ErrorAlGuardarException {
		file = annadirExtension(file);
		
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(file)))) {
			objectOutputStream.writeObject(localDate);
			objectOutputStream.writeUTF(Datos.CORAL_ALMANSA_DOMINGUEZ);
		} catch (IOException e) {
			throw new ErrorAlGuardarException("Error al guardar del fichero.");
		}
	}

	public static Datos leer(File file) throws ErrorAlLeerException {
		file = annadirExtension(file);
		
		try(ObjectInputStream objectInputStream = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream(file)))) {
				
			return new Datos((LocalDate) objectInputStream.readObject(), objectInputStream.readUTF());
					
		}catch (IOException | ClassNotFoundException e){
				throw new ErrorAlLeerException("Error al leer el fichero.");
		}
	}
	
	/**
	 * Añade la extensión fec sólo en caso de ser necesario
	 * 
	 * @param file
	 * @return Fichero con la extensión ".fec"
	 */
	private static File annadirExtension(File file) {

		String path = file.getPath();
		if (!path.endsWith(".fec"))
			return new File(path + ".fec");
		return file;
	}
}
