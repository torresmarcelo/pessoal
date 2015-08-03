package sarine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.TreeSet;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import entidade.AtributoEntidadeIBM;

public class Comparador {
	private HashMap<String,AtributoEntidadeIBM> listaIBM = new HashMap<String,AtributoEntidadeIBM>();
	private HashMap<String, Integer> p = new HashMap<String, Integer>();
	
	public Comparador() {
		Properties prop = new Properties();
		try {
		    //load a properties file from class path, inside static method
		    prop.load(this.getClass().getClassLoader().getResourceAsStream("columns.properties"));

		    
		    
		    
		    
			p.put("Rotulo", parseInt(prop.getProperty("Rotulo")) -1 );
			p.put("Nome_da_Entidade", parseInt(prop.getProperty("Nome_da_Entidade")) -1 );
			p.put("Descricao_da_Entidade", parseInt(prop.getProperty("Descricao_da_Entidade")) -1 );
			p.put("Nome_Atributo", parseInt(prop.getProperty("Nome_Atributo")) -1 );
			p.put("Descricao_do_Atributo", parseInt(prop.getProperty("Descricao_do_Atributo")) -1 );
			p.put("Tipo_de_Dado", parseInt(prop.getProperty("Tipo_de_Dado")) -1 );
			p.put("Tamanho", parseInt(prop.getProperty("Tamanho")) -1 );
			p.put("Dominio", parseInt(prop.getProperty("Dominio")) -1 );
			p.put("Obrigatorio", parseInt(prop.getProperty("Obrigatorio")) -1 );
			p.put("is_PK", parseInt(prop.getProperty("is_PK")) -1 );
			p.put("is_FK", parseInt(prop.getProperty("is_FK")) -1 );
			p.put("Column_Validation_Name", parseInt(prop.getProperty("Column_Validation_Name")) -1 );
			p.put("Column_Validation_Value", parseInt(prop.getProperty("Column_Validation_Value")) -1 );
			p.put("INITIAL_ROW_COUNT", parseInt(prop.getProperty("INITIAL_ROW_COUNT")) -1 );
			p.put("MAX_ROWS", parseInt(prop.getProperty("MAX_ROWS")) -1 );
			p.put("GROWTH_BY_MONTH", parseInt(prop.getProperty("GROWTH_BY_MONTH")) -1 );
			p.put("Attribute_Order", parseInt(prop.getProperty("Attribute_Order")) -1 );
			p.put("Physical_Order", parseInt(prop.getProperty("Physical_Order")) -1 );
			p.put("Role_Name", parseInt(prop.getProperty("Role_Name")) -1 );
			
			p.put("resultado", parseInt(prop.getProperty("resultado")) -1 );
		    

		} 
		catch (IOException ex) {
		    ex.printStackTrace();
		}
	}
	
	
	public Integer parseInt(String valr) {
		return valr == null ? 0 : Integer.parseInt(valr);
	}
	
	public String getCellValue(int posicao, Row row) {
		
		if (posicao == -2)
			return "INEXISTENTE";
		
		Cell cell = row.getCell(posicao);
		
		if ( cell == null )
			return "";
		
		switch(cell.getCellType()) {
        case Cell.CELL_TYPE_BOOLEAN:
            return cell.getBooleanCellValue() == true ? "Yes" : "No";
        case Cell.CELL_TYPE_NUMERIC:
        	return trataDouble(cell.getNumericCellValue());
        case Cell.CELL_TYPE_STRING:
        	return  cell.getStringCellValue();
		}	
		
		return "";

	}
	
	public String trataDouble (double d) {
		if ( (int) d < d )
			return ("" + d).replace('.', ',');
		else
			return ("" + (int) d); 
	}
	
