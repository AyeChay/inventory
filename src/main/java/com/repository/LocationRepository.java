package com.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.LocationDTO;

public class LocationRepository {
	public int insertLocation(LocationDTO dto) {
        Connection con = ConnectionClass.getConnection();
        int result = 0;
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO location (location_name, address, deleted) values (?,?,?)");
            ps.setString(1, dto.getName());
            ps.setString(2, dto.getAddress());
            ps.setBoolean(3, false);

            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Insert Location: " + e.getMessage());
        }
        return result;
    }

    public List<LocationDTO> getAllLocations() {
        Connection con = ConnectionClass.getConnection();
        List<LocationDTO> lists = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM location WHERE deleted = FALSE");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LocationDTO dto = new LocationDTO();
                dto.setId(rs.getInt("location_id"));
                dto.setName(rs.getString("location_name"));
                dto.setAddress(rs.getString("address"));
                dto.setDeleted(rs.getBoolean("deleted"));
                lists.add(dto);
            }
        } catch (SQLException e) {
            System.out.println("Get All Locations: " + e.getMessage());
        }
        return lists;
    }

    public LocationDTO getLocationById(int id) {
        LocationDTO locationDTO = null;
        Connection con = ConnectionClass.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM location WHERE location_id=? AND deleted = FALSE");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                locationDTO = new LocationDTO();
                locationDTO.setId(rs.getInt("location_id"));
                locationDTO.setName(rs.getString("location_name"));
                locationDTO.setAddress(rs.getString("address"));
                locationDTO.setDeleted(rs.getBoolean("deleted"));
            }
        } catch (SQLException e) {
            System.out.println("Get Location By Id: " + e.getMessage());
        }
        return locationDTO;
    }

    public int updateLocation(LocationDTO dto) {
        Connection con = ConnectionClass.getConnection();
        int result = 0;
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE location SET location_name = ?, address = ? WHERE location_id = ?");
            ps.setString(1, dto.getName());
            ps.setString(2, dto.getAddress());
            ps.setInt(3, dto.getId());

            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Update Location: " + e.getMessage());
        }
        return result;
    }

    public int softDeleteLocation(int id) {
        Connection con = ConnectionClass.getConnection();
        int result = 0;
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE location SET deleted = TRUE WHERE location_id = ?");
            ps.setInt(1, id);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Soft Delete Location: " + e.getMessage());
        }
        return result;
    }
}
