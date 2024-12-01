/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capaNegocio;

import capaDatos.clsJDBC;
import java.sql.ResultSet;
import java.util.Random;

/**
 *
 * @author Grupo_Veterinaria
 */
public class clsCaptcha {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
    
    private String [] letras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"};
    private int numDigitos = 5;
    private int numLetras = 2;

    public String mostrarCaptcha() throws Exception {
        String captcha = "";
        int auxiliar;
        int auxiliar2;
        
        String auxiliar3;
        
        for (int i = 0; i < numDigitos; i++) {
            Random rand = new Random();
            auxiliar = rand.nextInt(9);
            auxiliar3 = String.valueOf(auxiliar);
            
            captcha += auxiliar3;   
        }
        
        for (int i = 0; i < numLetras; i++) {
            Random rand = new Random();
            auxiliar2 = rand.nextInt(12);
            
            captcha += letras[auxiliar2];
        }
        
        return captcha;
    }

    public Boolean validarCaptcha(String captcha, String respuesta) throws Exception {
        
        if (captcha.equals(respuesta)) {
            return true;
        } else {
            return false;
        }
    }
}
