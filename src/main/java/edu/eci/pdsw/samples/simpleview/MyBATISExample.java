/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.simpleview;

import edu.eci.pdsw.persistence.impl.mappers.PacienteMapper;
import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Eps;
import edu.eci.pdsw.samples.entities.Paciente;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author hcadavid
 */
public class MyBATISExample {

/**
     * Método que construye una fábrica de sesiones de MyBatis a partir del
     * archivo de configuración ubicado en src/main/resources
     *
     * @return instancia de SQLSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getLocalizedMessage(),e);
            }
        }
        return sqlSessionFactory;
    }

    /**
     * Programa principal de ejempo de uso de MyBATIS
     * @param args
     * @throws SQLException 
     */
    public static void main(String args[]) throws SQLException {
        SqlSessionFactory sessionfact = getSqlSessionFactory();
        SqlSession sqlss = sessionfact.openSession();
        PacienteMapper pmapper=sqlss.getMapper(PacienteMapper.class);

        List<Paciente> pacientes=pmapper.loadPacientes();
//imprimir contenido de la lista        
        for (Paciente p : pacientes) {
            System.out.println(p.getNombre());
        }
        Paciente paci = new Paciente(10, "CC", "Roman", new Date(1998, 9, 15), new Eps("Sanitas", "8456982"));
        //pmapper.insertarPaciente(paci);
        Consulta con1 = new Consulta(new Date(2017, 10, 10), "Brazo Roto", 15000);
        Consulta con2 = new Consulta(new Date(2017, 10, 10), "Brazo Roto", 15000);
        Consulta con3 = new Consulta(new Date(2017, 10, 10), "Brazo Roto", 15000);
        Set<Consulta> consultas = new LinkedHashSet<Consulta>();
        consultas.add(con1);
        consultas.add(con2);
        consultas.add(con3);
        //pmapper.insertConsulta(con, 10, "CC", 15000);
        paci.setConsultas(consultas);
        //Paciente paciente = pmapper.loadPacienteById(81310257, "CC");
//        System.out.println("");
//        System.out.println(paciente.getNombre());
        registrarNuevoPaciente(pmapper, paci);
        
        sqlss.commit();

        
    }

    /**
     * Registra un nuevo paciente y sus respectivas consultas (si existiesen).
     * @param pmap mapper a traves del cual se hará la operacion
     * @param p paciente a ser registrado
     */
    public static void registrarNuevoPaciente(PacienteMapper pmap, Paciente p){
        pmap.insertarPaciente(p);
        Set<Consulta> consults = p.getConsultas();
        Consulta[] consultas = new Consulta[consults.size()];
        consults.toArray(consultas);
        if(consultas.length > 0){
            for(Consulta c : consultas) {
                pmap.insertConsulta(c, p.getId(), p.getTipoId(), (int) c.getCosto());
            }       
        }
    }
    
}
