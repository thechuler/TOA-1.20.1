package net.rbkstudios.talesofaduranton.Entidades.Renders;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManEntity;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManZombieEntity;
import net.rbkstudios.talesofaduranton.Entidades.Modelos.FrogManModel;
import net.rbkstudios.talesofaduranton.Entidades.Modelos.FrogManZombieModel;
import net.rbkstudios.talesofaduranton.TalesOfAduranton;


public class FrogManZombieRender<type extends FrogManZombieEntity> extends MobRenderer<type, FrogManZombieModel<type>> {


    FrogManZombieEntity entity;

    private static final ResourceLocation TEXTURE_VARIANT_1 = new ResourceLocation("talesofaduranton", "textures/entity/frogman_zombie1.png");
    private static final ResourceLocation TEXTURE_VARIANT_2 = new ResourceLocation("talesofaduranton", "textures/entity/frogman_zombie2.png");
    private static final ResourceLocation TEXTURE_VARIANT_3 = new ResourceLocation("talesofaduranton", "textures/entity/frogman_zombie3.png");


    private static final ResourceLocation TEXTURAS = new ResourceLocation(TalesOfAduranton.MODID, "textures/entity/frogmanzombie.png");




    public FrogManZombieRender(EntityRendererProvider.Context pContext) {
        super(pContext, new FrogManZombieModel(pContext.bakeLayer(FrogManZombieModel.LAYER_LOCATION)), 0.5f);

    }





    @Override
    public void render(FrogManZombieEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render((type) pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
        entity = pEntity;
    }




    @Override
    public ResourceLocation getTextureLocation(FrogManZombieEntity entity) {
        switch (entity.getTextureVariant()) {
            case 1:
                return TEXTURE_VARIANT_2;
            case 2:
                return TEXTURE_VARIANT_3;
            default:
                return TEXTURE_VARIANT_1;
        }
    }
}
