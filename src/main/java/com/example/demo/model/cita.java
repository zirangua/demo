package com.example.demo.model;

import lombok.Data;

import java.sql.Date;

@Data
public class cita {
    public String consultorio;
    public String doctor;
    public Date horario;
    public String paciente;
}
