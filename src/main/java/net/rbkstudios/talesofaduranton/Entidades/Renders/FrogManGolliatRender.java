package net.rbkstudios.talesofaduranton.Entidades.Renders;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManEntity;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManGolliatEntity;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManTraderEntity;
import net.rbkstudios.talesofaduranton.Entidades.Modelos.FrogManGolliatModel;
import net.rbkstudios.talesofaduranton.Entidades.Modelos.FrogManTraderModel;
import net.rbkstudios.talesofaduranton.TalesOfAduranton;


public class FrogManGolliatRender<type extends FrogManGolliatEntity> extends MobRenderer<type, FrogManGolliatModel<type>> {


    FrogManEntity entity;

    private static final ResourceLocation TEXTURAS = new ResourceLocation(TalesOfAduranton.MODID, "textures/entity/frogman_golliat.png");




    public FrogManGolliatRender(EntityRendererProvider.Context pContext) {
        super(pContext, new FrogManGolliatModel<>(pContext.bakeLayer(FrogManGolliatModel.LAYER_LOCATION)), 0.5f);


    }


    @Override
    public void render(FrogManGolliatEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.scale(2, 2, 2);
        super.render((type) pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);

    }

    @Override
    public ResourceLocation getTextureLocation(type type) {
            return TEXTURAS;
    }
}
