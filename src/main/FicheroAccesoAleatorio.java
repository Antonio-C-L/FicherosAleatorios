/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author antoniocruz
 */
public class FicheroAccesoAleatorio {

    private File f;
    private Map<Integer, String> campos;
    private long longReg;
    private long numReg = 0;

    FicheroAccesoAleatorio(String nomFich, Map<Integer, String> campos) throws IOException {
        this.campos = campos;
        this.f = new File(nomFich);
        longReg = 0;
        
//        campos.forEach((k,v) -> this.longReg+=campos.get(k));//TODO arreglar
       
        for (Map.Entry<Integer, String> entry : campos.entrySet()) {
            System.out.println("clave=" + entry.getKey() + ", valor=" + entry.getValue());
        }

        if (f.exists()) {
            this.numReg = f.length() / this.longReg;
        }
    }

    public long getNumReg() {
        return numReg;
    }

    public void insertar(Map<String, String> reg) throws IOException {
        insertar(reg, this.numReg++);
    }

    public void insertar(Map<Integer, String> reg, long pos) throws IOException {
        try (RandomAccessFile faa = new RandomAccessFile(f, "rws")) {
            faa.seek(pos * this.longReg);
            for (Map.Entry<Integer, String> entry : campos.entrySet()) {
            	String nomCampo=campos.get(faa);
            	Integer longCampo = campos.get(longCampo);
            	String valorCampo = reg.get(nomCampo);
            }
//            for (Map<String, Integer> campo : this.campos) {
//                String nomCampo = campo.get(faa);
//                Integer longCampo = campo.getValue();
//                String valorCampo = reg.get(nomCampo);
//                if (valorCampo == null) {
//                    valorCampo
//                            = "";
//                }
                String valorCampoForm = String.format("%1$-" + longCampo + "s", valorCampo);
                faa.write(valorCampoForm.getBytes("UTF-8"), 0, longCampo);
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	Map<Integer, String> campos = new HashMap<Integer, String>();
        campos.put(9, "DNI");
        campos.put(32, "NOMBRE");
        campos.put(5, "CP");
        try {
            FicheroAccesoAleatorio faa = new FicheroAccesoAleatorio("fic_acceso_aleat.dat", campos);
            Map reg = new HashMap();
            reg.put("DNI", "56789012B");
            reg.put("NOMBRE", "SAMPER");
            reg.put("CP", "29730");
            faa.insertar(reg);
            reg.clear();
            reg.put("DNI", "89012345E");
            reg.put("NOMBRE", "ROJAS");
            faa.insertar(reg);
            reg.clear();
            reg.put("DNI", "23456789D");
            reg.put("NOMBRE", "DORCE");
            reg.put("CP", "13700");
            faa.insertar(reg);
            reg.clear();
            reg.put("DNI", "78901234X");
            reg.put("NOMBRE", "NADALES");
            reg.put("CP", "44126");
            faa.insertar(reg, 1);
        } catch (IOException e) {
            System.err.println("Error de E / S: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
