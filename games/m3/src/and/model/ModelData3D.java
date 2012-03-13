package and.model;

import java.util.Arrays;

public class ModelData3D {
	 public int[] vertices;
	 public float[] tex;
	 public short[] indices;
	 public int vertexCount;
	 public void print() {
		 System.out.println("vertices=" + Arrays.toString(vertices));
		 System.out.println("tex=" + Arrays.toString(tex));
		 System.out.println("indices=" + Arrays.toString(indices));
	 }
 }