package net.hibiscus.naturespirit.entity;

import net.minecraft.entity.ai.brain.sensor.HoglinSpecificSensor;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

public class HibiscusSensorTypes {

   public static final SensorType <BisonSpecificSensor> BISON_SEPCIFIC_SENSOR = register("bison_specific_sensor", BisonSpecificSensor::new);

   private static <U extends Sensor <?>> SensorType<U> register(String id, Supplier <U> factory) {
      return (SensorType) Registry.register(Registries.SENSOR_TYPE, new Identifier(MOD_ID, id), new SensorType(factory));
   }
}
