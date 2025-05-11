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
	public void fullyConnectedUndirectedTest() throws FileNotFoundException {
		var graph_k3 = FileLoader.loadGraphFromJson("src/test/resources/fully_connected_undirected_3.json");
		var graph_k4 = FileLoader.loadGraphFromJson("src/test/resources/fully_connected_undirected_4.json");
		var graph_k5 = FileLoader.loadGraphFromJson("src/test/resources/fully_connected_undirected_5.json");
		var graph_k9 = FileLoader.loadGraphFromJson("src/test/resources/fully_connected_undirected_9.json");

    	assertThat(chromaticNumber.run(graph_k3) == 3).isTrue();
    	assertThat(chromaticNumber.run(graph_k4) == 4).isTrue();
    	assertThat(chromaticNumber.run(graph_k5) == 5).isTrue();
    	assertThat(chromaticNumber.run(graph_k9) == 9).isTrue();
	}

	// @Test
	// public void fullyConnectedDirectedTest() throws FileNotFoundException {
		
	// }
}

