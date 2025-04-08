package com.AIMLproject.backend.domain.customobject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AIMLproject.backend.domain.customobject.model.Mesh;
import com.AIMLproject.backend.domain.project.model.Project;
import com.AIMLproject.backend.domain.customobject.repository.MeshRepository;

@Service
public class MeshService {

	private final MeshRepository meshRepository;

	@Autowired
	public MeshService(MeshRepository meshRepository) {
		this.meshRepository = meshRepository;
	}

	public List<Mesh> getAllObjects(Project project) {
		return meshRepository.findByProject(project);
	}

	public Mesh getObject(Project project, Long objectId) {
		Mesh mesh = meshRepository.findById(objectId).orElseThrow(() -> new RuntimeException("Object not found"));
		if (!mesh.getProject().equals(project)) {
			throw new RuntimeException("Object not found");
		}
		return mesh;
	}

	public Mesh createObject(Project project, List<Double> matrix, String geometry, String material) {
		return meshRepository.save(new Mesh(project, matrix, geometry, material));

	}

	public Mesh updateObject(Project project, Long objectId, List<Double> matrix, String geometry, String material) {
		Mesh object = getObject(project, objectId);

		if (matrix != null) {
			object.setMatrix(matrix);
		}
		if (geometry != null) {
			object.setGeometry(geometry);
		}
		if (material != null) {
			object.setMaterial(material);
		}

		return meshRepository.save(object);
	}

	public void deleteObject(Project project, Long objectId) {
		Mesh object = getObject(project, objectId);
		meshRepository.delete(object);
	}

}
