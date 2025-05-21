package ru.leti;

import ru.leti.wise.task.graph.util.FileLoader;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ChromaticNumberTest {
	private final ChromaticNumber chromaticNumber = new ChromaticNumber();

	@Test
	public void emptyTest() throws FileNotFoundException {
		var graph = FileLoader.loadGraphFromJson("src/test/resources/empty.json");

		assertThat(chromaticNumber.run(graph) == 0).isTrue();
	}

	@Test
	public void fullyConnectedTest() throws FileNotFoundException {
		var graph_k3 = FileLoader.loadGraphFromJson("src/test/resources/fully_connected_3.json");
		var graph_k4 = FileLoader.loadGraphFromJson("src/test/resources/fully_connected_4.json");
		var graph_k5 = FileLoader.loadGraphFromJson("src/test/resources/fully_connected_5.json");
		var graph_k9 = FileLoader.loadGraphFromJson("src/test/resources/fully_connected_9.json");

		assertThat(chromaticNumber.run(graph_k3) == 3).isTrue();
		assertThat(chromaticNumber.run(graph_k4) == 4).isTrue();
		assertThat(chromaticNumber.run(graph_k5) == 5).isTrue();
		assertThat(chromaticNumber.run(graph_k9) == 9).isTrue();
	}

	@Test
	public void unlinkedNodesTest() throws FileNotFoundException {
		var graph_single = FileLoader.loadGraphFromJson("src/test/resources/single_node.json");
		
		assertThat(chromaticNumber.run(graph_single) == 1).isTrue();
	}

	@Test
	public void bipartiteTest() throws FileNotFoundException {
		var graph_bp = FileLoader.loadGraphFromJson("src/test/resources/bipartite.json");
		
		assertThat(chromaticNumber.run(graph_bp) == 2).isTrue();
	}
	
	@Test
	public void notConnectedNodesTest() throws FileNotFoundException {
		var graph_nc2 = FileLoader.loadGraphFromJson("src/test/resources/not_connected_2.json");
		var graph_nc5 = FileLoader.loadGraphFromJson("src/test/resources/not_connected_5.json");
		
		assertThat(chromaticNumber.run(graph_nc2) == 1).isTrue();
		assertThat(chromaticNumber.run(graph_nc5) == 1).isTrue();
	}

	@Test
	public void cyclesTest() throws FileNotFoundException {
		var graph_c1 = FileLoader.loadGraphFromJson("src/test/resources/cycle_even_length.json");
		var graph_c2 = FileLoader.loadGraphFromJson("src/test/resources/cycle_odd_length.json");
		var graph_c3_c5_bridged = FileLoader.loadGraphFromJson("src/test/resources/cycle_two_odd_bridged.json");
		
		assertThat(chromaticNumber.run(graph_c1) == 2).isTrue();
		assertThat(chromaticNumber.run(graph_c2) == 3).isTrue();
		assertThat(chromaticNumber.run(graph_c3_c5_bridged) == 3).isTrue();
		
	}

	@Test
	public void treeTest() throws FileNotFoundException {
		var graph_tree = FileLoader.loadGraphFromJson("src/test/resources/tree.json");
		var graph_star  = FileLoader.loadGraphFromJson("src/test/resources/star.json");

		assertThat(chromaticNumber.run(graph_tree) == 2).isTrue();
		assertThat(chromaticNumber.run(graph_star)  == 2).isTrue();
	}

	@Test
	public void petersenTest() throws FileNotFoundException {
		var graph = FileLoader.loadGraphFromJson("src/test/resources/petersen.json");
		assertThat(chromaticNumber.run(graph) == 3).isTrue();
	}
}

