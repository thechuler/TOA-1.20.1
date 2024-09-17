package net.rbkstudios.talesofaduranton.Entidades.Modelos;// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.rbkstudios.talesofaduranton.Entidades.Animaciones.FrogManAnimaciones;
import net.rbkstudios.talesofaduranton.Entidades.Animaciones.FrogManBeastAnimaciones;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManBeastEntity;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManEntity;

public class FrogManBeastModel<T extends FrogManBeastEntity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "frogmanbeastmodel"), "main");
	private final ModelPart root;


	public FrogManBeastModel(ModelPart root) {
		this.root = root.getChild("root");

	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 7.0F));

		PartDefinition BrazoIzq = root.addOrReplaceChild("BrazoIzq", CubeListBuilder.create().texOffs(58, 60).addBox(-7.0F, -1.0F, -4.0F, 8.0F, 17.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-10.0F, 8.0F, -7.0F));

		PartDefinition BrazoDer = root.addOrReplaceChild("BrazoDer", CubeListBuilder.create().texOffs(58, 60).mirror().addBox(-1.0F, -1.0F, -4.0F, 8.0F, 17.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(10.0F, 8.0F, -7.0F));

		PartDefinition PiernaDer = root.addOrReplaceChild("PiernaDer", CubeListBuilder.create().texOffs(66, 43).mirror().addBox(-1.0F, -2.0F, -4.0F, 6.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, 19.0F, -7.0F));

		PartDefinition PiernaIzq = root.addOrReplaceChild("PiernaIzq", CubeListBuilder.create().texOffs(66, 43).addBox(-5.0F, -2.0F, -4.0F, 6.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 19.0F, -7.0F));

		PartDefinition Cabeza = root.addOrReplaceChild("Cabeza", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, -7.0F));

		PartDefinition mandibulaInf = Cabeza.addOrReplaceChild("mandibulaInf", CubeListBuilder.create().texOffs(51, 22).addBox(-9.0F, -1.0F, -14.0F, 18.0F, 7.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 6.0F));

		PartDefinition mandibulasup = Cabeza.addOrReplaceChild("mandibulasup", CubeListBuilder.create(), PartPose.offset(0.0F, -5.0F, 7.0F));

		PartDefinition cube_r1 = mandibulasup.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-9.0F, -17.0F, -9.0F, 12.0F, 17.0F, 19.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 0).addBox(-39.0F, -17.0F, -9.0F, 12.0F, 17.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(18.0F, -6.0F, -8.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r2 = mandibulasup.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(43, 0).mirror().addBox(-9.0F, -11.0F, 2.0F, 12.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(43, 0).addBox(-39.0F, -11.0F, 2.0F, 12.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(18.0F, -6.3F, -27.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r3 = mandibulasup.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 36).addBox(-9.0F, -17.0F, -8.0F, 18.0F, 17.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -8.0F, -0.1745F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}



	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	public void setupAnim(T t, float v, float v1, float v2, float v3, float v4) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animateWalk(FrogManBeastAnimaciones.WALK, v, v1, 4f, 2.5f);
		this.animate(((FrogManBeastEntity) t).gruñirAnimationState, FrogManBeastAnimaciones.GRUÑIDO, v2, 1f);
		this.animate(((FrogManBeastEntity) t).idleAnimationState, FrogManBeastAnimaciones.IDLE, v2, 1f);
		this.animate(((FrogManBeastEntity) t).chargeAnimationState, FrogManBeastAnimaciones.CHARGE, v2, 1f);
		this.animate(((FrogManBeastEntity) t).atacarAnimationState, FrogManBeastAnimaciones.SPRINT, v2, 1f);
	}

}