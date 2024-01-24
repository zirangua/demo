package com.example.demo.controller;

import com.example.demo.model.cita;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlDataAccessService implements operaciones {
    private final JdbcTemplate jdbcTemplate;

    public MysqlDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String agregarcita(cita agregarCita) {
        String customsql = "insert into citas values(?,?,?,?)";


        jdbcTemplate.query(customsql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {

            }
        });

        jdbcTemplate.execute(customsql, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, agregarCita.consultorio);
                ps.setString(2, agregarCita.doctor);
                ps.setDate(3, agregarCita.horario);
                ps.setString(4, agregarCita.paciente);
                return ps.execute();
            }
        });
        return "cita Agregada";
    }

    @Override
    public Boolean cancelarCita(int idcita) {
        String cancelarCita = "delete from citas where idcitas = ?";
        return jdbcTemplate.execute(cancelarCita, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setInt(1, idcita);
                return ps.execute();
            }
        });
    }

    @Override
    public List<cita> consultarCitas() {

        String consultarCitas = "select * from citas;";
        return jdbcTemplate.query(consultarCitas, BeanPropertyRowMapper.newInstance(cita.class));
    }

    @Override
    public List<cita> consultaxDoctor(String doctor) {
        String consultaxDoctor = "select * from citas where doctor = ? ";
        ArrayList<cita> listaCita = new ArrayList<>();
        return jdbcTemplate.execute(consultaxDoctor, new PreparedStatementCallback<List<cita>>() {
            @Override
            public List<cita> doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, doctor);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    cita setCita = new cita();
                    setCita.consultorio = rs.getString(0);
                    setCita.doctor = rs.getString(1);
                    setCita.horario = rs.getDate(1);
                    setCita.paciente = rs.getString(1);
                    listaCita.add(setCita);
                }
                return listaCita;
            }
        });
    }
}