	public void leIBM(String arq) throws Exception {

		     
		    FileInputStream file = new FileInputStream(new File(arq));
		     
		    //Get the workbook instance for XLS file 
		    HSSFWorkbook workbook = new HSSFWorkbook(file);
		 
		    //Get first sheet from the workbook
		    HSSFSheet sheet = workbook.getSheetAt(0);
		     
		    //Iterate through each rows from first sheet
		    Iterator<Row> rowIterator = sheet.iterator();
		    int i = 0;
		    
		    while(rowIterator.hasNext()) {
		        Row row = rowIterator.next();
	
		        if ( ++i < 4 )
		        	continue;
		        
		        AtributoEntidadeIBM attr = new AtributoEntidadeIBM();
		        

				attr.setRotulo(getCellValue(p.get("Rotulo"), row ));
				attr.setNome_da_Entidade(getCellValue(p.get("Nome_da_Entidade"), row ));
				attr.setDescricao_da_Entidade(getCellValue(p.get("Descricao_da_Entidade"), row ));
				attr.setNome_Atributo(getCellValue(p.get("Nome_Atributo"), row ));
				attr.setDescricao_do_Atributo(getCellValue(p.get("Descricao_do_Atributo"), row ));
				attr.setTipo_de_Dado(getCellValue(p.get("Tipo_de_Dado"), row ));
				attr.setTamanho(getCellValue(p.get("Tamanho"), row ));
				attr.setDominio(getCellValue(p.get("Dominio"), row ));
				attr.setObrigatorio(getCellValue(p.get("Obrigatorio"), row ));
				attr.setIs_PK(getCellValue(p.get("is_PK"), row ));
				attr.setIs_FK(getCellValue(p.get("is_FK"), row ));
				attr.setColumn_Validation_Name(getCellValue(p.get("Column_Validation_Name"), row ));
				attr.setColumn_Validation_Value(getCellValue(p.get("Column_Validation_Value"), row ));
				attr.setINITIAL_ROW_COUNT(getCellValue(p.get("INITIAL_ROW_COUNT"), row ));
				attr.setMAX_ROWS(getCellValue(p.get("MAX_ROWS"), row ));
				attr.setGROWTH_BY_MONTH(getCellValue(p.get("GROWTH_BY_MONTH"), row ));
				attr.setAttribute_Order(getCellValue(p.get("Attribute_Order"), row ));
				attr.setPhysical_Order(getCellValue(p.get("Physical_Order"), row ));
		        
		        listaIBM.put(attr.getNome_da_Entidade() + "#" + attr.getNome_Atributo(), attr);
		    }
		    file.close();
		    
		     

	}
	
	public static String removeAccents(String text) {
	    return text == null ? null : Normalizer.normalize(text, Form.NFD)
	            .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}
	
	public boolean comparaString(String str1, String str2) {
		
		if ("INEXISTENTE".equalsIgnoreCase(str2)) {
			return true;
		}
		
		str1 = str1 == null? "":str1;
		str2 = str2 == null? "":str2;
		
		str1 = removeAccents(str1.replaceAll("\\r|\\n|\\t", " ").replaceAll(" ", "").replaceAll(" ", ""));
		str2 = removeAccents(str2.replaceAll("\\r|\\n|\\t", " ").replaceAll(" ", "").replaceAll(" ", ""));
		
		
		if ( ! str1.equalsIgnoreCase(str2) ) {
			return false;
		} else {
			return true;
		}
		
		
	}
	
