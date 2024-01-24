package com.example.demo.controller;

import com.example.demo.model.cita;

import java.util.List;

public interface operaciones {
    public String agregarcita(
            cita agregarCita
    );

    public Boolean cancelarCita(int idcita);

    public List<cita> consultarCitas();

    public List<cita>consultaxDoctor(String doctor);
}
