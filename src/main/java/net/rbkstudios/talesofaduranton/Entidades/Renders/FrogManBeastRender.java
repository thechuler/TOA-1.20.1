package net.rbkstudios.talesofaduranton.Entidades.Renders;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManBeastEntity;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManEntity;
import net.rbkstudios.talesofaduranton.Entidades.Modelos.FrogManBeastModel;
import net.rbkstudios.talesofaduranton.Entidades.Modelos.FrogManModel;
import net.rbkstudios.talesofaduranton.TalesOfAduranton;


public class FrogManBeastRender<type extends FrogManBeastEntity> extends MobRenderer<type, FrogManBeastModel<type>> {



    private static final ResourceLocation TEXTURAS = new ResourceLocation(TalesOfAduranton.MODID, "textures/entity/frogmanbeast.png");



    public FrogManBeastRender(EntityRendererProvider.Context pContext) {
        super(pContext, new FrogManBeastModel<>(pContext.bakeLayer(FrogManBeastModel.LAYER_LOCATION)), 0.5f);

    }




    @Override
    public void render(FrogManBeastEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

        super.render((type) pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);

    }




    @Override
    public ResourceLocation getTextureLocation(type type) {
        return TEXTURAS;
    }
}
