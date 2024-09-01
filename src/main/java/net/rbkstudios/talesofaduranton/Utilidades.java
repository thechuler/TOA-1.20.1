package net.rbkstudios.talesofaduranton;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Random;

public class Utilidades {





    public static BlockPos GenerarPosicionAleatoriaEnArea(BlockPos centro, int radio) {
        Random random = new Random();


        double angulo = 2 * Math.PI * random.nextDouble();
        double distancia = radio * Math.sqrt(random.nextDouble());
        int x = (int) (centro.getX() + distancia * Math.cos(angulo));
        int z = (int) (centro.getZ() + distancia * Math.sin(angulo));
        return new BlockPos(x, centro.getY() + 1, z);
    }



    public static int GenerarNumeroAleatorio(int min,int max){
        Random random = new Random();
        int randomNumber = random.nextInt((max - min) + 1) + min;
    return randomNumber;
    }


    public static Entity getEntityPlayerIsLookingAt(Player player, double maxDistance) {
        Level world = player.level();
        Vec3 eyePosition = player.getEyePosition(1.0F); // Posición de los ojos del jugador
        Vec3 lookVector = player.getViewVector(1.0F); // Dirección en la que el jugador está mirando
        Vec3 reachVector = eyePosition.add(lookVector.scale(maxDistance)); // Punto final del rayo basado en el alcance

        // Crea un AABB para el rayo
        AABB boundingBox = player.getBoundingBox().expandTowards(lookVector.scale(maxDistance)).inflate(1.0D, 1.0D, 1.0D);

        // Realiza el ray tracing para encontrar la entidad
        EntityHitResult entityHitResult = ProjectileUtil.getEntityHitResult(world, player, eyePosition, reachVector, boundingBox,
                entity -> !entity.isSpectator() && entity.isPickable());

        if (entityHitResult != null) {
            return entityHitResult.getEntity(); // Devuelve la entidad si se encuentra
        }

        return null; // Devuelve null si no se encuentra ninguna entidad
    }

    public static List<LivingEntity> ObtenerEntidadesEnArea(Level level, BlockPos centro, int radio) {
        // Calcular los límites del AABB en función del punto central y el radio
        double minX = centro.getX() - radio;
        double minY = centro.getY() - radio;
        double minZ = centro.getZ() - radio;
        double maxX = centro.getX() + radio;
        double maxY = centro.getY() + radio;
        double maxZ = centro.getZ() + radio;

        // Crear el AABB
        AABB area = new AABB(minX, minY, minZ, maxX, maxY, maxZ);

        // Obtener y devolver la lista de entidades vivientes dentro del AABB
        return level.getEntitiesOfClass(LivingEntity.class, area);
    }







    public static void spawnParticlesEnArea(Level level, ParticleOptions particleType, BlockPos center, double radius, int particleCount) {
        if (level instanceof ServerLevel serverLevel) {
            for (int i = 0; i < particleCount; i++) {
                // Generar un ángulo aleatorio para las coordenadas esféricas
                double theta = Math.random() * Math.PI; // Ángulo polar
                double phi = Math.random() * 2 * Math.PI; // Ángulo azimutal

                // Calcular las coordenadas en el espacio esférico
                double x = center.getX() + radius * Math.sin(theta) * Math.cos(phi);
                double y = center.getY() + radius * Math.cos(theta);
                double z = center.getZ() + radius * Math.sin(theta) * Math.sin(phi);

                // Enviar la partícula a la posición calculada
                serverLevel.sendParticles(particleType, x, y, z, 1, 0, 0, 0, 0);
            }
        }
    }



    public static ItemStack EncontrarItemEnJugador(Player player, Item item){
        for(ItemStack itemStack : player.getInventory().items){
            if(!itemStack.isEmpty() && itemStack.getItem() == item){
                return itemStack;
            }
        }
        return  ItemStack.EMPTY;
    }



    public static void spawnearParticulas(LivingEntity entity, int particleCount , ParticleOptions particleType) {
        if (!entity.level().isClientSide()) {
            Random random = new Random();
            ServerLevel serverLevel = (ServerLevel) entity.level();


            for (int i = 0; i < particleCount; i++) {
                double offsetX = (random.nextDouble() - 0.5) * 2.0;
                double offsetY = random.nextDouble() * 2.0;
                double offsetZ = (random.nextDouble() - 0.5) * 2.0;

                double posX = entity.getX() + offsetX;
                double posY = entity.getY() + offsetY;
                double posZ = entity.getZ() + offsetZ;


                serverLevel.sendParticles(particleType, posX, posY, posZ, 1, 0, 0, 0, 0);

            }

        }
    }








}
