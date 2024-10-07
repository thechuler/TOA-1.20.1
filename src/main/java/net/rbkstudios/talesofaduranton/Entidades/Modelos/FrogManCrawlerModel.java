package net.rbkstudios.talesofaduranton.Entidades.Modelos;// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.rbkstudios.talesofaduranton.Entidades.Animaciones.FrogManBeastAnimaciones;
import net.rbkstudios.talesofaduranton.Entidades.Animaciones.FrogManCrawlerAnimaciones;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManBeastEntity;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManCrawlerEntity;

public class FrogManCrawlerModel<T extends FrogManCrawlerEntity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "frogmancrawlermodel"), "main");
	private final ModelPart root;


	public FrogManCrawlerModel(ModelPart root) {
		this.root = root.getChild("root");

	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(7.0F, 14.0F, 0.0F));

		PartDefinition BrazoIzq = root.addOrReplaceChild("BrazoIzq", CubeListBuilder.create().texOffs(52, 33).addBox(-3.0F, -1.0F, -3.0F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-14.0F, 0.0F, 0.0F));

		PartDefinition Art1 = BrazoIzq.addOrReplaceChild("Art1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0.0F, -3.0F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 8.0F, 0.0F));

		PartDefinition BrazoDer2 = root.addOrReplaceChild("BrazoDer2", CubeListBuilder.create().texOffs(52, 33).mirror().addBox(0.0F, -1.0F, -3.0F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Art2 = BrazoDer2.addOrReplaceChild("Art2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.0F, 0.0F, -3.0F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.0F, 8.0F, 0.0F));

		PartDefinition PiernaDer = root.addOrReplaceChild("PiernaDer", CubeListBuilder.create().texOffs(42, 0).mirror().addBox(0.0F, 0.0F, -3.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, 6.0F, 0.0F));

		PartDefinition PiernaIzq = root.addOrReplaceChild("PiernaIzq", CubeListBuilder.create().texOffs(42, 0).addBox(-4.0F, 0.0F, -3.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-10.0F, 6.0F, 0.0F));

		PartDefinition Cabeza = root.addOrReplaceChild("Cabeza", CubeListBuilder.create(), PartPose.offsetAndRotation(-7.0F, 6.0F, 0.0F, 0.5672F, 0.0F, 0.0F));

		PartDefinition mandibulaInf = Cabeza.addOrReplaceChild("mandibulaInf", CubeListBuilder.create(), PartPose.offset(0.0F, -7.0F, 5.0F));

		PartDefinition cube_r1 = mandibulaInf.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 31).mirror().addBox(7.0F, -9.0F, -13.0F, 0.0F, 9.0F, 14.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 31).addBox(-7.0F, -9.0F, -13.0F, 0.0F, 9.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(38, 23).addBox(-7.0F, -10.0F, -13.0F, 14.0F, 10.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-7.0F, 0.0F, -13.0F, 14.0F, 9.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.2248F, 0.6994F, -0.2182F, 0.0F, 0.0F));

		PartDefinition mandibulasup = Cabeza.addOrReplaceChild("mandibulasup", CubeListBuilder.create(), PartPose.offset(0.0F, -6.7F, 6.7F));

		PartDefinition cube_r2 = mandibulasup.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(28, 31).mirror().addBox(6.0F, 0.0F, -14.0F, 0.0F, 5.0F, 14.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(28, 31).addBox(-6.0F, 0.0F, -14.0F, 0.0F, 5.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(0, 23).addBox(-6.0F, -8.0F, -14.0F, 12.0F, 8.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.8727F, 0.0F, 0.0F));

		PartDefinition cube_r3 = mandibulasup.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(28, 50).addBox(-6.0F, -8.0F, -14.0F, 12.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.0F, -6.0F, -0.8727F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}





	@Override
	public ModelPart root() {
		return this.root;
	}
	@Override
	public void setupAnim(T t, float v, float v1, float v2, float v3, float v4) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animateWalk(FrogManCrawlerAnimaciones.WALK, v, v1, 1f, 2.5f);
		this.animate(((FrogManCrawlerEntity) t).idleAnimationState, FrogManBeastAnimaciones.IDLE, v2, 1f);

	}
}