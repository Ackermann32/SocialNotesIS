package materiale;
import java.io.InputStream;

public class FileBean {

	private String filename;
	private String formato;
	private InputStream contenuto;
	private int dimensione;
	private int idFile;
	
	public FileBean() {
		this.filename="";
		this.formato="";
		this.contenuto=null;
		this.dimensione=0;
		this.idFile=-1;
	}

	
	public String getFilename() {
		return filename;
	}

	
	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public InputStream getContenuto() {
		return contenuto;
	}

	public void setContenuto(InputStream contenuto) {
		this.contenuto = contenuto;
	}

	public int getDimensione() {
		return dimensione;
	}

	public void setDimensione(int dimensione) {
		this.dimensione = dimensione;
	}
	
	
	public int getIdFile() {
		return idFile;
	}


	public void setIdFile(int idFile) {
		this.idFile = idFile;
	}


	public boolean isEmpty() {
		return this.filename.compareTo("")==0;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		return this.idFile==((FileBean)obj).getIdFile();
	}


	@Override
	public String toString() {
		return "FileBean [filename=" + filename + ", formato=" + formato + ", contenuto=" + contenuto + ", dimensione="
				+ dimensione + ", idFile=" + idFile + "]";
	}

	


}