	public String compara(String arqERwin) throws Exception {
		
	    FileInputStream file = new FileInputStream(new File(arqERwin));
	    
	    //Lista de entidades no arquivo IBM
	    ArrayList<String> atribsIBM = new ArrayList<String>();
	    TreeSet<String> tatribsIBM = new TreeSet<String>();
	    for (Map.Entry<String,AtributoEntidadeIBM> entry : listaIBM.entrySet()) {
	        atribsIBM.add(entry.getKey());
	        tatribsIBM.add(entry.getKey());
	    }
	    
	    
	    //Get the workbook instance for XLS file 
	    HSSFWorkbook workbook = new HSSFWorkbook(file);
	    HSSFCellStyle styleVERDE = workbook.createCellStyle();
	    styleVERDE.setFillForegroundColor(HSSFColor.GREEN.index);
	    styleVERDE.setFillBackgroundColor(HSSFColor.GREEN.index);
	    
	    HSSFCellStyle styleVERMELHO = workbook.createCellStyle();
	    styleVERMELHO.setFillForegroundColor(HSSFColor.RED.index);
	    styleVERMELHO.setFillBackgroundColor(HSSFColor.RED.index);
	    
	    //Get first sheet from the workbook
	    HSSFSheet sheet = workbook.getSheetAt(0);
	     
	    //Iterate through each rows from first sheet
	    Iterator<Row> rowIterator = sheet.iterator();
	    int i = 0;
	    
	    Row row2 = null;
	    
	    while(rowIterator.hasNext()) {
	        Row row = rowIterator.next();

	        Cell cell = row.createCell(p.get("resultado"));
	        Cell cell2 = row.createCell(p.get("resultado")+1);	        
	        Cell cell3 = row.createCell(p.get("resultado")+2);	
	        Cell cell4 = row.createCell(p.get("resultado")+3);
	        
	        if ( ++i == 1 ) {
	        	
        		cell.setCellValue("Descrição do(s) Erro(s)");
        		cell2.setCellValue("Número de Erros");
	        	cell3.setCellValue("Entidades/Atributos não encontradas no arquivo ERWin");
	        	cell4.setCellValue("Número Entidades/Atributos não encontradas no arquivo ERWin");
	        	continue;
	        }
	        
	        if ( i == 2 ) {
	        	row2 = row;
	        }
	        
	        String chave = getCellValue(0, row )+ "#" + getCellValue(2, row );
	        AtributoEntidadeIBM attr = listaIBM.get(chave);
	        atribsIBM.remove(chave);
	        tatribsIBM.remove(chave);
	        
	        int numerros = 0;

	        if ( attr == null ) {
	        	cell.setCellValue("NÃO ENCONTRADA NO ARQUIVO IBM");
	        	cell.setCellStyle(styleVERMELHO);
	        } else {
	        	String celula = "";
	        	if ( ! comparaString(getCellValue(1, row ),attr.getDescricao_da_Entidade())) {
	        		celula += "Descricao da Entidade DIFERENTE\n";
	        		numerros++;
	        	}

	        	if ( ! comparaString(getCellValue(3, row ),attr.getDescricao_do_Atributo())) {
	        		celula += "Descricao do Atributo DIFERENTE\n";
	        		numerros++;
	        	}	        	
	        	
	        	
	        	if ( ("Yes".equalsIgnoreCase(attr.getObrigatorio()) && "NULL".equalsIgnoreCase(getCellValue(7, row )) ) ||
	        			("No".equalsIgnoreCase(attr.getObrigatorio()) && "NOT NULL".equalsIgnoreCase(getCellValue(7, row )) )
	        			
	        			) {
	        		celula += "Null Option DIFERENTE\n";
	        		numerros++;
	        	}	
	        	
	        	if ( ! comparaString(getCellValue(8, row ),attr.getIs_PK())) {
	        		celula += "Is PK DIFERENTE\n";
	        		numerros++;
	        	}		        	
	        	
	        	if ( ! comparaString(getCellValue(9, row ),attr.getIs_FK())) {
	        		celula += "Is FK DIFERENTE\n";
	        		numerros++;
	        	}	

	        	String tipoDado= attr.getTipo_de_Dado() + "(" + attr.getTamanho().replaceAll(",0", "") +")";
	        	
	        	
	        	if ( "date".equalsIgnoreCase(attr.getTipo_de_Dado()) )
	        		tipoDado = attr.getTipo_de_Dado();
	        	
	        	
	        	if ( ! comparaString(getCellValue(5, row ),tipoDado)) {
	        		celula += "Tipo de Dado DIFERENTE:" + tipoDado + "\n";
	        		numerros++;
	        	}	

	        	
	        	if ( ! comparaString(getCellValue(10, row ),attr.getColumn_Validation_Name())) {
	        		celula += "Column_Validation_Name DIFERENTE\n";
	        		numerros++;
	        	}	
	        	
	        	if ( ! comparaString(getCellValue(11, row ),attr.getColumn_Validation_Value())) {
	        		celula += "Column_Validation_Value DIFERENTE\n";
	        		numerros++;
	        	}
	        	
	        	if ( ! comparaString(getCellValue(15, row ),attr.getINITIAL_ROW_COUNT())) {
	        		celula += "VOLUMETRIA INICIAL DIFERENTE\n";
	        		numerros++;
	        	}
	        	
	        	if ( ! comparaString(getCellValue(16, row ),attr.getMAX_ROWS())) {
	        		celula += "VOLUMETRIA MAXIMA DIFERENTE\n";
	        		numerros++;
	        	}
	        	
	        	if ( ! comparaString(getCellValue(17, row ),attr.getGROWTH_BY_MONTH())) {
	        		celula += "CRESCIMENTO MENSAL DIFERENTE\n";
	        		numerros++;
	        	}
	        	
	        	if ( ! comparaString(getCellValue(20, row ),attr.getAttribute_Order())) {
	        		celula += "Attribute_Order DIFERENTE\n";
	        		numerros++;
	        	}
	        	
	        	if ( ! comparaString(getCellValue(22, row ),attr.getPhysical_Order())) {
	        		celula += "Physical_Order DIFERENTE\n";
	        		numerros++;
	        	}

	        	
	        	if ( ! comparaString(getCellValue(23, row ),attr.getRole_Name())) {
	        		celula += "ROLE NAME DIFERENTE\n";
	        		numerros++;
	        	}

	        	if ( ! comparaString(getCellValue(24, row ),attr.getDominio())) {
	        		celula += "DOMINIO DIFERENTE\n";
	        		numerros++;
	        	}	        	
	        	
	        	if ("".equals(celula)) {
	        		cell.setCellValue("OK");
	        		cell2.setCellValue(numerros);
	        	} else {
	        		cell.setCellValue(celula);
	        		cell2.setCellValue(numerros);
	        	}
	        		
	        }
	        
	    }
	    
	    file.close();
	    
	    
	    Cell cellNaoEncontradosERWin = row2.createCell(p.get("resultado")+2);
	    Cell cellNumNaoEncontradosERWin = row2.createCell(p.get("resultado")+3);
	    String strNaoEncontradosERWin = "";
	    for (String atributo : tatribsIBM) {
	    	strNaoEncontradosERWin += ( atributo + "\n" );
		}
	    
	    
	    cellNaoEncontradosERWin.setCellValue(strNaoEncontradosERWin);
	    cellNumNaoEncontradosERWin.setCellValue(tatribsIBM.size());
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    String nomArquivoResultado = arqERwin.replaceAll(".xls", "_resultado" + (new java.util.Date()).hashCode() + ".xls");
	    
        FileOutputStream out = new FileOutputStream(new File(nomArquivoResultado));
        workbook.write(out);
        out.close();
        System.out.println("Excel written successfully..");

	    return nomArquivoResultado;
	}
	
}
