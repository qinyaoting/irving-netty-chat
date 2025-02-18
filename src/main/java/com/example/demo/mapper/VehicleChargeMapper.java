package com.example.demo.mapper;

import com.example.demo.model.VehicleCharge;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VehicleChargeMapper {
    @Select("SELECT id,vehicle_type vehicleType, vehicle_desc vehicleDesc, iu_prefix iuPrefix, hour_charge hourCharge, tax_rate taxRate FROM t_vehicle_charge ORDER BY id LIMIT #{offset}, #{limit}")
    List<VehicleCharge> getAll(@Param("offset") int offset, @Param("limit") int limit);

    @Select("SELECT COUNT(*) FROM t_vehicle_charge")
    int count();

    @Insert("INSERT INTO t_vehicle_charge (vehicle_type, vehicle_desc, iu_prefix, hour_charge, tax_rate) " +
            "VALUES (#{vehicleType}, #{vehicleDesc}, #{iuPrefix}, #{hourCharge}, #{taxRate})")
    void add(VehicleCharge vehicleCharge);

    @Update("UPDATE t_vehicle_charge SET vehicle_type = #{vehicleType}, vehicle_desc = #{vehicleDesc}, " +
            "iu_prefix = #{iuPrefix}, hour_charge = #{hourCharge}, tax_rate = #{taxRate} WHERE id = #{id}")
    void update(VehicleCharge vehicleCharge);

    @Delete("DELETE FROM t_vehicle_charge WHERE id = #{id}")
    void delete(int id);
}
