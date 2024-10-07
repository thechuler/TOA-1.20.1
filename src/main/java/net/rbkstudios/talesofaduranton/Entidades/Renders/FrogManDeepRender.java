package net.rbkstudios.talesofaduranton.Entidades.Renders;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManBeastEntity;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManDeepEntity;
import net.rbkstudios.talesofaduranton.Entidades.Modelos.FrogManBeastModel;
import net.rbkstudios.talesofaduranton.Entidades.Modelos.FrogManDeepModel;
import net.rbkstudios.talesofaduranton.TalesOfAduranton;


public class FrogManDeepRender<type extends FrogManDeepEntity> extends MobRenderer<type, FrogManDeepModel<type>> {



    private static final ResourceLocation TEXTURAS = new ResourceLocation(TalesOfAduranton.MODID, "textures/entity/frogmandeep.png");



    public FrogManDeepRender(EntityRendererProvider.Context pContext) {
        super(pContext, new FrogManDeepModel<>(pContext.bakeLayer(FrogManDeepModel.LAYER_LOCATION)), 0.5f);

    }




    @Override
    public void render(FrogManDeepEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

        super.render((type) pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);

    }




    @Override
    public ResourceLocation getTextureLocation(type type) {
        return TEXTURAS;
    }
}
