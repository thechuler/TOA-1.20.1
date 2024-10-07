package net.rbkstudios.talesofaduranton.Entidades.Renders;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManCrawlerEntity;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManEntity;
import net.rbkstudios.talesofaduranton.Entidades.Modelos.FrogManCrawlerModel;
import net.rbkstudios.talesofaduranton.Entidades.Modelos.FrogManModel;
import net.rbkstudios.talesofaduranton.TalesOfAduranton;


public class FrogManCrawlerRender<type extends FrogManCrawlerEntity> extends MobRenderer<type, FrogManCrawlerModel<type>> {


    private static final ResourceLocation TEXTURAS = new ResourceLocation(TalesOfAduranton.MODID, "textures/entity/frogmancrawler.png");



    public FrogManCrawlerRender(EntityRendererProvider.Context pContext) {

        super(pContext, new FrogManCrawlerModel<>(pContext.bakeLayer(FrogManCrawlerModel.LAYER_LOCATION)), 0.5f);
    }




   



    @Override
    public void render(FrogManCrawlerEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.scale(1.5f, 2, 1.3f);

        super.render((type) pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);

    }




    @Override
    public ResourceLocation getTextureLocation(type type) {

            return TEXTURAS;

    }
}
