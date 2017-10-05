/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.pdsw.samples.entities;


import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author hcadavid
 */
public class Paciente {
    
    private int id;
    private String tipoId;
    private String nombre;
    private Date fechaNacimiento;
    Set<Consulta> consultas=new LinkedHashSet<>();;
    private Eps eps;
    

    public Paciente(int id,String tipoid, String nombre, Date fechaNacimiento,Eps eps) {
        this.id=id;
        this.tipoId = tipoid;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        consultas=new LinkedHashSet<>();
        this.eps=eps;
    }

    public Paciente(){
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public Eps getEps(){
        return eps;
    }

    public void setEps(Eps eps){
        this.eps = eps;
    }
    
    
    public String getTipoId(){
        return tipoId;
    }

    public void setTipoId(String tipoId){
        this.tipoId = tipoId;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public Date getFechaNacimiento(){
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento){
        this.fechaNacimiento = fechaNacimiento;
    }

    public Set<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(Set<Consulta> consultas) {
        this.consultas = consultas;
    }

    @Override
    public String toString() {
        String rep="Paciente:["+id+","+tipoId+","+nombre+","+fechaNacimiento+"]\n";
        for (Consulta c:consultas){
            rep+="\t["+c+"]\n";
        }
        return rep;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.id;
        hash = 23 * hash + Objects.hashCode(this.tipoId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Paciente other = (Paciente) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.tipoId, other.tipoId)) {
            return false;
        }
        return true;
    }


    
    
}