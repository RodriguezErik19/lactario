package arbolAVL;
/*
 * Determina si se debe cambiar la altura de un nodo
 * Es utilizado por la calse ArbolAVL
 */

 public class Logical {
	boolean v;
	public Logical (boolean f) {
		v=f;
	}
	public void setLogical(boolean f) {
		v=f;
	}
	public boolean booleanValue() {
		return v;
	}
	

}
