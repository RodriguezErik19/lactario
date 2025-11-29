package arbolAVL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class BTreePrinter {

	// Mínimo de ancho para un nodo
	private static final int MIN_WIDTH = 3;
	// Separación mínima entre centros de nodos hermanos en el nivel de hojas
	private static final int GAP = 6;

	// Clase interna para almacenar información de posición de nodos
	private static class NodeInfo {
		Nodo node;
		int x; // posición X del centro del nodo
		int level;
		
		NodeInfo(Nodo node, int x, int level) {
			this.node = node;
			this.x = x;
			this.level = level;
		}
	}

	public static void printNode(Nodo root) {
		if (root == null) {
			System.out.println("(árbol vacío)\n");
			return;
		}
		int nodeWidth = Math.max(MIN_WIDTH, computeMaxValueWidth(root));
		
		// Asignar posiciones X a todos los nodos de forma recursiva
		Map<Nodo, Integer> positions = new HashMap<>();
		assignPositionsRecursive(root, positions);
		
		// Agrupar nodos por nivel
		int maxLevel = maxLevel(root);
		List<List<NodeInfo>> levels = new ArrayList<>();
		for (int i = 0; i <= maxLevel; i++) {
			levels.add(new ArrayList<>());
		}
		collectNodesByLevel(root, positions, levels, 0);
		
		// Imprimir cada nivel
		for (int i = 0; i < levels.size(); i++) {
			printLevelWithPositions(levels.get(i), nodeWidth);
			if (i < levels.size() - 1) {
				System.out.println(); // línea en blanco entre niveles
			}
		}
	}

	// Asigna posiciones X de manera recursiva, garantizando que padres estén centrados
	private static int assignPositionsRecursive(Nodo node, Map<Nodo, Integer> positions) {
		if (node == null) return -1;
		
		int leftPos = assignPositionsRecursive(node.getIzquierdo(), positions);
		int rightPos = assignPositionsRecursive(node.getDerecho(), positions);
		
		int nodePos;
		if (leftPos == -1 && rightPos == -1) {
			// Nodo hoja: asignar siguiente posición disponible
			nodePos = positions.size() * GAP;
		} else if (leftPos == -1) {
			// Solo hijo derecho
			nodePos = rightPos;
		} else if (rightPos == -1) {
			// Solo hijo izquierdo
			nodePos = leftPos;
		} else {
			// Ambos hijos: centrar entre ellos
			nodePos = (leftPos + rightPos) / 2;
		}
		
		positions.put(node, nodePos);
		return nodePos;
	}

	// Recolecta nodos organizados por nivel con sus posiciones
	private static void collectNodesByLevel(Nodo node, Map<Nodo, Integer> positions, 
	                                        List<List<NodeInfo>> levels, int level) {
		if (node == null) return;
		
		int x = positions.get(node);
		levels.get(level).add(new NodeInfo(node, x, level));
		
		collectNodesByLevel(node.getIzquierdo(), positions, levels, level + 1);
		collectNodesByLevel(node.getDerecho(), positions, levels, level + 1);
	}

	// Imprime un nivel usando posiciones absolutas
	private static void printLevelWithPositions(List<NodeInfo> nodes, int nodeWidth) {
		if (nodes.isEmpty()) return;
		
		// Ordenar por posición X
		nodes.sort((a, b) -> Integer.compare(a.x, b.x));
		
		int currentPos = 0;
		for (NodeInfo info : nodes) {
			// La posición X es el centro del nodo, ajustamos para el inicio
			int nodeStart = info.x - (nodeWidth / 2);
			int spacesNeeded = nodeStart - currentPos;
			
			// Asegurar al menos un espacio entre nodos
			if (spacesNeeded < 1 && currentPos > 0) {
				spacesNeeded = 1;
			}
			
			if (spacesNeeded > 0) {
				printWhitespaces(spacesNeeded);
				currentPos += spacesNeeded;
			}
			
			String value = String.valueOf(info.node.getValor());
			String padded = padCenter(value, nodeWidth);
			System.out.print(padded);
			
			currentPos += nodeWidth;
		}
		System.out.println();
	}

	private static void printWhitespaces(int count) {
		for (int i = 0; i < count; i++)
			System.out.print(" ");
	}

	// Centra y recorta/ajusta el texto al ancho fijo del nodo
	private static String padCenter(String s, int width) {
		if (s == null) s = "";
		if (s.length() > width) {
			return s.substring(0, width);
		}
		int total = width - s.length();
		int left = total / 2; // centrado real simétrico
		int right = total - left;
		StringBuilder sb = new StringBuilder(width);
		for (int i = 0; i < left; i++) sb.append(' ');
		sb.append(s);
		for (int i = 0; i < right; i++) sb.append(' ');
		return sb.toString();
	}

	private static int computeMaxValueWidth(Nodo root) {
		int max = 0;
		List<Nodo> nivel = new ArrayList<>();
		nivel.add(root);
		while (!nivel.isEmpty()) {
			List<Nodo> siguiente = new ArrayList<>();
			for (Nodo n : nivel) {
				if (n != null) {
					String v = String.valueOf(n.getValor());
					if (v.length() > max) max = v.length();
					siguiente.add(n.getIzquierdo());
					siguiente.add(n.getDerecho());
				}
			}
			nivel = siguiente;
		}
		return max;
	}

	private static int maxLevel(Nodo node) {
		if (node == null)
			return 0;

		return Math.max(BTreePrinter.maxLevel(node.getIzquierdo()),
				BTreePrinter.maxLevel(node.getDerecho())) + 1;
	}

	private static <T> boolean isAllElementsNull(List<T> list) {
		for (Object object : list) {
			if (object != null)
				return false;
		}

		return true;
	}

}
