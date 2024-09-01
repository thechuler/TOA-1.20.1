package net.rbkstudios.talesofaduranton.Entidades.Renders;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManEntity;
import net.rbkstudios.talesofaduranton.Entidades.Modelos.FrogManModel;
import net.rbkstudios.talesofaduranton.TalesOfAduranton;


public class FrogManSkeletonRender<type extends FrogManEntity> extends MobRenderer<type, FrogManModel<type>> {


    FrogManEntity entity;

    private static final ResourceLocation TEXTURAS = new ResourceLocation(TalesOfAduranton.MODID, "textures/entity/frogman_skeleton.png");




    public FrogManSkeletonRender(EntityRendererProvider.Context pContext) {
        super(pContext, new FrogManModel<>(pContext.bakeLayer(FrogManModel.LAYER_LOCATION)), 0.5f);

    }





    @Override
    public void render(FrogManEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        entity = pEntity;
        super.render((type) pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);

    }




    @Override
    public ResourceLocation getTextureLocation(type type) {


            return TEXTURAS;






    }
}
