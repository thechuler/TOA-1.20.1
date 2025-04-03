package net.rbkstudios.talesofaduranton.Entidades.Renders;


import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManEntity;
import net.rbkstudios.talesofaduranton.Entidades.Entity.NitroFroglinEntity;
import net.rbkstudios.talesofaduranton.Entidades.Modelos.FrogManModel;
import net.rbkstudios.talesofaduranton.Entidades.Modelos.NitroFroglinModel;
import net.rbkstudios.talesofaduranton.TalesOfAduranton;
import org.joml.Vector3d;

public class NitroFroglinRender<type extends NitroFroglinEntity> extends MobRenderer<type, NitroFroglinModel<type>> {


    NitroFroglinEntity entity;
    private static final ResourceLocation TEXTURAS = new ResourceLocation(TalesOfAduranton.MODID, "textures/entity/nitrofroglin.png");
    private static final ResourceLocation BABY = new ResourceLocation(TalesOfAduranton.MODID, "textures/entity/nitrofroglinbaby.png");
    private static final ResourceLocation APAGADO = new ResourceLocation(TalesOfAduranton.MODID, "textures/entity/nitrofroglinapagado.png");
    private static final ResourceLocation EASTEREGG = new ResourceLocation(TalesOfAduranton.MODID, "textures/entity/nitrofroglinriver.png");


    public NitroFroglinRender(EntityRendererProvider.Context pContext) {
        super(pContext, new NitroFroglinModel<>(pContext.bakeLayer(NitroFroglinModel.LAYER_LOCATION)), 0.5f);

    }






    @Override
    public void render(NitroFroglinEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        entity = pEntity;
        if(entity.isBaby()){
            pPoseStack.scale(0.7f,0.7f,0.7f);
        }


        super.render((type) pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }




    @Override
    public ResourceLocation getTextureLocation(type type) {
        if(!this.entity.getData(entity.ENCENDIDO)){
            return APAGADO;
        }
        if(this.entity.isBaby()){
            return BABY;
        }
        if (this.entity.hasCustomName() && this.entity.getCustomName() != null && this.entity.getCustomName().getString().equals("Enzo")) {
            return EASTEREGG;
        } else {
            return TEXTURAS;
        }


    }




}
