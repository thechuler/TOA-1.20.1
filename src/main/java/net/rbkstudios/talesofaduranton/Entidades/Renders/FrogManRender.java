package net.rbkstudios.talesofaduranton.Entidades.Renders;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManEntity;
import net.rbkstudios.talesofaduranton.Entidades.Modelos.FrogManModel;
import net.rbkstudios.talesofaduranton.TalesOfAduranton;


public class FrogManRender<type extends FrogManEntity> extends MobRenderer<type, FrogManModel<type>> {


    FrogManEntity entity;

    private static final ResourceLocation[] TEXTURAS = new ResourceLocation[] {
           new ResourceLocation(TalesOfAduranton.MODID, "textures/entity/frogmantexture.png"),
            new ResourceLocation(TalesOfAduranton.MODID, "textures/entity/frogmantexture1.png"),
            new ResourceLocation(TalesOfAduranton.MODID, "textures/entity/frogmantexture2.png")
    };
    private static final ResourceLocation EASTEREGG = new ResourceLocation(TalesOfAduranton.MODID, "textures/entity/frogmanboca1.png");



    public FrogManRender(EntityRendererProvider.Context pContext) {
        super(pContext, new FrogManModel<>(pContext.bakeLayer(FrogManModel.LAYER_LOCATION)), 0.5f);
        this.addLayer(new FrogmanEyesLayer(this));
    }





    @Override
    public void render(FrogManEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        entity = pEntity;
        super.render((type) pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);

    }




    @Override
    public ResourceLocation getTextureLocation(type type) {

        if (this.entity.hasCustomName() && this.entity.getCustomName() != null && this.entity.getCustomName().getString().equals("Roman")) {
            return EASTEREGG;
        } else {
            return TEXTURAS[this.entity.index];
        }





    }
}
