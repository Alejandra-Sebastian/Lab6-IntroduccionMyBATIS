/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.simpleview;

import edu.eci.pdsw.persistence.impl.mappers.PacienteMapper;
import edu.eci.pdsw.samples.entities.Paciente;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
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
        Paciente paciente = pmapper.loadPacienteById(1068953380, "CC");
        for (Paciente p : pacientes) {
            System.out.println(p.getNombre());
        }
        System.out.println("");
        System.out.println(paciente.getNombre());

//imprimir contenido de la lista
        
    }

    /**
     * Registra un nuevo paciente y sus respectivas consultas (si existiesen).
     * @param pmap mapper a traves del cual se hará la operacion
     * @param p paciente a ser registrado
     */
    public void registrarNuevoPaciente(PacienteMapper pmap, Paciente p){
        
    }
    
}
