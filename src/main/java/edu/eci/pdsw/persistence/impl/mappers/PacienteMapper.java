package edu.eci.pdsw.persistence.impl.mappers;

import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Paciente;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author 2106913
 */
public interface PacienteMapper {
        
    public Paciente loadPacienteById(@Param("idp") int id,@Param("tipoidp") String tipoid);
    
    public List<Paciente> loadPacientes();
    
    public void insertarPaciente(@Param("paci") Paciente p);
    
    public void actualizarPaciente(@Param("maper") PacienteMapper pmap, @Param("paci") Paciente p);
    
    public void insertConsulta(@Param("consul") Consulta con, @Param("idpaci") int idPaciente, @Param("tipoidpaci") String tipoid,@Param("costo") int costoconsulta);

}
